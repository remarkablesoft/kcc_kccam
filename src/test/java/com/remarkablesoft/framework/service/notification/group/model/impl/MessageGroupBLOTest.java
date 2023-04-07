package com.remarkablesoft.framework.service.notification.group.model.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.notification.message.group.model.impl.MessageGroupBLO;
import com.remarkablesoft.framework.service.notification.message.group.vo.MessageGroupCnd;
import com.remarkablesoft.framework.service.notification.message.group.vo.MessageGroupInfo;
import com.remarkablesoft.framework.service.org.user.model.impl.UserBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	notification - group
 * 		@프로그램 ID		:	MessageGroupBLOTest
 * 		@프로그램 개요		:	MessageGroup BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 06. : 안병현 - 신규 생성( 테스트 RUN X )
 *      ============================================================================
 */

public class MessageGroupBLOTest extends BaseModelTest {
	
	@Autowired
	MessageGroupBLO messageGroupBLO;
	
	/*
	 * INSERT 테스트
	 * */
	@Test
	public void insert_테스트(){
		
		//메시지 그룹 객체 생성
		MessageGroupInfo info = new MessageGroupInfo();
		info.setOid( "1D2d34D" );	//테스트 시행을 못해서 임시로 부여한 OID
		info.setDescr( "그룹그룹그룹" );
		info.setInputDate( LocalDateTime.now() );
		info.setInputUser( "TedTest" );
		
		//INSERT
		info = messageGroupBLO.insert( info );
		
		//결과
		System.out.println( "-------------------------" );
		System.out.println( "그룹 OID : " + info.getOid() );
		System.out.println( "그룹 이름 : " + info.getName() );
		System.out.println( "그룹 설명 : " + info.getDescr() );
		System.out.println( "-------------------------" );
		
	}
	
	/*
	 * UPDATE 테스트
	 * */
	@Test
	public void update_테스트(){
		
		MessageGroupInfo info = new MessageGroupInfo();
		info.setOid( "1D2d34D" );	//테스트 시행을 못해서 임시로 부여한 OID
		info.setName( "그룹수정이름1" );
		info.setDescr( "룹그룹그룹그" );
		
		info = messageGroupBLO.update( info );
		//결과
		System.out.println( "-------------------------" );
		System.out.println( "그룹 OID : " + info.getOid() );
		System.out.println( "그룹 이름 : " + info.getName() );
		System.out.println( "그룹 설명 : " + info.getDescr() );
		System.out.println( "-------------------------" );
		
	}
	
	/*
	 * GET 테스트
	 * */
	@Test
	public void get_테스트(){
		
		//객체 생성
		MessageGroupCnd cnd = new MessageGroupCnd();
		cnd.setOid( "1D2d34D" ); //테스트 시행을 못해서 임시로 부여한 OID
		
		//GET
		MessageGroupInfo info = messageGroupBLO.get( cnd );
		
		//결과
		System.out.println( "-------------------------" );
		System.out.println( "그룹 OID : " + info.getOid() );
		System.out.println( "그룹 이름 : " + info.getName() );
		System.out.println( "그룹 설명 : " + info.getDescr() );
		System.out.println( "-------------------------" );
		
		
	}
	
	/*
	 * 메시지 그룹정보에 속해있는 유저 리스트를 가져옵니다.
	 * 
	 * */
	@Test
	public void list_테스트(){
		
		MessageGroupCnd cnd = new MessageGroupCnd();
		cnd.setOid( "1D2d34D" ); //테스트 시행을 못해서 임시로 부여한 OID
		
		PageList<UserInfo> list = messageGroupBLO.groupUserList( cnd );
		
		for( UserInfo info : list ) {
			System.out.println( info );
		}
		
	}
	
	/*
	 * listAll 테스트
	 * */
	@Test
	public void listAll_테스트(){
		
		MessageGroupCnd cnd =  new MessageGroupCnd();
		
		List<MessageGroupInfo> list =  messageGroupBLO.listAll( cnd );
		
		list.forEach( System.out::println ); 
		
	}

}
