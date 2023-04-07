package com.remarkablesoft.framework.service.board.contents.model.impl;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsCnd;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;
import com.remarkablesoft.framework.service.board.posting.vo.PostingInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.util.AutheUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 설명 : Contents BLO 객체
 * </pre>
 *
 * @author James
 * @since 2016. 2. 04.
 *
 */
@BLO
public class ContentsBLO {

		@Autowired
		protected ContentsDAO contentsDAO;


		public ContentsInfo insert( ContentsInfo info ) {

				/*
				 * if ( StringUtils.isEmpty( info.getContents() )) {
				 * throw new BLORuntimeException( "등록할 컨텐츠 내용이 없습니다." );
				 * }
				 */

				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}

				if ( StringUtils.isNotEmpty( info.getContents() ) ) {
						info.setContentsSize( info.getContents().getBytes().length );
				}

				if ( StringUtils.isEmpty( info.getInputUser() ) ) {
						info.setInputUser( AutheUtils.getLoginUserOid() );
				}

				contentsDAO.insert( info );

				return info;
		}
		
		/**
		* 다수의 콘텐츠 정보를 한번에 저장합니다.
		*
		* @param list
		* @return int
		* @author 최원준
		*/
		public int insertBulk( List<ContentsInfo> list ) {
			
				if( CollectionUtils.isEmpty( list ) ){
						return 0;
				}
				
				list.forEach( info -> {
						if ( StringUtils.isEmpty( info.getOid() ) ) {
							info.setOid( OIDGenerator.generateOID() );
						}
						
						if ( StringUtils.isNotEmpty( info.getContents() ) ) {
							info.setContentsSize( info.getContents().getBytes().length );
						}
						
						if ( StringUtils.isEmpty( info.getInputUser() ) ) {
							info.setInputUser( AutheUtils.getLoginUserOid() );
						}
				} );
			
				return contentsDAO.insertBulk( list );

		}

		public int update( ContentsInfo info ) {

				if ( StringUtils.isEmpty( info.getContents() ) ) {
						//						throw new BLORuntimeException( "업데이트할 컨텐츠 내용이 없습니다." );
						return 0;
				}

				if ( StringUtils.isNotEmpty( info.getContents() ) ) {
						info.setContentsSize( info.getContents().getBytes().length );
				}
				

				return contentsDAO.update( info );
		}

		/**
		 * posting 전용 업데이트 메소드
		 *
		 *
		 * @author james
		 * @param posting
		 * @return
		 */
		public int update( PostingInfo posting ) {

				if ( posting == null || StringUtils.isEmpty( posting.getContents() ) ) {

						return 0;
				}

				ContentsInfo contents = convertContentsInfo( posting );
				return update( contents );

		}

		public ContentsInfo get( ContentsInfo info ) {
				return contentsDAO.get( info );
		}

		public String getContents( ContentsInfo info ) {
				if ( info == null || StringUtils.isEmpty( info.getTargetOid() ) ) {
						return null;
				}
				return contentsDAO.getContents( info );
		}

		public List<ContentsInfo> list( ContentsCnd cnd ) {
				return contentsDAO.list( cnd );
		}

		public List<ContentsInfo> list( String targetOid, String targetObject ) {

				if ( StringUtils.isEmpty( targetOid ) || StringUtils.isEmpty( targetObject ) ) {
						return null;
				}

				ContentsCnd cnd = new ContentsCnd();
				cnd.setTargetOid( targetOid );
				cnd.setTargetObject( targetObject );

				return list( cnd );

		}

		public List<ContentsInfo> list( List<String> targetOidList, String targetObject ) {

				if ( CollectionUtils.isEmpty( targetOidList ) || StringUtils.isEmpty( targetObject ) ) {
						return null;
				}

				ContentsCnd cnd = new ContentsCnd();

				cnd.setTargetOidList( targetOidList );
				cnd.setTargetObject( targetObject );

				return list( cnd );

		}

		public int delete( ContentsCnd cnd ) {

				if ( StringUtils.isEmpty( cnd.getTargetOid() ) || StringUtils.isEmpty( cnd.getTargetObject() ) ) {
						return 0;
				}

				return contentsDAO.delete( cnd );
		}

		public void deleteByTarget( String targetOid, String targetObject ) {

				ContentsCnd cnd = new ContentsCnd();
				cnd.setTargetOid( targetOid );
				cnd.setTargetObject( targetObject );

				delete( cnd );
		}
		
		public void deleteByTarget( String targetOid, String targetObject, String lang ) {
				
				ContentsCnd cnd = new ContentsCnd();
				cnd.setTargetOid( targetOid );
				cnd.setTargetObject( targetObject );
				cnd.setLang( lang );
				
				delete( cnd );
		}

		/**
		 * Posting 객체를 Contents 객체로 변경
		 *
		 * @param posting
		 * @return
		 */
		public ContentsInfo convertContentsInfo( PostingInfo posting ) {

				ContentsInfo info = SystemFactory.getContentsInfo();

				info.setContents( posting.getContents() );
				info.setTargetObject( PostingInfo.getObjectType() );
				info.setTargetOid( posting.getOid() );
				info.setInputUser( posting.getInputUser() );
				info.setContainerOid( posting.getBoardOid() );

				//get에서 사용하는경우 targetOid와 targetObject만으로 contentsInfo객체를 만드는 경우가 있어 추가 
				if ( StringUtils.hasText( posting.getContents() ) ) {
						info.setContentsSize( posting.getContents().getBytes().length );
				}

				return info;
		}

		/**
		 * 포스트의 내용을 채워주기 위해 사용합니다.
		 * 
		 * @author sirena
		 * @param postingList
		 * @param targetOidList
		 */
		public void fillPostContents( List<PostingInfo> postingList, List<String> targetOidList ) {

				fillPostContents( postingList, targetOidList, false);
		}
		
		/**
		 * 포스트의 내용을 채워주기 위해 사용합니다.
		 * 
		 * @author sirena
		 * @param postingList
		 * @param targetOidList
		 */
		public void fillPostContents( List<PostingInfo> postingList, List<String> targetOidList , boolean removeHtmlTag) {

				if ( CollectionUtils.isEmpty( postingList ) || CollectionUtils.isEmpty( targetOidList ) ) {
						return;
				}
				
				List<ContentsInfo> contentsList = list( targetOidList, PostingInfo.getObjectType() );
				
				if ( CollectionUtils.isEmpty( contentsList ) ) {
						return;	
				}
				
				postingList.forEach( posting -> contentsList.stream()
												.filter( contents -> posting.getOid().equals( contents.getTargetOid() ) )
												.forEach( contents ->  {
													
														String szContents = contents.getContents();
														if ( StringUtils.isNotEmpty( szContents ) && removeHtmlTag) {
																
																szContents = 	StringUtils.html2text( szContents ) ;
														}
														posting.setContents( szContents );
												} )
									);
				
		}

}
