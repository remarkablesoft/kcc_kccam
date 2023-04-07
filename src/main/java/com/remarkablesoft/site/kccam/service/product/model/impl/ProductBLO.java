package com.remarkablesoft.site.kccam.service.product.model.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.remarkablesoft.framework.service.board.contents.vo.ContentsCnd;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.audit.view.model.impl.AuditViewBLO;
import com.remarkablesoft.framework.service.audit.view.vo.AuditViewInfo;
import com.remarkablesoft.framework.service.board.contents.model.impl.ContentsBLO;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;
import com.remarkablesoft.framework.service.doc.doc.model.impl.DocBLO;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryCnd;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryInfo;
import com.remarkablesoft.framework.service.org.branch.model.impl.BranchBLO;
import com.remarkablesoft.framework.service.org.branch.vo.BranchCnd;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;
import com.remarkablesoft.framework.service.org.user.model.impl.UserBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserCnd;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.AssertUtils;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.site.kccam.service.classification.model.impl.ClassificationBLO;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationCnd;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationInfo;
import com.remarkablesoft.site.kccam.service.datasheet.model.impl.DatasheetBLO;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetCnd;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetInfo;
import com.remarkablesoft.site.kccam.service.material.model.impl.MaterialBLO;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialCnd;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductRelInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 *
 * 		@주시스템		:	kccam
 * 		@서브 시스템		:	product - product
 * 		@프로그램 ID		:	ProductBLO
 * 		@프로그램 개요	:	제품정보 BLO
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@BLO
public class ProductBLO {

		@Autowired
		protected ProductDAO productDAO;

		@Autowired
		protected ProductLangDAO productLangDAO;

		@Autowired
		protected ProductRelBLO productRelBLO;

		@Autowired
		protected MaterialBLO materialBLO;

		@Autowired
		protected ContentsBLO contentsBLO;

		@Autowired
		protected DocBLO docBLO;

		@Autowired
		protected ClassificationBLO classificationBLO;

		@Autowired
		protected BranchBLO branchBLO;

		@Autowired
		protected UserBLO userBLO;

		@Autowired
		protected DatasheetBLO datasheetBLO;

		@Autowired
		protected AuditViewBLO auditViewBLO;

		/**
		 * 제품 정보를 저장합니다.
		 *
		 * @param info
		 * @return ProductInfo
		 * @author 최원준
		 */
		@CacheEvict( value = "product", allEntries = true )
		public ProductInfo insert( ProductInfo info ) {

				if ( StringUtils.hasText( info.getOid() ) && StringUtils.hasText( info.getProductCode() ) ) {
						ProductCnd cnd = new ProductCnd();
						cnd.setOid( info.getOid() ).setProductCode( info.getProductCode() );
						AssertUtils.isTrue( exist( cnd ), "This product cannot be registered because it is duplicated." );
				}


				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}

				insertDoc( info );
				insertContents( info );
				insertDatasheet( info );
				insertProductClassification( info );
				insertFunctionClassification( info );
				insertContact( info );

				// 제품 관계정보를 저장합니다.(마지막)
				insertProductRel( info );

				productDAO.insert( info );
		
				if ( CollectionUtils.isNotEmpty( info.getLangProductList() ) ) {
						insertOrUpdateLangList( info );
				}
				else {
						productLangDAO.insert( info );
				}

