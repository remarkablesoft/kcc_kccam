package com.remarkablesoft.framework.service.org.user.model.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.remarkablesoft.site.kccam.service.onetoone.onetoone.model.impl.OneToOneBLO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.exception.BLORuntimeException;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.app.device.model.impl.DeviceBLO;
import com.remarkablesoft.framework.service.app.device.model.impl.DeviceRelBLO;
import com.remarkablesoft.framework.service.authority.role.model.impl.RoleBLO;
import com.remarkablesoft.framework.service.authority.role.model.impl.RoleUserRelBLO;
import com.remarkablesoft.framework.service.authority.role.vo.RoleInfo;
import com.remarkablesoft.framework.service.authority.role.vo.RoleUserRelInfo;
import com.remarkablesoft.framework.service.mgnt.code.model.impl.CodeBLO;
import com.remarkablesoft.framework.service.mgnt.code.vo.CodeInfo;
import com.remarkablesoft.framework.service.mgnt.system.vo.SystemInfo;
import com.remarkablesoft.framework.service.org.branch.model.impl.BranchBLO;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;
import com.remarkablesoft.framework.service.org.user.model.UserPasswordBLO;
import com.remarkablesoft.framework.service.org.user.vo.NullUserInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserCnd;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.service.storage.file.model.impl.FileBLO;
import com.remarkablesoft.framework.service.storage.file.vo.FileCnd;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.util.AutheUtils;
import com.remarkablesoft.framework.web.util.WebUtils;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	org-user
 * 		@프로그램 ID		:	UserBLO.java
 * 		@프로그램 개요 		:	사용자 BLO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2014. 5. 12.	:	james	-	신규생성
 * 		1.1  2019. 10. 16	:	최원준	-	protected Method들을 파일 하단으로 이동 및 fill Method를 Lambda로 변경
 * 		============================================================================
 */
@BLO
public class UserBLO {

		@Autowired
		protected UserBLO _self;					// aop로 사용할수 있는 메소드가 있어서 따로 처리
		
		@Autowired
		protected CodeBLO codeBLO;
		
		@Autowired
		protected FileBLO fileBLO;
		
		@Autowired
		protected DeviceBLO deviceBLO;
		
		@Autowired
		protected DeviceRelBLO deviceRelBLO;
		
		@Autowired
		protected UserDAO userDAO;
		
		@Autowired
		protected BranchBLO branchBLO;
		
		@Autowired
		protected RoleBLO roleBLO;
		
		@Autowired
		protected UserPasswordBLO userPasswordBLO;
		
		@Autowired
		protected RoleUserRelBLO roleUserRelBLO;
		
		@Autowired
		protected OneToOneBLO oneToOneBLO;
		
		
		public UserInfo insertOrUpdate( UserInfo user ) {

				if ( user == null ) {
						return new NullUserInfo();
				}

				if ( (StringUtils.hasText( user.getOid() ) && userDAO.exist( user.getOid() )) || userDAO.existById( user ) ) {
						return _self.update( user );
				}
				else {
						return _self.insert( user );
				}

		}

		@CacheEvict( value = "user", allEntries = true )
		public UserInfo insert( UserInfo user ) {

				return _self.insert( user, true );
		}

