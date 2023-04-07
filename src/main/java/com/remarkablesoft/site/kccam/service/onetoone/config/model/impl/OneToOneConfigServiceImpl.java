package com.remarkablesoft.site.kccam.service.onetoone.config.model.impl;

import java.util.List;

import com.remarkablesoft.site.kccam.service.onetoone.config.model.OneToOneConfigService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.onetoone.config.vo.OneToOneConfigInfo;
import com.remarkablesoft.site.kccam.service.onetoone.config.vo.OneToOneConfigCnd;
import com.remarkablesoft.framework.util.StringUtils;
import org.springframework.transaction.annotation.Transactional;


/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	onetoone - config
 * 		@프로그램 ID		:	OneToOneConfigServiceImpl
 * 		@프로그램 개요		:	OneToOneConfig Service Impl
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@Service
@Transactional
public class OneToOneConfigServiceImpl implements OneToOneConfigService {

    @Autowired
    protected OneToOneConfigBLO oneToOneConfigBLO;

    public OneToOneConfigInfo insert( OneToOneConfigInfo info ) {
        return oneToOneConfigBLO.insert( info );
    }

    public OneToOneConfigInfo update( OneToOneConfigInfo info ) {
        return oneToOneConfigBLO.update( info );
    }

    public int delete( String oid ) {
        return oneToOneConfigBLO.delete( oid );
    }

    public OneToOneConfigInfo get( OneToOneConfigCnd cnd ) {
        return oneToOneConfigBLO.get( cnd );
    }

    public List<OneToOneConfigInfo> listAll( OneToOneConfigCnd cnd ) {
        return oneToOneConfigBLO.listAll( cnd );
    }

    public OneToOneConfigInfo insertOrUpdate(OneToOneConfigInfo info) {
        return oneToOneConfigBLO.insertOrUpdate( info );
    }
    
    @Override
    public int deleteFlagUpdate( OneToOneConfigInfo info ) {
        return oneToOneConfigBLO.deleteFlagUpdate( info );
    }
    
}
