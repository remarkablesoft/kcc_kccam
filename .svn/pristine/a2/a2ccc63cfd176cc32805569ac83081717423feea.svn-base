package com.remarkablesoft.site.kccam.service.lang.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;

import com.remarkablesoft.site.kccam.service.lang.vo.LangInfo;
import com.remarkablesoft.site.kccam.service.lang.vo.LangCnd;



/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	lang - lang
 * 		@프로그램 ID		:	LangDAO
 * 		@프로그램 개요		:	Lang DAO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@DAO
public class LangDAO extends BaseDAO {

    public int insert( LangInfo info ) {
        return sql().insert( id( "insert" ),  info );
    }

    public int delete( String oid ) {
        return sql().delete( id( "delete" ),  oid );
    }

    public LangInfo get( LangCnd cnd ) {
        return sql().selectOne( id( "get" ),  cnd );
    }

    public List<LangInfo> listAll( LangCnd cnd ) {
        return sql().selectList( id( "listAll" ),  cnd );
    }

}
