package com.remarkablesoft.site.kccam.service.datasheet.model.impl;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetCnd;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetTargetItemInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * 		@주시스템		:	kccam
 * 		@서브 시스템		:	datasheet - datasheetTargetItem
 * 		@프로그램 ID		:	DatasheetTargetItemBLOTest
 * 		@프로그램 개요	:	데이터시트 타겟 상세 항목 BLO 테스트
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

public class DatasheetTargetItemBLOTest extends BaseModelTest {

    @Autowired
    DatasheetTargetItemBLO datasheetTargetItemBLO;
    
    @Test
    public void insert_테스트() {
        DatasheetTargetItemInfo info = new DatasheetTargetItemInfo();
    
        info.setDatasheetOid( OIDGenerator.generateOID() );
        info.setTargetOid( OIDGenerator.generateOID() );
        info.setItemValue( "12" );
        info.setItemUnit( "L" );
        
        info.setItemType( "QWER" );
        info.setItemGroupCode( "CHK" );
        info.setXLoc( "1" );
        info.setYLoc( "2" );
        
        info = datasheetTargetItemBLO.insert( info );
        System.out.println( info.toString() );
    
    }
    
    @Test
    public void update_테스트() {
        
        DatasheetTargetItemInfo info = new DatasheetTargetItemInfo();
        info.setOid( "1SREm5fr002" );
        
        info.setDatasheetOid( OIDGenerator.generateOID() );
        info.setTargetOid( OIDGenerator.generateOID() );
        info.setTargetObject( "ASDF" );
        info.setItemValue( "11241" );
        info.setItemUnit( "kg" );
        
        info.setItemType( "RAD" );
        info.setItemGroupCode( "G1" );
        info.setXLoc( "15" );
        info.setYLoc( "26" );
        
        info = datasheetTargetItemBLO.update( info );
        System.out.println( info.toString() );
    
    }
    
    @Test
    public void delete_테스트() {
        DatasheetCnd cnd = new DatasheetCnd();
        cnd.setOid( "1SREot5V002" );
        int result = datasheetTargetItemBLO.delete( cnd );
     
        System.out.println( result );
    }
    
    @Test
    public void get_테스트() {
        
        DatasheetCnd cnd = new DatasheetCnd();
        cnd.setOid( "1SREm5fr002" );
        DatasheetTargetItemInfo info = datasheetTargetItemBLO.get( cnd );
        
        System.out.println( info.toString() );
        
    }
    
    @Test
    public void listAll_테스트() {
        
        DatasheetCnd cnd = new DatasheetCnd();
        List<DatasheetTargetItemInfo> list = datasheetTargetItemBLO.listAll( cnd );
        list.forEach( item -> System.out.println( item.toString() ) );
        
    }
}