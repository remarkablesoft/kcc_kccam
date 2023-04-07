package com.remarkablesoft.framework.service.audit.visit.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitCnd;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitInfo;


/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템			:	audit - visit
* 		@프로그램 ID			:	AuditVisitService
* 		@프로그램 개요 		:   방문자 수 통계를 위한 Service
*
*
* 		@변경이력
*      ============================================================================
* 		1.0  2020. 09. 11.	:	max		-	신규생성
* 		============================================================================
*/
public interface AuditVisitService {

		public AuditVisitInfo insert( AuditVisitInfo info );
		
		public List<AuditVisitInfo> insertBulk ( List<AuditVisitInfo> list );
		
		public AuditVisitInfo get( AuditVisitCnd cnd );
		
		public List<AuditVisitInfo> listAll (  AuditVisitCnd cnd );

		public PageList<AuditVisitInfo> list( AuditVisitCnd cnd );
		
		public int listCount( AuditVisitCnd cnd );
		
		public AuditVisitInfo update( AuditVisitInfo info );
		
		public int delete ( String oid );

		public int deleteAll( AuditVisitCnd cnd );
		
}
