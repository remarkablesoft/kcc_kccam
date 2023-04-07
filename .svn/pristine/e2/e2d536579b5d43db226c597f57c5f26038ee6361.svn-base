package com.remarkablesoft.framework.service.org.branch.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.org.branch.model.BranchService;
import com.remarkablesoft.framework.service.org.branch.vo.BranchCnd;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

		@Autowired
		protected BranchBLO branchBLO;

		@Override
		public BranchInfo insert( BranchInfo info ) {
				return branchBLO.insert( info );
		}

		@Override
		public int update( BranchInfo info ) {
				return branchBLO.update( info );
		}

		@Override
		public BranchInfo get( BranchCnd cnd ) {
				return branchBLO.get( cnd );
		}

		@Override
		public List<BranchInfo> listAll( BranchCnd cnd ) {
				return branchBLO.listAll( cnd );
		}

		@Override
		public PageList<BranchInfo> list ( BranchCnd cnd ){
				return branchBLO.list( cnd );
		}

		@Override
		public int saveForBranchList( BranchInfo info ) {
				return branchBLO.saveForBranchList( info );
		}

		@Override
		public int delete( BranchInfo info ) {
				return branchBLO.delete( info.getOid() );
		}


}
