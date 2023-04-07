package com.remarkablesoft.framework.web.module.notification.sms.impl.infobank;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.util.ApplicationPropertiesUtils;
import com.remarkablesoft.framework.util.DateUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.notification.sms.impl.infobank.exception.SendFailException;
import com.remarkablesoft.framework.web.util.WebUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * 		@주시스템			:	Roltech
 * 		@서브 시스템		:	sms
 * 		@프로그램 ID		:	SmsSendBLO.java
 * 		@프로그램 개요 	:	Sms 서비스 모듈 구현체 - 인포뱅크용 : http://www.ibizplus.co.kr/technical/datacenter/rest
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 3. 7.	:	james	-	신규생성
 * 		============================================================================
 */
@BLO
public class SmsInfoBank {

		private static final Logger logger = LoggerFactory.getLogger( "SendSMS" );

		public static String TOKEN_URL = "https://auth.supersms.co:7000/auth/v3/token";
		private static String MESSAGE_URL = "https://sms.supersms.co:7020/sms/v3/multiple-destinations";
		private static String OTPCONTENT = ApplicationPropertiesUtils.getValue( "otp.content" );

		public static HashMap<String, String> tokenMap = new HashMap<>();


		public final static String SMS_TEL = "";

		public final static String TOKEN_ACCESSTOKEN = "accessToken";
		public final static String TOKEN_SCHEMA = "schema";
		public final static String TOKEN_EXPIRED = "expired";

		public String getToken() {

				// Create an instance of HttpClient.
				HttpClient client = new HttpClient();

				// Create a method instance.
				PostMethod method = new PostMethod( TOKEN_URL );

				String token = "";

				try {
						method.setRequestHeader( "Accept", "application/json" );
						method.setRequestHeader( "X-IB-Client-Id", "magnachip" );
						method.setRequestHeader( "X-IB-Client-Passwd", "QNVAU51G74163EIMDP79" );

						// Execute the method.
						int statusCode = client.executeMethod( method );

						if ( statusCode != HttpStatus.SC_OK ) {
								logger.error( "SSmsSendServiceMS 전송 실패 : " + method.getStatusLine() );
						}

						// Read the response body.
						byte[] responseBody = method.getResponseBody();

						// Deal with the response.
						// Use caution: ensure correct character encoding and is not binary data
						String result = new String( responseBody );

						tokenMap = WebUtils.GSON.fromJson( result, HashMap.class );

						logger.debug( "SMS 문자 값 : " + result );
						logger.debug( "토큰 값 : " + tokenMap.get( TOKEN_ACCESSTOKEN ) );
						logger.info( result );

				}
				catch ( HttpException e ) {
						logger.error( "SMS 전송 실패 : Fatal protocol violation: " + e.getMessage() );
				}
				catch ( IOException e ) {
						logger.error( "SMS 전송 실패 : Fatal transport error: " + e.getMessage() );
				}
				finally {
						// Release the connection.
						method.releaseConnection();
				}

				return token;
		}

		public void sendSms( SmsMessageInfo message ) throws SendFailException {

				// 맨처음 호출하는 것이라면 토큰이 생성되도록 체크
				if ( StringUtils.isEmpty( tokenMap.get( TOKEN_ACCESSTOKEN ) ) ) {
						getToken();
				}

				String current = DateUtils.getCurrentDate( DateUtils.DF_YYYYMMDDHHmmss );
				Long nCurrent = Long.parseLong( current );

				Long nExpired = Long.parseLong( tokenMap.get( TOKEN_EXPIRED ) );

				// 토큰의 만료일을 체크해서 지났다면 토큰을 다시 생성
				if ( nCurrent >= nExpired ) {
						getToken();
				}

				// Create an instance of HttpClient.
				CloseableHttpClient client = HttpClients.createDefault();

				// Create a method instance.
				HttpPost httpPost = new HttpPost( MESSAGE_URL );

				try {

						String authorization = "Basic " + tokenMap.get( TOKEN_ACCESSTOKEN );

						httpPost.setHeader( "Authorization", authorization );
						httpPost.setHeader( "Content-Type", "application/json" );
						httpPost.setHeader( "Accept", "application/json" );

						String msg = makeParam( message );
						StringEntity entity = new StringEntity( msg, HTTP.UTF_8 );

						logger.info( "json : " + entity );
						httpPost.setEntity( entity );

						// Execute the method.
						CloseableHttpResponse response = client.execute( httpPost );

						if ( response.getStatusLine().getStatusCode() != HttpStatus.SC_OK ) {
								logger.error( "SMS 전송 실패 : " + response.getStatusLine() );
						}

						String json = EntityUtils.toString( response.getEntity(), "UTF-8" );
						logger.info( json );

				}
				catch ( HttpException e ) {
						logger.error( "SMS 전송 실패 : Fatal protocol violation: " + e.getMessage() );
						throw new SendFailException( "SMS 전송 실패 : Fatal protocol violation: " + e.getMessage(), e );
				}
				catch ( IOException e ) {
						logger.error( "SMS 전송 실패 : Fatal transport error: " + e.getMessage() );
						throw new SendFailException( "SMS 전송 실패 : Fatal transport error: " + e.getMessage(), e );
				}
				finally {
						// Release the connection.
						httpPost.releaseConnection();

				}
		}

		/**
		 * 매그나칩반도체에 맞는 데이터를 json 형태로 보여준다.
		 * {"destinations":[{"to":"+8201040262279"}],"from":"01030672373","text":"[매그나칩반도체] 인증번호는 [77777]입니다. 정확히 입력해 주세요.","ttl":"60"}
		 *
		 * @param message
		 * @return
		 */
		public String makeParam( SmsMessageInfo message ) {

				HashMap<String, Object> map = new HashMap<>();

				HashMap<String, Object> map2 = new HashMap<>();
				String firstChar = message.getTel().substring( 0, 3 );
				if( "010".equals( firstChar ) || "011".equals( firstChar ) || "016".equals( firstChar ) || "017".equals( firstChar )  || "018".equals( firstChar ) ) {
						map2.put( "to", "+82" + message.getTel() );
				}
				else {
						map2.put( "to", "+" + message.getTel() );
				}

				map.put( "from", SMS_TEL );
//				map.put( "from", "01030672373" );
				map.put( "text", "[" + OTPCONTENT + "] OTP Number는 [" + message.getOtpCode() + "]입니다. 정확히 입력해 주세요." );
				map.put( "destinations", new HashMap[] { map2 } );
				map.put( "ttl", "60" );

				return WebUtils.GSON.toJson( map );
		}

}
