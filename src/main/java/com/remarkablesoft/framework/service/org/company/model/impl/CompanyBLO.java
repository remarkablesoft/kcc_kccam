package com.remarkablesoft.framework.service.org.company.model.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.org.company.vo.CompanyCnd;
import com.remarkablesoft.framework.service.org.company.vo.CompanyInfo;
import com.remarkablesoft.framework.service.org.group.model.impl.GroupBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;


/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	Company
 * 		@프로그램 ID		:	CompanyBLO
 * 		@프로그램 개요 		:	법인 사업자
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 10. 07.	:	HONG	-	법인
 * 		============================================================================
 */

@BLO
public class CompanyBLO {

	@Autowired
	protected CompanyDAO companyDAO;

	@Autowired
	protected GroupBLO groupBLO;

	/**
	 * 법인 회사 정보를 저장하거나 수정합니다.
	 *
	 * @param info
	 * @return
	 */
	public CompanyInfo saveOrUpdate( CompanyInfo info ) {

		if ( StringUtils.isEmpty( info.getOid() ) ) {

			insert( info );
		}
		else {

			update(info);
		}

		return info;
	}


	/**
	 * 법인 회사 정보를 입력합니다.
	 *
	 * @param info
	 * @return
	 */
	public CompanyInfo insert( CompanyInfo info ) {

		if ( StringUtils.isEmpty( info.getOid() ) ) {
			info.setOid( OIDGenerator.generateOID() );
		}

		/**
		 *  COMPANY는 그룹에 1DEPTH로 들어갑니다
		 */
	    groupBLO.insertCompany( info );

		companyDAO.insert(info);

		return info;
	}

	/**
	 * 법인 회사 정보를 수정합니다.
	 *
	 * @param info
	 * @return
	 */
	public int update( CompanyInfo info ) {

		return companyDAO.update(info);
	}

	/**
	 * 법인 회사 정보를 가져옵니다.
	 *
	 * @param cnd
	 * @return
	 */
	public CompanyInfo get( CompanyCnd cnd ) {

		return companyDAO.get(cnd);
	}

	/**
	 * 법인 회사 정보를 가져옵니다.
	 *
	 * @param cnd
	 * @return
	 */
	public CompanyInfo get( String companyOid ) {

		CompanyCnd companyCnd = new CompanyCnd();
		companyCnd.setOid( companyOid);

		return get( companyCnd );
	}

	/**
	 * 법인 회사 정보 리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return
	 */
	public List<CompanyInfo> listAll( CompanyCnd cnd ) {

		return companyDAO.listAll(cnd);
	}

	/**
	 * 법인 회사 정보 페이징리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return
	 */
	public PageList<CompanyInfo> list( CompanyCnd  cnd ) {

		return companyDAO.list(cnd);
	}

	/**
	 * 법인 회사 정보를 삭제합니다.
	 *
	 * @param oid
	 * @return
	 */
	public int delete( CompanyCnd cnd ) {

		return companyDAO.delete( cnd );
	}


	/**
	 * 회사 정보를 채워줍니다.
	 * @author 최원준
	 * @param list
	 */
	public void fillCompany( List<UserInfo> userList ) {

		List<String> companyOidList = userList.stream().map( UserInfo::getCompanyOid ).collect( Collectors.toList() );

		if( CollectionUtils.isEmpty( companyOidList ) ) {
				return;
		}

		CompanyCnd companyCnd = new CompanyCnd();
		companyCnd.setCompanyOidList( companyOidList );
		List<CompanyInfo> companyList = listAll( companyCnd );

		if( CollectionUtils.isEmpty( companyList ) ) {
				return;
		}

		// list에서 null 제거
		CollectionUtils.nullRemove( userList );
		CollectionUtils.nullRemove( companyList );

		userList.stream()
		.filter( user -> user.getCompanyOid() != null && StringUtils.hasText( user.getCompanyOid() ) )
		.forEach( user -> companyList.stream()
								.filter( company -> company.getOid().equals( user.getCompanyOid() ) )
								.forEach( company -> {
										user.setCompany( company );
										user.setCompanyName( company.getCompanyName() );
								})
				);

	}


}
