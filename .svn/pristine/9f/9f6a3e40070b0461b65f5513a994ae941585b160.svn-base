package com.remarkablesoft.framework.web.util;

import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.EncryptUtils;
import com.remarkablesoft.framework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 설명 : Cookie 유틸리티
 * 
 * 2021.01.20  james 쿠키의 기본 암호화를 TripleDES 에서 AES로  변경.
 * 						  스크립트와 자바단에서 같은 암호화를 사용하기 위해 
 * 
 *
 * @author james
 * @since 2014. 5. 21.
 *
 */
public abstract class CookieUtils {

		public static final int ONE_DAY_MAX_AGE = 60 * 60 * 24 ; 	    	// 하루 동안 쿠키 저장
		public static final int ONE_MONTH_MAX_AGE = ONE_DAY_MAX_AGE * 30; 	// 한달 동안 쿠키 저장

		public static final String ENCODING = "UTF-8";

		/**
		 * 쿠키 이름에 해당하는 쿠키를 반환한다.
		 *
		 * @param request HttpServletRequest
		 * @param cookieName 쿠키명
		 * @return 쿠키
		 */
		public static Cookie getCookie( HttpServletRequest request, String cookieName ) {
				return org.springframework.web.util.WebUtils.getCookie( request, cookieName );
		}

		/**
		 * 쿠키 이름에 해당하는 쿠키의 값을 반환한다.
		 *
		 * @param request HttpServletRequest
		 * @param name 쿠키명
		 * @return 쿠키명에 해당하는 쿠키의 값
		 */
		public static String getCookieValue( HttpServletRequest request, String cookieName ) {
				Cookie cookie = getCookie( request, cookieName );
				if ( cookie == null ) {
						return null;
				}
				return cookie.getValue();
		}

		/**
		 * 현재 세션에 한해서, 쿠키값을 추가한다. 브라우져를 재시작하면 쿠키값은 사라짐.
		 *
		 * @param response HttpServletResponse
		 * @param cookieName 쿠키명
		 * @param cookieValue 쿠키값
		 */
		public static void addCookie( HttpServletResponse response, String cookieName, String cookieValue ) {
				addCookie( response, -1, cookieName, cookieValue );
		}

		/**
		 * 한달 기간의 쿠키값을 추가한다.
		 *
		 * @param response HttpServletResponse
		 * @param cookieName 쿠키명
		 * @param cookieValue 쿠키값
		 */
		public static void addCookieOneMonth( HttpServletResponse response, String cookieName, String cookieValue ) {
				addCookie( response, ONE_MONTH_MAX_AGE, cookieName, cookieValue );
		}

		/**
		 * 정해진 기간만큼의 쿠키값을 추가한다.
		 *
		 * @param response HttpServletResponse
		 * @param cookieExpiration 쿠키 만료일
		 * @param cookieName 쿠키명
		 * @param cookieValue 쿠키값
		 */
		public static void addCookie( HttpServletResponse response, int cookieExpiration, String cookieName, String cookieValue ) {

				Cookie cookie = new Cookie( cookieName, cookieValue );
				cookie.setMaxAge( cookieExpiration );
				// cookie.setDomain( ApplicationPropertiesUtils.getValue("application.domain"));
				cookie.setPath( "/" );
				response.addCookie( cookie );

		}

		/**
		 * 쿠키값을 삭제한다.
		 *
		 * @param request HttpServletRequest
		 * @param response HttpServletResponse
		 * @param cookieKey 쿠키명
		 */
		public static void removeCookie( HttpServletRequest request, HttpServletResponse response, String cookieKey ) {
				Cookie cookie = getCookie( request, cookieKey );
				if ( cookie != null ) {
						cookie.setValue( "" );
						cookie.setMaxAge( 0 );
						// cookie.setDomain( ApplicationPropertiesUtils.getValue("application.domain"));

						cookie.setPath( "/" );
						response.addCookie( cookie );
				}
		}

		/**
		 * 현재 세션에 한해서, 암호화된 쿠키값을 추가한다. 브라우져를 재시작하면 쿠키값은 사라짐.
		 *
		 * @param response HttpServletResponse
		 * @param cookieName 쿠키명
		 * @param cookieValue 쿠키값
		 */
		public static void addCookieEnc( HttpServletResponse response, String cookieName, String cookieValue ) {
			
				addCookieEnc( response, -1, cookieName, cookieValue );
		}

