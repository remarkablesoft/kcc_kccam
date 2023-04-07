package com.remarkablesoft.site.kccam.service.lang.model.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.lang.vo.LangInfo;
import com.remarkablesoft.site.kccam.service.lang.vo.LangCnd;
import com.remarkablesoft.framework.util.StringUtils;



/**                                                            
 * 	                                                           
 * 		@주시스템		:	kccam
 * 		@서브 시스템		:	lang - lang
 * 		@프로그램 ID		:	LangBLOTest
 * 		@프로그램 개요	:	다국어 BLO Test
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

public class LangBLOTest extends BaseModelTest {

    @Autowired
    protected LangBLO langBLO;

    @Test
    public void insert_테스트() {

    	LangInfo info = new LangInfo();
    	
    	info.setTargetOid( OIDGenerator.generateOID() );
    	info.setTargetType( "QWER" );
    	info.setLang( "KO" );
    	
    	info = langBLO.insert( info );
        System.out.println( info.toString() );
    
    }

    @Test
    public void delete_테스트() {

        int result = langBLO.delete( "1SREzJ82001" );
        System.out.println( result );
    }

    @Test
    public void get_테스트() {
        LangCnd cnd = new LangCnd();
        cnd.setOid( "1SREzJ82001" );
        LangInfo info = langBLO.get( cnd );
        System.out.println( info.toString() );
    }

    @Test
    public void listAll_테스트() {
		LangCnd cnd =  new LangCnd();
		List<LangInfo> list =  langBLO.listAll( cnd );

		list.forEach( System.out::println ); 
    }


}
