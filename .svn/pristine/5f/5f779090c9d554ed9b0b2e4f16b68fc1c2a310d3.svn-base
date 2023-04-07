package com.remarkablesoft.site.kccam.web.api;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.web.controller.BaseController;
import com.remarkablesoft.site.kccam.service.docshareaudit.model.DocShareAuditService;
import com.remarkablesoft.site.kccam.service.docshareaudit.vo.DocShareAuditCnd;
import com.remarkablesoft.site.kccam.service.docshareaudit.vo.DocShareAuditInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@WEBLog
@RestController
@RequestMapping( "/kccam/api/docShareAudit/" )
public class DocShareAuditController extends BaseController {
		
		@Autowired
		DocShareAuditService docShareAuditService;
		
		/**
		 * 문서 다운/공유 사용자의 이메일 정보를 등록합니다
		 *
		 * @param info
		 * @return DocShareAuditInfo
		 * @author 황지영
		 */
		@RequestMapping( value = "/docShareAudit_insert" )
		public ResponseEntity<DocShareAuditInfo> insert( @RequestBody DocShareAuditInfo info ) {
				
				info = docShareAuditService.insert( info );
				return ResponseEntity.ok( info );
		}
		
		/**
		 * 문서 다운/공유 사용자 정보 이력 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return PageList<DocShareAuditInfo>
		 * @author 황지영
		 */
		@RequestMapping( value = "/docShareAudit_list" )
		public ResponseEntity<Map<String, Object>> list( @RequestBody DocShareAuditCnd cnd ){
				
				PageList<DocShareAuditInfo> list = docShareAuditService.list( cnd );
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put( "list", list );
				resultMap.put( "totalCount", CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );
				
				return ResponseEntity.ok( resultMap );
		}
}
