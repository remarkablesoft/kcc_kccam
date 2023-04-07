package com.remarkablesoft.site.kccam.service.lang.model.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.remarkablesoft.site.kccam.service.lang.model.LangService;
import com.remarkablesoft.site.kccam.service.lang.vo.LangCnd;
import com.remarkablesoft.site.kccam.service.lang.vo.LangInfo;



/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	lang - lang
 * 		@프로그램 ID		:	LangServiceImpl
 * 		@프로그램 개요		:	Lang Service Impl
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@Service
@Transactional
public class LangServiceImpl implements LangService {

    @Autowired
    protected LangBLO langBLO;

    public LangInfo insert( LangInfo info ) {
        return langBLO.insert( info );
    }

    public int delete( String oid ) {

        return langBLO.delete( oid );
    }

    public LangInfo get( LangCnd cnd ) {

        return langBLO.get( cnd );
    }

    public List<LangInfo> listAll( LangCnd cnd ) {

        return langBLO.listAll( cnd );
    }

}
