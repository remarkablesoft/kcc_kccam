package com.remarkablesoft.framework.service.app.device.model.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.remarkablesoft.framework.module.oid.OIDGenerator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.service.app.device.vo.DeviceCnd;
import com.remarkablesoft.framework.service.app.device.vo.DeviceInfo;



/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	doc - doc
 * 		@프로그램 ID		:	DocBLOTest
 * 		@프로그램 개요		:	Doc BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      1.1 2021. 03. 05. : 안병현 - 테스트 코드 작성( 테스트 RUN X )
 *      ============================================================================
 */

public class DeviceBLOTest extends BaseModelTest {

    @Autowired
    protected DeviceBLO deviceBLO;
    
    @Test
    public void insert_테스트() {
    	
    	DeviceInfo info = new DeviceInfo();

    	info.setOid( OIDGenerator.generateOID() );
    	info.setDeviceModelName( "TESTTEST" );
    	info.setSerialNum( "123456" );
		info.setImei( "asdnkjflwemfrko" );
    	info.setBuildVersion( "1.0.0.1" );

    	info.setOsTypeFlag( "A" );
		info.setAppVersion( "1.2" );
		info.setOsVersion( "1.1" );
		info.setUuid( "1111" );
		info.setPushToken( "asdjmfkazxcvnjklabjsklajhn" );

    	info.setPushYn( "Y" );
    	info.setInputDate(LocalDateTime.now());
    	
    	info = deviceBLO.insert( info );
    	
    	//결과
    	System.out.println( "======= INSERT 결과 =======" );
    	System.out.println( " OID : " + info.toString() );

    }
    
    @Test
    public void insert_or_update_테스트() {
    	  	//기기 정보
    		//uuid로 get을 시행 -> 없으면 insert 있으면 update
    		DeviceInfo info = new DeviceInfo();
    		info.setOid( "0000000" );
    		info.setUuid( "1111" );
    		info.setOsTypeFlag( "I" );
    		
    		info = deviceBLO.insertOrUpdate( info );
    		
    		//결과    		
    		System.out.println( info.toString() );
    		
        	
    }
    
    @Test
    public void update_테스트() {
    	
    	DeviceInfo info = new DeviceInfo();
    	info.setOid( "0000000" );
    	info.setSerialNum( "111111" );
    	info.setPushYn( "N" );
    	
    	info = deviceBLO.update( info );
    	
    	//결과
    	System.out.println( "======= UPDATE 결과 =======" );
    	System.out.println( info.toString() );
    	
    }
       
    @Test
    public void delete_테스트() {
    	
    	DeviceCnd cnd = new DeviceCnd();
    	cnd.setOid( "0000000" );
    	
    	String result = ( deviceBLO.delete( cnd )  > 0) ? "삭제완료" : "삭제실패";
    	
    	System.out.println( result );
    }

    @Test
    public void get_테스트() {

    	DeviceCnd cnd = new DeviceCnd();
    	cnd.setOid( "0000000" );
    	DeviceInfo info = deviceBLO.get( cnd );
    	
    	//결과
    	System.out.println( "======= GET 결과 =======" );
    	System.out.println( info.toString() );
    }

    @Test
    public void list_테스트() {
    	DeviceCnd cnd =  new DeviceCnd();
		cnd.setOsTypeFlag( "A" );
		List<DeviceInfo> list =  deviceBLO.list( cnd );
		
		//결과
		for( DeviceInfo info : list ) {
			System.out.println( info.toString() );
		}
    	
    	//pushtoken
    }

    @Test
    public void listAll_테스트() {

		DeviceCnd cnd =  new DeviceCnd();
		
		List<DeviceInfo> list =  deviceBLO.listAll( cnd );
		
		list.forEach( System.out::println ); 
    }
    
    

}
