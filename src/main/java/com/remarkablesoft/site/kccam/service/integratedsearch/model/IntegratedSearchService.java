package com.remarkablesoft.site.kccam.service.integratedsearch.model;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.integratedsearch.vo.IntegratedSearchCnd;
import com.remarkablesoft.site.kccam.service.integratedsearch.vo.IntegratedSearchInfo;

/**
 * @주시스템		    :	kccam
 * @서브 시스템		:   integratedSearch
 * @프로그램 ID		:   IntegratedSearchService.java
 * @프로그램 개요	    :   통합검색 서비스
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-04-11 : 최원준 - 신규생성
 * ============================================================================
 */
public interface IntegratedSearchService {
	
	/**
	* 통합검색 제품 리스트
	*
	* @param cnd
	* @return PageList<IntegratedSearchInfo>
	* @author 최원준
	*/
	PageList<IntegratedSearchInfo> productList( IntegratedSearchCnd cnd );
	
	/**
	* 통합검색 application 리스트
	*
	* @param cnd
	* @return PageList<IntegratedSearchInfo>
	* @author 최원준
	*/
	PageList<IntegratedSearchInfo> applicationList( IntegratedSearchCnd cnd );
	
	/**
	* 통합검색 마켓 리스트
	*
	* @param cnd
	* @return PageList<IntegratedSearchInfo>
	* @author 최원준
	*/
	PageList<IntegratedSearchInfo> marketList( IntegratedSearchCnd cnd );
	
	/**
	* 통합검색 function 리스트
	*
	* @param cnd
	* @return PageList<IntegratedSearchInfo>
	* @author 최원준
	*/
	PageList<IntegratedSearchInfo> functionList( IntegratedSearchCnd cnd );
	
}
