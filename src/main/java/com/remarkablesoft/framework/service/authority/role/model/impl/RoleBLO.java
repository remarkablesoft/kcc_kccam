package com.remarkablesoft.framework.service.authority.role.model.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.authority.role.vo.RoleCnd;
import com.remarkablesoft.framework.service.authority.role.vo.RoleInfo;
import com.remarkablesoft.framework.service.authority.role.vo.RoleRightInfo;
import com.remarkablesoft.framework.service.authority.role.vo.RoleUserRelInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	authority - role
 * 		@프로그램 ID		:	RoleBLO
 * 		@프로그램 개요 	:	Role BLO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 1. 25.	:	james	-	신규생성
 * 		1.1  2019. 5. 10.	:	james	-	ROLE을 ObjectRel을 통해서 넣는 구조가 아닌 User객체에 ROLE_OID를 가지고 있는 형태로 변경
 * 		============================================================================
 */
@BLO
public class RoleBLO {
		

		@Autowired
		protected RoleDAO roleDAO;

		@Autowired	
		protected RoleRightBLO roleRightBLO;

		@Autowired
		protected RoleUserRelBLO roleUserRelBLO;

		/**
		 * Role 등록
		 * 
		 * @author james
		 * @param roleInfo
		 * @return
		 */
		public RoleInfo insert( RoleInfo roleInfo ) {
				
				if ( roleInfo == null ) {
						return null;
				}

				if ( StringUtils.isEmpty( roleInfo.getOid() ) ) {
						roleInfo.setOid( OIDGenerator.generateOID() );
				}

				if ( roleInfo.getInputDate() != null ) {

						roleInfo.setInputDate( LocalDateTime.now() );
				}

				roleDAO.insert( roleInfo );

				return roleInfo;
		}
		

		public int update( RoleInfo roleInfo ) {

				updateRight( roleInfo );

				return roleDAO.update( roleInfo );
		}
		

		/**
		 * 권한만 업데이트
		 * 권한은 기존에 있는것 다 지우고 새로 생성하도록 처리
		 *
		 * @param roleInfo
		 */
		public void updateRight( RoleInfo roleInfo ) {
				
				if ( roleInfo == null || CollectionUtils.isEmpty( roleInfo.getRoleRightInfoList() )) {
						return;
				}
		
				RoleCnd cnd = new RoleCnd();
				cnd.setRoleOid( roleInfo.getOid() );

				// 기존에 권한이 있었다면 전부 삭제
				roleRightBLO.delete( cnd );
				
				// 권한 추가
				roleInfo.getRoleRightInfoList().forEach( right -> {
						
						if ( StringUtils.isEmpty( right.getRoleOid()) ) {
								right.setRoleOid( roleInfo.getOid() );
						}
						roleRightBLO.insert( right);
				});

		}

		
		/**
		 * 롤 삭제할 시 롤의 유저 및 롤의 권한도 삭제
		 * 롤에 해당하는 유저도 삭제
		 * 롤에 해당하는 권한 삭제
		 * 
		 *
		 * @param cnd
		 * @return
		 */
		public int delete( RoleCnd cnd ) {
				
				// 1. 롤의 권한 삭제 
				roleRightBLO.delete( cnd );
				
				// 2. 롤의 유저 삭제
				roleUserRelBLO.delete( cnd );

				// 3. 롤 삭제
				return roleDAO.delete( cnd );
		}

		
		/**
		 * 롤 삭제할 시 사용
		 * 롤 삭제시 관계도 삭제
		 *
		 * @param cnd
		 * @return
		 */
		public int delete( String roleOid ) {

				RoleCnd cnd = new RoleCnd();
				cnd.setRoleOid( roleOid );

				return delete( cnd );
		}



		public PageList<RoleInfo> list( RoleCnd cnd ) {

				PageList<RoleInfo> roleInfoList = roleDAO.list( cnd );
				return (PageList<RoleInfo>) commonList( roleInfoList, cnd);
		}

		
		

		public List<RoleInfo> listAll( RoleCnd cnd ) {

				return commonList( roleDAO.listAll( cnd ), cnd);
		}



