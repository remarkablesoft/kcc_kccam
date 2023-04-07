package com.remarkablesoft.framework.service.board.audit.view.vo;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.remarkablesoft.framework.model.vo.SearchCnd;
import com.remarkablesoft.framework.util.ApplicationPropertiesUtils;
import com.remarkablesoft.framework.util.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board - audit - view
 * 		@프로그램 ID		:	PostingAuditViewCnd
 * 		@프로그램 개요 		:	포스팅 보기이력 테이블
 *                          - 포스팅은 기존 AuditViewInfo 객체를 사용하지하지 않고 이 객체를 이용한다
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	james	-	신규생성
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true)
@ToString
@EqualsAndHashCode (callSuper = true)
public class PostingAuditViewCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = -6287842830691985709L;

		private String oid;								// OID
		private String postingOid;						// POSTING OID
		private List<String> postingOidList;			// 포스팅에서 리스트에서 fill할때 사용
		private String boardOid;						// BOARD OID

		private String viewUser;						// 본사람 OID
		private LocalDateTime viewDate;					// 본 일시
		private String viewUserIp;						// 사용자 IP
	
		private String viewYear;
		private String viewMonth;
		private String viewWeek;
		private String viewDay;
		private String viewHour;
		
		private int expirationPeriod;					// 조회 유효 기간  단위는 시간 
		private int limit;								// 검색 개수
		private String viewDateFrom;					// 조회 일 시작
		private String viewDateTo;						// 조회 일 종료
		private int searchCount;						// 순위 검색 개수

		/** 날짜 검색 타입 */
		private String searchDateType;
		
		// 순위 제외 포스트 목록
		private String exceptPostingOids = ApplicationPropertiesUtils.getValue( "posting.rank.except.postingoid" , "");
		private List<String> exceptPostingOidList = Arrays.asList( StringUtils.splitPreserveAllTokens( exceptPostingOids, "," ));
		
}
