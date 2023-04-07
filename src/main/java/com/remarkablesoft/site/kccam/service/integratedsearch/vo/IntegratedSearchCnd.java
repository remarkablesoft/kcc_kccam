package com.remarkablesoft.site.kccam.service.integratedsearch.vo;
import com.remarkablesoft.framework.model.vo.SearchCnd;
import com.remarkablesoft.framework.service.SystemConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @주시스템		    :	kccam
 * @서브 시스템		:   integratedSearch
 * @프로그램 ID		:   IntegratedSearchCnd.java
 * @프로그램 개요	    :   통합검색 검색조건 객체
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-04-11 : 최원준 - 신규생성
 * ============================================================================
 */
@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class IntegratedSearchCnd extends SearchCnd {
	
	private static final long serialVersionUID = -923779549651522663L;
	
	private String searchText;				// 검색어
	private String productSearchText;		// 제품 검색어
	private String applicationSearchText;	// Application 검색어
	private String marketSearchText;		// Market 검색어
	private String functionSearchText;		// function 검색어
	
	private String productOid;				// 제품 OID
	private String lang = SystemConstants.LANG_KOR.getKey();	// 다국어
	
}
