package com.remarkablesoft.framework.service.org.group.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.service.mgnt.treenode.model.impl.TreeNodeBLO;
import com.remarkablesoft.framework.service.org.group.model.GroupService;
import com.remarkablesoft.framework.service.org.group.vo.GroupCnd;
import com.remarkablesoft.framework.service.org.group.vo.GroupInfo;


/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	org-group
 * 		@프로그램 ID		:	GroupServiceImpl.java
 * 		@프로그램 개요 		:	GroupService Impl
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 4. 3.	:	james	-	신규생성
 * 		============================================================================
 */
@Service
@Transactional
public class GroupServiceImpl implements GroupService {

		@Autowired
		protected GroupBLO groupBLO;

		@Autowired
		protected TreeNodeBLO treeNodeBLO;

		@Override
		public boolean exist( String oid ) {
				return groupBLO.exist( oid );
		}

//		@Override
//		public boolean existGroupName( GroupInfo groupInfo ) {
//				return groupBLO.existGroupName( groupInfo );
//		}

		@Override
		public List<GroupInfo> listAll( GroupCnd groupCnd ) {
				return groupBLO.listAll( groupCnd );
		}

		@Override
		public boolean checkExistGroupName(GroupInfo groupInfo) {
			return groupBLO.checkExistGroupName( groupInfo );
		}

		@Override
		public GroupInfo get( String oid ) {
				return groupBLO.get( oid );
		}

		@Override
		public GroupInfo insert( GroupInfo groupInfo ) {
				return groupBLO.insert( groupInfo );
		}

		@Override
		public GroupInfo update( GroupInfo groupInfo ) {
				return groupBLO.update( groupInfo );
		}

		@Override
		public int delete( GroupCnd cnd ) {
				return groupBLO.delete( cnd );
		}


		@Override
		public GroupInfo get( GroupCnd groupCnd ) {
				return groupBLO.get( groupCnd );
		}

		@Override
		public GroupInfo saveOrUpdate( GroupInfo info ) {

				return groupBLO.saveOrUpdate( info );
		}

		@Override
		public void move( String groupOid, String targetOid, String direction ) {

				treeNodeBLO.move( groupOid, targetOid, direction , GroupInfo.TABLE_NAME);
		}

		@Override
		public List<GroupInfo> listFirstLevel( GroupCnd groupCnd ) {

				return groupBLO.listFirstLevel( groupCnd );
		}

		@Override
		public List<GroupInfo> listAllTree( GroupCnd groupCnd ) {
				groupCnd.setListType( GroupCnd.LIST_TYPE_TREE );
				return listAll( groupCnd );
		}

}
