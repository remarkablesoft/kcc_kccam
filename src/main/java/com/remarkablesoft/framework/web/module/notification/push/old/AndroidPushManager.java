package com.remarkablesoft.framework.web.module.notification.push.old;

import com.remarkablesoft.framework.module.log.CommonLoggerFactory;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.app.device.vo.DeviceInfo;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistInfo;
import com.remarkablesoft.framework.util.ApplicationPropertiesUtils;
import com.remarkablesoft.framework.util.HttpClientUtils;
import com.remarkablesoft.framework.util.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module-notification-push
 * 		@프로그램 ID		:	AndroidPushManager
 * 		@프로그램 개요 	:	안드로이드 OS PUSH 발송 기능
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 10. 7.	:	choi	-	주석 추가
 * 		1.1  2019. 12. 09.	:	james   -   pushMessage대신 message사용하도록 변경
 * 		============================================================================
 */
@Service
public class AndroidPushManager {

	private static final Logger logger = CommonLoggerFactory.getLogger( "AndroidPushUtil" );


	public String sendPush( String sendKey, MessageInfo info, DeviceInfo deviceInfo ) throws Exception {

		Map<String, String> header = new HashMap<>();

		header.put( "Content-Type", "application/json" );
//		header.put( "Authorization", "key=" + getServerKey( info.getDomain() ) );
//		header.put( "Authorization", "key=AAAAWxZ8uG8:APA91bHug1MkHBlcB-IhXRyNMo3bs4T1AqC-vXwrLqy02pprxSRN_ztALN1SsjOlS4xtHgcOndKXZTm9xpBRhgJcwpJjvlmLZgxwjh9iOlK-QjtZ-HUutJnyWjTFWDb_x5WprSqShAEp" );

		String key = "";



		// 키가 있다면 해당 키를 그대로 쓰도록 처리
		if( StringUtils.isNotEmpty( sendKey ) ) {
			key = "key=" + sendKey; 			//인증키
		}
		else {
			key = "key=" + ApplicationPropertiesUtils.getValue( "push.key.android" ) ;
		}

		header.put( "Authorization", key );

		FcmMessage message = new FcmMessage();
		message.setTitle( info.getName() );
		message.setBody( info.getContents() );
		message.setToken( deviceInfo.getPushToken() );
		message.setLinkUrl( info.getLinkUrl1());

		String json = message.toJson();

		//결과 처리
		String result = HttpClientUtils.post( "https://fcm.googleapis.com/fcm/send", header, json );

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


		if ( StringUtils.isNotEmpty( errorMsg ) ) {
			errorMsg = " 안드로이드 푸시 전송 실패 - " + errorMsg;

			MessageSendHistInfo hist = SystemFactory.getMessageSendHistInfo();
			hist.setReceiverUuid( deviceInfo.getUuid() );
			hist.setReceiverOid( deviceInfo.getUserOid() );
			hist.setMessageOid( info.getOid() );
			hist.setSuccessYn( SystemConstants.FLAG_NO );
			hist.setErrorMessage( errorMsg );

//			messageSendHistService.insert( hist);

		}
		else {
			errorMsg = " 안드로이드 푸시 전송 성공 " + result;
		}

		if ( StringUtils.isNotEmpty( errorMsg ) ) {
			logger.debug( errorMsg );
		}

		return errorMsg;

	}


}
