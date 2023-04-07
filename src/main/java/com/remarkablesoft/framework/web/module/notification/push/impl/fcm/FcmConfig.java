package com.remarkablesoft.framework.web.module.notification.push.impl.fcm;

import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.util.StringUtils;
import org.json.simple.JSONObject;

/**
 * 		@주시스템			:	framework-web
 * 		@서브시스템		:	module - notification - push
 * 		@프로그램ID		:	FcmConfig
 * 		@프로그램개요 	:	PUSH 발송 JSON생성
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 5. 08.	:	이균환	-	 안드로이드 FCM용 메세지 객체 JSON을 만들때 사용
 * 		1.1  2020. 1. 09.	:	최원준	-	OS 구분없이 FCM으로 PUSH 발송하면서 인터페이를 통해 FcmConfig를 설정하여 PUSH 발송되도록 변경
 * 		============================================================================
 */
public class FcmConfig  {

		public final static String MSG_TYPE_ONE_TOON = "ONETOONE";

		private int badge;
		private String title;			// 푸시 제목
		private String body;
		private String message;
		private double seqPushMessage;
		
		private String token;
		private String linkUrl1;
		private String linkUrl2;
		private String linkUrl3;
		private String msgType;

		private String sendKey;
		private String osType; 
		
		// for iOS Push
		private String priority;
		private String sound;

		public int getBadge() {
				return badge;
		}
		
		public void setBadge( int badge ) {
				this.badge = badge;
		}

		public String getTitle() {
				return title;
		}

		public void setTitle(String title) {
				this.title = title;
		}

		public String getBody() {
				return body;
		}

		public void setBody(String body) {
				this.body = body;
		}

		public String getMessage() {
				return message;
		}
		
		public void setMessage( String message ) {
				this.message = message;
		}
		
		public double getSeqPushMessage() {
				return seqPushMessage;
		}
		
		public void setSeqPushMessage( double seqPushMessage ) {
				this.seqPushMessage = seqPushMessage;
		}

		public String getToken() {
				return token;
		}

		public void setToken(String token) {
				this.token = token;
		}

		public String getMsgType() {
				return msgType;
		}

		public void setMsgType(String msgType) {
				this.msgType = msgType;
		}

		public String getLinkUrl1() {
				return linkUrl1;
		}

		public void setLinkUrl1(String linkUrl1) {
				this.linkUrl1 = linkUrl1;
		}
		
		public String getLinkUrl2() {
				return linkUrl2;
		}
		
		public void setLinkUrl2( String linkUrl2 ) {
				this.linkUrl2 = linkUrl2;
		}
		
		public String getLinkUrl3() {
				return linkUrl3;
		}
		
		public void setLinkUrl3( String linkUrl3 ) {
				this.linkUrl3 = linkUrl3;
		}

		public String getSendKey() {
				return sendKey;
		}

		public void setSendKey( String sendKey ) {
				this.sendKey = sendKey;
		}

		public String getOsType() {
				return osType;
		}
		
		public void setOsType( String osType ) {
				this.osType = osType;
		}
		
		public String getPriority() {
				return priority;
		}
		
		public void setPriority( String priority ) {
				this.priority = priority;
		}
		
		public String getSound() {
				return sound;
		}
		
		public void setSound( String sound ) {
				this.sound = sound;
		}

		@SuppressWarnings("unchecked")
		public String toJson() {

				JSONObject message = new JSONObject();
				JSONObject data = new JSONObject();
				
				if( SystemConstants.OS_ANDROID.getKey().equals( getOsType() ) ) {
						data.put( "badge", getBadge() );
						data.put("title", getTitle());
						data.put("body", getBody());
						data.put( "message", getMessage() );
						
						data.put("linkUrl", getLinkUrl1());
//						data.put("linkUrl2", getLinkUrl2());
//						data.put("linkUrl3", getLinkUrl3());
						data.put("msgType", getMsgType());
						
						message.put( "to", getToken() );
						message.put("data", data );
				}
				// iOS
				else {
						JSONObject notification = new JSONObject();
						
						notification.put( "title", getTitle() );
						notification.put( "body", getBody() );
						notification.put( "sound", getSound() );
						
						data.put( "linkUrl", getLinkUrl1() );
						data.put( "msgType", getMsgType() );
						
						message.put( "to", getToken() );
						message.put( "priority", getPriority() );
						message.put( "notification", notification );
						message.put( "data", data );
				}

				return StringUtils.replace( message.toString(), "\\/", "/");
		}

}
