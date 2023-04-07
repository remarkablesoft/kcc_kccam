package com.remarkablesoft.site.kccam.service.docshareaudit.model.impl;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.docshareaudit.vo.DocShareAuditCnd;
import com.remarkablesoft.site.kccam.service.docshareaudit.vo.DocShareAuditInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	service - docShareAudit
 * 		@프로그램 ID		:	DocShareAuditDAO
 * 		@프로그램 개요 		:	DocShareAuditDAO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2021. 06. 10.	:	zero	-	신규생성
 * 		============================================================================
 */

@DAO
public class DocShareAuditDAO extends BaseDAO {
	
		public int insert( DocShareAuditInfo info ) { return sql().insert( id( "insert" ), info ); }

		public PageList<DocShareAuditInfo> list( DocShareAuditCnd cnd ) { return sql().queryForPageListAndTCount( id("list"), cnd ); }

}
