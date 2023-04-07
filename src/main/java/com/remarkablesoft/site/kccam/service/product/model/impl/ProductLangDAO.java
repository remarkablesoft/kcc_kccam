package com.remarkablesoft.site.kccam.service.product.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;

/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	product - product
 * 		@프로그램 ID		:	ProductLangDAO
 * 		@프로그램 개요		:	Product 다국어 dao
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 05. 16. : james - 신규생성
 *      ============================================================================
 */

@DAO
public class ProductLangDAO extends BaseDAO {

		public int insert( ProductInfo productInfo ) {
				return sql().insert( id( "insert" ), productInfo );
		}

		public int update( ProductInfo productInfo ) {
				return sql().update( id( "update" ), productInfo );
		}

		public boolean exist( ProductCnd cnd ) {
				return convertIntegerToBoolean( sql().selectOne( id( "exist" ), cnd ) );
		}

		public int delete( ProductCnd cnd ) {
				return sql().delete( id( "delete" ), cnd );
		}
		
		public List<ProductInfo> listAll( String oid ) {
			return sql().selectList( id( "listAll" ), oid );
	}

}
