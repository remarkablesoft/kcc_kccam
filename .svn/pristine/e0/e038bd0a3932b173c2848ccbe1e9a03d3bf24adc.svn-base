package com.remarkablesoft.site.kccam.service.lang.model.impl;

import java.util.List;

import com.remarkablesoft.site.kccam.service.lang.vo.LangCnd;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.lang.vo.LangItemInfo;
import com.remarkablesoft.framework.util.StringUtils;



/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	lang - langItem
 * 		@프로그램 ID		:	LangItemBLO
 * 		@프로그램 개요		:	LangItem BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@BLO
public class LangItemBLO {

    @Autowired
    protected LangItemDAO langItemDAO;

    /**
    * 다국어 아이템 저장
    *
    * @param info
    * @return LangItemInfo
    * @author 최원준
    */
    public LangItemInfo insert( LangItemInfo info ) {

        if ( StringUtils.isEmpty( info.getLangOid() ) || StringUtils.isEmpty( info.getLangKey() ) ) {
        		return null;
        }
        
        if ( exist( info ) ) {
            return update( info );
        }

        return langItemDAO.insert( info ) > 0 ? info : null;
    }

    /**
    * 다국어 아이템 수정
    *
    * @param info
    * @return LangItemInfo
    * @author 최원준
    */
    public LangItemInfo update( LangItemInfo info ) {

        if ( info == null || StringUtils.isEmpty( info.getLangOid() ) || StringUtils.isEmpty( info.getLangKey() ) ) {
        		return null;
        }

        return langItemDAO.update( info ) > 0 ? info : null;
    }

    /**
    * 다국어 아이템 삭제
    *
    * @param cnd
    * @return int
    * @author 최원준
    */
    public int delete( LangCnd cnd ) {

        if ( StringUtils.isEmpty( cnd.getLangOid() ) || StringUtils.isEmpty( cnd.getLangKey() ) ) {
        		return 0;
        }

        return langItemDAO.delete( cnd );
    }

    /**
    * 다국어 아이템 단건 정보를 가져옵니다.
    *
    * @param cnd
    * @return LangItemInfo
    * @author 최원준
    */
    public LangItemInfo get( LangCnd cnd ) {

        if ( cnd == null || StringUtils.isEmpty( cnd.getLangOid() ) ) {
        		return null;
        }

        return langItemDAO.get( cnd );
    }


    public List<LangItemInfo> listAll( LangCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }

        return langItemDAO.listAll( cnd );
    }

    /**
    * 다국어에 동일 정보가 있는지 확인합니다.
    *
    * @param info
    * @return boolean
    * @author 최원준
    */
    public boolean exist( LangItemInfo info ) {

        if ( StringUtils.isEmpty( info.getLangOid() ) || StringUtils.isEmpty( info.getLangKey() ) ) {
            return false;
        }

        return langItemDAO.exist( info );
    }

}
