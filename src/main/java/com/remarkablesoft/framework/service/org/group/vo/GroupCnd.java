package com.remarkablesoft.framework.service.org.group.vo;

import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.service.mgnt.treenode.vo.TreeNodeCnd;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	org-group
 * 		@프로그램 ID		:	GroupCnd.java
 * 		@프로그램 개요 		:	그룹 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 4. 4.	:	sunny	-	신규생성
 * 		1.1  2019. 9. 29.	:	james	-	트리구조 변경으로 TreeNodeCnd를 상속받아서 처리
 * 		============================================================================
 */
@Getter
@Setter
@Accessors( chain = true)
@ToString
@EqualsAndHashCode (callSuper = true)
public class GroupCnd extends TreeNodeCnd {

		public GroupCnd(  ) {
				super( GroupInfo.TABLE_NAME );
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 8086411127315759506L;

		public static final String LIST_TYPE_TREE = "tree";
		
		private String name ;
		private String owner ;

		private String branchOid ;
		private String companyOid;
		
		private String listType = "";							 // LIST_TYPE_TREE or ?

		private List<String> exceptGroupOid = new ArrayList<>(); // 제외할 그룹 oid
		private Boolean  isExceptParentGroupIsRoot = false;		 // Root OID의 자식들은 제외

		/** 검색결과 카운트와 상관없이 사용자 전체 카운트 정보 여부 */
		private Boolean isUserCntSearch = false;

		/** 부서장 정보를 가져올 지 여부 */
		private Boolean isOwnerSearch = false;

		/** oid list로 가져오기 */
		private List<String> groupOidList = new ArrayList<String>();

		/** 팀 상위에 지점정보가 있으면 Y */
		private String branchUseYn = "N";
		private Boolean isBranchSearch = false;

		private Boolean isWithChildGroup = false;	//하위그룹까지 모두 가져올때
		private Boolean isOnlyChildGroup = false;	//부모에 속한 하위그룹만 가져올때


		public void addGroupOid( String groupOid ) {
				this.groupOidList.add( groupOid );
		}

		public void addExceptGroupOid( String groupOid ) {
				exceptGroupOid.add( groupOid );
		}


}
