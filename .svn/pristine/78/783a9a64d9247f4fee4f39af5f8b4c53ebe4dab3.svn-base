package com.remarkablesoft.framework.service.audit.visit.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitCnd;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitInfo;
import com.remarkablesoft.framework.util.StringUtils;;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	audit - visit
* 		@프로그램 ID		:	AuditVisitDAO
* 		@프로그램 개요 		:   방문자 수 통계를 체크하는 DAO
*
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 12.	:	james	-	신규생성
* 		============================================================================
*/
@DAO
public class AuditVisitDAO extends BaseDAO {
		
		public AuditVisitInfo insert( AuditVisitInfo info ) {
				
				if ( StringUtils.isEmpty( info.getOid() ) ) {
						
						info.setOid( OIDGenerator.generateOID() );
				}
				
				sql().insert( id( "insert" ), info );
				
				return info;
		}
		
		public int insertBulk( List<AuditVisitInfo> list  ) {
				
				return sql().insert( id( "insertBulk" ), list );
		}

		public AuditVisitInfo get( AuditVisitCnd cnd ) {
				
				return sql().selectOne( id( "get" ), cnd );
		}
		
		public List<AuditVisitInfo> listAll( AuditVisitCnd cnd ) {
				
				return sql().selectList( id( "listAll" ), cnd );
		}
		
		public PageList<AuditVisitInfo> list( AuditVisitCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "list" ), cnd );
		}
		
		public int listCount( AuditVisitCnd cnd ) {
				return sql().selectOne( id( "list_count" ), cnd );
		}
		
		public int update( AuditVisitInfo info ) {
				return sql().update( id( "update" ), info );
		}
		
		public int delete( String oid ) {
				
				return sql().delete( id( "delete" ), oid );
		}

		public int deleteAll( AuditVisitCnd cnd ) {
			
				return sql().delete( id( "deleteAll" ), cnd );
		}
		
		
		
}
