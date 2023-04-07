package com.remarkablesoft.framework.service.board.posting.model.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.exception.BLORuntimeException;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.board.audit.view.model.impl.PostingAuditViewBLO;
import com.remarkablesoft.framework.service.board.contents.model.impl.ContentsBLO;
import com.remarkablesoft.framework.service.board.posting.vo.PostingCnd;
import com.remarkablesoft.framework.service.board.posting.vo.PostingInfo;
import com.remarkablesoft.framework.service.org.user.model.impl.UserBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserCnd;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.service.storage.file.model.impl.FileBLO;
import com.remarkablesoft.framework.service.storage.file.vo.FileCnd;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.framework.service.storage.thumbnail.model.impl.ThumbnailBLO;
import com.remarkablesoft.framework.service.storage.thumbnail.vo.ThumbnailCnd;
import com.remarkablesoft.framework.service.storage.thumbnail.vo.ThumbnailInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.EncryptUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.util.AutheUtils;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;
import com.remarkablesoft.site.kccam.service.product.model.impl.ProductBLO;
import com.remarkablesoft.site.kccam.service.product.model.impl.ProductRelBLO;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductRelInfo;

/**
 * <pre>
 * 설명 : 게시판 서비스 구현객체
 * 2016.02.04 : 1. 게시판 현재 콘텐츠(text 컬럼) 합쳐져 있는데 이것을 TB_CONTENTS로 분리하는 작업.
 *              2. 기존 게시판 뷰 카운트를 TB_HIST_VIEW 테이블로 분리하여 해당 사용자마다 뷰카운트를 따로 처리하게 수정.
 * 2019.11.27 : 답변게시판 기능 추가 - 답변을 달려면 해당 답변의 부모 OID만 PostingInfo의 setPackParentOidVC에 채워주면 된다.
 * 
 * 2021.06.01 : 다국어 구조로 변경 - 김웅기
 * </pre>
 *
 * @author James
 * @since 2015. 4. 13.
 *
 */
@BLO
public class PostingBLO {

		@Autowired
		protected PostingBLO _self;
		
		@Autowired
		protected PostingDAO postingDAO;
		
		@Autowired
		protected FileBLO fileBLO;
		
		@Autowired
		protected UserBLO userBLO;
		
		@Autowired
		protected ContentsBLO contentsBLO;
		
		@Autowired
		protected PostingAuditViewBLO postingViewAuditBLO;
		
		@Autowired
		protected ThumbnailBLO thumbnailBLO ;
		
		@Autowired
		protected ProductRelBLO productRelBLO;
		
		@Autowired
		protected ProductBLO productBLO;
		
		@Autowired
		protected PostingLangDAO postingLangDAO;
		
		/**
		 * 포스팅이 있는지 확인해서
		 * 있다면 Update 하고 없다면 Insert
		 *
		 * @param posting
		 * @return
		 */
		@CacheEvict( value = "posting", allEntries = true )
		public PostingInfo insertOrUpdate( PostingInfo posting ) {

				if ( posting == null )
						return null;

				if ( StringUtils.isEmpty( posting.getOid() ) ) {
						posting.setOid( OIDGenerator.generateOID() );
				}

				if ( postingDAO.exist( posting.getOid() ) ) {
						return _self.update( posting );
				}
				else {
						
						return _self.insert( posting );
				}

		}

		/**
		 * 포스팅 등록
		 *
		 * @param posting
		 * @return
		 */
		@CacheEvict( value = "posting", allEntries = true )
		public PostingInfo insert( PostingInfo posting ) {

				if ( posting == null ) {
						return null;
				}

				//				if ( StringUtils.isEmpty(  posting.getBoardOid() )) {
				//					throw new BLORuntimeException( "보드OID가 존재하지 않습니다" );
				//				}
				
				if ( StringUtils.isEmpty( posting.getInputUser())) {
						posting.setInputUser( AutheUtils.getLoginUserOid() );
				}


				if ( StringUtils.isEmpty( posting.getOid() ) ) {
						posting.setOid( OIDGenerator.generateOID() );
				}
				
				if( StringUtils.hasText( posting.getAnonymousPwd() ) ) {
						posting.setAnonymousPwd( EncryptUtils.encryptSHA256( posting.getAnonymousPwd() ) );
				}

				// 답글일 경우
				if ( StringUtils.hasText( posting.getThreadParentOid() )) {
						
						PostingCnd cnd = new PostingCnd();
						cnd.setOid( posting.getThreadParentOid() );
					
						PostingInfo parent = postingDAO.get( cnd );
						if ( parent != null ) {
								postingDAO.updateThreadOrderNo( parent.getThreadOid(), parent.getThreadOrderNo() );
						
								posting.setThreadOid( parent.getThreadOid() );
								posting.setThreadOrderNo( parent.getThreadOrderNo() + 1  );
								posting.setThreadDepth( parent.getThreadDepth() + 1 );
						
								// 부모객체 답글 +1
								PostingInfo parentUpdate = SystemFactory.getPostingInfo();
								parentUpdate.setReplyCount( parent.getReplyCount() + 1 );
								parentUpdate.setOid( posting.getThreadParentOid() );

								//					String fullPathOid = parent.getFullPathOid() == null ? parent.getOid() : parent.getFullPathOid();
						
								posting.setThreadFullPathOid( parent.getThreadFullPathOid() + SystemConstants.DELIMETER_DEFAULT + posting.getOid() );
								postingDAO.updateThread( parentUpdate );
						}

				}
				else {
						posting.setThreadOid( posting.getOid() );
						posting.setThreadFullPathOid( posting.getOid() );
				}
				
				String userInfoList = userBLO.convertUserInfoList( posting.getInputUser() );
				posting.setInputUserInfoList( userInfoList );

				postingDAO.insert( posting );
				
				if ( CollectionUtils.isNotEmpty( posting.getLangPostingList() ) ) {
					insertOrUpdateLangList( posting );  
				}
				else {
					postingLangDAO.insert( posting );
				}
				
//				contentsBLO.insert( contentsBLO.convertContentsInfo( posting ) );
				
				fileInsert( posting , posting.getFileList(), PostingInfo.FILE_TYPE_GENERAL);
				fileInsert( posting , posting.getContentsFileList(), PostingInfo.FILE_TYPE_CONTENTS );
				
				if ( StringUtils.hasText( posting.getObjectType() ) && StringUtils.isNotEmpty( posting.getIconFile().getStorageFileUid() ) ) {
						posting.getIconFile().setTargetObject( posting.getObjectType() );
						fileInsert( posting, posting.getIconFile(), PostingInfo.FILE_TYPE_ICON );
				}
				
				thumbnailBLO.insert( PostingInfo.getObjectType(), posting.getOid(), posting.getThumnnailList() );
				
				//관련 제품 관계 등록
				productRelInsert( posting );
				
				return posting;
		}



