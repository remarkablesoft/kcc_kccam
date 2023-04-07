package com.remarkablesoft.framework.web.module.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.remarkablesoft.framework.annotation.Authentication;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.ApplicationPropertiesUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.authentication.annotation.HttpServletAuthentication;
import com.remarkablesoft.framework.web.module.authentication.exception.AuthenticationModelAndViewException;
import com.remarkablesoft.framework.web.util.CookieUtils;

/**
 * 설명 :
 *
 * <PRE>
 * Controller 클래스의 각 메서드에서 Authentication
 * 또는 Authentication(ignoreAuthenticationException=true)
 * 어노테이션을 선언할 경우 호출된다.
 * 쿠키 정보를 이용한 로그인 인증 절차를 거쳐
 * 로그인이 되었는지 확인한다.
 *
 * 2020-02-15 - Role을 콤마로 여러개 가지고 있도록 처리
 *
 * </PRE>
 *
 * @author james
 * @since 2014. 5. 13.
 *
 */
public class HttpServletAuthenticationImpl implements HttpServletAuthentication {

		/** The Constant ANONYMOUS_MEMBER. */
//		public static final UserInfo ANONYMOUS_MEMBER = SystemFactory.getUserInfo();
		public static final UserInfo ANONYMOUS_MEMBER = new UserInfo();

		protected String redirectViewName = "";

		public void setRedirectViewName( String redirectViewName ) {
				this.redirectViewName = redirectViewName;
		}

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
				
				// 로그인 한 유저 Oid 가져오기(LUO : Login User Oid )
				String userOid = CookieUtils.getCookieDec( request, AuthenticationConstant.LOGIN_USER_OID );
				// 로그인 한 유저 ID 가져오기(LUI : Login User Id)
				String userId = CookieUtils.getCookieDec( request, AuthenticationConstant.LOGIN_USER_ID );
				// 로그인 한 유저의 ROLE( LURL : Login User Role List )
				String roleList = CookieUtils.getCookieDec( request, AuthenticationConstant.LOGIN_USER_ROLE_LIST );
				
				String url = request.getServletPath();
				String customUrl = CustomLoginPathManager.containCustomUrl( url );
				String loginPath = "";
				
				// 로그인 한 위치
				if ( url.contains( SystemConstants.LOGIN_MENU_USER ) && !url.contains( SystemConstants.LOGIN_MENU_MANAGER ) ) {
						loginPath = ApplicationPropertiesUtils.getValue( "login.page.user" );
				}
				else if ( url.contains( SystemConstants.LOGIN_MENU_MANAGER ) ) {
						loginPath = ApplicationPropertiesUtils.getValue( "login.page.manager" );
				}
				// 사용자/관리자 외의 접속 페이지가 존재할 시
				else if ( StringUtils.hasText( customUrl ) ) {
						loginPath = CustomLoginPathManager.getLoginPath( customUrl );
				}
				
				
				
				//	start url
				if( StringUtils.isEmpty( loginPath ) ) {
						loginPath = CookieUtils.getCookieDec( request, AuthenticationConstant.LOGIN_START_MENU );
				}

				redirectViewName = "redirect:" + loginPath;

				// 오류 처리(로그인이 필요한 메뉴) : @Authentication
				ModelAndView modelAndView = new ModelAndView( redirectViewName ); // "redirect:/site/roltech/login/login_login"
				
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
