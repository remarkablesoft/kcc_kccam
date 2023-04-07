package com.remarkablesoft.framework.service.org.group.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.org.group.vo.GroupCnd;
import com.remarkablesoft.framework.service.org.group.vo.GroupInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	org-group
 * 		@프로그램 ID		:	GroupDAO.java
 * 		@프로그램 개요 		:	Group DAO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 4. 4.	:	sunny	-	신규생성
 * 		============================================================================
 */
@DAO
public class GroupDAO extends BaseDAO {

		public boolean existByOid( String oid ) {
				return convertIntegerToBoolean( sql().selectOne( id( "existByOid" ), oid ) );
		}

		public boolean existDuplicate( GroupInfo info ) {
				return convertIntegerToBoolean( sql().selectOne( id( "existDuplicate" ), info ) );
		}
		
		public List<GroupInfo> list( GroupCnd cnd ) {
				return sql().selectList( id( "list" ), cnd );
		}

		public List<GroupInfo> listAll( GroupCnd groupCnd ) {
				return sql().selectList( id( "listAll" ), groupCnd );
		}

		public int insert( GroupInfo groupInfo ) {
				return sql().insert( id( "insert" ), groupInfo );
		}

		public int update( GroupInfo groupInfo ) {
				return sql().update( id( "update" ), groupInfo );
		}

		public GroupInfo get( String oid ) {
				return sql().selectOne( id( "get" ), oid );
		}

		public GroupInfo get( GroupCnd groupCnd ) {
				return sql().selectOne( id( "getByCnd" ), groupCnd );
		}


		/** GROUP_OID에 따른 그룹 멤버 수을 불러옵니다.
		 * 	oid를 파라미터로 주면 해당 oid부서에 속해있는 사용자 수만 가져오고, 빈값으로 넣어주면 각 부서 별 속한 사용자 수를 전부 가져옵니다. */
		public List<GroupInfo> listUserCntByGroup( String oid ) {
				return sql().selectList( id( "listUserCntByGroup" ), oid );
		}

		public int delete( GroupCnd groupCnd ) {
				return sql().delete( id( "delete" ), groupCnd );
		}

		public GroupInfo checkExist( GroupInfo groupInfo ) {
				return sql().selectOne( id( "checkExist" ), groupInfo );
		}


}
