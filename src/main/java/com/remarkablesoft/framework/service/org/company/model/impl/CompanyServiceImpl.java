package com.remarkablesoft.framework.service.org.company.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.org.company.model.CompanyService;
import com.remarkablesoft.framework.service.org.company.vo.CompanyCnd;
import com.remarkablesoft.framework.service.org.company.vo.CompanyInfo;


/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	Company
 * 		@프로그램 ID		:	CompanyServiceImpl
 * 		@프로그램 개요 		:	법인 사업자
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 10. 07.	:	HONG	-	법인
 * 		============================================================================
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	protected CompanyBLO companyBLO;

	public CompanyInfo insert( CompanyInfo info ) {

		return companyBLO.insert(info);
	}

	public int update( CompanyInfo info ) {

		return companyBLO.update(info);
	}

	public CompanyInfo get( CompanyCnd cnd ) {

		return companyBLO.get(cnd);
	}

	public List<CompanyInfo> listAll( CompanyCnd cnd ) {

		return companyBLO.listAll(cnd);
	}

	public PageList<CompanyInfo> list( CompanyCnd  cnd ) {

		return companyBLO.list(cnd);
	}

	public int delete( CompanyCnd  cnd ) {

		return companyBLO.delete( cnd );
	}
	
	public CompanyInfo saveOrUpdate( CompanyInfo info ) {

		return companyBLO.saveOrUpdate( info );
	}

}
