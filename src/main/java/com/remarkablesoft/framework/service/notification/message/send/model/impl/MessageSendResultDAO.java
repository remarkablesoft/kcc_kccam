package com.remarkablesoft.framework.service.notification.message.send.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistCnd;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendResultInfo;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - send
* 		@프로그램 ID		:	MessageSendResultDAO
* 		@프로그램 개요	:	메시지 발송 결과 DAO
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 6.	:	choi 	-	신규생성
* 		============================================================================
*/
@DAO
public class MessageSendResultDAO extends BaseDAO {
		
		public int insert( MessageSendResultInfo info ) {
				return sql().insert( id( "insert" ), info );
		}
		
		public int update( MessageSendResultInfo info ) {
				return sql().update( id( "update" ), info );
		}
		
		public MessageSendResultInfo get( MessageSendHistCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}
		
		public PageList<MessageSendResultInfo> list( MessageSendHistCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "list" ), cnd );
		}
		
		public List<MessageSendResultInfo> listAll( MessageSendHistCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}
		
}
