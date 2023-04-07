package com.remarkablesoft.framework.service.org.group.model.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.common.constants.StatusType;
import com.remarkablesoft.framework.exception.BLORuntimeException;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.org.branch.model.impl.BranchBLO;
import com.remarkablesoft.framework.service.org.company.vo.CompanyInfo;
import com.remarkablesoft.framework.service.org.group.vo.GroupCnd;
import com.remarkablesoft.framework.service.org.group.vo.GroupInfo;
import com.remarkablesoft.framework.service.org.group.vo.NullGroupInfo;
import com.remarkablesoft.framework.service.org.user.model.impl.UserDAO;
import com.remarkablesoft.framework.service.org.user.vo.UserCnd;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	org-group
 * 		@프로그램 ID		:	GroupBLO.java
 * 		@프로그램 개요 		:	GroupBLO 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 4. 3.	:	james	-	신규생성
 * 		1.1  2019. 9. 29.	:	james	-	트리구조 변경으로 TreeNodeCnd를 상속받아서 처리
 * 		============================================================================
 */
@BLO
public class GroupBLO  {

		
		@Autowired
		protected GroupDAO groupDAO;
		
		@Autowired
		protected UserDAO userDAO;
		
		@Autowired
		protected BranchBLO branchBLO;
		
		
		@Cacheable( value = "group" , keyGenerator = "cacheKeyGenerator")
		public boolean exist( String oid ) {
				return groupDAO.existByOid( oid );
		}

		/**
		 * 그룹리스트를 가져옵니다.
		 * @param groupCnd
		 * @return
		 */
		@Cacheable( value = "group" , keyGenerator = "cacheKeyGenerator")
		public List<GroupInfo> listAll( GroupCnd groupCnd ) {

				List<GroupInfo> groupList = groupDAO.listAll( groupCnd );

				if ( CollectionUtils.isEmpty( groupList )) {
						return null;
				}

				// 자신의 하위그룹들 리스트를 찾아서 담는다.
				fillGroupChild( groupList, groupCnd );

				// depth를 채운다.
				fillGroupDepth( groupList);


				/** 그룹별 인원수까지 채워 정보를 가져옵니다. */
				if ( groupCnd.getIsUserCntSearch() ) {
						fillGroupUserCnt( groupList );
				}

				if( groupCnd.getIsBranchSearch() ) {
						branchBLO.fillBranchInfo( groupList );
				}

				return groupList;
		}

		@Cacheable( value = "group" , keyGenerator = "cacheKeyGenerator")
		public List<GroupInfo> list( GroupCnd cnd ) {

				List<GroupInfo> list = groupDAO.list( cnd );
				if( cnd.getIsUserCntSearch() ) {
					fillGroupUserCnt( list );
				}
				return groupDAO.list( cnd );
		}


		/**
		 * 1레벨의 리스트만 가져옵니다.
		 *
		 *
		 * @param groupCnd
		 * @return
		 */
		@Cacheable( value = "group" , keyGenerator = "cacheKeyGenerator")
		public List<GroupInfo> listFirstLevel( GroupCnd groupCnd ) {

				List<GroupInfo> groupList = listAll( groupCnd );

				if ( CollectionUtils.isEmpty( groupList )) {
					return null;
				}

				// 1레벨이 아니면 제거
				List<GroupInfo> firstLevel  = new ArrayList<>();
				String fullPathIndex = "";
				for ( GroupInfo groupInfo : groupList ) {

					// 제외할 그룹처리
					if ( groupCnd.getExceptGroupOid().contains( groupInfo.getOid() )) {
						continue;
					}

					if ( groupCnd.getIsExceptParentGroupIsRoot() && SystemConstants.ROOT_TREE_OID.equals( groupInfo.getParentOid() )) {
						continue;
					}

					// 1레벨만 처리
					if ( groupInfo.getFullPathIndex().indexOf( fullPathIndex ) < 0) {

						firstLevel.add( groupInfo );
					}

					fullPathIndex = groupInfo.getFullPathIndex();
				}


				return firstLevel;

		}



		/**
		 * 자신의 하위그룹들 리스트를 찾아서 담는다.
		 * @param groupList
		 */
		public void fillGroupChild( List<GroupInfo> groupList, GroupCnd groupCnd ) {

				if( CollectionUtils.isEmpty( groupList ) || !GroupCnd.LIST_TYPE_TREE.equals( groupCnd.getListType() ) ) {
						return;
				}

				for ( int i = 0 ; i < groupList.size(); i++ ) {
						GroupInfo groupInfo = groupList.get( i );

						//parent group을 찾아 해당 groupInfo의 child로 들어감
						for ( int j = 0 ; j < i ; j ++ ) {
								GroupInfo parentInfo = groupList.get( j );

								if( groupInfo.getParentOid().equals( parentInfo.getOid() ) ) {
										parentInfo.addChildGroupList( groupInfo );
								}
						}
				}

				/**
				 * 최상위 부모의 바로 아래 노드가 아니면 모두 자식노드이므로 자식노드는 리스트에서 지움.
				 * 자식노드도 하위노드로 출력되는 것이 아닌 리스트에 그대로 출력되게 하려면 아래를 주석처리하면 됩니다.
				 */
				for ( int i = 0 ; i < groupList.size(); i ++ ) {
						if( !groupList.get( i ).getParentOid().equals( groupCnd.getParentOid() ) ) {
								groupList.remove( i );
								i --;
						}
				}
		}

