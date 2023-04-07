package com.remarkablesoft.site.kccam.service.datasheet.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetCnd;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetInfo;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetItemInfo;



/**                                                            
 * 	                                                           
 * 		@주시스템		:	kccam 	                   
 * 		@서브 시스템		:	datasheet - datasheet
 * 		@프로그램 ID		:	DatasheetBLOTest
 * 		@프로그램 개요	:	데이터시트 BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

public class DatasheetBLOTest extends BaseModelTest {

    @Autowired
    protected DatasheetBLO datasheetBLO;

    @Test
    public void insert_테스트() {

    	DatasheetInfo info = new DatasheetInfo();

    	info.setStdDatasheetOid( OIDGenerator.generateOID() );
    	info.setDatasheetType( "QWERTYUI" );
    	info.setTitle( "title2" );
    	info.setDescr( "description2" );
    	info.setInputUser( OIDGenerator.generateOID() );
    	
    	info = datasheetBLO.insert( info );
        System.out.println( info.toString() );
        
    }

    @Test
    public void update_테스트() {

    	DatasheetInfo info = new DatasheetInfo();
        info.setOid( "1SREEUj9002" );
        info.setStdDatasheetOid( OIDGenerator.generateOID() );
        info.setTitle( "title3" );
        info.setDescr( "descr3" );
        
        info = datasheetBLO.update( info );
        System.out.println( info.toString() );
    
    }

    @Test
    public void delete_테스트() {
        int result = datasheetBLO.delete( "1SREEgkM002" );
        System.out.println( "삭제결과 : " + result );
    }

    @Test
    public void get_테스트() {
        DatasheetCnd cnd = new DatasheetCnd();
        cnd.setOid( "1SREEUj9002" );
        DatasheetInfo info = datasheetBLO.get( cnd );
        
        System.out.println( info.toString() );
    }

    @Test
    public void list_테스트() {

		DatasheetCnd cnd =  new DatasheetCnd();
		PageList<DatasheetInfo> list =  datasheetBLO.list( cnd );

		list.forEach( System.out::println ); 
    }

    @Test
    public void listAll_테스트() {

		DatasheetCnd cnd =  new DatasheetCnd();
		List<DatasheetInfo> list =  datasheetBLO.listAll( cnd );

		list.forEach( System.out::println ); 
    }
    
    @Test
    public void insert_관련정보_저장() {
    	//dataSheet와 관련된 모든 정보를 저장
    	DatasheetInfo info = new DatasheetInfo();
    	DatasheetItemInfo item = new DatasheetItemInfo();
    	List<DatasheetItemInfo> list = new ArrayList<DatasheetItemInfo>();
    	
    	info.setStdDatasheetOid( OIDGenerator.generateOID() );
    	info.setDatasheetType( "QWERTYUI" );
    	info.setTitle("test4");
    	info.setDescr("testtest4");
    	
    	item.setName("testName");
    	item.setItemGroupCode( "ASDFGHJK" );
    	item.setTestMethod("ByTest");
    	list.add(item);
    	info.setDatasheetItemList(list);
    	
    	info =  datasheetBLO.insert(info);
    	
    	
    	System.out.println( info );
    	System.out.println( "=========" );
    	info.getDatasheetItemList().forEach( System.out::println );
    		
    }
    
    @Test
    public void get_datasheet_and_datasheetItem() {
    	
    	DatasheetCnd cnd = new DatasheetCnd();
    	cnd.setOid("1ST2PMwF001");
    	DatasheetInfo info = datasheetBLO.get(cnd);
    	
    	System.out.println( info );
    	System.out.println( "================" );
    	info.getDatasheetItemList().forEach( System.out::println );
    	
    }

}
