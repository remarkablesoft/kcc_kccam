package com.remarkablesoft.framework.service.authority.role.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.authority.role.vo.RoleCnd;
import com.remarkablesoft.framework.service.authority.role.vo.RoleInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	authority - role
 * 		@프로그램 ID		:	RoleDAO
 * 		@프로그램 개요 	:	Role의 DAO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 1. 25.	:	james	-	신규생성
 * 		============================================================================
 */
@DAO
public class RoleDAO extends BaseDAO {

		public boolean exist( String oid ) {
				return convertIntegerToBoolean( sql().selectOne( id( "exist" ), oid ) );
		}

		public int insert( RoleInfo roleinfo ) {
				return sql().insert( id( "insert" ), roleinfo );
		}

		public int update( RoleInfo roleinfo ) {
				return sql().update( id( "update" ), roleinfo );
		}

		public int delete( RoleCnd cnd ) {
				return sql().delete( id( "delete" ), cnd );
		}

		public RoleInfo get( RoleCnd roleCnd ) {
				return sql().selectOne( id( "get" ), roleCnd );
		}

		public PageList<RoleInfo> list( RoleCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "list" ), cnd );
		}

		public List<RoleInfo> listAll( RoleCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}



}
