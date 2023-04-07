package com.remarkablesoft.framework.service.notification.message.template.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
* 		@프로그램 개요 		:	메시지 템플릿 객체
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
public class MessageTemplateInfo extends Entity {


		/**
		 *
		 */
		private static final long serialVersionUID = -7729966513630224927L;


		private String oid;							// 리마커블관리하는 messageTemplate이란 뜻
		private String partOid;						// Part OID
		private String targetOid; 					// 타겟 객체의 Oid
		private String targetObject; 				// 타겟 객체의 타입
		private String name;						// 메세지 템플릿명
		
		private String classType;					// 분류코드 ex) 개인정보이용, 회원가입
		private String contents;					// 콘텐츠
		private String templateId;					// 외부의 messageTemplate이란 뜻 - 메시지 유형 템플릿 코드(알림톡 필수)
		
		private String imageUrl1;					// 이미지 Url1
		private String imageUrl2;					// 이미지 Url2
		private String imageUrl3;					// 이미지 Url3

		private String linkUrl1;					// 링크 Url1
		private String linkUrl2;					// 링크 Url2
		private String linkUrl3;					// 링크 Url3

		private String inputUser;					// 생성자
		private LocalDateTime inputDate = LocalDateTime.now();			// 생성일시

		private List<MessageTemplateItemInfo> templateItemList = new ArrayList<>();		// 템플릿의 치환할 변수 리스트 
		

}