		@Cacheable( value = "group" , keyGenerator = "cacheKeyGenerator")
		public GroupInfo get( String oid ) {

				if ( StringUtils.isEmpty( oid ) ) {
						throw new BLORuntimeException( "oid가 필요합니다." );
				}

				GroupCnd cnd = new GroupCnd();
				cnd.setOid( oid );
				return get( cnd);
		}

		/** groupCnd의 isOwnerSearch와 isUserCntSearch에 따라 채워주는 정보가 다릅니다.
		 * isOwnerSearch일 때 부서장 정보를 채워줍니다.
		 * @param groupCnd
		 * @return
		 */
		@Cacheable( value = "group" , keyGenerator = "cacheKeyGenerator")
		public GroupInfo get( GroupCnd groupCnd ) {

				GroupInfo info = groupDAO.get( groupCnd );

				if ( info == null ) {
						return new NullGroupInfo();
				}

				if ( groupCnd.getIsOwnerSearch() ) {
						fillOwnerInfo( info );
				}
				if ( groupCnd.getIsUserCntSearch() ) {
						fillGroupUserCnt( info );
				}
				return info;
		}


		public GroupInfo saveOrUpdate( GroupInfo info ) {

				String oid = "";
				/**
				 * 같은 부모 내에 이름중복체크
				 */
				if( groupDAO.existDuplicate( info ) ) {
						throw new BLORuntimeException( "이미 존재하는 그룹명입니다." );
				}

				if ( StringUtils.isEmpty( info.getOid() ) ) {

						oid = OIDGenerator.generateOID();
						info.setOid( oid );
						return insert ( info );
				}
				else {

						return update( info );
				}
		}

		/**
		 * company를 그룹의 상위노드로 삽입합니다.
		 * @param companyInfo
		 * @return
		 */
		public GroupInfo insertCompany( CompanyInfo companyInfo ) {

			GroupInfo groupInfo = new GroupInfo();
			groupInfo.setOid( companyInfo.getOid() );
			groupInfo.setParentOid( SystemConstants.ROOT_TREE_OID );
			groupInfo.setName( companyInfo.getCompanyName() );

			return insert( groupInfo );
		}

		/**
		 * 위에 existGroupName
		 * name(그룹명)과 parentOid(부모)로 기존의 존재여부를 확인합니다.
		 * @param groupInfo
		 * @return
		 */
		@Cacheable( value = "group" , keyGenerator = "cacheKeyGenerator")
		public boolean checkExistGroupName( GroupInfo groupInfo ) {

				if( StringUtils.isEmpty( groupInfo.getName() ) ) {
					throw new BLORuntimeException( "그룹명이 비어있습니다." );
				}
				GroupInfo existInfo = groupDAO.checkExist( groupInfo );
				return ( existInfo != null ) ? true : false;
		}

		/**
		 * 중복되는 그룹 정보를 가져옵니다.
		 * @param groupInfo
		 * @return
		 */
		@Cacheable( value = "group" , keyGenerator = "cacheKeyGenerator")
		public GroupInfo getDuplicateGroup( GroupInfo groupInfo ) {
				return groupDAO.checkExist( groupInfo );
		}

		@CacheEvict( value = "group", allEntries = true )
		public GroupInfo insert( GroupInfo groupInfo ) {

				//그룹명 중복체크 여부가 true이면 중복체크 실행
				if( groupInfo.isCheckExist() ) {
					checkExistGroupName( groupInfo );
				}

				if ( StringUtils.isEmpty( groupInfo.getOid() ) ) {
						groupInfo.setOid( OIDGenerator.generateOID() );
				}
				if ( StringUtils.isEmpty( groupInfo.getInputDate() ) ) {
						groupInfo.setInputDate( LocalDateTime.now() );
				}

				/** 부모노드가 빈 상태로 들어온다면 최상위 노드의 자식노드입니다. */
				if ( StringUtils.isEmpty( groupInfo.getParentOid() ) ) {
						groupInfo.setParentOid( SystemConstants.ROOT_TREE_OID );
				}

				GroupInfo parentGroup = groupDAO.get( groupInfo.getParentOid() );
				parentGroup.setSubLastIndex( parentGroup.getSubLastIndex() + 1 );
				groupDAO.update( parentGroup );

				groupInfo.setFullPathIndex( parentGroup.getFullPathIndex() + String.format( SystemConstants.FULL_PATH_SHORT_LENGTH, (parentGroup.getSubLastIndex())) + SystemConstants.FULL_PATH_INDEX_DELIMITER );

				int result = groupDAO.insert( groupInfo );

				return result > 0 ? groupInfo : null;
		}


