package com.remarkablesoft.framework.service.notification.message.booking.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.booking.vo.MessageBookingCnd;
import com.remarkablesoft.framework.service.notification.message.booking.vo.MessageBookingInfo;

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
public class MessageBookingDAO extends BaseDAO {

		public int insert( MessageBookingInfo info ) {
				return sql().insert( id( "insert" ), info );
		}

		public int update( MessageBookingInfo info ) {
				return sql().update( id( "update" ), info );
		}

		public MessageBookingInfo get( MessageBookingCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}

		public PageList<MessageBookingInfo> list( MessageBookingCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "list" ), cnd );
		}

		public List<MessageBookingInfo> listAll( MessageBookingCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}

}
