package com.remarkablesoft.framework.web.module.authentication.exception;

import org.springframework.web.servlet.ModelAndView;

import com.remarkablesoft.framework.annotation.Authentication;

/**
 * 설명 : {@code ModelAndView}를 제공하는 인증 오류
 *
 * @author james
 * @since 2014. 5. 13.
 *
 */
public class AuthenticationModelAndViewException extends AuthenticationException {

		/**
		 *
		 */
		private static final long serialVersionUID = -4731832866093080504L;

		/** The model and view. */
		private ModelAndView modelAndView;

		/**
		 * {@code ModelAndView} 정보와 함께 생성함. 이때 오류 메시지는 "인증이 필요합니다."를 사용 함
		 *
		 * @param modelAndView modelAndView
		 */
		public AuthenticationModelAndViewException( ModelAndView modelAndView ) {
				super();
				this.modelAndView = modelAndView;
		}

		/**
		 * {@code ModelAndView} 정보, 오류메 시지와 함께 생성함
		 *
		 * @param message 오류 메시지
		 * @param modelAndView modelAndView
		 */
		public AuthenticationModelAndViewException( String message, ModelAndView modelAndView ) {
				super( message );
				this.modelAndView = modelAndView;
		}

		/**
		 * {@code ModelAndView} 정보, 인증 정보와 함께 생성함
		 *
		 * @param authentication 인증 정보 annotation
		 * @param modelAndView modelAndView
		 */
		public AuthenticationModelAndViewException( Authentication authentication, ModelAndView modelAndView ) {
				super( authentication );
				this.modelAndView = modelAndView;
		}

		/**
		 * {@code ModelAndView} 정보, 인증 정보, 오류 메시지와 함께 생성함
		 *
		 * @param message 오류 메시지
		 * @param authentication 인증 정보 annotation
		 * @param modelAndView modelAndView
		 */
		public AuthenticationModelAndViewException( String message, Authentication authentication, ModelAndView modelAndView ) {
				super( message, authentication );
				this.modelAndView = modelAndView;
		}

		/**
		 * Gets the model and view.
		 *
		 * @return the model and view
		 */
		public ModelAndView getModelAndView() {
				return modelAndView;
		}

		/**
		 * Sets the model and view.
		 *
		 * @param modelAndView the new model and view
		 */
		public void setModelAndView( ModelAndView modelAndView ) {
				this.modelAndView = modelAndView;
		}
}
