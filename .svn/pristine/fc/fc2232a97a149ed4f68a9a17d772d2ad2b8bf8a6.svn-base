package com.remarkablesoft.site.kccam.web.api;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.service.authority.role.vo.RoleInfo;
import com.remarkablesoft.framework.service.org.user.model.UserService;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.EncryptUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.controller.LoginController;
import com.remarkablesoft.framework.web.util.CookieUtils;

@WEBLog
@RestController
@RequestMapping( "/kccam/api/loginApi" )
public class LoginAPIController extends LoginController {

		@Autowired
		protected UserService userService;

		@RequestMapping( value = "/loginApi_login", method = RequestMethod.POST )
		public Map<String, String> login( @RequestParam( value = "returnUrl", required = false ) String returnUrl,
								@RequestParam( value = "rememberMe", defaultValue = "false", required = false ) boolean rememberMe,
								@RequestParam( value = "autoLogin", defaultValue="false", required = false ) boolean autoLogin,
								@RequestBody UserInfo userInfo,
								HttpServletRequest request,
								HttpServletResponse response ) {


				if ( rememberMe ) {
						CookieUtils.addCookie( response, -1, "userId", userInfo.getUserId() );
				}
				else {
						CookieUtils.removeCookie( request, response, "userId" );
				}

				UserInfo user = null;
				
				Map<String, String> result = new HashMap<>();
				
				boolean userExist = userService.existById( userInfo );
				
				// 존재하지 않는 ID일 경우
				if ( !userExist ) {
					result.put( "errorUserId", userInfo.getUserId() );
					
					String errorMessage = "존재하지 않는 <strong>아이디</strong>입니다. <br>";
					errorMessage += "<br>자세한 사항은 관리자에게 문의 부탁드립니다.";
					
					result.put( "errorMessage", errorMessage );
					result.put( "success", String.valueOf( Boolean.FALSE ) );
					return result;
				}
				

				// 넘어온 패스워드 복호화
				userInfo.setPwd( EncryptUtils.decryptAESValue( userInfo.getPwd() ) );
				user = getLoginUser( userInfo, request );

				//로그인 실패 시
				if( user == null || StringUtils.isEmpty( user.getOid() ) ) {
						//다시 로그인 페이지로
						result.put( "errorUserId", userInfo.getUserId() );
						result.put( "errorMessage", "<strong>아이디</strong> 혹은 <strong>패스워드</strong>가 일치하지 않습니다." );
						result.put( "success", String.valueOf( Boolean.FALSE ) );
						return result;
				}

				// 자동로그인 정보 저장
//				if( autoLogin ) {
//						// 자동로그인 LOGIN_AUTO => "LA";
//						// 로그인상태유지(자동로그인) 기본 30일로 변경
//						CookieUtils.addCookieEnc( response, 3600 * 24 * 30, AuthenticationConstant.LOGIN_AUTO, SystemConstants.FLAG_YES );
//				}

				loginSuccessProcess( user, response );
				result.put( "success", String.valueOf( Boolean.TRUE ) );
				result.put( "userId", user.getUserId() );
				result.put( "userName", user.getName() );
				result.put( "userOid", user.getOid() );
				result.put( "userEmail", user.getEmail() );

				result.put( "userPhone", user.getPhone() );
				result.put( "phonePart1", user.getPhonePart1() );
				result.put( "phonePart2", user.getPhonePart2() );
				result.put( "phonePart3", user.getPhonePart3() );
				result.put( "orgName", user.getOrganizationName() );
				
				result.put( "empNo", user.getEmpNo() );

				String roleOids = "";
				if ( CollectionUtils.isNotEmpty( user.getUserRoleList() ))  {
					roleOids = user.getUserRoleList().stream().map( RoleInfo::getOid ).collect( Collectors.joining( "," ) );
				}

				result.put( "userRoleOids", roleOids );

				return result;

		}

		
		/**
		 * 로그인 시도 사용자를 db에서 가져옴
		 *
		 * @author sunny
		 * @param userInfo
		 * @param request
		 * @return
		 */
		public UserInfo getLoginUser( UserInfo userInfo, HttpServletRequest request ) {
				return userService.login( userInfo );
		}

		@Override
		public void addStartMenuCookie( HttpServletResponse response ) {
				// TODO Auto-generated method stub

		}
}
