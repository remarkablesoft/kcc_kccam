package com.remarkablesoft.framework.service.storage.storagefile.model.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.service.storage.storagefile.vo.StorageFileCnd;
import com.remarkablesoft.framework.service.storage.storagefile.vo.StorageFileInfo;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	storage - storageFile
 * 		@프로그램 ID		:	StorageFileBLOTest
 * 		@프로그램 개요		:	STORAGEFILE BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 06. : 안병현 - 신규 생성( 테스트 RUN X )
 *      ============================================================================
 */

class StorageFileBLOTest extends BaseModelTest {
	
	@Autowired
	StorageFileBLO storageFileBLO;
	
	/*
	 * StorageFile INSERT 테스트
	 * */
	@Test
	public void insert_테스트(){
		StorageFileInfo info = new StorageFileInfo();
		info.setStorageFileUid( "11111" );			// 테스트용 임시 UID
		info.setHashValue( "123B123123h" );
		info.setInputDate( LocalDateTime.now() );
		
		//결과
		info = storageFileBLO.insert( info );
		System.out.println( "-------------------------------------" );
		System.out.println( "스토리지 파일 UID" + info.getStorageFileUid() );
		System.out.println( "Hash 값" 		+ info.getHashValue() );
		System.out.println( "파일 카운트" 		+ info.getFileCount() );
		System.out.println( "-------------------------------------" );
	}
	
	/*
	 * 스토리지 파일 UPDATE 테스트
	 * */
	public void update_테스트() {
		
		StorageFileInfo info = new StorageFileInfo();
		info.setStorageFileUid( "11111" );			// 테스트용 임시 UID
		info.setHashValue( "456y789o" );
		
		//결과
		info = storageFileBLO.update( info );
		System.out.println( "-------------------------------------" );
		System.out.println( "스토리지 파일 UID" + info.getStorageFileUid() );
		System.out.println( "Hash 값" 		+ info.getHashValue() );
		System.out.println( "파일 카운트" 		+ info.getFileCount() );
		System.out.println( "-------------------------------------" );
	}	
	
	/*
	 * 스트리지 파일카운트 감소 테스트
	 * */
	@Test
	public void 카운트_감소_테스트(){
			
		// 카운트 감소
		List<String> storageFileUidList = new ArrayList<String>();
		storageFileUidList.add( "11111" );
		
		//결과
		int result = storageFileBLO.fileCountDecrease( storageFileUidList );
		String resultStr = ( 0 <= result ) ?  "감소 성공" : "감소 실패";
		
		System.out.println( resultStr );
	
		
	}
	
	/*
	 * 스토리지 파일 GET 테스트
	 * */
	public void get_테스트() {
		
		// 사전값
				StorageFileCnd cnd = new StorageFileCnd();
				cnd.setStorageFileUid( "11111" );
				StorageFileInfo info = storageFileBLO.get( cnd );
				
				//결과
				info = storageFileBLO.update( info );
				System.out.println( "-------------------------------------" );
				System.out.println( "스토리지 파일 UID" + info.getStorageFileUid() );
				System.out.println( "Hash 값" 		+ info.getHashValue() );
				System.out.println( "파일 카운트" 		+ info.getFileCount() );
				System.out.println( "-------------------------------------" );
				
	}

	
	/*
	 * 스토리지 파일 존재 여부 테스트
	 * */
	public void exist_테스트() {
		
		StorageFileCnd cnd = new StorageFileCnd();
		cnd.setStorageFileUid( "11111" );
		boolean result = storageFileBLO.exist( cnd );
		
		String resultStr = ( result ) ?  "해당 데이터 존재" : "해당 데이터 없음";
		System.out.println( resultStr );
	}

}
