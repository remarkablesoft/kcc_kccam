package com.remarkablesoft.site.kccam.service.lang.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;

import com.remarkablesoft.site.kccam.service.lang.vo.LangCnd;
import com.remarkablesoft.site.kccam.service.lang.vo.LangItemInfo;


/**
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	lang - langItem
 * 		@프로그램 ID		:	LangItemDAO
 * 		@프로그램 개요		:	LangItem DAO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@DAO
public class LangItemDAO extends BaseDAO {

    public int insert( LangItemInfo info ) {
        return sql().insert( id( "insert" ),  info );
    }

    public int update( LangItemInfo info ) {
        return sql().update( id( "update" ),  info );
    }

    public int delete( LangCnd cnd ) {
        return sql().delete( id( "delete" ),  cnd );
    }

    public LangItemInfo get( LangCnd cnd ) {
        return sql().selectOne( id( "get" ),  cnd );
    }

    public List<LangItemInfo> listAll( LangCnd cnd ) {
        return sql().selectList( id( "listAll" ),  cnd );
    }

    public boolean exist( LangItemInfo info  ) {
        Object obj = sql().selectOne( id( "exist" ), info );
        return convertIntegerToBoolean( obj );
    }

}
