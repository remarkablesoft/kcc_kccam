package com.remarkablesoft.framework.service.mgnt.part.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartCnd;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt - part
 * 		@프로그램 ID		:	PartDAO
 * 		@프로그램 개요 		:	Part DAO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	hong	-	신규생성
 * 		============================================================================
 */
@DAO
public class PartDAO extends BaseDAO {

		public int insert( PartInfo info ) {
				return sql().insert( id( "insert" ), info );
		}

		public PartInfo get( PartCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}

		public int update( PartInfo info ) {
				return sql().update( id( "update" ), info );
		}

		public int delete( PartCnd cnd ) {
				return sql().delete( id( "delete" ), cnd.getOid() );
		}

		public PageList<PartInfo> list( PartCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "list" ), cnd );
		}

		public List<PartInfo> listAll( PartCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}
}