		@CacheEvict( value = "user", allEntries = true )
		public UserInfo insert( UserInfo user, boolean isIdCheck ) {

				if ( user == null )
						return new NullUserInfo();

				if ( StringUtils.isEmpty( user.getOid() ) ) {
						user.setOid( OIDGenerator.generateOID() );
				}

				if ( StringUtils.hasText( user.getPwd() ) && user.getPwd().length() < userPasswordBLO.getEncryptCharLength() ) {
						user.setPwd( userPasswordBLO.encrypt( user.getPwd() ) );
				}

				if ( StringUtils.hasText( user.getPwd2() ) && user.getPwd2().length() < userPasswordBLO.getEncryptCharLength() ) {
						user.setPwd2( userPasswordBLO.encrypt( user.getPwd2() ) );
				}
				
				// ID공백 제거
				if ( StringUtils.isNotEmpty( user.getUserId() ) ) {
						
						user.setUserId( user.getUserId().trim() );
				}
				
				if ( isIdCheck ) {
						// 이미 아이디가 있다면 아이디 중복
						if ( userDAO.existById( user ) ) {
								throw new BLORuntimeException( "아이디 [" + user.getUserId() + "]는 이미 사용하고 있습니다." );
						}
				}

				// 프로파일이 있을 경우
				if( user.getProfile() != null ) {
						user.getProfile().setFileType( UserInfo.FILE_TYPE_PROFILE );
						user.getProfile().setThumbYn( SystemConstants.FLAG_YES );
				}
				fileBLO.insert( user.getProfile(), UserInfo.getObjectType(), user.getOid(), user.getGroupOid() );
				// 해당 사용자의 첨부파일이 있을 경우
				fileBLO.insert( user );
				
				// 롤 등록
				if ( CollectionUtils.isNotEmpty( user.getUserRoleList() ) ) {

						// role 등록
						// TODO RoleTargetRelBLO에서 롤을 등록하도록 변경
						user.getUserRoleList().forEach( role -> {
								roleUserRelBLO.insert( role.getOid(), UserInfo.getObjectType(), user.getOid() );
						} );
				}
				user.setInputDate( LocalDateTime.now() );
				int result = userDAO.insert( user );

				return result > 0 ? user : new NullUserInfo();
		}

		
		@CacheEvict( value = "user", allEntries = true )
		public int delete( String oid ) {

				if ( StringUtils.isEmpty( oid ) ) {
						return 0;
				}

				// 파일
				fileBLO.deleteByTarget( oid, UserInfo.getObjectType() );

				// 디바이스
				deviceRelBLO.deleteByUserOid( oid );

				// 사용자의 롤
				roleUserRelBLO.delete( UserInfo.getObjectType(), oid );
				
				// 작성한 1대1문의글 삭제
				oneToOneBLO.deleteByInputUser( oid );
				
				return userDAO.delete( oid );

		}

		@CacheEvict( value = "user", allEntries = true )
		public int deleteById( String id ) {

				if ( StringUtils.isEmpty( id ) ) {
						return 0;
				}

				UserInfo info = userDAO.getUserById( id );

				if ( info == null ) {
						return 0;
				}

				return delete( info.getOid() );
		}

		/**
		 * 조건으로 여러개를 삭제할때.
		 * 사용자 삭제는 중요함으로 하나씩 삭제하는걸로 변경 by James 2020-02-15
		 *
		 * @param cnd
		 * @return
		 */
		@CacheEvict( value = "user", allEntries = true )
		public int deleteByCnd( UserCnd cnd ) {

				if ( cnd == null ) {
						return 0;
				}

				List<String> userOids = cnd.getUserOidList();

				if ( CollectionUtils.isNotEmpty( cnd.getGroupOidList() ) ) {

						List<UserInfo> userList = userDAO.listAll( cnd );

						if ( CollectionUtils.isNotEmpty( userList ) ) {
								List<String> userOidsByGroup = userList.stream().map( UserInfo::getOid ).collect( Collectors.toList() );
								userOids.addAll( userOidsByGroup );
						}

				}

				// 사용자가 없다면 사용자 삭제부분은 Pass 한다
				if ( CollectionUtils.isEmpty( userOids ) ) {
						
						return 0;
				}

				int delCnt = 0;

				// 사용자 삭제는 건바이건으로 처리.
				for ( String oid : userOids ) {

						delCnt += delete( oid );
				}

				return delCnt;
		}
		

		@CacheEvict( value = "user", allEntries = true )
		public int updateStatusTypeFlagByList( String statusTypeFlag, List<String> userIds ) {
		
				if ( StringUtils.isEmpty( userIds )) {
						return 0;
				}
				
				return userDAO.updateStatusTypeFlagByList( statusTypeFlag, userIds );
		}
		
		
		@CacheEvict( value = "user", allEntries = true )
		public int updateStatusTypeFlagById( String statusTypeFlag, String userId ) {
		
				if ( StringUtils.isEmpty( userId )) {
						return 0;
				}
				
				return userDAO.updateStatusTypeFlagById( statusTypeFlag, userId );
		}
		
		
		
