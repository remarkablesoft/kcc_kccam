package com.remarkablesoft.framework.service.notification.message.group.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.group.vo.MessageGroupCnd;
import com.remarkablesoft.framework.service.notification.message.group.vo.MessageGroupInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	notification - message - group	
 * 		@프로그램 ID		:	MessageGroupService
 * 		@프로그램 개요 		:	메세지 그룹 서비스
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 1. 6.	:	최원준	-	신규생성
 * 		============================================================================
 */
public interface MessageGroupService {

		/**
		 * 메세지 그룹 정보를 삭제합니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public int delete( MessageGroupCnd cnd );
		
		/**
		 * 메세지 그룹 정보를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public MessageGroupInfo get( MessageGroupCnd cnd );

		/**
		 * 조건에 맞는 메세지 그룹 정보 리스트를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public List<MessageGroupInfo> listAll( MessageGroupCnd cnd );

		/**
		 * 메세지 그룹정보에 속해있는 유저 페이지 리스트를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public PageList<UserInfo> groupUserList( MessageGroupCnd cnd );
		
}
