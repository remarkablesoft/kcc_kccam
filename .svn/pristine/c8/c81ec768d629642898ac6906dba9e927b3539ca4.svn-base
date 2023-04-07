package com.remarkablesoft.framework.service.doc.doc.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;

import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;



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
public class DocDAO extends BaseDAO {

    public int insert( DocInfo info ) {
        return sql().insert( id( "insert" ),  info );
    }

    public int update( DocInfo info ) {
        return sql().update( id( "update" ),  info );
    }

    public int delete( String oid ) {
        return sql().delete( id( "delete" ),  oid );
    }

    public DocInfo get( DocCnd cnd ) {
        return sql().selectOne( id( "get" ),  cnd );
    }

    public PageList<DocInfo> list( DocCnd cnd ) {
        return sql().queryForPageListAndTCount( id( "list" ),  cnd );
    }

    public List<DocInfo> listAll( DocCnd cnd ) {
        return sql().selectList( id( "listAll" ),  cnd );
    }
    
    public List<DocInfo> viewList( DocCnd cnd ) {
        return sql().selectList( id( "viewList" ), cnd );
    }
}
