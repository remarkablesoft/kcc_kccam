package com.remarkablesoft.site.kccam.service.lang.model.impl;

import java.util.List;

import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.site.kccam.service.lang.vo.LangCnd;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.lang.vo.LangItemInfo;


/**
 * 	                                                           
 * 		@주시스템		:	kccam 	                   
 * 		@서브 시스템		:	lang - langItem
 * 		@프로그램 ID		:	LangItemBLOTest
 * 		@프로그램 개요	:	다국어 상세정보 BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

public class LangItemBLOTest extends BaseModelTest {

    @Autowired
    protected LangItemBLO langItemBLO;

    @Test
    public void insert_테스트() {

    	LangItemInfo info = new LangItemInfo();
    	info.setLangOid( OIDGenerator.generateOID() );
    	info.setLangKey( "qwerty" );
    	info.setLangVal( "111" );
    	
    	info = langItemBLO.insert( info );
        System.out.println( info.toString() );
        
    }

    @Test
    public void update_테스트() {

    	LangItemInfo info = new LangItemInfo();
    	
    	info.setLangOid( "1SRF32oo000" );
    	info.setLangKey( "asdfa" );
    	
    	info.setLangVal( "24678" );
    	info = langItemBLO.update( info );
    	
        System.out.println( info.toString() );
    }

    @Test
    public void deleteCnd_테스트() {

        LangCnd cnd = new LangCnd();
        cnd.setLangOid( "1SRF32oo000" );
        cnd.setLangKey( "asdfa" );
        
        int result = langItemBLO.delete( cnd );
        System.out.println( result );
        
    }

    @Test
    public void get_테스트() {

        LangCnd cnd = new LangCnd();
        cnd.setLangOid( "1SRF32oo000" );
        cnd.setLangKey( "asdfa" );
        
        LangItemInfo info = langItemBLO.get( cnd );
        System.out.println( info.toString() );
        
    }

    @Test
    public void listAll_테스트() {

		LangCnd cnd =  new LangCnd();
		List<LangItemInfo> list =  langItemBLO.listAll( cnd );

		list.forEach( System.out::println ); 
    }

    @Test
    public void exist_테스트() {

        LangItemInfo info = new LangItemInfo();
        info.setLangOid( "1SRF32oo000" );
        info.setLangKey( "asdfa" );
        
        boolean result = langItemBLO.exist( info );
        System.out.println( result );
    }

}
