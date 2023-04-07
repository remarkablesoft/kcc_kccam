package com.remarkablesoft.framework.service.authority.role.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.exception.BLORuntimeException;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.authority.role.vo.RoleCnd;
import com.remarkablesoft.framework.service.authority.role.vo.RoleRightInfo;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	authority - role
 * 		@프로그램 ID		:	RoleRightBLO
 * 		@프로그램 개요 	:	롤의 권한 BLO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 1. 25.	:	james	-	신규생성
 * 		1.1  2018. 4. 09.	:	james	-	관계 등록을 ObjectRelBLO에서 하도록 처리
 * 		============================================================================
 */
@BLO
public class RoleRightBLO {

		@Autowired
		protected RoleRightDAO roleRightDAO;
		

		public RoleRightInfo insertCheck( RoleRightInfo info ) {

				// 등록 Validation
				inserValidation( info );
				
				// 이미 있다면
				if ( roleRightDAO.exist( info ) ) {
						return null;
				}
				
				return roleRightDAO.insert( info ) > 0 ? info : null;
		}
		
		
		
		public RoleRightInfo insert( RoleRightInfo info ) {
				
				if ( info == null) {
						return null;
				}
				
				// 등록 Validation
				inserValidation( info );

				int result = roleRightDAO.insert( info );
				return result > 0 ? info : null;
		}
		
		
		public RoleRightInfo insert( String roleOid, RoleRightInfo info ) {

				info.setRoleOid( roleOid ) ;
				return insertCheck( info);
		}
		
		
		public RoleRightInfo insert( String roleOid, String targetOid, String targetObject, int aclValue ) {
				
				RoleRightInfo info = SystemFactory.getRoleRightInfo();
				info.setRoleOid( roleOid );
				info.setTargetOid( targetOid );
				info.setTargetObject( targetObject );
				info.setAclValue( aclValue );
				
				return insert( info );
		}


		public int delete( RoleCnd cnd ) {
				
				return roleRightDAO.delete( cnd );
		}
		
		

		public List<RoleRightInfo> listAll( RoleCnd cnd ) {

				return roleRightDAO.listAll( cnd );
		}
		
		
		/**
		 * 등록시 필수값은 validation 확인
		 * 
		 * @author james
		 * @param info
		 */
		protected void inserValidation( RoleRightInfo info  ) {
				
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
