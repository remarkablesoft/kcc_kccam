package com.remarkablesoft.framework.service.mgnt.code.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.service.SystemConstants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.mgnt.code.vo.CodeCnd;
import com.remarkablesoft.framework.service.mgnt.code.vo.CodeInfo;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	mgnt - Code
 * 		@프로그램 ID		:	CodeBLOTest
 * 		@프로그램 개요		:	Code BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 07. : 안병현 - 신규 생성( 테스트 RUN X )
 *      ============================================================================
 */

public class CodeBLOTest extends BaseModelTest{

	@Autowired
	CodeBLO codeBLO;
	
	/*
	 * 공통코드 INSERT 테스트
	 * */
	@Test
	public void insert_테스트() {
	
		CodeInfo info = SystemFactory.getCodeInfo();
		info.setCode( "TEST" );
		info.setCodeName( "테스트" );
		info.setCodeValue( "가나다" );
		info.setCodeLevel( 1 );
		
		info.setCodeType( "QWRWASDF" );
		info.setUseYn( SystemConstants.FLAG_YES );
		info.setDeleteYn( SystemConstants.FLAG_NO );
		info.setOrderNo( 5 );
		
		info.setCustomField1( "1" );
		info.setCustomField2( "2" );
		info.setCustomField3( "3" );
		info.setCustomField4( "4" );
		info.setCustomField5( "5" );
		
		int result = codeBLO.insert( info );
		System.out.println( result );
	}
	
	
	/*
	 * 공통코드 대량 INSERT 테스트
	 * */
	@Test
	public void insert_bulk_테스트() {
		
		//CodeList
		List<CodeInfo> list = new ArrayList<CodeInfo>();
		
		//Code Obj
		CodeInfo code = SystemFactory.getCodeInfo();
		code.setCode( "가" );
		code.setCodeName( "나" );
		code.setCodeValue( "다" );
		code.setCodeLevel( 1 );
		
		CodeInfo code1 = SystemFactory.getCodeInfo();
		code1.setCode( "A" );
		code1.setCodeName( "B" );
		code1.setCodeValue( "C" );
		code1.setCodeLevel( 2 );
		
		
		list.add( code );
		list.add( code1 );
		
		System.out.println( codeBLO.insertBulk( list ) ); 
		
	}
	
	
	/*
	 * 공통코드 SAVE 테스트
	 * */
	@Test
	public void save_테스트() {
		
		CodeInfo code = SystemFactory.getCodeInfo();
//		code.setOid( "1SRA3O7T000" );
		code.setCode( "savesave" );
		code.setCodeLevel( 5 );
		
		System.out.println( codeBLO.save( code ) );
		
	}
	
	/*
	 * 공통코드 update
	 * */
	@Test
	public void update_테스트() {
		
		CodeInfo code = SystemFactory.getCodeInfo();
		code.setOid( "1SR3duMx001" );
		code.setCodeLevel( 10 );
		code.setCodeValue( "아아아" );
		
		
		System.out.println( codeBLO.save( code ) );
		
	}
	
	/*
	 * 공통코드 대량 update
	 * */
	@Test
	public void update_bulk_테스트() {
		
		//CodeList
				List<CodeInfo> list = new ArrayList<CodeInfo>();
				
				//Code Obj
				CodeInfo code = SystemFactory.getCodeInfo();
				code.setOid( "1SRA3O7T000" );
				code.setCode( "Basic" );
				code.setCodeName( "사아" );
				code.setCodeValue( "다라" );
				code.setCodeLevel( 10 );
				
				CodeInfo code1 = SystemFactory.getCodeInfo();
				code1.setOid( "1SRA3ogF000" );
				code1.setCode( "Basic" );
				code1.setCodeName( "BB" );
				code1.setCodeValue( "CC" );
				code1.setCodeLevel( 7 );
				
				
				list.add( code );
				list.add( code1 );
				
				System.out.println( codeBLO.updateBulk( list ) ); 
		
	}
	
	/*
	 * 공통코드 리스트 조회
	 * */
	@Test
	public void list_테스트() {
		
		CodeCnd cnd = new CodeCnd();
		List<CodeInfo> list = codeBLO.list( cnd );
		list.forEach( s -> System.out.println( s ) );
		
	}
	
	
	/*
	 * 공통코드 get
	 * */
	@Test
	public void get_테스트() {
		
		CodeInfo info = codeBLO.get( "1SRA34Qb000" );
		
		System.out.println( info.toString() );
		
	}
	
	/*
	 * 공통코드 delete
	 * */
	@Test
	public void delete_테스트() {
		int result = codeBLO.delete( "1SR3duMx000" );
		System.out.println( result );
	}
	
	/*
	 * 공통코드 대량 삭제
	 * */
	@Test
	public void deleteByOids_테스트() {
		List<CodeInfo> list = new ArrayList<>();
		
		CodeInfo info = SystemFactory.getCodeInfo();
		info.setOid( "1SRA3O7T000"	);
		list.add( info );
		
		CodeInfo info2 = SystemFactory.getCodeInfo();
		info2.setOid( "1SR3duMx001"	);
		list.add( info2 );
		
		int result = codeBLO.deleteByOids( list );
		System.out.println( result );
	}
}

