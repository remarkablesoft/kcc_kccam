package com.remarkablesoft.framework.service.notification.message.send.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistCnd;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - send
* 		@프로그램 ID		:	MessageSendService
* 		@프로그램 개요 		:	메시지 발송 Service
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 09.	:	james	-	신규생성
* 		1.1  2020. 10. 04.	:	james	-	send 메소드는 return값을 받지 않도록 변경하여 처리. send는 보내고 끝.
* 		============================================================================
*/
public interface MessageSendService {
		
		
		/**
		 * 단일 메세지를 발송합니다.
		 * 단일 메세지 발송의 경우 이력만 저장하고 전체 발송결과를 저장하지 않습니다.
		 * 
		 * @author james
		 * @param info
		 */
		public void send( MessageInfo info, String userOid );


		/**
		 * 해당 메세지의 관련된 설정을 찾아 모두 발송
		 *
		 * @author 최원준
		 * @param info
		 * @return
		 */
		public void sendAll( MessageInfo info, List<UserInfo> receiveUsers );
		
		/**
		 * 해당 메세지의 관련된 설정을 찾아 모두 발송
		 *
		 * @author 최원준
		 * @param info
		 * @return
		 */
		public void sendAll( MessageInfo info, UserInfo... receiveUsers ) ;

		/**
		 * 조건에 맞는 메세지 발송이력 페이지 리스트를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public PageList<MessageSendHistInfo> histList( MessageSendHistCnd cnd );
		
}
