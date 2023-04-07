package com.remarkablesoft.framework.service.audit.visit.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitCnd;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitInfo;

/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	audit - visit
 * 		@프로그램 ID		:	AuditVisitBLO
 * 		@프로그램 개요 		:   방문자 수 통계를 위한 BLO
 *
 * 		@변경이력
 *      ============================================================================
* 		1.0  2020. 09. 11.	:	max		-	신규생성
 * 		============================================================================
 */
@BLO
public class AuditVisitBLO {

		protected AuditVisitDAO auditVisitDAO;
		
		@Autowired
		public AuditVisitBLO( AuditVisitDAO auditVisitDAO ) {
				this.auditVisitDAO = auditVisitDAO;
		}
		
		public AuditVisitInfo insert ( AuditVisitInfo info ) {
				
				return auditVisitDAO.insert( info );
		}
		
		public List<AuditVisitInfo> insertBulk( List<AuditVisitInfo> list ){
				
				auditVisitDAO.insertBulk( list );
				return list;
		}
		
		public AuditVisitInfo get( AuditVisitCnd cnd ) {
				
				return auditVisitDAO.get( cnd );
		}
		
		public List<AuditVisitInfo> listAll (  AuditVisitCnd cnd ){
				
				return auditVisitDAO.listAll( cnd );
		}

		public PageList<AuditVisitInfo> list( AuditVisitCnd cnd ) {
				return auditVisitDAO.list( cnd );
		}
		
		public int listCount( AuditVisitCnd cnd ) {
				return auditVisitDAO.listCount( cnd );
		}
		
		public AuditVisitInfo update( AuditVisitInfo info ) {
				
				return auditVisitDAO.update( info ) == 1 ? info : null;
		}
		
		public int delete ( String oid ) {
				
				return auditVisitDAO.delete( oid );
		}

		public int deleteAll( AuditVisitCnd cnd ) {
			
				return auditVisitDAO.deleteAll( cnd );
		}
}
