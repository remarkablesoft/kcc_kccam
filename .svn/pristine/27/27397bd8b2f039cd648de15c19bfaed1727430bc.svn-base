package com.remarkablesoft.site.kccam.service.datasheet.model.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.datasheet.model.DatasheetService;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetCnd;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetInfo;



/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	datasheet - datasheet
 * 		@프로그램 ID		:	DatasheetServiceImpl
 * 		@프로그램 개요		:	Datasheet Service Impl
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@Service
@Transactional
public class DatasheetServiceImpl implements DatasheetService {

    @Autowired
    protected DatasheetBLO datasheetBLO;

    public DatasheetInfo insert( DatasheetInfo info ) {
        return datasheetBLO.insert( info );
    }

    public DatasheetInfo update( DatasheetInfo info ) {
        return datasheetBLO.update( info );
    }

    public int delete( String oid ) {
        return datasheetBLO.delete( oid );
    }

    public DatasheetInfo get( DatasheetCnd cnd ) {
        return datasheetBLO.get( cnd );
    }

    public PageList<DatasheetInfo> list( DatasheetCnd cnd ) {
        return datasheetBLO.list( cnd );
    }

    public List<DatasheetInfo> listAll( DatasheetCnd cnd ) {
        return datasheetBLO.listAll( cnd );
    }


}
