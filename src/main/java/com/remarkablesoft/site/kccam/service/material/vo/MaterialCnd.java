package com.remarkablesoft.site.kccam.service.material.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.remarkablesoft.framework.model.vo.SearchCnd;
import com.remarkablesoft.framework.service.SystemConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @주시스템		    :	kccam
 * @서브 시스템		:   material
 * @프로그램 ID		:   MaterialCnd.java
 * @프로그램 개요	    :   소재정보 검색 객체
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-03-16 : 최원준 - 신규생성
 * 1.1 2021-05-25 : 김웅기 - PartCnd를 상속받지 않도록 변경
 * ============================================================================
 */
@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class MaterialCnd extends SearchCnd {
    
    private static final long serialVersionUID = 3902258155256448191L;
    
    private String oid ;										// OID
	private String systemOid;									// SYSTEM OID
	private String lang = SystemConstants.LANG_KOR.getKey();	// 다국어
	private String name;										// 소재 구분명
	private String className;									// 소재 구분 부제
	private String inputUser;									// 등록자
	private LocalDateTime inputDate;							// 등록일

	// KCC AM 추가 조건
	private List<String> oidList;								// OID List
	
    private String categoryType = "";							// 분류 타입
    private boolean isFillProductCnt = false;					// 카운트 채우기 여부	
    private boolean isFillLangList = false;						// 다국어 채우기 여부
	
}
