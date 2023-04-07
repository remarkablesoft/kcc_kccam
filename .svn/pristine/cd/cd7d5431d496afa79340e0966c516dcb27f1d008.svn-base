package com.remarkablesoft.framework.service.mgnt.category.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryCnd;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryInfo;

/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt-category
 * 		@프로그램 ID		:	CategoryLangDAO
 * 		@프로그램 개요 	:	Category 다국어 dao
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2021. 5. 19.	:	james	-	신규생성
 * 		============================================================================
 */
@DAO
public class CategoryLangDAO extends BaseDAO {

		public int insert( CategoryInfo categoryInfo ) {
				return sql().insert( id( "insert" ), categoryInfo );
		}

		public int update( CategoryInfo categoryInfo ) {
				return sql().update( id( "update" ), categoryInfo );
		}

		public boolean exist( CategoryCnd cnd ) {
				return convertIntegerToBoolean( sql().selectOne( id( "exist" ), cnd ) );
		}

		public int delete( CategoryCnd cnd ) {
				return sql().delete( id( "delete" ), cnd );
		}

		public int deleteAll( String oid ) {
				return sql().delete( id( "deleteAll" ), oid );
		}
		
		public List<CategoryInfo> listAll( String oid ) {
				return sql().selectList( id( "listAll" ), oid );
		}

}
