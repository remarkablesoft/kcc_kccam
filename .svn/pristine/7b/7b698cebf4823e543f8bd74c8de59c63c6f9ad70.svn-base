package com.remarkablesoft.framework.web.module.notification;

import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module	-	notification
 * 		@프로그램 ID		:	SendManager
 * 		@프로그램 개요 		:	메세지 발신 매니저
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020.  1. 13.	:	최원준	-	신규생성
 * 		1.1  2020. 10. 09.	:	james	-	이름변경(SendManager -> MessageSendProvider) 및  메시지의 base의 interface로만 처리하도록
 * 		============================================================================
 */
public interface MessageSendProvider {

		public void send( MessageInfo message, UserInfo user );
		
}
