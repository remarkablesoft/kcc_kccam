package com.remarkablesoft.framework.service.org.group.model;

import java.util.List;

import com.remarkablesoft.framework.service.mgnt.treenode.model.TreeNodeService;
import com.remarkablesoft.framework.service.org.group.vo.GroupCnd;
import com.remarkablesoft.framework.service.org.group.vo.GroupInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	org-group
 * 		@프로그램 ID		:	GroupService.java
 * 		@프로그램 개요 		:	그룹 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 4. 3.	:	james	-	신규생성
 * 		============================================================================
 */
public interface GroupService extends TreeNodeService {

		public boolean exist( String oid );

		public boolean checkExistGroupName( GroupInfo groupInfo );

		public List<GroupInfo> listAll( GroupCnd groupCnd );
		
		/**
		 * 트리구조로 list를 가져옵니다.
		 * @author user
		 * @param groupCnd
		 * @return
		 */
		public List<GroupInfo> listAllTree( GroupCnd groupCnd );

		public GroupInfo get( String oid );

		public GroupInfo get( GroupCnd groupCnd );

		public GroupInfo insert( GroupInfo groupInfo );

		public GroupInfo update( GroupInfo groupInfo );

		public int delete( GroupCnd cnd);

		public GroupInfo saveOrUpdate( GroupInfo info);


		/**
		 * 1레벨의 리스트만 가져옵니다.
		 *
		 *
		 * @param groupCnd
		 * @return
		 */
		public List<GroupInfo> listFirstLevel( GroupCnd groupCnd );



}
