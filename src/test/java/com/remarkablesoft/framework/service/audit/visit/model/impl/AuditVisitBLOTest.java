package com.remarkablesoft.framework.service.audit.visit.model.impl;


import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitCnd;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuditVisitBLOTest extends BaseModelTest {

    @Autowired
    AuditVisitBLO auditVisitBLO;
    
    @Test
    public void insert_테스트() {
        
        AuditVisitInfo info = SystemFactory.getAuditVisitInfo();
        
        info.setVisitYear( "2021" );
        info.setVisitMonth( "07" );
        info.setVisitWeek( "4" );
        info.setVisitDay( "21" );
        info.setUserIp( "127.0.0.1" );
        
        info.setUserAgent( "KCC" );
        info.setInputDate( LocalDateTime.now() );
        info.setVisitURL( "http://localhost:3000/kccam/user/main/user_main" );
        
        info = auditVisitBLO.insert( info );
        System.out.println( info.toString() );
    }
    
    @Test
    public void insertBulk_테스트() {
        
        List<AuditVisitInfo> list = new ArrayList<>();
    
        AuditVisitInfo info = SystemFactory.getAuditVisitInfo();
    
        info.setOid( OIDGenerator.generateOID() );
        info.setVisitYear( "2021" );
        info.setVisitMonth( "11" );
        info.setVisitWeek( "1" );
        info.setVisitDay( "13" );
        info.setUserIp( "1.1.1.1" );
        list.add( info );
    
        info = SystemFactory.getAuditVisitInfo();
        info.setOid( OIDGenerator.generateOID() );
        info.setVisitYear( "2022" );
        info.setVisitMonth( "12" );
        info.setVisitWeek( "5" );
        info.setVisitDay( "14" );
        info.setUserIp( "2.2.2.2" );
        list.add( info );
    
        list = auditVisitBLO.insertBulk( list );
        list.forEach( item -> System.out.println( item.toString() ) );
        
    }
    
    @Test
    public void get_테스트() {
    
        AuditVisitCnd cnd = new AuditVisitCnd();
        cnd.setOid( "1SdnXXuE000" );
        AuditVisitInfo info = auditVisitBLO.get( cnd );
        
        System.out.println( info.toString() );
        
    }
    
    @Test
    public void listAll_테스트() {
        AuditVisitCnd cnd = new AuditVisitCnd();
        List<AuditVisitInfo> list = auditVisitBLO.listAll( cnd );
        list.forEach( item -> System.out.println( item.toString() ) );
    }
    
    @Test
    public void list_테스트() {
        AuditVisitCnd cnd = new AuditVisitCnd();
        cnd.setStartIndex( 1 );
        cnd.setPageSize( 10 );
        List<AuditVisitInfo> list = auditVisitBLO.list( cnd );
        list.forEach( item -> System.out.println( item.toString() ) );
    }
    
    @Test
    public void update_테스트() {
        AuditVisitInfo info = SystemFactory.getAuditVisitInfo();
        info.setOid( "1SR8ghFn000" );
        info.setUserAgent( "hcc" );
        info = auditVisitBLO.update( info );
    
        System.out.println( info.toString() );
    }
    
    @Test
    public void delete_테스트() {
        
        int result = auditVisitBLO.delete( "1SdnXH1H000" );
        System.out.println( "result : " + String.valueOf( result ) );
        
    }

}
