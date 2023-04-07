package com.remarkablesoft.framework.service.org.company.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.org.company.vo.CompanyCnd;
import com.remarkablesoft.framework.service.org.company.vo.CompanyInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	Company
 * 		@프로그램 ID		:	CompanyDAO
 * 		@프로그램 개요 		:	법인 사업자
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 10. 07.	:	HONG	-	법인
 * 		============================================================================
 */
@DAO
public class CompanyDAO extends BaseDAO{

	public int insert( CompanyInfo info ) {
		return sql().insert( id( "insert" ), info );
	}

	public int update( CompanyInfo info ) {
			return sql().update( id( "update" ), info );
	}

	public CompanyInfo get( CompanyCnd cnd ) {
			return sql().selectOne( id( "get" ), cnd );
	}

	public List<CompanyInfo> listAll( CompanyCnd cnd ) {
			return sql().selectList( id( "listAll" ), cnd );
	}

	public PageList<CompanyInfo> list( CompanyCnd  cnd ) {
			return sql().queryForPageListAndTCount( id( "list" ), cnd );
	}

	public int delete( CompanyCnd  cnd ) {
			return sql().delete( id( "delete" ), cnd );
	}

}
