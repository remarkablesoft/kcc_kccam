package com.remarkablesoft.framework.web.module.authentication;

import java.util.Arrays;
import java.util.List;

import com.remarkablesoft.framework.util.ApplicationPropertiesUtils;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module	-	authentication
 * 		@프로그램 ID		:	CustomLoginPathManager
 * 		@프로그램 개요 	:	유저/관리자를 제외한 외부 프로젝트 접속 URL을 사용하기 위한 매니저
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 9. 16.	:	최원준	-	신규생성
 * 		============================================================================
 */
public class CustomLoginPathManager {

		private CustomLoginPathManager() {
				
		}
		
		private static final String DELIMETER = ",";
		private static final String LOGIN_MENU_EMPTY = "";
		private static final String LOGIN_MENU_PREFIX = "login.page.";
		private static final String CUSTOM_MENU_URL = "login.custom.url";

		/**
		 * 현재 요청받은 URL이 커스텀 URL 중 존재하는지 확인합니다. 
		 * 
		 * @author 최원준
		 * @param url
		 * @return
		 */
		public static String containCustomUrl( String url ) {
				
				String menuUrlStringList = ApplicationPropertiesUtils.getValue( CUSTOM_MENU_URL );
				
				if ( StringUtils.isEmpty( menuUrlStringList )) {
						return "";
				}
				
				List<String> menuUrlList = Arrays.asList( menuUrlStringList.split( DELIMETER ) );
				
				String result = "";
				for ( String menuUrl : menuUrlList ) {
						if ( url.contains( menuUrl ) ) {
							result = menuUrl;
							break;
						}
				}
				
				return result;
		}
		
		
		/**
		 * 
		 * 
		 * @author 최원준
		 * @param menuUrlKey
		 * @return
		 */
		public static String getLoginPath( String menuUrlKey ) {
				
				if ( StringUtils.isEmpty( menuUrlKey ) ) {
						return LOGIN_MENU_EMPTY;
				}
				
				String propertiesKey = LOGIN_MENU_PREFIX.concat( menuUrlKey.substring( 1, menuUrlKey.length()-1 ) );
				String loginPath = ApplicationPropertiesUtils.getValue( propertiesKey );

				if ( StringUtils.isEmpty( loginPath ) ) {
						return LOGIN_MENU_EMPTY;
				}
				
				return loginPath;
		}
		
}
