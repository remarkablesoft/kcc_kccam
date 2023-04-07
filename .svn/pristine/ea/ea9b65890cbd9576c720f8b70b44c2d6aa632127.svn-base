package com.remarkablesoft.site.kccam.service.integratedsearch.model.impl;
import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.integratedsearch.vo.IntegratedSearchCnd;
import com.remarkablesoft.site.kccam.service.integratedsearch.vo.IntegratedSearchInfo;

@DAO
public class IntegratedSearchDAO extends BaseDAO {
	
	public PageList<IntegratedSearchInfo> productList( IntegratedSearchCnd cnd ) {
		return sql().queryForPageListAndTCount( id( "productList" ), cnd );
	}
	
	public PageList<IntegratedSearchInfo> applicationList( IntegratedSearchCnd cnd ) {
		return sql().queryForPageListAndTCount( id( "applicationList" ), cnd );
	}
	
	public PageList<IntegratedSearchInfo> marketList( IntegratedSearchCnd cnd ) {
		return sql().queryForPageListAndTCount( id( "marketList" ), cnd );
	}
	
	public PageList<IntegratedSearchInfo> functionList( IntegratedSearchCnd cnd ) {
		return sql().queryForPageListAndTCount( id( "functionList" ), cnd );
	}
	
}
