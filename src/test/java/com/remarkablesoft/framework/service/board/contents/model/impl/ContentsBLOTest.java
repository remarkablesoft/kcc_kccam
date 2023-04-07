package com.remarkablesoft.framework.service.board.contents.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.board.board.vo.BoardInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsCnd;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;
import com.remarkablesoft.framework.service.board.posting.vo.PostingInfo;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템			:	board - content
 * 		@프로그램 ID			:	ContentsBLOTest
 * 		@프로그램 개요		:	Contents BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 07. : 안병현 - 신규 생성( 테스트 RUN X )
 *      ============================================================================
 */

public class ContentsBLOTest extends BaseModelTest {
	
	@Autowired
	ContentsBLO contentBLO;
	
	/*
	 * 글 내용 INSERT
	 * */
	@Test
	public void insert_테스트() {
		
		//ContentsInfo
		ContentsInfo info = SystemFactory.getContentsInfo();
		
		info.setTargetOid( "1SR9FfAz000" );
		info.setTargetObject( BoardInfo.getObjectType() );
		info.setContents( "<br>공지사항2<br>공지사항2" );
		info.setContentsSize( 22 );
		info.setContainerOid( "22222222222" );
		
		info.setInputUser( "2Test" );
		
		info = contentBLO.insert( info );
		
		System.out.println( info.toString() );
	}
	
	/*
	 * 글 내용 UPDATE
	 * */
	@Test
	public void update_테스트() {
			
		ContentsInfo info = SystemFactory.getContentsInfo();
		info.setContents( "수정된공지사항" );
		info.setTargetOid( "1SR9FfAz000" );
		info.setTargetObject( BoardInfo.getObjectType() );
		
		int result = contentBLO.update( info );
		
		String resultStr = ( 0 < result ) ? "수정 성공" : "수정 실패";
		System.out.println( resultStr );
	}
	
	/*
	 * 글 내용 GET
	 * */
	@Test
	public void get_테스트() {
		
		ContentsInfo info = SystemFactory.getContentsInfo();
		info.setTargetOid( "1SUcjmuw006" );	//POSTING: 테스트를 못해서 임시로 부여한 ID
		info.setTargetObject( "FWPO" );
		
		info = contentBLO.get( info );
		
		System.out.println( info.toString() );
	}
	
	/*
	 * 글 내용을 리스트로 불러옵니다.
	 * */
	@Test
	public void list_테스트() {
		
		ContentsCnd cnd = new ContentsCnd();
		cnd.setTargetOid( "1SR9FfAz000" );
		cnd.setTargetObject( BoardInfo.getObjectType() );
		
		List<ContentsInfo> list = contentBLO.list( cnd );
		
		list.forEach( s -> System.out.println( s ) );
			
	}
	
	/*
	 * Posting 객체를 Contents로 변경하는 메소드
	 * */
	@Test
	public void 게시글_전환_테스트() {
		
		//Postting 객체
		PostingInfo post = SystemFactory.getPostingInfo();
		post.setOid( OIDGenerator.generateOID() );
		post.setInputUser( "TedTest" );
		post.setContents( "나는 포스팅 객체 객체 객체" );
		
		ContentsInfo info = contentBLO.convertContentsInfo( post );
		
		System.out.println( "------------------------------------" );
		System.out.println( "OID : " + info.getOid() );
		System.out.println( "내용 : " + info.getContents() );
		System.out.println( "------------------------------------" );	
		
	}
	
	/*
	 * 게시물의 내용을 채워주는 테스트
	 * */
	@Test
	public void 포스팅_내용_찾기_테스트() {
		
		//POSTING LIST
		List<PostingInfo> postList = new ArrayList<PostingInfo>();
		PostingInfo info = new PostingInfo();
		info.setOid( "000000" );
		postList.add( info );
		
		//컨텐츠 내용 OIDLIST: 해당 targetOid와 일치하는 Contents를 찾는다.
		List<String> contentOidList = new ArrayList<String>();
		contentOidList.add( "00000" );
		
		contentBLO.fillPostContents( postList, contentOidList );
		
		postList.forEach( s -> System.out.println( s.getContents() ) );
	}
	
	/*
	 * 글 내용 DELETE
	 * */
	@Test
	public void delete_테스트() {
		
		ContentsCnd cnd = new ContentsCnd();
		cnd.setTargetOid( "1SR9FfAz000" );
		cnd.setTargetObject( BoardInfo.getObjectType() );
		int result = contentBLO.delete( cnd );
		String resultStr = ( 0 <= result ) ? "삭제 성공" : "삭제 실패";
		System.out.println( resultStr );
		
	}
	
	@Test
	public void insertBulk_테스트() {
		
		List<ContentsInfo> list = new ArrayList<>();
		ContentsInfo contents = SystemFactory.getContentsInfo();
		contents.setContents( "123123" );
		contents.setInputUser( "11111111111" );
		
		list.add( contents );
		contents = SystemFactory.getContentsInfo();
		contents.setContents( "123123" );
		contents.setInputUser( "11111111111" );
		list.add( contents );
		contents = SystemFactory.getContentsInfo();
		contents.setContents( "123123" );
		contents.setInputUser( "11111111111" );
		list.add( contents );
		
		contentBLO.insertBulk( list );
	}
}
