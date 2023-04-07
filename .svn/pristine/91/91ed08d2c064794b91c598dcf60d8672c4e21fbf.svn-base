package com.remarkablesoft.site.kccam.service.classification.model.impl;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.remarkablesoft.framework.service.board.contents.vo.ContentsCnd;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryCnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.board.contents.model.impl.ContentsBLO;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;
import com.remarkablesoft.framework.service.doc.doc.model.impl.DocBLO;
import com.remarkablesoft.framework.service.doc.doc.model.impl.DocRelBLO;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.mgnt.category.model.impl.CategoryBLO;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryInfo;
import com.remarkablesoft.framework.util.AssertUtils;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.site.kccam.common.InfoConverter;
import com.remarkablesoft.site.kccam.common.ListConverter;
import com.remarkablesoft.site.kccam.service.AmConstants;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationCnd;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationInfo;
import com.remarkablesoft.site.kccam.service.material.model.impl.MaterialBLO;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialCnd;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;
import com.remarkablesoft.site.kccam.service.product.model.impl.ProductBLO;
import com.remarkablesoft.site.kccam.service.product.model.impl.ProductRelBLO;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductRelInfo;

/**
 * @주시스템 :	kccam
 * @서브 시스템        :   classification
 * @프로그램 ID        :   ClassificationBLO.java
 * @프로그램 개요        :	제품구분/App/마켓/Function 분류 정보 로직
 * @변경이력 ============================================================================
 * 1.0 2021-03-19 : 최원준 - 신규생성
 * ============================================================================
 */
@BLO
public class ClassificationBLO extends CategoryBLO {

	@Autowired
	protected MaterialBLO materialBLO;

	@Autowired
	protected DocBLO docBLO;

	@Autowired
	protected DocRelBLO docRelBLO;

	@Autowired
	protected ContentsBLO contentsBLO;

	@Autowired
	protected ProductRelBLO productRelBLO;

	@Autowired
	protected ProductBLO productBLO;

	/**
	 * 분류 정보를 저장합니다.
	 *
	 * @param info
	 * @return ClassificationInfo
	 * @author 최원준
	 */
	@CacheEvict( value = "classification", allEntries = true )
	public ClassificationInfo insert( ClassificationInfo info ) {

		if ( StringUtils.hasText( info.getClassificationObject() ) && info.getIconFile() != null ) {
			info.getIconFile().setTargetObject( info.getClassificationObject() );
		}

		CategoryInfo categoryInfo = super.insert( info );
		if ( categoryInfo == null || StringUtils.isEmpty( categoryInfo.getOid() ) ) {
			return null;
		}

		info.setOid( categoryInfo.getOid() );

		insertDoc( info );
//		insertContents( info );
		insertProductRel( info );

		return info;

	}

	/**
	 * 분류 정보를 수정합니다.
	 *
	 * @param info
	 * @return ClassificationInfo
	 * @author 최원준
	 */
	@CacheEvict( value = "classification", allEntries = true )
	public ClassificationInfo update( ClassificationInfo info ) {

		if ( StringUtils.hasText( info.getClassificationObject() ) && info.getIconFile() != null ) {
			info.getIconFile().setTargetObject( info.getClassificationObject() );
		}

		CategoryInfo categoryInfo = super.update( info );
		if ( categoryInfo == null || StringUtils.isEmpty( categoryInfo.getOid() ) ) {
			return null;
		}

		updateDoc( info );
//		updateContents( info );
		updateProductRel( info );

		return info;

	}

	/**
	 * 분류 정보를 삭제합니다
	 *
	 * @param cnd
	 * @return int
	 * @author 최원준
	 */
	@CacheEvict( value = "classification", allEntries = true )
	public int delete( ClassificationCnd cnd ) {

		if ( cnd == null ) {
			return 0;
		}

		int deleteResult = super.delete( cnd.getOid() );
		if ( deleteResult == 0 ) {
			return 0;
		}

		deleteDoc( cnd );
		contentsBLO.deleteByTarget( cnd.getOid(), ClassificationInfo.getObjectType() );

		return deleteResult;
	}


