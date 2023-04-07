package com.remarkablesoft.site.kccam.service.onetoone.onetoone.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;

import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneCnd;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneDetailInfo;


/**
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	onetoone - onetooneDetail
 * 		@프로그램 ID		:	OneToOneDetailDAO
 * 		@프로그램 개요		:	OneToOneDetail DAO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@DAO
public class OneToOneDetailDAO extends BaseDAO {

    public int insert( OneToOneDetailInfo info ) {
        return sql().insert( id( "insert" ),  info );
    }

    public int update( OneToOneDetailInfo info ) {
        return sql().update( id( "update" ),  info );
    }

    public int delete( OneToOneCnd oid ) {
        return sql().delete( id( "delete" ),  oid );
    }

    public OneToOneDetailInfo get( OneToOneCnd cnd ) {
        return sql().selectOne( id( "get" ),  cnd );
    }

    public List<OneToOneDetailInfo> listAll( OneToOneCnd cnd ) {
        return sql().selectList( id( "listAll" ),  cnd );
    }

}
