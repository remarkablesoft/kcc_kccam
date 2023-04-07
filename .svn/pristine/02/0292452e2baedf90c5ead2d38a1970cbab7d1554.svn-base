package com.remarkablesoft.site.kccam.service.classification.vo;

import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryInfo;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.site.kccam.service.AmConstants;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductRelInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @주시스템		    :	kccam
 * @서브 시스템		:   service - classification
 * @프로그램 ID		:   classificationInfo
 * @프로그램 개요	    :   제품구분/App/마켓 분류 정보 객체
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021. 03. 11. : 최원준 - 신규생성
 * ============================================================================
 */
@Getter
@Setter
@Accessors( chain = true)
@ToString
@NoArgsConstructor
public class ClassificationInfo extends CategoryInfo {
    
    private static final long serialVersionUID = 2125352576941622502L;
    
    public static final String AM_CLASSIFICATION_TYPE_PRODUCT = "AMCFPRDT";        // 제품구분
    public static final String AM_CLASSIFICATION_TYPE_APPLICATION = "AMCFAPPL";    // Application
    public static final String AM_CLASSIFICATION_TYPE_MARKET = "AMCFMRKT";         // Market
    public static final String AM_CLASSIFICATION_TYPE_FUNCTION = "AMCFFUNC";       // Function

    private String materialOid = super.getPartOid();    // 소재 OID
	private String classificationObject;				// 분류정보 Object(AmConstant의 OBJECT_AM_CLASSIFICATION)
	private int productCnt = 0;							// 제품 수
	
    private MaterialInfo materialInfo;                  // 소재 정보
    private List<DocInfo> docList = new ArrayList<>();                      // 관련문서, 참고자료 리스트
    private List<ContentsInfo> addContentsList = new ArrayList<>();         // 추가내용
    private List<ProductRelInfo> productRelList = new ArrayList<>();        // 제품 관계정보 리스트
    private List<ProductInfo> productList = new ArrayList<>();				// 제품 리스트
	
	private Map<String, List<ProductInfo>> groupByMaterialProductMap = new HashMap<>();	// 소재로 그룹화한 제품 리스트
	private List<ClassificationInfo> childClassificationList = new ArrayList<>();		// 자식 분류정보
	private List<MaterialInfo> relateMaterialList = new ArrayList<>();		// 관련 소재정보 리스트

    private String parentName;          //상위 카테고리 이름

    public static String getObjectType() {
        return AmConstants.OBJECT_AM_TYPE_CLASSIFICATION.getKey();
    }
    
    public static String getProductObjectType() {
    	return AmConstants.OBJECT_AM_CLASSIFICATION_PRODUCT.getKey();
	}
    
	public static String getApplicationObjectType() {
    	return AmConstants.OBJECT_AM_CLASSIFICATION_APPLICATION.getKey();
	}
	
	public static String getMarketObjectType()	 {
    	return AmConstants.OBJECT_AM_CLASSIFICATION_MARKET.getKey();
	}
	
	public static String getFunctionObjectType() {
    	return AmConstants.OBJECT_AM_CLASSIFICATION_FUNCTION.getKey();
	}
	
    public void setMaterialOid( String materialOid ) {
        super.setPartOid( materialOid );
    }
    
    public void addProductRelInfo( ProductRelInfo relInfo ) {
        this.productRelList.add( relInfo );
    }
    
    public void addRelatedMaterialList( MaterialInfo materialInfo ) {
    	this.relateMaterialList.add( materialInfo );
	}
    
}