		/**
		 * 포스팅 업데이트
		 *
		 * @param posting
		 * @return
		 */
		@CacheEvict( value = "posting", allEntries = true )
		public PostingInfo update( PostingInfo posting ) {

				if ( posting == null )
						return null;

				if ( StringUtils.isEmpty( posting.getOid() ) ) {
						posting.setOid( OIDGenerator.generateOID() );
				}

				posting.setModDate( LocalDateTime.now() );

				if ( StringUtils.isEmpty( posting.getModUser() )) {
						posting.setModUser( AutheUtils.getLoginUserOid() );
				}

				if ( StringUtils.hasText( posting.getAnonymousPwd() ) && posting.getAnonymousPwd().length() < EncryptUtils.ENCRYPT_CHAR_LENGTH ) {
						posting.setAnonymousPwd( EncryptUtils.encryptSHA256( posting.getAnonymousPwd() ) );
				}
				
				postingDAO.update( posting );
				
				if ( CollectionUtils.isNotEmpty( posting.getLangPostingList() ) ) {
					insertOrUpdateLangList( posting );  
				}
				else {
					postingLangDAO.update( posting );
				}

				// 컨텐츠 업데이트 - 변경이력 저장
//				contentsBLO.update( posting );

				// 기존의 파일은 삭제 후 다시 입력
				fileDeleteAndInsert ( posting );
				
				//썸네일 전체를 삭제후 다시 INSERT
				thumbnailBLO.deleteAndInsert( posting.getOid(), PostingInfo.getObjectType(), posting.getThumnnailList() );
				
				//관련 제품 관계 등록
				productRelDeleteAndInsert( posting );
				
				
				return posting;
		}
		
		/**
		 * 다국어 리스트 등록
		 * 
		 * @author Woong
		 * @param posting
		 * @return
		 */
		public int insertOrUpdateLangList( PostingInfo info ) {
				if (  CollectionUtils.isEmpty( info.getLangPostingList() ) ) {
					return 0;
				}
				
				int result = 0;
				
				for ( PostingInfo postingLang : info.getLangPostingList() ) {
					String tempLang = postingLang.getLang();
					
					postingLang.setLang( tempLang.toUpperCase() );
					
					PostingCnd cnd = new PostingCnd();
					
					cnd.setOid( info.getOid() );
					cnd.setLang( postingLang.getLang() );
					
					boolean exist = postingLangDAO.exist( cnd );
					
					postingLang.setOid( info.getOid() );
					postingLang.setModDate( LocalDateTime.now() );
					
					if ( exist ) {
						result += postingLangDAO.update( postingLang );
					} else {
						postingLang.setInputDate( LocalDateTime.now() );
						result += postingLangDAO.insert( postingLang );
					}
				}
				
				return result;
		}

		/**
		 * <pre>
		 * 해당 포스팅정보만 반환.
		 * 조회카운트는 증가시키지 않음
		 *
		 * 포스팅의 파일과 등록자의 정보도 있으면 반환
		 *
		 * </pre>
		 *
		 * @param oid
		 * @return
		 */
		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public PostingInfo get( String oid ) {

				PostingCnd cnd = new PostingCnd();
				cnd.setOid( oid );
								
				return get ( cnd );
		}

