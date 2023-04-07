package com.remarkablesoft.framework.web.module.authentication;

import com.remarkablesoft.framework.util.ApplicationPropertiesUtils;

/**
 * 인증처리에 사용하는 상수모음
 *
 * @author James
 *
 */
public class AuthenticationConstant {

		/**
		 * 로그인한 유저의 OID
		 * Login User Oid
		 */
		public final static String LOGIN_USER_OID = "LUO"; // 로그인한 유저의 OID Login User Oid

		/**
		 * 로그인한 유저 ID
		 * Login User Id
		 */
		public final static String LOGIN_USER_ID = "LUI"; // 로그인한 유저 ID Login User Id

		/**
		 * 로그인한 유저 USER_TYPE
		 * Login User type
		 */
		public final static String LOGIN_USER_USER_TYPE = "LUUT";

		/**
		 * 로그인 한 사용자의 아이디와 암호화 시킨 인증용 쿠키
		 * User Authentication Cooki
		 */
		public final static String USER_AUTHENTICATION_COOKIE = "UAC"; //  로그인 한 사용자의 아이디와 암호화 시킨 인증용 쿠키 User Authentication CookiE

		/**
		 * 로그인 한 사용자 상세 정보 암호화 쿠키값
		 * User Detail infomation Cookie
		 */
		public final static String USER_DETAIL_INFORMATION_COOKIE = "UDC"; // 로그인 한 사용자 상세 정보 암호화 쿠키값 User Detail infomation Cookie

		/**
		 * 로그인 한 위치
		 * LOGIN_START_MENU
		 */
		public final static String LOGIN_START_MENU = "LSM";

		/**
		 * 로그인한 유저 Role ID
		 */
		public static final String LOGIN_USER_ROLE_LIST = "LURL";

		/**
		 * 세션 타임아웃
		 */
		public static final int SESSION_TIMEOUT = ApplicationPropertiesUtils.getIntValue( "session.timeout" , 3600 * 1 ) ;

		/**
		 * 유저 기본 정보
		 */
		public final static String LOGIN_USER_BASE_INFO = "LUBI";

		/**
		 * 로그인 디바이스 UUID
		 */
		public final static String LOGIN_DEVICE_UUID = "LDU"; // 로그인한 디바이스 UUID

		/**
		 * 로그인 디바이스 OID
		 */
		public final static String LOGIN_DEVICE_OID = "LDO"; // 로그인한 디바이스 OID

		/**
		 * 암호화 accessToken
		 */
		public final static String LOGIN_ACCESS_TOKEN = "LAT"; // 로그인한 디바이스 OID

		/**
		 * 자동 로그인 여부
		 */
		public final static String LOGIN_AUTO = "LA";

		/**
		 * 자동 로그인 시 이동할 위치
		 */
		public final static String LOGIN_AUTO_MENU = "LAM";
		
		/**
		 * 자동 로그인 시 LOGIN_AUTO_PASS_MENU 호출되면 LOGIN_AUTO_MENU가 호출되도록 하는 상수
		 */
		public final static String LOGIN_AUTO_PASS_MENU = "LAPM";
		
		/**
		 * 로그인 사용자의 이름
		 */
		public final static String LOGIN_USER_NAME = "LUN";

		
}
