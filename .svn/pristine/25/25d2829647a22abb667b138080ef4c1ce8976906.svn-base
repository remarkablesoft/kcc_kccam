package com.remarkablesoft.framework.service.notification.message.template.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageCnd;
import com.remarkablesoft.framework.service.notification.message.template.vo.MessageTemplateItemInfo;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - template
* 		@프로그램 ID		:	MessageTemplateDAO
* 		@프로그램 개요 		:	메시지 템플릿 아이템 DAO
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 6.	:	choi	-	신규생성
* 		============================================================================
*/
@DAO
public class MessageTemplateItemDAO extends BaseDAO {
		
		public int insert( MessageTemplateItemInfo info ) {
				return sql().insert( id( "insert" ), info );
		}
		
		public int update( MessageTemplateItemInfo info ) {
				return sql().update( id( "update" ), info );
		}
		
		public int delete( MessageCnd cnd ) {
				return sql().delete( id( "delete" ), cnd );
		}
		
		public MessageTemplateItemInfo get( MessageCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}
		
		public List<MessageTemplateItemInfo> listAll( MessageCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}
		
}
