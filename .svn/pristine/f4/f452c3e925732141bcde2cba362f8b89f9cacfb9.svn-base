package com.remarkablesoft.framework.service.audit.view.model.impl;

import java.util.List;

import com.remarkablesoft.framework.service.audit.view.vo.AuditViewCnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.service.audit.view.model.AuditViewService;
import com.remarkablesoft.framework.service.audit.view.vo.AuditViewInfo;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	audit - view
* 		@프로그램 ID		:	AuditViewServiceImpl
* 		@프로그램 개요 		:   보기 카운트를 체크하는 Service Impl
*
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 12.	:	james	-	신규생성
* 		============================================================================
*/
@Service
@Transactional
public class AuditViewServiceImpl implements AuditViewService {

		@Autowired
		protected AuditViewBLO auditViewBLO;

		@Override
		public AuditViewInfo insert( AuditViewInfo info ) {
				return auditViewBLO.insert( info );
		}

		@Override
		public List<AuditViewInfo> listByTargetObjectCount( AuditViewCnd cnd ) {
				return auditViewBLO.listByTargetObjectCount( cnd );
		}

		@Override
		public List<AuditViewInfo> listByUserCount( AuditViewCnd cnd ) {
				return auditViewBLO.listByUserCount( cnd );
		}
	
		@Override
		public List<AuditViewInfo> groupByCountList( AuditViewCnd cnd ) {
				return auditViewBLO.groupByCountList( cnd );
		}
	
}