		@CacheEvict( value = "user", allEntries = true )
		public UserInfo update( UserInfo user ) {

				if ( user == null ) {
						return new NullUserInfo();
				}

				if ( StringUtils.isEmpty( user.getOid() ) && StringUtils.isEmpty( user.getUserId() ) ) {
						throw new BLORuntimeException( "사용자 oid가 비어있습니다." );
				}

				if ( StringUtils.hasText( user.getPwd() ) && user.getPwd().length() < userPasswordBLO.getEncryptCharLength() ) {
						user.setPwd( userPasswordBLO.encrypt( user.getPwd() ) );
				}

				if ( StringUtils.hasText( user.getPwd2() ) && user.getPwd2().length() < userPasswordBLO.getEncryptCharLength() ) {
						user.setPwd2( userPasswordBLO.encrypt( user.getPwd2() ) );
				}
				
				//수정날짜
				user.setUpdateDate( LocalDateTime.now() );

				if ( user.getProfile() != null ) {

						FileCnd fileCnd = new FileCnd();
						fileCnd.setFileType( UserInfo.FILE_TYPE_PROFILE );
						fileCnd.setTargetOid( user.getOid() );
						fileCnd.setTargetObject( UserInfo.getObjectType() );
						fileBLO.deleteByCnd( fileCnd );
						
						user.getProfile().setFileType( UserInfo.FILE_TYPE_PROFILE );
						user.getProfile().setThumbYn( SystemConstants.FLAG_YES );
						fileBLO.insert( user.getProfile(), UserInfo.getObjectType(), user.getOid(), user.getGroupOid() );
				}

				if ( CollectionUtils.isNotEmpty( user.getFileList() ) ) {

						FileCnd fileCnd = new FileCnd();
						fileCnd.setFileType( UserInfo.FILE_TYPE_NOMAL );
						fileCnd.setTargetOid( user.getOid() );
						fileCnd.setTargetObject( UserInfo.getObjectType() );

						fileBLO.deleteByCnd( fileCnd );
						user.getProfile().setFileType( UserInfo.FILE_TYPE_NOMAL );
						fileBLO.insert( user.getFileList(), UserInfo.getObjectType(), user.getOid(), user.getGroupOid() );
				}

				// 롤 등록
				if ( CollectionUtils.isNotEmpty( user.getUserRoleList() ) ) {

						deleteAndInsertRoleTargetRel( user );
				}

				int result = userDAO.update( user );
				return result > 0 ? user : new NullUserInfo();
		}

		
		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public UserInfo getUser( String oid ) {

				if ( StringUtils.isEmpty( oid ) ) {
						return new NullUserInfo();
				}

				UserCnd cnd = new UserCnd();
				cnd.setOid( oid );

				return getUser( cnd );
		}

		/**
		 * 유저의 기본정보만 반환
		 * 
		 * @author james
		 * @param oid
		 * @return
		 */
		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public UserInfo getOnlyUser( String oid ) {

				if ( StringUtils.isEmpty( oid ) ) {
						return new NullUserInfo();
				}

				return userDAO.getUserByOid(  oid );
		}
		
		
		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public UserInfo getUser( UserCnd cnd ) {

				UserInfo user = userDAO.getUser( cnd );

				if ( user == null ) {
						return new NullUserInfo();
				}
				if( cnd.isProfileSearch() ) {
					fillUserInfo( user );
				}
				fillAddUserInfo( user, cnd );

				return user;
		}

		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public UserInfo getUserById( String id ) {

				if ( StringUtils.isEmpty( id ) ) {
						return new NullUserInfo();
				}

				UserCnd cnd = new UserCnd();
				cnd.setUserId( id );

				return getUser( cnd );
		}



		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public PageList<UserInfo> listUser( UserCnd cnd ) {

				PageList<UserInfo> userList = userDAO.listUser( cnd );

				if ( CollectionUtils.isEmpty( userList ) ) {
						return null;
				}
				cnd.setBranchSearch( true );
				
				fillUserData( userList, cnd );
				
				return userList;

		}

		/**
		 * 현재 존재하는 사용자 수를 모두 가져옵니다.
		 * @param userCnd
		 * @return
		 */
		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public int listAllCount( UserCnd userCnd ) {

				PageList<UserInfo> list = userDAO.listUser( userCnd );
				return CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount();
		}

