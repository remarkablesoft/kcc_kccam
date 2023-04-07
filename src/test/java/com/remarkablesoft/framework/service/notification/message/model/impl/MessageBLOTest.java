package com.remarkablesoft.framework.service.notification.message.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.service.SystemConstants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.booking.vo.MessageBookingInfo;
import com.remarkablesoft.framework.service.notification.message.message.model.impl.MessageBLO;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageCnd;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistInfo;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	notification - message
 * 		@프로그램 ID		:	MessageBLOTest
 * 		@프로그램 개요		:	Message BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 06. : 안병현 - 신규 생성( 테스트 RUN X )
 *      ============================================================================
 */

public class MessageBLOTest extends BaseModelTest {
	
	@Autowired
	MessageBLO messageBLO;

	/*
	 * INSERT 테스트
	 * */
	@Test
	public void insert_테스트() {
		
		//메시지 객체
		MessageInfo info = new MessageInfo();
		
		info.setSystemOid( OIDGenerator.generateOID() );
		info.setPartOid( OIDGenerator.generateOID() );
		info.setMsgTypeMode( MessageInfo.MESSAGE_TYPE_MODE_SMS );
		info.setClassType( "QWERTY" );
		
		info.setName( "name3" );
		info.setMsgTemplateOid( OIDGenerator.generateOID() );
		info.setMsgBookingOid( OIDGenerator.generateOID() );
		info.setSendTypeFlag( MessageInfo.MESSAGE_SEND_TYPE_FLAG_BOOK );
		info.setReplaceMsgYn( SystemConstants.FLAG_NO );
		
		info.setContents( "테스트테스트3" );
		info.setImageUrl2( "imageUrl2" );
		info.setLinkUrl2( "linkUrl2" );
		info.setInputUser( "TedTest2" );
		info.setCustomField2( "customField2" );
		
		//INSERT
		info = messageBLO.insert( info );
		
		//결과
		System.out.println( info.toString() );
		
	}
	
	/*
	 * update 테스트
	 * */
	@Test
	public void update_테스트() {
		
		MessageInfo info = new MessageInfo();
		info.setOid( "1SRDRyl6004" );
		info.setClassType( "ASDFGHJK" );
		info.setName( "name2" );
		
		info.setContents( "수정된테스트테스트" );
		info.setCustomField3( "테스트테슷흐" );
		
		//UPDATE
		info = messageBLO.update( info );
		
		//결과
		System.out.println( info.toString() );
	}
	
	/*
	 * get 테스트
	 * */
	@Test
	public void get_테스트() {
		
		MessageCnd cnd = new MessageCnd();
		cnd.setOid( "1SRDRyl6004" ); //테스트를 할 수 없어서 임시로 부여한 OID
		MessageInfo info = messageBLO.get( cnd );
		
		System.out.println( info.toString() );
	}
	
	/*
	 * list 테스트
	 * */
	@Test
	public void list_테스트() {
		
		MessageCnd cnd = new MessageCnd();
		cnd.setMsgTypeMode( MessageInfo.MESSAGE_TYPE_MODE_SMS );
		
		//리스트 검색
		PageList<MessageInfo> list = messageBLO.list( cnd );
		list.forEach( item -> System.out.println( item.toString() ) );
	}
	
	/*
	 * listAll 테스트
	 * */
	@Test
	public void listAll_테스트() {
		
		MessageCnd cnd =  new MessageCnd();
		
		List<MessageInfo> list =  messageBLO.listAll( cnd );
		
		list.forEach( System.out::println );
		
	}
	
	/*
	 * 메세지 이력에 메세지 정보를 채워주는 테스트
	 * */
	@Test
	public void 메시지이력_업데이트_테스트() {
		
		//메시지 이력 객체 생성 후 메시지 이력 객체리스트에 추가
		MessageSendHistInfo info = new MessageSendHistInfo();
		info.setOid( OIDGenerator.generateOID() );
		info.setMessageOid( "1S4fv56D" );
		
		List<MessageSendHistInfo> list = new ArrayList<MessageSendHistInfo>();
		list.add( info );
		
		//메시지 정보 검색
		messageBLO.fillMessage( list );
		
		//결과
		for( MessageSendHistInfo histInfo : list ) {
			System.out.println( histInfo.getMessageInfo() );
		}
	}
}
