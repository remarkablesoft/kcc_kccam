package com.remarkablesoft.framework.service.board.audit.view.model.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.board.audit.view.vo.PostingAuditViewCnd;
import com.remarkablesoft.framework.service.board.audit.view.vo.PostingAuditViewInfo;
import com.remarkablesoft.framework.service.board.posting.model.impl.PostingDAO;
import com.remarkablesoft.framework.service.board.posting.vo.PostingInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.DateUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.util.AutheUtils;
import com.remarkablesoft.framework.web.util.WebUtils;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board - audit - view
 * 		@프로그램 ID		:	PostingAuditViewBLO
 * 		@프로그램 개요 		:	포스팅 보기이력 BLO
 *                          - 포스팅은 기존 AuditViewInfo 객체를 사용하지하지 않고 이 객체를 이용한다
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	james	-	신규생성
 * 		1.1  2020. 07. 13.	:	max		-	칼럼별 검색 및 그룹바이 정렬 메소드 추가
 * 		============================================================================
 */
@BLO
public class PostingAuditViewBLO {

		@Autowired
		protected PostingAuditViewDAO postingAuditViewDAO;
		
		@Autowired
		protected PostingDAO postingDAO;

		/**
		 * 포스팅객체를 받아서 보기이력 생성
		 *
		 * @author james
		 * @param posting
		 * @param viewUserOid
		 * @return
		 */
		public PostingAuditViewInfo insert( PostingInfo posting, String viewUserOid ) {

				String viewUserIp = WebUtils.getClientIp();
				
				PostingAuditViewCnd cnd = new PostingAuditViewCnd();
				cnd.setViewUser( viewUserOid );
				cnd.setViewUserIp( viewUserIp );
				cnd.setPostingOid( posting.getOid() );
				
				if ( postingAuditViewDAO.exist( cnd )) {
						return null;
				}
				
				PostingAuditViewInfo info = SystemFactory.getPostingAuditViewInfo();

				info.setOid( OIDGenerator.generateOID() );
				info.setBoardOid( posting.getBoardOid() );
				info.setPostingOid( posting.getOid() );
				info.setViewUser( viewUserOid );
				info.setViewDate( LocalDateTime.now() );
				info.setViewUserIp( viewUserIp );
				
				Map<String, String> dateMap = DateUtils.getDate();
				info.setViewYear( dateMap.get( DateUtils.DF_PARAM_YEAR ) );
				info.setViewMonth( dateMap.get( DateUtils.DF_PARAM_MONTH ));
				info.setViewWeek( dateMap.get( DateUtils.DF_PARAM_WEEK ) );
				info.setViewDay( dateMap.get( DateUtils.DF_PARAM_DAY ) );
				info.setViewHour( dateMap.get( DateUtils.DF_PARAM_HOUR ) );

				int result = postingAuditViewDAO.insert( info );
				
				// 포스팅의 조회카운트 증가 -> 현재 뉴스룸만 이용하므로 사용 x
//				postingDAO.incrementViewCount( posting );

				return result > 0 ? info : null;
		}


		public PostingAuditViewInfo get( PostingAuditViewCnd cnd ) {

				return postingAuditViewDAO.get( cnd );
		}

		public int delete( String oid ) {

				return postingAuditViewDAO.delete( oid );
		}

		public PageList<PostingAuditViewInfo> list( PostingAuditViewCnd cnd ) {

				return postingAuditViewDAO.list( cnd );
		}

		public List<PostingAuditViewInfo> listAll( PostingAuditViewCnd cnd ) {

				return postingAuditViewDAO.listAll( cnd );
		}


		/**
		 * 포스팅객체의 보기 카운트
		 *
		 * @author james
		 * @param posting
		 * @return
		 */
		public int getPostingViewCount( PostingInfo posting ) {

				PostingAuditViewCnd cnd = new PostingAuditViewCnd();
				cnd.setBoardOid( posting.getBoardOid() );
				cnd.setPostingOid( posting.getOid() );

				return postingAuditViewDAO.getPostingViewCount( cnd );
		}


		/**
		 *
		 * 포스팅 아이디 리스트로 포스팅뷰 리스트를 찾아서 반환
		 *
		 * @author james
		 * @param postingOidList
		 * @return
		 */
		public List<PostingAuditViewInfo> listCountByPostingOidList( List<String> postingOidList ) {

				PostingAuditViewCnd cnd = new PostingAuditViewCnd();
				cnd.setPostingOidList( postingOidList );

				return postingAuditViewDAO.listCountByPostingOidList( cnd );
		}


		/**
		 * postingOidList만큼 포스팅을 봤는지 확인.
		 *
		 * @author james
		 * @param postingOidList
		 * @return
		 */
		public List<PostingAuditViewInfo> listByLoginUserReadYn( List<String> postingOidList ) {

				String loginUser = AutheUtils.getLoginUserOid();

				if ( StringUtils.isEmpty( loginUser )) {
					return null;
				}

				PostingAuditViewCnd cnd = new PostingAuditViewCnd();
				cnd.setPostingOidList( postingOidList );
				cnd.setViewUser( loginUser );

				return postingAuditViewDAO.listByLoginUserReadYn( cnd );
		}


		/**
		 * 포스팅을 봤는지 체크해서 ReadYN을 채운다.
		 *
		 * @author james
		 * @param postingList
		 * @param viewList
		 */
		public void fillReadYn( List<PostingInfo> postingList, List<String> targetOidList ) {

				if ( CollectionUtils.isEmpty( postingList ) || CollectionUtils.isEmpty( targetOidList ) )
						return;

				List<PostingAuditViewInfo> histViewList = listByLoginUserReadYn( targetOidList );
				
				if ( CollectionUtils.isEmpty( histViewList ) ) {
						return;	
				}
				
				postingList.forEach( posting -> histViewList.stream()
												.filter( view -> posting.getOid().equals( view.getPostingOid() ))
												.forEach( view -> posting.setReadYnVC( SystemConstants.FLAG_YES ) )
										);
		}
		
		/**
		 * 원하는 조건에 따라 동적으로 칼럼을 생성하여
		 * grouptBy로 정렬 후 ListMap 을 반환한다
		 * 
		 * @author max
		 * @param cnd
		 * @return List<Map<String, Object>> 
		 */
		public List<Map<String, Object>> listRank ( PostingAuditViewCnd cnd ) {
				
				return postingAuditViewDAO.listRank( cnd );
		}
		
		
}
