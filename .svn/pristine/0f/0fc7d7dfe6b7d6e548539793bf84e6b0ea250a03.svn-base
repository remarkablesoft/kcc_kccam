package com.remarkablesoft.site.kccam.service.onetoone.config.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.onetoone.config.vo.OneToOneConfigInfo;
import com.remarkablesoft.site.kccam.service.onetoone.config.vo.OneToOneConfigCnd;



/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	onetoone - config
 * 		@프로그램 ID		:	OneToOneConfigService
 * 		@프로그램 개요		:	OneToOneConfig Service
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

public interface OneToOneConfigService {

    /**
    * 1대1 문의 설정 정보를 저장합니다.
    *
    * @param info
    * @return OneToOneConfigInfo
    * @author 최원준
    */
    OneToOneConfigInfo insert( OneToOneConfigInfo info );

    /**
    * 1대1 문의 설정 정보를 수정합니다.
    *
    * @param info
    * @return OneToOneConfigInfo
    * @author 최원준
    */
    OneToOneConfigInfo update( OneToOneConfigInfo info );


    /**
    * 1대1 문의 설정 정보를 삭제합니다.
    *
    * @param oid
    * @return int
    * @author 최원준
    */
    int delete( String oid );


    /**
    * 1대1 문의 설정 정보를 가져옵니다.
    *
    * @param cnd
    * @return OneToOneConfigInfo
    * @author 최원준
    */
    OneToOneConfigInfo get( OneToOneConfigCnd cnd );


    /**
    * 1대1 문의 설정 정보 리스트를 가져옵니다.
    *
    * @param cnd
    * @return List<OneToOneConfigInfo>
    * @author 최원준
    */
    List<OneToOneConfigInfo> listAll( OneToOneConfigCnd cnd );

    /**
     *  1대1 문의 설정 정보를 신규 저장하거나 수정합니다.
     *
     *  @param  info
     *  @return info
     *  @author 남윤재
     */
    OneToOneConfigInfo insertOrUpdate(OneToOneConfigInfo info);
    
    /**
     * 1대1 문의 설정 정보 사용 유무를 변경합니다.
     *
     * @param info
     * @return int
     * @author 남윤재
     */
    int deleteFlagUpdate( OneToOneConfigInfo info );
}
