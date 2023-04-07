package com.remarkablesoft.site.kccam.service.material.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartCnd;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartInfo;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialCnd;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	service - material
 * 		@프로그램 ID		:	MaterialDAO
 * 		@프로그램 개요 		:	MaterialDAO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2021. 05. 25.	:	Woong	-	신규생성
 * 		============================================================================
 */
@DAO
public class MaterialDAO extends BaseDAO {

		public int insert( MaterialInfo info ) {
				return sql().insert( id( "insert" ), info );
		}

		public MaterialInfo get( MaterialCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}

		public int update( MaterialInfo info ) {
				return sql().update( id( "update" ), info );
		}

		public int delete( MaterialCnd cnd ) {
				return sql().delete( id( "delete" ), cnd.getOid() );
		}

		public PageList<MaterialInfo> list( MaterialCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "list" ), cnd );
		}

		public List<MaterialInfo> listAll( MaterialCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}
}
