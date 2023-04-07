package com.remarkablesoft.framework.service.authority.role.vo;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.SystemConstants;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	authority - role
 * 		@프로그램 ID		:	RoleRightInfo
 * 		@프로그램 개요 	:	권한객체. 타겟Object와 타겟Oid으로 타겟을 구분하고 aclValue로 해당 권한을 처리
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 1. 25.	:	james	-	신규생성
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true)
@ToString
public class RoleRightInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = 1945875270103815842L;


		private String roleOid ;					// roleOid
		private String targetObject ;				// 타겟 Object
		private String targetOid ;					// 타겟 Oid
		private Integer aclValue = null;			// bitsum으로 처리 ACLObject 값을 가지고 있음

		
		
		
		public static String getObjectType() {
				return SystemConstants.OBJECT_FW_TYPE_RIGHT.getKey();
		}


}