		/**
		 * <pre>
		 * 해당 포스팅정보만 반환.
		 * 조회카운트는 증가시키지 않음
		 *
		 * 포스팅의 파일과 등록자의 정보도 있으면 반환
		 *
		 * </pre>
		 *
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public PostingInfo get( PostingCnd cnd ) {

				PostingInfo posting = getOnlyPosting( cnd.getOid(), cnd.getLang() );

				if ( posting == null ) {
						return null;
				}
				
				
				List<FileInfo> fileList = fileBLO.listByTarget( PostingInfo.getObjectType(), posting.getOid() );
				fillFileList ( posting, fileList );		// 일반파일과 본문내 컨텐츠까지 한번에 채운다
				
				posting.setInputUserInfo( userBLO.convertUserInfo( posting.getInputUserInfoList() ) );
				
//				posting.setContents( contentsBLO.getContents( contentsBLO.convertContentsInfo( posting ) ) );

				if( cnd.isThumbnailSearch() ) {
						fillThumbnailInfo( posting );
				}
				
				fillProductInfo( posting );
				
				if( cnd.isFillIconFile() ){
						fillIconFile( posting );
				};
				
				if( cnd.isFillLangList() && StringUtils.isNotEmpty( cnd.getOid() ) ) {
					List<PostingInfo> langList = postingLangDAO.listAll( cnd.getOid() );
					
					posting.setLangPostingList( langList );
				}
				
				return posting;
		}


		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public PostingInfo getWithPrevAndNext( PostingCnd cnd ) {

				if ( StringUtils.isEmpty( cnd.getOid() ) ) {
						return null;
				}
				
				PostingCnd postingCnd = new PostingCnd();
				
				postingCnd.setOid( cnd.getOid() );
				postingCnd.setLang( cnd.getLang() );

				PostingInfo posting = get( postingCnd );
				
				if ( posting == null) {
						return null;
				}
				
				posting.setPrevPosting( viewPrev( cnd ) );
				posting.setNextPosting( viewNext( cnd ) );

				return posting;
		}

		/**
		 * <pre>
		 * 해당 포스팅정보만 반환.
		 * 조회카운트는 증가시키지 않음
		 * </pre>
		 *
		 * @param oid
		 * @return
		 */
		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public PostingInfo getOnlyPosting( String oid, String lang ) {
				
				PostingCnd cnd = new PostingCnd();
				cnd.setOid( oid );
				cnd.setLang( lang );

				PostingInfo posting = postingDAO.get( cnd );
				return posting;
		}

		/**
		 * 보기시 조회카운트 증가
		 *
		 * @param cnd
		 * @return
		 */
		public PostingInfo view( PostingCnd cnd ) {

				PostingInfo posting = get( cnd );

				if ( posting == null ) {
						return null;
				}

				if ( StringUtils.isEmpty( cnd.getViewUserOid() )) {

						cnd.setViewUserOid( AutheUtils.getLoginUserOid() );
				}

				postingViewAuditBLO.insert ( posting, cnd.getViewUserOid() );

				return posting;
		}
		

		@CacheEvict( value = "posting", allEntries = true )
		public PostingInfo viewWithPrevAndNext( PostingCnd cnd ) {

				if ( StringUtils.isEmpty( cnd.getOid() ) ) {
						return null;
				}

				PostingInfo posting = view( cnd );
				
				if ( posting == null ) {
						return null;
				}

				if ( CollectionUtils.isEmpty( cnd.getBoardOidList() ) ) {
						cnd.setBoardOidList( cnd.getBoardOidList() );
				}
				
				if ( StringUtils.isEmpty( cnd.getBoardOid() ) ) {
						cnd.setBoardOid( cnd.getBoardOid() );
				}

				if ( cnd.isCurrentPostingSearch() && StringUtils.isNotEmpty( posting.getThreadOid() ) ) {
						posting.setCurrentPosting( viewCurrent( posting.getThreadOid() ) );
				}
				
				posting.setPrevPosting( viewPrev( cnd ) );
				posting.setNextPosting( viewNext( cnd ) );
				
				fillProductInfo( posting );

				return posting;
		}

		
		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public PostingInfo viewPrev( PostingCnd cnd ) {
				return postingDAO.getPrev( cnd );
		}

		
		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public PostingInfo viewNext( PostingCnd cnd ) {
				return postingDAO.getNext( cnd );
		}

		public List<PostingInfo> viewCurrent( String threadOid ){
				
				PostingCnd cnd = new PostingCnd();
				cnd.setThreadOid( threadOid );
						
				return listAll( cnd );
		}
		

		/**
		 * 사용자에게서 삭제는 flag 처리만함.
		 * 실제 삭제는 관리자만 삭제할 수 있다.
		 *
		 * @author james
		 * @param oid
		 * @param deleteUser
		 * @return
		 *
		 * @see terminate
		 */
		@CacheEvict( value = "posting", allEntries = true )
		public int delete( String oid, String deleteUser ) {
				
				PostingCnd cnd = new PostingCnd();
				cnd.setOid( oid );

				PostingInfo posting = postingDAO.get( cnd );

				if ( StringUtils.isEmpty( deleteUser )) {

						deleteUser = AutheUtils.getLoginUserOid();
				}

				posting.setDelUser( deleteUser );
				posting.setDelDate( LocalDateTime.now() );

				int threadCount = 0;
				int childCount = 0;

				// 루트를 삭제하면 하단까지 전부 삭제되도록 처리.
				if ( posting.getOid().equals( posting.getThreadOid() )) {

						threadCount = postingDAO.deleteFlagUpdateByThreadOid( posting );
				}
				// 루트가 아니면서 자신이 하위를 가지고 있을 경우
				else {

						if ( posting.getThreadDepth() > 0 ) {

								cnd = new PostingCnd();
								cnd.setThreadFullPathOid( posting.getOid() );
								cnd.setThreadDepth( posting.getThreadDepth() );
								List<PostingInfo> list = postingDAO.listAll( cnd );

								if ( CollectionUtils.isNotEmpty( list )) {

										for ( PostingInfo postingInfo : list ) {
												
												postingInfo.setDelUser( deleteUser );
												postingInfo.setDelDate( LocalDateTime.now() );
												
												childCount += deletePosting( postingInfo );
										}
								}

						}
						else {

								childCount = deletePosting( posting );
						}

				}

				if ( childCount > 0 ) {
						cnd = new PostingCnd();
						cnd.setThreadParentOid( posting.getThreadParentOid() );
						PostingInfo parent = postingDAO.get( cnd );
						parent.setReplyCount( parent.getReplyCount() - childCount );
						postingDAO.updateReplyCount( parent );
				}

				int result = threadCount + childCount;
				return result;
		}




