package com.remarkablesoft.framework.service.storage.thumbnail.model.impl;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.service.storage.thumbnail.vo.ThumbnailCnd;
import com.remarkablesoft.framework.service.storage.thumbnail.vo.ThumbnailInfo;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	storage - Thumbnail
 * 		@프로그램 ID		:	ThumbnailBLOTest
 * 		@프로그램 개요		:	Thumbnail BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 06. : 안병현 - 신규 생성( 테스트 RUN X )
 *      ============================================================================
 */

class ThumbnailBLOTest extends BaseModelTest{

	@Autowired
	ThumbnailBLO thumbnailBLO;
	
	/*
	 * INSERT 테스트
	 **/
	@Test
	public void insert_테스트() {
		
		//유저 객체: targetOid, ObjecTType 용
		UserInfo user = new UserInfo();
		user.setOid( "1fD3Vd3" );
		
		//썸네일 객체: OID는 테스트를 못해서 임시로 지정
		ThumbnailInfo thum = new ThumbnailInfo();
		thum.setOid( "1d2jm2b4" );
		thum.setTargetOid( user.getOid() );
		thum.setTargetObject( user.getObjectType() );
		thum.setStorageFileUid( "1D2dsf9d" );
		thum.setThumbnailType( "png" );
		thum.setInputUser( "TedTest" );
		
		thum = thumbnailBLO.insert( thum );
		
		System.out.println( "--------------------------------" );
		System.out.println( "썸네일 OID" + thum.getOid() );
		System.out.println( "썸네일 타입" + thum.getThumbnailType() );
		System.out.println( "--------------------------------" );
	}
	
	/*
	 * Thumbnail List를 Insert하는 메소드 테스트
	 **/
	@Test
	public void insert_target_테스트() {
		
		List<ThumbnailInfo> list = new ArrayList<ThumbnailInfo>();
		
		//썸네일 객체 생성 > 리스트에 추가
		for( int i = 0 ; i < 2 ; i++ ){
			ThumbnailInfo thum = new ThumbnailInfo();
			thum.setOid( OIDGenerator.generateOID() );
			thum.setThumbnailType( "png" );	
			list.add( thum );
		}
		
		//User 객체 생성
		UserInfo info = new UserInfo();
		info.setOid( "1fD3Vd3" );
		
		int result = thumbnailBLO.insert( info.getObjectType() , info.getOid(), list );
		
		String resultStr = ( 0 <= result ) ? "저장 성공" : "저장 실패";
		System.out.println( resultStr );
		
	}
	
	/*
	 * 조건에 맞는 썸네일을 반환
	 **/
	@Test
	public void get_테스트() {
		ThumbnailCnd cnd = new ThumbnailCnd();
		cnd.setThumbnailType( "png" );
		cnd.setStorageFileUid( "1D2dsf9d" );
		
		ThumbnailInfo info = thumbnailBLO.get( cnd );
		System.out.println( "--------------------------------" );
		System.out.println( "썸네일 OID" + info.getOid() );
		System.out.println( "썸네일 타입" + info.getThumbnailType() );
		System.out.println( "--------------------------------" );
	}
	
	/*
	 * Thumbnail List를 반환하는 메소드 테스트
	 **/
	@Test
	public void listAll_테스트() {
	
		ThumbnailCnd cnd =  new ThumbnailCnd();
		
		List<ThumbnailInfo> list =  thumbnailBLO.listAll( cnd );
		
		list.forEach( System.out::println ); 
	}
	
	/*
	 * 삭제 테스트
	 **/
	@Test
	public void delete_테스트() {
			
		int result = thumbnailBLO.deleteByTargetOid( "1fD3Vd3" );
		String resultStr = ( 0 <= result ) ? "삭제 성공" : "삭제 실패";
		System.out.println( resultStr );
		
	}
}
