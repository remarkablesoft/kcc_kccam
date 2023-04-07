package com.remarkablesoft.site.kccam.service.integratedsearch.model.impl;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.category.model.impl.CategoryDAO;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryCnd;
import com.remarkablesoft.site.kccam.service.classification.model.impl.ClassificationBLO;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationCnd;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationInfo;
import com.remarkablesoft.site.kccam.service.integratedsearch.vo.IntegratedSearchCnd;
import com.remarkablesoft.site.kccam.service.integratedsearch.vo.IntegratedSearchInfo;
import com.remarkablesoft.site.kccam.service.material.model.impl.MaterialBLO;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialCnd;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;
import com.remarkablesoft.site.kccam.service.product.model.impl.ProductBLO;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @주시스템		    :	kccam
 * @서브 시스템		:   integratedSearch
 * @프로그램 ID		:   integratedSearchBLO.java
 * @프로그램 개요	    :   통합검색 BLO
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-04-11 : 최원준 - 신규생성
 * ============================================================================
 */
@BLO
public class IntegratedSearchBLO {
	
	@Autowired
	protected IntegratedSearchDAO integratedSearchDAO;
	
	@Autowired
	protected ProductBLO productBLO;
	
	@Autowired
	protected ClassificationBLO classificationBLO;

	@Autowired
	protected MaterialBLO materialBLO;

	@Autowired
	protected CategoryDAO categoryDAO;
	
	/**
	* 통합검색 제품 리스트
	*
	* @param cnd
	* @return PageList<ProductInfo>
	* @author 최원준
	*/
	public PageList<IntegratedSearchInfo> productList( IntegratedSearchCnd cnd ) {
		
		cnd.setProductSearchText( cnd.getSearchText() );
		PageList<IntegratedSearchInfo> list = integratedSearchDAO.productList( cnd );
		if ( CollectionUtils.isEmpty( list ) ) {
			return null;
		}
		
		fillName( list , cnd.getLang() );
		
		return list;
	}
	
	
	/**
	* 통합검색 application 리스트
	*
	* @param cnd
	* @return PageList<IntegratedSearchInfo>
	* @author 최원준
	*/
	public PageList<IntegratedSearchInfo> applicationList( IntegratedSearchCnd cnd ) {
		
		cnd.setApplicationSearchText( cnd.getSearchText() );
		PageList<IntegratedSearchInfo> list = integratedSearchDAO.applicationList( cnd );
		if ( CollectionUtils.isEmpty( list ) ) {
			return null;
		}
		
		List<String> materialOidList = list.stream()
											   .map( IntegratedSearchInfo::getMaterialOid )
											   .distinct().collect( Collectors.toList() );
		
		fillFullPathName( list, cnd.getLang() );
		fillMaterial( list, materialOidList , cnd.getLang() );
		fillDetailClassification( list, cnd.getLang() );
		fillProduct( list, cnd.getLang() );
		
		return list;
	}
	
	/**
	* 통합검색 마켓 리스트
	*
	* @param cnd
	* @return PageList<IntegratedSearchInfo>
	* @author 최원준
	*/
	public PageList<IntegratedSearchInfo> marketList( IntegratedSearchCnd cnd ) {
		
		cnd.setMarketSearchText( cnd.getSearchText() );
		PageList<IntegratedSearchInfo> list = integratedSearchDAO.marketList( cnd );
		if ( CollectionUtils.isEmpty( list ) ) {
			return null;
		}
		
		fillProduct( list, cnd.getLang() );
		fillMaterialList( list, cnd.getLang() );
		
		return list;
	}
	
	/**
	* 통합검색 function 리스트
	*
	* @param cnd
	* @return PageList<IntegratedSearchInfo>
	* @author 최원준
	*/
	public PageList<IntegratedSearchInfo> functionList( IntegratedSearchCnd cnd ) {
		
		cnd.setFunctionSearchText( cnd.getSearchText() );
		PageList<IntegratedSearchInfo> list = integratedSearchDAO.functionList( cnd );
		if ( CollectionUtils.isEmpty( list ) ) {
			return null;
		}
		
		fillProduct( list, cnd.getLang() );
		
		List<String> materialOidList = list.stream()
				   .map( IntegratedSearchInfo::getMaterialOid )
				   .distinct().collect( Collectors.toList() );
		
		fillMaterial( list, materialOidList, cnd.getLang() );
		fillMaterialList( list, cnd.getLang() );
		
		return list;
	}
	
