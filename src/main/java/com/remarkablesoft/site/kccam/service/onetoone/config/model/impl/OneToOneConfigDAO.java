package com.remarkablesoft.site.kccam.service.onetoone.config.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;

import com.remarkablesoft.site.kccam.service.onetoone.config.vo.OneToOneConfigInfo;
import com.remarkablesoft.site.kccam.service.onetoone.config.vo.OneToOneConfigCnd;



/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	onetoone - config
 * 		@프로그램 ID		:	OneToOneConfigDAO
 * 		@프로그램 개요		:	OneToOneConfig DAO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@DAO
public class OneToOneConfigDAO extends BaseDAO {

    public int insert( OneToOneConfigInfo info ) {
        return sql().insert( id( "insert" ),  info );
    }

    public int update( OneToOneConfigInfo info ) {
        return sql().update( id( "update" ),  info );
    }

    public int delete( String oid ) {
        return sql().delete( id( "delete" ),  oid );
    }

    public OneToOneConfigInfo get( OneToOneConfigCnd cnd ) {
        return sql().selectOne( id( "get" ),  cnd );
    }

    public List<OneToOneConfigInfo> listAll( OneToOneConfigCnd cnd ) {
        return sql().selectList( id( "listAll" ),  cnd );
    }

    public int deleteAll() {
        return sql().delete(id("deleteAll"));
    }
		
    public int deleteFlagUpdate( OneToOneConfigInfo info ) {
        return sql().update( id( "deleteFlagUpdate"), info );
	}
}
