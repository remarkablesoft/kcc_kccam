package com.remarkablesoft.framework.service.org.branch.model.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.service.org.branch.vo.BranchCnd;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;

@BLO
public class BranchCacheBLO extends BranchBLO {

		@Override
		@CacheEvict( value = "branch", allEntries = true )
		public BranchInfo insert( BranchInfo info ) {
				return super.insert( info );
		}

		@Override
		@CacheEvict( value = "branch", allEntries = true )
		public int update( BranchInfo info ) {
				return super.update( info );
		}

		@Override
		@CacheEvict( value = "branch", allEntries = true )
		public int saveForBranchList( BranchInfo info ) {
				return super.saveForBranchList( info );
		}

		@Override
		@Cacheable( value = "branch" , keyGenerator = "cacheKeyGenerator" )
		public BranchInfo get( BranchCnd cnd ) {
				return super.get( cnd );
		}

		@Override
		@Cacheable( value = "branch" , keyGenerator = "cacheKeyGenerator" )
		public List<BranchInfo> listAll( BranchCnd cnd ) {
				return super.listAll( cnd );
		}


}
