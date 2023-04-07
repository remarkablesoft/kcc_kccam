package com.remarkablesoft.framework.service.doc.doc.model.impl;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;
import com.remarkablesoft.framework.service.doc.doc.vo.DocVersionInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 	                                                           
 * 		@주시스템		:	framework-web
 * 		@서브 시스템		:	doc - doc
 * 		@프로그램 ID		:	DocVersionBLOTest
 * 		@프로그램 개요	:	문서 버전 BLO Test
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      1.1 2021. 03. 05. : 안병현 - 테스트 코드 작성( 테스트 RUN X )
 *      ============================================================================
 */

public class DocVersionBLOTest extends BaseModelTest {

    @Autowired
    protected DocVersionBLO docVersionBLO;
    
    @Test
	public void insert_테스트() {
    	
    	DocVersionInfo info = SystemFactory.getDocVersionInfo();
    	info.setDocOid( OIDGenerator.generateOID() );
    	info.setDocNo( "14CXQW" );
    	info.setVersion( "asjeklf" );
    	info.setStatusTypeFlag( "Q" );
    	
    	info.setOutLinkUrl( "asdnvjklasdan" );
    	info.setDescr( "description" );
    	info.setInputUser( OIDGenerator.generateOID() );
 
    	info = docVersionBLO.insert( info );
    	
		System.out.println( info.toString() );
	
	}
	
	@Test
	public void update_테스트() {
 
    	DocVersionInfo info = SystemFactory.getDocVersionInfo();
    	
    	info.setOid( "1SRKeSHQ002" );
    	
    	info.setDocNo( "dnjswns22" );
    	info.setVersion( "1.3.0.22" );
    	info.setStatusTypeFlag( "U" );
    	info.setOutLinkUrl( "https://www.naver.com" );
    	info.setDescr( "asdjkvlzxcvmioa" );
    	
    	info.setModUser( OIDGenerator.generateOID() );
    	info = docVersionBLO.update( info );
		
		System.out.println( info.toString() );
	}
	
	@Test
	public void delete_테스트() {
    	DocCnd cnd = new DocCnd();
    	cnd.setOid( "1SRKeSHQ002" );
 		int result = docVersionBLO.delete( cnd );
		System.out.println( result > 0 ? "삭제성공" : "삭제실패" );
	}
	
	@Test
	public void get_테스트() {
 
    	DocCnd cnd = new DocCnd();
    	cnd.setOid( "1SRKeSHQ002" );
    	DocVersionInfo info = docVersionBLO.get( cnd );
    	
		System.out.println( info.toString() );
		
	}
	
	@Test
	public void list_테스트() {
 		DocCnd cnd = new DocCnd();
 		PageList<DocVersionInfo> list = docVersionBLO.list( cnd );
		list.forEach( System.out::println );
	}

	@Test
	public void listAll_테스트() {
		DocCnd cnd = new DocCnd();
		List<DocVersionInfo> list = docVersionBLO.listAll( cnd );
		list.forEach( System.out::println );
	}
	
}
