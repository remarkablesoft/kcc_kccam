package com.remarkablesoft.framework.web.module.notification.push.impl.fcm;

import com.remarkablesoft.framework.exception.BLORuntimeException;
import com.remarkablesoft.framework.util.StringUtils;

/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module - notification - push
 * 		@프로그램 ID		:	FcmConfigBuilder
 * 		@프로그램 개요 	:	FcmConfig를 만들기 위한 Builder 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 1. 9.	:	최원준	-	신규생성
 * 		============================================================================
 */
public class FcmConfigBuilder {

		private int badge;
		private String title;						// PUSH 제목
		private String body;						// PUSH 내용
		private String message;
		private double seqPushMessage;
		
		private String token;					// PUSH TOKEN
		private String linkUrl1;				
		private String linkUrl2;
		private String linkUrl3;
		private String msgType;				
		
		private String sendKey;
		private String osType;
		
		// for iOS Push
		private String priority;
		private String sound;
		
	    public static FcmConfigBuilder create() {
	            return new FcmConfigBuilder();
	    }

	    FcmConfigBuilder() {
	    }

	    public FcmConfigBuilder setBadge( int badge ) {
	    		this.badge = badge;
	    		return this;
	    }
	    
		public FcmConfigBuilder setTitle( String title ) {
				this.title = title;
				return this;
		}

		public FcmConfigBuilder setBody( String body ) {
				this.body = body;
				return this;
		}
		
		public FcmConfigBuilder setMessage( String message ) {
				this.message = message;
				return this;
		}
		
		public FcmConfigBuilder setSeqPushMessage( double seqPushMessage ) {
				this.seqPushMessage = seqPushMessage;
				return this;
		}
		
		public FcmConfigBuilder setToken( String token ) {
				this.token = token;
				return this;
		}
		
		public FcmConfigBuilder setLinkUrl1( String linkUrl1 ) {
				this.linkUrl1 = linkUrl1;
				return this;
		}
		
		public FcmConfigBuilder setLinkUrl2( String linkUrl2 ) {
				this.linkUrl2 = linkUrl2;
				return this;
		}
		
		public FcmConfigBuilder setLinkUrl3( String linkUrl3 ) {
				this.linkUrl3 = linkUrl3;
				return this;
		}
		
		public FcmConfigBuilder setMsgType( String msgType ) {
				this.msgType = msgType;
				return this;
		}
		
		public FcmConfigBuilder setSendKey( String sendKey ) {
				this.sendKey = sendKey;
				return this;
		}

		public FcmConfigBuilder setOsType( String osType ) {
				this.osType = osType;
				return this;
		}
		
		public FcmConfigBuilder setPriority( String priority ) {
				this.priority = priority;
				return this;
		}
		
		public FcmConfigBuilder setSound( String sound ) {
				this.sound = sound;
				return this;
		}
		
		public FcmConfig build() {
				
//				if ( StringUtils.isEmpty( this.body )) {
//						throw new BLORuntimeException( "PUSH 내용을 채워주세요!![body]" );
//				}
				
				if ( StringUtils.isEmpty( this.token )) {
						throw new BLORuntimeException( "PUSH 토큰을 채워주세요!![token]" );
				}
				else if ( StringUtils.isEmpty( this.sendKey )) {
						throw new BLORuntimeException( "PUSH 키를 채워주세요!![sendKey]" );
				}
				
				FcmConfig config = new FcmConfig();
				
				config.setBadge( this.badge );
				config.setTitle( this.title );
				config.setBody( this.body );
				config.setMessage( this.message );
				config.setSeqPushMessage( this.seqPushMessage );

				config.setToken( this.token );
				config.setLinkUrl1( this.linkUrl1 );
				config.setLinkUrl2( this.linkUrl2 );
				config.setLinkUrl3( this.linkUrl3 );
				config.setMsgType( this.msgType );

				config.setSendKey( this.sendKey );
				config.setOsType( this.osType );
				config.setPriority( this.priority );
				config.setSound( this.sound );
				
				return config;
		}


}
