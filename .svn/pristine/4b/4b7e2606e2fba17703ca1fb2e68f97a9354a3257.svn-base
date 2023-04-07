package com.remarkablesoft.framework.service.notification.message.booking.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - booking
* 		@프로그램 ID		:	MessageBookingInfo
* 		@프로그램 개요 		:	메시지 예약 객체 - 예약이 있으면 해당객체를 통해 DB에 넣고 배치로 체크해서 발송하도록 한다
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
public class MessageBookingInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = 7548978198545273118L;

		public final static String PROCESS_TYPE_FLAG_WAIT = "W";
		public final static String PROCESS_TYPE_FLAG_DONE = "D";

		private String oid;
		private String messageOid;									// 메세지 OID
		private String partOid;										// PART OID
		private LocalDate bookingDay;								// 예약일자 - 년:월:일을 저장
		private LocalTime bookingTime;								// 예약시간 - 시간:분:초를 저장
	
		private String processTypeFlag;								// 프로세스 대기 W, 완료 D
		private String inputUser;									// 생성자
		private LocalDateTime inputDate; 							// 생성일자

}
