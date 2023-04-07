package com.remarkablesoft.framework.service.doc.doc.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;

import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;
import com.remarkablesoft.framework.service.doc.doc.vo.DocRelInfo;


/**
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	doc - docRel
 * 		@프로그램 ID		:	DocRelDAO
 * 		@프로그램 개요		:	DocRel DAO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@DAO
public class DocRelDAO extends BaseDAO {

    public int insert( DocRelInfo info ) {
        return sql().insert( id( "insert" ),  info );
    }

    public int update( DocRelInfo info ) {
        return sql().update( id( "update" ),  info );
    }

    public int delete( DocCnd cnd ) {
        return sql().delete( id( "delete" ),  cnd );
    }

    public DocRelInfo get( DocCnd cnd ) {
        return sql().selectOne( id( "get" ),  cnd );
    }

    public List<DocRelInfo> listAll( DocCnd cnd ) {
        return sql().selectList( id( "listAll" ),  cnd );
    }

    public boolean exist( String oid  ) {
        Object obj = sql().selectOne( id( "exist" ), oid );
        return convertIntegerToBoolean( obj );
    }

}
