package com.remarkablesoft.framework.web.module.notification.sms.impl.aligo;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.model.impl.MessageSendHistBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.DateUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.notification.MessageSendProvider;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module - notification - sms
 * 		@프로그램 ID		:	AligoSendManagerImpl
 * 		@프로그램 개요 	:	Aligo를 통한 SMS 발신 연계
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 5. 24.	:	이균환	-	문자 연계는 알리고(https://smartsms.aligo.in)
 *							   -  API 연동 안내 https://smartsms.aligo.in/admin/api/example.html
 *		1.1  2020. 1. 09	:	최원준	-	SmsSendManager 인터페이스를 활용하여 타 서비스로 SMS 발신을 구현하는 방식으로 변경
 * 		============================================================================
 */

@BLO
public class AligoSendProvider implements MessageSendProvider {


		protected static String ENCODING_TYPE = "UTF-8";
		protected static String BOUNDARY = "____boundary____";
		protected static String SMS_URL = "https://apis.aligo.in/send/"; // 전송요청 URL
		//final String sms_url = "https://apis.aligo.in/send_mass/";  대용량 전송요청 URL
		
		protected static String API_SENDER_ID = "";
		protected static String API_KEY = "";
		protected static String API_SEND_PHONE = "";

		@Autowired
		MessageSendHistBLO messageSendHistBLO;
		
		public void send( MessageInfo message, UserInfo user ) {

				// 발신 메세지와 메세지 설정 정보를 가지고 Aligo에서 SMS를 발신할 수 있도록 빌드합니다.
				String reserveDate = "";
				String reserveTime = "";
				if( MessageInfo.MESSAGE_SEND_TYPE_FLAG_BOOK.equals( message.getSendTypeFlag() ) ) {
						reserveDate = DateUtils.formatDate( message.getMessageBookingInfo().getBookingDay(), DateUtils.DF_YYYYMMDD );
						reserveTime = DateUtils.formatTime( message.getMessageBookingInfo().getBookingTime(), DateUtils.TF_HHmmss );
				}

				AligoConfigBuilder builder = AligoConfigBuilder.create();
				AligoConfig config = builder
										.setSenderId( API_SENDER_ID )
										.setAuthKey( API_KEY )
										.setReceiver( user.getPhone() )
										.setMsg( message.getContents() )
										.setTitle( message.getName() )
										.setSenderTel( API_SEND_PHONE )
										.setReserveDate( reserveDate )
										.setReserveTime( reserveTime )
										.build();
				
				String sendResult = sendSMS( config );
				messageSendHistBLO.insert( message, user, sendResult, MessageInfo.MESSAGE_TYPE_SMS );
//				return sendSMS( config );
		}

		private String sendSMS( AligoConfig config ) {

				String result = "";

				CloseableHttpClient client = HttpClients.createDefault();
				HttpPost httpPost = new HttpPost(SMS_URL);

				try  {

						/**************** 문자전송하기 예제 ******************/
						/* "result_code":결과코드,"message":결과문구, */
						/* "msg_id":메세지ID,"error_cnt":에러갯수,"success_cnt":성공갯수 */
						/* 동일내용 > 전송용 입니다.
						/******************** 인증정보 ********************/


						Map<String, String> sms = new HashMap<String, String>();

						sms.put("user_id", config.getSenderId()); // SMS 아이디
						sms.put("key", config.getAuthKey()); //인증키

						/******************** 인증정보 ********************/

						/******************** 전송정보 ********************/
						sms.put("receiver", config.getReceiver()); // 수신번호
//						sms.put("destination", "01111111111|담당자,01111111112|홍길동"); // 수신인 %고객명% 치환
						sms.put("sender", config.getSenderTel() ); // 발신번호

						if ( StringUtils.hasText( config.getReserveDate() )) {
								sms.put("rdate", config.getReserveDate()); // 예약일자 - 20161004 : 2016-10-04일기준
						}
						if ( StringUtils.hasText( config.getReserveTime() )) {
								sms.put("rtime", config.getReserveTime()); // 예약시간 - 1930 : 오후 7시30분
						}

						sms.put("testmode_yn", config.getTestModeFlag()); // Y 인경우 실제문자 전송X , 자동취소(환불) 처리

						sms.put("msg", config.getMsg()); // 메세지 내용
						sms.put("title", config.getTitle()); //  LMS, MMS 제목 (미입력시 본문중 44Byte 또는 엔터 구분자 첫라인)

						String image = "";
						//image = "/tmp/pic_57f358af08cf7_sms_.jpg"; // MMS 이미지 파일 위치

						/******************** 전송정보 ********************/

						MultipartEntityBuilder builder = MultipartEntityBuilder.create();

						builder.setBoundary(BOUNDARY);
						builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
						builder.setCharset(Charset.forName(ENCODING_TYPE));

						for(Iterator<String> i = sms.keySet().iterator(); i.hasNext();){
							String key = i.next();
							builder.addTextBody(key, sms.get(key)
									, ContentType.create("Multipart/related", ENCODING_TYPE));
						}

						File imageFile = new File(image);
						if(image!=null && image.length()>0 && imageFile.exists()){

							builder.addPart("image",
									new FileBody(imageFile, ContentType.create("application/octet-stream"),
											URLEncoder.encode(imageFile.getName(), ENCODING_TYPE)));
						}

						HttpEntity entity = builder.build();
						httpPost.setEntity(entity);

						HttpResponse res = client.execute(httpPost);

						if(res != null){
							BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), ENCODING_TYPE));
							String buffer = null;
							while((buffer = in.readLine())!=null){
								result += buffer;
							}
							in.close();
						}

						return result;

					}
					catch(Exception e){
							e.printStackTrace();
					}
    				finally {
    						// Release the connection.
    						httpPost.releaseConnection();

    				}

				return result;

		}

}
