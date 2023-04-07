package com.remarkablesoft.framework.service.audit.view.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.audit.view.vo.AuditViewCnd;
import com.remarkablesoft.framework.service.audit.view.vo.AuditViewInfo;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	audit - view
* 		@프로그램 ID		:	AuditViewDAO
* 		@프로그램 개요 		:   보기 카운트를 체크하는 DAO
*
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 12.	:	james	-	신규생성
* 		============================================================================
*/
@DAO
public class AuditViewDAO extends BaseDAO {

		public int insert( AuditViewInfo info ) {
				return sql().insert( id( "insert" ), info );
		}

		public int getCountByTargetObject( AuditViewCnd cnd ) {
				return (Integer) sql().selectOne( id( "getCountByTargetObject" ), cnd );
		}

		public List<AuditViewInfo> listByTargetObjectCount( AuditViewCnd cnd ) {
				return sql().selectList( id( "listByTargetObjectCount" ), cnd );
		}

		public List<AuditViewInfo> listByUserCount( AuditViewCnd cnd ) {
				return sql().selectList( id( "listByUserCount" ), cnd );
		}

		public boolean exist( AuditViewInfo info ) {
				Object obj = sql().selectOne( id( "exist" ), info );
				return convertIntegerToBoolean( obj );
		}

		public List<AuditViewInfo> listByLoginUserReadYn( AuditViewCnd cnd ) {
				return sql().selectList( id( "listByLoginUserReadYn" ), cnd );
		}
		
		public List<AuditViewInfo> groupByCountList( AuditViewCnd cnd ) {
				return sql().selectList( id( "groupByCountList" ), cnd );
		}

}