		/**
		 * 포스팅 복원처리
		 * 관리자 페이지에서 관리자만 가능
		 *
		 * @author james
		 * @param oid
		 * @return
		 */
		@CacheEvict( value = "posting", allEntries = true )
		public int restore( String oid ) {
			
				PostingCnd cnd = new PostingCnd();
				cnd.setOid( oid );

				PostingInfo posting = postingDAO.get( cnd );

				int threadCount = 0;
				int childCount = 0;

				// 루트를 복원하면 하단까지 전부 복원되도록 처리.
				if ( posting.getOid().equals( posting.getThreadOid() )) {

						threadCount = postingDAO.restoreByThreadOid( posting.getThreadOid() );
				}
				// 루트가 아니면서 자신이 하위를 가지고 있을 경우
				else {

						if ( posting.getThreadDepth() > 0 ) {

								cnd = new PostingCnd();
								cnd.setThreadFullPathOid( posting.getOid() );
								List<PostingInfo> list = postingDAO.listAll( cnd );

								if ( CollectionUtils.isNotEmpty( list )) {

										for ( PostingInfo postingInfo : list ) {
												childCount += restorePosting( postingInfo.getOid() );
										}
								}

						}
						else {

								childCount = restorePosting( posting.getOid());
						}

				}

				if ( childCount > 0 ) {
						cnd.setThreadParentOid( posting.getThreadParentOid() );
						PostingInfo parent = postingDAO.get( cnd );
						parent.setReplyCount( parent.getReplyCount() + childCount );
						postingDAO.updateReplyCount( parent );
				}

				int result = threadCount + childCount;
				return result;
		}


		/**
		 * terminate는 실제 Posting도 삭제
		 * 관련된 객체도 전부 삭제
		 *
		 * @author james
		 * @param oid
		 * @return
		 */
		@CacheEvict( value = "posting", allEntries = true )
		public int terminate( String oid  ) {
				
				PostingCnd cnd = new PostingCnd();
				cnd.setOid( oid );
				
				PostingInfo posting = postingDAO.get( cnd );

				if ( posting == null ) {
					return 0;
				}

				int threadCount = 0;
				int childCount = 0;

				// 루트를 삭제하면 하단까지 전부 삭제되도록 처리.
				if ( posting.getOid().equals( posting.getThreadOid() )) {

						// threadOid로 해당 하위 list 전체를 가져와서 파일 및 컨텐츠 삭제처리.
						cnd = new PostingCnd();
						cnd.setThreadOid( posting.getThreadOid() );
						List<PostingInfo> list = postingDAO.listAll( cnd );

						terminateSub( list );

						threadCount = postingDAO.deleteByThreadOid( posting.getThreadOid() );

				}
				// 루트가 아니면서 자신이 하위를 가지고 있을 경우
				else {

						if ( posting.getThreadDepth() > 0 ) {

								cnd = new PostingCnd();
								cnd.setThreadFullPathOid( posting.getOid() );
								List<PostingInfo> list = postingDAO.listAll( cnd );
								childCount = terminateSub( list );
						}
						else {

								terminateWithRelObject( posting );
								childCount = terminatePosting( oid );
						}

				}

				if ( childCount > 0 ) {
						cnd = new PostingCnd();
						cnd.setThreadParentOid( posting.getThreadParentOid() );
						PostingInfo parent = postingDAO.get( cnd );
						parent.setReplyCount( parent.getReplyCount() - childCount );
						postingDAO.updateReplyCount( parent );
				}
				
				// 다국어 정보 삭제
				postingLangDAO.deleteAll( oid );
				
				int result = threadCount + childCount;
				return result;
		}


