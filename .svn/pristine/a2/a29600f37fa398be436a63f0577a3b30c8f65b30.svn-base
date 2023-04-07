package com.remarkablesoft.framework.service.board.posting.model.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.board.posting.vo.PostingCnd;
import com.remarkablesoft.framework.service.board.posting.vo.PostingInfo;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	board - posting
 * 		@프로그램 ID		:	PostingAuditViewBLOTest
 * 		@프로그램 개요		:	PostingAuditView BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 07. : 안병현 - 신규 생성( 테스트 RUN X )
 *      ============================================================================
 */

public class PostingDAOTest extends BaseModelTest {

	@Autowired
	PostingDAO postingDAO;
	
	
	@Test
	public void insert() {
		PostingInfo info = new PostingInfo();
		
		info.setOid( OIDGenerator.generateOID() );
		info.setBoardOid( "1SYzT4IC008" );
		info.setNoticeLevel( 0 );
		info.setTempYn( "Y" );
		info.setFromDate( LocalDateTime.now() );
		info.setToDate( LocalDateTime.now().plusYears(1) );
		//info.setDelDate( LocalDateTime.now() );
		//info.setDelUser( "Woong" );
//		info.setThreadOid( OIDGenerator.generateOID() );
//		info.setThreadFullPathOid( info.getThreadOid() + SystemConstants.DELIMETER_DEFAULT + info.getOid() );
		//info.setThreadParentOid("");
		postingDAO.insert(info);
	}

	@Test
	public void update() {
		PostingInfo info = new PostingInfo();
		
		info.setOid( "1SZ8olBK000" );
		info.setBoardOid( "updateTest" );
		info.setNoticeLevel( 2 );
		info.setTempYn( "N" );
		info.setCommentCount( 33 );
		info.setRecommendCount( 25 );
		info.setLikeCount( 42 );
		info.setAnonymousPwd( "password" );
		info.setPickYn( "Y" );
//		info.setThreadOid( info.getOid() );
//		info.setThreadParentOid( "1SYJDWZ7005" );
//		info.setThreadFullPathOid( info.getThreadOid() + SystemConstants.DELIMETER_DEFAULT + info.getThreadParentOid() );
		
		info.setCustomField1( "customfield update" );
		
		postingDAO.update( info );
	}
	
	@Test
	public void get() {
		PostingCnd cnd = new PostingCnd();
		cnd.setOid( "1SUdZPwu006" );
		
		PostingInfo info = postingDAO.get( cnd );
		
		System.out.println( info );
	}
	
	@Test
	public void getPrev() {
		PostingCnd cnd = new PostingCnd();
		cnd.setOid( "1SUdZPwu006" );
		
		PostingInfo info = postingDAO.getPrev( cnd );
		
		System.out.println( info );
	}
	
	@Test
	public void getNext() {
		PostingCnd cnd = new PostingCnd();
		cnd.setOid( "1SUdZPwu006" );
		
		PostingInfo info = postingDAO.getNext( cnd );
		
		System.out.println( info );
	}
	
	@Test
	public void exist() {
		
		boolean result = postingDAO.exist( "1SUdZPwu006" );
		
		System.out.println( result );
	}
	
	@Test
	public void listAll() {
		PostingCnd cnd = new PostingCnd();
		cnd.setLang( SystemConstants.LANG_ENG.getKey() );
		
		List<PostingInfo> list = postingDAO.listAll( cnd );
		
		list.forEach( System.out :: println );
	}
	
	@Test
	public void list() {
		PostingCnd cnd = new PostingCnd();
		PageList<PostingInfo> list = postingDAO.list(cnd);
		
		list.forEach( System.out :: println );
	}
	
	@Test
	public void getNumber() {
		PostingCnd cnd = new PostingCnd();
		cnd.setOid( "1SZ8olBK000" );
		cnd.setBoardOid( "updateTest" );
		cnd.setLang( SystemConstants.LANG_ENG.getKey() );
		int result = postingDAO.getNumber( cnd );
		System.out.println( result );
	}
	
	@Test
	public void checkAnonymousPwd() {
		PostingInfo info = new PostingInfo();
		info.setOid( "1SZ8olBK000" );
		info.setAnonymousPwd( "password" );
		
		PostingCnd cnd = new PostingCnd();
		cnd.setPostingInfo( info );
		//cnd.setLang( SystemConstants.LANG_ENG.getKey() );
		
		boolean result = postingDAO.checkAnonymousPwd( cnd );
		System.out.println( result );
	}
	
	@Test
	public void listRank() {
		PostingCnd cnd = new PostingCnd();
		cnd.setSearchCount( 8 );
		
		List<String> orderByList = new ArrayList<>();
		orderByList.add( "view_cnt" );
		cnd.setOrderByList( orderByList );
		List<String> columnList = new ArrayList<>();
		columnList.add( "view_cnt" );
		cnd.setColumnList( columnList );
		
		List<Map<String, Object>> list = postingDAO.listRank( cnd );
		
		list.forEach(  System.out :: println );
	}
	
	@Test
	public void listMyAnswerOfParent() {
		PostingCnd cnd = new PostingCnd();
		cnd.setThreadOid( "1SZ8olBK000" );
		
		PageList<PostingInfo> list = postingDAO.listMyAnswerOfParent( cnd );
		list.forEach(  System.out :: println );
	}
	
	@Test
	public void listPick() {
		PostingCnd cnd = new PostingCnd();
		cnd.setPickYn( "N" );
		
		PageList<PostingInfo> list = postingDAO.listPick( cnd );
		list.forEach(  System.out :: println );
	}
	
}
