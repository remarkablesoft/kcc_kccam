package com.remarkablesoft.framework.service.notification.message.group.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.notification.message.group.vo.MessageGroupCnd;
import com.remarkablesoft.framework.service.notification.message.group.vo.MessageGroupUserRelInfo;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - group
* 		@프로그램 ID		:	MessageGroupUserRelDAO
* 		@프로그램 개요 		:	메시지 그룹과 사용자 Relation DAO 객체
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 4.	:	james	-	신규생성
* 		============================================================================
*/
@DAO
public class MessageGroupUserRelDAO extends BaseDAO {
		
		public int insert( MessageGroupUserRelInfo info ) {
				return sql().insert( id( "insert" ), info );
		}
		
		public int delete( MessageGroupCnd cnd ) {
				return sql().delete( id( "delete" ), cnd );
		}
		
		public List<MessageGroupUserRelInfo> listAll( MessageGroupCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}

}
