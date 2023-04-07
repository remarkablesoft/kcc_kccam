package com.remarkablesoft.site.kccam.service.lang.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.lang.vo.LangInfo;
import com.remarkablesoft.site.kccam.service.lang.vo.LangCnd;



/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	lang - lang
 * 		@프로그램 ID		:	LangService
 * 		@프로그램 개요		:	Lang Service
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

public interface LangService {

    /**
    * 다국어 정보를 저장합니다.
    *
    * @param info
    * @return LangInfo
    * @author 최원준
    */
    public LangInfo insert( LangInfo info );


    /**
    * 다국어 정보를 삭제합니다.
    *
    * @param oid
    * @return int
    * @author 최원준
    */
    public int delete( String oid );

    /**
    * 다국어 정보를 가져옵니다.
    *
    * @param cnd
    * @return LangInfo
    * @author 최원준
    */
    public LangInfo get( LangCnd cnd );

    /**
    *  다국어 리스트를 가져옵니다.
    *
    * @param cnd
    * @return Lang<LangInfo>
    * @author 최원준
    */
    public List<LangInfo> listAll( LangCnd cnd );
}
