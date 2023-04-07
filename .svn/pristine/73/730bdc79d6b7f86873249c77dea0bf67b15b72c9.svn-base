package com.remarkablesoft.site.kccam.service.docshareaudit.model;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.docshareaudit.vo.DocShareAuditCnd;
import com.remarkablesoft.site.kccam.service.docshareaudit.vo.DocShareAuditInfo;

/**
 * @주시스템 :	kccam
 * @서브 시스템        :   service - docShareAudit
 * @프로그램 ID        :   DOcShareAuditService.java
 * @프로그램 개요        :   문서 다운/공유 기능 이용자의 이메일 로그 서비스
 * @변경이력 ============================================================================
 * 1.0 2021-06-10 : 황지영 - 신규생성
 * ============================================================================
 */
public interface DocShareAuditService {
		
		/**
		 * 문서 공유/다운 로그을 기록합니다
		 *
		 * @param info
		 * @return DocShareAuditInfo
		 * @author 황지영
		 */
		public DocShareAuditInfo insert( DocShareAuditInfo info );
		
		/**
		 * 문서 공유/다운 로그 기록 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return PageList
		 * @author 황지영
		 */
		public PageList<DocShareAuditInfo> list( DocShareAuditCnd cnd );

}
