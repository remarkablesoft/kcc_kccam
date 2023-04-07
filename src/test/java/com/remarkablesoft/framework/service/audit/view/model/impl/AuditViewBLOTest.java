package com.remarkablesoft.framework.service.audit.view.model.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.audit.view.vo.AuditViewCnd;
import com.remarkablesoft.framework.service.audit.view.vo.AuditViewInfo;

public class AuditViewBLOTest extends BaseModelTest {
    
    @Autowired
    AuditViewBLO auditViewBLO;
    
    @Test
    public void insert_테스트(){
        
		AuditViewInfo info = SystemFactory.getAuditViewInfo();

		info.setTargetObject( "AMMT" );
		info.setTargetOid( "1SWKGVwR002" );
		info.setViewDate( LocalDateTime.now() );

		info = auditViewBLO.insert( info );
		System.out.println( info.toString() );
    }
    
    @Test
    public void getCountByTargetObject_테스트(){
        AuditViewCnd cnd = new AuditViewCnd();
        cnd.setTargetObject( "FWAS" );
        int result = auditViewBLO.getCountByTargetObject( cnd );
        
        System.out.println( "result : " + String.valueOf( result ) );
    }
    
    @Test
    public void listByUserCount_테스트(){
        AuditViewCnd cnd = new AuditViewCnd();
        cnd.setTargetObject( "FWAS" );
        List<AuditViewInfo> resultList = auditViewBLO.listByTargetObjectCount( cnd );
        resultList.forEach( result -> System.out.println( result.toString() ) );
    }
    
    @Test
	public void groupByCountList_테스트() {
    	AuditViewCnd cnd = new AuditViewCnd();
    	cnd.setTargetOid( "11111111111" );
    	cnd.addGroupBy( AuditViewCnd.GROUP_BY_VIEW_YEAR );
    	cnd.addGroupBy( AuditViewCnd.GROUP_BY_VIEW_MONTH );
    	
		List<AuditViewInfo> list = auditViewBLO.groupByCountList( cnd );
		list.forEach( System.out::println );
		
    }
    
}