		/**
		 * 현재 세션에 한해서, 암호화된 쿠키값을 추가한다. 브라우져를 재시작하면 쿠키값은 사라짐.
		 *
		 * @param response HttpServletResponse
		 * @param expiration the expiration
		 * @param cookieName 쿠키명
		 * @param cookieValue 쿠키값
		 */
		public static void addCookieEnc( HttpServletResponse response, int expiration, String cookieName, String cookieValue ) {
				/*
				 * String cookStr = AESUtils.encrypt( cookieValue );
				 * addCookie( response, expiration, cookieName, cookStr );
				 */

				String cookStr = "";
				try {
						cookStr = URLEncoder.encode( EncryptUtils.encryptAESValue( cookieValue ), ENCODING );
				}
				catch ( Exception e ) {
						e.printStackTrace();
				}
				addCookie( response, expiration, cookieName, cookStr );
		}

		/**
		 * 쿠키 이름에 해당하는 쿠키를 암호 해제하여 반환한다.
		 *
		 * @param request HttpServletRequest
		 * @param cookieName 쿠키명
		 * @return 쿠키
		 */
		public static String getCookieDec( HttpServletRequest request, String cookieName ) {
				String cookStr = "";
				Cookie cookie = getCookie( request, cookieName );
				if ( cookie != null ) {
						// cookStr = cookie.getValue().replaceAll("%3D", "=").replaceAll(" ", "+");
						//cookStr = new String( AESUtils.decrypt( cookie.getValue() ) ).trim();
						try {
								cookStr = EncryptUtils.decryptAESValue( URLDecoder.decode( cookie.getValue(), ENCODING ) ).trim();
						}
						catch ( Exception e ) {
								e.printStackTrace();
						}
				}

				return cookStr;
		}

		/**
		 * 현재 세션에 한해서, 암호화된 쿠키값을 추가한다. 브라우져를 재시작하면 쿠키값은 사라짐.
		 * cookieValue에 더 많은 값을 넣고 싶으면 HttpServletAuthenticationImpl를 수정해야 한다.
		 *
		 *
		 * @param response
		 * @param cookieName
		 * @param userInfo
		 */
		public static void addCookieUserInfoEnc( HttpServletResponse response, String cookieName, UserInfo userInfo, int expiration ) {

				String cookieValue = "";

				cookieValue += "name=" + userInfo.getName() + ";";
				cookieValue += "email=" + userInfo.getEmail() + ";";
				cookieValue += "userType=" + userInfo.getUserType() + ";";
				cookieValue += "snsJoinTypeFlag=" + userInfo.getSnsJoinTypeFlag() + ";";
				cookieValue += "userId=" + userInfo.getUserId() + ";";
				cookieValue += "oid=" + userInfo.getOid() + ";";

				//String cookStr = AESUtils.encrypt( cookieValue );
				String cookStr = "";

				try {
						cookStr = URLEncoder.encode( EncryptUtils.encryptAESValue( cookieValue ), ENCODING );
				}
				catch ( UnsupportedEncodingException e ) {
						e.printStackTrace();
				}
				catch ( Exception e ) {
						e.printStackTrace();
				}

				addCookie( response, expiration, cookieName, cookStr );
		}

		/**
		 * 쿠키 이름에 해당하는 쿠키를 암호 해제하여 반환한다.
		 *
		 * @param request
		 * @param cookieName
		 * @return
		 */
		public static UserInfo getCookieUserInfoDec( HttpServletRequest request, String cookieName ) {

				String cookieValue = "";
				UserInfo userInfo = SystemFactory.getUserInfo();
				Cookie cookie = getCookie( request, cookieName );

				if ( cookie != null ) {

						try {
								cookieValue = EncryptUtils.decryptAESValue( URLDecoder.decode( cookie.getValue(), ENCODING ) ).trim();
						}
						catch ( Exception e ) {
								e.printStackTrace();
						}

						String[] arrCookie = cookieValue.split( ";" );

						if ( arrCookie.length > 0 ) {
								userInfo.setName( getValue( arrCookie[0] ) );
						}
						if ( arrCookie.length > 1 ) {
								userInfo.setEmail( getValue( arrCookie[1] ) );
						}
						if ( arrCookie.length > 2 ) {
								userInfo.setUserType( getValue( arrCookie[2] ) );
						}
						if ( arrCookie.length > 3 ) {
								userInfo.setSnsJoinTypeFlag( getValue( arrCookie[3] ) );
						}
						if ( arrCookie.length > 4 ) {
								userInfo.setUserId( getValue( arrCookie[4] ) );
						}
						if ( arrCookie.length > 5 ) {
								userInfo.setOid( getValue( arrCookie[5] ) );
						}

				}

				return userInfo;
		}

		public static String getValue( String target ) {

				if ( StringUtils.isEmpty( target ) && target.indexOf( "=" ) < 0 ) {
						return "";
				}

				String[] arrResult = StringUtils.tokenizeToStringArray( target, "=" );

				return arrResult[1] != null ? arrResult[1] : "";
		}

}
