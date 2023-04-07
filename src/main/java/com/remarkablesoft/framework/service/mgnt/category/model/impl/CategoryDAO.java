package com.remarkablesoft.framework.service.mgnt.category.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryCnd;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt-category
 * 		@프로그램 ID		:	CategoryDAO
 * 		@프로그램 개요 	:	카테고리 객체. 단독으로 사용하고 board의 부모로도 사용
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	hong	-	신규생성
 * 		============================================================================
 */
@DAO
public class CategoryDAO extends BaseDAO{

		public int insert( CategoryInfo info ) {
				return sql().insert( id( "insert" ), info );
		}

		public CategoryInfo get( String oid ) {

				CategoryCnd cnd = new CategoryCnd();
				cnd.setOid( oid );

				return get ( cnd);
		}

		public CategoryInfo get( CategoryCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}

		public String getFullPathName( CategoryCnd cnd ) {
				return sql().selectOne( id( "getFullPathName" ), cnd );
		}

		public int update( CategoryInfo info ) {
				return sql().update( id( "update" ), info );
		}


		public int delete( String oid ) {
				return sql().delete( id( "delete" ), oid );
		}


		public PageList<CategoryInfo> list( CategoryCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "list" ), cnd );
		}

		public List<CategoryInfo> listAll( CategoryCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}

}