				return info;
		}

		/**
		 * 제품 정보를 수정합니다.
		 *
		 * @param info
		 * @return ProductInfo
		 * @author 최원준
		 */
		@CacheEvict( value = "product", allEntries = true )
		public ProductInfo update( ProductInfo info ) {

				if ( info == null || StringUtils.isEmpty( info.getOid() ) ) {
						return null;
				}

				if ( info.getModDate() == null ) {
						info.setModDate( LocalDateTime.now() );
				}

				updateDoc( info );
//				updateContents( info );

				// 제품 관계 정보를 수정합니다.(마지막)
				updateProductRel( info );

				productDAO.update( info );
		
				if ( CollectionUtils.isNotEmpty( info.getLangProductList() ) ) {
						insertOrUpdateLangList( info );
				}
				else {
						productLangDAO.update( info );
				}

				return info;
		}
    
		/**
		 * 다국어 리스트 등록
		 *
		 * @author Woong
		 * @param product
		 * @return
		 */
		@CacheEvict( value = "product", allEntries = true )
		public int insertOrUpdateLangList( ProductInfo product ) {
				if (  CollectionUtils.isEmpty( product.getLangProductList() ) ) {
						return 0;
				}
			
				int result = 0;
			
				for ( ProductInfo productLang : product.getLangProductList() ) {
						String tempLang = productLang.getLang();
				
						productLang.setLang( tempLang.toUpperCase() );
				
						ProductCnd cnd = new ProductCnd();
				
						cnd.setOid( product.getOid() );
						cnd.setLang( productLang.getLang() );
				
						boolean exist = productLangDAO.exist( cnd );
				
						productLang.setOid( product.getOid() );
						productLang.setModDate( LocalDateTime.now() );
				
						if ( exist ) {
								result += productLangDAO.update( productLang );
								updateLangAddContentsList( productLang );
						} else {
								productLang.setInputDate( LocalDateTime.now() );
								result += productLangDAO.insert( productLang );
								insertLangAddContentsList( productLang );
						}
				}
			
				return result;
		}

		/**
		 * 제품 정보를 삭제합니다.
		 *
		 * @param oid
		 * @return int
		 * @author 최원준
		 */
		@CacheEvict( value = "product", allEntries = true )
		public int delete( ProductCnd cnd ) {

				if ( cnd == null || StringUtils.isEmpty( cnd.getOid() ) ) {
						return 0;
				}

				deleteProductRel( cnd.getOid() );
				deleteDoc( cnd.getOid() );
				contentsBLO.deleteByTarget( cnd.getOid(), ProductInfo.getObjectType() );

				productDAO.delete( cnd.getOid() );

				return productLangDAO.delete( cnd );
		}

		/**
		 * update 시 트랜잭션 단위로 인해 발생하는 무결성 에러를 처리하기 위한 삭제
		 *
		 * @param info
		 * @author 최원준
		 */
		@CacheEvict( value = "product", allEntries = true )
		public void deleteForUpdate( ProductInfo info ) {

				// 관계정보를 삭제 후 재등록합니다.
				ProductCnd cnd = new ProductCnd();
				cnd.setProductOid( info.getOid() );

				// 제품 구분 정보를 삭제합니다.
				cnd.setTargetObject( ClassificationInfo.getProductObjectType() );
				productRelBLO.delete( cnd );

				// Function 정보를 삭제합니다.
				cnd.setTargetObject( ClassificationInfo.getFunctionObjectType() );
				productRelBLO.delete( cnd );

				// 데이터시트 정보를 삭제합니다.
				cnd.setTargetObject( DatasheetInfo.getObjectType() );
				productRelBLO.delete( cnd );

				// 지점 정보를 삭제합니다.
				cnd.setTargetObject( BranchInfo.getObjectType() );
				productRelBLO.delete( cnd );

				// 지점 담당자 정보를 삭제합니다.
				cnd.setTargetObject( UserInfo.getObjectType() );
				productRelBLO.delete( cnd );

		}

		/**
		 *  다수의 제품 관계정보를 삭제합니다.
		 *
		 * @param list
		 * @param targetOid
		 * @param targetObject
		 * @return int
		 * @author 황지영
		 */
		@CacheEvict( value = "product", allEntries = true )
		public int deleteRelOnlyByTarget( String targetOid, String targetObject ) {

				if ( StringUtils.isEmpty( targetOid ) || StringUtils.isEmpty( targetObject ) ){
						return 0;
				}

				ProductCnd cnd = new ProductCnd();
				cnd.setTargetOid( targetOid );
				cnd.setTargetObject( targetObject );

				return productRelBLO.deleteByTarget( cnd );
		}

		/**
		 * 제품 정보를 가져옵니다.
		 *
		 * @param cnd
		 * @return ProductInfo
		 * @author 최원준
		 */
		@Cacheable( value = "product", keyGenerator = "cacheKeyGenerator" )
		public ProductInfo get( ProductCnd cnd ) {

				if ( cnd == null ) {
						return null;
				}

				ProductInfo info = productDAO.get( cnd );
				if ( info == null ) {
						return null;
				}


				fillMaterial( info );
				fillProductRel( info );
				fillDoc( info );
				fillContents( info );
        
				// 다국어 정보를 채울지 여부
				if ( cnd.isFillLangList() && StringUtils.isNotEmpty( cnd.getOid() ) ) {
						List<ProductInfo> langList = productLangDAO.listAll( cnd.getOid() );
			
						info.setLangProductList( langList );
						
						// langAddContentsList 채우기
						List<ProductInfo> langProductList = info.getLangProductList();
						for ( ProductInfo product : langProductList ) {
								
								ContentsCnd contentsCnd = new ContentsCnd();
								contentsCnd.setTargetObject( product.getObjectType() );
								contentsCnd.setTargetOid( product.getOid() );
								contentsCnd.setLang( product.getLang() );
								
								List<ContentsInfo> contentsList = contentsBLO.list( contentsCnd );
								
								if( CollectionUtils.isNotEmpty( contentsList ) ){
										product.setLangAddContentsList( contentsList );
								}
						}
				}

				return info;
		}

		/**
		 * 제품정보 페이지 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return PageList<ProductInfo>
		 * @author 최원준
		 */
		@Cacheable( value = "product", keyGenerator = "cacheKeyGenerator" )
		public PageList<ProductInfo> list( ProductCnd cnd ) {

				if ( cnd == null ) {
						return null;
				}

				PageList<ProductInfo> list = productDAO.list( cnd );
				if ( CollectionUtils.isEmpty( list ) ) {
						return null;
				}

				fillMaterial( list, cnd );

				return list;
		}

		/**
		 * 타겟 OID를 조건으로 하는 페이지리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return PageList<ProductInfo>
		 * @author 최원준
		 */
		@Cacheable( value = "product", keyGenerator = "cacheKeyGenerator" )
		public PageList<ProductInfo> listByTargetOid( ProductCnd cnd ) {

				if ( StringUtils.isEmpty( cnd.getTargetOid() ) ) {
						return null;
				}

				List<ProductRelInfo> relList = productRelBLO.listAll( cnd );
				if ( CollectionUtils.isEmpty( relList ) ) {
						return null;
				}

				List<String> productOidList = relList.stream().map( ProductRelInfo::getProductOid ).collect( Collectors.toList());
				ProductCnd productCnd = new ProductCnd();
				productCnd.setOidList( productOidList );
				productCnd.setStartIndex( cnd.getStartIndex() );
				productCnd.setPageSize( cnd.getPageSize() );
				productCnd.setFillMaterial( cnd.isFillMaterial() );

				PageList<ProductInfo> list = list( productCnd );

				fillMaterial( list , productCnd );

				return list;
		}


		/**
		 * 제품정보 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return List<ProductInfo>
		 * @author 최원준
		 */
		@Cacheable( value = "product", keyGenerator = "cacheKeyGenerator" )
		public List<ProductInfo> listAll( ProductCnd cnd ) {

				if ( cnd == null ) {
						return null;
				}

				List<ProductInfo> list = productDAO.listAll( cnd );
				if ( CollectionUtils.isEmpty( list ) ) {
						return null;
				}

				fillDatasheet( list, cnd );
				fillMaterial( list, cnd );

				return list;
		}

		/**
		 * 소재 OID로 GroupBy된 제품 카운트 리스트를 반환합니다.
		 *
		 * @param cnd
		 * @return List<ProductInfo>
		 * @author 최원준
		 */
		@Cacheable( value = "product", keyGenerator = "cacheKeyGenerator" )
		public List<ProductInfo> cntListGroupByMaterialOid( ProductCnd cnd ) {
				List<ProductInfo> list = productDAO.cntListGroupByMaterialOid( cnd );
				return list;
		}


		/**
		 * 제품 - 제품Rel 로 만든 View에서 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return List<ProductInfo>
		 * @author 최원준
		 */
		@Cacheable( value = "product", keyGenerator = "cacheKeyGenerator" )
		public List<ProductInfo> viewListAll( ProductCnd cnd ) {
				if ( cnd == null ) {
						return null;
				}

				List<ProductInfo> list = productDAO.viewListAll( cnd );
				if ( CollectionUtils.isEmpty( list ) ) {
						return null;
				}

				fillMaterial( list, cnd );
				fillDatasheet( list, cnd );

				return list;
		}

		/**
		 * 제품 - 제품 REL 에서 타겟 OID에 속하지 않은 제품 리스트를 반환합니다.
		 *
		 * @param cnd
		 * @return List<ProductInfo>
		 * @author 최원준
		 */
		@Cacheable( value = "product", keyGenerator = "cacheKeyGenerator" )
		public List<ProductInfo> targetExceptList( ProductCnd cnd ) {
				if ( cnd == null ) {
						return null;
				}

				List<ProductInfo> list = productDAO.targetExceptList( cnd );
				if ( CollectionUtils.isEmpty( list ) ) {
						return null;
				}

				fillMaterial( list, cnd );

				return list;
		}
	
		/**
		 * 제품정보 존재여부를 체크합니다.
		 *
		 * @param oid
		 * @return boolean
		 * @author 최원준
		 */
		public boolean exist( ProductCnd cnd ) {

				if ( cnd == null ) {
						return false;
				}

				return productDAO.exist( cnd );
		}

		/**
		 * 제품 조회수 카운트를 증가시킵니다.
		 *
		 * @param info
		 * @return int
		 * @author 최원준
		 */
		@CacheEvict( value = "product", allEntries = true )
		public int addViewCnt( ProductInfo info ) {

				int result = 0;
				if ( info == null || StringUtils.isEmpty( info.getOid() ) ) {
						return 0;
				}

				AuditViewInfo productViewInfo = SystemFactory.getAuditViewInfo();
				productViewInfo.setTargetOid( info.getOid() );
				productViewInfo.setTargetObject( ProductInfo.getObjectType() );
				productViewInfo.setViewDate( LocalDateTime.now() );

				AuditViewInfo auditViewInfo = auditViewBLO.insert( productViewInfo );
				if ( auditViewInfo != null ) {
						result++;
				}

				if ( StringUtils.isEmpty( info.getMaterialOid() ) ) {
						return result;
				}

				AuditViewInfo materialViewInfo = SystemFactory.getAuditViewInfo();
				materialViewInfo.setTargetOid( info.getMaterialOid() );
				materialViewInfo.setTargetObject( MaterialInfo.getObjectType() );
				materialViewInfo.setViewDate( LocalDateTime.now() );
				auditViewInfo = auditViewBLO.insert( materialViewInfo );

				if ( auditViewInfo != null ) {
						result++;
				}

				return result;
		}

		/**
		 * 제품 관계정보를 저장합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void insertProductRel( ProductInfo info ) {

				if ( CollectionUtils.isEmpty( info.getProductRelList() ) ) {
						return;
				}

				info.getProductRelList().forEach( productRel -> productRel.setProductOid( info.getOid() ) );

				int result = productRelBLO.insertBulk( info.getProductRelList() );
				AssertUtils.isTrue( result == 0, "Product Info insert error" );

		}

		/**
		 * 제품 관련 문서 정보를 저장합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void insertDoc( ProductInfo info ) {

				if ( CollectionUtils.isEmpty( info.getDocList() ) ) {
						return;
				}

				info.getDocList().forEach( doc -> {
						doc.setOid( OIDGenerator.generateOID() );
						docBLO.insert( doc, info.getOid(), ProductInfo.getObjectType() );
				} );

		}

		/**
		 * 제품 관련 추가정보를 저장합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void insertContents( ProductInfo info ) {

				if ( CollectionUtils.isEmpty( info.getAddContentsList() ) ) {
						return;
				}

				info.getAddContentsList().forEach( contents -> contents.setTargetOid( info.getOid() )
							                                               .setTargetObject( ProductInfo.getObjectType() ) );

				contentsBLO.insertBulk( info.getAddContentsList() );

		}

		/**
		 * 데이터시트 정보를 저장합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void insertDatasheet( ProductInfo info ) {
				if ( info.getDatasheetInfo() == null ) {
						return;
				}

				if ( StringUtils.hasText( info.getDatasheetInfo().getOid() ) ) {
						datasheetBLO.delete( info.getDatasheetInfo().getOid() );
				}

				info.getDatasheetInfo().setOid( OIDGenerator.generateOID() );
				DatasheetInfo datasheetInfo = datasheetBLO.insert( info.getDatasheetInfo() );

				if ( datasheetInfo != null ) {
						ProductRelInfo relInfo = new ProductRelInfo();
						relInfo.setTargetOid( datasheetInfo.getOid() );
						relInfo.setTargetObject( DatasheetInfo.getObjectType() );
						info.addProductRelInfo( relInfo );
				}
		}

		/**
		 * 제품구분 정보를 저장합니다
		 *
		 * @param info
		 * @author 최원준
		 */
		private void insertProductClassification ( ProductInfo info ) {

				if ( info.getProductClassificationInfo() == null ) {
						return;
				}

				ProductRelInfo relInfo = new ProductRelInfo();
				relInfo.setTargetOid( info.getProductClassificationInfo().getOid() );
				relInfo.setTargetObject( ClassificationInfo.getProductObjectType() );
				info.addProductRelInfo( relInfo );

		}

		/**
		 * Function 정보를 저장합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void insertFunctionClassification( ProductInfo info ) {
				if ( CollectionUtils.isEmpty( info.getFunctionList() ) ) {
						return;
				}

				info.getFunctionList().forEach( function -> {
						ProductRelInfo relInfo = new ProductRelInfo();
						relInfo.setTargetOid( function.getOid() );
						relInfo.setTargetObject( ClassificationInfo.getFunctionObjectType() );
						info.addProductRelInfo( relInfo );
				});

		}

		/**
		 * Contact 정보를 저장합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void insertContact( ProductInfo info ) {

				if ( CollectionUtils.isEmpty( info.getBranchList() ) ) {
						return;
				}

				info.getBranchList().forEach( branch -> {

						ProductRelInfo relInfo = new ProductRelInfo();
						relInfo.setTargetOid( branch.getOid() );
						relInfo.setTargetObject( BranchInfo.getObjectType() );
						info.addProductRelInfo( relInfo );
				} );

				if ( CollectionUtils.isEmpty( info.getBranchManagerList() ) ) {
						return;
				}

				info.getBranchManagerList().forEach( user -> {
						ProductRelInfo userRelInfo = new ProductRelInfo();
						userRelInfo.setTargetOid( user.getOid() );
						userRelInfo.setTargetObject( UserInfo.getObjectType() );
						info.addProductRelInfo( userRelInfo );
				} );
		}
		
		/**
		 * 소재정보의 추가정보를 추가합니다.(다국어)
		 *
		 * @param info
		 * @author 황지영
		 */
		private int insertLangAddContentsList( ProductInfo info ){
				
				if ( CollectionUtils.isEmpty( info.getLangAddContentsList() )) {
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
		private int updateLangAddContentsList( ProductInfo info ){
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
		 * 제품 관련 정보를 수정합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void updateProductRel( ProductInfo info ) {

				// 관련 정보를 저장합니다.
				insertProductClassification( info );
				insertFunctionClassification( info );
				insertDatasheet( info );
				insertContact( info );

				insertProductRel( info );
		}

		/**
		 * 제품 관련 문서를 수정합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void updateDoc( ProductInfo info ) {
				// 문서 삭제 후 재등록합니다.
				DocCnd docCnd = new DocCnd();
				docCnd.setTargetOid( info.getOid() ).setTargetObject( ProductInfo.getObjectType() );
				List<DocInfo> existDocList = docBLO.viewList( docCnd );

				if ( CollectionUtils.isNotEmpty( existDocList ) ) {
						existDocList.forEach( doc -> docBLO.delete( doc.getOid() ) );
				}

				insertDoc( info );
		}

		/**
		 * 제품 추가정보를 수정합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void updateContents( ProductInfo info ) {
				// 삭제 후 재등록합니다.
				contentsBLO.deleteByTarget( info.getOid(), ProductInfo.getObjectType() );
				insertContents( info );
		}

		/**
		 * 제품 관계정보를 삭제합니다.
		 *
		 * @param oid
		 * @author 최원준
		 */
		private void deleteProductRel( String oid ) {
				ProductCnd cnd = new ProductCnd();
				cnd.setOid( oid );
				productRelBLO.delete( cnd );
		}

		/**
		 * 제품의 문서정보를 삭제합니다.
		 *
		 * @param oid
		 * @author 최원준
		 */
		private void deleteDoc( String oid ) {
				DocCnd docCnd = new DocCnd();
				docCnd.setTargetOid( oid ).setTargetObject( ProductInfo.getObjectType() );
				List<DocInfo> docList = docBLO.viewList( docCnd );

				docBLO.deleteRelOnly( docList, oid, ProductInfo.getObjectType() );

		}

		/**
		 * 소재 정보를 채워줍니다.
		 * @param info
		 * - 수정 사항 : 21.03.29 안병현, 해당하는
		 */
		private void fillMaterial( ProductInfo info ) {

				if( StringUtils.isEmpty( info.getMaterialOid() ) ) {
						return;
				}

				MaterialCnd materialCnd = new MaterialCnd();
				materialCnd.setOid( info.getMaterialOid() );
				MaterialInfo material = materialBLO.get( materialCnd );

				if ( material == null ) {
						return;
				}

				info.setMaterialInfo( material );
		}

		/**
		 * 제품정보를 채워줍니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void fillProductRel( ProductInfo info ) {

				ProductCnd cnd = new ProductCnd();
				cnd.setProductOid( info.getOid() );
				List<ProductRelInfo> productRelList = productRelBLO.listAll( cnd );

				if ( CollectionUtils.isEmpty( productRelList ) ) {
						return;
				}
				info.setProductRelList( productRelList );
				Map<String, List<ProductRelInfo>> groupedMap = productRelList.stream()
							                                               .sorted( Comparator.comparing( ProductRelInfo::getInputDate ) )
							                                               .collect( Collectors.groupingBy( ProductRelInfo::getTargetObject, Collectors.toList() ) );

				List<ProductRelInfo> classificationRelList = new ArrayList<>();
				List<ProductRelInfo> productGroupList = groupedMap.get( ClassificationInfo.getProductObjectType() );
				List<ProductRelInfo> applicationGroupList = groupedMap.get( ClassificationInfo.getApplicationObjectType() );
				List<ProductRelInfo> marketGroupList = groupedMap.get( ClassificationInfo.getMarketObjectType() );
				List<ProductRelInfo> functionGroupList = groupedMap.get( ClassificationInfo.getFunctionObjectType() );

				if ( CollectionUtils.isNotEmpty( productGroupList ) ) {
						classificationRelList.addAll( productGroupList );
				}
				if ( CollectionUtils.isNotEmpty( applicationGroupList ) ) {
						classificationRelList.addAll( applicationGroupList );
				}
				if ( CollectionUtils.isNotEmpty( marketGroupList ) ) {
						classificationRelList.addAll( marketGroupList );
				}
				if ( CollectionUtils.isNotEmpty( functionGroupList ) ) {
						classificationRelList.addAll( functionGroupList );
				}

				fillClassification( info, classificationRelList );
				fillBranch( info, groupedMap.get( BranchInfo.getObjectType() ) );
				fillBranchManger( info, groupedMap.get( UserInfo.getObjectType() ) );
				fillDatasheet( info, groupedMap.get( DatasheetInfo.getObjectType() ) );

		}

		/**
		 * 제품구분/Application/Market/Function 정보를 채워줍니다.
		 *
		 * @param info
		 * @param relList
		 * @author 최원준
		 */
		private void fillClassification( ProductInfo info, List<ProductRelInfo> relList ) {

				if ( CollectionUtils.isEmpty( relList ) ) {
						return;
				}

				List<String> classificationOidList = relList.stream().map( ProductRelInfo::getTargetOid )
							                                     .collect( Collectors.toList());

				ClassificationCnd classificationCnd = new ClassificationCnd();
				classificationCnd.setCategoryOidList( classificationOidList );
				classificationCnd.setFillFullPathName( true );
				classificationCnd.setLang( info.getLang() );
				List<ClassificationInfo> classificationList = classificationBLO.listAll( classificationCnd );

				if ( CollectionUtils.isEmpty( classificationList ) ) {
						return;
				}

				Map<String, List<ClassificationInfo>> groupedMap = classificationList.stream()
							                                                   .collect( Collectors.groupingBy( ClassificationInfo::getCategoryType, Collectors.toList() ) );

				// 제품구분 정보를 채워줍니다.
				List<ClassificationInfo> productClassificationList = groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_PRODUCT );
				if ( CollectionUtils.isNotEmpty( productClassificationList ) && productClassificationList.size() == 1 ) {
						info.setProductClassificationInfo( productClassificationList.get( 0 ) );
				}

				// Function 을 채워줍니다.
				fillFunction( info, groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_FUNCTION ) );

				// Market 을 채워줍니다.
				if ( CollectionUtils.isNotEmpty( groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_MARKET ) ) ) {
						info.setMarketList( groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_MARKET ) );
				}

				// Application 을 채워줍니다.
				if ( CollectionUtils.isNotEmpty( groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_APPLICATION ) ) ) {
						info.setApplicationList( groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_APPLICATION ) );
				}
		}

		/**
		 * 제품정보에 function 을 채워줍니다.
		 *
		 * @param info
		 * @param list
		 * @author 최원준
		 */
		private void fillFunction( ProductInfo info, List<ClassificationInfo> list ) {

				if ( CollectionUtils.isEmpty( list ) ) {
						return;
				}

				info.setFunctionList( list );

				if ( StringUtils.hasText( info.getMainFuncOid() ) ) {
						list.forEach( function -> {

								if ( info.getMainFuncOid().equals( function.getOid() ) ) {
										info.setMainFuncInfo( function );
								}

						});
				}

		}

		/**
		 * Contact 지점정보를 채워줍니다.
		 *
		 * @param info
		 * @param relList
		 * @author 최원준
		 */
		private void fillBranch( ProductInfo info, List<ProductRelInfo> relList ) {

				if ( CollectionUtils.isEmpty( relList ) ) {
						return;
				}

				List<String> branchOidList = relList.stream()
							                             .map( ProductRelInfo::getTargetOid )
							                             .collect( Collectors.toList());

				BranchCnd branchCnd = new BranchCnd();
				branchCnd.setBranchOidList( branchOidList );
				List<BranchInfo> branchList = branchBLO.listAll( branchCnd );

				if ( CollectionUtils.isEmpty( branchList ) ) {
						return;
				}

				info.setBranchList( branchList );

		}

		/**
		 * 지점의 Contact 매니저 정보를 채워줍니다.
		 *
		 * @param info
		 * @param relList
		 * @author 최원준
		 */
		private void fillBranchManger( ProductInfo info, List<ProductRelInfo> relList ) {

				if ( CollectionUtils.isEmpty( relList ) ) {
						return;
				}

				List<String> managerOidList = relList.stream().map( ProductRelInfo::getTargetOid ).collect( Collectors.toList() );
				UserCnd userCnd = new UserCnd();
				userCnd.setUserOidList( managerOidList );
				List<UserInfo> managerList = userBLO.listAll( userCnd );

				if ( CollectionUtils.isEmpty( managerList ) ) {
						return;
				}

				info.setBranchManagerList( managerList );

		}

		/**
		 * 문서정보를 채워줍니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void fillDoc( ProductInfo info ) {
				DocCnd docCnd = new DocCnd();
				docCnd.setFillFile( true );
				docCnd.setTargetOid( info.getOid() ).setTargetObject( ProductInfo.getObjectType() );
				//List<DocInfo> docList = docBLO.listAll( docCnd );

				//21.03.29 안병현 : 기존의 listAll에서는 TB_DOC에서만 조회 -> targetOid로 검색 불가 -> viewList로 전환
				List<DocInfo> docList = docBLO.viewList( docCnd );

				if ( CollectionUtils.isEmpty( docList ) ) {
						return;
				}

				info.setDocList( docList );
		}

		/**
		 * 제품 추가정보를 채워줍니다.
		 *
		 * @param info
		 * @author 최원준
		 * 수정 사항 : 21.03.30 안병현 CollectionUtils.isNotEmpty -> isEmpty
		 */
		private void fillContents( ProductInfo info ) {
				
				if ( info == null ) {
						return;
				}
				
				ContentsCnd cnd = new ContentsCnd();
				cnd.setTargetObject( info.getObjectType() );
				cnd.setTargetOid( info.getOid() );
				cnd.setLang( info.getLang() );
				
				List<ContentsInfo> addContentsList = contentsBLO.list( cnd );
				if ( CollectionUtils.isEmpty( addContentsList ) ) { //수정 전 : CollectionUtils.isNotEmpty( addContentsList)
						return;
				}

				info.setAddContentsList( addContentsList );
		}

		/**
		 * 제품 리스트에 소재정보를 채워줍니다.
		 *
		 * @param list
		 * @author 최원준
		 */
		private void fillMaterial( List<ProductInfo> list, ProductCnd cnd ) {

				if ( !cnd.isFillMaterial() ) {
						return;
				}

				List<String> materialOidList = list.stream().map( ProductInfo::getMaterialOid ).distinct().collect( Collectors.toList());
				MaterialCnd materialCnd = new MaterialCnd();
				materialCnd.setOidList( materialOidList );
				List<MaterialInfo> materialList = materialBLO.listAll( materialCnd );

				if ( CollectionUtils.isEmpty( materialList ) ) {
						return;
				}

				list.forEach( product -> materialList.stream()
							                         .filter( material -> StringUtils.hasText( product.getMaterialOid() ) )
							                         .filter( material -> product.getMaterialOid().equals( material.getOid() ) )
							                         .forEach( product::setMaterialInfo ) );

		}

		/**
		 * 제품정보에 데이터시트를 채워줍니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void fillDatasheet( ProductInfo info, List<ProductRelInfo> datasheetProductRelList ) {

				if ( CollectionUtils.isEmpty( datasheetProductRelList ) ) {
						return;
				}

				List<String> datasheetOidList = datasheetProductRelList.stream()
							                                .map( ProductRelInfo::getTargetOid )
							                                .collect( Collectors.toList() );

				if( CollectionUtils.isEmpty( datasheetOidList ) ) {
						return;
				}

				DatasheetCnd datasheetCnd = new DatasheetCnd();
				datasheetCnd.setOid( datasheetOidList.get( 0 ) );
				DatasheetInfo datasheetInfo = datasheetBLO.get( datasheetCnd );

				if ( datasheetInfo == null ) {
						return;
				}

				info.setDatasheetInfo( datasheetInfo );

		}

		/**
		 * 제품정보 리스트에 데이터시트를 채워줍니다.
		 *
		 * @param list
		 * @author 최원준
		 */
		private void fillDatasheet( List<ProductInfo> list, ProductCnd cnd ) {

				if ( !cnd.isFillDatasheet() ) {
						return;
				}

				List<String> productOidList = list.stream().map( ProductInfo::getOid ).collect( Collectors.toList() );
				ProductCnd relCnd = new ProductCnd();
				relCnd.setOidList( productOidList ).setTargetObject( DatasheetInfo.getObjectType() );
				List<ProductRelInfo> productRelList = productRelBLO.listAll( relCnd );

				if ( CollectionUtils.isEmpty( productRelList ) ) {
						return;
				}

				List<String> datasheetOidList = productRelList.stream().map( ProductRelInfo::getTargetOid ).collect( Collectors.toList() );
				DatasheetCnd datasheetCnd = new DatasheetCnd();
				datasheetCnd.setOidList( datasheetOidList );
				List<DatasheetInfo> datasheetList = datasheetBLO.listAll( datasheetCnd );

				if ( CollectionUtils.isEmpty( datasheetList ) ) {
						return;
				}

				Map<String, DatasheetInfo> datasheetMap = datasheetList.stream().collect( Collectors.toMap( DatasheetInfo::getOid, datasheetInfo -> datasheetInfo ) );


				list.forEach( product -> productRelList.stream().filter( rel -> product.getOid().equals( rel.getProductOid() ) )
							                         .forEach( rel -> product.setDatasheetInfo( datasheetMap.get( rel.getTargetOid() ) ) )
				);

		}

}
