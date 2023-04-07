package com.remarkablesoft.framework.service.notification.send.model.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.notification.message.group.vo.MessageGroupCnd;
import com.remarkablesoft.framework.service.notification.message.group.vo.MessageGroupInfo;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.model.impl.MessageSendHistBLO;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistCnd;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistInfo;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	notification - template
 * 		@프로그램 ID		:	MessageSendHistBLOTest
 * 		@프로그램 개요		:	MessageTemplateHist BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 06. : 안병현 - 신규 생성( 테스트 RUN X )
 */
public class MessageSendHistBLOTest extends BaseModelTest{
	
	@Autowired
	MessageSendHistBLO messageSendHistBLO;
	
	/*
	 * 메시지 발송 이력 저장
	 * */
	@Test
	public void insert_테스트() {
		
		//메시지 객체
		
		MessageInfo message = new MessageInfo();
		message.setOid( OIDGenerator.generateOID() );
		message.setContents( "TESTTERST" );
		
		//메시지 이력 객체
		MessageSendHistInfo info = new MessageSendHistInfo();
		info.setOid( "1AD34c5" );		//테스트 시행을 하지 못해서 임시로 부여한 OID
		info.setMessageOid( message.getOid() );
		info.setOpenYn( SystemConstants.FLAG_YES );
		info.setMessageType( MessageInfo.MESSAGE_TYPE_EMAIL );
		info.setMessageInfo( message );
		
		//INSERT
		info = messageSendHistBLO.insert( info );
		
		//결과
		System.out.println( "---------------------------" );
		System.out.println( "메시지 이력 OID : " + info.getOid() );
		System.out.println( "메시지 타입     : " + info.getMessageType() );
		System.out.println( "메시지 내용     : " + info.getMessageInfo() );
		System.out.println( "---------------------------" );
		
	}
	
	/*
	 * 메시지 발송 이력 수정
	 * */
	@Test
	public void update_테스트() {
		
		MessageSendHistInfo info = new MessageSendHistInfo();
		info.setOid( "1AD34c5" ); //테스트 시행을 하지 못해서 임시로 부여한 OID
		info.setOpenYn( SystemConstants.FLAG_NO );
		info.setMessageType( MessageInfo.MESSAGE_TYPE_SMS );
		
		//UPDATE
		info = messageSendHistBLO.update( info );
		
		//결과
		System.out.println( "---------------------------" );
		System.out.println( "메시지 이력 OID : " + info.getOid() );
		System.out.println( "메시지 타입     : " + info.getMessageType() );
		System.out.println( "메시지 내용     : " + info.getMessageInfo() );
		System.out.println( "---------------------------" );
	}
	
	/*
	 * 메시지 발송 이력 조회
	 * */
	@Test
	public void list_테스트() {
		
		MessageSendHistCnd cnd = new MessageSendHistCnd();
		cnd.setMessageType( MessageInfo.MESSAGE_TYPE_EMAIL );
		
		PageList<MessageSendHistInfo> list = messageSendHistBLO.list( cnd );
		
		//결과
		for( MessageSendHistInfo info : list ) {
			System.out.println( info );
		}
		
	}
	/*
	 * 메시지 발송 이력 조회
	 * */
	@Test
	public void listAll_테스트() {
		
		MessageSendHistCnd cnd =  new MessageSendHistCnd();
		
		List<MessageSendHistInfo> list =  messageSendHistBLO.listAll( cnd );
		
		list.forEach( System.out::println ); 
		
	}
	
	/*
	 * 메시지 발송 이력 삭제
	 * */
	@Test
	public void delete_테스트() {
		
		MessageSendHistCnd cnd = new MessageSendHistCnd();
		cnd.setOid( "1AD34c5" );
		
		int result = messageSendHistBLO.delete( cnd );
		
		String resultStr = ( 0 <= result ) ? "삭제 성공" : "삭제 실패"; 
		System.out.println( resultStr );
		
	}
	
}
