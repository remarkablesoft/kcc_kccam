package com.remarkablesoft.site.kccam.service.newsroom.model.impl;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.board.audit.view.model.impl.PostingAuditViewBLO;
import com.remarkablesoft.framework.service.board.posting.vo.PostingInfo;
import com.remarkablesoft.framework.service.storage.file.model.impl.FileBLO;
import com.remarkablesoft.framework.service.storage.file.vo.FileCnd;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.util.AutheUtils;
import com.remarkablesoft.site.kccam.service.AmConstants;
import com.remarkablesoft.site.kccam.service.newsroom.vo.NewsroomCnd;
import com.remarkablesoft.site.kccam.service.newsroom.vo.NewsroomInfo;
import com.remarkablesoft.site.kccam.service.product.model.impl.ProductBLO;
import com.remarkablesoft.site.kccam.service.product.model.impl.ProductRelBLO;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductRelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@BLO
public class NewsroomBLO {
		
		@Autowired
		protected NewsroomDAO newsroomDAO;
		
		@Autowired
		protected ProductBLO productBLO;
		
		@Autowired
		protected ProductRelBLO productRelBLO;
		
		@Autowired
		protected FileBLO fileBLO;
		
		@Autowired
		protected PostingAuditViewBLO postingAuditViewBLO;
		
		/**
		 * 뉴스를 저장합니다.
		 * 뉴스가 있는지 확인 후 , 있으면 update 없으면 insert 합니다
		 *
		 * @param info
		 * @return NewsroomInfo
		 * @author zero
		 */
		@CacheEvict( value = "newsroom", allEntries = true )
		public NewsroomInfo insertOrUpdate( NewsroomInfo info ){
				if ( info == null ) {
					return null;
				}
				
				if ( StringUtils.isEmpty( info.getOid() ) ) {
						
						return insert( info );
				}
				else {
						
						return update( info );
				}
		}
		
		/**
		 * 뉴스정보를 등록합니다.
		 *
		 * @param info
		 * @return NewsroomInfo
		 * @author zero
		 */
		@CacheEvict( value = "newsroom", allEntries = true )
		public NewsroomInfo insert( NewsroomInfo info ){
				
				if ( info == null ) {
						return null;
				}
				
				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}
				
				newsroomDAO.insert( info );
				
				//대표 이미지 등록
				if(  info.getIconFile() != null && StringUtils.isNotEmpty( info.getIconFile().getStorageFileUid() ) ){
						fileInsert( info, info.getIconFile(), NewsroomInfo.FILE_TYPE_ICON );
				}
				
				//관련 제품 리스트 관계 등록
				productRelListInsert( info );
				