	/**
	 * 분류 정보 단건을 가져옵니다.
	 *
	 * @param cnd
	 * @return ClassificationInfo
	 * @author 최원준
	 */
	@Cacheable( value = "classification", keyGenerator = "cacheKeyGenerator" )
	public ClassificationInfo get( ClassificationCnd cnd ) {

		ClassificationInfo info = InfoConverter.convertInfo( super.get( cnd ), ClassificationInfo.class );
		if ( info == null ) {
			return null;
		}

		fillMaterial( info );
		fillDoc( info, cnd );
		fillContents( info );
		fillChildClassification( info, cnd );
		fillProduct( info, cnd );

		fillParentName( info );

		return info;
	}

	/**
	 * 제품 정보를 포함한 제품분류정보를 가져옵니다.
	 *
	 * @param cnd
	 * @return ClassificationInfo
	 * @author 최원준
	 */
	@Cacheable( value = "classification", keyGenerator = "cacheKeyGenerator" )
	public ClassificationInfo getInfoIncludeProduct( ClassificationCnd cnd ) {

		ClassificationInfo info = get( cnd );

		if ( info == null ) {
			return null;
		}

		fillProduct( info, cnd );

		return info;
	}

	/**
	 * 분류정보 페이지리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return PageList<ClassificationInfo>
	 * @author 최원준
	 */
	@Cacheable( value = "classification", keyGenerator = "cacheKeyGenerator" )
	public PageList<ClassificationInfo> list( ClassificationCnd cnd ) {

		PageList<CategoryInfo> categoryList = super.list( cnd );
		if ( CollectionUtils.isEmpty( categoryList ) ) {
			return null;
		}

		PageList<ClassificationInfo> list = ListConverter.convertPageList( categoryList, ClassificationInfo[].class );
		fillMaterial( list, cnd );
		fillProductCnt( list, cnd );

		return list;

	}

	/**
	 * 분류정보 리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return List<ClassificationInfo>
	 * @author 최원준
	 */
	@Cacheable( value = "classification", keyGenerator = "cacheKeyGenerator" )
	public List<ClassificationInfo> listAll( ClassificationCnd cnd ) {

		List<CategoryInfo> categoryList = super.listAll( cnd );
		if ( CollectionUtils.isEmpty( categoryList ) ) {
			return null;
		}

		List<ClassificationInfo> list = ListConverter.convertList( categoryList, ClassificationInfo[].class );
		fillMaterial( list, cnd );
		fillProduct( list, cnd );
		list = list.stream().sorted( Comparator.comparing( ClassificationInfo::getName ) ).collect( Collectors.toList());
		return list;
	}

	/**
	 * FullPathIndex를 가지고 트리구조의 리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return List<ClassificationInfo>
	 * @author 최원준
	 */
	@Cacheable( value = "classification", keyGenerator = "cacheKeyGenerator" )
	public List<ClassificationInfo> listAllTreeChildren( ClassificationCnd cnd ) {

		List<CategoryInfo> categoryList = super.listAllTreeChilds( cnd );
		if ( CollectionUtils.isEmpty( categoryList ) ) {
			return null;
		}

		List<ClassificationInfo> list = ListConverter.convertList( categoryList, ClassificationInfo[].class );
		fillMaterial( list, cnd );
		
		return list;
	}

	/**
	* 동일한 소재 하위의 분류정보를 가져옵니다.
	*
	* @param cnd
	* @return List<ClassificationInfo>
	* @author 최원준
	*/
	public List<ClassificationInfo> sameMaterialList( ClassificationCnd cnd ) {

		AssertUtils.isEmpty( cnd.getOid(), "OID is Empty" );
		ClassificationInfo info = get( cnd );
		if ( info == null ) {
			return null;
		}

		cnd = new ClassificationCnd();
		cnd.setPartOid( info.getPartOid() );
		cnd.setLang( info.getLang() );

		List<ClassificationInfo> list = listAll( cnd );

		if ( CollectionUtils.isEmpty( list ) ) {
			return null;
		}

		return list;
	}

