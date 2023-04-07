package com.remarkablesoft.framework.web.module.notification.kakao.impl.bizmsg;

import com.google.gson.Gson;
import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.model.impl.MessageSendHistBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.ApplicationPropertiesUtils;
import com.remarkablesoft.framework.util.BitSumUtils;
import com.remarkablesoft.framework.util.DateUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.notification.MessageSendProvider;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 		@주시스템			:	RAPS
 * 		@서브 시스템		:
 * 		@프로그램 ID		:	KaKaoUtils.java
 * 		@프로그램 개요 		:	카카오 연계
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 5. 24.	:	이균환	-	랩스 카카오 연계는 www.bizmsg.kr 것을 이용
 *							   - 연계방식은 알림톡과 친구톡이 있는데 알림톡은 광고는 안되고 전화번호만 알면 모두게 전송가능
 *                             - 친구톡은 광고가 가능하지만 해당 친구가 가입이 되어있어야 하고 저녁 8시부터 다음날 8시까지는 전송이 안됨.
 *                             - 자세한 룰 :  https://www.bizmsg.kr/collected_statics/assets_landing/doc/alimtalk_template_guide.pdf
 *      1.1  2020. 1. 09	:	최원준	-	KakaoSendManager 인터페이스를 활용하여 타 서비스로 카카오 발신을 구현하는 방식으로 변경
 * 		============================================================================
 */

@BLO
public class BizMsgSendProvider implements MessageSendProvider {

		public static String KAKAO_URL = ApplicationPropertiesUtils.getValue( "msg.kakao.friend.send.url" ); // 친구톡 URL
//		public static final String KAKAO_URL = "https://dev-alimtalk-api.bizmsg.kr:1443/v2/sender/send"; // 친구톡 개발 URL
//		public static final String KAKAO_URL = "https://alimtalk-api.bizmsg.kr/v2/sender/send"; // 친구톡 운영 URL

		public static String API_SEND_ID = "";
		public static String API_KEY = "";
		
		
		@Autowired
		protected MessageSendHistBLO messageSendHistBLO;
		
		
		
		public static final Gson GSON = new Gson();

		public void send( MessageInfo message, UserInfo user ) {

				// 발신 메세지와 메세지 설정 정보를 가지고 BizM에서 카카오톡을 발신할 수 있도록 빌드합니다.
				String reserveDate = BizMsgConfig.BIZMSG_DEFAULT_RESERVE_DATE;
				String reserveTime = BizMsgConfig.BIZMSG_DEFAULT_RESERVE_TIME;
				if( MessageInfo.MESSAGE_SEND_TYPE_FLAG_BOOK.equals( message.getSendTypeFlag() ) ) {
						reserveDate = DateUtils.formatDate( message.getMessageBookingInfo().getBookingDay(), DateUtils.DF_YYYYMMDD );
						reserveTime = DateUtils.formatTime( message.getMessageBookingInfo().getBookingTime(), DateUtils.TF_HHmmss );
				}
				
				
				String messageType = "";
				if ( BitSumUtils.getCheck( message.getMsgTypeMode(), MessageInfo.MESSAGE_TYPE_MODE_KAKAO_NOTI )) {
						messageType = "AT";
				}
				else if ( BitSumUtils.getCheck( message.getMsgTypeMode(), MessageInfo.MESSAGE_TYPE_MODE_KAKAO_FRIEND )) {
						messageType = "FT";
				} 

				BizMsgConfigBuilder builder = BizMsgConfigBuilder.create();
				BizMsgConfig config = builder
										.setSenderId( API_SEND_ID )
										.setAuthKey( API_KEY )
										.setMsg( message.getContents() )
										.setMessageType( messageType )
										.setTmplId( message.getMessageTemplateInfo().getTemplateId() )
										.setRecieverTel( user.getPhone() )
										.setReserveDate( reserveDate )
										.setReserveTime( reserveTime )
										.setTestModeFlag( SystemConstants.FLAG_NO )
										.build();

				String sendResult = sendTalk( config );
				
				messageType = "";
				if ( BitSumUtils.getCheck( message.getMsgTypeMode(), MessageInfo.MESSAGE_TYPE_MODE_KAKAO_NOTI )) {
						messageType = MessageInfo.MESSAGE_TYPE_NOTI ;
				}
				else if ( BitSumUtils.getCheck( message.getMsgTypeMode(), MessageInfo.MESSAGE_TYPE_MODE_KAKAO_FRIEND )) {
						messageType = MessageInfo.MESSAGE_TYPE_FRIEND ;
				}
				messageSendHistBLO.insert( message, user, sendResult, messageType );

		}

		private String sendTalk( BizMsgConfig config ) {

				String result = "";

				// Create an instance of HttpClient.
				CloseableHttpClient client = HttpClients.createDefault();

				// Create a method instance.
				HttpPost httpPost = new HttpPost( KAKAO_URL );

				try {

						httpPost.setHeader( "userid", config.getSenderId() );

						Map<String, Object> sms = new HashMap<String, Object>();

						/******************** 전송정보 ********************/
						sms.put("message_type", "ft");
						sms.put("phn",  config.getReceiverTel()); // 수신자 전화번호
						sms.put("profile", config.getAuthKey()); // 발신자 프로파일 키
						sms.put("msg", config.getMsg() );

						String reserveDt = "";

						if ( StringUtils.hasText( config.getReserveDate() ))  {
								reserveDt += 	config.getReserveDate();
						}
						if ( StringUtils.hasText( config.getReserveTime() ))  {
								reserveDt += 	config.getReserveTime();
						}

						if ( StringUtils.isEmpty(  reserveDt )) {
								reserveDt = "00000000000000";
						}

						sms.put("reserveDt", reserveDt );

						String msg = GSON.toJson( sms , HashMap.class) ;
						msg = "[" + msg + "]";

						StringEntity entity = new StringEntity( msg, "UTF-8" );
						entity.setContentType( "application/json" );

						httpPost.setEntity( entity );

						// Execute the method.
						CloseableHttpResponse response = client.execute( httpPost );

						if ( response.getStatusLine().getStatusCode() != HttpStatus.SC_OK ) {
								System.out.println( "카카오톡 전송 실패 : " + response.getStatusLine() );
						}

						String json = EntityUtils.toString( response.getEntity(), "UTF-8" );

						return json;

				}
				catch ( Exception e ) {
						e.printStackTrace();
				}
				finally {
						// Release the connection.
						httpPost.releaseConnection();

				}

				return result;
		}
}
