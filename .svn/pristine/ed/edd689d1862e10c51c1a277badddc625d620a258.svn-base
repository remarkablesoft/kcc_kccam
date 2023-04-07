package com.remarkablesoft.framework.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.service.authority.role.vo.RoleInfo;
import com.remarkablesoft.framework.service.org.user.model.impl.UserBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.authentication.AuthenticationConstant;
import com.remarkablesoft.framework.web.util.CookieUtils;
import com.remarkablesoft.framework.web.util.WebUtils;

/**
 * 
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	web
 * 		@프로그램 ID		:	LoginController
 * 		@프로그램 개요 		:	로그인 컨트롤러의 Base
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 10. 31	:	james	-	신규생성
 * 		============================================================================
 */
public abstract class LoginController extends BaseController {


		@Autowired
		protected UserBLO userBLO;
		
		/**
		 * 로그인 성공 후 process - 쿠키,이력 저장 등
		 * @author sunny
		 * @param user
		 * @param response
		 */
		public void loginSuccessProcess( UserInfo user, HttpServletResponse response ) {

				// 유저 기본 정보를 쿠키에 저장합니다. 암호화 되지 않은 쿠키.
//				addCookieUserInfo( response, AuthenticationConstant.LOGIN_USER_BASE_INFO, user, "" );

				addCookie( response, user );
		}


		//	TODO 로그인 Controller에서 공통으로 사용하는 메소드 정의
		public void addCookie( HttpServletResponse response, UserInfo user ) {

				// 디폴트 쿠키정보
				addDefaultCookie( response, user);

				// 처음 어디에서 로그인 했는지 확인. (Login Start Menu)
				addStartMenuCookie( response);

		}

		
		// 처음 어디에서 로그인 했는지 확인. (Login Start Menu)
		public abstract void addStartMenuCookie( HttpServletResponse response);

		
		
		/**
		 * 시스템에서 필요한 쿠키정보
		 * 앱을 위해 쿠키시간을 늘려 놓아서 전체적인 로그인관련이 꼬였음.
		 * 일단 UDC를 짧게 두어 로그인을 맞추도록 처리.
		 * 
		 * @author james
		 * @param response
		 * @param user
		 */
		public void addDefaultCookie( HttpServletResponse response, UserInfo user ) {

				// 로그인 한 사용자 아이디값 쿠키 저장(LUI : Login User Id)
				// 임시로 2주 이것과 관련하여 자동로그인을 accessToken을 가져오는것으로 변경해야함. by james
				CookieUtils.addCookieEnc( response, 3600 * 24 * 15, AuthenticationConstant.LOGIN_USER_ID, user.getUserId() );

				// 로그인 한 사용자 OID 쿠키 저장(LUO : Login User OId )
				CookieUtils.addCookieEnc( response, AuthenticationConstant.SESSION_TIMEOUT, AuthenticationConstant.LOGIN_USER_OID, user.getOid() );

				// 로그인 한 사용자 USER_TYPE 쿠키 저장(LUUT : Login User UserType )
				CookieUtils.addCookieEnc( response, AuthenticationConstant.LOGIN_USER_USER_TYPE, user.getUserType() );

				// 로그인 한 사용자의 유저롤리스트 저장( LURL : Login User Role List )
				// 사용자의 유저롤은 여러개가 될 수 있으므로 ,의 문자열형태로 저장
				List<String> userRoleOidList = user.getUserRoleList().stream().map( RoleInfo::getOid ).collect( Collectors.toList() );
				CookieUtils.addCookieEnc( response, AuthenticationConstant.LOGIN_USER_ROLE_LIST,  StringUtils.join( userRoleOidList, "," ) );

				// 로그인 한 아이디와 OID 값 암호화 시킨 인증용 쿠키 저장 (UAC : User Authentication Cookie)
				CookieUtils.addCookieEnc( response, AuthenticationConstant.SESSION_TIMEOUT, AuthenticationConstant.USER_AUTHENTICATION_COOKIE, "id=" + user.getUserId() + ";oid=" + user.getOid() + ";userType=" + user.getUserType() );

				// 로그인 한 유저의 상세 정보 쿠키에 암호화 저장 (UDC : User Detail infomation Cookie)
				CookieUtils.addCookieUserInfoEnc( response, AuthenticationConstant.USER_DETAIL_INFORMATION_COOKIE, user, -1 );
				
				//	로그인한 유저의 이름
				CookieUtils.addCookieEnc( response, 3600 * 24, AuthenticationConstant.LOGIN_USER_NAME, user.getName() );
				
		}

		/**
		 * 사용자 정보를 쿠키에 저장합니다.
		 * AuthenticationConstant.LOGIN_USER_BASE_INFO 쿠키는 
		 * 암호화되지 않은 쿠키라 늘리면 안됨.
		 * 
		 * 암호화되지 않은 쿠키는 최대한 안쓰도록 해야함.
		 * 
		 * @param response
		 * @param cookieName
		 * @param user
		 */
		@Deprecated
		public void addCookieUserInfo( HttpServletResponse response, String cookieName, UserInfo user, String lastLogin ) {

				StringBuilder sb = new StringBuilder();
				sb.append( "oid=" ).append( user.getOid() )
	    			.append( ";id=" ).append( user.getUserId() )
	    			.append( ";name=" ).append( user.getName() )
	    			.append( ";lastLogin=" ).append( lastLogin )
	    			.append( ";companyOid=" ).append( user.getCompanyOid() )
	    			.append( ";userType=" ).append( user.getUserType() )
	    			.append( ";snsJoinTypeFlag=" ).append( user.getSnsJoinTypeFlag() );
				
				
				//	변호사는 roleOid가 필요합니다.
				if( CollectionUtils.isNotEmpty( user.getUserRoleList() ) ) {
					sb.append( ";roleOid=" ).append( user.getUserRoleList().get( 0 ).getOid() );
				}
				
				// 쿠키시간은 -1로 세션이 살아있을때로만 한정 해야함.
				CookieUtils.addCookie( response, -1, cookieName, WebUtils.URLEncode( sb.toString() ) );
		}
		

}