		public RoleInfo get( RoleCnd cnd ) {

				RoleInfo roleInfo = roleDAO.get( cnd );

				if ( roleInfo == null ) {
						return null;
				}
				
				// 검색시 롤의 권한도 같이 가져올것인지 여부
				if ( cnd.isRoleRightSearch()) {

						cnd = new RoleCnd();
						cnd.setRoleOid( roleInfo.getOid() );
						roleInfo.setRoleRightInfoList( roleRightBLO.listAll( cnd ) );
				}
				
				// 검색시 롤의 권한도 같이 가져올것인지 여부
				if ( cnd.isRoleUserRelSearch()) {
						
						cnd = new RoleCnd();
						cnd.setRoleOid( roleInfo.getOid() );
						roleInfo.setRoleUserRelList( roleUserRelBLO.listAll( cnd ) );
				}

				return roleInfo;
		}


		
		/**
		 * 사용자 OID로 자신이 가질수 있는 모든 롤을 체크한다.
		 * 
		 * 1. 사용자 role
		 * 2. pgroup에 포함된 사용자의 role
		 * 3. group에 포함된 사용자의 role
		 *
		 * @author james
		 * @param oid
		 * @param object
		 * @param targetObject
		 * @return
		 */
		public List<RoleInfo> listRoleByUserOid( String userOid ) {

				List<RoleUserRelInfo> userRoleList = roleUserRelBLO.listRoleByUserOid( userOid );
				
				if ( CollectionUtils.isEmpty( userRoleList ) ) {
						return new ArrayList<>();
				}

				List<String> roleOid = userRoleList.stream().map( RoleUserRelInfo::getRoleOid ).collect( Collectors.toList() );
				
				RoleCnd cnd = new RoleCnd();
				cnd.setRoleOidList( roleOid );

				List<RoleInfo> roleList = listAll( cnd );
				
				fillRoleUserRelInfo( userRoleList, roleList );
				
				return roleList != null ? roleList : new ArrayList<>();
		}


		/**
		 * roleInfo에 roleUserRelInfoList를 채워줍니다.
		 * 
		 * @author sirena
		 * @param roleUserRelInfoList
		 * @param roleList
		 */
		protected void fillRoleUserRelInfo( List<RoleUserRelInfo> roleUserRelInfoList, List<RoleInfo> roleList ) {

				if ( CollectionUtils.isEmpty( roleUserRelInfoList ) || CollectionUtils.isEmpty( roleList ) ) {
						return;
				}


				roleList.forEach( role -> roleUserRelInfoList.stream()
												.filter( rel -> role.getOid().equals( rel.getRoleOid() ) )
												.forEach( rel -> role.addRoleUser( rel ) ) );
		}

		
		public List<RoleInfo> getByUser( String roleOid ) {
				
				RoleCnd cnd = new RoleCnd();
				cnd.setRoleOid( roleOid );
		
				return getByUser( cnd );
		}
		
		
		public List<RoleInfo> getByUser( RoleCnd roleCnd ) {


				RoleCnd cnd = new RoleCnd();
				cnd.setTargetObject( UserInfo.getObjectType() );
				cnd.setTargetOid( roleCnd.getTargetOid() );
				
				List<RoleUserRelInfo> roleUserRelList = roleUserRelBLO.listAll( cnd );
				
				if ( CollectionUtils.isEmpty( roleUserRelList )) {
						
						return new ArrayList<>();
				}
				
				List<String> roleList = roleUserRelList.stream().map( RoleUserRelInfo::getRoleOid ).collect(  Collectors.toList() );
				
				if ( CollectionUtils.isEmpty( roleList )) {
						
						return new ArrayList<>();
				}
				
				cnd = new RoleCnd();
				cnd.setRoleOidList( roleList );
				
				return listAll( cnd );
		}
		

		/**
		 * 해당 roleOid에 맞는 모든 권한정보 리스트를 반환
		 * 
		 * @author james
		 * @param oid
		 * @return
		 */
		public List<RoleRightInfo> listRoleRight( String roleOid ) {

				RoleCnd cnd = new RoleCnd();
				cnd.setRoleOid( roleOid );
				
				return roleRightBLO.listAll( cnd );
		}
		
		
		
		/**
		 * role에 해당 사용자 추가
		 * 
		 * @author james
		 * @param roleOid
		 * @param userOids
		 * @return
		 */
		public int addRoleUser( String roleOid, String ... userOids ) {
				
				if ( StringUtils.isEmpty( roleOid ) || StringUtils.isEmpty( userOids ) ) {
						return 0;
				}
				
				
				for ( String userOid : userOids ) {
				
						roleUserRelBLO.insert( roleOid, UserInfo.getObjectType(), userOid );
				}
				

				return userOids.length;
		}

