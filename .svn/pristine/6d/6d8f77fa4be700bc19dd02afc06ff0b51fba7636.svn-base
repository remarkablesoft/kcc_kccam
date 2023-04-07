package com.remarkablesoft.framework.service.doc.doc.model.impl;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocVersionInfo;

import java.util.List;


/**
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	doc - doc
 * 		@프로그램 ID		:	DocDAO
 * 		@프로그램 개요		:	Doc DAO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@DAO
public class DocVersionDAO extends BaseDAO {

    public int insert( DocVersionInfo info ) {
        return sql().insert( id( "insert" ),  info );
    }

    public int update( DocVersionInfo info ) {
        return sql().update( id( "update" ),  info );
    }

    public int delete( DocCnd cnd ) {
        return sql().delete( id( "delete" ),  cnd );
    }

    public DocVersionInfo get( DocCnd cnd ) {
        return sql().selectOne( id( "get" ),  cnd );
    }

    public PageList<DocVersionInfo> list( DocCnd cnd ) {
        return sql().queryForPageListAndTCount( id( "list" ),  cnd );
    }

    public List<DocVersionInfo> listAll( DocCnd cnd ) {
        return sql().selectList( id( "listAll" ),  cnd );
    }

    public boolean exist( String oid  ) {
        Object obj = sql().selectOne( id( "exist" ), oid );
        return convertIntegerToBoolean( obj );
    }

}
