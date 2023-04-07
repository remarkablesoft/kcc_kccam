package com.remarkablesoft.site.kccam.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.service.audit.view.model.AuditViewService;
import com.remarkablesoft.framework.service.audit.view.vo.AuditViewCnd;
import com.remarkablesoft.framework.service.audit.view.vo.AuditViewInfo;
import com.remarkablesoft.framework.web.controller.BaseController;

/**
 * @주시스템		    :	kccam
 * @서브 시스템		:   api
 * @프로그램 ID		:   DocController.java
 * @프로그램 개요	    :   문서 서비스 호출 컨트롤러
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-04-29 : 최원준 - 신규생성
 * ============================================================================
 */

@WEBLog
@RestController
@RequestMapping( "/kccam/api/auditView" )
public class AuditViewController extends BaseController {

	@Autowired
	protected AuditViewService auditViewService;

	/**
	 * 조건에 맞는 조회수 리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return List<AuditViewInfo>
	 * @author 최원준
	 */
	@RequestMapping( value = "/auditView_groupByCountList" )
	public ResponseEntity<List<AuditViewInfo>> groupByCountList( @RequestBody AuditViewCnd cnd ) {

		List<AuditViewInfo> list = auditViewService.groupByCountList( cnd );
		return ResponseEntity.ok( list );
	}

}
