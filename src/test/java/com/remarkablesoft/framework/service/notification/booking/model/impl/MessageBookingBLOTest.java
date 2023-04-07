package com.remarkablesoft.framework.service.notification.booking.model.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.booking.model.impl.MessageBookingBLO;
import com.remarkablesoft.framework.service.notification.message.booking.vo.MessageBookingCnd;
import com.remarkablesoft.framework.service.notification.message.booking.vo.MessageBookingInfo;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	notification - booking
 * 		@프로그램 ID		:	MessageBookingBLOTest
 * 		@프로그램 개요		:	MessageBooking BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 06. : 안병현 - 신규 생성( 테스트 RUN X )
 *      ============================================================================
 */

/*
 * 1. 수정 필요 사항
 * - DB: TB_MSG_BOOKING에 PORT_OID 없음
 * */
public class MessageBookingBLOTest extends BaseModelTest{

	@Autowired
	MessageBookingBLO messageBookingBLO;
	
	/*
	 * 메세지 예약 정보를 입력하는 메소드 테스트 
	 * */
	@Test
	public void insert_테스트(){
		
		//메시지 예약 정보 객체
		MessageBookingInfo info = new MessageBookingInfo();
		
		info.setOid( "1SD2f98" );	//코드 작성만 가능해서 임시로 부여한 OID
		info.setMessageOid( OIDGenerator.generateOID() );
		info.setBookingDay( LocalDate.now() );
		info.setProcessTypeFlag( MessageBookingInfo.PROCESS_TYPE_FLAG_WAIT );
		info.setInputUser( "TedTest" );
		info.setInputDate( LocalDateTime.now());
		
		info = messageBookingBLO.insert( info );
		
		System.out.println( "--------------------------------" );
		System.out.println( "메시지 예약 OID : " + info.getOid() );
		System.out.println( "메시지 OID : " + info.getMessageOid() );
		System.out.println( "프로세트 상태 : " + info.getProcessTypeFlag() );
		System.out.println( "--------------------------------" );
		
	}
	
	/*
	 * 메세지 객체에서 예약 정보를 저장하는 메소드
	 * */
	@Test
	public void bookingMessage_테스트(){
		
		//메시지 예약 정보 객체
		MessageBookingInfo info = new MessageBookingInfo();
		info.setOid( "2s0Ke2D" );	//코드 작성만 가능해서 임시로 부여한 OID
		
		info.setBookingDay( LocalDate.now() );
		info.setInputUser( "TedTest" );
		info.setInputDate( LocalDateTime.now() );
				
				
		//MESSAGE 객체
		MessageInfo message = new MessageInfo();
		message.setOid( "1se32Cd" ); //코드 작성만 가능해서 임시로 부여한 OID
		message.setMsgBookingOid( "2s0Ke2D" );
		message.setContents( "테스트테스트TEST" );
		message.setSendTypeFlag( MessageInfo.MESSAGE_SEND_TYPE_FLAG_BOOK );
		message.setMsgTypeMode( MessageInfo.MESSAGE_TYPE_MODE_SMS );
		message.setMessageBookingInfo( info );
		
		
		
		info = messageBookingBLO.bookingMessage( message );
		
		//결과
		System.out.println( "--------------------------------" );
		System.out.println( "메시지 예약 OID : " + info.getOid() );
		System.out.println( "메시지 OID : " + info.getMessageOid() );
		System.out.println( "프로세트 상태 : " + info.getProcessTypeFlag() );
		System.out.println( "--------------------------------" );
	}
	
	/*
	 * 메세지 예약 정보를 수정하는 메소드 테스트 
	 * */
	@Test
	public void update_테스트(){
		
		// 예약 정보 객체
		MessageBookingInfo info = new MessageBookingInfo();
		info.setOid( "2s0Ke2D" );
		info.setProcessTypeFlag( MessageBookingInfo.PROCESS_TYPE_FLAG_WAIT );
		info.setInputUser( "TESTTESTTEST" );
		info.setInputDate( LocalDateTime.now() );
		
		info = messageBookingBLO.update( info );
		
		//결과
		System.out.println( "--------------------------------" );
		System.out.println( "메시지 예약 OID : " + info.getOid() );
		System.out.println( "메시지 OID : " + info.getMessageOid() );
		System.out.println( "프로세트 상태 : " + info.getProcessTypeFlag() );
		System.out.println( "--------------------------------" );
	}
	
	/*
	 * 메세지 예약 정보를 가져오는 메소드 테스트
	 * */
	@Test
	public void get_테스트(){
		
		MessageBookingCnd cnd = new MessageBookingCnd();
		cnd.setOid( "2s0Ke2D" );
		MessageBookingInfo info = messageBookingBLO.get( cnd );
		
		//결과
		System.out.println( "--------------------------------" );
		System.out.println( "메시지 예약 OID : " + info.getOid() );
		System.out.println( "메시지 OID : " + info.getMessageOid() );
		System.out.println( "프로세트 상태 : " + info.getProcessTypeFlag() );
		System.out.println( "--------------------------------" );
	}
	
	/*
	 * 메세지 예약 정보 리스트를 가져오는 메소드 테스트
	 * */
	@Test
	public void list_테스트(){
		
		MessageBookingCnd cnd = new MessageBookingCnd();
		cnd.setProcessTypeFlag( MessageBookingInfo.PROCESS_TYPE_FLAG_WAIT );

		PageList<MessageBookingInfo> list = messageBookingBLO.list( cnd );
		
		//결과
		for( MessageBookingInfo info : list ) {
			System.out.println( info );
		}
		
	}
	
	/*
	 * 메세지 예약 정보 리스트를 모두 가져오는 메소드 테스트
	 * */
	@Test
	public void listAll_테스트(){
		
		MessageBookingCnd cnd =  new MessageBookingCnd();
		
		List<MessageBookingInfo> list =  messageBookingBLO.listAll( cnd );
		
		list.forEach( System.out::println ); 
		
	}
	
	/*
	 * 메세지 정보에 해당 메시지의 예약 정보를 찾아주는 메소드 테스트
	 * */
	@Test
	public void 메시지_예약_정보_찾기_테스트(){
		
		MessageInfo message = new MessageInfo();
		message.setOid( "1se32Cd" );
		message.setMsgBookingOid( "2s0Ke2D" );
		messageBookingBLO.fillBookingMessage( message );
		
		MessageBookingInfo info = message.getMessageBookingInfo();
		
		//결과
		System.out.println( "--------------------------------" );
		System.out.println( "메시지 예약 OID : " + info.getOid() );
		System.out.println( "메시지 OID : " + info.getMessageOid() );
		System.out.println( "프로세스 상태 : " + info.getProcessTypeFlag() );
		System.out.println( "--------------------------------" );
		
	}
}	

