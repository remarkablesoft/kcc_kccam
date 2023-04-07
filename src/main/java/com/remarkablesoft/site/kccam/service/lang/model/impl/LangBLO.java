package com.remarkablesoft.site.kccam.service.lang.model.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.lang.vo.LangInfo;
import com.remarkablesoft.site.kccam.service.lang.vo.LangCnd;
import com.remarkablesoft.framework.util.StringUtils;



/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	lang - lang
 * 		@프로그램 ID		:	LangBLO
 * 		@프로그램 개요		:	Lang BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@BLO
public class LangBLO {

    @Autowired
    protected LangDAO langDAO;

    /**
    * 다국어정보를 저장합니다.
    *
    * @param info
    * @return LangInfo
    * @author 최원준
    */
    public LangInfo insert( LangInfo info ) {

        if ( StringUtils.isEmpty( info.getOid() ) ) {
        		info.setOid( OIDGenerator.generateOID() );
        }

        return langDAO.insert( info ) > 0 ? info : null;
    }

    /**
    * 다국어 정보를 삭제합니다.
    *
    * @param oid
    * @return int
    * @author 최원준
    */
    public int delete( String oid ) {

        if ( StringUtils.isEmpty( oid ) ) {
        		return 0;
        }

        return langDAO.delete( oid );
    }

    /**
    * 다국어 정보를 가져옵니다.
    *
    * @param cnd
    * @return LangInfo
    * @author 최원준
    */
    public LangInfo get( LangCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }

        return langDAO.get( cnd );
    }

    /**
    * 다국어 정보 리스트를 가져옵니다.
    *
    * @param cnd
    * @return List<LagnInfo>
    * @author 최원준
    */
    public List<LangInfo> listAll( LangCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }

        return langDAO.listAll( cnd );
    }

}