		/**
		 * listAll은 내부에서 사용하기 위해 삭제된 것도 다 검색.
		 *
		 * @author james
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public List<PostingInfo> listAll( PostingCnd cnd ) {

				List<PostingInfo> postingList = postingDAO.listAll( cnd );

				if ( CollectionUtils.isEmpty( postingList ) ) {
						return null;
				}

				return commonList( postingList, cnd );
		}
		

		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public PageList<PostingInfo> list( PostingCnd cnd ) {

				PageList<PostingInfo> postingList = postingDAO.list( cnd );
				
				if ( CollectionUtils.isEmpty( postingList ) ) {
						return null;
				}
				
				fillProductInfo( postingList, cnd );
				
				if( cnd.isFillIconFile() ){
						fillIconFile( postingList );
				}

				return (PageList<PostingInfo>) commonList( postingList, cnd );
		}



		/**
		 * PostingInfo 객체만 조회하여 반환합니다.
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public PageList<PostingInfo> listPostingExcludeSub( PostingCnd cnd ) {

				PageList<PostingInfo> postingList = postingDAO.list( cnd );
				return postingList;
		}
		

		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public PageList<PostingInfo> listPostingWithFile( PostingCnd cnd ) {

				PageList<PostingInfo> postingList = list( cnd );

				if ( postingList == null )
						return null;

				List<String> targetOidList = postingList.stream().map( PostingInfo::getOid ).collect( Collectors.toList() );

				// 첨부파일 및 컨텐츠내 파일을 다 맞춰준다.
				List<FileInfo> fileList = fileBLO.listByTarget( PostingInfo.getObjectType(), targetOidList );
				fillFileInfo( postingList, fileList );

				return (PageList<PostingInfo>) commonList( postingList, cnd );
		}
		
		

		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public int listPostingCount( PostingCnd cnd ) {
				return postingDAO.listCount( cnd );
		}
		

		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public int getNumber( PostingCnd cnd ) {
				return postingDAO.getNumber( cnd );
		}
		
		
		/**
		 * 기존비밀번호가 맞는지 확인합니다.
		 * 
		 * @author sirena
		 * @param postingInfo
		 * @return
		 */
		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public boolean checkAnonymousPwd( PostingInfo postingInfo ) {

				if ( StringUtils.isEmpty( postingInfo.getOid() ) ) {
						throw new BLORuntimeException( "사용자 oid가 비어있습니다." );
				}

				if ( StringUtils.isEmpty( postingInfo.getAnonymousPwd() ) ) {
						throw new BLORuntimeException( "체크할 비밀번호가 비어있습니다." );
				}

				//	비밀번호 암호화
				if ( StringUtils.hasText( postingInfo.getAnonymousPwd() ) ) {
						postingInfo.setAnonymousPwd( EncryptUtils.encryptSHA256( postingInfo.getAnonymousPwd() ) );
				}

				PostingCnd cnd = new PostingCnd();
				cnd.setPostingInfo( postingInfo );

				return postingDAO.checkAnonymousPwd( cnd );
		}
		
		
		/**
		 * oid, password를 받아와 기존비밀번호와 일치여부를 확인합니다.
		 * 
		 * @author sirena
		 * @param oid
		 * @param password
		 * @return
		 */
		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public boolean checkAnonymousPwd( String oid, String password ) {

				PostingInfo postingInfo = SystemFactory.getPostingInfo();
				postingInfo.setOid( oid );
				postingInfo.setAnonymousPwd( password );

				return checkAnonymousPwd( postingInfo );
		}
		
		
		/**
		 * 원하는 조건에 따라 동적으로 칼럼을 생성하여
		 * grouptby 로 불러옴
		 * 
		 * @author max
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public List<Map<String, Object>> listRank ( PostingCnd cnd ) {
				
				return postingDAO.listRank( cnd );
		}

		/**
		 * 내가 쓴 답글의 원글 리스트.
		 * 
		 * @author sirena
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public PageList<PostingInfo> listMyAnswerOfParent(PostingCnd cnd) {
				
			PageList<PostingInfo> postingList = postingDAO.listMyAnswerOfParent( cnd );

			if ( CollectionUtils.isEmpty( postingList ) ) {
					return null;
			}

			return (PageList<PostingInfo>) commonList( postingList, cnd );
		}

		
		
		
		/**
		 * 채택여부에 따라 리스트를 가져옵니다.
		 * 
		 * @author sirena
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "posting" , keyGenerator = "cacheKeyGenerator")
		public PageList<PostingInfo> listPick( PostingCnd cnd ) {
				
				PageList<PostingInfo> postingList = postingDAO.listPick( cnd );

				if ( CollectionUtils.isEmpty( postingList ) ) {
						return null;
				}

				return (PageList<PostingInfo>) commonList( postingList, cnd );
		}
		
		
		protected List<PostingInfo> commonList( List<PostingInfo> postingList, PostingCnd cnd ) {

				List<String> targetUserList = postingList.stream().map( PostingInfo::getInputUser ).distinct().collect( Collectors.toList() );
				List<String> targetOidList = postingList.stream().map( PostingInfo::getOid ).collect( Collectors.toList() );
				
				// 포스팅의 inputUserInfoList로 등록자를 채운다.
				if ( CollectionUtils.isNotEmpty( targetUserList ) ) {
						fillInputUser( postingList , cnd.isFillInputUserProfile() , cnd.isUserSearch());
				}

				// 내가 읽었는지 확인
				if ( CollectionUtils.isNotEmpty( targetOidList ) ) {
						postingViewAuditBLO.fillReadYn( postingList, targetOidList );
				}
				
				// 원글에서 채택한 답변리스트 가져오기
				if ( cnd.isFillPickedPosting() ) {
						fillPickedPostingInfo( postingList, targetOidList );
				}
				
				// 내용을 채웁니다.
//				if ( cnd.isFillContents() || cnd.isFillContentsWithoutHtmlTag()) {
//						contentsBLO.fillPostContents( postingList, targetOidList, cnd.isFillContentsWithoutHtmlTag() );
//				}
				
				// 썸네일
				if ( cnd.isThumbnailSearch() ) {
						fillThumbnailList( postingList, targetOidList);
				}
				

				return postingList;
		}
		
		
		/**
		 * 포스팅의 inputUserInfoList로 등록자정보를 채운다.
		 * 
		 * @author james
		 * @param postingList			포스팅 리스트
		 * @param isFillUserProfile		사용자의 프로파일을 채울지 여부
		 * @param isUserSearch   		실제 userBLO에서 검색할지 여부
		 */
		protected void fillInputUser( List<PostingInfo> postingList, boolean isFillUserProfile, boolean isUserSearch ) {
				
				List<UserInfo> userInfoList = new ArrayList<UserInfo>();
				for ( PostingInfo postingInfo : postingList ) {
						
						if ( !isUserSearch) {
							postingInfo.setInputUserInfo( userBLO.convertUserInfo( postingInfo.getInputUserInfoList() ) );	
						}
						
						userInfoList.add( postingInfo.getInputUserInfo() );
				}
				
				if ( isUserSearch && userInfoList.size() > 0) {
						fillInputUser( postingList );	
				}
				
				if ( isFillUserProfile && userInfoList.size() > 0) {
						fileBLO.fillProfile( userInfoList);
				}
				 
				userInfoList = null;
		}



