package com.remarkablesoft.framework.service.link.relationship.model.impl;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.link.relationship.vo.RelationshipCnd;
import com.remarkablesoft.framework.service.link.relationship.vo.RelationshipInfo;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class RelationshipBLOTest extends BaseModelTest {
    
    @Autowired
    RelationshipBLO relationshipBLO;
    
    @Test
    public void insert_테스트() {
        RelationshipInfo info = SystemFactory.getRelationshipInfo();
        info.setOid( OIDGenerator.generateOID() );
        info.setObject( BranchInfo.getObjectType() );
        info.setRelationshipOid( OIDGenerator.generateOID() );
        info.setRelationshipObject( UserInfo.getObjectType() );
        info.setRelationshipType( "12345678" );
        
        info.setInputDate( LocalDateTime.now() );
        info = relationshipBLO.insert( info );
    
        System.out.println( info.toString() );
    }
    
    @Test
    public void delete_테스트() {
        RelationshipCnd cnd = new RelationshipCnd();
        cnd.setOid( "1SR9ieC2000" );
        int result = relationshipBLO.delete( cnd );
        System.out.println( String.valueOf( result ) );
    }
    
    @Test
    public void listAll_테스트() {
        RelationshipCnd cnd = new RelationshipCnd();
        List<RelationshipInfo> list = relationshipBLO.listAll( cnd );
        list.forEach( item -> System.out.println( item.toString() ) );
    }
}