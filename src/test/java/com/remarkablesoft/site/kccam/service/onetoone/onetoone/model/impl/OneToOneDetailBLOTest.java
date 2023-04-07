package com.remarkablesoft.site.kccam.service.onetoone.onetoone.model.impl;

import java.util.List;

import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneCnd;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneDetailInfo;


/**
 * 	                                                           
 * 		@주시스템		:	kccam
 * 		@서브 시스템		:	onetoone - onetooneDetail
 * 		@프로그램 ID		:	OneToOneDetailBLOTest
 * 		@프로그램 개요	:	1대1 문의 상세정보 BLO Test
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

public class OneToOneDetailBLOTest extends BaseModelTest {

    @Autowired
    protected OneToOneDetailBLO oneToOneDetailBLO;

    @Test
    public void insert_테스트() {

    	OneToOneDetailInfo info = new OneToOneDetailInfo();
    	info.setOid(OIDGenerator.generateOID());
    	info.setOtoOid(  "1SYCr2nY000");
    	info.setDetailType( "QWER" );
    	info.setDetailQuestion( "Question1" );
    	info.setDetailAnswer( "Answer1" );
    	
    	info = oneToOneDetailBLO.insert( info );
        System.out.println( info.toString() );
    
    }

    @Test
    public void update_테스트() {

    	OneToOneDetailInfo info = new OneToOneDetailInfo();
    	
    	info.setOid( "1SRFt4ek001" );
        info.setDetailType( "ASDF" );
        info.setDetailQuestion( "Question2" );
        info.setDetailAnswer( "Answer2" );
        
        info = oneToOneDetailBLO.update( info );
        System.out.println( info.toString() );
    
    }

    @Test
    public void delete_테스트() {

        OneToOneCnd cnd = new OneToOneCnd();
        cnd.setOid( "1SRFsPBH001" );
        int result = oneToOneDetailBLO.delete( cnd );
        System.out.println( result );
    
    }

    @Test
    public void get_테스트() {
        OneToOneCnd cnd = new OneToOneCnd();
        cnd.setOid( "1SRFsPBH001" );
        OneToOneDetailInfo info = oneToOneDetailBLO.get( cnd );
        System.out.println( info.toString() );
    }

    @Test
    public void listAll_테스트() {

		OneToOneCnd cnd =  new OneToOneCnd();
		List<OneToOneDetailInfo> list =  oneToOneDetailBLO.listAll( cnd );

		list.forEach( System.out::println ); 
    }


}