				return info;
		}
		
		/**
		 * 뉴스정보를 업데이트합니다.
		 *
		 * @param info
		 * @return NewsroomInfo
		 * @author zero
		 */
		@CacheEvict( value = "newsroom", allEntries = true )
		public NewsroomInfo update( NewsroomInfo info ){
				if ( info == null || StringUtils.isEmpty( info.getOid() ) ) {
						return null;
				}
				
				info.setModDate( LocalDateTime.now() );
				newsroomDAO.update( info );
				
				//대표 이미지 삭제 후 등록
				fileDeleteAndInsert( info );
				
				//관련 제품 리스트 관계 삭제 후 등록
				productRelListDeleteAndInsert( info );
				
				return info;
		}
		
		/**
		 * 뉴스정보를 삭제 플러그 처리합니다.
		 *
		 * @param info
		 * @return int
		 * @author zero
		 */
		@CacheEvict( value = "newsroom", allEntries = true )
		public int deleteFlagUpdate( NewsroomInfo info ){
				if( info == null || StringUtils.isEmpty( info.getOid() ) ){
						return 0;
				}
				
				info.setDelDate( LocalDateTime.now() );
				
				return newsroomDAO.deleteFlagUpdate( info );
		}
		
		/**
		 * 뉴스정보를 완전히 삭제 처리합니다.
		 *
		 * @param oid
		 * @return int
		 * @author zero
		 */
		@CacheEvict( value = "newsroom", allEntries = true )
		public int delete( String oid ){
				if( StringUtils.isEmpty( oid ) ){
						return 0;
				}
				
				return newsroomDAO.delete( oid );
		}
		
		/**
		 * 뉴스 단건 정보를 가져옵니다.
		 *
		 * @param cnd
		 * @return NewsroomInfo
		 * @author zero
		 */
		@Cacheable( value = "newsroom", keyGenerator = "cacheKeyGenerator" )
		public NewsroomInfo get( NewsroomCnd cnd ) {
				
				if ( StringUtils.isEmpty( cnd.getOid() ) ) {
					return null;
				}
				
				NewsroomInfo info = newsroomDAO.get( cnd );
				
				if ( info == null ) {
						return null;
				}
				
				fillIconFile( info, cnd );
				fillProductList( info, cnd );
				
				return info;
		}
		
		/**
		 * 이전,다음글을 포함한 뉴스 단건 정보를 가져옵니다.
		 *
		 * @param cnd
		 * @return NewsroomInfo
		 * @author zero
		 */
		@Cacheable( value = "newsroom", keyGenerator = "cacheKeyGenerator" )
		public NewsroomInfo getWithPrevAndNext( NewsroomCnd cnd ) {
				
				if ( StringUtils.isEmpty( cnd.getOid() ) ) {
						return null;
				}
				
				NewsroomCnd newsroomCnd = new NewsroomCnd();
				
				newsroomCnd.setOid( cnd.getOid() );
				newsroomCnd.setLang( cnd.getLang() );
				
				NewsroomInfo newsInfo = get( newsroomCnd );
				
				if ( newsInfo == null ) {
						return null;
				}
				
				newsInfo.setPrevNewsInfo( viewPrev( cnd ) );
				newsInfo.setNextNewsInfo( viewNext( cnd ) );
				
				return newsInfo;
		}
		
		/**
		 *  뉴스 보기시 조회수 카운트 증가
		 *
		 * @param cnd
		 * @return NewsroomInfo
		 * @author zero
		 */
		
		public NewsroomInfo view( NewsroomCnd cnd ) {
				
				NewsroomInfo info = get( cnd );
				
				if ( info == null ) {
						return null;
				}
				
				PostingInfo posting = new PostingInfo();
				posting.setOid( info.getOid() );
				
				postingAuditViewBLO.insert( posting, AutheUtils.getLoginUserOid() );
				
				newsroomDAO.incrementViewCount( info );
				
				return info;
		}
		
		/**
		 *  조회수 카운트를 증가시키면서 이전글, 다음글을 포함한 게시글 정보를 가져옵니다.
		 *
		 * @param cnd
		 * @return NewsroomInfo
		 * @author zero
		 */
		@CacheEvict( value = "newsroom", keyGenerator = "cacheKeyGenerator" )
		public NewsroomInfo viewWithPrevAndNext( NewsroomCnd cnd ) {
				
				if ( StringUtils.isEmpty( cnd.getOid() ) ) {
						return null;
				}
				
				NewsroomInfo newsroomInfo = view( cnd );
				fillProductList( newsroomInfo, cnd );
				
				if ( newsroomInfo == null ) {
						return null;
				}
				
				newsroomInfo.setPrevNewsInfo( viewPrev( cnd ) );
				newsroomInfo.setNextNewsInfo( viewNext( cnd ) );
				
				return newsroomInfo;
		}
		
		@Cacheable( value = "newsroom", keyGenerator = "cacheKeyGenerator" )
		public NewsroomInfo viewPrev( NewsroomCnd cnd ){
				return newsroomDAO.getPrev( cnd );
		}
		
		@Cacheable( value = "newsroom", keyGenerator = "cacheKeyGenerator" )
		public NewsroomInfo viewNext( NewsroomCnd cnd ){
				return newsroomDAO.getNext( cnd );
		}
		
		/**
		 * 뉴스정보 페이지 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return PageList<NewsroomInfo>
		 * @author zero
		 */
		@Cacheable( value = "newsroom", keyGenerator = "cacheKeyGenerator" )
		public PageList<NewsroomInfo> list( NewsroomCnd cnd ) {
				
				PageList<NewsroomInfo> newsList = newsroomDAO.list( cnd );
				
				if ( CollectionUtils.isEmpty( newsList ) ) {
					return null;
				}
				
				fillIconFile( newsList, cnd );
				fillProductList( newsList, cnd );
				removeHtmlTag( newsList, cnd );
				
				return newsList;
		}
		
		/**
		 * 뉴스정보 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return PageList<NewsroomInfo>
		 * @author zero
		 */
		@Cacheable( value = "newsroom", keyGenerator = "cacheKeyGenerator" )
		public List<NewsroomInfo> listAll( NewsroomCnd cnd ) {
				
				List<NewsroomInfo> newsList = newsroomDAO.listAll( cnd );
				
				if ( CollectionUtils.isEmpty( newsList ) ) {
						return null;
				}
				
				return newsList;
		}
		
		/**
		 * 대표이미지 등록
		 *
		 * @author zero
		 * @param info
		 * @param fileInfo
		 * @param fileType
		 */
		protected void fileInsert( NewsroomInfo info, FileInfo fileInfo, String fileType ){
			
				if ( info == null || fileInfo == null || StringUtils.isEmpty( fileInfo.getStorageFileUid() )){
						return;
				}
				
				fileInfo.setOid( OIDGenerator.generateOID() );
				fileInfo.setTargetOid( info.getOid() );
				fileInfo.setTargetObject( NewsroomInfo.getObjectType() );
				
				fileInfo.setFileType( fileType );
				
				fileInfo.setInputDate( LocalDateTime.now() );
				
				fileBLO.insert( fileInfo );
				
		}
		
		/**
		 * 대표이미지 삭제 후 등록
		 *
		 * @author zero
		 * @param info
		 */
		protected void fileDeleteAndInsert( NewsroomInfo info ){
				
				if ( info == null ) {
						return;
				}
				
				FileCnd cnd = new FileCnd();
				cnd.setTargetObject( info.getObjectType() );
				cnd.setTargetOid( info.getOid() );
				fileBLO.deleteByCnd( cnd );
				
				if ( info.getIconFile() !=null && StringUtils.isNotEmpty( info.getIconFile().getStorageFileUid() ) ) {
						info.getIconFile().setTargetObject( info.getObjectType() );
						fileInsert( info, info.getIconFile(), NewsroomInfo.FILE_TYPE_ICON );
				}
				
		}
		
		
		/**
		 * 뉴스와 관련된 제품 리스트 관계 저장
		 *
		 * @author zero
		 * @param info
		 */
		protected void productRelListInsert( NewsroomInfo info ){
				
				if( info == null || CollectionUtils.isEmpty( info.getProductList() )) {
						return;
				}
				
				List<ProductRelInfo> productRelList = new ArrayList<>();
				
				for( ProductInfo productInfo : info.getProductList() ){
						ProductRelInfo relInfo = new ProductRelInfo();
						relInfo.setTargetObject( AmConstants.OBJECT_AM_NEWSROOM.getKey() );
						relInfo.setTargetOid( info.getOid() );
						relInfo.setProductOid( productInfo.getOid() );
						productRelList.add( relInfo );
				}
				
				productRelBLO.insertBulk( productRelList );
		}
		
		/**
		 * 뉴스와 관련된 제품 리스트 관계 삭제 후 저장
		 *
		 * @author zero
		 * @param info
		 */
		protected void productRelListDeleteAndInsert( NewsroomInfo info ){
				
				if( info == null ){
					return;
				}
				
				productBLO.deleteRelOnlyByTarget( info.getOid(), info.getObjectType() );
				
				productRelListInsert( info );
		}
		
		/**
		 * 뉴스 객체에 제품 정보들을 세팅합니다
		 *
		 * @author zero
		 * @param list
		 * */
		protected void fillProductList( List<NewsroomInfo> list, NewsroomCnd cnd ){
				
				if( !cnd.isFillProductList() || CollectionUtils.isEmpty( list ) ) {
						return;
				}
				
				for( NewsroomInfo info : list ){
						fillProductList( info, cnd );
				}
				
		}
		
		/**
		 * 뉴스 객체에 제품 정보들을 세팅합니다
		 *
		 * @author zero
		 * @param info
		 * */
		protected void fillProductList( NewsroomInfo info, NewsroomCnd newsCnd ){
			
				if( !newsCnd.isFillProductList() || info == null || StringUtils.isEmpty( info.getOid() )){
						return;
				}
				
				ProductCnd cnd = new ProductCnd();
				cnd.setTargetObject( AmConstants.OBJECT_AM_NEWSROOM.getKey() );
				cnd.setTargetOid( info.getOid() );
				cnd.setFillMaterial( true );
				
				List<ProductInfo> productList = productBLO.viewListAll( cnd );
				
				if ( CollectionUtils.isEmpty( productList ) ){
						return;
				}
				
				info.setProductList( productList );
				
		}
		
		/**
		 * 뉴스 객체리스트에 대표이미지들을 세팅합니다
		 * @param list
		 * */
		protected void fillIconFile( List<NewsroomInfo> list , NewsroomCnd cnd ){
				
				if( !cnd.isFillIconFile() || CollectionUtils.isEmpty( list ) ) {
						return;
				}
				
				List<String> targetOidList = list.stream().map( NewsroomInfo :: getOid ).collect( Collectors.toList());
				
				FileCnd fileCnd = new FileCnd();
				fileCnd.setTargetObject( NewsroomInfo.getObjectType() );
				fileCnd.setTargetOidList( targetOidList );
				
				List<FileInfo> fileList = fileBLO.listAll( fileCnd );
				
				if ( CollectionUtils.isEmpty( fileList ) ) {
						return;
				}
				
				list.forEach( news -> fileList.stream()
											.filter( file -> file !=null && StringUtils.hasText( news.getOid() ) )
											.filter( file -> news.getOid().equals( file.getTargetOid() ) )
											.forEach( file -> news.setIconFile( file ) ) );
		}
		
		/**
		 * 뉴스 객체에 대표이미지를 세팅합니다
		 * @param info
		 * */
		protected void fillIconFile( NewsroomInfo info, NewsroomCnd cnd ){
				
				if( !cnd.isFillIconFile() || info == null ) {
						return;
				}
				
				FileCnd fileCnd = new FileCnd();
				fileCnd.setTargetObject( NewsroomInfo.getObjectType() );
				fileCnd.setTargetOid( info.getOid() );
				
				info.setIconFile( fileBLO.getByCnd( fileCnd ) );
		}
		
		/**
		 * 뉴스 컨텐츠의 html 태그를 없애줍니다.
		 * @param list
		 * @param cnd
		 * */
		protected void removeHtmlTag( List<NewsroomInfo> list, NewsroomCnd cnd ){
				
				if( CollectionUtils.isEmpty( list ) || !cnd.isRemoveHtmlTag() ){
						return;
				}
				
				list.forEach( news -> {
						if ( StringUtils.isNotEmpty( news.getNewsroomContents() ) ){
								String szContents = news.getNewsroomContents();
								szContents = StringUtils.html2text( szContents );
								news.setNewsroomContents( szContents );
						}
				} );
		}
		
}
