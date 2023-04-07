package com.remarkablesoft.framework.web.module.notification.kakao.impl.bizmsg;

/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module - notification - kakao 
 * 		@프로그램 ID		:	BizMsgConfig
 * 		@프로그램 개요 	:	BizM을 통해 카카오 친구보내기시 필요한 정보객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 6. 8.	:	james	-	신규생성
 * 		============================================================================
 */
public class BizMsgConfig {

		public static final String BIZMSG_TYPE_NOTI = "AT";			// 알림톡 
		public static final String BIZMSG_TYPE_FRIEND = "FT";		// 친구톡
		
		public static final String BIZMSG_DEFAULT_RESERVE_DATE = "00000000";
		public static final String BIZMSG_DEFAULT_RESERVE_TIME = "000000";
		
		private String senderId;		// 발신자 아이디
		private String authKey;			// 발신자 인증키

		private String receiverTel;		// 수신자 전화번호
		private String msg;				// 메세지

		private String messageType;		//메시지 타입 ( "AT"(알림톡) 또는 "FT"(친구톡) )
		private String tmplId;			//템플릿 코드( 알림톡의 경우 필수입력 )

		//메시지예약발송을위한시간 값(yyyyMMddHHmmss) (즉시전송:00000000000000,예약전송: 20180601000000)
		/**
		 * yyyyMMdd
		 */
		private String reserveDate;		// 예약 일자

		/**
		 * HHmmss
		 */
		private String reserveTime;		// 예약 시간

		private String testModeFlag;	// 테스트 모드

		public String getTmplId() {
			return tmplId;
		}

		public BizMsgConfig setTmplId(String tmplId) {
			this.tmplId = tmplId;
			return this;
		}

		public String getMessageType() {
			return messageType;
		}

		public BizMsgConfig setMessageType(String messageType) {
			this.messageType = messageType;
			return this;
		}

		public String getSenderId() {
				return senderId;
		}

		public String getAuthKey() {
				return authKey;
		}


		public String getMsg() {
				return msg;
		}


		public void setSenderId( String senderId ) {
				this.senderId = senderId;
		}

		public void setAuthKey( String authKey ) {
				this.authKey = authKey;
		}


		public void setMsg( String msg ) {
				this.msg = msg;
		}

		public String getReceiverTel() {
				return receiverTel;
		}


		public void setReceiverTel( String senderTel ) {
				this.receiverTel = senderTel;
		}


		public String getReserveDate() {
				return reserveDate;
		}


		public String getReserveTime() {
				return reserveTime;
		}


		public void setReserveDate( String reserveDate ) {
				this.reserveDate = reserveDate;
		}


		public void setReserveTime( String reserveTime ) {
				this.reserveTime = reserveTime;
		}



		public String getTestModeFlag() {
				return testModeFlag;
		}


		public void setTestModeFlag( String testModeFlag ) {
				this.testModeFlag = testModeFlag;
		}

		@Override
		public String toString() {
				StringBuilder builder = new StringBuilder();
				builder.append( "KakaoConfig [senderId=" );
				builder.append( senderId );
				builder.append( ", authKey=" );
				builder.append( authKey );
				builder.append( ", senderTel=" );
				builder.append( receiverTel );
				builder.append( ", msg=" );
				builder.append( msg );
				builder.append( ", reserveDate=" );
				builder.append( reserveDate );
				builder.append( ", reserveTime=" );
				builder.append( reserveTime );
				builder.append( ", testModeFlag=" );
				builder.append( testModeFlag );
				builder.append( "]" );
				return builder.toString();
		}





}
