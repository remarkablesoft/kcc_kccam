package com.remarkablesoft.site.kccam.web.api;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.audit.visit.model.AuditVisitService;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitCnd;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.web.controller.BaseController;
import com.remarkablesoft.framework.web.util.VisitWebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @주시스템		    :	KCCAM
 * @서브 시스템		:   api
 * @프로그램 ID		:   HistVisitController
 * @프로그램 개요	    :   방문 이력 관리 프로그램
 * 						framework 의 audit - visit 서비스
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-07-21 : 남윤재 - 신규생성
 * ============================================================================
 */

@WEBLog
@RestController
@RequestMapping( "/kccam/api/histVisit" )
public class HistVisitController extends BaseController {
		
		@Autowired
		protected AuditVisitService auditVisitService;
		
		@Autowired
		VisitWebUtils visitWebUtils;
		
		/**
		 * 방문 이력 정보를 저장합니다.
		 * @param info
		 * @return AuditVisitInfo
		 * @author 남윤재
		 */
		@RequestMapping( value = "histVisit_inset" )
		public void insert( HttpServletRequest request, @RequestBody AuditVisitInfo info ) {
				
				visitWebUtils.insertVisit( request, info );
		}
		
		/**
		 * 방문 이력 페이지 리스트를 호출합니다.
		 * @param cnd
		 * @return HashMap
		 * @author 남윤재
		 */
		@RequestMapping( value = "histVisit_list" )
		public ResponseEntity<HashMap<String, Object>> list( @RequestBody AuditVisitCnd cnd ) {
		
				HashMap<String, Object> resultMap = new HashMap<>();
				PageList<AuditVisitInfo> list = auditVisitService.list( cnd );
				resultMap.put( "list", list );
				resultMap.put( "totalCount", CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );
				
				return ResponseEntity.ok( resultMap );
		}
		
		/**
		 * 방문 이력 목록 전부를 조회합니다.
		 * @param cnd
		 * @return List<AuditVisitInfo>
		 * @author 남윤재
		 */
		@RequestMapping( value = "histVisit_listAll" )
		public ResponseEntity<List<AuditVisitInfo>> listAll( @RequestBody AuditVisitCnd cnd ) {
				List<AuditVisitInfo> list = auditVisitService.listAll( cnd );
				return ResponseEntity.ok( list );
		}
}
