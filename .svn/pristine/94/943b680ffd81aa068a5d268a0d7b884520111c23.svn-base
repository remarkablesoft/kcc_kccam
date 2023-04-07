package com.remarkablesoft.framework.service.notification.template.model.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageCnd;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.template.model.impl.MessageTemplateBLO;
import com.remarkablesoft.framework.service.notification.message.template.vo.MessageTemplateInfo;
import com.remarkablesoft.framework.service.notification.message.template.vo.MessageTemplateItemInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	notification - template
 * 		@프로그램 ID		:	MessageTemplateBLOTest
 * 		@프로그램 개요		:	MessageTemplate BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 06. : 안병현 - 신규 생성( 테스트 RUN X )
 *      ============================================================================
 */

public class MessageTemplateBLOTest extends BaseModelTest {
	
	@Autowired
	MessageTemplateBLO mesageTemplateBLO;
	
	/*
	 * insert 테스트
	 * */
	@Test
	public void insert_테스트() {
		
		//템플릿 객체
		MessageTemplateInfo info = new MessageTemplateInfo();
		info.setOid( "1D2d5D2" );	//테스트를 할 수 없어서 임시로 부여한 oid
		info.setTargetOid( "1s234ed" ); //테스트를 할 수 없어서 임시로 부여한 oid
		info.setName( "메시지템플릿명" );
		info.setContents( "테스트" );
		info.setInputUser( "TedTest" );
		info.setInputDate( LocalDateTime.now() );
		
		//템플릿 변수 객체 리스트
		List<MessageTemplateItemInfo> list = new ArrayList<MessageTemplateItemInfo>();
		
		MessageTemplateItemInfo item = new MessageTemplateItemInfo();
		item.setMessageTemplateOid( info.getOid() );
		item.put( "이름", "홍길동" );
		MessageTemplateItemInfo item1 = new MessageTemplateItemInfo();
		item1.setMessageTemplateOid( info.getOid() );
		item1.put( "금액", "213123" );
		
		list.add( item );
		list.add( item1 );
		
		info.setTemplateItemList( list );
		
		info = mesageTemplateBLO.insert( info );
		
		System.out.println( "--------------------------------------" );
		System.out.println( " 템플릿 OID : " + info.getOid() );
		System.out.println( " 템플릿 이름 : " + info.getName() );
		System.out.println( " 템플릿 내용 : " + info.getContents() );
		System.out.println( "--------------------------------------" );
		
		
	}
	
	/*
	 * 템플릿의 속성 항목들을 저장하는 테스트
	 * */
	@Test
	public void 속성항목_저장_테스트() {
		
		//템플릿 변수 객체 리스트			
		List<MessageTemplateItemInfo> list = new ArrayList<MessageTemplateItemInfo>();
		
		MessageTemplateItemInfo item = new MessageTemplateItemInfo();
		item.setMessageTemplateOid( "23fd3D2F" );
		item.put( "테스트", "테스트" );
		
		list.add( item );
		
		mesageTemplateBLO.insertTemplateItem( list, "1D2d5D2" );
		
	}
	
	/*
	 * 수정 테스트
	 * */
	@Test
	public void update_테스트() {
		MessageTemplateInfo info = new MessageTemplateInfo();
		info.setOid( "1D2d5D2" );	//테스트를 할 수 없어서 임시로 부여한 oid
		info.setName( "수정된 메시지 템플릿 명" );
		info.setTemplateId( "AVCD" );
		info.setContents( "테스트" );
		
		info = mesageTemplateBLO.update( info );
		
		System.out.println( "--------------------------------------" );
		System.out.println( " 템플릿 OID : " + info.getOid() );
		System.out.println( " 템플릿 이름 : " + info.getName() );
		System.out.println( " 템플릿 내용 : " + info.getContents() );
		System.out.println( "--------------------------------------" );
	}
	
