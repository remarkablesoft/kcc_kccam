package com.remarkablesoft.framework.service.org.user.model.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.exception.BLORuntimeException;
import com.remarkablesoft.framework.service.org.user.model.UserPasswordBLO;
import com.remarkablesoft.framework.service.org.user.vo.NullUserInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserCnd;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	org-user
 * 		@프로그램 ID		:	UserLoginBLO
 * 		@프로그램 개요 		:	사용자 로그인만 별도 처리
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 2. 14.	:	james	- 로그인만 따로 처리
 * 		============================================================================
 */
@BLO
public class UserLoginBLO {

		@Autowired
		protected UserDAO userDAO;

		@Autowired
		protected UserBLO userBLO;

		@Autowired
		protected UserPasswordBLO userPasswordBLO;

		public UserInfo login( UserInfo info ) {

				// sns로그인 또는 id,pwd 로그인
				if ( StringUtils.isEmpty( info.getSnsId() ) && (StringUtils.isEmpty( info.getUserId() ) || StringUtils.isEmpty( info.getPwd() )) ) {
						return new NullUserInfo();
				}

				//	일반로그인 시 pwd암호화
				if ( StringUtils.isEmpty( info.getSnsId() ) ) {

						if ( StringUtils.isEmpty( info.getUserId() ) || StringUtils.isEmpty( info.getPwd() ) ) {
								return new NullUserInfo();
						}

						if ( info.getPwd().length() < userPasswordBLO.getEncryptCharLength() ) {
								info.setPwd( userPasswordBLO.encrypt( info.getPwd() ) );
						}
				}

				UserInfo user = userDAO.login( info );
				userBLO.fillUserInfo( user );

				return user;
		}

		/**
		 * 사용자 아이디중복체크
		 * @author user
		 * @param info
		 * @return
		 */
		public boolean checkDuplicateId( UserInfo info ) {

				if ( StringUtils.isEmpty( info.getUserId() ) ) {
						throw new BLORuntimeException( "아이디가 비어있습니다." );
				}

				return userDAO.checkDuplicateId( info );
		}

		/**
		 * 기존비밀번호가 맞는지 확인합니다.
		 */
		public boolean checkPassword( UserCnd cnd ) {

				if ( StringUtils.isEmpty( cnd.getOid() ) ) {
						throw new BLORuntimeException( "사용자 oid가 비어있습니다." );
				}

				if ( StringUtils.isEmpty( cnd.getPwd() ) && StringUtils.isEmpty( cnd.getPwd2() ) ) {
						throw new BLORuntimeException( "체크할 비밀번호가 비어있습니다." );
				}

				//	비밀번호 길이 체크
				String password = StringUtils.hasText( cnd.getPwd() ) ? cnd.getPwd() : cnd.getPwd2();
				if ( password.length() >= userPasswordBLO.getEncryptCharLength() ) {
						throw new BLORuntimeException( "비밀번호는 " + userPasswordBLO.getEncryptCharLength() + "자 미만으로 입력해주세요." );
				}

				//	비밀번호 암호화
				if ( StringUtils.hasText( cnd.getPwd() ) ) {
						cnd.setPwd( userPasswordBLO.encrypt( cnd.getPwd() ) );
				}

				if ( StringUtils.hasText( cnd.getPwd2() ) ) {
						cnd.setPwd2( userPasswordBLO.encrypt( cnd.getPwd2() ) );
				}

				return userDAO.checkPassword( cnd );
		}
		

		/**
		 * oid, password를 받아와 기존비밀번호 일치여부를 확인해 return
		 */
		public boolean checkPassword( String oid, String password ) {

				UserCnd cnd = new UserCnd();
				cnd.setOid( oid );
				cnd.setPwd( password );

				return checkPassword( cnd );
		}

		public int changePassword( String oid, String password ) {

				if ( password.length() >= userPasswordBLO.getEncryptCharLength() ) {
						throw new BLORuntimeException( "비밀번호는 " + userPasswordBLO.getEncryptCharLength() + "자 미만으로 입력해주세요." );
				}

				UserInfo user = userBLO.getUser( oid );
				user.setPwd( password );

				UserInfo info = userBLO.update( user );

				return info != null ? 1 : 0;
		}

		/**
		 * 이메일, 아이디와 같은 중복이 불가능한 요소의 중복여부를 판별합니다
		 * @author user
		 * @param user
		 * @return
		 */
		@Cacheable( value = "user", keyGenerator = "cacheKeyGenerator" )
		public boolean checkDuplicateByUserCnd( UserCnd cnd ) {

				return userDAO.checkDuplicateByUserCnd( cnd );
		}
		

}
