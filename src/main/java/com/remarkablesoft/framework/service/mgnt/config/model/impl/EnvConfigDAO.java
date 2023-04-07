package com.remarkablesoft.framework.service.mgnt.config.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.mgnt.config.vo.EnvConfigInfo;


/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt-config
 * 		@프로그램 ID		:	EnvConfigDAO
 * 		@프로그램 개요 		:	환경설정 테이블
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 6. 7.	:	james	-	신규생성
 * 		============================================================================
 */
@DAO
public class EnvConfigDAO extends BaseDAO {

	public int insert( EnvConfigInfo info ) {
		return sql().insert( id( "insert" ), info );
	}

	public boolean exist( EnvConfigInfo info ) {
			Object obj = sql().selectOne( id( "exist" ), info );
			return convertIntegerToBoolean( obj );
	}

	public int update( EnvConfigInfo info ) {
			return sql().update( id( "update" ), info );
	}

	public int delete( String envKey ) {
			return sql().delete( id( "delete" ), envKey );
	}

	public EnvConfigInfo get( String envKey ) {
			return sql().selectOne( id( "get" ), envKey );
	}

	public List<EnvConfigInfo> listAll( ) {
			return sql().selectList( id( "listAll" ));
	}

}
