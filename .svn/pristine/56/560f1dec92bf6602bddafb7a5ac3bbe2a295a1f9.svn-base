package com.remarkablesoft.site.kccam.service.integratedsearch.model.impl;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.integratedsearch.model.IntegratedSearchService;
import com.remarkablesoft.site.kccam.service.integratedsearch.vo.IntegratedSearchCnd;
import com.remarkablesoft.site.kccam.service.integratedsearch.vo.IntegratedSearchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IntegratedSearchServiceImpl implements IntegratedSearchService {
	
	@Autowired
	protected IntegratedSearchBLO integratedSearchBLO;
	
	@Override
	public PageList<IntegratedSearchInfo> productList( IntegratedSearchCnd cnd ) {
		return integratedSearchBLO.productList( cnd );
	}
	@Override
	public PageList<IntegratedSearchInfo> applicationList( IntegratedSearchCnd cnd ) {
		return integratedSearchBLO.applicationList( cnd );
	}
	@Override
	public PageList<IntegratedSearchInfo> marketList( IntegratedSearchCnd cnd ) {
		return integratedSearchBLO.marketList( cnd );
	}
	@Override
	public PageList<IntegratedSearchInfo> functionList( IntegratedSearchCnd cnd ) {
		return integratedSearchBLO.functionList( cnd );
	}
	
}
