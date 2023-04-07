package com.remarkablesoft.framework.service.storage.file.model.impl;


import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.service.SystemFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.service.storage.file.vo.FileCnd;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	storage - file
 * 		@프로그램 ID		:	FileBLOTest
 * 		@프로그램 개요		:	FILE BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 06. : 안병현 - 신규 생성( 테스트 RUN X )
 *      ============================================================================
 */

/*
 * 1. 수정 필요 사항
 * - TB_FILE에 ORDER_NO가 없음요
 * - 
 * */
public class FileBLOTest extends BaseModelTest {
	
	@Autowired
	FileBLO fileBLO;
	
	/*
	 * UserInfo를 파라메터로 받는 insert 메소드
	 * */
	@Test
	public void insert_var_user_테스트() {
		
		//파일 리스트 생성
		List<FileInfo> list = new ArrayList<FileInfo>();
		for( int i = 0 ; i < 2 ; i++ ) {
			FileInfo file = SystemFactory.getFileInfo();
			file.setStorageFileUid( OIDGenerator.generateOID() );
			file.setFileSize( 102 );
			file.setFileName( "TEST" );
			file.setFileType( "jpg" );
			list.add( file );		
		}
		
		//유저 생성: 임시
		UserInfo user = SystemFactory.getUserInfo();
		user.setOid( OIDGenerator.generateOID() );
		user.setGroupOid( OIDGenerator.generateOID() );
		user.setInputUser( "TedTEST" );
		user.setFileList( list );	//file SET
		
		//결과
		int result = fileBLO.insert( user );
		String resultStr = ( 0 <= result ) ? "저장 성공" : "저장 실패";
		System.out.println( resultStr );
	}
	
	/*
	 * fileInfo를 파라메터로 받는 insert 메소드
	 * */
	@Test
	public void insert_file_테스트() {
		
		//USER
		UserInfo user = SystemFactory.getUserInfo();
		user.setOid( OIDGenerator.generateOID() );	
		user.setGroupOid( OIDGenerator.generateOID() );
		
		//FILE
		FileInfo file = SystemFactory.getFileInfo();
		
		file.setStorageFileUid( "11111" );
		file.setFileSize( 1023 );
		file.setFileName( "TEST2" );
		file.setFileType( "PNG" );
		file.setFileUrl( "../../" );
		
		file = fileBLO.insert( file, user.getObjectType() , user.getOid() , user.getGroupOid() );
		
		System.out.println( file.toString() );
		
	}
	
	/*
	 * get 테스트
	 * */
	@Test
	public void get_테스트() {
		FileInfo file = fileBLO.get( "1SRDX7Qw002" );
		System.out.println( file.toString() );
	}
	
	/*
	 * 리스트 조회 테스트
	 * */
	@Test
	public void list_테스트() {
		
		List<FileInfo> list = fileBLO.listByTarget( UserInfo.getObjectType() , "1SRDWZHI002" );
		list.forEach( item -> System.out.println( item.toString() ) );
	}
	
	/*
	 * 리스트 조회 테스트
	 * */
	@Test
	public void listAll_테스트() {
		FileCnd cnd =  new FileCnd();
		
		List<FileInfo> list =  fileBLO.listAll( cnd );
		
		list.forEach( System.out::println ); 
	}
	
	/*
	 * 리스트 조회 테스트
	 * */
	@Test
	public void delete_테스트() {
		
		int result = fileBLO.deleteByStorageFileUid( "11111" );
		String resultStr = ( 0 <= result ) ? "삭제 성공" : "삭제 실패";
		System.out.println( resultStr );
	}
	
	
	/*
	 * 사용자 프로필 조회 테스트
	 * */
	@Test
	public void fillProfile_테스트() {
		
		//유저 생성: 임시
		List<UserInfo> list = new ArrayList<UserInfo>();
		UserInfo user = new UserInfo();
		user.setOid( "22222" );	
		user.setGroupOid( OIDGenerator.generateOID() );
		user.setInputUser( "TedTEST" );
		list.add( user );
		
		fileBLO.fillProfile( list );
		
		//결과
		for( UserInfo info : list ){
			
			System.out.println( info.getProfile() );
			
		}
	}

}
