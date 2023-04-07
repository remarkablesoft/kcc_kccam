package com.remarkablesoft.framework.service.notification.message.send.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - send
* 		@프로그램 ID		:	MessageSendHistInfo
* 		@프로그램 개요 		:	메시지 발송 이력 객체
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
public class MessageSendHistInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = 3405706883384379504L;

		private String oid;
		private String messageOid;								// 메세지 OID
		private String partOid;									// Part OID 
		private String messageType;								// 메세지타입  MessageInfo에 정의
		
		private String receiverOid;								// 수신자 OID
		private String receiverPhone;							// 수신자 번호
		
		private String receiverUuid;							// 설치된 DEVICE 고유 ID - Push용
		private String successYn;								// 발송 성공여부
		private String errorMessage;							// 에러 메세지
		private String replaceOid;								// 대체문자 발송 시 대체문자를 보내게 된 보낸 이력 OID
		
		private String openYn;									// 확인여부
		private LocalDateTime openDate;							// 확인일시
		private LocalDateTime inputDate;						// 생성일자

		MessageInfo messageInfo = null;							// 이력에 대한 메세지 정보
		
		
		
}
