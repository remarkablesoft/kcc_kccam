package com.remarkablesoft.framework.web.module.notification.email.impl;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.log.CommonLoggerFactory;
import com.remarkablesoft.framework.module.mail.MailManager;
import com.remarkablesoft.framework.module.mail.RemarkableEmail;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.model.impl.MessageSendHistBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.web.module.notification.MessageSendProvider;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module - notification - push
 * 		@프로그램 ID		:	FcmSendManagerImpl
 * 		@프로그램 개요 	:	안드로이드,IOS PUSH 발송 기능
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 11. 12.	:	hong	-	생성
 * 		============================================================================
 */

@BLO
@Primary
public class EmailSendProvider implements MessageSendProvider  {

		protected static final Logger logger = CommonLoggerFactory.getLogger( "EmailSend" );
	
		@Autowired
		protected MessageSendHistBLO messageSendHistBLO;
				
		
		@Override
		public void send( MessageInfo message, UserInfo user ) {

			
				String sendResult = "";
				
				try {
					
					sendResult = sendEmail(message, user);
					
				} 
				catch (Exception e) {

					e.printStackTrace();
				}
				
				messageSendHistBLO.insert( message, user, sendResult, MessageInfo.MESSAGE_TYPE_EMAIL );

		}
		
		public String sendEmail( MessageInfo message, UserInfo user ) throws Exception{

				RemarkableEmail mail = new RemarkableEmail();
				mail.addTo( user.getEmail(), user.getName() );
				
				mail.setEmailAddress( user.getEmail() );
				mail.setSubject( message.getMessageTemplateInfo().getName() );
				mail.setUserOid( "system" );
	
				
				mail.setTemplatePath( "templates/emailTemplate.html"  );
	//			mail.setLogo( "" );				
	//			mail.setHtmlMsg( "내용!!!!!!!!!!");
				mail.setInnerText( message.getMessageTemplateInfo().getContents() );
				
				mail = MailManager.makeEmailTemplate( mail );
				
				boolean result = MailManager.send( mail );			
				
				return result ? "성공" : "실패" ;
		}



}
