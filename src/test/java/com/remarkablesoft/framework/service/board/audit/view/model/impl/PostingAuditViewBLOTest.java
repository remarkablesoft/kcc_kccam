package com.remarkablesoft.framework.service.board.audit.view.model.impl;

import java.util.List;
import java.util.concurrent.ScheduledFuture;

import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.SystemFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.board.audit.view.vo.PostingAuditViewCnd;
import com.remarkablesoft.framework.service.board.audit.view.vo.PostingAuditViewInfo;
import com.remarkablesoft.framework.service.board.posting.vo.PostingInfo;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	board - audit
 * 		@프로그램 ID		:	PostingAuditViewBLOTest
 * 		@프로그램 개요		:	PostingAuditView BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 07. : 안병현 - 신규 생성( 테스트 RUN X )
 *      ============================================================================
 */

public class PostingAuditViewBLOTest extends BaseModelTest{

	@Autowired
	PostingAuditViewBLO postingAuditViewBLO;
	
	/*
	 * 포스팅 보기이력 Insert
	 * */
	@Test
	public void insert_테스트() {
		
		PostingInfo info = SystemFactory.getPostingInfo();
		info.setOid( OIDGenerator.generateOID() );
		info.setContents( "<br>공지<br>사항" );
		info.setInputUser( "T1121edTest" );
		info.setBoardOid( "2132354" );
		
		PostingAuditViewInfo hist = postingAuditViewBLO.insert( info, "11111111111" );	//Posting, ViewUserOID, User DB에 있어야함
		
		System.out.println( hist.toString() );
	}
	
	/*
	 * 게시판 보기 이력 GET 
	 * */
	@Test
	public void get_테스트() {
		
		PostingAuditViewCnd cnd = new PostingAuditViewCnd();
		cnd.setOid( "1SR8qy63001" );
		
		PostingAuditViewInfo hist = postingAuditViewBLO.get( cnd );
		
		System.out.println( hist.toString() );
	}
	
	/*
	 * 게시판 보기 이력을 List로 가져오는 테스트
	 * */
	@Test
	public void list_테스트() {
		
		PostingAuditViewCnd cnd = new PostingAuditViewCnd();
		cnd.setBoardOid( "2132354" );
		
		PageList<PostingAuditViewInfo> list = postingAuditViewBLO.list( cnd );
		
		list.forEach( s -> System.out.println( s ) );
		
		
	}
	
	/*
	 * 게시판 보기 이력을 모두 가져오는 테스트
	 * */
	@Test
	public void listAll_테스트() {
		
		PostingAuditViewCnd cnd = new PostingAuditViewCnd();
		
		List<PostingAuditViewInfo> list = postingAuditViewBLO.listAll( cnd );
		
		list.forEach( s -> System.out.println( s ) );

		
	}
	
	/*
	 * 포스팅객체의 이력 개수를 카운트하는 테스트
	 * */
	@Test
	public void 포스팅객체의_보기_카운트_테스트() {
		
		PostingInfo info = SystemFactory.getPostingInfo();
		info.setOid( "1SR8tfvM000" );
		info.setBoardOid( "2132354" );
		
		int result = postingAuditViewBLO.getPostingViewCount( info );
		
		System.out.println( "--------------------------------------" );
		System.out.println( info.getOid() + "의 현재 이력 개수 	          : " + result );
		System.out.println( "--------------------------------------" );	
	}
	
	

}
