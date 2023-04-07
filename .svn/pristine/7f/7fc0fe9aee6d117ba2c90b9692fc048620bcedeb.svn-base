package com.remarkablesoft.site.kccam.service.datasheet.model.impl;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetCnd;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetItemInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 	                                                           
 * 		@주시스템		:	kccam
 * 		@서브 시스템		:	datasheet - datasheetItem
 * 		@프로그램 ID		:	DatasheetItemBLOTest
 * 		@프로그램 개요	:	데이터시트 항목 BLO 테스트
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

public class DatasheetItemBLOTest extends BaseModelTest {

    @Autowired
    protected DatasheetItemBLO datasheetItemBLO;

    @Test
    public void insert_테스트() {

    	DatasheetItemInfo info = new DatasheetItemInfo();

    	info.setDatasheetOid( OIDGenerator.generateOID() );
    	info.setItemValue( "1000" );
    	info.setItemUnit( "L" );
    	info.setItemType( "QWER" );
    	
    	info.setItemGroupCode( "ASDFGHJK" );
    	info.setXLoc( "3" );
    	info.setYLoc( "4" );
    	
    	info = datasheetItemBLO.insert( info );
        System.out.println( info.toString() );
    }

    @Test
    public void update_테스트() {

    	DatasheetItemInfo info = new DatasheetItemInfo();
    	info.setOid( "1SRERNHg001" );
    	info.setItemValue( "asdf2" );
    	info.setItemUnit( "kg" );
    	info.setItemType( "ASDF" );
    	
    	info.setXLoc( "3" );
    	info.setYLoc( "4" );
    	
    	info = datasheetItemBLO.update( info );
        System.out.println( info.toString() );
    
    }

    @Test
    public void deleteCnd_테스트() {
        
        DatasheetCnd cnd = new DatasheetCnd();
        cnd.setOid( "1SRERwSv001" );
        int result = datasheetItemBLO.delete( cnd );
        String szResult = result > 0 ? "삭제 성공" : "삭제 실패";
        System.out.println( szResult );
    
    }

    @Test
    public void get_테스트() {

        DatasheetCnd cnd = new DatasheetCnd();
        cnd.setOid( "1SRERNHg001" );
        DatasheetItemInfo info = datasheetItemBLO.get( cnd );
        
        System.out.println( info.toString() );
    }

    @Test
    public void listAll_테스트() {

		DatasheetCnd cnd =  new DatasheetCnd();
		List<DatasheetItemInfo> list =  datasheetItemBLO.listAll( cnd );

		list.forEach( System.out::println ); 
    }


}
