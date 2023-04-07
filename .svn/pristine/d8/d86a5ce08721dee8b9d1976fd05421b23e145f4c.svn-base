package com.remarkablesoft.site.kccam.service.onetoone.onetoone.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;

import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneInfo;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneCnd;



/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	onetoone - onetoone
 * 		@프로그램 ID		:	OneToOneDAO
 * 		@프로그램 개요		:	OneToOne DAO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@DAO
public class OneToOneDAO extends BaseDAO {

    public int insert( OneToOneInfo info ) {
        return sql().insert( id( "insert" ),  info );
    }

    public int update( OneToOneInfo info ) {
        return sql().update( id( "update" ),  info );
    }

    public int delete( String oid ) {
        return sql().delete( id( "delete" ),  oid );
    }

    public OneToOneInfo get( OneToOneCnd cnd ) {
        return sql().selectOne( id( "get" ),  cnd );
    }

    public PageList<OneToOneInfo> list( OneToOneCnd cnd ) {
        return sql().queryForPageListAndTCount( id( "list" ),  cnd );
    }

    public List<OneToOneInfo> listAll( OneToOneCnd cnd ) {
        return sql().selectList( id( "listAll" ),  cnd );
    }
		
    public int deleteByInputUser( String oid ) {
        return sql().delete( id("deleteByInputUser"), oid );
    }
}
