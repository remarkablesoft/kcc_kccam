package com.remarkablesoft.framework.web.module.authentication;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.remarkablesoft.framework.annotation.Authentication;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.security.SecurityContext;
import com.remarkablesoft.framework.web.module.security.SecurityContextHolder;
import com.remarkablesoft.framework.web.util.CookieUtils;

/**
 * 설명 : 인증 정보를 제어 함.
 *
 * @author james
 * @since 2014. 5. 13.
 *
 */
public class HttpServletSecurityContextHandlerInterceptor implements HandlerInterceptor {

		/** The authentication manager. */
		protected HttpServletAuthenticationManager authenticationManager;

		@Override
		public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
				// include 자원에 대해서는 보안 처리를 따로 하지 않음.
				if ( WebUtils.isIncludeRequest( request ) ) {
						return true;
				}
				boolean ret = true;

				if ( handler instanceof HandlerMethod ) {
						Authentication authentication = AnnotationUtils.getAnnotation( ((HandlerMethod) handler).getMethod(), Authentication.class );
						if ( authentication != null || ( authentication != null && StringUtils.hasText( CookieUtils.getCookieDec( request, AuthenticationConstant.LOGIN_AUTO ) ) )) {

								Object returnValue = authenticationManager.certifyCredential( request, response, authentication );
								ret = processReturnValue( request, response, returnValue );
						}
				}
				return ret;
		}

		@Override
		public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {
		}

		@Override
		public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) throws Exception {
				// include 자원에 대해서는 보안 처리를 따로 하지 않음.
				if ( WebUtils.isIncludeRequest( request ) ) {
						return;
				}

				if ( handler instanceof HandlerMethod ) {
						Authentication authentication = AnnotationUtils.getAnnotation( ((HandlerMethod) handler).getMethod(), Authentication.class );
						if ( authentication != null ) {
								SecurityContextHolder.removeSecurityContext();
						}
				}
		}

		/**
		 * Set the authentication manager.
		 *
		 * @param authenticationManager the new authentication manager
		 */
		@Autowired
		public HttpServletSecurityContextHandlerInterceptor setAuthenticationManager( HttpServletAuthenticationManager authenticationManager ) {
				this.authenticationManager = authenticationManager;
				return this;
		}

		protected boolean processReturnValue( HttpServletRequest request, HttpServletResponse response, Object value ) throws ServletException, IOException
		{
				boolean ret = true;
				if ( value instanceof ModelAndView ) {
						// ModelAndView를 반환하는 경우, 계속 진행하지 않고, 해당 컨트롤러로 forward
						RequestDispatcher rd = request.getRequestDispatcher( ((ModelAndView) value).getViewName() );
						rd.forward( request, response );
						ret = false;
				}
				else if ( value instanceof UserInfo ) {
						// 사용자 정보인 경우 SecurityContext에 저장 함.
						SecurityContext securityContext = new SecurityContext( (UserInfo) value );
						SecurityContextHolder.setSecurityContext( securityContext );

				}
				else {
						// 사용자 정보를 획득하지 못한 경우에는 SecurityContext 값을 지우고, 혼선이 없게 함
						SecurityContextHolder.removeSecurityContext();
				}
				return ret;
		}

}
