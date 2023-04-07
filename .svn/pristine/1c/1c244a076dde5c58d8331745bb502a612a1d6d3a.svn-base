package com.remarkablesoft.site.kccam.service.product.vo;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryInfo;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.site.kccam.service.AmConstants;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationInfo;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetInfo;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @주시스템			:	framework-web
 * @서브 시스템		:	product - product
 * @프로그램 ID		:	ProductInfo
 * @프로그램 개요		:	Product 정보 객체
 *
 * @변경이력
 * ============================================================================
 * 1.0 2021. 02. 25. : 최원준 - 신규생성
 * ============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
public class ProductInfo extends Entity {

    private static final long serialVersionUID = -5147601006579007737L;

    private String oid;					            						// OID
    private String lang = SystemConstants.LANG_KOR.getKey();				// 언어설정
    private String productCode;					    						// 제품코드
	private String stdProductCode;											// 기준 제품코드
    private String name;					        						// 제품명
    private String materialOid;					    						// 소재구분 OID(EMC, Ceramic Substrates 등)
    private String materialName;											// 소재이름

    private String releaseYn;					    						// 노출여부
    private String mainFuncOid;					    						// Main Function OID
    private String descr;                           						// 설명
    private String inputUser;					    						// 등록자
    private LocalDateTime inputDate = LocalDateTime.now(); 					// 등록일시

    private String modUser;					        						// 수정자
    private LocalDateTime modDate;											// 수정일시

    private String customField1;											// 커스텀 필드1
    private String customField2;											// 커스텀 필드2
    private String customField3;											// 커스텀 필드3
    private String customField4;											// 커스텀 필드4
    private String customField5;											// 커스텀 필드5

	private String targetOid;												// REL TargetOid
	private String targetObject;											// REL TargetObject
	private int productCnt = 0;												// 제품 카운트

    private List<ProductRelInfo> productRelList = new ArrayList<>();    	// 제품 관계정보 리스트
    private List<DocInfo> docList = new ArrayList<>();                  	// 참고자료, 관련문서 리스트
    private List<ContentsInfo> addContentsList = new ArrayList<>();     	// 추가내용 리스트
    private MaterialInfo materialInfo;              						// 소재정보
	private DatasheetInfo datasheetInfo;									// 데이터시트 정보

    private ClassificationInfo mainFuncInfo;        						// Main Function Info
    private List<ClassificationInfo> functionList = new ArrayList<>();      // function 리스트
    private ClassificationInfo productClassificationInfo;       			// 제품구분
    private List<ClassificationInfo> applicationList = new ArrayList<>();   // Application List
    private List<ClassificationInfo> marketList = new ArrayList<>();        // Market List
    

    private List<String> oidList = new ArrayList<>();
    private List<BranchInfo> branchList = new ArrayList<>();                // 지점 리스트
    private List<UserInfo> branchManagerList = new ArrayList<>();           // 지점 매니저 리스트
    protected List<ProductInfo> langProductList = new ArrayList<>(); 		// 제품 다국어 리스트
    
    private List<ContentsInfo> langAddContentsList = new ArrayList<>();                 // 다국어 별 추가 컨텐츠 내용

    public static String getObjectType() {
        return AmConstants.OBJECT_AM_TYPE_PRODUCT.getKey();
    }

    public void addProductRelInfo( ProductRelInfo relInfo ) {
        this.getProductRelList().add( relInfo );
    }

}