	/**
	* 분류정보 이름을 채워줍니다.
	*
	* @param list
	* @author 최원준
	*/
	private void fillName( PageList<IntegratedSearchInfo> list, String lang ) {
		
		List<String> classificationOidList = new ArrayList<>();
		List<String> materialOidList = new ArrayList<>();
		
		list.forEach( is -> {
			
			if ( !materialOidList.contains( is.getMaterialOid() ) ) {
				materialOidList.add( is.getMaterialOid() );
			}
			
			if ( StringUtils.hasText( is.getProductClassificationOids() ) ) {
				classificationOidList.addAll( Arrays.asList( is.getProductClassificationOids().split( "," ) ) );
			}
			
			if ( StringUtils.hasText( is.getApplicationOids() ) ) {
				classificationOidList.addAll( Arrays.asList( is.getApplicationOids().split( "," ) ) );	
			}
			
			if ( StringUtils.hasText( is.getMarketOids() ) ) {
				classificationOidList.addAll( Arrays.asList( is.getMarketOids().split( "," ) ) );	
			}
			
			if ( StringUtils.hasText( is.getFunctionOids() ) ) {
				classificationOidList.addAll( Arrays.asList( is.getFunctionOids().split( "," ) ) );	
			}
		});

		if ( CollectionUtils.isEmpty( classificationOidList ) ) {
			return;
		}
		
		List<String> distinctMaterialOidList = materialOidList.stream().distinct().collect( Collectors.toList() );
		List<String> distinctClassificationOidList = classificationOidList.stream().distinct().collect( Collectors.toList() );
		
		fillMaterial( list, distinctMaterialOidList, lang );
		fillEachClassification( list, distinctClassificationOidList, lang );
	}
	
	/**
	* 
	*
	* @param list	통합검색 리스트
	* @param distinctMaterialOidList	중복 제외 소재 OID 리스트
	* @author 최원준
	*/
	private void fillMaterial( List<IntegratedSearchInfo> list, List<String> distinctMaterialOidList, String lang ) {
		
		MaterialCnd materialCnd = new MaterialCnd();
		materialCnd.setOidList( distinctMaterialOidList );
		materialCnd.setLang( lang );
		List<MaterialInfo> materialList = materialBLO.listAll( materialCnd );
		
		if ( CollectionUtils.isEmpty( materialList ) ) {
			return;
		}
	
		list.forEach( is -> materialList.stream()
									.filter( material -> StringUtils.hasText( is.getMaterialOid() ) && is.getMaterialOid().equals( material.getOid() ) )
									.forEach( material -> is.setMaterialName( material.getName() ) )
		);
		
	}
	
	/**
	* 소재 리스트를 채워줍니다.
	*
	* @param list
	* @author 최원준
	*/
	private void fillMaterialList( List<IntegratedSearchInfo> list, String lang ) {
		
		List<MaterialInfo> materialList = materialBLO.listAll( new MaterialCnd().setLang( lang ) );
		
		if ( CollectionUtils.isEmpty( materialList ) ) {
			return;
		}
		
		list.forEach( is -> materialList.forEach( material -> {
			if ( is.getMaterialOidList().contains( material.getOid() ) ) {
				is.addMaterial( material );
			}
		} ) );
		
	}
	
