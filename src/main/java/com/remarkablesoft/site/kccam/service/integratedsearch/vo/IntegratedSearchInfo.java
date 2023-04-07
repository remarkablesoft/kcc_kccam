package com.remarkablesoft.site.kccam.service.integratedsearch.vo;


import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationInfo;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductRelInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @주시스템		    :	kccam
 * @서브 시스템		:   integratedSearch
 * @프로그램 ID		:   IntegratedSearchInfo.java
 * @프로그램 개요	    :   통합검색 정보
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-04-11 : 최원준 - 신규생성
 * ============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString( callSuper = true )
public class IntegratedSearchInfo extends Entity {
	
	
	private static final long serialVersionUID = 2767883532808084755L;
	
	private String oid;							// OID
	private String materialOid;					// 소재 OID
	private String categoryType;				// 카테고리 타입
	private String fullPathIndex;				// 분류정보 FullPathIndex
	
	private String fullPathName;				// FullPath명
	private String productOids;					// 다수 제품 OID 문자열
	private String productClassificationOids;	// 다수 제품 구분 OID
	private String applicationOids;				// 다수 application OID
	private String marketOids;					// 다수 마켓 OID
	
	private String functionOids;				// 다수 function OID
	private String detailApplicationOids;		// 하위 다수 Application 의 OID
	
	private String productName;					// 제품명
	private String materialName;				// 소재명
	private String applicationName;				// application 명
	private String marketName;					// 마켓명
	private String functionName;				// function 명
	private String materialNames;				// 소재명 리스트
	
	private List<MaterialInfo> materialList = new ArrayList<>();			// 소재 리스트
	private List<ClassificationInfo> productClassificationList = new ArrayList<>();	// 제품 구분 리스트
	private List<ClassificationInfo> applicationList = new ArrayList<>();	// application 리스트
	private List<ClassificationInfo> marketList = new ArrayList<>();		// market 리스트
	private List<ClassificationInfo> functionList = new ArrayList<>();		// function 리스트
	
	private List<ProductInfo> productList = new ArrayList<>();				// 제품 리스트
	private List<ProductRelInfo> productRelList = new ArrayList<>();		// 제품 관계정보 리스트
	private List<ClassificationInfo> detailClassificationList = new ArrayList<>();	// 세부 분류 리스트
	private List<String> materialOidList = new ArrayList<>();				// 소재 OID 리스트
	
	public void addProductClassificationList( ClassificationInfo info ) {
		this.productClassificationList.add( info );
	}
	
	public void addApplicationList( ClassificationInfo info ) {
		this.applicationList.add( info );
	}
	
	public void addMarketList( ClassificationInfo info ) {
		this.marketList.add( info );
	}
	
	public void addFunctionList( ClassificationInfo info ) {
		this.functionList.add( info );
	}
	
	public void addDetailClassification( ClassificationInfo info ) {
		this.detailClassificationList.add( info );
	}
	
	public void addProduct( ProductInfo info ) {
		this.productList.add( info );
	}
	
	public void addMaterial( MaterialInfo info ) {
		this.materialList.add( info );
	}
	
	public void addMaterialOid( String materialOid ) {
		this.materialOidList.add( materialOid );
	}
	
}
