package com.remarkablesoft.framework.web.module.authentication.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.remarkablesoft.framework.annotation.Authentication;
import com.remarkablesoft.framework.web.module.authentication.exception.AuthenticationModelAndViewException;

/**
 * 설명 : 인증정보를 관리하는 인터페이스
 *
 * @author james
 * @since 2014. 5. 13.
 *
 */
public interface HttpServletAuthentication {

		/**
		 * {@code UserInfo} 사용자 정보를 획득하여, 반환한다.<br />
		 * 인증 실패 시, {@code Authentication#ignoreAuthenticationException()} 정보를 확인하여, {@code AuthenticationModelAndViewException} 예외를 발생 시킴.
		 *
		 * @param request HttpServletRequest
		 * @param response HttpServletResponse
		 * @param authentication Authentication annotation 정보
		 * @return UserInfo 객체. 만일 인증에 실패 할 경울 null을 반환 함.
		 * @throws AuthenticationModelAndViewException 인증에 실패한 경우 발생
		 */
		public Object certifyCredential( HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws AuthenticationModelAndViewException;

}
