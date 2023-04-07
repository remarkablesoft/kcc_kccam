package com.remarkablesoft.framework.service.notification.message.send.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistCnd;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistInfo;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - send
* 		@프로그램 ID		:	MessageSendHistDAO
* 		@프로그램 개요 		:	메시지 발송 이력 DAO
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 4.	:	james	-	신규생성
* 		============================================================================
*/
@DAO
public class MessageSendHistDAO extends BaseDAO {
		
		public int insert( MessageSendHistInfo info ) {
				return sql().insert( id( "insert" ), info );
		}
		
		public int update( MessageSendHistInfo info ) {
				return sql().update( id( "update" ), info );
		}
		
		public int delete( MessageSendHistCnd cnd ) {
				return sql().delete( id( "delete" ),cnd );
		}
		
		public PageList<MessageSendHistInfo> list( MessageSendHistCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "list" ), cnd );
		}
		
		public List<MessageSendHistInfo> listAll( MessageSendHistCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}
		
		public List<MessageSendHistInfo> listByMsgOid( MessageSendHistCnd cnd ) {
				return sql().selectList( id( "listByGroupByMsgOid" ), cnd );
		}
		
}
