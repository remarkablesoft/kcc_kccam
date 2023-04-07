package com.remarkablesoft.framework.web.module.notification.push.old;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.remarkablesoft.framework.exception.BLORuntimeException;
import com.remarkablesoft.framework.module.log.CommonLoggerFactory;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.app.device.vo.DeviceInfo;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistInfo;
import com.remarkablesoft.framework.service.storage.file.model.impl.FileBLO;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.storage.StorageUtils;
import com.remarkablesoft.framework.web.util.WebUtils;

import javapns.Push;
import javapns.notification.PushNotificationBigPayload;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module-notification-push
 * 		@프로그램 ID		:	IndroidPushManager
 * 		@프로그램 개요 		:	iOS PUSH 발송 기능
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 10. 7.	:	choi	-	주석 추가
 * 		1.1  2019. 12. 09.	:	james   -   pushMessage대신 message사용하도록 변경
 * 		============================================================================
 */
@Service
public class IosPushManager {

	private static final Logger logger = CommonLoggerFactory.getLogger( "PUSHSend" );

	public static Map<String, byte[]> fileCache = new HashMap<>();

	@Autowired
	protected FileBLO fileBLO ;
	
	public static String IOS_FILE_OID = "";
	public static String IOS_FILE_CERT_PWD = "";
	
	public static String filePath ;
	


	/**
	 * 해당 기기에 푸시메시지를 보냅니다.
	 * @param info
	 * @param pushToken
	 * @param customRootMap 기본 포맷에서 추가로 보내고 싶은 파라미터 맵입니다.
	 */
	public String sendPush( MessageInfo info, DeviceInfo deviceInfo ) throws Exception {

		String errorMsg = "";

		Map<String, String> alert = new HashMap<>();
		alert.put( "title",  info.getName() );
		alert.put( "body", info.getContents() );

		Map<String, Object> map = new HashMap<>();

		//map.put( "badge", 1 );
		map.put( "sound", "default" );
		map.put( "alert", alert );
		map.put( "content-available", 1 );

		Map<String, Object> apns = new HashMap<String, Object>();
		apns.put( "aps", map );

		String json = WebUtils.GSON.toJson( apns );
		logger.debug( json );

		PushNotificationPayload payload = PushNotificationBigPayload.fromJSON( json );

		logger.debug( "payload - " + payload );
		logger.debug( "push token" + deviceInfo.getPushToken() );

		/* 푸시 인증서를 db에서 가져옵니다. */
		
		FileInfo file = fileBLO.get( IOS_FILE_OID );
		if ( file != null ) {
				filePath = StorageUtils.getStorageFilePath( file.getStorageFileUid() ) ;
				new BLORuntimeException( "Push 인증서를 찾을 수 없습니다.");
		}

		byte[] bytes = getPushCertificate( filePath );

		try {

			// 푸시 오류가 있을때에만 DB에 저장
			List<PushedNotification> notifications =  Push.payload( payload, bytes, IOS_FILE_CERT_PWD, false, deviceInfo.getPushToken() );

			for ( PushedNotification noti : notifications ) {

				if ( noti.isSuccessful() ) {
					logger.debug( "아이폰 푸시 전송 성공 " );
				}
				else {
					errorMsg = noti.getException().getMessage();
					logger.debug( " 아이폰 푸시 전송 실패 : " + errorMsg );

					MessageSendHistInfo hist = SystemFactory.getMessageSendHistInfo();
					hist.setReceiverUuid( deviceInfo.getUuid() );
					hist.setReceiverOid( deviceInfo.getUserOid() );
					hist.setMessageOid( info.getOid() );
					hist.setSuccessYn( SystemConstants.FLAG_NO );
					hist.setErrorMessage( errorMsg );

//					messageSendHistService.insert( hist);
				}

			}
		}
		catch( Exception e ) {
			if( !StringUtils.isEmpty( e.getMessage() ) ) {
				errorMsg = e.getMessage();
				logger.debug( " 아이폰 푸시 전송 실패 : " + errorMsg );
			}
		}

		return errorMsg;
	}

	/**
	 * IOS 인증서에서 푸시 인증서를 가져오는 부분 캐쉬처리
	 *
	 * @param storageFileUrl
	 * @return
	 * @throws Exception
	 */
	public byte[] getPushCertificate( String storageFileUrl) throws Exception {

			byte[] bytes = null;
			if ( fileCache.containsKey( storageFileUrl )) {
				bytes = fileCache.get( storageFileUrl );
			}
			else {
				bytes = IOUtils.toByteArray( new FileInputStream( storageFileUrl ));
				fileCache.put( storageFileUrl , bytes);
			}

			return bytes;
	}



}
