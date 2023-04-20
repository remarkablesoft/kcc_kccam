package com.remarkablesoft.framework.service.mgnt.category.model.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.remarkablesoft.framework.service.board.contents.model.impl.ContentsBLO;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsCnd;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryCnd;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryInfo;
import com.remarkablesoft.framework.service.storage.file.model.impl.FileBLO;
import com.remarkablesoft.framework.service.storage.file.vo.FileCnd;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board-category
 * 		@프로그램 ID		:	CategoryBLO
 * 		@프로그램 개요 		:	카테고리 객체. 단독으로 사용하고 board의 부모로도 사용
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	hong	-	신규생성
 * 		============================================================================
 */
@BLO
public class CategoryBLO {


//		@Autowired
//		protected CategoryBLO _self;


		@Autowired
		protected CategoryDAO categoryDAO;

		@Autowired
		protected CategoryLangDAO categoryLangDAO;

		@Autowired
		protected FileBLO fileBLO;
		
		@Autowired
		protected ContentsBLO contentsBLO;

		/**
		 * 카테고리는 많이 사용될수 있어서 fullPathIndex를 5자리로 사용
		 * ex) 0|00001|
		 *
		 *
		 * @author james
		 * @param category
		 * @return
		 */
		@CacheEvict( value = "category", allEntries = true )
		public CategoryInfo insert( CategoryInfo category ) {

				if ( StringUtils.isEmpty( category.getOid() ) ) {
						category.setOid( OIDGenerator.generateOID() );
				}

				if ( StringUtils.isEmpty( category.getInputDate() ) ) {
						category.setInputDate( LocalDateTime.now() );
				}

				String pathFormat = SystemConstants.FULL_PATH_LONG_LENGTH;

				// parentOid가 없다면 디폴트 처리
				if ( StringUtils.isEmpty( category.getParentOid() ) ) {
						category.setParentOid( SystemConstants.ROOT_TREE_OID );
				}

				CategoryInfo parentCategory = categoryDAO.get( category.getParentOid() );
				if ( parentCategory != null ) {
					parentCategory.setSubLastIndex( parentCategory.getSubLastIndex() + 1 );
					categoryDAO.update( parentCategory );

					String fullPathIndex = parentCategory.getFullPathIndex() + String.format( pathFormat, parentCategory.getSubLastIndex() ) + SystemConstants.FULL_PATH_INDEX_DELIMITER;
					category.setFullPathIndex( fullPathIndex );
				}

				categoryDAO.insert( category );
				
				if ( CollectionUtils.isNotEmpty( category.getLangCategoryList() ) ) {
					insertOrUpdateLangList( category );
				} 
				else {
					categoryLangDAO.insert( category );						
				}

				// 카테고리 아이콘이 있을 경우
				fileBLO.insert( category.getIconFile(), CategoryInfo.getObjectType(), category.getOid(), category.getPartOid() );

				return category;
		}
		
		/**
		 * 다국어 리스트 등록
		 * 
		 * @author Woong
		 * @param category
		 * @return
		 */
		public int insertOrUpdateLangList( CategoryInfo category ) {
				if (  CollectionUtils.isEmpty( category.getLangCategoryList() ) ) {
					return 0;
				}
				
				int result = 0;
				
				for ( CategoryInfo categoryLang : category.getLangCategoryList() ) {
						String tempLang = categoryLang.getLang();
					
						categoryLang.setLang( tempLang.toUpperCase() );
					
						CategoryCnd cnd = new CategoryCnd();
					
						cnd.setOid( category.getOid() );
						cnd.setLang( categoryLang.getLang() );
					
						boolean exist = categoryLangDAO.exist( cnd );
					
						categoryLang.setOid( category.getOid() );
					
					if ( exist ) {
							categoryLang.setModDate( LocalDateTime.now() );
							result += categoryLangDAO.update( categoryLang );
							updateLangAddContentsList( categoryLang );
							
					} else {
							categoryLang.setInputDate( LocalDateTime.now() );
							result += categoryLangDAO.insert( categoryLang );
							insertLangAddContentsList( categoryLang );
					}
				}
				
				return result;
		}

