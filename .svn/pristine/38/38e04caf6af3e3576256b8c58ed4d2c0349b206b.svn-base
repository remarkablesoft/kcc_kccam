package com.remarkablesoft.framework.service.authority.role.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * 
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	authority - role
 * 		@프로그램 ID		:	RoleTargetRelInfo
 * 		@프로그램 개요 	:	Role에 포함된 타겟 객체정보 - 타겟은 User, Group, Pgroup
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2021. 2. 5.	:	james	-	신규생성
 * 		============================================================================
 */
@Getter
@Setter
@Accessors( chain = true)
@ToString
public class RoleUserRelInfo extends Entity {


		/**
		 * 
		 */
		private static final long serialVersionUID = 6221723071330543085L;
		
		
		private String roleOid;									// role oid
		private String targetObject; 							// 타겟 객체의 타입 -> User, Group, PGroup
		private String targetOid; 								// 타겟 객체의 Oid
		
		
		private LocalDateTime roleStartDate;					// ROLE 시작일시
		private LocalDateTime roleEndDate;						// ROLE 종료일시
		
		private String inputUser ;								// 등록자
		private LocalDateTime inputDate = LocalDateTime.now();	// 등록일

		
		private UserInfo user;									// 타갯객체와 맵핑된 사용자
}
