package com.remarkablesoft.framework.service.audit.visit.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.audit.visit.model.AuditVisitService;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitCnd;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitInfo;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	audit - visit
* 		@프로그램 ID		:	AuditVisitServiceImpl
* 		@프로그램 개요 		:   방문자 수 통계를 위한 Service Impl
*
*
* 		@변경이력
*      ============================================================================
* 		1.0  2020. 09. 11.	:	max		-	신규생성
* 		============================================================================
*/
@Service
@Transactional
public class AuditVisitServiceImpl implements AuditVisitService {

		protected AuditVisitBLO auditVisitBLO;
		
		@Autowired
		public AuditVisitServiceImpl( AuditVisitBLO auditVisitBLO ) {
				this.auditVisitBLO = auditVisitBLO;
		}
		
		@Override
		public AuditVisitInfo insert( AuditVisitInfo info ) {
				return auditVisitBLO.insert( info );
		}

		@Override
		public List<AuditVisitInfo> insertBulk( List<AuditVisitInfo> list ) {
				return auditVisitBLO.insertBulk( list );
		}
		
		@Override
		public AuditVisitInfo get( AuditVisitCnd cnd ) {
				return auditVisitBLO.get( cnd );
		}

		@Override
		public List<AuditVisitInfo> listAll( AuditVisitCnd cnd ) {
				return auditVisitBLO.listAll( cnd );
		}

		@Override
		public PageList<AuditVisitInfo> list( AuditVisitCnd cnd ) {
				return auditVisitBLO.list( cnd );
		}
		
		@Override
		public int listCount( AuditVisitCnd cnd ) {
				return auditVisitBLO.listCount( cnd );
		}
		
		@Override
		public AuditVisitInfo update( AuditVisitInfo info ) {
				return auditVisitBLO.update( info );
		}

		@Override
		public int delete( String oid ) {
				return auditVisitBLO.delete( oid );
		}

		@Override
		public int deleteAll( AuditVisitCnd cnd ) {
				return auditVisitBLO.deleteAll( cnd );
		}

}