		@Cacheable( value = "category" , keyGenerator = "cacheKeyGenerator")
		public CategoryInfo get( CategoryCnd cnd ) {

				CategoryInfo category = categoryDAO.get( cnd );

				if ( category == null ) {
						return null;
				}

				//	프로필정보를 채움
				if ( cnd.isFillIconFile() ) {
						FileCnd fileCnd = new FileCnd();
						fileCnd.setTargetOid( cnd.getOid() );
						fileCnd.setTargetObject( CategoryInfo.getObjectType() );

						FileInfo iconFile = fileBLO.getByCnd( fileCnd );
						category.setIconFile( iconFile );
				}

				// 카테고리명을 채울지 여부
				if ( cnd.isFillFullPathName() ) {

						cnd.calculateFullPath( category.getFullPathIndex() );
						String fullPathName = categoryDAO.getFullPathName( cnd );
						category.setFullPathNameVC( fullPathName );
				}
				
				// 다국어 정보를 채울지 여부
				if ( cnd.isFillLangList() && StringUtils.isNotEmpty( cnd.getOid() ) ) {
						List<CategoryInfo> langList = categoryLangDAO.listAll( cnd.getOid() );
					
						category.setLangCategoryList( langList );
					
						// langAddContentsList 채우기
						List<CategoryInfo> langCategoryList = category.getLangCategoryList();
						for( CategoryInfo info : langCategoryList ){
							
								ContentsCnd contentsCnd = new ContentsCnd();
								contentsCnd.setTargetOid( info.getOid() );
								contentsCnd.setLang( info.getLang() );
							
								List<ContentsInfo> contentsList = contentsBLO.list( contentsCnd );
								
								if( CollectionUtils.isNotEmpty( contentsList ) ){
										info.setLangAddContentsList( contentsList );
								}
						}
				}

				return category;
		}

		@CacheEvict( value = "category", allEntries = true )
		public CategoryInfo update( CategoryInfo info ) {

				String pathFormat = SystemConstants.FULL_PATH_LONG_LENGTH;

				// parentOid가 없다면 디폴트 처리
				if ( StringUtils.isEmpty( info.getParentOid() ) ) {
						info.setParentOid( SystemConstants.ROOT_TREE_OID );
				}

				CategoryInfo parentCategory = categoryDAO.get( info.getParentOid() );
				if ( parentCategory != null ) {
						parentCategory.setSubLastIndex( parentCategory.getSubLastIndex() + 1 );
						categoryDAO.update( parentCategory );

						String fullPathIndex = parentCategory.getFullPathIndex() + String.format( pathFormat, parentCategory.getSubLastIndex() ) + SystemConstants.FULL_PATH_INDEX_DELIMITER;
						info.setFullPathIndex( fullPathIndex );
				}

				categoryDAO.update( info );

				if ( CollectionUtils.isNotEmpty( info.getLangCategoryList() ) ) {
					insertOrUpdateLangList( info );
				} 
				else {					
					categoryLangDAO.update( info );
				}
				
				if ( info.getIconFile() != null ) {

						FileCnd fileCnd = new FileCnd();
						fileCnd.setTargetOid( info.getOid() );
						fileCnd.setTargetObject( CategoryInfo.getObjectType() );

						fileBLO.deleteByCnd( fileCnd );
						fileBLO.insert( info.getIconFile(), CategoryInfo.getObjectType(), info.getOid(), info.getParentOid() );
				}

				return info;
		}