	/**
	* breadcrumb를 사용하기 위한 Map을 만들어 반환합니다.
	*
	* @param
	* @return
	* @author 최원준
	*/
	@Cacheable( value = "classification", keyGenerator = "cacheKeyGenerator" )
	public Map<String, List<ClassificationInfo>> getBreadcrumbMap( ClassificationCnd cnd ) {

		if ( StringUtils.isEmpty( cnd.getOid() ) ) {
			return null;
		}

		// 조회되는 기준 분류정보
		ClassificationInfo info = get( cnd );
		if ( info == null ) {
			return null;
		}

		LinkedHashMap<String, List<ClassificationInfo>> resultMap = new LinkedHashMap<>();

		cnd.setOid( "" );
		List<ClassificationInfo> list;

		// 분류정보의 Root OID
		String classificationRootOid = getClassificationRootOid( info );

		cnd.setParentOid( info.getParentOid() );

		while( true ) {

			list = listAll( cnd );
			if ( CollectionUtils.isEmpty( list ) ) {
				break;
			}

			ClassificationInfo sameDepthInfo = list.get( 0 );
			resultMap.put( sameDepthInfo.getParentOid(), list );

			if ( sameDepthInfo.getParentOid().equals( classificationRootOid ) ) {
				break;
			}

			ClassificationCnd parentCnd = new ClassificationCnd();
			parentCnd.setOid( sameDepthInfo.getParentOid() );
			ClassificationInfo parentInfo = get( parentCnd );

			cnd.setParentOid( parentInfo.getParentOid() );

		}

		return resultMap;
	}

	/**
	 * 분류정보의 문서를 저장합니다.
	 *
	 * @param info
	 * @author 최원준
	 */
	private void insertDoc( ClassificationInfo info ) {

		if ( CollectionUtils.isEmpty( info.getDocList() ) ) {
			return;
		}

		info.getDocList().forEach( doc -> doc = docBLO.insert( doc, info.getOid(), ClassificationInfo.getObjectType() ) );

	}

	/**
	 * 분류정보의 문서를 수정합니다.
	 *
	 * @param info
	 * @author 최원준
	 */
	private void updateDoc( ClassificationInfo info ) {


		 // 문서 삭제 후 재등록합니다.
		DocCnd docCnd = new DocCnd();
		docCnd.setTargetOid( info.getOid() ).setTargetObject( ClassificationInfo.getObjectType() );
		List<DocInfo> existDocList = docBLO.viewList( docCnd );

		if ( CollectionUtils.isNotEmpty( existDocList ) ) {
			existDocList.forEach( doc -> docBLO.delete( doc.getOid() ) );
		}

		insertDoc( info );

	}

	/**
	 * 분류정보의 추가내용을 저장합니다.
	 *
	 * @param info
	 * @author 최원준
	 */
	private void insertContents( ClassificationInfo info ) {

		if ( CollectionUtils.isEmpty( info.getAddContentsList() ) ) {
			return;
		}

		info.getAddContentsList().forEach( contents -> contents.setTargetOid( info.getOid() )
															   .setTargetObject( info.getClassificationObject() ) );

		contentsBLO.insertBulk( info.getAddContentsList() );

	}

	/**
	 * 분류정보의 추가내용을 수정합니다.
	 *
	 * @param info
	 * @author 최원준
	 */
	private void updateContents( ClassificationInfo info ) {
		// 삭제 후 재등록합니다.
		if ( info.getClassificationObject().equals( AmConstants.OBJECT_AM_CLASSIFICATION_FUNCTION.getKey() )) {
			contentsBLO.deleteByTarget( info.getOid(), ClassificationInfo.getFunctionObjectType() );
		}
		else if( info.getClassificationObject().equals( AmConstants.OBJECT_AM_CLASSIFICATION_MARKET.getKey() ) ) {
			contentsBLO.deleteByTarget( info.getOid(), ClassificationInfo.getMarketObjectType());
		}
		else if( info.getClassificationObject().equals( AmConstants.OBJECT_AM_CLASSIFICATION_APPLICATION.getKey() ) ) {
			contentsBLO.deleteByTarget( info.getOid(), ClassificationInfo.getApplicationObjectType());
		}
		else if( info.getClassificationObject().equals( AmConstants.OBJECT_AM_CLASSIFICATION_PRODUCT.getKey() ) ) {
			contentsBLO.deleteByTarget( info.getOid(), ClassificationInfo.getProductObjectType());
		}
		else {
			contentsBLO.deleteByTarget( info.getOid(), ClassificationInfo.getObjectType() );
		}

		insertContents( info );

	}

