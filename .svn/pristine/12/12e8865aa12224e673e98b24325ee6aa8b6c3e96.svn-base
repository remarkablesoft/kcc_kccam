package com.remarkablesoft.framework.service.org.user.model.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.org.user.vo.UserCnd;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 설명 : 사용자 DAO
 *
 * @author james
 * @since 2014. 5. 9.
 *
 */
@DAO
public class UserDAO extends BaseDAO {

		public int insert( UserInfo user ) {
				return sql().insert( id( "insert" ), user );
		}

		/**
		 * 조직도 연동시 사용.
		 * 유저의 모든 필드가 있는것은 아님.
		 */
		public int insertBulk( List<UserInfo> list ) {
				return sql().insert( id( "insertBulk" ), list );
		}

		public int delete( String oid ) {
				return sql().delete( id( "delete" ), oid );
		}

		public int deleteUserById( String id ) {
				return sql().delete( id( "deleteUserById" ), id );
		}

		public int deleteUserByCnd( UserCnd cnd ) {
				return sql().delete( id( "deleteUserByCnd" ), cnd );
		}

		public UserInfo getUserByOid( String oid ) {
				return (UserInfo) sql().selectOne( id( "getUserByOid" ), oid );
		}

		public UserInfo getUserById( String id ) {
				return (UserInfo) sql().selectOne( id( "getUserById" ), id );
		}

		public UserInfo getUser( UserCnd cnd ) {
				return sql().selectOne( id( "getUser" ), cnd );
		}

		public boolean checkDuplicateByUserCnd( UserCnd cnd ) {

				Object obj = sql().selectOne( id( "checkDuplicateByUserCnd" ), cnd );
				return convertIntegerToBoolean( obj );
		}

		public boolean exist( String oid ) {

				Object obj = sql().selectOne( id( "exist" ), oid );
				return convertIntegerToBoolean( obj );
		}

		public boolean existById( UserInfo user ) {

				Object obj = sql().selectOne( id( "existById" ), user );
				return convertIntegerToBoolean( obj );
		}

		public boolean checkDuplicateId( UserInfo user ) {
				Object obj = sql().selectOne( id( "checkDuplicateId" ), user );
				return convertIntegerToBoolean( obj );
		}

		public int updateStatusTypeFlagByList( String statusTypeFlag, List<String> userIds ) {

				if ( StringUtils.isEmpty( userIds ) ) {
						return 0;
				}

				Map<String, Object> map = new HashMap<>();
				map.put( "statusTypeFlag", statusTypeFlag );
				map.put( "userIds", userIds );

				return sql().update( id( "updateStatusTypeFlagByList" ), map );
		}

		public int updateStatusTypeFlagById( String statusTypeFlag, String userId ) {

				if ( StringUtils.isEmpty( userId ) ) {
						return 0;
				}

				Map<String, Object> map = new HashMap<>();
				map.put( "statusTypeFlag", statusTypeFlag );
				map.put( "userId", userId );

				return sql().update( id( "updateStatusTypeFlagById" ), map );
		}

		public int updateStatusTypeFlagByUserOidList( UserInfo userInfo ) {
				return sql().update( id( "updateStatusTypeFlagByUserOidList" ), userInfo );
		}

		public int update( UserInfo user ) {
				return sql().update( id( "update" ), user );
		}

		/**
		 * 조직도 연동시 사용.
		 * 유저의 모든 필드가 있는것은 아님.
		 */
		public int updateBulk( List<UserInfo> user ) {

				return sql().update( id( "updateBulk" ), user );
		}

		public PageList<UserInfo> listUser( UserCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "listUser" ), cnd );
		}

		public PageList<UserInfo> listRoleUser( UserCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "listRoleUser" ), cnd );
		}

		public List<UserInfo> listAllRoleUser( UserCnd cnd ) {
				return sql().selectList( id( "listAllRoleUser" ), cnd );
		}

		public int listUserCnt( UserCnd cnd ) {
				return sql().selectOne( id( "listUser_count" ), cnd );
		}

		public UserInfo login( UserInfo info ) {

				return (UserInfo) sql().selectOne( id( "login" ), info );
		}

		/**
		 * 사용자의 id or oid로만 로그인처리
		 */
		public UserInfo sloByIdOrOid( UserCnd cnd ) {

				return (UserInfo) sql().selectOne( id( "sloByIdOrOid" ), cnd );
		}

		public boolean checkPassword( UserCnd cnd ) {

				Object obj = sql().selectOne( id( "checkPassword" ), cnd );

				return convertIntegerToBoolean( obj );
		}

		public List<UserInfo> listByBranchOid( UserCnd cnd ) {

				return sql().selectList( id( "listByBranchOid" ), cnd );
		}

		public List<UserInfo> getUserCountListByBranch( UserCnd cnd ) {

				return sql().selectList( id( "getUserCountListByBranch" ), cnd );
		}

		public List<UserInfo> listAll( UserCnd cnd ) {

				return sql().selectList( id( "listAll" ), cnd );
		}

		public int getCount( UserCnd cnd ) {

				return sql().selectOne( id( "listUser_count" ), cnd );
		}

		public Map<String, Integer> getStatusCount( UserCnd cnd ) {

				//			Map<String, Integer> map = sql().selectOne( id( "getStatusCount" ), cnd );
				//			Map<String, Integer> result = new HashMap<>();
				//			
				//			for ( Entry<String, Object> entry : map.entrySet() ) {
				//				
				//				String key = (String) entry.getKey();
				//				int value = Integer.parseInt( String.valueOf( (Object) entry.getValue() ) );
				//				result.put( key, value );
				//			}

				return sql().selectOne( id( "getStatusCount" ), cnd );
		}
}
