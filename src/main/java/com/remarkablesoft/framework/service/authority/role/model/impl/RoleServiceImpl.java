package com.remarkablesoft.framework.service.authority.role.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.authority.role.model.RoleService;
import com.remarkablesoft.framework.service.authority.role.vo.RoleCnd;
import com.remarkablesoft.framework.service.authority.role.vo.RoleInfo;
import com.remarkablesoft.framework.service.authority.role.vo.RoleRightInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	authority - role
 * 		@프로그램 ID		:	RoleServiceImpl
 * 		@프로그램 개요 	:	Role 서비스 impl
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 1. 25.	:	james	-	신규생성
 * 		============================================================================
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

		@Autowired
		protected RoleBLO roleBLO;
		
		@Autowired
		protected RoleRightBLO roleRightBLO;

		

		@Override
		public RoleInfo insert( RoleInfo roleInfo ) {
				return roleBLO.insert( roleInfo );
		}
		
		@Override
		public RoleInfo get( String oid ) {
				RoleCnd cnd = new RoleCnd();
				cnd.setRoleOid( oid );
				return get( cnd );
		}

		@Override
		public RoleInfo get( RoleCnd cnd ) {
				return roleBLO.get( cnd );
		}


		@Override
		public int delete( RoleCnd cnd ) {
				return roleBLO.delete( cnd );
		}
		
		@Override
		public int update( RoleInfo roleInfo ) {
				return roleBLO.update( roleInfo );
		}


		@Override
		public PageList<RoleInfo> list( RoleCnd cnd ) {
				return roleBLO.list( cnd );
		}

		@Override
		public List<RoleInfo> listAll( RoleCnd cnd ) {
				return roleBLO.listAll( cnd );
		}


		@Override
		public List<RoleRightInfo> listRoleRight( String roleOid ) {

			return roleBLO.listRoleRight( roleOid );
		}

		@Override
		public int addRoleRight( String roleOid, List<RoleRightInfo> listRoleRight ) {
				
				return roleBLO.addRoleRight( roleOid, listRoleRight );
		}

//		@Override
//		public int addRolePgroup( String roleOid, String ... pgroupOids ) {
//				
//				return roleBLO.addRolePgroup( roleOid, pgroupOids );
//		}
}