	/**
	 * 분류정보에 속한 제품정보를 저장합니다.
	 *
	 * @param info
	 * @author 최원준
	 */
	private void insertProductRel( ClassificationInfo info ) {

		if ( CollectionUtils.isEmpty( info.getProductRelList() ) ) {
			return;
		}

		info.getProductRelList().forEach( productRel -> {
			if ( StringUtils.isEmpty( productRel.getTargetOid() ) ) {
				productRel.setTargetOid( info.getOid() );
			}

			if ( StringUtils.isEmpty( productRel.getTargetObject() ) ) {
				productRel.setTargetObject( ClassificationInfo.getObjectType() );
			}
		});

		productRelBLO.insertBulk( info.getProductRelList() );
	}

	/**
	 * 분류정보의 관련 제품을 수정합니다.
	 *
	 * @param info
	 * @author 황지영
	 */
	private void updateProductRel( ClassificationInfo info ) {
		//제품 관계를 삭제 후 재등록합니다.
		if ( CollectionUtils.isEmpty( info.getProductRelList() ) ) {
			return;
		}

		productBLO.deleteRelOnlyByTarget( info.getOid() , info.getClassificationObject() );
		insertProductRel( info );

	}

	/**
	 * 분류정보의 문서를 삭제합니다.
	 *
	 * @param cnd
	 * @author 최원준
	 */
	private void deleteDoc( ClassificationCnd cnd ) {

		DocCnd docCnd = new DocCnd();
		docCnd.setTargetOid( cnd.getOid() ).setTargetObject( ClassificationInfo.getObjectType() );

		if ( cnd.isDeleteRelInfo() ) {

			List<DocInfo> relDocList = docBLO.viewList( docCnd );
			// 문서의 관계정보만 삭제합니다.
			if ( CollectionUtils.isNotEmpty( relDocList ) ) {
				relDocList.forEach( relDoc -> docBLO.deleteRelOnly( relDoc.getOid(), cnd.getOid(), ClassificationInfo.getObjectType() ) );
			}

		} else {
			docRelBLO.delete( docCnd );
		}

	}

	/**
	 * 소재정보를 채워줍니다.
	 *
	 * @param info
	 * @author 최원준
	 */
	private void fillMaterial( ClassificationInfo info ) {

		if ( StringUtils.isEmpty( info.getPartOid() ) ) {
			return;
		}

		MaterialCnd materialCnd = new MaterialCnd();
		materialCnd.setOid( info.getPartOid() );
		materialCnd.setLang( info.getLang() );
		MaterialInfo materialInfo = materialBLO.get( materialCnd );

		if ( materialInfo == null || StringUtils.isEmpty( materialInfo.getOid() ) ) {
			return;
		}

		info.setMaterialInfo( materialInfo );

	}

	/**
	 * 분류정보 리스트에 소재정보를 채워줍니다.
	 *
	 * @param list
	 * @author 최원준
	 */
	private void fillMaterial( List<ClassificationInfo> list, ClassificationCnd cnd ) {

		if ( !cnd.isFillMaterial() ) {
			return;
		}

		List<String> partOidList = list.stream().map( ClassificationInfo::getPartOid ).collect( Collectors.toList() );
		if ( CollectionUtils.isEmpty( partOidList ) ) {
			return;
		}

		MaterialCnd materialCnd = new MaterialCnd();
		materialCnd.setOidList( partOidList );
		materialCnd.setLang( cnd.getLang() );

		List<MaterialInfo> materialList = materialBLO.listAll( materialCnd );
		if ( CollectionUtils.isEmpty( materialList ) ) {
			return;
		}

		list.forEach( classification -> materialList.stream()
												.filter( material -> classification.getPartOid().equals( material.getOid() ) )
												.forEach( material -> classification.setMaterialInfo( material ) ) );

	}

	/**
	 * 분류정보의 문서를 채워줍니다.
	 *
	 * @param info
	 * @author 최원준
	 */
	private void fillDoc( ClassificationInfo info, ClassificationCnd cnd ) {
			
			if( !cnd.isFillDoc() ){
				return;
			}
			
			DocCnd docCnd = new DocCnd();
			docCnd.setFillFile( true );
		
			docCnd.setTargetOid( info.getOid() ).setTargetObject( ClassificationInfo.getObjectType() );
			List<DocInfo> docList = docBLO.viewList( docCnd );

			if ( CollectionUtils.isEmpty( docList ) ) {
					return;
			}

			info.setDocList( docList );

	}

