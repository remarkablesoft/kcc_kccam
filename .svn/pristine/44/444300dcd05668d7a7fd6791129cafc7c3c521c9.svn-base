package com.remarkablesoft.site.kccam.service.product.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;

import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductRelInfo;


/**
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	product - productRel
 * 		@프로그램 ID		:	ProductRelDAO
 * 		@프로그램 개요		:	ProductRel DAO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@DAO
public class ProductRelDAO extends BaseDAO {

    public int insert( ProductRelInfo info ) {
        return sql().insert( id( "insert" ),  info );
    }

    public int insertBulk( List<ProductRelInfo> list ) {
        return sql().insert( id( "insertBulk" ), list );
    }
    
    public int delete(ProductCnd cnd ) {
        return sql().delete( id( "delete" ),  cnd );
    }

    public ProductRelInfo get(ProductCnd cnd ) {
        return sql().selectOne( id( "get" ),  cnd );
    }

    public List<ProductRelInfo> listAll(ProductCnd cnd ) {
        return sql().selectList( id( "listAll" ),  cnd );
    }

    public boolean exist( ProductCnd cnd ) {
		Object obj = sql().selectOne( id( "exist" ), cnd );
		return convertIntegerToBoolean( obj );
	}
    
}
