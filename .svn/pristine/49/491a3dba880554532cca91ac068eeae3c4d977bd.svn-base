package com.remarkablesoft.framework.service.authority.role.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.authority.role.vo.RoleCnd;
import com.remarkablesoft.framework.service.authority.role.vo.RoleInfo;
import com.remarkablesoft.framework.service.authority.role.vo.RoleRightInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	authority - role
 * 		@프로그램 ID		:	RoleService
 * 		@프로그램 개요 	:	Role 서비스
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 1. 25.	:	james	-	신규생성
 * 		============================================================================
 */
public interface RoleService {
		

		/**
		 * Role을 등록합니다.
		 * 
		 * @author james
		 * @param roleInfo
		 * @return
		 */
		public RoleInfo insert( RoleInfo roleInfo );
		
		
		/**
		 * Role을 가져옵니다.
		 * 
		 * @author james
		 * @param oid
		 * @return
		 */
		public RoleInfo get( String oid );

		/**
		 * Role의 정보를 가져옵니다.
		 * 
		 * @author james
		 * @param cnd
		 * @return
		 */
		public RoleInfo get( RoleCnd cnd );


		/**
		 * Role의 정보를 업데이트합니다.
		 * 
		 * @author james
		 * @param roleInfo
		 * @return
		 */
		public int update( RoleInfo roleInfo );


		/**
		 * Role을 삭제합니다.
		 *
		 * @param cnd
		 * @return
		 */
		public int delete( RoleCnd cnd );


		/**
		 * Role의 PageList를 가져옵니다.
		 * 
		 * @author james
		 * @param cnd
		 * @return
		 */
		public PageList<RoleInfo> list( RoleCnd cnd );

		/**
		 * Role을 전부 가져옵니다.
		 * 
		 * @author james
		 * @param cnd
		 * @return
		 */
		public List<RoleInfo> listAll( RoleCnd cnd );


		/**
		 * 해당 롤의 oid로 권한 리스트를 가져옵니다
		 *
		 * @param oid
		 * @return
		 */
		public List<RoleRightInfo> listRoleRight( String roleOid ) ;
		
		/**
		 * role에 권한을 추가해줍니다.
		 * 
		 * @author sirena
		 * @param roleOid
		 * @param listRoleRight
		 * @return
		 */
		public int addRoleRight( String roleOid, List<RoleRightInfo> listRoleRight );
		
//		/**
//		 * role에 PGroup을 추가해줍니다.
//		 * 
//		 * @author sirena
//		 * @param roleOid
//		 * @param pgroupOids
//		 * @return
//		 */
//		public int addRolePgroup( String roleOid, String ... pgroupOids );
}
