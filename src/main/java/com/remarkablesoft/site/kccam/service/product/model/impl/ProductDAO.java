package com.remarkablesoft.site.kccam.service.product.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;

import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;



/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	product - product
 * 		@프로그램 ID		:	ProductDAO
 * 		@프로그램 개요		:	Product DAO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@DAO
public class ProductDAO extends BaseDAO {

    public int insert( ProductInfo info ) {
        return sql().insert( id( "insert" ),  info );
    }

    public int update( ProductInfo info ) {
        return sql().update( id( "update" ),  info );
    }

    public int delete( String oid ) {
        return sql().delete( id( "delete" ),  oid );
    }

    public ProductInfo get( ProductCnd cnd ) {
        return sql().selectOne( id( "get" ),  cnd );
    }

    public PageList<ProductInfo> list( ProductCnd cnd ) {
        return sql().queryForPageListAndTCount( id( "list" ),  cnd );
    }

    public List<ProductInfo> listAll( ProductCnd cnd ) {
        return sql().selectList( id( "listAll" ),  cnd );
    }

    public boolean exist( ProductCnd cnd  ) {
        Object obj = sql().selectOne( id( "exist" ), cnd );
        return convertIntegerToBoolean( obj );
    }
    
    public List<ProductInfo> viewListAll( ProductCnd cnd ) {
    	return sql().selectList( id( "viewListAll" ), cnd );
	}
    
	public List<ProductInfo> cntListGroupByMaterialOid( ProductCnd cnd ) {
		return sql().selectList( id( "cntListGroupByMaterialOid" ), cnd );
	}
	
	public List<ProductInfo> targetExceptList( ProductCnd cnd ) {
    	return sql().selectList( id( "targetExceptList" ), cnd );
	}
    
}
