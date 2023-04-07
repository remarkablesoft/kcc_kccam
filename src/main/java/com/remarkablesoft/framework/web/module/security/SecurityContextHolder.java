package com.remarkablesoft.framework.web.module.security;

import org.springframework.core.NamedInheritableThreadLocal;

import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.web.module.authentication.exception.AuthenticationException;

/**
 * 설명 : 인증정보를 보관하는 컨텍스트 객체.
 *
 * @author james
 * @since 2014. 5. 13.
 *
 */
public final class SecurityContextHolder
{

		/** 인증 정보를 보관하는 ThreadLocal 변수. */
		private static final NamedInheritableThreadLocal<SecurityContext> SECURITY_CONTEXT_HOLDER = new NamedInheritableThreadLocal<SecurityContext>( SecurityContextHolder.class + "_CONTEXT_HOLDER" );

		/**
		 * 객체 생성 방지.
		 */
		private SecurityContextHolder() {

		}

		/**
		 * 인증 정보를 삭제.
		 */
		public static void removeSecurityContext()
		{
				SECURITY_CONTEXT_HOLDER.set( null );
		}

		/**
		 * 인증 정보를 보관.
		 *
		 * @param securityContext 인증 정보
		 */
		public static void setSecurityContext( SecurityContext securityContext )
		{
				SECURITY_CONTEXT_HOLDER.set( securityContext );
		}

		/**
		 * 인증 정보를 반환하며, 인증 정보가 없을 경우 {@link AuthenticationException}를 발생.
		 *
		 * @return 인증 정보
		 */
		public static SecurityContext getSecurityContext()
		{
				SecurityContext securityContext = SECURITY_CONTEXT_HOLDER.get();
				return securityContext;
		}

		/**
		 * 인증된 사용자의 정보를 반환.
		 *
		 * @return 인정 정보 반환
		 */
		public static UserInfo getCredential()
		{
				SecurityContext context = SecurityContextHolder.getSecurityContext();
				if ( context != null ) {
						return context.getUserInfo();
				}
				return null;
		}
}
