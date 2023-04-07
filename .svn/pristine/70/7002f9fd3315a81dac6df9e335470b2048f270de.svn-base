package com.remarkablesoft.framework.service.notification.message.send.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - send
* 		@프로그램 ID		:	MessageSendResultInfo
* 		@프로그램 개요 		:	메시지 발송 결과 객체
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
public class MessageSendResultInfo extends Entity {


		/**
		 *
		 */
		private static final long serialVersionUID = -5956614470014059989L;

		private String oid;
		private String messageOid;								// 메세지 OID
		private String partOid;									// Part OID

		private int sendTotalCount;								// 발송 총카운트
		private int sendSuccessCount;							// 발송 성공 카운트
		private int sendFailCount;								// 발송 실패 카운트
		private int sendOpenCount;								// 발송 확인 카운트

		private String inputUser;								// 발신인
		private LocalDateTime inputDate;						// 생성일자
		private String customField1;							// 커스텀필드1
		private String customField2;							// 커스텀필드2
		private String customField3;							// 커스텀필드3

}
