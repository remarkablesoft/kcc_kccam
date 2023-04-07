package com.remarkablesoft.site.kccam.common;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.part.model.impl.PartBLO;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartCnd;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartInfo;
import com.remarkablesoft.migration.kccam.service.MigrationUtil;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @주시스템		    :	kccam
 * @서브 시스템		:   common
 * @프로그램 ID		:   PageListConverterTest.java
 * @프로그램 개요	    :   페이지 리스트 변환 테스트
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-03-17 : 최원준 - 신규생성
 * ============================================================================
 */
public class ListConverterTest extends BaseModelTest {
	
	@Autowired
	protected PartBLO partBLO;
	
	@Test
	public void 변환_테스트() {
		
		PartCnd cnd = new PartCnd();
		PageList<PartInfo> partList = partBLO.list( cnd );
		
		PageList<MaterialInfo> list = ListConverter.convertPageList( partList, MaterialInfo[].class );
		
		list.forEach( info -> System.out.println( "oid : " + info.getOid() + ":::" +  info.toString() ) );
		System.out.println( list.getTotalCount() );
	}
	
	@Test
	public void 텍스트변환_테스트() {
		String text = "엔터입력**두번째줄|| 콤마입력";
		String result = MigrationUtil.parseText( text );
		System.out.println( result );
	}
	
	@Test
	public void 텍스트스플릿_테스트() {
		String text = "5900GUD\\5500GB\\5900GSP\\5900GAA\\5900GSC\\5300GH\\5200GW\\5200GWE\\5600GWH\\5900GWS\\5950GC\\5900GJ\\5900GE\\3097GX";
		String[] arr = text.split( "\\\\" );
		for( String item : arr ) {
			System.out.println( item );
		}
	}
}
