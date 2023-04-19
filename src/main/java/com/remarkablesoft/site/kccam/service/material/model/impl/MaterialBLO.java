package com.remarkablesoft.site.kccam.service.material.model.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsCnd;
import com.remarkablesoft.framework.service.doc.doc.model.impl.DocRelBLO;
import com.remarkablesoft.framework.service.doc.doc.vo.DocRelInfo;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.board.contents.model.impl.ContentsBLO;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;
import com.remarkablesoft.framework.service.doc.doc.model.impl.DocBLO;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.storage.file.model.impl.FileBLO;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.util.AutheUtils;
import com.remarkablesoft.site.kccam.service.classification.model.impl.ClassificationBLO;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationCnd;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationInfo;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialCnd;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;
import com.remarkablesoft.site.kccam.service.product.model.impl.ProductBLO;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;

@BLO
public class MaterialBLO {
		
		@Autowired
		protected ContentsBLO contentsBLO;

		@Autowired
		protected FileBLO fileBLO;
		
		@Autowired
		protected DocBLO docBLO;
		
		@Autowired
		protected DocRelBLO docRelBLO;
		
		@Autowired
		protected ClassificationBLO classificationBLO;
		
		@Autowired
		protected ProductBLO productBLO;
		
		@Autowired
		protected MaterialDAO materialDAO;
		
		@Autowired
		protected MaterialLangDAO materialLangDAO;
		
		/**
		 * 소재정보를 저장합니다.
		 *
		 * @param info
		 * @return MaterialInfo
		 * @author Woong
		 */
		@CacheEvict( value = "classification", allEntries = true )
		public MaterialInfo insert( MaterialInfo info ) {
				
				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}
				
				if ( StringUtils.isEmpty( info.getInputUser() ) ) {
						info.setInputUser( AutheUtils.getLoginUserOid() );
				}
				
				materialDAO.insert( info );
				
				if ( CollectionUtils.isNotEmpty( info.getLangMaterialList() ) ) {
						insertOrUpdateLangList( info );
				}
				else {
						materialLangDAO.insert( info );
				}
				
				// 콘텐츠 정보를 저장합니다.
				insertContents( info );
				// 소재 이미지 정보를 저장합니다.
				info.setMainImg( insertMainImg( info ) );
				// 문서 정보를 저장합니다.
				insertDocList( info );
				
