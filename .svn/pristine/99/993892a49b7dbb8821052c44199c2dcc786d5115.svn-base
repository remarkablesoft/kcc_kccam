package com.remarkablesoft.framework.service.mgnt.batch.model.impl;

import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.mgnt.batch.vo.BatchProcessCnd;
import com.remarkablesoft.framework.util.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.service.mgnt.batch.vo.BatchProcessInfo;

import javax.xml.validation.SchemaFactory;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	mgnt - batch
 * 		@프로그램 ID		:	BatchProcessBLOTest
 * 		@프로그램 개요		:	BatchProcess BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 07. : 안병현 - 신규 생성( 테스트 RUN X )
 *      ============================================================================
 */

public class BatchProcessBLOTest extends BaseModelTest{
	
	@Autowired
	BatchProcessBLO batchProcessBLO;
	
	@Test
	public void insert_테스트() {
		
		BatchProcessInfo info = SystemFactory.getBatchProcessInfo();
		
		info.setOid( OIDGenerator.generateOID() );
		info.setBatchName( "batchName" );
		info.setThreadName( "threadName" );
		info.setClassName( "className" );
		info.setApiName( "apiName" );
		
		info.setSuccessYn( SystemConstants.FLAG_YES );
		info.setExecuteYn( SystemConstants.FLAG_NO );
		info.setTotalCnt( 111 );
		info.setSuccessCnt( 222 );
		info.setFailCnt( 333 );
		
		info.setStartTime( LocalTime.now().toString() );
		info.setElapsedTime( 1111 );
		info.setDescr( "설명" );
		info.setInputDate( LocalDateTime.now() );
	
		int result = batchProcessBLO.insert( info );
		System.out.println( String.valueOf( result ) );
		
	}
	
	@Test
	public void exist_테스트() {
		BatchProcessCnd cnd = new BatchProcessCnd();
		cnd.setBatchName( "batchName" );
		batchProcessBLO.exist( cnd );
	}

	@Test
	public void 출력_테스트() {
		System.out.println( LocalTime.now().toString() );
	}
}
