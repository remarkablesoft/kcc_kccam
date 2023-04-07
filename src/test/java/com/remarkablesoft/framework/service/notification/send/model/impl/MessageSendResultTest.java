package com.remarkablesoft.framework.service.notification.send.model.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.send.model.impl.MessageSendResultBLO;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistCnd;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendResultInfo;


public class MessageSendResultTest extends BaseModelTest {

	@Autowired
	MessageSendResultBLO messageSendResultBLO;
	
	/*
	 * INSERT 테스트: 메시지 발송 결과를 저장하는 테스트
	 * */
	@Test
	public void insert_테스트() {
		
		//Message insert 객체
		MessageSendResultInfo info = new MessageSendResultInfo();
		info.setOid( "1ASDe2d2" );	//테스트를 할 수 없어서 임시로 부여한 OID
		info.setMessageOid( "2D23da2" );  //테스트를 할 수 없어서 임시로 부여한 OID
		info.setSendTotalCount( 10 );
		info.setSendSuccessCount( 5 );
		info.setSendFailCount( 5 );
		info.setSendOpenCount( 5 );
		info.setInputUser( "TedTest" );
		info.setInputDate( LocalDateTime.now() );
		
		info = messageSendResultBLO.insert( info );
		
		System.out.println( "--------------------------------" );
		System.out.println(  );
		System.out.println( "--------------------------------" );
	}
	
	/*
	 * INSERT 테스트: 메시지와 성공실패 카운트로 메시지 발송 결과를 저장
	 * */
	@Test
	public void insert_count_테스트() {
		
	}
	
	/*
	 * UPDATE 테스트
	 * */
	@Test
	public void update_테스트() {
		
	}
	
	/*
	 * GET 테스트
	 * */
	@Test
	public void get_테스트() {
		
		MessageSendHistCnd cnd = new MessageSendHistCnd();
		cnd.setOid( "1ASDe2d2" );
		
		MessageSendResultInfo info = messageSendResultBLO.get( cnd );
		
		//결과
	}
	
	/*
	 * LIST 테스트
	 * */
	@Test
	public void list_테스트() {
		
		MessageSendHistCnd cnd = new MessageSendHistCnd();
		cnd.setOid( "1ASDe2d2" );
		PageList<MessageSendResultInfo> list = messageSendResultBLO.list( cnd );
		list.forEach( s -> System.out.println( s ) );
		
	}
	
	/*
	 * LISTALL 테스트
	 * */
	@Test
	public void listAll_테스트() {
		
	}
}
