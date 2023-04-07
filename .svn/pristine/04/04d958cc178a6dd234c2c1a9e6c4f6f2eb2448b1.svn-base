package com.remarkablesoft.framework.service.audit.view.model;

import java.util.List;

import com.remarkablesoft.framework.service.audit.view.vo.AuditViewCnd;
import com.remarkablesoft.framework.service.audit.view.vo.AuditViewInfo;


/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	audit - view
* 		@프로그램 ID		:	AuditViewService
* 		@프로그램 개요 		:   보기 카운트를 체크하는 DAO
*
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 12.	:	james	-	신규생성
* 		============================================================================
*/
public interface AuditViewService {

		AuditViewInfo insert( AuditViewInfo info );

		List<AuditViewInfo> listByTargetObjectCount( AuditViewCnd cnd );

		List<AuditViewInfo> listByUserCount( AuditViewCnd cnd );
	
		/**
		 * Cnd의 groupByList를 사용하여 년/월/주/일 별 카운트를 가져옵니다. 
		 *
		 * @param cnd
		 * @return List<AuditViewInfo>
		 * @author 최원준
		 */
		List<AuditViewInfo> groupByCountList( AuditViewCnd cnd );

}
