package com.remarkablesoft.site.kccam.service.datasheet.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;

import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetInfo;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetCnd;



/**                                                            
 * 	                                                           
 * 		@주시스템		:	framework-web
 * 		@서브 시스템		:	datasheet - datasheet
 * 		@프로그램 ID		:	DatasheetDAO
 * 		@프로그램 개요	:	Datasheet DAO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@DAO
public class DatasheetDAO extends BaseDAO {

    public int insert( DatasheetInfo info ) {
        return sql().insert( id( "insert" ),  info );
    }

    public int update( DatasheetInfo info ) {
        return sql().update( id( "update" ),  info );
    }

    public int delete( String oid ) {
        return sql().delete( id( "delete" ),  oid );
    }

    public DatasheetInfo get( DatasheetCnd cnd ) {
        return sql().selectOne( id( "get" ),  cnd );
    }

    public PageList<DatasheetInfo> list( DatasheetCnd cnd ) {
        return sql().queryForPageListAndTCount( id( "list" ),  cnd );
    }

    public List<DatasheetInfo> listAll( DatasheetCnd cnd ) {
        return sql().selectList( id( "listAll" ),  cnd );
    }

}
