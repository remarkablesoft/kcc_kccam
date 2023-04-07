package com.remarkablesoft.framework.service.authority.role.model.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.exception.BLORuntimeException;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.authority.role.vo.RoleCnd;
import com.remarkablesoft.framework.service.authority.role.vo.RoleRightInfo;
import com.remarkablesoft.framework.service.authority.role.vo.RoleUserRelInfo;
import com.remarkablesoft.framework.service.org.user.model.impl.UserBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserCnd;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;


/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	authority - role
 * 		@프로그램 ID		:	RoleTargetRelBLO
 * 		@프로그램 개요 		:	Role에 포함된 타겟 객체정보 - 타겟은 User, Group, Pgroup
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2021. 2. 5.	:	sirena	-	신규생성
 * 		============================================================================
 */
@BLO
public class RoleUserRelBLO {

		@Autowired
		protected RoleUserRelDAO roleUserRelDAO;
		
		@Autowired
		protected UserBLO userBLO;
		
		

		public RoleUserRelInfo insertCheck( RoleUserRelInfo info ) {

				// 등록 Validation
				inserValidation( info );
				
				// 이미 있다면
				if ( roleUserRelDAO.exist( info ) ) {
						return null;
				}

				return roleUserRelDAO.insert( info ) > 0 ? info : null ;
		}
		
		public RoleUserRelInfo insert( RoleUserRelInfo info ) {

				// 등록 Validation
				inserValidation( info );
				
				roleUserRelDAO.insert( info );
				return info;
		}

		
		
		public RoleUserRelInfo insert( String roleOid, String targetObject, String targetOid ) {

				RoleUserRelInfo info = SystemFactory.getRoleUserRelInfo();
				info.setRoleOid( roleOid );
				info.setTargetObject( targetObject );
				info.setTargetOid( targetOid );

				return insertCheck( info );
		}
		
		/**
		 * userList만큼 롤에 맵핑처리
		 * 
		 * @author james
		 * @param roleOid
		 * @param userList
		 */
		public void insert( String roleOid, List<UserInfo> userList ) {
		
				if ( StringUtils.isEmpty( roleOid ) || CollectionUtils.isEmpty( userList )) {
						return ;
				}
				
				for ( UserInfo userInfo : userList ) {
						
						insert ( roleOid, UserInfo.getObjectType(), userInfo.getOid());
				}
				
		}


		public int delete( RoleCnd cnd ) {
				
				return roleUserRelDAO.delete( cnd );
		}

		public RoleUserRelInfo get( RoleCnd cnd ) {
				
				return roleUserRelDAO.get( cnd );
		}

		public List<RoleUserRelInfo> listAll( RoleCnd cnd ) {
				
				List<RoleUserRelInfo> roleUserList = roleUserRelDAO.listAll( cnd );
				
				if ( cnd.isFillUserInfo()) {
						
						fillUserinfo( roleUserList );
				}
				
				return roleUserList;
		}
		
		
		/**
		 * 사용자 OID로 자신이 가질수 있는 모든 롤을 체크한다.
		 * 1. 사용자 role
		 * 2. pgroup에 포함된 사용자의 role
		 * 3. group에 포함된 사용자의 role
		 * 
		 * @author james
		 * @param userOid
		 * @return
		 */
		public List<RoleUserRelInfo> listRoleByUserOid( String userOid ) {
				
				List<RoleUserRelInfo> roleUserList = roleUserRelDAO.listRoleByUserOid( userOid );
				return roleUserList;
		}
		
		


		public List<UserInfo> converUsers( List<RoleUserRelInfo> listRoleUser ) {
				

				
				List<String> userOidList = listRoleUser.stream().map( RoleUserRelInfo::getTargetOid ).collect( Collectors.toList() );
				
				UserCnd userCnd = new UserCnd();
				userCnd.setUserOidList( userOidList );
				
				return userBLO.listAll( userCnd );
		}
		
		

		public int delete( String targetObject, String targetOid ) {
				
				if ( StringUtils.isEmpty( targetObject ) || StringUtils.isEmpty( targetOid )) {
						 return 0;
				}
				
				RoleCnd cnd = new RoleCnd();
				cnd.setTargetObject( targetObject );
				cnd.setTargetOid( targetOid );
				
				return delete( cnd );
		}

		
		
		/**
		 * roleUserRelInfo에서 userInfo값을 찾아서 채워준다.
		 * 
		 * @author james
		 * @param roleUserList
		 */
		protected void fillUserinfo( List<RoleUserRelInfo> roleUserList ) {

				if ( CollectionUtils.isEmpty( roleUserList )) {
						return ;
				}
				
				
				List<String> userOidList = roleUserList.stream().map( RoleUserRelInfo::getTargetOid ).collect( Collectors.toList() );
				
				UserCnd userCnd = new UserCnd();
				userCnd.setUserOidList( userOidList );
				List<UserInfo> userList =  userBLO.listAll( userCnd );
				
				if ( CollectionUtils.isEmpty( userList )) {
						return ;
				}
				
				roleUserList.forEach( roleUser -> userList.stream()
														  .filter( user -> roleUser.getTargetOid().equals( user.getOid() ) )
														  .forEach( user -> {
																  roleUser.setUser( user );
														  }));
				
		}
		
		
		/**
		 * 등록시 필수값은 validation 확인
		 * 
		 * @author james
		 * @param info
		 */
		protected void inserValidation( RoleUserRelInfo info  ) {
				
				if ( StringUtils.isEmpty( info.getRoleOid() ) ) {
						throw new BLORuntimeException( "RoleOid는 필수값입니다." );
				}
				
				if ( StringUtils.isEmpty( info.getTargetObject() ) ) {
						throw new BLORuntimeException( "TargetObject는 필수값입니다." );
				}
				
				if ( StringUtils.isEmpty( info.getTargetOid() ) ) {
						throw new BLORuntimeException( "TargetOid는 필수값입니다." );
				}
		}
		
}