	/**
	 * 분류정보의 추가정보를 채워줍니다.
	 *
	 * @param info
	 * @author 최원준
	 */
	private void fillContents( ClassificationInfo info ) {

		fillClassificationObject( info );
		ContentsCnd cnd = new ContentsCnd();
		cnd.setTargetOid( info.getOid() );
		cnd.setLang( info.getLang() );
		
		List<ContentsInfo> contentsList = contentsBLO.list( cnd );
		if ( CollectionUtils.isEmpty( contentsList ) ) {
			return;
		}

		info.setAddContentsList( contentsList );
	}

	/**
	* 분류정보의 RootOid를 가져옵니다.
	*
	* @param info
	* @return String
	* @author 최원준
	*/
	private String getClassificationRootOid( ClassificationInfo info ) {

		String rootOid = "";
		switch ( info.getCategoryType() ) {
			case ClassificationInfo.AM_CLASSIFICATION_TYPE_PRODUCT : {
				rootOid = AmConstants.ROOT_TREE_OID_PRODUCT;
				break;
			}
			case ClassificationInfo.AM_CLASSIFICATION_TYPE_APPLICATION : {
				rootOid = AmConstants.ROOT_TREE_OID_APPLICATION;
				break;
			}
			case ClassificationInfo.AM_CLASSIFICATION_TYPE_MARKET : {
				rootOid = AmConstants.ROOT_TREE_OID_MARKET;
				break;
			}
			case ClassificationInfo.AM_CLASSIFICATION_TYPE_FUNCTION : {
				rootOid = AmConstants.ROOT_TREE_OID_FUNCTION;
				break;
			}
			default : {
				rootOid = SystemConstants.ROOT_TREE_OID;
				break;
			}
		}

		return rootOid;

	}
	
	/**
	* 분류정보에 속한 제품정보를 채워줍니다.
	*
	* @param info
	* @param cnd
	* @author 최원준
	*/
	private void fillProduct( ClassificationInfo info, ClassificationCnd cnd ) {
			
			ProductCnd productCnd = new ProductCnd();
			productCnd.setTargetOid( info.getOid() );
			productCnd.setFillMaterial( true );
			
			List<ProductInfo> productList = productBLO.viewListAll( productCnd );
			
			if( CollectionUtils.isEmpty( productList ) ){
					return;
			}
			
			info.setProductList( productList );
			
			if ( cnd.isGroupByMaterial() ) {
					Map<String, List<ProductInfo>> groupedMap = productList.stream()
																			.collect( Collectors.groupingBy( ProductInfo::getMaterialOid, Collectors.toList() ) );
					info.setGroupByMaterialProductMap( groupedMap );
			}
			
			if ( cnd.isFillRelateMaterial() ) {
					List<String> materialOidList = productList.stream().map( ProductInfo::getMaterialOid ).collect( Collectors.toList() );
					materialOidList = materialOidList.stream().distinct().collect( Collectors.toList());
					
					MaterialCnd materialCnd = new MaterialCnd();
					materialCnd.setOidList( materialOidList );
					materialCnd.setLang( cnd.getLang() );
					
					List<MaterialInfo> materialList = materialBLO.listAll( materialCnd );
					if ( CollectionUtils.isNotEmpty( materialList ) ) {
							info.setRelateMaterialList( materialList );
					}
			}

	}

	/**
	* 제품분류 리스트에 제품정보를 채워줍니다.
	*
	* @param list
	* @param cnd
	* @author 최원준
	*/
	private void fillProduct( List<ClassificationInfo> list, ClassificationCnd cnd ) {

		if ( !cnd.isFillProduct() || CollectionUtils.isEmpty( list ) ) {
			return;
		}

		list.forEach( classification -> {
			ProductCnd productCnd = new ProductCnd();
			productCnd.setTargetOid( classification.getOid() );
			List<ProductRelInfo> productRelList = productRelBLO.listAll( productCnd );

			if ( CollectionUtils.isEmpty( productRelList ) ) {
				return;
			}

			List<String> productOidList = productRelList.stream().map( ProductRelInfo::getProductOid ).collect( Collectors.toList() );

			productCnd = new ProductCnd();
			productCnd.setOidList( productOidList );
			productCnd.setLang( cnd.getLang() );
			List<ProductInfo> productList = productBLO.listAll( productCnd );

			if ( CollectionUtils.isEmpty( productList ) ) {
				return;
			}

			classification.setProductList( productList );
			List<String> materialOidList = productList.stream().map( ProductInfo::getMaterialOid ).distinct().collect( Collectors.toList() );
			MaterialCnd materialCnd = new MaterialCnd();
			materialCnd.setOidList( materialOidList );
			materialCnd.setLang( cnd.getLang() );
			classification.setRelateMaterialList( materialBLO.listAll( materialCnd ) );

		});

	}

