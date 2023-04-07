package com.remarkablesoft.framework.service.board.audit.view.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board - audit - view
 * 		@프로그램 ID		:	PostingAuditViewInfo
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
@Accessors( chain = true )
@ToString
public class PostingAuditViewInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = -7528154519833049172L;

		private String oid;								// OID
		private String postingOid;						// POSTING OID
		private String boardOid;						// BOARD OID
		
		
		private String viewYear;							// 등록 년
		private String viewMonth;							// 등록 월
		private String viewWeek;							// 등록 주
		private String viewDay;								// 등록 일
		private String viewHour;							// 등록시간

		private String viewUser;						// 본사람 OID
		private LocalDateTime viewDate;					// 본 일시
		private String viewUserIp;						// 사용자 IP

		private int viewCountVC = 0;					// 뷰 카운트


}