	/**
	* 각 분류정보를 리스트에 알맞게 채워줍니다.
	*
	* @param list	통합검색 리스트
	* @param distinctClassificationOidList	중복 제외 분류정보 OID 리스트 
	* @author 최원준
	*/
	private void fillEachClassification( List<IntegratedSearchInfo> list, List<String> distinctClassificationOidList, String lang ) {
		
		ClassificationCnd classificationCnd = new ClassificationCnd();
		classificationCnd.setCategoryOidList( distinctClassificationOidList );
		classificationCnd.setLang( lang );
		
		List<ClassificationInfo> classificationList = classificationBLO.listAll( classificationCnd );
		
		if ( CollectionUtils.isEmpty( classificationList ) ) {
			return;
		}
		
		Map<String, List<ClassificationInfo>> groupedMap = classificationList.stream()
																   .collect( Collectors.groupingBy( ClassificationInfo::getCategoryType, Collectors.toList() ) );
		
		List<ClassificationInfo> productClassificationList = groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_PRODUCT );
		List<ClassificationInfo> applicationList = groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_APPLICATION );
		List<ClassificationInfo> marketList = groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_MARKET );
		List<ClassificationInfo> functionList = groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_FUNCTION );
		
		list.forEach( is -> {
			
			if ( CollectionUtils.isNotEmpty( productClassificationList ) ) {
				productClassificationList.forEach( cf -> {
					if ( StringUtils.hasText( is.getProductClassificationOids() ) && is.getProductClassificationOids().contains( cf.getOid() ) ) {
						is.addProductClassificationList( cf );
					}
				});
			}
			
			if ( CollectionUtils.isNotEmpty( applicationList ) ) {
				applicationList.forEach( cf -> {
					if ( StringUtils.hasText( is.getApplicationOids() ) && is.getApplicationOids().contains( cf.getOid() ) ) {
						is.addApplicationList( cf );
					}
				});
			}
			
			if ( CollectionUtils.isNotEmpty( marketList ) ) {
				marketList.forEach( cf -> {
					if ( StringUtils.hasText( is.getMarketOids() ) && is.getMarketOids().contains( cf.getOid() ) ) {
						is.addMarketList( cf );
					}
				});
			}
			
			if ( CollectionUtils.isNotEmpty( functionList ) ) {
				functionList.forEach( cf -> {
					if ( StringUtils.hasText( is.getFunctionOids() ) && is.getFunctionOids().contains( cf.getOid() ) ) {
						is.addFunctionList( cf );
					}
				});
			}
			
		});
		
	}

	/**
	* 통합검색 리스트에 카테고리 FullPath명을 채워줍니다.
	*
	* @param list 		통합검색 결과 리스트
	* @author 최원준
	*/
	private void fillFullPathName( List<IntegratedSearchInfo> list, String lang ) {
		
		list.forEach( is -> {
			CategoryCnd cnd = new CategoryCnd();
			cnd.setLang( lang );
			cnd.calculateFullPath( is.getFullPathIndex() );
			cnd.setCategoryType( is.getCategoryType() );
			is.setFullPathName( categoryDAO.getFullPathName( cnd ) );
		} );
		
	}
	
	/**
	* 통합검색에서 하위 분류정보를 채워줍니다.
	*
	* @param list
	* @author 최원준
	*/
	private void fillDetailClassification( List<IntegratedSearchInfo> list, String lang ) {
		
		List<String> detailClassificationOidList = new ArrayList<>();
		list.forEach( is -> {
			if ( StringUtils.hasText( is.getDetailApplicationOids() ) ) {
				detailClassificationOidList.addAll( Arrays.asList( is.getDetailApplicationOids().split( "," ) ) );
			}
		} );
		
		List<String> distinctDetailClassificationOidList = detailClassificationOidList.stream().distinct().collect( Collectors.toList() );
		ClassificationCnd classificationCnd = new ClassificationCnd();
		classificationCnd.setLang( lang );
		classificationCnd.setCategoryOidList( distinctDetailClassificationOidList );
		List<ClassificationInfo> detailClassificationList = classificationBLO.listAll( classificationCnd );
		
		if ( CollectionUtils.isEmpty( detailClassificationList ) ) {
			return;
		}
		
		list.forEach( is -> detailClassificationList.stream()
									.filter( dc -> is.getOid().equals( dc.getParentOid() ) )
									.forEach( is::addDetailClassification ) );
		
	}
	
	/**
	* 제품 정보를 체워줍니다.
	*
	* @param list	통합검색 결과 리스트
	* @author 최원준
	*/
	private void fillProduct( List<IntegratedSearchInfo> list, String lang ) {
		
		List<String> productOidList = new ArrayList<>();
		list.forEach( is -> {
			if ( StringUtils.hasText( is.getProductOids() ) ) {
				productOidList.addAll( Arrays.asList( is.getProductOids().split( "," ) ) );
			}
		});
		
		List<String> distinctProductOidList = productOidList.stream().distinct().collect( Collectors.toList() );
		
		ProductCnd productCnd = new ProductCnd();
		productCnd.setLang( lang );
		productCnd.setOidList( distinctProductOidList );
		List<ProductInfo> productList = productBLO.listAll( productCnd );
		
		if ( CollectionUtils.isEmpty( productList ) ) {
			return;
		}
		
		list.forEach( is -> productList.stream()
									.filter( product -> StringUtils.hasText( is.getProductOids() ) && is.getProductOids().contains( product.getOid() ) )
									.forEach( product -> {
										is.addProduct( product );
										is.addMaterialOid( product.getMaterialOid() );
									} ) 
		);
		
	}
	
}