	/*
	 * get 테스트
	 * */
	@Test
	public void get_테스트() {
		
		MessageCnd cnd = new MessageCnd();
		cnd.setOid( "1D2d5D2" );
		MessageTemplateInfo info = mesageTemplateBLO.get( cnd );
					
		System.out.println( "--------------------------------------" );
		System.out.println( " 템플릿 OID : " + info.getOid() );
		System.out.println( " 템플릿 이름 : " + info.getName() );
		System.out.println( " 템플릿 내용 : " + info.getContents() );
		System.out.println( "--------------------------------------" );
		
	}
	
	/*
	 * list 테스트
	 * */
	@Test
	public void list_테스트() {
		
		MessageCnd cnd = new MessageCnd();
		cnd.setMsgTemplateOid( "1D2d5D2" );
		
		PageList<MessageTemplateInfo> list = mesageTemplateBLO.list( cnd );
		
		for( MessageTemplateInfo info : list ) {
			System.out.println( info );
		}
		
	}
	
	/*
	 * listAll 테스트
	 * */
	@Test
	public void listAll_테스트() {
		
		MessageCnd cnd =  new MessageCnd();
		
		List<MessageTemplateInfo> list =  mesageTemplateBLO.listAll( cnd );
		
		list.forEach( System.out::println ); 
		
	}
	
	/*
	 * 삭제 테스트
	 * */
	@Test
	public void delete_테스트() {
		
		MessageCnd cnd = new MessageCnd();
		cnd.setMsgTemplateOid( "1D2d5D2" );
		
		int result = mesageTemplateBLO.delete( cnd );
		
		String resultStr = ( 0 <= result ) ? "삭제 성공" : "삭제 실패";
		System.out.println( resultStr );
	}
	
	/*
	 * 템플릿 아이디와 템플릿 변수 항목 리스트로 템플릿 내용을 만듭니다.
	 * */
	@Test
	public void 템플릿_생성_테스트() {
		
		//템플릿 변수 객체 리스트
		List<MessageTemplateItemInfo> list = new ArrayList<MessageTemplateItemInfo>();
				
		MessageTemplateItemInfo item = new MessageTemplateItemInfo();
		item.setMessageTemplateOid( OIDGenerator.generateOID() );
		item.put( "이름", "홍길동" );
		MessageTemplateItemInfo item1 = new MessageTemplateItemInfo();
		item1.setMessageTemplateOid( OIDGenerator.generateOID() );
		item1.put( "금액", "213123" );
				
		list.add( item );
		list.add( item1 );
				
		//템플릿 생성		
		String template = mesageTemplateBLO.makeTemplateContentsById( "AVCD", list );
		System.out.println( template );
	}
	
	/*
	 * 메세지에 설정된 템플릿 정보를 채워주는 테스트
	 * */
	@Test
	public void 메시지_템플릿_생성_테스트() {
		
		//매시지 검색
		MessageCnd cnd = new MessageCnd();
		cnd.setMsgTemplateOid( "1D2d5D2" );
		
		//User 객체
		UserInfo user = new UserInfo();
		user.setOid( OIDGenerator.generateOID() );
		//메시지 객체
		MessageInfo message = new MessageInfo();
		message.setOid( "2ce35tv" );  //테스트를 할 수 없어서 임시로 부여한 oid
		message.setMsgTemplateOid( "1D2d5D2" );
		message.setMessageTemplateInfo( mesageTemplateBLO.get( cnd ) );
		
		mesageTemplateBLO.fillTemplateMessage( message, user );
	}
	
	
	/*
	 * 메세지에 템플릿 아이템을 채우는 테스트
	 * */
	@Test
	public void 메시지_템플릿_검색_테스트() {
		
		MessageTemplateInfo info = new MessageTemplateInfo();
		info.setOid( "1D2d5D2" );
		
		for( MessageTemplateItemInfo tem : info.getTemplateItemList() ) {
			System.out.println( tem );
		}
		
		
		
	}
}