				return info;
		}
		
		/**
		 * 소재정보를 수정합니다.
		 *
		 * @param info
		 * @return MaterialInfo
		 * @author Woong
		 */
		@CacheEvict( value = "classification", allEntries = true )
		public MaterialInfo update( MaterialInfo info ) {
				
				if ( info == null || StringUtils.isEmpty( info.getOid() ) ) {
						return null;
				}
				
				if ( StringUtils.isNotEmpty( info.getSystemOid() ) ) {
						materialDAO.update( info );
				}
				
				if ( CollectionUtils.isNotEmpty( info.getLangMaterialList() ) ) {
						insertOrUpdateLangList( info );
				}
				else {
						materialLangDAO.update( info );
				}
				
				// 콘텐츠 정보를 수정합니다. -> insertOrUpdateLangList를 통해 각 다국어 정보 하위의 콘텐츠 정보가 수정됩니다.
//				updateContents( info );
				// 소재 이미지 정보를 수정합니다.
				info.setMainImg( updateMainImg( info ) );
				// 문서 정보를 수정합니다.
				updateDocList( info );
				
				return info;
		}
		
		/**
		 * 다국어 리스트 등록
		 *
		 * @author Woong
		 * @param info
		 * @return
		 */
		@CacheEvict( value = "classification", allEntries = true )
		public int insertOrUpdateLangList( MaterialInfo info ) {
				if ( CollectionUtils.isEmpty( info.getLangMaterialList() ) ) {
						return 0;
				}
				
				int result = 0;
				
				for ( MaterialInfo materialLang : info.getLangMaterialList() ) {
						String tempLang = materialLang.getLang();
						
						materialLang.setLang( tempLang.toUpperCase() );
						
						MaterialCnd cnd = new MaterialCnd();
						
						cnd.setOid( info.getOid() );
						cnd.setLang( materialLang.getLang() );
						
						boolean exist = materialLangDAO.exist( cnd );
						
						materialLang.setOid( info.getOid() );
						materialLang.setModDate( LocalDateTime.now() );
						
						if ( exist ) {
								result += materialLangDAO.update( materialLang );
								updateLangAddContentsList( materialLang );
						}
						else {
								materialLang.setInputDate( LocalDateTime.now() );
								result += materialLangDAO.insert( materialLang );
								insertLangAddContentsList( materialLang );
						}
				}
				
				return result;
		}
		
		/**
		 * 소재정보 단건을 가져옵니다.
		 *
		 * @param cnd
		 * @return MaterialInfo
		 * @author Woong
		 */
		@Cacheable( value = "classification", keyGenerator = "cacheKeyGenerator" )
		public MaterialInfo get( MaterialCnd cnd ) {
				
				MaterialInfo info = materialDAO.get( cnd );
				
				if ( info == null ) {
						return null;
				}
				
				fillContents( info );
				info.setMainImg( fileBLO.getByTarget( MaterialInfo.getObjectType(), info.getOid() ) );
				fillDoc( info );
				fillClassification( info, cnd );
				
				if ( cnd.isFillProductCnt() ) {
						fillProductCnt( info );
				}
				
				// 다국어 정보를 채울지 여부
				if ( cnd.isFillLangList() && StringUtils.isNotEmpty( cnd.getOid() ) ) {
						List<MaterialInfo> langList = materialLangDAO.listAll( cnd.getOid() );
						
						info.setLangMaterialList( langList );
						
						// langAddContentsList 채우기
						List<MaterialInfo> langMaterialList = info.getLangMaterialList();
						for( MaterialInfo material : langMaterialList ){
								
								ContentsCnd contentsCnd = new ContentsCnd();
								contentsCnd.setTargetObject( material.getObjectType() );
								contentsCnd.setTargetOid( material.getOid() );
								contentsCnd.setLang( material.getLang() );
								
								List<ContentsInfo> contentsList = contentsBLO.list( contentsCnd );
								
								if( CollectionUtils.isNotEmpty( contentsList ) ){
										material.setLangAddContentsList( contentsList );
								}
						}
				}
				
				return info;
		}
		
		/**
		 * 소재정보 페이지 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return PageList<MaterialInfo>
		 * @author Woong
		 */
		@Cacheable( value = "classification", keyGenerator = "cacheKeyGenerator" )
		public PageList<MaterialInfo> list( MaterialCnd cnd ) {
				
				PageList<MaterialInfo> materialList = materialDAO.list( cnd );
				
				if ( CollectionUtils.isEmpty( materialList ) ) {
						return null;
				}
				
				fillClassificationList( materialList, cnd.getLang() );
				if ( cnd.isFillProductCnt() ) {
						fillProductCnt( materialList, cnd.getLang() );
				}
				
				return materialList;
		}
		
		/**
		 * 소재정보 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return List<MaterialInfo>
		 * @author Woong
		 */
		@Cacheable( value = "classification", keyGenerator = "cacheKeyGenerator" )
		public List<MaterialInfo> listAll( MaterialCnd cnd ) {
				
				List<MaterialInfo> materialList = materialDAO.listAll( cnd );
				
				if ( CollectionUtils.isEmpty( materialList ) ) {
						return null;
				}
				
				return materialList;
		}
		
		/**
		 * 메뉴로 사용하는 소재정보와 소재 하위정보들을 채워서 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return List<MaterialInfo>
		 * @author Woong
		 */
		@Cacheable( value = "classification", keyGenerator = "cacheKeyGenerator" )
		public List<MaterialInfo> menuList( MaterialCnd cnd ) {
				
				List<MaterialInfo> materialList = materialDAO.listAll( cnd );
				
				if ( CollectionUtils.isEmpty( materialList ) ) {
						return null;
				}
				
				for( MaterialInfo info : materialList ){
						info.setMainImg( fileBLO.getByTarget( MaterialInfo.getObjectType(), info.getOid() ) );
				}
				
				fillClassificationList( materialList, cnd.getLang() );
				
				return materialList;
		}
		
		/**
		 * 소재정보의 콘텐츠 내용을 저장합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void insertContents( MaterialInfo info ) {
				
				// 본문 내용 등록
				if ( info.getMainContents() != null ) {
						// MainContents의 OrderNo는 고정값
						info.getMainContents().setOrderNo( MaterialInfo.MAIN_CONTENTS_ORDER_NO );
						info.addContentsInfo( info.getMainContents() );
				}
				
				if ( CollectionUtils.isEmpty( info.getAddContentsList() ) ) {
						return;
				}
				
				// 추가 내용 등록
				info.getAddContentsList().forEach( contents -> contents.setTargetOid( info.getOid() )
																		   .setTargetObject( MaterialInfo.getObjectType() ) );
				
				contentsBLO.insertBulk( info.getAddContentsList() );
				
		}
		
		/**
		 * 소재정보의 콘텐츠 내용을 수정합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void updateContents( MaterialInfo info ) {
				// 관련 내용을 삭제 후 재등록합니다.
				contentsBLO.deleteByTarget( info.getOid(), MaterialInfo.getObjectType() );
				insertContents( info );
		}
		
		/**
		 * 소재정보의 추가정보를 추가합니다.(다국어)
		 *
		 * @param info
		 * @author 황지영
		 */
		private int insertLangAddContentsList( MaterialInfo info ){
				
				if ( info == null || org.apache.commons.collections.CollectionUtils.isEmpty( info.getLangAddContentsList() )) {
						return 0;
				}
				
				info.getLangAddContentsList().forEach( contents -> contents.setTargetOid( info.getOid() )
							                                                   .setLang( info.getLang() )
							                                                   .setTargetObject( info.getObjectType() ) );
				
				return contentsBLO.insertBulk( info.getLangAddContentsList() );
		}
		
		/**
		 * 소재정보의 추가정보를 수정합니다.(다국어)
		 *
		 * @param info
		 * @author 황지영
		 */
		private int updateLangAddContentsList( MaterialInfo info ){
				if ( info == null ) {
						return 0;
				}
				
				//일괄 삭제 후 등록합니다.
				contentsBLO.deleteByTarget( info.getOid(), info.getObjectType(), info.getLang() );
				
				if( CollectionUtils.isEmpty( info.getLangAddContentsList() ) ){
						return 0;
				}
				
				info.getLangAddContentsList().forEach( contents -> contents.setTargetOid( info.getOid() )
							                                                   .setLang( info.getLang() )
							                                                   .setTargetObject( info.getObjectType() ) );
				
				
				return contentsBLO.insertBulk( info.getLangAddContentsList() );
		}
		
		/**
		 * 소재정보의 메인 이미지 파일을 저장합니다.
		 *
		 * @param info
		 * @return FileInfo
		 * @author 최원준
		 */
		private FileInfo insertMainImg( MaterialInfo info ) {
				
				if ( info.getMainImg() == null ) {
						return null;
				}
				
				info.getMainImg().setFileType( MaterialInfo.MATERIAL_FILE_TYPE_MAIN_IMG );
				return fileBLO.insert( info.getMainImg(), MaterialInfo.getObjectType(), info.getOid() );
		}
		
		/**
		 * 소재정보의 메인 이미지 정보를 수정합니다.
		 *
		 * @param info
		 * @return FileInfo
		 * @author 최원준
		 */
		private FileInfo updateMainImg( MaterialInfo info ) {
				
				// 관련 내용을 삭제 후 재등록 합니다.
				fileBLO.deleteByTarget( info.getOid(), MaterialInfo.getObjectType(), MaterialInfo.MATERIAL_FILE_TYPE_MAIN_IMG );
				
				if ( info == null || info.getMainImg() == null ) {
						return null;
				}
				
				info.getMainImg().setFileType( MaterialInfo.MATERIAL_FILE_TYPE_MAIN_IMG );
				return fileBLO.insert( info.getMainImg(), MaterialInfo.getObjectType(), info.getOid() );
		}
		
		/**
		 * 소재와 관련된 문서정보를 저장합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void insertDocList( MaterialInfo info ) {
				
				if ( CollectionUtils.isEmpty( info.getDocList() ) ) {
						return;
				}
				
				info.getDocList().forEach( doc -> doc = docBLO.insert( doc, info.getOid(), MaterialInfo.getObjectType() ) );
				
		}
		
		/**
		 * 소재 관련 문서정보를 수정합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void updateDocList( MaterialInfo info ) {
				
				if ( info == null || StringUtils.isEmpty( info.getOid() ) ) {
						return;
				}
				
				// 문서 관계정보를 삭제후 재등록합니다.
				
				//관련 문서를 삭제 후 재등록합니다.
				DocCnd cnd = new DocCnd();
				cnd.setTargetOid( info.getOid() );
				cnd.setTargetObject( MaterialInfo.getObjectType() );
				List<String> docOidList = docRelBLO.listAll( cnd ).stream().map( DocRelInfo::getDocOid ).collect( Collectors.toList());
				
				docOidList.forEach( docOid -> docBLO.delete( docOid ) );
				
				insertDocList( info );
				
		}
		
		/**
		 * 소재정보의 콘텐츠를 채워줍니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void fillContents( MaterialInfo info ) {
				
				if ( info == null ) {
						return;
				}
				ContentsCnd cnd = new ContentsCnd();
				cnd.setTargetObject( info.getObjectType() );
				cnd.setTargetOid( info.getOid() );
				cnd.setLang( info.getLang() );
				
				List<ContentsInfo> contentsInfoList = contentsBLO.list( cnd );
				
				if ( CollectionUtils.isEmpty( contentsInfoList ) ) {
						return;
				}
				
//				for ( int i = 0; i < contentsInfoList.size(); i++ ) {
//						ContentsInfo contentsInfo = contentsInfoList.get( i );
//
//						if ( MaterialInfo.MAIN_CONTENTS_ORDER_NO == contentsInfo.getOrderNo() ) {
//								info.setMainContents( contentsInfo );
//								contentsInfoList.remove( i );
//								break;
//						}
//				}
				
				info.setAddContentsList( contentsInfoList );
				
		}
		
		/**
		 * 문서정보를 채워줍니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void fillDoc( MaterialInfo info ) {
				
				DocCnd docCnd = new DocCnd();
				docCnd.setTargetOid( info.getOid() );
				docCnd.setTargetObject( MaterialInfo.getObjectType() );
				
				List<DocInfo> docList = docBLO.viewList( docCnd );
				if ( CollectionUtils.isEmpty( docList ) ) {
						return;
				}
				
				List<String> currentVersionOidList = docList.stream().map( DocInfo::getCurrentVersionOid ).collect( Collectors.toList());
				List<FileInfo> fileList = fileBLO.listByTarget( SystemConstants.OBJECT_FW_TYPE_DOC.getKey(), currentVersionOidList );
				
				for( DocInfo doc : docList ){
						for( FileInfo file : fileList ){
								if( file.getTargetOid().equals( doc.getCurrentVersionOid()) ){
										doc.setDocFileSize( file.getFileSize() );
										break;
								}
						}
				}
				
				info.setDocList( docList );
				
		}
		
		/**
		 * 소재에 분류정보를 채워줍니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void fillClassification( MaterialInfo info, MaterialCnd cnd ) {
				
				ClassificationCnd classificationCnd = new ClassificationCnd();
				classificationCnd.setPartOid( info.getOid() );
				classificationCnd.setLang( info.getLang() );
				classificationCnd.setFillIconFile( true );
				if ( StringUtils.hasText( cnd.getCategoryType() ) ) {
						classificationCnd.setCategoryType( cnd.getCategoryType() );
				}
				
				List<ClassificationInfo> classificationList = classificationBLO.listAllTreeChildren( classificationCnd );
				if ( CollectionUtils.isEmpty( classificationList ) ) {
						return;
				}
				Map<String, List<ClassificationInfo>> groupedMap = classificationList.stream()
																			   .sorted( Comparator.comparing( ClassificationInfo::getOrderNo ) )
																			   .collect( Collectors.groupingBy( ClassificationInfo::getCategoryType, Collectors.toList() ) );
				
				info.setApplicationList( groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_APPLICATION ) );
				
				int applicationCnt = CollectionUtils.isEmpty( groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_APPLICATION ) ) ? 0 : groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_APPLICATION ).size();
				info.setApplicationCnt( applicationCnt );
				
				info.setProductClassificationList( groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_PRODUCT ) );
				
				int productClassificationCnt = CollectionUtils.isEmpty( groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_PRODUCT ) ) ? 0 : groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_PRODUCT ).size();
				info.setProductClassificationCnt( productClassificationCnt );
				
				info.setFunctionList( groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_FUNCTION ) );
				
				int functionCnt = CollectionUtils.isEmpty( groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_FUNCTION ) ) ? 0 : groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_FUNCTION ).size();
				info.setFunctionCnt( functionCnt );
		}
		
		/**
		 * 소재리스트에 분류정보 리스트를 채워줍니다.
		 *
		 * @param list
		 * @author 최원준
		 */
		private void fillClassificationList( List<MaterialInfo> list, String lang ) {
				
				List<String> materialOidList = list.stream().map( MaterialInfo::getOid ).collect( Collectors.toList() );
				
				ClassificationCnd classificationCnd = new ClassificationCnd();
				classificationCnd.setLang( lang );
				classificationCnd.setPartOidList( materialOidList );
				classificationCnd.addCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_PRODUCT );
				classificationCnd.addCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_APPLICATION );
				classificationCnd.addCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_FUNCTION );
				
				List<ClassificationInfo> classificationList = classificationBLO.listAllTreeChildren( classificationCnd );
				
				if ( CollectionUtils.isEmpty( classificationList ) ) {
						return;
				}
				
				Map<String, List<ClassificationInfo>> groupedMap = classificationList.stream().collect( Collectors.groupingBy( ClassificationInfo::getCategoryType, Collectors.toList() ) );
				
				fillProductClassification( list, groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_PRODUCT ) );
				fillApplication( list, groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_APPLICATION ) );
				fillFunction( list, groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_FUNCTION ) );
		}
		
		/**
		 * 제품구분 정보를 채워줍니다.
		 *
		 * @param materialList
		 * @param classificationList
		 * @author 최원준
		 */
		private void fillProductClassification( List<MaterialInfo> materialList, List<ClassificationInfo> classificationList ) {
				
				if ( CollectionUtils.isEmpty( classificationList ) ) {
						return;
				}
				
				Map<String, List<ClassificationInfo>> groupedMap = classificationList.stream()
																			   .sorted( Comparator.comparing(ClassificationInfo::getName))
																			   .collect( Collectors.groupingBy( ClassificationInfo::getPartOid, Collectors.toList() ) );
				
				materialList.forEach( material -> {
						List<ClassificationInfo> matchedList = groupedMap.get( material.getOid() );
						if ( CollectionUtils.isNotEmpty( matchedList ) ) {
								material.setProductClassificationList( matchedList );
								material.setProductClassificationCnt( matchedList.size() );
						}
				} );
				
		}
		
		/**
		 * Application 정보를 채워줍니다.
		 *
		 * @param materialList
		 * @param classificationList
		 * @author 최원준
		 */
		private void fillApplication( List<MaterialInfo> materialList, List<ClassificationInfo> classificationList ) {
				
				if ( CollectionUtils.isEmpty( classificationList ) ) {
						return;
				}
				
				Map<String, List<ClassificationInfo>> groupedMap = classificationList.stream()
																			   .sorted( Comparator.comparing(ClassificationInfo::getOrderNo))
																			   .collect( Collectors.groupingBy( ClassificationInfo::getPartOid, Collectors.toList() ) );
				
				materialList.forEach( material -> {
						List<ClassificationInfo> matchedList = groupedMap.get( material.getOid() );
						if ( CollectionUtils.isNotEmpty( matchedList ) ) {
								material.setApplicationList( matchedList );
								material.setApplicationCnt( matchedList.size() );
						}
				} );
				
		}
		
		/**
		 * function 정보를 채워줍니다.
		 *
		 * @param materialList
		 * @param classificationList
		 * @author 최원준
		 */
		private void fillFunction( List<MaterialInfo> materialList, List<ClassificationInfo> classificationList ) {
				
				if ( CollectionUtils.isEmpty( classificationList ) ) {
						return;
				}
				
				Map<String, List<ClassificationInfo>> groupedMap = classificationList.stream()
																			   .collect( Collectors.groupingBy( ClassificationInfo::getPartOid, Collectors.toList() ) );
				
				materialList.forEach( material -> {
						List<ClassificationInfo> matchedList = groupedMap.get( material.getOid() );
						if ( CollectionUtils.isNotEmpty( matchedList ) ) {
								material.setFunctionList( matchedList );
								material.setFunctionCnt( matchedList.size() );
						}
				} );
				
		}
		
		/**
		 * 소재정보에 제품과 분류정보 카운트를 채워줍니다.
		 *
		 * @param list
		 * @author 최원준
		 */
		private void fillProductCnt( List<MaterialInfo> list, String lang ) {
				
				List<ProductInfo> productCntList = productBLO.cntListGroupByMaterialOid( new ProductCnd().setLang( lang ) );
				
				if ( CollectionUtils.isEmpty( productCntList ) ) {
						return;
				}
				
				list.forEach( material -> {
						productCntList.forEach( productCnt -> {
								if ( material.getOid().equals( productCnt.getMaterialOid() ) ) {
										material.setProductCnt( productCnt.getProductCnt() );
								}
						} );
				} );
				
		}
		
		/**
		 * 단일 소재정보에 제품 카운트를 채워줍니다.
		 *
		 * @param info
		 * @author 황지영
		 */
		private void fillProductCnt( MaterialInfo info ) {
				
				if ( info == null ) {
						return;
				}
				
				ProductCnd productCnd = new ProductCnd();
				productCnd.setMaterialOid( info.getOid() );
				productCnd.setLang( info.getLang() );
				
				List<ProductInfo> productCntList = productBLO.cntListGroupByMaterialOid( productCnd );
				
				if ( CollectionUtils.isEmpty( productCntList ) ) {
						return;
				}
				
				info.setProductCnt( productCntList.get( 0 ).getProductCnt() );
		}
		
}

