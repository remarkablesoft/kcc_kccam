package com.remarkablesoft.framework.service.authority.role.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.authority.role.vo.RoleCnd;
import com.remarkablesoft.framework.service.authority.role.vo.RoleUserRelInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	authority - role
 * 		@프로그램 ID		:	RoleUserRelDAO
 * 		@프로그램 개요 	:	Role에 포함된 타겟 객체정보 - 타겟은 User, Group, Pgroup
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2021. 2. 5.	:	sirena	-	신규생성
 * 		============================================================================
 */
@DAO
public class RoleUserRelDAO extends BaseDAO {

		public boolean exist( RoleUserRelInfo info ) {
				return convertIntegerToBoolean( sql().selectOne( id( "exist" ), info ) );
		}

		public int insert( RoleUserRelInfo info ) {
				return sql().insert( id( "insert" ), info );
		}

		public int delete( RoleCnd cnd ) {
				return sql().delete( id( "delete" ), cnd );
		}

		public RoleUserRelInfo get( RoleCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}

		public List<RoleUserRelInfo> listAll( RoleCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}
		
		public List<RoleUserRelInfo> listRoleByUserOid( String oid ) {
				return sql().selectList( id( "listRoleByUserOid" ), oid );
		}
		
		
}
