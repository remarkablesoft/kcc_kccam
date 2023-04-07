package com.remarkablesoft.framework.web.module.notification;

import com.remarkablesoft.framework.service.notification.message.template.vo.MessageTemplateInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

import java.util.Map;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module	-	notification
 * 		@프로그램 ID		:	MessageTemplateManager
 * 		@프로그램 개요 		:	메세지 템플릿 내용을 만들어주는 매니저
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 7. 30.	:	최원준	-	신규생성
 * 		1.1  2020. 10. 09.	:	james	-	이름변경(MessageTemplateManager -> MessageTemplateProvider) 
 * 		============================================================================
 */
public interface MessageTemplateProvider {


		public static final String USER_NAME = "userName";
		public static final String USER_PHONE = "userPhone";
		
		
		/**
		 * 복수의 수신자에게 메세지를 발송할 때 각 수신자에 맞는 템플릿을 만들어줍니다. 
		 * 
		 * @author 최원준
		 * @param msgTemplate
		 * @param user
		 * @return
		 */
		public String makeTemplateContents( MessageTemplateInfo msgTemplate, UserInfo user );
		
		/**
		 * DB 에 저장되어있는 템플릿 정보로 템플릿 내용을 만듭니다.
		 * 
		 * @author 최원준
		 * @param msgTemplate
		 * @return
		 */
		public String makeTemplateContents( MessageTemplateInfo msgTemplate );
		
		
		/**
		 * 템플릿에 있는 변수 키에 맞는 맵 value 값으로 템플릿 내용을 만듭니다.
		 * 
		 * @author 최원준
		 * @param msgTemplate
		 * @param itemMap
		 * @return
		 */
		public String makeTemplateContents( MessageTemplateInfo msgTemplate, Map<String, String> itemMap );

		
}
