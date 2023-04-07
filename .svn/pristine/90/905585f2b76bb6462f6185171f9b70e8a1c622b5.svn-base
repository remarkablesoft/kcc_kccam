package com.remarkablesoft.framework.web.module.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.remarkablesoft.framework.annotation.Authentication;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.util.ApplicationPropertiesUtils;
import com.remarkablesoft.framework.web.util.CookieUtils;

/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module - authentication
 * 		@프로그램 ID		:	URLCheckHandlerInterceptor
 * 		@프로그램 개요 		:	url을 체크해서 사용자는 사용자 화면만, 관리자는 관리자 화면만 볼수 있도록 처리
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 2. 14.	:	james	-	신규생성
 * 		============================================================================
 */
public class URLCheckHandlerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {

		// include 자원에 대해서는 보안 처리를 따로 하지 않음.
		if ( WebUtils.isIncludeRequest( request ) ) {
			return true;
		}

		// @Authentication이 존재하는 메소드만 User Level을 확인합니다.
		// 클래스 어노테이션 확인이 필요할 경우 추가 // ((HandlerMethod) handler).getBeanType().getAnnotation( Authentication.class )
		if ( !(handler instanceof HandlerMethod) || AnnotationUtils.getAnnotation( ((HandlerMethod) handler).getMethod(), Authentication.class ) == null ) {
			return true;
		}

		String url = request.getServletPath();

		// json 호출도 제외
		if ( url.indexOf( ".json" ) >= 0 ) {
			return true;
		}


		String loginStartMenu = CookieUtils.getCookieDec( request, AuthenticationConstant.LOGIN_START_MENU );

		// 일반 유저가 일반 유저화면사용시
		if ( url.startsWith( SystemConstants.LOGIN_MENU_USER ) && SystemConstants.LOGIN_MENU_USER.equals( loginStartMenu ) ) {
			return true;
		}
		// 일반 유저가 관리자 화면으로 접속시
		else if ( url.startsWith( SystemConstants.LOGIN_MENU_USER ) && !SystemConstants.LOGIN_MENU_USER.equals( loginStartMenu ) ) {
			response.sendRedirect( request.getContextPath() + ApplicationPropertiesUtils.getValue( "login.page.user" )  );
			return false;
		}
		// 관리자 유저가 관리자 화면사용시
		else if ( url.startsWith( SystemConstants.LOGIN_MENU_MANAGER ) && SystemConstants.LOGIN_MENU_MANAGER.equals( loginStartMenu ) ) {
			return true;
		}
		// 관리자가일반 유저 화면으로 접속시
		else if ( url.indexOf( SystemConstants.LOGIN_MENU_MANAGER ) >= 0 && !SystemConstants.LOGIN_MENU_MANAGER.equals( loginStartMenu ) ) {
			response.sendRedirect( request.getContextPath() + ApplicationPropertiesUtils.getValue( "login.page.user" ) );
			return false;
		}

		return true;
	}

	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {

	}

	@Override
	public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) throws Exception {

	}

}
