package com.remarkablesoft.site.kccam.service.datasheet.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;

import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetCnd;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetTargetItemInfo;


/**
 * 	                                                           
 * 		@주시스템			:	kccam
 * 		@서브 시스템		:	datasheet - datasheetTargetItem
 * 		@프로그램 ID		:	DatasheetTargetItemDAO
 * 		@프로그램 개요		:	DatasheetTargetItem DAO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@DAO
public class DatasheetTargetItemDAO extends BaseDAO {

    public int insert( DatasheetTargetItemInfo info ) {
        return sql().insert( id( "insert" ),  info );
    }

    public int update( DatasheetTargetItemInfo info ) {
        return sql().update( id( "update" ),  info );
    }

    public int delete( DatasheetCnd cnd ) {
        return sql().delete( id( "delete" ),  cnd );
    }

    public DatasheetTargetItemInfo get( DatasheetCnd cnd ) {
        return sql().selectOne( id( "get" ),  cnd );
    }

    public List<DatasheetTargetItemInfo> listAll( DatasheetCnd cnd ) {
        return sql().selectList( id( "listAll" ),  cnd );
    }

}
