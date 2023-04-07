package com.remarkablesoft.framework.web.module.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.authentication.exception.AuthenticationException;
import com.remarkablesoft.framework.web.module.authentication.exception.AuthenticationModelAndViewException;

/**
 * 설명 : 인증 오류 처리기. 응답코드로 401을 설정하고, {@code AuthenticationModelAndViewException} 또는 {@code AuthenticationException} 예외를 처리 함.<br />
 * 만일 failCode이 {@code ModelAndView}에 없다면, 추가 함
 *
 * @author james
 * @since 2014. 6. 01.
 *
 */
public class AuthenticationHandlerExceptionResolver implements HandlerExceptionResolver {

		/*
		 * (non-Javadoc)
		 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest,
		 * javax.servlet.http.HttpServletResponse,
		 * java.lang.Object, java.lang.Exception)
		 */
		@Override
		public ModelAndView resolveException( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) {
				if ( ex instanceof AuthenticationException ) {
						
						if ( !WebUtils.isIncludeRequest( request ) ) {
								response.setStatus( HttpServletResponse.SC_UNAUTHORIZED );
								request.setAttribute( WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, HttpServletResponse.SC_UNAUTHORIZED );
						}

						ModelAndView returnModelAndView = new ModelAndView();
						if ( ex instanceof AuthenticationModelAndViewException ) {
								returnModelAndView = ((AuthenticationModelAndViewException) ex).getModelAndView();
								
								// spa 일때는 페이지 없이 401 오류 발생.
								if ( StringUtils.isEmpty( returnModelAndView.getView())) {
										returnModelAndView = new ModelAndView();
										returnModelAndView.setStatus( HttpStatus.UNAUTHORIZED );	
								}
						}

						// faileCode 설정
						String faileCode = (String) returnModelAndView.getModelMap().get( "failCode" );
						if ( StringUtils.isEmpty( faileCode ) ) {
								faileCode = ((AuthenticationException) ex).getFailCode();
								if ( StringUtils.hasText( faileCode ) ) {
										returnModelAndView.addObject( "failCode", faileCode );
								}
						}
						return returnModelAndView;
				}
				return null;
		}
}