		/**
		 * 하위 포스팅 제거시 사용
		 *
		 * @author james
		 * @param list
		 * @param deleteUser
		 * @return
		 */
		protected int terminateSub( List<PostingInfo> list ) {

				if ( CollectionUtils.isEmpty( list )) {
						return 0;
				}

				// 게시물에 달린 파일 및 컨텐츠 삭제
				for ( PostingInfo postingInfo : list ) {

						terminateWithRelObject ( postingInfo);
						terminatePosting( postingInfo.getOid() );
				}

				return list.size();
		}


		/**
		 * 포스팅에 관련된 객체들을 전부 제거
		 * 파일, 컨텐츠, 태그, 의견
		 *
		 *
		 * @author james
		 * @param posting
		 */
		protected void terminateWithRelObject( PostingInfo posting) {

				// 파일삭제
				fileBLO.deleteByTarget( posting.getOid(), PostingInfo.getObjectType() );

				// 컨텐츠
				contentsBLO.deleteByTarget( posting.getOid(), PostingInfo.getObjectType() );

		}


		/**
		 * 포스팅 삭제 flag 업데이트
		 *
		 * @author james
		 * @param posting
		 * @param deleteUser
		 * @return
		 */
		protected int deletePosting( PostingInfo posting ) {

				return postingDAO.deleteFlagUpdate( posting );

		}


		/**
		 *
		 * 포스팅 복원
		 * oid로 delUser = null, delDate = null 처리
		 *
		 * @author james
		 * @param oid
		 * @return
		 */
		protected int restorePosting( String oid ) {

				return postingDAO.restorePosting( oid );

		}


		/**
		 * 실제 포스팅 삭제
		 *
		 * @author james
		 * @param oid
		 * @return
		 */
		protected int terminatePosting( String oid) {

				return postingDAO.delete( oid );
		}
		
		
		/**
		 * 
		 * @param postingList
		 * @param targetOidList
		 * @param boardOid
		 */
		protected void fillPickedPostingInfo( List<PostingInfo> postingList, List<String> targetOidList ) {

				if ( CollectionUtils.isEmpty( postingList ) || CollectionUtils.isEmpty( targetOidList ) ) {
						return;
				}
				
				PostingCnd cnd = new PostingCnd();
				
				cnd.setThreadOidList( targetOidList );
				cnd.setPickYn( SystemConstants.FLAG_YES );
				cnd.setThreadDepth( cnd.getThreadDepth() + 1 );	
				
				//자식List가져오기
				List< PostingInfo> childList = postingDAO.listAll( cnd ); 
				
				postingList.forEach( parent -> childList.stream()
									 			.filter( child -> parent.getOid().equals( child.getThreadParentOid() ) ) 
												.forEach( child -> {
														parent.addObjectMap( PostingInfo.SUB_PICK , child );
												}));
				
		}
		
		
		/**
		 * posting내 파일리스트를 채운다.
		 * 첨부파일리스트와 본문내 파일리스트를 같이처리
		 * 
		 * @author james
		 * @param posting
		 * @param fileList
		 */
		protected void fillFileList( PostingInfo posting, List<FileInfo> fileList ) {

				if ( CollectionUtils.isEmpty(  fileList )) {
						return ;
				}
				
				fileList.stream()
        				.filter( file -> file != null && posting.getOid() != null)
        				.filter( file -> PostingInfo.getObjectType().equals( file.getTargetObject() ))
        				.filter( file -> posting.getOid().equals( file.getTargetOid() ) )
        				.forEach( file ->  {
        					
        						if ( PostingInfo.FILE_TYPE_GENERAL.equals(  file.getFileType() )) {
        						
        								posting.addFile( file );
        						}
        						else if ( PostingInfo.FILE_TYPE_CONTENTS.equals(  file.getFileType() )) {
        								
        								posting.addContentsFile( file );
        						}
        						
        				});
				
		}
		
		
		
