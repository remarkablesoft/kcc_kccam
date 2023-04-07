package com.remarkablesoft.framework.service.org.company.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.org.company.vo.CompanyCnd;
import com.remarkablesoft.framework.service.org.company.vo.CompanyInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	Company
 * 		@프로그램 ID		:	CompanyService
 * 		@프로그램 개요 		:	법인 사업자
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 10. 07.	:	HONG	-	법인
 * 		============================================================================
 */
public interface CompanyService {

		/**
		 * 법인 회사 정보를 저장합니다.
		 *
		 * @param info
		 * @return
		 */
		public CompanyInfo insert( CompanyInfo info );
		
		/**
		 * 법인 회사 정보를 수정합니다.
		 *
		 * @param info
		 * @return
		 */
		public int update( CompanyInfo info );
		
		/**
		 * 법인 회사 정보를 가져옵니다.
		 *
		 * @param cnd
		 * @return
		 */
		public CompanyInfo get( CompanyCnd cnd );
		
		/**
		 * 법인 회사 정보 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return
		 */
		public List<CompanyInfo> listAll( CompanyCnd cnd );
		
		/**
		 * 법인 회사 정보 페이징 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return
		 */
		public PageList<CompanyInfo> list( CompanyCnd  cnd );
		
		/**
		 * 법인 회사 정보를 삭제합니다.
		 *
		 * @param oid
		 * @return
		 */
		public int delete( CompanyCnd  cnd );
		
		/**
		 * 법인 회사 정보를 저장합니다.
		 *
		 * @param info
		 * @return
		 */
		public CompanyInfo saveOrUpdate( CompanyInfo info );

}
