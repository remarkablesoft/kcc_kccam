package com.remarkablesoft.framework.service.authority.role.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.SystemConstants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	authority - role
 * 		@프로그램 ID		:	RoleInfo
 * 		@프로그램 개요 	:	Role 객체  - Role 사용자 , 그룹, Pgroup에서 지정해서 사용
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
@NoArgsConstructor
public class RoleInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = 4597136712345248613L;

		private String oid;
		private String roleName;
		private String roleType;
		private LocalDateTime inputDate = LocalDateTime.now();
		private String inputUser;

		private String customField1;											// 커스텀 필드1
		private String customField2;											// 커스텀 필드2
		private String customField3;											// 커스텀 필드3
		private String customField4;											// 커스텀 필드4
		private String customField5;											// 커스텀 필드5

		private List<RoleRightInfo> roleRightInfoList = new ArrayList<>();		// 롤의 권한객체
		private List<RoleUserRelInfo> roleUserRelList = new ArrayList<>();		// 롤의 사용자객체
		
		private RoleUserRelInfo roleUserRelInfo;								// 유저에서 롤체크 검색시 사용

		
		public void addRoleRight( RoleRightInfo roleRight ) {
				
				this.roleRightInfoList.add( roleRight );
		}

		public void addRoleUser( RoleUserRelInfo roleTargetRel ) {
				
				this.roleUserRelList.add( roleTargetRel );
		}
		

		/**
		 * 자신의 타입을 반환
		 *
		 * @return
		 */
		public static String getObjectType() {
				return SystemConstants.OBJECT_FW_TYPE_ROLE.getKey();
		}

		@Override
		public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((oid == null) ? 0 : oid.hashCode());
				return result;
		}

		/**
		 * role은 oid만 체크하면 될듯.
		 * userInfo에서 addRole시 사용.
		 *
		 */
		@Override
		public boolean equals( Object obj ) {
				if ( this == obj )
						return true;
				if ( obj == null )
						return false;
				if ( getClass() != obj.getClass() )
						return false;
				RoleInfo other = (RoleInfo) obj;
				if ( oid == null ) {
						if ( other.oid != null )
								return false;
				}
				else if ( !oid.equals( other.oid ) )
						return false;
				return true;
		}
}
