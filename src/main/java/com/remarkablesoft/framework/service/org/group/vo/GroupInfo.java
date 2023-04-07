package com.remarkablesoft.framework.service.org.group.vo;

import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.mgnt.treenode.vo.TreeNodeInfo;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	org-group
 * 		@프로그램 ID		:	GroupInfo.java
 * 		@프로그램 개요 		:	그룹 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 4. 3.	:	james	-	신규생성
 * 		1.1  2019. 9. 29.	:	james	-	트리구조 변경으로 TreeNodeCnd를 상속받아서 처리
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true)
@ToString
public class GroupInfo extends TreeNodeInfo {

		public GroupInfo() {
				super( TABLE_NAME );
		}


		/**
		 *
		 */
		private static final long serialVersionUID = -2204379072874851911L;

		public static final String TABLE_NAME = "TB_GROUP";


		private String name ;								// 부서명
		private String owner ;								// 부서장
		private String inputUser ;
		private LocalDateTime inputDate = LocalDateTime.now();

		private String companyOid ;							// 회사 OID
		private String branchOid ;							// 지점 OID

		private int userCnt = 0;							// 부서 내 인원수
		private int orderNoCV = 0;							// 순번

		private String customField1 ;						// 커스텀 필드1
		private String customField2 ;						// 커스텀 필드2
		private String customField3 ;						// 커스텀 필드3
		private String customField4 ;						// 커스텀 필드4
		private String customField5 ;						// 커스텀 필드5

		private BranchInfo branchInfo = null;
		private UserInfo ownerInfo = null;										//	부서장 정보

		private List<GroupInfo> groupList = new ArrayList<GroupInfo>();			// 추가 및 수정시 사용
		private List<GroupInfo> deleteGroupList = new ArrayList<GroupInfo>();	// 삭제시 사용
		private List<String> userOidList = new ArrayList<String>();				// 사용자 oids
		protected List<GroupInfo> childGroupList = new ArrayList<GroupInfo>();	// 하위 그룹 리스트

		private boolean isCheckExist = false;									//존재여부 체크할지 여부

		
		public void addChildGroupList( GroupInfo groupInfo ) {
				this.childGroupList.add( groupInfo );
		}
		

		public void addUserOidList( String userOid ) {
				this.userOidList.add( userOid );
		}
		
		/**
		 * 자신의 타입을 반환
		 *
		 * @return
		 */
		public static String getObjectType() {
				return SystemConstants.OBJECT_FW_TYPE_GROUP.getKey();
		}
}
