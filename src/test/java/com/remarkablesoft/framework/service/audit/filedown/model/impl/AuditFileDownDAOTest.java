package com.remarkablesoft.framework.service.audit.filedown.model.impl;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.audit.filedown.vo.AuditFileDownInfo;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitInfo;
import com.remarkablesoft.framework.service.storage.file.vo.FileCnd;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AuditFileDownDAOTest extends BaseModelTest {
    
    @Autowired
    AuditFileDownDAO auditFileDownDAO;
    
    @Test
    public void insert_테스트() {
        
        AuditFileDownInfo info = SystemFactory.getAuditFileDownInfo();
        
        info.setOid( OIDGenerator.generateOID() );
        info.setDownDate( LocalDateTime.now() );
        info.setDownUser( "11111111111" );
        info.setBoardOid( "22222222222" );
        info.setFileOid( "33333333333" );
        info.setPostingOid( "44444444444" );
        
        int result = auditFileDownDAO.insert( info );
        System.out.println( "result : " + String.valueOf( result ) + "::" + info.toString() );
    }
    
    @Test
    public void get_테스트() {
        FileCnd fileCnd = new FileCnd();
        fileCnd.setDownAuditOid( "1SR8LXWO000" );
        AuditFileDownInfo info = auditFileDownDAO.get( fileCnd );
        System.out.println( info.toString() );
    }
    
    @Test
    public void listAll_테스트() {
        auditFileDownDAO.listAll( new FileCnd() );
    }
    
    @Test
    public void delete_테스트() {
        auditFileDownDAO.delete( "1SR8LXWO000" );
    }
    
    @Test
    public void systemFactoryGet_테스트() {
        AuditFileDownInfo auditFileDownInfo = SystemFactory.getAuditFileDownInfo();
        System.out.println( auditFileDownInfo.toString() );
    }
}