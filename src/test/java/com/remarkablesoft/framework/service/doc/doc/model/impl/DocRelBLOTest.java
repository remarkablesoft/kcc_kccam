package com.remarkablesoft.framework.service.doc.doc.model.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.board.posting.vo.PostingInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;

import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.doc.doc.vo.DocRelInfo;


/**
 * 	                                                           
 * 		@주시스템		:	framework-web
 * 		@서브 시스템		:	doc - docRel
 * 		@프로그램 ID		:	DocRelBLOTest
 * 		@프로그램 개요	:	문서 관계정보 BLO Test
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      1.1 2021. 03. 07. : 테스트 코드 작성
 *      ============================================================================
 */

public class DocRelBLOTest extends BaseModelTest {

    @Autowired
    protected DocRelBLO docRelBLO;

    @Test
    public void insert_테스트() {

    	DocRelInfo info = SystemFactory.getDocRelInfo();
    	info.setDocOid( OIDGenerator.generateOID() );
    	info.setTargetOid( OIDGenerator.generateOID() );
    	info.setTargetObject( PostingInfo.getObjectType() );
    	info.setLastOpenDate( LocalDateTime.now() );
 
    	info = docRelBLO.insert( info );
    	
    	System.out.println( info.toString() );
    }

    @Test
    public void update_테스트() {

    	DocRelInfo info = new DocRelInfo();
    	info.setDocOid( "1SR2vOKb000" );
    	info.setTargetOid( "1SR2pGgW0" );
    	info.setTargetObject( "TEST" );
    	
    	info = docRelBLO.update( info );
    	
    	System.out.println( info.toString() );
    }

    @Test
    public void deleteCnd_테스트() {
    	
    	DocCnd cnd = new DocCnd();
    	cnd.setDocOid( "1SRKYVBC000" );
    	cnd.setTargetOid( "1SRKYVBC001" );
    	int delResult = docRelBLO.delete( cnd );
    	
    	String result = ( 0 < delResult ) ? "삭제완료" : "삭제실패";
    	
    	System.out.println( result );
    }

    @Test
    public void get_테스트() {
    	DocCnd cnd = new DocCnd();
    	cnd.setDocOid( "1SRKYVBC000" );
    	cnd.setTargetOid( "1SRKYVBC001" );
    	
    	DocRelInfo info = docRelBLO.get( cnd );
    	System.out.println( info.toString() );
    }

    @Test
    public void listAll_테스트() {

		DocCnd cnd =  new DocCnd();
		cnd.setTargetObject( MaterialInfo.getObjectType() );
		cnd.setTargetOid( "1SWKGVwR008" );
		List<DocRelInfo> list =  docRelBLO.listAll( cnd );

		list.forEach( System.out::println ); 
    }

    @Test
    public void exist_테스트() {
    	String result = ( docRelBLO.exist( "1SRKYVBC000" ) ) ? "있음" : "없음";
    	System.out.println( result );
    }

}