		/**
		 * 지점별 유저 수 리스트
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public List<UserInfo> listByBranchOid( UserCnd cnd ) {

				return userDAO.listByBranchOid( cnd );
		}

		/**
		 * <pre>
		 * 지점별 직원 수 리스트
		 * 사용방법 : 해당 지점별로 사융자 카운트를 구함
		 *
		 * UserCnd cnd = new UserCnd();
		 * List<UserInfo> list =userBLO.getUserCountListByBranch ( cnd);
		 * list.stream().forEach(user -> System.out.println( user.getBranchOid() + " : " + user.getCount()));
		 *
		 * output
		 * 1RFxRQeM000 : 26
		 * 1RL9Af59000 : 97
		 * </pre>
		 *
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public List<UserInfo> getUserCountListByBranch( UserCnd cnd ) {

				return userDAO.getUserCountListByBranch( cnd );
		}

		/**
		 * 유저 리스트
		 * 그룹정보도 함께 채워줍니다.
		 *
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public List<UserInfo> listAll( UserCnd cnd ) {

				List<UserInfo> userList = userDAO.listAll( cnd );
				
				fillUserData( userList, cnd );

				return userList;
		}

		/**
		 * 사용자 카운트만 따로 필요할때가 있어서.
		 */
		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public int listUserCnt( UserCnd cnd ) {
				return userDAO.listUserCnt( cnd );
		}

		/**
		 * 유저-롤 뷰에서 유저 페이지 리스트를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public PageList<UserInfo> listRoleUser( UserCnd cnd ) {
				return userDAO.listRoleUser( cnd );
		}
		
		/**
		 * 유저-롤 뷰 리스트를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public List<UserInfo> listAllRoleUser( UserCnd cnd ) {
				return userDAO.listAllRoleUser( cnd );
		}
		

		
		/**
		 * objectRel을 delete 후, insert 합니다.
		 * 
		 * @author sirena
		 * @param user
		 */
		public void deleteAndInsertRoleTargetRel( UserInfo user ) {
				
				if ( CollectionUtils.isEmpty( user.getUserRoleList() ) ) {
						return;
				}

    			// 기존 role 제거
    			roleUserRelBLO.delete( UserInfo.getObjectType(), user.getOid() );
    
    			// role 등록
    			user.getUserRoleList().stream().forEach( role -> {
    
    					RoleUserRelInfo roleUserRelInfo = SystemFactory.getRoleUserRelInfo();
    					roleUserRelInfo.setRoleOid( role.getOid() );
    					roleUserRelInfo.setTargetObject( UserInfo.getObjectType() );
    					roleUserRelInfo.setTargetOid( user.getOid() );
    					roleUserRelBLO.insert( roleUserRelInfo );
    			} );
		}
		
		/**
		 * 해당 사용자의 정보를 연결된 문자열로 반환
		 *
		 * @param user
		 * @return
		 */
		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public String convertUserInfoList( String userOid ) {

				if ( StringUtils.isEmpty( userOid ) ) {
						userOid = AutheUtils.getLoginUserOid();
				}
				
				if ( StringUtils.isEmpty( userOid )) {
						return "";
				}

				UserInfo user = AutheUtils.getLoginUserInfo();

				// 로그인한 유저와 해당 유저가 같지 않으면 해당 유저의 정보를 구해온다.
				if ( !userOid.equals( user.getOid() ) ) {
						user = getUser( userOid );
				}

				return convertUserInfoList( user );
		}

		/**
		 * 해당 사용자의 정보를 연결된 문자열로 반환
		 *
		 * 회사 oid + 회사명, 지점  oid + 지점 명, 그룹 oid + 그룹명
		 * oid + 아이디 + 이름,
		 * 직급 + 직급명
		 * 직책 + 직책명
		 * 잡
		 * 조직(기관) 코드 +  조직(기관) 이름
		 * 
		 *
		 * @param user
		 * @return
		 */
		public String convertUserInfoList( UserInfo user ) {

				if ( user == null || user instanceof NullUserInfo ) {
						return "";
				}
				
				Map<String, String> map = new HashMap<String, String>();
				
				map.put( "companyOid", StringUtils.getNullAndWhiespaceRemove( user.getCompanyOid() ) );
				map.put( "companyName", StringUtils.getNullAndWhiespaceRemove( user.getCompanyName() ) );
				map.put( "branchOid", StringUtils.getNullAndWhiespaceRemove( user.getBranchOid() ) );
				map.put( "branchName", StringUtils.getNullAndWhiespaceRemove( user.getBranchName() ) );
				map.put( "groupOid", StringUtils.getNullAndWhiespaceRemove( user.getGroupOid() ) );
				
				map.put( "groupName", StringUtils.getNullAndWhiespaceRemove( user.getGroupName() ) );
				map.put( "userType", StringUtils.getNullAndWhiespaceRemove( user.getUserType() ) );
				map.put( "oid", StringUtils.getNullAndWhiespaceRemove( user.getOid() ) );
				map.put( "id", StringUtils.getNullAndWhiespaceRemove( user.getUserId() ) );
				map.put( "name", StringUtils.getNullAndWhiespaceRemove( user.getName() ) );
				
				map.put( "email", StringUtils.getNullAndWhiespaceRemove( user.getEmail() ) );
				map.put( "phone", StringUtils.getNullAndWhiespaceRemove( user.getPhone() ) );
				map.put( "rank", StringUtils.getNullAndWhiespaceRemove( user.getRank() ) );
				map.put( "rankName", StringUtils.getNullAndWhiespaceRemove( user.getRankName() ) );
				map.put( "position", StringUtils.getNullAndWhiespaceRemove( user.getPosition() ) );
				
				map.put( "positionName", StringUtils.getNullAndWhiespaceRemove( user.getPositionName() ) );
				map.put( "job", StringUtils.getNullAndWhiespaceRemove( user.getJob() ) );
				map.put( "organizationCode", StringUtils.getNullAndWhiespaceRemove( user.getOrganizationCode() ) );
				map.put( "organizationName", StringUtils.getNullAndWhiespaceRemove( user.getOrganizationName() ) );
				
				String json = WebUtils.GSON.toJson( map );
				return json;
		}


