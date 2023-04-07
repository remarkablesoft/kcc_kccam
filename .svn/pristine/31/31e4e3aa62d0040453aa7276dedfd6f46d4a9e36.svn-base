package com.remarkablesoft.framework.service.notification.message.booking.vo;

import java.time.LocalDate;
import java.time.LocalTime;

import com.remarkablesoft.framework.model.vo.SearchCnd;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - booking
* 		@프로그램 ID		:	MessageBookingCnd
* 		@프로그램 개요 		:	메시지 예약 검색 객체
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 4.	:	james	-	신규생성
* 		============================================================================
*/

@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class MessageBookingCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = -5862192567047859675L;

		private String oid;
		private String messageOid;									// 메세지 OID
		private LocalDate bookingDay;								// 예약일자 - 년:월:일을 저장
		private LocalTime bookingTime;								// 예약시간 - 시간:분:초를 저장
		private String processTypeFlag;								// 프로세스 대기 W, 완료 D



}
