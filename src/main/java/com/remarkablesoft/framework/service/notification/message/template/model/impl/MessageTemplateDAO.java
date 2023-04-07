package com.remarkablesoft.framework.service.notification.message.template.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageCnd;
import com.remarkablesoft.framework.service.notification.message.template.vo.MessageTemplateInfo;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - template
* 		@프로그램 ID		:	MessageTemplateDAO
* 		@프로그램 개요 		:	메시지 템플릿 DAO
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 4.	:	james	-	신규생성
* 		============================================================================
*/
@DAO
public class MessageTemplateDAO extends BaseDAO {
		
		public int insert( MessageTemplateInfo info ) {
				return sql().insert( id( "insert" ), info );
		}
		
		public int update( MessageTemplateInfo info ) {
				return sql().update( id( "update" ), info );
		}
		
		public int delete( MessageCnd cnd ) {
				return sql().delete( id( "delete" ), cnd );
		}
		
		public MessageTemplateInfo get( MessageCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}

		public PageList<MessageTemplateInfo> list( MessageCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "list" ), cnd );
		}
		
		public List<MessageTemplateInfo> listAll( MessageCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}
		
}