		/**
		 * 파일등록
		 * 
		 * @author james
		 * @param posting
		 * @param fileInfoList
		 * @param fileType
		 * @return
		 */
		protected int fileInsert(  PostingInfo posting, List<FileInfo> fileInfoList, String fileType ) {

				if ( posting == null ||  CollectionUtils.isEmpty( fileInfoList ) ) {
						return 0;
				}

				
				for ( FileInfo fileInfo : fileInfoList ) {

						assertThat( fileInfo.getStorageFileUid() ).as( "StorageFileUid가 없습니다." ).isNotEmpty();
						
						fileInfo.setContainerOid( posting.getBoardOid() );
						fileInfo.setOid( OIDGenerator.generateOID() );
						fileInfo.setTargetObject( PostingInfo.getObjectType() );
						fileInfo.setTargetOid( posting.getOid() );
						fileInfo.setFileType( fileType );
						
						fileInfo.setInputUser( posting.getInputUser() );
						fileInfo.setInputDate( LocalDateTime.now() );
						
						fileBLO.insert( fileInfo );
				}

				return fileInfoList.size();
				
		}
		/**
		 * 파일등록(대표이미지)
		 *
		 * @author jiyoung
		 * @param posting
		 * @param fileInfo
		 * @param fileType
		 * @return
		 */
		protected void fileInsert( PostingInfo posting, FileInfo fileInfo, String fileType ){
				
				
				if( posting == null || fileInfo == null ){
						return;
				}
				
				assertThat( fileInfo.getStorageFileUid() ).as( "StorageFileUid가 없습니다." ).isNotEmpty();
				
				fileInfo.setContainerOid( posting.getBoardOid() );
				fileInfo.setOid( OIDGenerator.generateOID() );
				fileInfo.setTargetObject( PostingInfo.getObjectType() );
				fileInfo.setTargetOid( posting.getOid() );
				fileInfo.setFileType( fileType );
				
				fileInfo.setInputUser( posting.getInputUser() );
				fileInfo.setInputDate( LocalDateTime.now() );
				
				fileBLO.insert( fileInfo );
				
		}
		
		
		/**
		 * 포스팅객체의 첨부파일 일과 삭제 후 등록
		 *
		 * @author james
		 * @param posting
		 * @return
		 */
		protected int fileDeleteAndInsert( PostingInfo posting ) {

				FileCnd cnd = new FileCnd();
				cnd.setTargetOid( posting.getOid() );
				cnd.setTargetObject( PostingInfo.getObjectType() );
				fileBLO.deleteByCnd( cnd );
				
				// 등록은 일반과 본문 두부분.
				int result = fileInsert( posting, posting.getFileList(), PostingInfo.FILE_TYPE_GENERAL );
				result += fileInsert( posting, posting.getContentsFileList(), PostingInfo.FILE_TYPE_CONTENTS );
				
				if ( StringUtils.hasText( posting.getObjectType() ) && posting.getIconFile() != null && StringUtils.isNotEmpty( posting.getIconFile().getStorageFileUid() ) ) {
						posting.getIconFile().setTargetObject( posting.getObjectType() );
						fileInsert( posting, posting.getIconFile(), PostingInfo.FILE_TYPE_ICON );
				}
				
				return result;
		}
		
		/*
		* 포스팅과 관련된 제품정보를 저장
		* */
		protected void productRelInsert( PostingInfo postingInfo ){
				
				if( postingInfo == null ){
						return;
				}
				
				if( postingInfo.getProductInfo() == null ){
						return;
				}
				
				ProductRelInfo relInfo = new ProductRelInfo();
				relInfo.setTargetOid( postingInfo.getOid() );
				relInfo.setTargetObject( SystemConstants.OBJECT_FW_TYPE_POSTING.getKey() );
				relInfo.setProductOid( postingInfo.getProductInfo().getOid() );
				
				productRelBLO.insert( relInfo );
		}
		
		/*
		* 포스팅과 관련된 제품정보를 삭제 후 저장
		* */
		protected void productRelDeleteAndInsert( PostingInfo postingInfo ){
		
				if( postingInfo == null ){
						return;
				}
				if( postingInfo.getProductInfo() == null ){
						return;
				}
			
				ProductCnd cnd = new ProductCnd();
				cnd.setTargetOid( postingInfo.getOid() );
				cnd.setTargetObject( SystemConstants.OBJECT_FW_TYPE_POSTING.getKey() );
			
				productRelBLO.deleteByTarget( cnd );
			
				productRelInsert( postingInfo );
		}
		
		/*
		* 포스팅 객체에 제품정보를 세팅합니다
		* @param postingInfo
		* */
		protected void fillProductInfo( PostingInfo postingInfo ){
				if( postingInfo == null || StringUtils.isEmpty( postingInfo.getOid() )){
						return;
				}
				ProductCnd cnd = new ProductCnd();
				cnd.setTargetOid( postingInfo.getOid() );
				cnd.setTargetObject( SystemConstants.OBJECT_FW_TYPE_POSTING.getKey() );
				
				ProductRelInfo relInfo = productRelBLO.get( cnd );
				
				if( relInfo == null || StringUtils.isEmpty( relInfo.getProductOid() )){
						return;
				}
				
				cnd.setTargetOid( "" );
				cnd.setTargetObject( "" );
				cnd.setOid( relInfo.getProductOid() );
				
				ProductInfo productInfo = productBLO.get( cnd );
				
				if( productInfo == null ){
						return;
				}
				
				postingInfo.setProductInfo( productInfo );
		}
		
		/*
		* 포스팅 리스트에 관련 제품 정보를 채워줍니다.
		* */
		protected void fillProductInfo( List<PostingInfo> postingList, PostingCnd cnd ){
				if ( !cnd.isFillProductInfo() ){
						return;
				}
			
				for( PostingInfo info : postingList ){
						fillProductInfo( info );
				}
		}
		
