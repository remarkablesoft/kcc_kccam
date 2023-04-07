package com.remarkablesoft.framework.service.org.user.model.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.org.user.model.UserService;
import com.remarkablesoft.framework.service.org.user.vo.UserCnd;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	org-user
 * 		@프로그램 ID		:	UserService.java
 * 		@프로그램 개요 		:	사용자 서비스 Impl 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2014. 5. 12.	:	james	-	신규생성
 * 		============================================================================
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

		@Autowired
		protected UserBLO userBLO;
		
		@Autowired
		protected UserLoginBLO userLoginBLO;

		@Override
		public UserInfo insertOrUpdate( UserInfo user ) {
				return userBLO.insertOrUpdate( user );
		}

		@Override
		public UserInfo insert( UserInfo user ) {
				return userBLO.insert( user );
		}

		@Override
		public int delete( String oid ) {
				return userBLO.delete( oid );
		}

		@Override
		public int deleteById( String id ) {
				return userBLO.deleteById( id );
		}

		@Override
		public UserInfo getUser( UserCnd cnd ) {
				return userBLO.getUser( cnd );
		}
		
		@Override
		public int updateStatusTypeFlagByUserOidList( UserInfo user ) {

			return userBLO.updateStatusTypeFlagByUserOidList( user );
		}

		@Override
		public UserInfo update( UserInfo user ) {
				return userBLO.update( user );
		}

		@Override
		public UserInfo getById( String id ) {
				return userBLO.getUserById( id );
		}

		@Override
		public UserInfo getUser( String oid ) {
				return userBLO.getUser( oid );
		}
	
		@Override
		public UserInfo login( UserInfo user ) {
			return userLoginBLO.login( user );
		}
	
	@Override
		public PageList<UserInfo> list( UserCnd cnd ) {
				return userBLO.listUser( cnd );
		}

		@Override
		public int listAllCount( UserCnd userCnd ) {
				return userBLO.listAllCount( userCnd );
		}

		@Override
		public List<UserInfo> listAll( UserCnd cnd ) {
				return userBLO.listAll( cnd );
		}

		@Override
		public PageList<UserInfo> listRoleUser( UserCnd cnd ) {
				return userBLO.listRoleUser( cnd );
		}

		@Override
		public List<UserInfo> listAllRoleUser( UserCnd cnd ) {
				return userBLO.listAllRoleUser( cnd );
		}
		
		@Override
		public int deleteByCnd( UserCnd cnd ) {
				return userBLO.deleteByCnd( cnd );
		}

		@Override
		public int getCount( UserCnd cnd ) {
				return userBLO.getCount( cnd );
		}
		
		@Override
		public Map<String, Integer> getStatusCount( UserCnd cnd ) {
				return userBLO.getStatusCount( cnd );
		}
	
		@Override
		public boolean existById( UserInfo info ) {
				return userBLO.existById( info );
		}
	
}
