package com.remarkablesoft.framework.service.org.branch.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.org.branch.vo.BranchCnd;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;

@DAO
public class BranchDAO extends BaseDAO {

		public int insert( BranchInfo info ) {
				return sql().insert( id( "insert" ), info );
		}

		public int update( BranchInfo info ) {
				return sql().update( id( "update" ), info );
		}

		public BranchInfo get( BranchCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}

		public List<BranchInfo> listAll( BranchCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}

		public PageList<BranchInfo> list( BranchCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "list" ), cnd );
		}

		public int delete( String oid ) {
				return sql().delete( id( "delete" ), oid );
		}

}
