package com.remarkablesoft.framework.service.notification.message.message.vo;

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
* 		@서브 시스템		:	notification - message - message
* 		@프로그램 ID		:	MessageCnd
* 		@프로그램 개요 		:	메시지 검색 객체
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
public class MessageCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = -5862192567047859675L;

		private String oid;
		private String targetOid; 								// 타겟 객체의 Oid
		private int msgTypeMode = -1;							// 메시지타입(친구톡/알림톡/SMS/Push/....)
		private String classType;								// 분류코드 코드값을 지정해서 사용 ex) 활동보고, 공지사항
		private String name;									// 메세지명
		
		private String msgTemplateOid;							// 메시지 템플릿 OID
		private String userOid;									// 사용자 OID
		private String templateId;								// 템플릿 ID
		private String partOid;									// part OID
		
		private List<String> oidList = new ArrayList<>();


}
