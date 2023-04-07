package com.remarkablesoft.site.kccam.service.material.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialCnd;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;

/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	Material - Material
 * 		@프로그램 ID		:	MaterialLangDAO
 * 		@프로그램 개요		:	Material 다국어 dao
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 05. 25. : Woong - 신규생성
 *      ============================================================================
 */

@DAO
public class MaterialLangDAO extends BaseDAO {

		public int insert( MaterialInfo materialInfo ) {
				return sql().insert( id( "insert" ), materialInfo );
		}

		public int update( MaterialInfo materialInfo ) {
				return sql().update( id( "update" ), materialInfo );
		}

		public boolean exist( MaterialCnd cnd ) {
				return convertIntegerToBoolean( sql().selectOne( id( "exist" ), cnd ) );
		}

		public int delete( MaterialCnd cnd ) {
				return sql().delete( id( "delete" ), cnd );
		}
		
		public List<MaterialInfo> listAll( String oid ) {
			return sql().selectList( id( "listAll" ), oid );
	}

}
