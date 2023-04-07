package com.remarkablesoft.framework.web.module.authentication.exception;

import com.remarkablesoft.framework.annotation.Authentication;
import com.remarkablesoft.framework.exception.ApplicationRuntimeException;

/**
 * 설명 : 인증 실패 시 발생하는 Exception.
 *
 * @author james
 * @since 2014. 5. 13.
 *
 */
public class AuthenticationException extends ApplicationRuntimeException {

		/**
		 *
		 */
		private static final long serialVersionUID = 224340381409031737L;

		/**
		 * 실패 코드.
		 *
		 * @see com.remarkablesoft.framework.annotation.Authentication.failCode()
		 */
		private String failCode;

		/**
		 * "인증이 필요합니다"라는 오류 메시지로 생성 함
		 */
		public AuthenticationException() {
				super( "인증이 필요합니다." );
		}

		/**
		 * 오류 메시지와 함께 생성 함
		 *
		 * @param message 오류 메시지
		 */
		public AuthenticationException( String message ) {
				super( message );
		}

		/**
		 * 인증이 필요합니다.라는 메시지를 출력하며, 인증 정보를 제공한다.
		 *
		 * @param authentication 인증 정보
		 */
		public AuthenticationException( Authentication authentication ) {
				this();
				setFailCode( authentication.failCode() );
		}

		/**
		 * 인증이 필요합니다.라는 메시지를 출력하며, 인증 정보를 제공한다.
		 *
		 * @param message 오류 메시지
		 * @param authentication 인증 정보
		 */
		public AuthenticationException( String message, Authentication authentication ) {
				super( message );
				setFailCode( authentication.failCode() );
		}

		/**
		 * 실패 관련 정보를 설정.
		 *
		 * @param authentication 인증관련 Annotation
		 */
		public void setAuthenticationAnnotation( Authentication authentication ) {
				setFailCode( authentication.failCode() );
		}

		/**
		 * 실패 코드 설정
		 *
		 * @param fail 실패코드
		 */
		public final void setFailCode( String fail ) {
				this.failCode = fail;
		}

		/**
		 * 실패 코드 반환
		 *
		 * @return 실패 코드
		 * @see com.remarkablesoft.framework.annotation.Authentication.failCode()
		 */
		public String getFailCode() {
				return failCode;
		}
}
