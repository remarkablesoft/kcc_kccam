package com.remarkablesoft.framework.web.module.notification.sms.impl.aligo;

import com.remarkablesoft.framework.exception.BLORuntimeException;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.util.StringUtils;

/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module - notification - sms
 * 		@프로그램 ID		:	AligoConfigBuilder
 * 		@프로그램 개요 	:	AligoConfig를 만들기 위한 Builder 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 6. 8.	:	james		-	신규생성
 * 		============================================================================
 */
public class AligoConfigBuilder {

		private String senderId;					// 발신자 아이디
		private String authKey;						// 발신자 인증키
		private String receiver;					// 수신자
		private String msg;							// 메세지
		private String title;						// 제목

		private String senderTel;					// 발신자 전화번호
		private String reserveDate;					// 예약 일자
		private String reserveTime;					// 예약 시간
		private String testModeFlag = SystemConstants.FLAG_YES;		// 테스트 모드 Y,N



	    public static AligoConfigBuilder create() {
	            return new AligoConfigBuilder();
	    }

	    AligoConfigBuilder() {
	    }


		public AligoConfigBuilder setSenderId( String senderId ) {
				this.senderId = senderId;
				return this;
		}

		public AligoConfigBuilder setAuthKey( String authKey ) {
				this.authKey = authKey;
				return this;
		}

		public AligoConfigBuilder setReceiver( String receiver ) {
				this.receiver = receiver;
				return this;
		}

		public AligoConfigBuilder setMsg( String msg ) {
				this.msg = msg;
				return this;
		}

		public AligoConfigBuilder setTitle( String title ) {
				this.title = title;
				return this;
		}


		public AligoConfigBuilder setSenderTel( String senderTel ) {
				this.senderTel = senderTel;
				return this;
		}



		public AligoConfigBuilder setReserveDate( String reserveDate ) {
				this.reserveDate = reserveDate;
				return this;
		}


		public AligoConfigBuilder setReserveTime( String reserveTime ) {
				this.reserveTime = reserveTime;
				return this;
		}



		public AligoConfigBuilder setTestModeFlag( String testModeFlag ) {
				this.testModeFlag = testModeFlag;
				return this;
		}

		public AligoConfig build() {

				if ( StringUtils.isEmpty( this.senderId )) {

						throw new BLORuntimeException( "발신자 아이디를 채워주세요!![senderId]" );
				}
				else if ( StringUtils.isEmpty( this.authKey )) {

						throw new BLORuntimeException( "발신자의 인증키를 채워주세요!![authKey]" );
				}
				else if ( StringUtils.isEmpty( this.senderTel )) {

						throw new BLORuntimeException( "발신자 전화번호를 채워주세요!![senderTel]" );
				}
				else if ( StringUtils.isEmpty( this.receiver )) {

						throw new BLORuntimeException( "수신자를 채워주세요!![receiver]" );
				}


				AligoConfig info = new AligoConfig();
				info.setSenderId( this.senderId );
				info.setAuthKey( this.authKey );
				info.setMsg( this.msg );
				info.setReceiver( this.receiver );

				String title = this.title;

				// 제목이 없을 경우 내용에서 10자를 잘라서 제목으로 만든다.
				if ( StringUtils.isEmpty(  title)) {

						if ( this.msg.length() > 10) {

								 title = this.msg.substring( 0, 10 ) ;
						}
				}


				info.setTitle( title );
				info.setSenderTel( this.senderTel );
				info.setReserveDate( this.reserveDate );
				info.setReserveTime( this.reserveTime );
				info.setTestModeFlag( this.testModeFlag );

				return info;
		}


}
