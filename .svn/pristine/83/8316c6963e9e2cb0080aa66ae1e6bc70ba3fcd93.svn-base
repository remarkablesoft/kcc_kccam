package com.remarkablesoft.site.kccam.service.onetoone.config.model.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.onetoone.config.vo.OneToOneConfigInfo;
import com.remarkablesoft.site.kccam.service.onetoone.config.vo.OneToOneConfigCnd;
import com.remarkablesoft.framework.util.StringUtils;



/**                                                            
 * 	                                                           
 * 		@주시스템		:	kccam
 * 		@서브 시스템		:	onetoone - config
 * 		@프로그램 ID		:	OneToOneConfigBLOTest
 * 		@프로그램 개요	:	1대1 문의 설정 BLO Test
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

public class OneToOneConfigBLOTest extends BaseModelTest {

    @Autowired
    protected OneToOneConfigBLO oneToOneConfigBLO;

    @Test
    public void insert_테스트() {

        OneToOneConfigInfo info = new OneToOneConfigInfo();

        info.setConfigType( OneToOneConfigInfo.CONFIG_TYPE_QUESTION );
        info.setConfigQuestion( "부모 질문1" );
        info.setOid( OIDGenerator.generateOID() );
        info.setLang( "KO" );

        info.addDetailConfigInfo( makeDetailQuestionList( 1) );
        info.addDetailConfigInfo( makeDetailQuestionList( 2) );
        info.addDetailConfigInfo( makeDetailQuestionList( 3 ) );

        info.addEmailConfigInfo( makeReceiverMailList( 1 ));
        info.addEmailConfigInfo( makeReceiverMailList( 2 ));
        info.addEmailConfigInfo( makeReceiverMailList( 3 ));

//        info = oneToOneConfigBLO.insert( info );

        System.out.println( info.toString() );
    }

    private OneToOneConfigInfo makeDetailQuestionList( int num ) {

        OneToOneConfigInfo detail = new OneToOneConfigInfo();
        detail.setConfigType( OneToOneConfigInfo.CONFIG_TYPE_DETAIL_QUESTION);
        detail.setLang( "KO" );
        detail.setConfigQuestion( " 세부 질문" + num);
        detail.setConfigAnswer(  "세부 답변" + num);
        return detail;
    }
    private OneToOneConfigInfo makeReceiverMailList( int num ) {
        OneToOneConfigInfo mail = new OneToOneConfigInfo();
        mail.setLang( "KO" );
        mail.setConfigType( OneToOneConfigInfo.CONFIG_TYPE_RECEIVER_EMAIL );
        mail.setConfigReceiverEmail("tester" + num + "@remarkablesoft.com" );
        return mail;
    }

    @Test
	public void 상세문의_insert_테스트() {
    	OneToOneConfigInfo info = new OneToOneConfigInfo();
    	info.setConfigQuestion( "문의 제품 소재정보" );
    	info.setConfigAnswer( "ex) EMC" );
    	info.setConfigType( OneToOneConfigInfo.CONFIG_TYPE_DETAIL_QUESTION );
    	info.setParentOid( "1SUS2tcZ000" );
    	
    	info = oneToOneConfigBLO.insert( info );
		System.out.println( info.toString() );
	}
    
    @Test
    public void update_테스트() {

    	OneToOneConfigInfo info = new OneToOneConfigInfo();
    	info.setOid( "1SRFGLum001" );
    	info = oneToOneConfigBLO.update( info );
    	
        System.out.println( info.toString() );
    
    }

    @Test
    public void delete_테스트() {
        int result = oneToOneConfigBLO.delete( "1SbD1umA002" );
        System.out.println( result );
    
    }

    @Test
    public void get_테스트() {
        OneToOneConfigCnd cnd = new OneToOneConfigCnd();
        cnd.setOid( "1SY0QF14002" );
        
        OneToOneConfigInfo info = oneToOneConfigBLO.get( cnd );
        System.out.println( info.toString() );
        
    }

    @Test
    public void listAll_테스트() {

		OneToOneConfigCnd cnd =  new OneToOneConfigCnd();
		cnd.setLang( "KO" );
		cnd.setUseYn( "Y" );
		List<OneToOneConfigInfo> list =  oneToOneConfigBLO.listAll( cnd );

		list.forEach( System.out::println ); 
    }
    @Test
	public void deleteFlag_테스트() {
    		OneToOneConfigInfo info = new OneToOneConfigInfo();
    		info.setUseYn( "Y" );
    		info.setOid( "1SbELDGS004" );
    		int result = oneToOneConfigBLO.deleteFlagUpdate( info );
			System.out.println( result );
	}

}
