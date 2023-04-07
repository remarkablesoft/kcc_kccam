package com.remarkablesoft.framework.web.module.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.Authentication;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.web.module.authentication.annotation.HttpServletAuthentication;

/**
 * 설명 : 인증정보를 관리하는 클래스.
 *
 * @author james
 * @since 2014. 5. 13.
 *
 */
public class HttpServletAuthenticationManager implements HttpServletAuthentication
{

		/** 익명 인증 서비스. */
		public static final HttpServletAuthentication ANONYMOUS_HTTP_SERVLET_AUTHENTICATION = new HttpServletAuthentication() {

				@Override
				public Object certifyCredential( HttpServletRequest request, HttpServletResponse response, Authentication authentication )
				{
						return SystemFactory.getUserInfo();
				}

		};

		/** 서비스 제공 인스턴스. */
		private HttpServletAuthentication httpServletAuthentication = ANONYMOUS_HTTP_SERVLET_AUTHENTICATION;

		/**
		 * Sets the authentication.
		 *
		 * @param httpServletAuthentication the new authentication
		 */
		@Autowired( required = false )
		public HttpServletAuthenticationManager setAuthentication( HttpServletAuthentication httpServletAuthentication )
		{
				this.httpServletAuthentication = httpServletAuthentication;
				return this;
		}

		@Override
		public Object certifyCredential( HttpServletRequest request, HttpServletResponse response, Authentication authentication )
		{
				return httpServletAuthentication.certifyCredential( request, response, authentication );
		}

}
