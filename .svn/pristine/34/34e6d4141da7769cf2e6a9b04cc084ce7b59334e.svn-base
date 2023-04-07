package com.remarkablesoft.framework.web.module.notification.kakao.impl.bizmsg;


import com.remarkablesoft.framework.exception.BLORuntimeException;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.util.StringUtils;

/**
 *
 * 		@주시스템			:	Raps
 * 		@서브 시스템		:	SMS
 * 		@프로그램 ID		:	BizMsgConfigBuilder.java
 * 		@프로그램 개요 		:	KakaoConfig를 만들기 위한 Builder 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 6. 8.	:	james	-	신규생성
 * 		============================================================================
 */
public class BizMsgConfigBuilder {

		private String senderId;					// 발신자 아이디
		private String authKey;						// 발신자 인증키
		private String msg;							// 메세지
		private String messageType;					// 메시지 타입 ( "AT"(알림톡) 또는 "FT"(친구톡) )
		private String tmplId;						// 템플릿 코드( 알림톡의 경우 필수입력 )

		private String recieverTel;					// 수신자 전화번호

		//메시지예약발송을위한시간 값(yyyyMMddHHmmss) (즉시전송:00000000000000,예약전송: 20180601000000)
		private String reserveDate;		// 예약 일자 yyyyMMdd
		private String reserveTime;		// 예약 시간 HHmmss
		private String testModeFlag = SystemConstants.FLAG_YES;		// 테스트 모드 Y,N

		
	    public static BizMsgConfigBuilder create() {
	            return new BizMsgConfigBuilder();
	    }

	    BizMsgConfigBuilder() {
	    }


		public String getTmplId() {
			return tmplId;
		}

		public BizMsgConfigBuilder setTmplId(String tmplId) {
			this.tmplId = tmplId;
			return this;
		}

		public String getMessageType() {
			return messageType;
		}

		public BizMsgConfigBuilder setMessageType(String messageType) {
			this.messageType = messageType;
			return this;
		}

		public BizMsgConfigBuilder setSenderId( String senderId ) {
				this.senderId = senderId;
				return this;
		}

		public BizMsgConfigBuilder setAuthKey( String authKey ) {
				this.authKey = authKey;
				return this;
		}


		public BizMsgConfigBuilder setMsg( String msg ) {
				this.msg = msg;
				return this;
		}



		public BizMsgConfigBuilder setRecieverTel( String recieverTel ) {
				this.recieverTel = recieverTel;
				return this;
		}


		/**
		 * yyyyMMdd
		 */
		public BizMsgConfigBuilder setReserveDate( String reserveDate ) {
				this.reserveDate = reserveDate;
				return this;
		}

		/**
		 * HHmmss
		 */
		public BizMsgConfigBuilder setReserveTime( String reserveTime ) {
				this.reserveTime = reserveTime;
				return this;
		}



		public BizMsgConfigBuilder setTestModeFlag( String testModeFlag ) {
				this.testModeFlag = testModeFlag;
				return this;
		}

		public BizMsgConfig build() {

				if ( StringUtils.isEmpty( this.senderId )) {

						throw new BLORuntimeException( "발신자 아이디를 채워주세요!![senderId]" );
				}
				else if ( StringUtils.isEmpty( this.authKey )) {

						throw new BLORuntimeException( "발신자의 인증키를 채워주세요!![authKey]" );
				}
				else if ( StringUtils.isEmpty( this.recieverTel )) {

						throw new BLORuntimeException( "발신자 전화번호를 채워주세요!![senderTel]" );
				}
				else if ( this.recieverTel.length() < 5) {

						throw new BLORuntimeException( "발신자 전화번호 자리수를 확인해주세요!![senderTel]" );
				}
				else if ( StringUtils.isEmpty( this.msg )) {

						throw new BLORuntimeException( "메세지를 채워주세요!![senderTel]" );
				}


				BizMsgConfig info = new BizMsgConfig();
				info.setSenderId( this.senderId );
				info.setAuthKey( this.authKey );
				info.setMsg( this.msg );

				// 전화번호는 82로 시작해야함
				String firstChar = this.recieverTel.substring( 0, 3 );
				if( "010".equals( firstChar ) || "011".equals( firstChar ) || "016".equals( firstChar ) || "017".equals( firstChar )  || "018".equals( firstChar ) ) {
						this.recieverTel = "82" +  this.recieverTel.substring( 1, this.recieverTel.length() );
				}

				info.setReceiverTel( this.recieverTel );
				info.setReserveDate( this.reserveDate );
				info.setReserveTime( this.reserveTime );
				info.setTestModeFlag( this.testModeFlag );
				info.setMessageType( this.messageType );
				info.setTmplId( this.tmplId );

				return info;
		}


}