		/**
		 * role에 해당 사용자리스트 추가
		 * 
		 * @author james
		 * @param roleOid
		 * @param userList
		 */
		public void addRoleUser( String roleOid, List<UserInfo> userList ) {

				if ( StringUtils.isEmpty( roleOid ) || CollectionUtils.isEmpty( userList )) {
						return ;
				}
				
				roleUserRelBLO.insert( roleOid, userList );
				
		}
		
//		
//		/**
//		 * role에 Pgroup 추가
//		 * 
//		 * @author james
//		 * @param roleOid
//		 * @param pgroupOids
//		 * @return
//		 */
//		public int addRolePgroup( String roleOid, String ... pgroupOids ) {
//				
//				if ( StringUtils.isEmpty( roleOid ) || StringUtils.isEmpty( pgroupOids ) ) {
//						return 0;
//				}
//				
//				for ( String pgroupOid : pgroupOids ) {
//				
//						roleUserRelBLO.insert( roleOid, PGroupInfo.getObjectType(), pgroupOid );
//				}
//				
//				return pgroupOids.length;
//		}
//		
//		
//		/**
//		 * role에 Group 추가
//		 * 
//		 * @author james
//		 * @param roleOid
//		 * @param groupOids
//		 * @return
//		 */
//		public int addRoleGroup( String roleOid, String ... groupOids ) {
//				
//				if ( StringUtils.isEmpty( roleOid ) || StringUtils.isEmpty( groupOids ) ) {
//						return 0;
//				}
//				
//				
//				for ( String groupOid : groupOids ) {
//				
//						roleUserRelBLO.insert( roleOid, GroupInfo.getObjectType(), groupOid );
//				}
//				
//
//				return groupOids.length;
//		}
//		
		
		/**
		 * role에 권한을 추가한다.
		 * 
		 * @author james
		 * @param roleOid
		 * @param listRoleRight
		 * @return
		 */
		public int addRoleRight ( String roleOid, List<RoleRightInfo> listRoleRight ) {
				
				if ( CollectionUtils.isEmpty( listRoleRight )) {
						return 0;
				}
				
				for ( RoleRightInfo roleRightInfo : listRoleRight ) {
				
						roleRightBLO.insert( roleOid, roleRightInfo );
				}
				
				return listRoleRight.size();
		}

		
		/**
		 * role에 권한을 추가한다.
		 * 
		 * @author james
		 * @param roleOid
		 * @param listRoleRight
		 * @return
		 */
		public int addRoleRight ( String roleOid, RoleRightInfo ...roleRight ) {
				
				if ( StringUtils.isEmpty( roleRight ) ) {
						return 0;
				}
				
				for ( RoleRightInfo roleRightInfo : roleRight ) {
				
						roleRightBLO.insert( roleOid, roleRightInfo );
				}
				
				return roleRight.length;
		}
		
		
		/**
		 * roleIn
		 * 
		 * @author james
		 * @param roleInfoList
		 * @param cnd
		 * @return
		 */
		protected List<RoleInfo> commonList( List<RoleInfo> roleInfoList, RoleCnd cnd ) {

				if ( CollectionUtils.isEmpty( roleInfoList )) {
						return null;
				}

				List<String> roleOids = roleInfoList.stream().map( RoleInfo::getOid ).collect( Collectors.toList() );

				// 검색시 롤의 권한도 같이 가져올것인지 여부
				if ( cnd.isRoleRightSearch()) {

						RoleCnd roleCnd = new RoleCnd();
						roleCnd.setRoleOidList( roleOids );
						List<RoleRightInfo> roleRightList = roleRightBLO.listAll( roleCnd );
						
						// role에 해당 권한을 채워준다
						roleInfoList.forEach( role -> roleRightList.stream()
                    									 		   .filter( right -> role.getOid().equals( right.getRoleOid() ) ) 
                    											   .forEach( right -> {
                    													   				role.addRoleRight( right );
                    												}));
						
				}
				
				// 검색시 롤의 권한도 같이 가져올것인지 여부
				if ( cnd.isRoleUserRelSearch()) {
						
						RoleCnd roleCnd = new RoleCnd();
						roleCnd.setRoleOidList( roleOids );
						roleCnd.setFillUserInfo( cnd.isFillUserInfo() ); 								// roleUser에서 userInfo를 채워올지 여부  
						List<RoleUserRelInfo> roleUserList = roleUserRelBLO.listAll( roleCnd );
						
						if ( CollectionUtils.isNotEmpty( roleUserList )) {
							
								// role에 해당 사용자을 채워준다
								roleInfoList.forEach( role -> roleUserList.stream()
		                    									 		  .filter( user -> role.getOid().equals( user.getRoleOid() ) ) 
		                    											  .forEach( user -> {
		                    													   				role.addRoleUser( user );
		                    												}));
						}
						
						

				}
				
				
				return roleInfoList;
		}




}