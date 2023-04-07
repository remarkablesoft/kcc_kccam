package com.remarkablesoft.framework.web.util;

import com.remarkablesoft.framework.service.org.user.vo.NullUserInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.web.module.security.SecurityContextHolder;


/**
 * 설명 :
 *
 * @author james
 * @since 2014. 6. 2.
 *
 */
public final class AutheUtils {

		public static String getLoginUserOid() {

				UserInfo userInfo = getLoginUserInfo();

				if ( userInfo != null ) {
						return userInfo.getOid();
				}

				return "";
		}

		public static UserInfo getLoginUserInfo() {

				UserInfo userInfo = SecurityContextHolder.getCredential();

				if ( userInfo == null ) {
						return new NullUserInfo();
				}

				return userInfo;
		}

		/**
		 * 로그인 여부
		 *
		 * @param request
		 * @return
		 */
		public static boolean isLogin() {

				UserInfo userInfo = SecurityContextHolder.getCredential();

				return userInfo != null;
		}
}
