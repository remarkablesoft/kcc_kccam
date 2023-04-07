package com.remarkablesoft.framework.service.mgnt.treenode.model.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.mgnt.treenode.vo.TreeNodeCnd;
import com.remarkablesoft.framework.service.mgnt.treenode.vo.TreeNodeInfo;

/**
 * 		@주시스템			:	framwork-web
 * 		@서브 시스템		:	mgnt-treeNode
 * 		@프로그램 ID		:	TreeNodeInfo.java
 * 		@프로그램 개요 		:	트리쪽 DAO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 9. 29.	:	james	-	신규생성
 * 		============================================================================
 */
@DAO
public class TreeNodeDAO extends BaseDAO {

		public TreeNodeInfo get( String oid, String tableName ) {

				TreeNodeInfo info = new TreeNodeInfo( tableName );
				info.setOid( oid );

				return get ( info);
		}

		public TreeNodeInfo get( TreeNodeInfo info ) {
				return sql().selectOne( id( "get" ), info );
		}


		public int update( TreeNodeInfo node ) {
				return sql().update( id( "update" ), node );
		}


		public int updateForAllSubNode( String newFullPathIndex, String oldFullPathIndex, String tableName, String paramRootColName, String paramRootOid ) {

				Map<String, String> map = new HashMap<>();
				map.put( "newFullPathIndex", newFullPathIndex );
				map.put( "oldFullPathIndex", oldFullPathIndex );
				map.put( "tableName", tableName );
				map.put( "paramRootColName", paramRootColName );
				map.put( "paramRootOid", paramRootOid );


				return sql().update( id( "updateForAllSubNode" ), map );
		}



		public List<TreeNodeInfo> listAll( TreeNodeCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}


}
