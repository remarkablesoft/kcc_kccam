package com.remarkablesoft.framework.service.notification.message.send.vo;

import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.SearchCnd;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - send
* 		@프로그램 ID		:	MessageSendHistCnd
* 		@프로그램 개요 		:	메시지 발송 이력 검색 객체
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
public class MessageSendHistCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = 752132299088350070L;

		private String oid;
		private String partOid;												// Part OID
		private String messageOid;											// 메세지 OID
		private String messageType;											// 메세지타입
		
		private String receiverOid;											// 수신자 OID
		private String receiverPhone;										// 수신자 번호

		private String receiverUuid;										// 수신자 UUID
		private String successYn;											// 발송 성공여부
		private String replaceOid;											// 대체문자의 실패 이력 OID
		private String openYn;												// 확인여부

		private List<String> messageOidList = new ArrayList<String>();

		private boolean isFillMessage = false;								// 메세지 정보 fill 여부
		

}
