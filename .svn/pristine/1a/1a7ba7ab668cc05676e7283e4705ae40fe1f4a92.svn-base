package com.remarkablesoft.framework.service.notification.message.message.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageCnd;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - message
* 		@프로그램 ID		:	MessageDAO
* 		@프로그램 개요 		:	메시지 DAO
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 4.	:	james	-	신규생성
* 		============================================================================
*/
@DAO
public class MessageDAO extends BaseDAO {

		public int insert( MessageInfo info ) {
				return sql().insert( id( "insert" ), info );
		}

		public int update( MessageInfo info ) {
				return sql().update( id( "update" ), info );
		}

		public MessageInfo get( MessageCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}

		public PageList<MessageInfo> list( MessageCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "list" ), cnd );
		}

		public List<MessageInfo> listAll( MessageCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}

}
