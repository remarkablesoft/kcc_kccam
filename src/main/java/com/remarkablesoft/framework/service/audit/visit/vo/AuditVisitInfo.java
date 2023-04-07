package com.remarkablesoft.framework.service.audit.visit.vo;

import java.time.LocalDateTime;
import java.util.Map;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.util.DateUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 
 * 		@주시스템			:	framework-web
 * 		@서브 시스템			:	audit - visit
 * 		@프로그램 ID			:	AuditVisitInfo
 * 		@프로그램 개요 		:	방문자의 이력을 남기기 위해
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 9. 11	:	max		-	신규생성
 *		1.1  2021. 3.  8	:	최경주	-	userDeviceTypeFlag, userOSFlag, userBrowser -> userAgent 로 통합
 *											( 연결타입 웹인지 모바일인지 구분하기 위해.
 *											navigator.userAgent을 체크하여 ipad, iphone, android, shw 등의 값 )
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true)
@ToString
public class AuditVisitInfo extends Entity {
		

		/**
		 * 
		 */
		private static final long serialVersionUID = -1848136044226215282L;
		
		public static final String VISIT_FLAG = "VF";  					// 방문 여부 쿠키 키 값
		public static final String IP_LOCAL_HOST = "127.0.0.1";
		
		private String oid;												// OID
		private String visitYear;										// 통계 년
		private String visitMonth;										// 통계 월
		private String visitWeek;										// 통계 주
		private String visitDay;										// 통계 일
		
		/**
		 * KCCAM histVisit 기능 추가
		 * 2017.07.22 남윤재
		*/
		private String visitURL;										// 방문 URL
		private String visitPageName;									// 방문 화면이름 (간략히 표시함)

		/** IP. */
		private String userIp = "";
		private String userAgent = "";									// 로그인한 사용자 브라우져
		private LocalDateTime inputDate = LocalDateTime.now();			// 방문 시간

		/**
		 * oid 및 날짜에 대한 기본값을 설정해줍니다.
		 * @author max
		 * @return
		 */
		public AuditVisitInfo setDefaultInfo() {

				return setDefaultInfo( null );
		}

		public AuditVisitInfo setDefaultInfo( Map<String, String> dateMap ) {
				
				if ( dateMap == null ) {
						dateMap = DateUtils.getDate();
				}
				
				return this.setVisitYear( dateMap.get( DateUtils.DF_PARAM_YEAR ) )
								.setVisitMonth( dateMap.get( DateUtils.DF_PARAM_MONTH ) )
								.setVisitWeek( dateMap.get( DateUtils.DF_PARAM_WEEK ) )
								.setVisitDay( dateMap.get( DateUtils.DF_PARAM_DAY ) )
								.setOid( OIDGenerator.generateOID() );
		}
		
}
