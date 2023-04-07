package com.remarkablesoft.framework.service.notification.message.message.model;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageCnd;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;

/**
 * 		@주시스템			:	framwork-web
 * 		@서브 시스템		:	notification - message - message
 * 		@프로그램 ID		:	MessageService
 * 		@프로그램 개요 	:	메세지 정보 서비스
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 1. 3.	:	최원준	-	신규생성
 * 		============================================================================
 */
public interface MessageService {

		/**
		 * 메세지 정보를 저장합니다.
		 * 
		 * @author 최원준
		 * @param info
		 * @return
		 */
		public MessageInfo insert( MessageInfo info );
		
		
		/**
		 * 메세지 정보를 수정합니다.
		 * 
		 * @author 최원준
		 * @param info
		 * @return
		 */
		public MessageInfo update( MessageInfo info );
		
		/**
		 * 메세지 정보를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public MessageInfo get( MessageCnd cnd );

		/**
		 * 메세지 정보 페이지 리스트를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public PageList<MessageInfo> list( MessageCnd cnd );

		
}