		/**
		 * userInfoList 문자열로 해당 유저를 반환
		 *
		 * @param user
		 * @return
		 */
		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public UserInfo convertUserInfo( String userInfoList ) {

				if ( StringUtils.isEmpty( userInfoList ) ) {
						return new NullUserInfo();
				}

				UserInfo user = WebUtils.GSON.fromJson( userInfoList, UserInfo.class );
				return user;
		}

		/**
		 * 유저 수 구하기. 검색
		 *
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public int getCount( UserCnd cnd ) {

				return userDAO.getCount( cnd );
		}
		
		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public boolean existById( UserInfo user ) {
				
				return userDAO.existById( user );
		}
		
		/**
		 * userOid 리스트로 statusTypeFlag를 변경합니다.
		 * 
		 * @param user
		 * @return
		 */
		@CacheEvict( value = "user", allEntries = true )
		public int updateStatusTypeFlagByUserOidList( UserInfo user ) {

				UserInfo userInfo = SystemFactory.getUserInfo();
				userInfo.setUpdateDate( LocalDateTime.now() );
				userInfo.setUserOidList( user.getUserOidList() );
				userInfo.setStatusTypeFlag( user.getStatusTypeFlag() );
				
				return userDAO.updateStatusTypeFlagByUserOidList( userInfo );
		}
		
