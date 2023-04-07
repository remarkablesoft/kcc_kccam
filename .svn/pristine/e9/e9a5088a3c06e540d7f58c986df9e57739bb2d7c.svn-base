package com.remarkablesoft.framework.service.authority.role.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.authority.role.vo.RoleCnd;
import com.remarkablesoft.framework.service.authority.role.vo.RoleRightInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:
 * 		@프로그램 ID		:	RightDAO.java
 * 		@프로그램 개요 	:	권한 DAO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 1. 25.	:	james	-	신규생성
 * 		============================================================================
 */
@DAO
public class RoleRightDAO extends BaseDAO {

		public boolean exist( RoleRightInfo roleRightInfo ) {
				return convertIntegerToBoolean( sql().selectOne( id( "exist" ), roleRightInfo ) );
		}

		public int insert( RoleRightInfo roleRightInfo ) {
				return sql().insert( id( "insert" ), roleRightInfo );
		}
		
		public int updateACL ( RoleRightInfo roleRightInfo ) {
				return sql().update( id( "updateACL" ), roleRightInfo );
		}
		

		public int delete( RoleCnd cnd ) {
				return sql().delete( id( "delete" ), cnd );
		}


		public RoleRightInfo get( RoleCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}

		public List<RoleRightInfo> listAll( RoleCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}

}
