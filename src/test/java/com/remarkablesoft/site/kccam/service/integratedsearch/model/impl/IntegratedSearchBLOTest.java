package com.remarkablesoft.site.kccam.service.integratedsearch.model.impl;
import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.integratedsearch.vo.IntegratedSearchCnd;
import com.remarkablesoft.site.kccam.service.integratedsearch.vo.IntegratedSearchInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * @주시스템		    :	kccam
 * @서브 시스템		:   integratedSearch
 * @프로그램 ID		:   IntegratedSearchBLOTest.java
 * @프로그램 개요	    :   통합검색 BLO 테스트
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-04-11 : 최원준 - 신규생성
 * ============================================================================
 */
public class IntegratedSearchBLOTest extends BaseModelTest {
	
	@Autowired
	protected IntegratedSearchBLO integratedSearchBLO;
	
	@Test
	public void productList_테스트() {
		IntegratedSearchCnd cnd = new IntegratedSearchCnd();
		PageList<IntegratedSearchInfo> list = integratedSearchBLO.productList( cnd );
		list.forEach( info -> System.out.println( info.toString() ) );
	}
	
	@Test
	public void applicationList_테스트() {
		
	}
	
	@Test
	public void marketList_테스트() {
		
	}
	
	@Test
	public void functionList_테스트() {
		
	}

	@Test
	public void test() {
		String text = null;
		String[] res = text.split( "," );
		Arrays.asList( res );
	}
	
	
}