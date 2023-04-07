package com.remarkablesoft.framework.service.audit.view.vo;

import com.remarkablesoft.framework.model.vo.SearchCnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 		@주시스템		:	framework-web
 * 		@서브 시스템		:	audit - view
 * 		@프로그램 ID		:	AuditViewCnd
 * 		@프로그램 개요 	:	조회정보 검색 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2021. 4. 30.	:	choi	-	신규생성
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true)
@ToString
@EqualsAndHashCode (callSuper = true)
public class AuditViewCnd extends SearchCnd {
	
		private static final long serialVersionUID = -6194388552223646817L;
		
		public static final String GROUP_BY_VIEW_YEAR = "VIEW_YEAR";
		public static final String GROUP_BY_VIEW_MONTH = "VIEW_MONTH";
		public static final String GROUP_BY_VIEW_WEEK = "VIEW_WEEK";
		public static final String GROUP_BY_VIEW_DAY = "VIEW_DAY";
		
		private String oid;									// OID
		private String targetOid;							// 타겟 OID
		private String targetObject;						// 타겟 Object
		private String viewUser;							// 조회 유저
		private String loginUserVC;							// 로그인 사용자
	
		private String viewYear;							// 통계 년
		private String viewMonth;							// 통계 월
		private String viewWeek;							// 통계 주
		private String viewDay;								// 통계 일
	
		private List<String> targetOidList = new ArrayList<>();
		
		public void addTargetOid( String targetOid ) {
			this.targetOidList.add( targetOid );
		}
		
		public void addGroupBy( String groupBy ) {
			super.addGroupByList( groupBy );
		}

}
