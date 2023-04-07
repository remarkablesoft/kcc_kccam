package com.remarkablesoft.framework.service.notification.message.template.vo;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - template
* 		@프로그램 ID		:	MessageTemplateInfo
* 		@프로그램 개요 		:	메시지 템플릿 아이템 객체
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
public class MessageTemplateItemInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = -7729966513630224927L;

		private String oid;							// OID
		private String messageTemplateOid;			// 메세지 템플릿 oid

		private String itemKeys ;					// 아이템 키 	ex) 고객님|주문상품명|금액
		private String itemValues ;					// 아이템 값	ex) 홍길동|테이블A|230,000

		public void put( String key, String value ) {
				setItemKeys( key );
				setItemValues( value );
		}

}