	/**
	* 제품 분류의 자식 분류정보 리스트를 채워줍니다.
	*
	* @param info
	* @param cnd
	* @author 최원준
	*/
	private void fillChildClassification( ClassificationInfo info, ClassificationCnd cnd ) {

		if ( !cnd.isFillChildClassification() ) {
			return;
		}

		ClassificationCnd childCnd = new ClassificationCnd();
		childCnd.setCategoryType( cnd.getCategoryType() );
		childCnd.setParentOid( info.getOid() );
		childCnd.setPartOid( info.getPartOid() );
		childCnd.setLang( cnd.getLang() );

		List<ClassificationInfo> childClassificationList = listAll( childCnd );

		if ( CollectionUtils.isEmpty( childClassificationList ) ) {
			return;
		}

		info.setChildClassificationList( childClassificationList );

	}

	/**
	* CategoryType으로 분류정보의 Object정보를 가져옵니다.
	*
	* @param info
	* @return String
	* @author 최원준
	*/
	private void fillClassificationObject( ClassificationInfo info ) {
		if ( StringUtils.isEmpty( info.getCategoryType() ) ) {
			info.setClassificationObject( AmConstants.OBJECT_AM_TYPE_CLASSIFICATION.getKey() );
			return;
		}

		String classificationObject = "";
		switch ( info.getCategoryType() ) {
			case ClassificationInfo.AM_CLASSIFICATION_TYPE_PRODUCT: {
				classificationObject = AmConstants.OBJECT_AM_CLASSIFICATION_PRODUCT.getKey();
				break;
			}
			case ClassificationInfo.AM_CLASSIFICATION_TYPE_APPLICATION : {
				classificationObject = AmConstants.OBJECT_AM_CLASSIFICATION_APPLICATION.getKey();
				break;
			}
			case ClassificationInfo.AM_CLASSIFICATION_TYPE_MARKET : {
				classificationObject = AmConstants.OBJECT_AM_CLASSIFICATION_MARKET.getKey();
				break;
			}
			case ClassificationInfo.AM_CLASSIFICATION_TYPE_FUNCTION : {
				classificationObject = AmConstants.OBJECT_AM_CLASSIFICATION_FUNCTION.getKey();
				break;
			}
		}

		info.setClassificationObject( classificationObject );
	}

	/**
	* 분류정보에 제품 수를 채워줍니다.
	*
	* @param list
	* @param cnd
	* @author 최원준
	*/
	private void fillProductCnt( List<ClassificationInfo> list, ClassificationCnd cnd ) {

		if ( !cnd.isFillProductCnt() ) {
			return;
		}

		List<String> classificationOidList = list.stream().map( ClassificationInfo::getOid ).collect( Collectors.toList() );
		ProductCnd productCnd = new ProductCnd();
		productCnd.setTargetOidList( classificationOidList );
		productCnd.setLang( cnd.getLang() );
		
		List<ProductInfo> productList = productBLO.viewListAll( productCnd );

		if ( CollectionUtils.isEmpty( productList ) ) {
			return;
		}

		Map<String, List<ProductInfo>> groupedMap = productList.stream().collect( Collectors.groupingBy( ProductInfo::getTargetOid, Collectors.toList() ) );

		list.forEach( classification -> classification.setProductCnt( groupedMap.get( classification.getOid() ) != null ? groupedMap.get( classification.getOid() ).size() : 0 ) );

	}

	/**
	 * parentOid를 이용하여 parentName을 채워줍니다
	 *
	 * @param info
	 * @author 황지영
	 */
	private void fillParentName ( ClassificationInfo info ){
		if ( StringUtils.isEmpty( info.getOid() ) ){
			return;
		}
		CategoryCnd cnd = new CategoryCnd();
		
		cnd.setOid( info.getParentOid() );
		cnd.setLang( info.getLang() );
		
		CategoryInfo categoryInfo = super.get(cnd);
		info.setParentName( categoryInfo.getName() );
	};

}