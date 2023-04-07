package com.remarkablesoft.framework.service.mgnt.category.model.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryCnd;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryInfo;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	mgnt - Category
 * 		@프로그램 ID		:	CategoryBLOTest
 * 		@프로그램 개요		:	Category BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 07. : 안병현 - 신규 생성( 테스트 RUN X )
 *      ============================================================================
 */

public class CategoryBLOTest extends BaseModelTest{
	
	@Autowired
	CategoryBLO categoryBLO;
	
	@Test
	public void root카테고리_생성() {
		CategoryInfo info = SystemFactory.getCategoryInfo();
		info.setOid( SystemConstants.ROOT_TREE_OID );
		
		info.setPartOid( OIDGenerator.generateOID() );
		info.setName( "Root Category" );
		info.setInputUser( "admin" );
		info.setInputDate( LocalDateTime.now() );
		
		info.setDisplayYn( SystemConstants.FLAG_YES );
		
		info = categoryBLO.insert( info );
		
		System.out.println( info.toString() );
	}
	
	/*
	 * 카테코리 INSERT 테스트
	 * */
	@Test
	public void insert_테스트() {
		
		//Category 객체 생성
		CategoryInfo info = SystemFactory.getCategoryInfo();
		String parentOid = OIDGenerator.generateOID();
		info.setOid( parentOid );
		info.setParentOid( SystemConstants.ROOT_TREE_OID );
		info.setCategoryType( CategoryInfo.CATEGORY_TYPE_LECTURE );
		info.setName( "강의 테스트 0 " );
		info.setDescr( "테스트 카테고리" );
		info.setInputUser( "TedTest" );
		
		//하위 카테고리 객체 리스트 생성
		List<CategoryInfo> list = new ArrayList<CategoryInfo>();
		
		CategoryInfo subCategory = SystemFactory.getCategoryInfo();
		subCategory.setParentOid( parentOid );
		subCategory.setOid( OIDGenerator.generateOID() );
		subCategory.setName( "강의 테스트 0-1" );
		subCategory.setDescr( "테스트 카테고리의 하위 테스트 카테고리" );
		subCategory.setInputUser( "TedTest" );
		list.add( subCategory );
		
		CategoryInfo subCategory1 = SystemFactory.getCategoryInfo();
		subCategory1.setParentOid( parentOid );
		subCategory1.setOid( OIDGenerator.generateOID() );
		subCategory1.setName( "강의 테스트 0-2" );
		subCategory1.setDescr( "테스트 카테고리의 하위 테스트 카테고리의 하위 테스트 카테고리" );
		subCategory1.setInputUser( "TedTest" );
		list.add( subCategory1 );
		
		info.setChildCategoryList( list );
		
		info = categoryBLO.insert( info );
		
		System.out.println( "-----------------------------------------------" );
		System.out.println( "상위 카테고리 : " + info.toString() );
		info.getChildCategoryList().forEach( s -> System.out.println( "하위 카테고리 : " + s.toString() ) );
		System.out.println( "-----------------------------------------------" );
		
		
	}
	
	/*
	 * 카테코리 UPDATE 테스트
	 * */
	@Test
	public void update_테스트() {
		
		CategoryInfo info = SystemFactory.getCategoryInfo();
		info.setOid( "1SRA0xTT000" );
		info.setName( "카테고리 업데이트" );
		
		info = categoryBLO.update( info );
		
		System.out.println( "-----------------------------------------------" );
		System.out.println( "상위 카테고리 이름 : " + info.toString() );
		info.getChildCategoryList().forEach( s -> System.out.println( "하위 카테고리 : " + s.getName() ) );
		System.out.println( "-----------------------------------------------" );
		
	}
	
	/*
	 * 카테코리 GET 테스트
	 * */
	@Test
	public void get_테스트() {
		
		CategoryCnd cnd = new CategoryCnd();
		cnd.setOid( "1STDUER000H" );
		cnd.setFillFullPathName( true );
		CategoryInfo info = categoryBLO.get( cnd );

		System.out.println( "-----------------------------------------------" );
		System.out.println( "상위 카테고리 : " + info.toString() );
		info.getChildCategoryList().forEach( s -> System.out.println( "하위 카테고리 : " + s.getName() ) );
		System.out.println( "-----------------------------------------------" );
	}
	
	/*
	 * 카테코리를 리스트로 가져오는 테스트
	 * */
	@Test
	public void list_테스트() {
		CategoryCnd cnd = new CategoryCnd();
		PageList<CategoryInfo> list = categoryBLO.list( cnd );
		
		list.forEach( s -> System.out.println( s ) );
	}
	
	/*
	 * 카테고리를 모두 테스트
	 * */
	@Test
	public void listAll_테스트() {
		
		CategoryCnd cnd = new CategoryCnd();
		
		PageList<CategoryInfo> list = categoryBLO.list( cnd );
		
		list.forEach( s -> System.out.println( s ) );
		list.forEach( s -> System.out.println( s.getDepthVC() ) );
	}
	
	
	/*
	 * 해당 카테고리의 모든 하위 카테고리를 가져오는 테스트
	 * */
	@Test
	public void 하위_카테고리_검색_테스트() {
		
		CategoryCnd cnd = new CategoryCnd();
		cnd.setOid( SystemConstants.ROOT_TREE_OID );
		
		List<CategoryInfo> list = categoryBLO.listAllTreeChilds( cnd );
		
		list.forEach( s -> System.out.println( s ) );
		
	}
	
	/*
	 * 해당 카테고리의 모든 하위 카테고리를 하나로 묶어서 반환합니다.
	 * */
	@Test
	public void getFullPath_테스트() {
		
		CategoryCnd cnd = new CategoryCnd();
		cnd.setOid( "00000" );
		
		String categorys = categoryBLO.getFullPathName( cnd );
		System.out.println( categorys );
		
	}
	
	/*
	 * 카테고리 DELETE 테스트
	 * */
	@Test
	public void delete_테스트() {
		
		int result = categoryBLO.delete( "00000" );
		String resultStr = ( 0 <= result ) ? "삭제 성공" : "삭제 실패";
		System.out.println( resultStr );
	}
	
	/*
	 * 카테고리 리스트의 아이콘 파일을 채워주는 테스트
	 * */
	@Test
	public void 아이콘_파일_체우기_테스트() {
		
		//하위 카테고리 객체 리스트 생성
		List<CategoryInfo> list = new ArrayList<CategoryInfo>();
				
		CategoryInfo subCategory = SystemFactory.getCategoryInfo();
		subCategory.setParentOid( "00000" );
		subCategory.setOid( "000001" );
		subCategory.setName( "강의 테스트 0-1" );
		subCategory.setDescr( "테스트 카테고리의 하위 테스트 카테고리" );
		subCategory.setInputUser( "TedTest" );
		list.add( subCategory );
				
		CategoryInfo subCategory1 = new CategoryInfo();
		subCategory1.setParentOid( "000001" );
		subCategory1.setOid( "0000011" );
		subCategory1.setName( "강의 테스트 0-2" );
		subCategory1.setDescr( "테스트 카테고리의 하위 테스트 카테고리의 하위 테스트 카테고리" );
		subCategory1.setInputUser( "TedTest" );
		list.add( subCategory1 );
		
		categoryBLO.fillIconFile( list );
		
		list.forEach( s -> System.out.println( s ) );
	}
	
}
