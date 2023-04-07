package com.remarkablesoft.framework.service.notification.message.send.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.model.MessageSendService;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistCnd;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;


/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - send
* 		@프로그램 ID		:	MessageSendServiceImpl
* 		@프로그램 개요 		:	메시지 발송 ServiceImpl
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 9.	:	james	-	신규생성
* 		1.1  2020. 07. 20.	:	james	-	신규생성
* 		============================================================================
*/
@Service
@Transactional
public class MessageSendServiceImpl implements MessageSendService {

		@Autowired
		protected MessageSendBLO messageSendBLO;

		@Autowired
		protected MessageSendHistBLO messageSendHistBLO;
		
		
		@Override
		public void send( MessageInfo info, String userOid ) {
				
				messageSendBLO.send( info, userOid );
				
		}
		
		/**
		 * 해당 메세지의 관련된 설정을 찾아 모두 발송
		 *
		 * @author 최원준
		 * @param info
		 * @return
		 */
		public void sendAll( MessageInfo info, UserInfo... receiveUsers ) {
		
				messageSendBLO.sendAll( info, receiveUsers );
		}
		
		
		@Override
		public void sendAll( MessageInfo info, List<UserInfo> receiveUserList ) {

				messageSendBLO.sendAll( info, receiveUserList );
		}


		@Override
		public PageList<MessageSendHistInfo> histList( MessageSendHistCnd cnd ) {
				
				return messageSendHistBLO.list( cnd );
		}





}