		/**
		 * fullPathIndex로 부모의 fullPathName까지 전부를 구한다
		 * ex) 고교학점제연구학교>인식도조사>교사설문
		 *
		 * @author james
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "category" , keyGenerator = "cacheKeyGenerator")
		public String getFullPathName( CategoryCnd cnd ) {

				return categoryDAO.getFullPathName( cnd );
		}


		@CacheEvict( value = "category", allEntries = true )
		public int delete( String oid ) {

				if ( StringUtils.isEmpty( oid ) ) {

						return 0;
				}
				
				CategoryCnd cnd = new CategoryCnd();
				cnd.setOid( oid );
				CategoryInfo categoryInfo = get( cnd );
				
				if ( categoryInfo == null) {
						return 0;
				}
				
				// 하위 카테고리가 있다면 모두 삭제처리
				cnd.setOid( "" ); // 지우지마세요.
				cnd.setFullPathIndex( categoryInfo.getFullPathIndex() );
				
				List<CategoryInfo> list = listAll( cnd );
				
				list.stream().forEach( category -> {
						
						fileBLO.deleteByTarget( category.getOid(), CategoryInfo.getObjectType() );
						categoryDAO.delete( category.getOid() );
						categoryLangDAO.deleteAll( category.getOid() );
				});
				
				return list.size();
		}


		@Cacheable( value = "category" , keyGenerator = "cacheKeyGenerator")
		public PageList<CategoryInfo> list( CategoryCnd cnd ) {

				PageList<CategoryInfo> list = categoryDAO.list( cnd );
				if ( cnd.isFillIconFile() ) {
						fillIconFile( list );
				}

				if ( cnd.isFullPathNameSearch()) {
					fillFullPathName( list, cnd.getLang() );
				}

				return list;
		}


		@Cacheable( value = "category" , keyGenerator = "cacheKeyGenerator")
		public List<CategoryInfo> listAll( CategoryCnd cnd ) {

				List<CategoryInfo> list = categoryDAO.listAll( cnd );
				if ( cnd.isFillIconFile() ) {
						fillIconFile( list );
				}


				if ( cnd.isFullPathNameSearch()) {

						fillFullPathName( list , cnd.getLang() );
				}


				return list;
		}



		@Cacheable( value = "category" , keyGenerator = "cacheKeyGenerator")
		public List<CategoryInfo> listAllTreeChilds( CategoryCnd cnd ) {

				List<CategoryInfo> list = categoryDAO.listAll( cnd );
				if ( cnd.isFillIconFile() ) {
						fillIconFile( list );
				}

				fillCategoryDepth( list );

				List<CategoryInfo> result = fillCategoryChild( list );

				return result;
		}


		public CategoryInfo insertOrUpdate( CategoryInfo info ) {

				CategoryInfo category = null;

				if ( StringUtils.isEmpty( info.getOid() ) ) {

						category = insert( info );
				}
				else {
						category = update( info );
				}

				return category;
		}
		
		private int insertLangAddContentsList( CategoryInfo info ){
				
				if ( info == null || CollectionUtils.isEmpty( info.getLangAddContentsList() ) || StringUtils.isEmpty( info.getContentsTargetObject() ) ) {
						return 0;
				}
				
				info.getLangAddContentsList().forEach( contents -> contents.setTargetOid( info.getOid() )
							                                                   .setLang( info.getLang() )
							                                                   .setTargetObject( info.getContentsTargetObject() ) );
		
				return contentsBLO.insertBulk( info.getLangAddContentsList() );
		}
		
		private int updateLangAddContentsList( CategoryInfo info ){
				if ( info == null ) {
				 return 0;
				}
				
				//일괄 삭제 후 등록합니다.
				contentsBLO.deleteByTarget( info.getOid(), info.getContentsTargetObject(), info.getLang() );
				
				if( CollectionUtils.isEmpty( info.getLangAddContentsList() ) || StringUtils.isEmpty( info.getContentsTargetObject() ) ){
						return 0;
				}
				
				info.getLangAddContentsList().forEach( contents -> contents.setTargetOid( info.getOid() )
							                                                   .setLang( info.getLang() )
							                                                   .setTargetObject( info.getContentsTargetObject() ) );
				
				
//				return 0;
				return contentsBLO.insertBulk( info.getLangAddContentsList() );
		}

		/**
		 * 카테고리 리스트의 아이콘 파일을 채워줍니다.
		 *
		 * @author 최원준
		 * @param list
		 */
		protected void fillIconFile( List<CategoryInfo> list ) {

				if ( CollectionUtils.isEmpty( list ) ) {
						return;
				}

				List<String> targetOidList = list.stream().map( CategoryInfo::getOid ).collect( Collectors.toList() );

				FileCnd fileCnd = new FileCnd();
				fileCnd.setTargetOidList( targetOidList );
				fileCnd.setTargetObject( CategoryInfo.getObjectType() );

				List<FileInfo> fileList = fileBLO.listAll( fileCnd );

				if ( CollectionUtils.isEmpty( fileList ) ) {
						return;
				}

				list.forEach( category -> fileList.stream()
												  .filter( file -> file != null && StringUtils.hasText( category.getOid() ) )
												  .filter( file -> category.getOid().equals( file.getTargetOid() ) )
												  .forEach( file -> category.setIconFile( file ) ) );
		}

