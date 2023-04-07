package com.remarkablesoft.framework.web.module.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.remarkablesoft.framework.annotation.Authentication;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.authentication.annotation.HttpServletAuthentication;
import com.remarkablesoft.framework.web.module.authentication.exception.AuthenticationModelAndViewException;
import com.remarkablesoft.framework.web.util.CookieUtils;

/**
 * 
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module - authentication
 * 		@프로그램 ID		:	HttpServletAuthenticationTokenImpl
 * 		@프로그램 개요 		:	refreshToken과 accessToken을 이용해서 인증처리
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 10. 19	:	james	-	신규생성
 * 		============================================================================
 */

public class HttpServletAuthenticationTokenImpl implements HttpServletAuthentication {

		/** The Constant ANONYMOUS_MEMBER. */
//		public static final UserInfo ANONYMOUS_MEMBER = SystemFactory.getUserInfo();
		public static final UserInfo ANONYMOUS_MEMBER = new UserInfo();

		/**
		 * <PRE>
		 * LUO, LOI, UAC 쿠키를 비교하여 로그인 인증 절차를 수행한다.
		 * 1. Authentication 어노테이션을 선언했을 경우
		 * 로그인이 되면 로그인한 사용자 객체를 UDC 쿠키에 기록하고 사용자 객체를 리턴시킨다.
		 * 로그인이 안될 경우 로그인 페이지로 이동시켜준다.
		 * 2. Authentication(ignoreAuthenticationException=true) 어노테이션을 선언했을 경우
		 * 로그인 하지 않아도 로그인 페이지로 이동하지 않고 빈 파트너 객체를 리턴시킨다.
		 * </PRE>
		 *
		 * @param request
		 * @param response
		 * @param authentication
		 * @return the user info
		 * @see
		 *      com.remarkablesoft.framework.web.authentication.HttpServletAuthentication.certifyCredential(HttpServletRequest
		 *      , HttpServletResponse, Authentication)
		 */
		@Override
		public UserInfo certifyCredential( HttpServletRequest request, HttpServletResponse response, Authentication authentication ) {
				
				
//				final String accessToken = request.getHeader( SystemConstants.ACCESS_TOKEN);
//				boolean result = false;
				
				// 로그인 한 유저 Oid 가져오기(LUO : Login User Oid )
				String userOid = CookieUtils.getCookieDec( request, AuthenticationConstant.LOGIN_USER_OID );
				// 로그인 한 유저 ID 가져오기(LUI : Login User Id)
				String userId = CookieUtils.getCookieDec( request, AuthenticationConstant.LOGIN_USER_ID );
				// 로그인 한 유저의 ROLE( LURL : Login User Role List )
				String roleList = CookieUtils.getCookieDec( request, AuthenticationConstant.LOGIN_USER_ROLE_LIST );
				

				// 오류 처리(로그인이 필요한 메뉴) : @Authentication
				ModelAndView modelAndView = new ModelAndView(); // "redirect:/site/roltech/login/login_login"
				
				// 로그인 한 사용자의 아이디와 암호화 시킨 인증용 쿠키 가져오기 (UAC : User Authentication Cookie)
				// userAuthCookie 예시 "id=bluejames10;oid=1RvAiVb403q;userType=HLUTLWYR"
				String userAuthCookie = CookieUtils.getCookieDec( request, AuthenticationConstant.USER_AUTHENTICATION_COOKIE );
				
				if ( StringUtils.hasText( userAuthCookie ) && StringUtils.hasText( userId ) ) {
						
						// 암호화된 UAC 쿠키와 LUO, LUI 값이 같은지 확인한다.
						if ( !userAuthCookie.split( ";" )[0].endsWith( "=" ) && !userAuthCookie.split( ";" )[1].endsWith( "=" ) ) {

								String cookieUserId = userAuthCookie.split( ";" )[0].split( "=" )[1]; 
								String cookieUserOid = userAuthCookie.split( ";" )[1].split( "=" )[1];
								
								if ( userId.equals( cookieUserId ) && userOid.equals( cookieUserOid ) ) {
										try {

												// 로그인 한 사용자 상세 정보 암호화 쿠키값 가져와서 사용자 객체 생성 (UDC : User Detail infomation Cookie)
												UserInfo userInfo = CookieUtils.getCookieUserInfoDec( request, AuthenticationConstant.USER_DETAIL_INFORMATION_COOKIE );
												// 로그인 한 사용자 아이디값, 키값 사용자 객체에 저장
												userInfo.setOid( userOid );
												userInfo.setUserId( userId );

												CookieUtils.addCookieEnc( response, AuthenticationConstant.SESSION_TIMEOUT, AuthenticationConstant.USER_AUTHENTICATION_COOKIE, userAuthCookie );
												CookieUtils.addCookieEnc( response, AuthenticationConstant.SESSION_TIMEOUT, AuthenticationConstant.LOGIN_USER_OID, userOid );
												CookieUtils.addCookieEnc( response, AuthenticationConstant.SESSION_TIMEOUT, AuthenticationConstant.LOGIN_USER_ROLE_LIST, roleList );

												return userInfo;
										}
										catch ( NumberFormatException exception ) {

										}
								}
						}
				}
				// authentication.ignoreAuthenticationException가 false일때는 무시해야함.
				else if ( authentication != null && !authentication.ignoreAuthenticationException() && authentication.ignoreAuthenticationException() && !StringUtils.hasText( userAuthCookie ) && StringUtils.hasText( userId ) ) {
						// throw new LoginTimeOutException("타임아웃 에러");
				}
				// 로그인 하지 않았을 때

				// 오류 처리를 하지 않는 경우(회원가입 페이지 등 로그인이 필요 없는 메뉴) : @Authentication(ignoreAuthenticationException=true)
				if ( authentication != null && authentication.ignoreAuthenticationException() ) {
						return ANONYMOUS_MEMBER;
				}

				try {
						if ( ServletUriComponentsBuilder.fromRequest( request ).build().encode().toUriString().indexOf( "/fragment/" ) > -1 ) {
								return ANONYMOUS_MEMBER;
						}
						else {
								//	logout이 아닐 때만 return url을 넣어줌
								if( ServletUriComponentsBuilder.fromRequest( request ).build().encode().toUriString().indexOf( "logout" ) < 0 ) {
										modelAndView.addObject( "returnUrl", ServletUriComponentsBuilder.fromRequest( request ).build().encode().toUriString() );		
								}
								
						}

				}
				catch ( Exception e ) {
				}

				throw new AuthenticationModelAndViewException( authentication, modelAndView );
		}
}
