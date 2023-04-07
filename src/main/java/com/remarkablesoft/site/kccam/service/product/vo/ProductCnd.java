package com.remarkablesoft.site.kccam.service.product.vo;

import com.remarkablesoft.framework.model.vo.SearchCnd;
import com.remarkablesoft.framework.service.SystemConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	product - product
 * 		@프로그램 ID		:	ProductCnd
 * 		@프로그램 개요		:	Product 검색 정보 객체
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class ProductCnd extends SearchCnd {

    private static final long serialVersionUID = -6301357670000448482L;

    private String oid;					        						// OID
    private String lang = SystemConstants.LANG_KOR.getKey();			// 언어설정
    private String productCode;											// 제품코드
	private String stdProductCode;										// 기준 제품코드
    private String name;					    						// 제품명
		
    private String partOid;					   							// 소재구분 OID(EMC, Ceramic Substrates 등)
    private String releaseYn;											// 노출여부
    private String materialOid;											// 소재구분 OID(EMC, Ceramic Substrates 등). 21.03.29 안병현 추가
    private String materialName;										// 소재구분 Name
    private String mainFuncOid;											// Main Function OID
		
    private String inputUser;											// 등록자
    private int orderNo = -1;                   						// 제품 추가정보 정렬순서
    private String object;                      						// 주체가 되는 정보의 객체
    private String targetOid;                   						// 타겟 정보 OID
    private String productOid;                  						// 제품 OID
    private String targetObject;                						// 타겟 정보 객체

	private String customField1;										// 커스텀 필드1
    private String customField2;										// 커스텀 필드2
    private String customField3;										// 커스텀 필드3
    private String customField4;										// 커스텀 필드4
    private String customField5;										// 커스텀 필드5

    private String searchText;                 							// 검색어
	private String inputDateFrom;										// 작성일 From
	private String inputDateTo;											// 작성일 To
	private String modDateFrom;											// 수정일 From
	private String modDateTo;											// 수정일 To

	private boolean isFillMaterial = false;								// 소재정보 채움 여부
	private boolean isFillDatasheet = false;							// 데이터시트 채움 여부
	private boolean isOidNotIn = false; 								// 조건절 서브쿼리 추가 여부 (viewListAll용)
    private boolean isLikeSearch = false;								// 검색 모드
	private boolean isFillLangList = false;								// 다국어 리스트 채우기 여부
	
	private String targetOidInOidNotIn;									// 서브쿼리 내 targetOid용
		
    private List<String> oidList = new ArrayList<>();
    private List<String> targetOidList = new ArrayList<>();
    private List<String> exceptOidList = new ArrayList<>();


    public void addOidList( String oid ) {
    	this.oidList.add ( oid );
    }
}