		/*
		 * 포스팅 리스트에 대표 이미지를 채워줍니다.
		 * */
		protected void fillIconFile( List<PostingInfo> list ){
				if ( CollectionUtils.isEmpty( list ) ) {
						return;
				}
				
				List<String> targetOidList = list.stream().map( PostingInfo::getOid ).collect( Collectors.toList());
				
				FileCnd fileCnd = new FileCnd();
				fileCnd.setTargetOidList( targetOidList );
				fileCnd.setTargetObject( PostingInfo.getObjectType() );
				
				List<FileInfo> fileList = fileBLO.listAll( fileCnd );
				
				if ( CollectionUtils.isEmpty( fileList ) ) {
						return;
				}
				
				list.forEach( posting -> fileList.stream()
												.filter( file -> file !=null && StringUtils.hasText( posting.getOid() ) )
												.filter( file -> posting.getOid().equals( file.getTargetOid() ) )
												.forEach( file -> posting.setIconFile( file ) ) );
		}
		
		protected void fillIconFile( PostingInfo info ){
				if( info == null ){
						return;
				}
				
				FileCnd fileCnd = new FileCnd();
				fileCnd.setTargetOid( info.getOid() );
				fileCnd.setTargetObject( PostingInfo.getObjectType() );
				
				info.setIconFile( fileBLO.getByCnd( fileCnd ) );
		}
		
		/**
		 * 포스팅리스트객체에 파일정보를 셋팅한다
		 *
		 * @param postingList
		 * @param fileList
		 */
		protected void fillFileInfo( PageList<PostingInfo> postingList, List<FileInfo> fileList ) {

				if ( CollectionUtils.isEmpty( postingList ) || CollectionUtils.isEmpty( fileList ) ) {
						return;
				}

				postingList.forEach( posting -> fileList.stream()
										.filter( file -> file != null && posting.getOid() != null)
										.filter( file -> PostingInfo.getObjectType().equals( file.getTargetObject() ))
										.filter( file -> posting.getOid().equals( file.getTargetOid() ) )
										.forEach( file ->  {
											
												if ( PostingInfo.FILE_TYPE_GENERAL.equals(  file.getFileType() )) {
												
														posting.addFile( file );
												}
												else if ( PostingInfo.FILE_TYPE_CONTENTS.equals(  file.getFileType() )) {
														
														posting.addContentsFile( file );
												}
												
										})
									);

		}
		
		
		/**
		 * 포스팅리스트 객체에 썸네일 정보들을 채웁니다.
		 * 
		 * @param postingList
		 * @param targetOidList
		 */
		protected void fillThumbnailList( List<PostingInfo> postingList, List<String> targetOidList ) {

				if ( CollectionUtils.isEmpty( postingList ) || CollectionUtils.isEmpty( targetOidList ) ) {
						return;
				}

				ThumbnailCnd thumbnailCnd = new ThumbnailCnd();
				thumbnailCnd.setTargetObject( PostingInfo.getObjectType() );
				thumbnailCnd.setTargetOidList( targetOidList );

				List<ThumbnailInfo> list = thumbnailBLO.listAll( thumbnailCnd );

				if ( CollectionUtils.isEmpty( list ) ) {
						return;
				}

				postingList.forEach( posting -> list.stream()
													.filter( thumbnail -> StringUtils.hasText( thumbnail.getOid() ) )
													.filter( thumbnail -> thumbnail.getTargetOid().equals( posting.getOid() ) )
													.forEach( thumbnail -> posting.addThumnnail( thumbnail ) ) );

		}
		
		
		
		/**
		 * 포스팅리스트객체에 사용자를 셋팅한다
		 *
		 * @param postingList
		 * @param userList
		 */
		protected void fillInputUser( List<PostingInfo> postingList ) {

				
				if ( CollectionUtils.isEmpty( postingList ) ) {
						return;
				}
				
				List<String> userOids = postingList.stream().map( PostingInfo::getInputUser ).collect( Collectors.toList() );
				
				UserCnd cnd = new UserCnd();
				cnd.setUserOidList( userOids );
				
				List<UserInfo> userList = userBLO.listAll( cnd );
				
				if ( CollectionUtils.isEmpty( userList ) ) {
						return;
				}

				postingList.forEach( posting -> userList.stream()
														.filter( user -> user != null && posting.getInputUser() != null )
														.filter( user -> posting.getInputUser().equals( user.getOid() ) )
														.forEach( user -> posting.setInputUserInfo( user ) ) );

		}
		
		
		
		/**
		 * 포스팅 객체에 썸네일 정보들을 채웁니다.
		 * 
		 * @param posting
		 */
		protected void fillThumbnailInfo( PostingInfo posting ) {

				if ( posting == null ) {

						return;
				}

				ThumbnailCnd thumbnailCnd = new ThumbnailCnd();
				thumbnailCnd.setTargetObject( PostingInfo.getObjectType() );
				thumbnailCnd.setTargetOid( posting.getOid() );

				List<ThumbnailInfo> list = thumbnailBLO.listAll( thumbnailCnd );

				if ( CollectionUtils.isEmpty( list ) ) {
						return;
				}

				posting.setThumnnailList( list );
		}
		
}