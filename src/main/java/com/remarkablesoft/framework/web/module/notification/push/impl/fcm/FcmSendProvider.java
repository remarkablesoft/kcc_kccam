package com.remarkablesoft.framework.web.module.notification.push.impl.fcm;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.log.CommonLoggerFactory;
import com.remarkablesoft.framework.service.app.device.model.impl.DeviceBLO;
import com.remarkablesoft.framework.service.app.device.vo.DeviceInfo;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.model.impl.MessageSendHistBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.HttpClientUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.notification.MessageSendProvider;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module - notification - push
 * 		@프로그램 ID		:	FcmSendManagerImpl
 * 		@프로그램 개요 	:	안드로이드,IOS PUSH 발송 기능
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 11. 12.	:	hong	-	생성
 * 		1.1  2019. 12. 09.	:	james   -   pushMessage대신 message사용하도록 변경
 * 		1.2  2020. 01. 09.	:	최원준	-	PushSendManager 인터페이스를 통해 Fcm구현체를 통해 PUSH를 발송하도록 변경
 * 		============================================================================
 */

@BLO
@Primary
public class FcmSendProvider implements MessageSendProvider  {

		protected static final Logger logger = CommonLoggerFactory.getLogger( "PUSHSend" );

		@Autowired
		protected DeviceBLO deviceBLO;
		
		@Autowired
		protected MessageSendHistBLO messageSendHistBLO;
		

		public void send( MessageInfo message, UserInfo user ) {

				if( CollectionUtils.isEmpty( user.getDeviceList() ) ){

						messageSendHistBLO.insert( message, user, "해당 유저의 Device가 존재하지 않습니다.", MessageInfo.MESSAGE_TYPE_PUSH);
				}
				
				DeviceInfo device = user.getDeviceList().get( 0 );
		
				FcmConfigBuilder builder = FcmConfigBuilder.create();
				FcmConfig config = builder
										.setBadge( 1 )
										.setTitle( message.getName() )
										.setBody( message.getContents() )
										.setMessage( "" )
										.setSeqPushMessage( 0.0 )
										.setToken( device.getPushToken() )
										.setLinkUrl1( message.getLinkUrl1() )
										.setMsgType( FcmConfig.MSG_TYPE_ONE_TOON )
										.setSendKey( getAPIKey( message) )
										.setOsType( device.getOsTypeFlag() )
										.setPriority( "high" )
										.setSound( "default" )
										.build();

				String sendResult = sendPush( config );
				messageSendHistBLO.insert( message, user, sendResult, MessageInfo.MESSAGE_TYPE_PUSH );
		}

		protected String sendPush( FcmConfig config ) {

				Map<String, String> header = new HashMap<>();
				header.put( "Content-Type", "application/json" );

				header.put( "Authorization", "key=" + config.getSendKey() );

				logger.info( "-    푸시전송객체    -" + config.toString() );
				String json = config.toJson();
				
				logger.info(  header + "");

				//결과 처리
				String result = "";
				try {
						result = HttpClientUtils.postWithException( "https://fcm.googleapis.com/fcm/send", header, json );
				}
				catch ( Exception e ) {
						e.printStackTrace();
						logger.error( "FCM Error : " + StringUtils.getPrintStackTrace( e ) );
						return e.getMessage();
				}

				// 에러 메세지가 더 있을수 있으니 넘어오는 json에서 에러 값을 추출하는게 맞을듯
				String errorMsg = "";
				if ( StringUtils.contains( result, "NotRegistered" ) ) {
						errorMsg = "NotRegistered";
				}
				else if ( StringUtils.contains( result, "InvalidRegistration" ) ) {
						errorMsg = "InvalidRegistration";
				}
				else if ( StringUtils.contains( result, "MismatchSenderId" ) ) {
						errorMsg = "MismatchSenderId";
				}
				else if ( StringUtils.contains( result, "InvalidApnsCredential" ) ) {
						errorMsg = "InvalidApnsCredential";
				}

				if ( StringUtils.isNotEmpty( errorMsg ) ) {
						logger.error( "Send Error : " + errorMsg );
				}

				return errorMsg;

		}
		

		/**
		 * 각 사이트의 FcmSendManagerImpl에서 해당 apikey를 호출해서 사용
		 * 
		 * @author james
		 * @param message 
		 * @return
		 */
		protected String getAPIKey(MessageInfo message) {
				
				return null;
		}

}
