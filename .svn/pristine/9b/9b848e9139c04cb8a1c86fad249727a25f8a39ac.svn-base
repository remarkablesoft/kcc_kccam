package com.remarkablesoft.site.kccam.service.docshareaudit.model.impl;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.docshareaudit.vo.DocShareAuditCnd;
import com.remarkablesoft.site.kccam.service.docshareaudit.vo.DocShareAuditInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @주시스템            :	kccam
 * @서브 시스템        :   docShareAudit
 * @프로그램 ID        :   DocShareAuditBLOTest.java
 * @프로그램 개요        :   문서 다운/공유 이메일 이력 기록 BLO 테스트
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-06-10 : 황지영 - 신규생성
 * ============================================================================
 */
public class DocShareAuditBLOTest extends BaseModelTest {
		
		@Autowired
		DocShareAuditBLO docShareAuditBLO;
		
		@Test
		public void insert_테스트(){
				DocShareAuditInfo info = new DocShareAuditInfo();
				
				info.setOid( OIDGenerator.generateOID() );
				info.setEmail( "zero123@remarkablesoft.com" );
				
				docShareAuditBLO.insert( info );
		}
		
		@Test
		public void 이력리스트_테스트(){
				DocShareAuditCnd cnd = new DocShareAuditCnd();
				cnd.setStartIndex( 11 );
				cnd.setPageSize( 10 );
				
				PageList<DocShareAuditInfo> list = docShareAuditBLO.list( cnd );
				
				System.out.println( list.toString() );
		}
}