		@CacheEvict( value = "group", allEntries = true )
		public GroupInfo update( GroupInfo info ) {

				if ( !groupDAO.existByOid( info.getOid() ) ) {
						throw new BLORuntimeException( "존재하지 않는 그룹 oid 입니다." );
				}

				// 현재 중복체크는 프론트에서 진행합니다.
				//				if ( existGroupName( groupInfo ) ) {
				//						new BLORuntimeException( "중복된 이름 입니다." );
				//				}

				int result = groupDAO.update( info );
				return result > 0 ? info : null;
		}


		/**
		 * 해당 부서에 속한 사용자들도 모두 삭제( 삭제하려는 사용자리스트가 GroupInfo의 userOidList에 들어있어야 함. )
		 * @param info
		 */
		@CacheEvict( value = "group", allEntries = true )
		public int delete( GroupCnd cnd ) {

				if ( StringUtils.isEmpty( cnd.getOid() ) ) {
						return 0;
				}

				List<GroupInfo> list = null;

				if ( StringUtils.isEmpty( cnd.getFullPathIndex() )) {

						 GroupInfo info =	groupDAO.get( cnd.getOid() );

						 if ( info == null) {
								 return 0;
						 }

						 cnd.setOid( "" ); // 지우지마세요.
						 cnd.setFullPathIndex( info.getFullPathIndex() );
				}

				list = groupDAO.listAll( cnd );

				if( CollectionUtils.isNotEmpty( list ) ) {

					List<String> groupList = list.stream().map( GroupInfo::getOid ).collect( Collectors.toList() );

					// 그룹리스트로 사용자 삭제처리
					deleteProcessByGroupList( groupList );
				}

				return groupDAO.delete( cnd );
		}
		
		
		/**
		 * 그룹 삭제시 사용
		 * 사용자 그룹oid를 SystemConstract.DELETED_GROUP_OID_ROOT로 변경
		 * 상태코드를 사용하지 않음으로 변경
		 *
		 * @author james
		 * @param groupList
		 * @return
		 */
		public int deleteProcessByGroupList( List<String> groupList ) {

				if ( CollectionUtils.isEmpty( groupList ) ) {
						return 0;
				}

				UserCnd userCnd = new UserCnd();
				userCnd.setGroupOidList( groupList );
				List<UserInfo> userList = userDAO.listAll( userCnd );
				List<String> userOidList = userList.stream().map( UserInfo::getOid ).collect( Collectors.toList() );

				if ( CollectionUtils.isEmpty( userOidList ) ) {
						return 0;
				}

				UserInfo userInfo = SystemFactory.getUserInfo();
				userInfo.setUpdateDate( LocalDateTime.now() );
				userInfo.setUserOidList( userOidList );
				userInfo.setGroupOid( SystemConstants.DELETED_GROUP_OID_ROOT );
				userInfo.setStatusTypeFlag( StatusType.NOT_USE.getKey() );

				return userDAO.updateStatusTypeFlagByUserOidList( userInfo );
		}

		/**
		 * group의 인원수 정보를 채워줍니다.
		 *
		 * @param groupList
		 */
		public void fillGroupUserCnt( List<GroupInfo> groupList ) {

				List<GroupInfo> cntGroupList = groupDAO.listUserCntByGroup( "" );

				for ( GroupInfo groupInfo : groupList ) {
						for ( GroupInfo cntGroup : cntGroupList ) {
								if ( groupInfo.getOid().equals( cntGroup.getOid() ) ) {
										groupInfo.setUserCnt( cntGroup.getUserCnt() );
										break;
								}
						}
				}
		}

		/**
		 * group의 인원수 정보를 채워줍니다.
		 *
		 * @author james
		 * @param groupInfo
		 */
		public void fillGroupUserCnt( GroupInfo groupInfo ) {

				if ( StringUtils.isEmpty( groupInfo.getOid() ) ) {
						return;
				}

				List<GroupInfo> listGroup = groupDAO.listUserCntByGroup( groupInfo.getOid() );

				if ( CollectionUtils.isEmpty( listGroup )) {
					return;
				}

				groupInfo.setUserCnt( listGroup.get( 0 ).getUserCnt() );
		}


		/**
		 * 부서장의 정보를 가져옵니다.
		 *
		 * @author james
		 * @param groupInfo
		 */
		public void fillOwnerInfo( GroupInfo groupInfo ) {

				if ( StringUtils.isEmpty( groupInfo.getOwner() ) ) {
						return;
				}

				UserInfo ownerInfo = userDAO.getUserByOid( groupInfo.getOwner() );
				groupInfo.setOwnerInfo( ownerInfo );

		}


		/**
		 * 자신의 Depth를 채운다.
		 *
		 * @param groupList
		 */
		protected void fillGroupDepth( List<GroupInfo> groupList ) {

				if( CollectionUtils.isEmpty( groupList ) 	 ) {
						return;
				}

				for ( GroupInfo groupInfo : groupList ) {
						// 최상위 루트에 한개가 무조건 포함됨으로 -1을 한다.
						int nDepth = StringUtils.countMatches( groupInfo.getFullPathIndex(), SystemConstants.FULL_PATH_INDEX_DELIMITER );
						nDepth = nDepth - 1;
						groupInfo.setDepthVC( nDepth );
					}
		}

}
