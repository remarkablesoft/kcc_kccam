package com.remarkablesoft.framework.service.board.audit.view.model.impl;

import java.util.List;
import java.util.Map;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.board.audit.view.vo.PostingAuditViewCnd;
import com.remarkablesoft.framework.service.board.audit.view.vo.PostingAuditViewInfo;
import com.remarkablesoft.framework.util.StringUtils;


/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board - audit - view
 * 		@프로그램 ID		:	PostingAuditViewDAO
 * 		@프로그램 개요 		:	포스팅 보기이력 DAO
 *                          - 포스팅은 기존 AuditViewInfo 객체를 사용하지하지 않고 이 객체를 이용한다
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	james	-	신규생성
 * 		============================================================================
 */
@DAO
public class PostingAuditViewDAO extends BaseDAO{

		public int insert( PostingAuditViewInfo info ) {

				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}

				return sql().insert( id( "insert" ), info );
		}

		public PostingAuditViewInfo get( PostingAuditViewCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}


		public int getPostingViewCount( PostingAuditViewCnd cnd ) {
				return sql().selectOne( id( "getPostingViewCount" ), cnd );
		}

		public int delete( String oid ) {
				return sql().delete( id( "delete" ), oid );
		}

		public PageList<PostingAuditViewInfo> list( PostingAuditViewCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "list" ), cnd );
		}

		public List<PostingAuditViewInfo> listAll( PostingAuditViewCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}

		/**
		 * 포스팅 아이디 리스트로 포스팅뷰 리스트를 찾아서 반환
		 *
		 * @author james
		 * @param cnd
		 * @return
		 */
		public List<PostingAuditViewInfo> listCountByPostingOidList( PostingAuditViewCnd cnd ) {

				return sql().selectList( id( "listCountByPostingOidList" ), cnd );
		}


		/**
		 * 포스팅을 로그인한 사용자가 봤는지 확인
		 *
		 * @author james
		 * @param cnd
		 * @return
		 */
		public List<PostingAuditViewInfo> listByLoginUserReadYn( PostingAuditViewCnd cnd ) {

				return sql().selectList( id( "listByLoginUserReadYn" ), cnd );
		}

        /**
         * 해당 포스팅이력 테이블에 값이 있는지 확인
         *
         * @author james
         * @param info
         * @return
         */
		public boolean exist( PostingAuditViewCnd cnd ) {

				Object obj = sql().selectOne( id( "exist" ), cnd );
				return convertIntegerToBoolean( obj );
		}
		
		/**
		 * 원하는 조건에 따라 동적으로 칼럼을 생성하여
		 * grouptby 로 불러옴
		 * 
		 * @author max
		 * @param cnd
		 * @return
		 */
		public List<Map<String, Object>> listRank ( PostingAuditViewCnd cnd ) {
				
				
				return sql().selectList( id( "listRank" ), cnd );
		}
		
}
