package com.remarkablesoft.site.kccam.web.api;

import java.util.HashMap;
import java.util.Map;

import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.util.CookieUtils;
import com.remarkablesoft.site.kccam.service.AmConstants;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.web.controller.BaseController;
import com.remarkablesoft.site.kccam.service.integratedsearch.model.IntegratedSearchService;
import com.remarkablesoft.site.kccam.service.integratedsearch.vo.IntegratedSearchCnd;
import com.remarkablesoft.site.kccam.service.integratedsearch.vo.IntegratedSearchInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @주시스템		    :	kccam
 * @서브 시스템		:   api
 * @프로그램 ID		:   IntegratedSearchController.java
 * @프로그램 개요	    :   통합검색 API 호출 컨트롤러
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-04-19 : 최원준 - 신규생성
 * ============================================================================
 */
@WEBLog
@RestController
@RequestMapping( "/kccam/api/integratedSearch" )
public class IntegratedSearchController extends BaseController {

	@Autowired
	protected IntegratedSearchService integratedSearchService;

	/**
	* 통합검색 제품 리스트를 가져옵니다.
	*
	* @param cnd
	* @return Map<String, Object>
	* @author 최원준
	*/
	@RequestMapping( value = "/integratedSearch_productList" )
	public ResponseEntity<Map<String, Object>> productList( HttpServletRequest request, @RequestBody IntegratedSearchCnd cnd ) {

		String cookieLang = "";
		
		cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );
		
		if ( StringUtils.isNotEmpty( cookieLang ) ) {
				cnd.setLang( cookieLang.toUpperCase() );
		};
  
		PageList<IntegratedSearchInfo> list = integratedSearchService.productList( cnd );
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put( "list", list );
		resultMap.put( "totalCount", CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );

		return ResponseEntity.ok( resultMap );

	}

	/**
	 * 통합검색 Application 리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return Map<String, Object>
	 * @author 최원준
	 */
	@RequestMapping( value = "/integratedSearch_applicationList" )
	public ResponseEntity<Map<String, Object>> applicationList( HttpServletRequest request, @RequestBody IntegratedSearchCnd cnd ) {
			
	    String cookieLang = "";
	
		cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );
	
		if ( StringUtils.isNotEmpty( cookieLang ) ) {
			cnd.setLang( cookieLang.toUpperCase() );
		};

		PageList<IntegratedSearchInfo> list = integratedSearchService.applicationList( cnd );
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put( "list", list );
		resultMap.put( "totalCount", CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );

		return ResponseEntity.ok( resultMap );
	}

	/**
	 * 통합검색 마켓 리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return Map<String, Object>
	 * @author 최원준
	 */
	@RequestMapping( value = "/integratedSearch_marketList" )
	public ResponseEntity<Map<String, Object>> marketList( HttpServletRequest request, @RequestBody IntegratedSearchCnd cnd ) {
		
		String cookieLang = "";
		
		cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );
		
		if ( StringUtils.isNotEmpty( cookieLang ) ) {
			cnd.setLang( cookieLang.toUpperCase() );
		};
		
	    PageList<IntegratedSearchInfo> list = integratedSearchService.marketList( cnd );
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put( "list", list );
		resultMap.put( "totalCount", CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );

		return ResponseEntity.ok( resultMap );
	}

	/**
	 * 통합검색 Function 리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return Map<String, Object>
	 * @author 최원준
	 */
	@RequestMapping( value = "/integratedSearch_functionList" )
	public ResponseEntity<Map<String, Object>> functionList( HttpServletRequest request, @RequestBody IntegratedSearchCnd cnd ) {
		
		String cookieLang = "";
		
		cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );
		
		if ( StringUtils.isNotEmpty( cookieLang ) ) {
			cnd.setLang( cookieLang.toUpperCase() );
		};
		
		PageList<IntegratedSearchInfo> list = integratedSearchService.functionList( cnd );
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put( "list", list );
		resultMap.put( "totalCount", CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );

		return ResponseEntity.ok( resultMap );
	}

}
