package com.remarkablesoft.site.kccam.service.datasheet.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;

import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetCnd;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetItemInfo;


/**
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	datasheet - datasheetItem
 * 		@프로그램 ID		:	DatasheetItemDAO
 * 		@프로그램 개요		:	DatasheetItem DAO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@DAO
public class DatasheetItemDAO extends BaseDAO {

    public int insert( DatasheetItemInfo info ) {
        return sql().insert( id( "insert" ),  info );
    }
    
    public int insertBulk( List<DatasheetItemInfo> list ) {
    	return sql().insert( id( "insertBulk" ), list );
	}

    public int update( DatasheetItemInfo info ) {
        return sql().update( id( "update" ),  info );
    }

    public int delete( DatasheetCnd cnd ) {
        return sql().delete( id( "delete" ),  cnd );
    }

    public DatasheetItemInfo get( DatasheetCnd cnd ) {
        return sql().selectOne( id( "get" ),  cnd );
    }

    public List<DatasheetItemInfo> listAll( DatasheetCnd cnd ) {
        return sql().selectList( id( "listAll" ),  cnd );
    }

}