		/**
		 * statusTypeFlag별 개수를 가져옵니다.
		 * 
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "user" , keyGenerator = "cacheKeyGenerator")
		public Map<String, Integer> getStatusCount( UserCnd cnd ) {
			
			return userDAO.getStatusCount( cnd );
		}
	
	
		public void fillInputUsersName( List<SystemInfo> systemList ) {
			
			if ( CollectionUtils.isEmpty( systemList ) ) {
				return;
			}
			
			List<String> inputUserOidList = systemList.stream().map( SystemInfo::getInputUser ).collect( Collectors.toList() );
			
			if ( CollectionUtils.isEmpty( inputUserOidList ) ) {
				return;
			}
			
			UserCnd userCnd = new UserCnd();
			userCnd.setUserOidList( inputUserOidList );
			List<UserInfo> userList = listAll( userCnd );
			
			if ( CollectionUtils.isEmpty( userList ) ) {
				return;
			}
			
			systemList.forEach( system -> userList.stream()
												  .filter( user -> StringUtils.hasText( user.getOid() ) )
												  .filter( user -> user.getOid().equals( system.getInputUser() ) )
												  .forEach( user -> system.setInputUserNameVC( user.getName() ) ) );
		}
	
		public void fillManagerInfo( List<SystemInfo> systemList ) {
			
			if ( CollectionUtils.isEmpty( systemList ) ) {
				return;
			}
			
			List<String> managerOidList = systemList.stream().map( SystemInfo::getManagerOid ).collect( Collectors.toList() );
			
			if ( CollectionUtils.isEmpty( managerOidList ) ) {
				return;
			}
			
			UserCnd userCnd = new UserCnd();
			userCnd.setUserOidList( managerOidList );
			List<UserInfo> userList = listAll( userCnd );
			
			if ( CollectionUtils.isEmpty( userList ) ) {
				return;
			}
			
			systemList.forEach( system -> userList.stream()
												  .filter( user -> StringUtils.hasText( user.getOid() ) )
												  .filter( user -> user.getOid().equals( system.getManagerOid() ) )
												  .forEach( user -> system.setManagerInfo( user ) ) );
			
		}
		
		
		protected void fillUserInfo( UserInfo user ) {

				if ( user == null || StringUtils.isEmpty( user.getOid() ) ) {
						return;
				}

				//	프로필정보를 채움
				FileCnd fileCnd = new FileCnd();
				fileCnd.setFileType( UserInfo.FILE_TYPE_PROFILE );
				fileCnd.setTargetOid( user.getOid() );
				fileCnd.setTargetObject( UserInfo.getObjectType() );

				FileInfo profile = fileBLO.getByCnd( fileCnd );
				user.setProfile( profile );

				//	유저에 엮인 일반 파일정보들을 가져옴
				fileCnd = new FileCnd();
				fileCnd.setFileType( UserInfo.FILE_TYPE_NOMAL );
				fileCnd.setTargetOid( user.getOid() );
				fileCnd.setTargetObject( UserInfo.getObjectType() );
				List<FileInfo> fileList = fileBLO.listAll( fileCnd );
				user.setFileList( fileList );

				// 사용자 OID로 자신이 가질수 있는 모든 롤을 체크한다. ( User, Pgroup, Group )
//				List<RoleInfo> userRoleList = roleBLO.listRoleByUserOid( user.getOid() );
//				user.setUserRoleList( userRoleList );

		}

		protected void fillAddUserInfo( UserInfo user, UserCnd cnd ) {

				//	사용자 기기정보
				if ( cnd.isDeviceSearch() ) {
						deviceBLO.fillDevice( user );
				}

				//	사용자 기기 수
				if ( cnd.isDeviceCntSearch() ) {
						deviceBLO.fillDeviceCnt( user );
				}

				//	지점정보
				if ( cnd.isBranchSearch() && StringUtils.hasText( user.getBranchOid() ) ) {

						BranchInfo branchInfo = branchBLO.get( user.getBranchOid() );
						user.setBranch( branchInfo );
						user.setBranchName( branchInfo.getName() );
				}


				//	rank 직급
				if ( cnd.isRankSearch() && StringUtils.hasText( user.getRank() ) ) {

						CodeInfo codeInfo = codeBLO.get( user.getRank() );
						if ( codeInfo != null ) {
								user.setRankName( codeInfo.getCodeValue() );
						}
				}


		}

		/**
		 * 여러데이터를 채워준다.
		 *
		 * @param userList
		 * @param cnd
		 */
		protected void fillUserData( List<UserInfo> userList, UserCnd cnd ) {

				List<String> userOids = getUserOidList( userList );

				/** 프로필 파일 여부 */
				if ( cnd.isProfileSearch() ) {
						fileBLO.fillProfile( userList );
				}

				/** 사용자가 사용중인 디바이스 정보를 함께 가져옵니다. */
				if ( cnd.isDeviceSearch() ) {
						deviceBLO.fillDevice( userList, userOids );
				}

				/** 사용자 별로 사용 중인 디바이스의 개수를 가져옵니다. */
				if ( cnd.isDeviceCntSearch() ) {
						deviceBLO.fillDeviceCnt( userList, userOids );
				}

				/** 본사,지점정보를 채울지 여부 */
				if ( cnd.isBranchSearch() ) {
						branchBLO.fillBranch( userList );
				}

		}

		/** userlist에 있는 사용자가 포함되어있는 group들의 oid를 List에 담아 리턴해줍니다. */
		protected List<String> getGroupOidList( List<UserInfo> userList ) {

				if ( CollectionUtils.isEmpty( userList ) ) {
						return null;
				}

				return userList.stream().map( UserInfo::getGroupOid ).collect( Collectors.toList() );
		}

		protected List<String> getUserOidList( List<UserInfo> userList ) {

				if ( CollectionUtils.isEmpty( userList ) ) {
						return null;
				}

				return userList.stream().map( UserInfo::getOid ).collect( Collectors.toList() );
		}

		
		
		/**
		 * null을 공백으로. 공백은 전부 제거하고 반환
		 * 
		 * @author james
		 * @param target
		 * @return
		 */
		protected String getCheckString( String target ) {

				return StringUtils.trimAllWhitespace( StringUtils.defaultString( target));
		}
}
