package com.remarkablesoft.framework.service.board.popup.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;

import com.remarkablesoft.framework.service.board.popup.vo.PopupInfo;
import com.remarkablesoft.framework.service.board.posting.vo.PostingCnd;
import com.remarkablesoft.framework.service.board.posting.vo.PostingInfo;
import com.remarkablesoft.framework.service.board.popup.vo.PopupCnd;



/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board - popup
 * 		@프로그램 ID		:	PopupDAO
 * 		@프로그램 개요		:	Popup DAO
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 03. 05. : 이균환 - 신규생성
 *      ============================================================================
 */


@DAO
public class PopupDAO extends BaseDAO {

    public int insert( PopupInfo info ) {
        return sql().insert( id( "insert" ),  info );
    }

    public int update( PopupInfo info ) {
        return sql().update( id( "update" ),  info );
    }

    public int delete( String oid ) {
        return sql().delete( id( "delete" ),  oid );
    }

    public PopupInfo get( PopupCnd cnd ) {
        return sql().selectOne( id( "get" ),  cnd );
    }

    public PageList<PopupInfo> list( PopupCnd cnd ) {
        return sql().queryForPageListAndTCount( id( "list" ),  cnd );
    }

    public List<PopupInfo> listAll( PopupCnd cnd ) {
        return sql().selectList( id( "listAll" ),  cnd );
    }

    public boolean exist( String oid  ) {
        Object obj = sql().selectOne( id( "exist" ), oid );
        return convertIntegerToBoolean( obj );
    }

    public int updateUseYn( PopupInfo info ) {
        return sql().update( id( "updateUseYn" ),  info );
    }

	public PopupInfo getPrev( PopupCnd cnd ) {
		return (PopupInfo) sql().selectOne( id( "getPrev" ), cnd );
	}

	public PopupInfo getNext( PopupCnd cnd ) {
			return (PopupInfo) sql().selectOne( id( "getNext" ), cnd );
	}

}
