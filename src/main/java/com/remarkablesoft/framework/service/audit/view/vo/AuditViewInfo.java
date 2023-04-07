package com.remarkablesoft.framework.service.audit.view.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	audit - view
 * 		@프로그램 ID		:	AuditViewInfo
 * 		@프로그램 개요 		:	보기 이력 - posting, ebook 등 데이터가 많이 쌓일것은 따로 저장.
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2016. 2. 3	:	james	-	신규생성
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true)
@ToString
public class AuditViewInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = 6564126909859122043L;

		private String oid ;
		private String targetOid ;
		private String targetObject ;
		private LocalDateTime viewDate;
		private String viewUser ;
		
		private String viewYear;			// 조회 년
		private String viewMonth;			// 조회 월
		private String viewWeek;			// 조회 주
		private String viewDay;				// 조회 일
		private int countVC = 0;
		
		List<String> targetOidList = new ArrayList<>();
}
