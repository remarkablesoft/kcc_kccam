package com.remarkablesoft.site.kccam.service.classification.vo;

import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryCnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors( chain = true)
@ToString
@EqualsAndHashCode(callSuper = true)
public class ClassificationCnd extends CategoryCnd {
	
	private static final long serialVersionUID = -6531504000005157512L;
	
	private boolean isDeleteRelInfo = false;			// 관련데이터 삭제 여부
	private boolean isGroupByMaterial = false;			// 소재기준 그룹화 여부
	private boolean isFillChildClassification = false;	// 자식 분류정보 채움 여부
	private boolean isFillRelateMaterial = false;		// 관련 소재정보 채움 여부
	private boolean isFillProduct = false;				// 제품정보 채움 여부
	
	private boolean isFillMaterial = false;				// 소재정보 채움 여부
	private boolean isFillProductCnt = false;			// 제품 수 채움 여부
	private boolean isFillContents = false;				// 추가정보 채움 여부
	private boolean isFillDoc = false;                  // 관련 문서 채움 여부
	
	private String rootClassificationOid = "";			// 분류정보 Root OID
	private String productRelTargetObject = "";			// 제품 관계정보 타입

	private boolean isOrderByPartOID = false;			// part_oid 기준 정렬
	
	private String contentsTargetObject;                // langObj의 하위 contents의 타켓용
	
}
