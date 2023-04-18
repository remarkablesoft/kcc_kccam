package com.remarkablesoft.framework.service.board.popup.model.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.board.popup.model.PopupService;
import com.remarkablesoft.framework.service.board.popup.vo.PopupCnd;
import com.remarkablesoft.framework.service.board.popup.vo.PopupInfo;



/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board - popup
 * 		@프로그램 ID		:	PopupServiceImpl
 * 		@프로그램 개요		:	Popup Service Impl
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 03. 05. : 이균환 - 신규생성
 *      ============================================================================
 */
@Service
public class PopupServiceImpl implements PopupService{

    @Autowired
    protected PopupBLO popupBLO;

    @Override
    public PopupInfo insertOrUpdate( PopupInfo info ) {

    	return popupBLO.insertOrUpdate( info );
    }

    @Override
    public PopupInfo insert( PopupInfo info ) {

        return popupBLO.insert( info );
    }

    @Override
    public PopupInfo update( PopupInfo info ) {

        return popupBLO.update( info );
    }

    @Override
    public int delete( String oid ) {

        return popupBLO.delete( oid );
    }

    @Override
    public PopupInfo get( PopupCnd cnd ) {

        return popupBLO.get( cnd );
    }

    @Override
    public PageList<PopupInfo> list( PopupCnd cnd ) {

        return popupBLO.list( cnd );
    }

    @Override
    public List<PopupInfo> listAll( PopupCnd cnd ) {

        return popupBLO.listAll( cnd );
    }

    @Override
    public PopupInfo updateUseYn( PopupInfo info ) {

    	return popupBLO.updateUseYn( info );
    }

    @Override
    public PopupInfo getWithPrevAndNext( PopupCnd cnd ) {

    	return popupBLO.getWithPrevAndNext( cnd );
    }

    @Override
    public List<PopupInfo> operationList( PopupCnd cnd ) {

        return popupBLO.operationList( cnd );
    }
    
    @Override
    public int updateNotUseStatusTypeFlagBatch( LocalDate now ) {
        
        return popupBLO.updateNotUseStatusTypeFlagBatch( now );
    }

}
