package com.remarkablesoft.framework.service.mgnt.system.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.system.vo.SystemCnd;
import com.remarkablesoft.framework.service.mgnt.system.vo.SystemInfo;


/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	mgnt - system
* 		@프로그램 ID		:	SystemDAO
* 		@프로그램 개요 		:	시스템 DAO
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 4.	:	james	-	신규생성
* 		============================================================================
*/
@DAO
public class SystemDAO extends BaseDAO {

		public int insert( SystemInfo info ) {
				return sql().insert( id( "insert" ), info );
		}

		public int update( SystemInfo info ) {
				return sql().update( id( "update" ), info );
		}

		public int delete( String oid ) {
				return sql().delete( id( "delete" ), oid );
		}

		public SystemInfo get( SystemCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}

		public int getMaxOrderNo( SystemCnd cnd ) {
			return sql().selectOne( id( "getMaxOrderNo" ), cnd );
		}

		public PageList<SystemInfo> list( SystemCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "list" ), cnd );
		}

		public List<SystemInfo> listAll( SystemCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}

}
