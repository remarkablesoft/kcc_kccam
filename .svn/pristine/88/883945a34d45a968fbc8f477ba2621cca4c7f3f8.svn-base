package com.remarkablesoft.framework.service.mgnt.system.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.mgnt.system.vo.SystemCnd;
import com.remarkablesoft.framework.service.mgnt.system.vo.SystemDetailInfo;


/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	mgnt - system
* 		@프로그램 ID		:	SystemDAO
* 		@프로그램 개요 		:	시스템 상세 DAO
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 4.	:	james	-	신규생성
* 		============================================================================
*/
@DAO
public class SystemDetailDAO extends BaseDAO {

		public int insert( SystemDetailInfo info ) {
				return sql().insert( id( "insert" ), info );
		}

		public int update( SystemDetailInfo info ) {
				return sql().update( id( "update" ), info );
		}

		public int delete( String oid ) {
				return sql().delete( id( "delete" ), oid );
		}

		public SystemDetailInfo get( SystemCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}


		public List<SystemDetailInfo> listAll( SystemCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}


}
