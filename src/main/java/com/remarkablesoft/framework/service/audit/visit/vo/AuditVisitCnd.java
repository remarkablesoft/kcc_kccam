package com.remarkablesoft.framework.service.audit.visit.vo;

import java.util.Map;

import com.remarkablesoft.framework.model.vo.SearchCnd;
import com.remarkablesoft.framework.util.DateUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	audit - visit
 * 		@프로그램 ID		:	AuditVisitCnd
 * 		@프로그램 개요 		:	로그인 로그 검색 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2016. 4. 14.	:	dev	-	신규생성
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true)
@ToString
@EqualsAndHashCode (callSuper = true)
public class AuditVisitCnd extends SearchCnd {

		/**
		 * 
		 */
		private static final long serialVersionUID = -4394328784304520690L;

		private String oid;								// OID
		private String visitYear;						// 통계 년
		private String visitMonth;						// 통계 월
		private String visitWeek;						// 통계 주
		private String visitDay;						// 통계 일
		
		private String fromDate; 						// 검색 시작일
		private String toDate; 							// 검색 종료일
		private String searchDateType;					// 검색 날짜 타입
		private String userAgent;						// 로그인한 사용자의 브라우저 정보
		private String searchText;						// 검색어 ( KCCAM HistVisit검색용으로 추가 2021.07.22 남윤재 )

		/** IP */
		private String userIp;
		
		public AuditVisitCnd setDefaultDate() {
				
				return setDefaultDate( null );
		}
		
		public AuditVisitCnd setDefaultDate( Map<String, String> dateMap ) {
				
				if ( dateMap == null ) {
						dateMap = DateUtils.getDate();
				}
				
				return this.setVisitYear( dateMap.get( DateUtils.DF_PARAM_YEAR ) )
										.setVisitMonth( dateMap.get( DateUtils.DF_PARAM_MONTH ) )
										.setVisitWeek( dateMap.get( DateUtils.DF_PARAM_WEEK ) )
										.setVisitDay( dateMap.get( DateUtils.DF_PARAM_DAY ) );
		}
		

}
