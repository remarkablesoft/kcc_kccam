package com.remarkablesoft.framework.web.module.security;

import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

/**
 * 설명 : 사용자 정보를 보안을 위해 저장하고, 보안관련 기능을 제공한다.
 *
 * @author james
 * @since 2014. 5. 13.
 *
 */
public class SecurityContext
{

		/** 기본 사용자 정보. */
		private UserInfo userInfo = null;

		/**
		 * 기본 생성자.
		 *
		 * @param userInfo 인증된 사용자 정보
		 */
		public SecurityContext( UserInfo userInfo ) {
				this.userInfo = userInfo;
		}

		/**
		 * 인증한 사용자 정보를 반환
		 *
		 * @return 인증한 사용자 정보
		 */
		public UserInfo getUserInfo()
		{
				return userInfo;
		}
}
