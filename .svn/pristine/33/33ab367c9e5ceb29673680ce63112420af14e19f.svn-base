package com.remarkablesoft.site.kccam.service.onetoone.onetoone.model.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.model.OneToOneService;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneCnd;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneInfo;



/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	onetoone - onetoone
 * 		@프로그램 ID		:	OneToOneServiceImpl
 * 		@프로그램 개요		:	OneToOne Service Impl
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@Service
@Transactional
public class OneToOneServiceImpl implements OneToOneService {

    @Autowired
    protected OneToOneBLO oneToOneBLO;

    public OneToOneInfo insert( OneToOneInfo info ) {


        return oneToOneBLO.insert( info );
    }

    public OneToOneInfo update( OneToOneInfo info ) {

        return oneToOneBLO.update( info );
    }

    public int delete( String oid ) {

        return oneToOneBLO.delete( oid );
    }

    public OneToOneInfo get( OneToOneCnd cnd ) {

        return oneToOneBLO.get( cnd );
    }

    public PageList<OneToOneInfo> list( OneToOneCnd cnd ) {

        return oneToOneBLO.list( cnd );
    }

    public List<OneToOneInfo> listAll( OneToOneCnd cnd ) {

        return oneToOneBLO.listAll( cnd );
    }
    
    public void sendEmail( OneToOneInfo info ) {
        oneToOneBLO.sendEmail( info );
    }
    
    public void sendEmailToCustomer( OneToOneInfo info ) {
        oneToOneBLO.sendEmailToCustomer( info );
    }
    
}
