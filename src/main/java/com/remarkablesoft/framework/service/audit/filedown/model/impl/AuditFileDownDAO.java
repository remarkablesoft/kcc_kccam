package com.remarkablesoft.framework.service.audit.filedown.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.audit.filedown.vo.AuditFileDownInfo;
import com.remarkablesoft.framework.service.storage.file.vo.FileCnd;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	audit - filedown
 * 		@프로그램 ID		:	AuditFileDownDAO
 * 		@프로그램 개요 		:	파일 다운로드 이력 DAO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	james	-	신규생성
 * 		1.1  2020. 11. 01.	:	james	-	audit로 패키지 변경, 클래스명 변경 FileDownAuditDAO -> AuditFileDownDAO
 * 		============================================================================
 */

@DAO
public class AuditFileDownDAO extends BaseDAO{

		public int insert( AuditFileDownInfo info ) {

				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}

				return sql().insert( id( "insert" ), info );
		}

		public AuditFileDownInfo get( FileCnd cnd ) {
				return (AuditFileDownInfo) sql().selectOne( id( "get" ), cnd );
		}

		public List<AuditFileDownInfo> listAll( FileCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}

		public int delete( String oid ) {
				return sql().delete( id( "delete" ), oid );
		}

}