		/**
		 * 자신의 하위 카테고리 리스트를 가져온다
		 * 단 루트외의 밑에 있는 자식노드는 제외
		 *
		 * @param categoryList
		 */
		protected List<CategoryInfo> fillCategoryChild( List<CategoryInfo> categoryList ) {

				if ( CollectionUtils.isEmpty( categoryList ) ) {
						return null;
				}


				CategoryInfo root = categoryList.get( 0 );
				int rootDepth = StringUtils.countMatches( root.getFullPathIndex(), SystemConstants.FULL_PATH_INDEX_DELIMITER );

				// 자식이 있으면 담고
				categoryList.forEach( parent -> categoryList.stream()
										.filter( child -> child.getParentOid() != null )
										.filter( child -> parent.getOid().equals( child.getParentOid() ) )
										.forEach( child -> parent.addChildCategory( child )) );

				// 자식이 없으면 제거
				List<CategoryInfo> targetList = categoryList.stream().filter( category ->
				{

						int nDepth = StringUtils.countMatches( category.getFullPathIndex(), SystemConstants.FULL_PATH_INDEX_DELIMITER );

						if ( nDepth > rootDepth) {
								return false;
						}

						return true;
				} ).collect( Collectors.toList() );

				return targetList;

		}


		/**
		 * 자신의 Depth를 채운다.
		 *
		 * @param categoryList
		 */
		protected void fillCategoryDepth( List<CategoryInfo> categoryList ) {

				if( CollectionUtils.isEmpty( categoryList ) 	 ) {
						return;
				}

				for ( CategoryInfo categoryInfo : categoryList ) {
						// 최상위 루트에 한개가 무조건 포함됨으로 -1을 한다.
						int nDepth = StringUtils.countMatches( categoryInfo.getFullPathIndex(), SystemConstants.FULL_PATH_INDEX_DELIMITER );
						nDepth = nDepth - 1;
						categoryInfo.setDepthVC( nDepth );
					}
		}


		/**
		 * 리스트마다 FullPathName을 찾는다.
		 *
		 * @author james
		 * @param list
		 */
		protected void fillFullPathName( List<CategoryInfo> list, String lang ) {


				if ( CollectionUtils.isEmpty(  list )) {
						return;
				}

				list.forEach( category ->  {

						 CategoryCnd cnd = new CategoryCnd();
						 cnd.calculateFullPath( category.getFullPathIndex() );
						 cnd.setCategoryType(category.getCategoryType());
						 cnd.setLang( lang );
						 String fullPathName = categoryDAO.getFullPathName( cnd );
						 category.setFullPathNameVC( fullPathName );
				});

		}

}
