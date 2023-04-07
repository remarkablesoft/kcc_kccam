package com.remarkablesoft.framework.service.authority.role.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.remarkablesoft.framework.model.vo.SearchCnd;
import com.remarkablesoft.framework.util.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	authority - role
 * 		@프로그램 ID		:	RoleCnd
 * 		@프로그램 개요 	:	Role의 Cnd객체
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
@EqualsAndHashCode (callSuper = true)
public class RoleCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = -2430463850954209154L;

		private String roleOid ;										// Role OID
		private String roleRightOid ;									// RoleRight OID
		private String roleName ;										// Role 이름
		
		private String targetObject;									// 타겟 객체
		private String targetOid;										// 타겟 oid


		private List<String> targetOidList = new ArrayList<>();			// targetOid 리스트
		private List<String> roleOidList = new ArrayList<>();			// roleOid 리스트

		private String customField1 ;									// 커스텀 필드1
		private String customField2 ;									// 커스텀 필드2
		private String customField3 ;									// 커스텀 필드3
		private String customField4 ;									// 커스텀 필드4
		private String customField5 ;									// 커스텀 필드5
		
		
		private boolean isRoleRightSearch;								// 검색시 롤의 권한도 같이 가져올것인지 여부
		private boolean isRoleUserRelSearch;							// 검색시 롤의 사용자도 같이 가져올것인지 여부
		private boolean isFillUserInfo;									// RoleUserRelInfo에 UserInfo를 채울지 여부
		
		
		public void setRoleOids( String oids ) {

				if ( StringUtils.isEmpty( oids )) {
						return ;
				}

				roleOidList = new ArrayList<>( Arrays.asList( oids.split( "," ) ) );
		}
		
		public void addTargetOid( String targetOid ) {
				
				this.targetOidList.add( targetOid );
		}
		
		public void addRoleOid( String roleOid ) {
				
				this.roleOidList.add( roleOid );
		}




}
