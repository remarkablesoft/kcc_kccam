package com.remarkablesoft.framework.web.module.restapi;

import java.util.HashMap;
import java.util.Map;

public enum FailMessage {

		// 접근 실패
		REQUEST_API_KEY_NOT_FOUND( "RQA004", "API KEY이 존재하지 않습니다." ),
		REQUEST_API_KEY_INVALID( "RQA000", "API KEY가 유효하지 않습니다." ),
		REQUEST_ACCESS_TOKEN_NOT_FOUND( "RQA001", "Access Token이 존재하지 않습니다." ),
		REQUEST_ACCESS_TOKEN_EXPIRATION( "RQA002", "Access Token이 만료되었습니다." ),
		REQUEST_ACCESS_TOKEN_REQUIRED( "RQA003", "Access Token이 유효하지 않습니다." ),

		// 요청 상태
		RESPONSE_SERVER_ERROR( "DFR000", "처리 중 오류가 발생하였습니다." ),
		RESPONSE_REQUIRED_ERROR( "DFR001", "파라메터가 유효하지 않습니다." ),
		RESPONSE_INSERT_FAIL( "DFR002", "입력에 실패하였습니다." ),
		RESPONSE_UPDATE_FAIL( "DFR003", "수정에 실패하였습니다." ),
		RESPONSE_DELETE_FAIL( "DFR004", "삭제에 실패하였습니다." ),

		RESPONSE_USER_ID_EXIST( "DFR005", "중복된 아이디입니다." ),
		RESPONSE_USER_PWD_WRONG( "DFR006", "잘못된 비밀번호입니다." ),
		RESPONSE_MAIL_SEND_FAIL( "DFR007", "메일을 전송하는데 실패하였습니다. 메일 주소를 확인해주세요." ),
		RESPONSE_IMAGE_NOT_FOUND( "DFR008", "썸네일 이미지가 존재하지 않습니다." ),
		RESPONSE_USER_EMAIL_EXIST( "DFR009", "중복된 이메일입니다." ),
		
		RESPONSE_USER_PHONE_EXIST( "DFR010", "메세지 발송을 위한 수신자 번호가 존재하지 않습니다." ),

		// 회원 상태
		USER_STAUTS_NOT_FOUND( "DUS000", "해당 정보로 가입된 회원이 존재하지 않습니다." ),
		USER_STATUS_APPROVAL_WAIT( "DUS001", "승인 대기 중 입니다." ),
		USER_STATUS_APPROVAL_CANCEL( "DUS002", "승인이 취소된 사용자입니다." ),
		USER_STATUS_WITHDRAWAL( "DUS003", "탈퇴한 사용자입니다." ),
		USER_STATUS_APPROVAL_WAIT_ELAPSED( "DUS004", "마지막 로그인이 1년이 경과하여 대기 상태로 변경되었습니다. 관리자에게 문의 바랍니다." ),
		USER_STATUS_PWD_CONFIRM( "DUS005", "비밀번호를 확인 후 다시 입력해주세요." ),
		USER_STATUS_ID_OR_PWD_CONFIRM( "DUS006", "아이디 혹은 비밀번호를 확인 후 다시 입력해주세요." ),

		// 기기 상태
		DEVICE_STATE_REGISTER_NO( "DDS001", "등록이 해제된 기기입니다." ),
		DEVICE_STATE_NOT_FOUND( "DDS002", "기기를 찾을 수 없습니다." ),

		// 인증 실패
		AUTH_STATE_EXIST( "ASE001", "이미 인증 요청한 파일입니다." ),

		// PUSH 에러메세지
		PUSH_ERROR_APP_LOGIN( "PSE000", "App login first" ), PUSH_ERROR_APP_INSTALL( "PSE001", "Not Yet Install App." ),

		// OTP 인증 실패
		OTP_STATE_FAIL( "OTP001", "OTP 인증에 실패하였습니다." );


		private static final Map<String, String> codeToMessage = new HashMap<String, String>();
		private String code;
		private String message;

		static {
				for ( FailMessage message : values() ) {
						codeToMessage.put( message.getCode(), message.getMessage() );
				}
		}

		public static String getMessage( String code ) {
				if ( codeToMessage.containsKey( code ) ) {
						return codeToMessage.get( code );
				}
				return "";
		}

		FailMessage( String code, String message ) {
				this.code = code;
				this.message = message;
		}

		public String getCode() {
				return code;
		}

		public String getMessage() {
				return message;
		}

		public void setCode( String code ) {
				this.code = code;
		}

		public void setMessage( String message ) {
				this.message = message;
		}

}
