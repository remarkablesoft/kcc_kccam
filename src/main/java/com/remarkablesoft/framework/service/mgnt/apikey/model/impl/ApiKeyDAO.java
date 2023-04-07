package com.remarkablesoft.framework.service.mgnt.apikey.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.apikey.vo.ApiKeyCnd;
import com.remarkablesoft.framework.service.mgnt.apikey.vo.ApiKeyInfo;


/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	hist - apikey
 * 		@프로그램 ID		:	HistApiKeyDAO
 * 		@프로그램 개요 		:	apikey 발급 이력
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 10. 08.	:	안희홍	-	신규생성
 * 		============================================================================
 */
@DAO
public class ApiKeyDAO extends BaseDAO{


	public int insert( ApiKeyInfo info ) {
		return sql().insert( id( "insert" ), info );
	}

	public int update( ApiKeyInfo info ) {
			return sql().update( id( "update" ), info );
	}

	public int delete( ApiKeyCnd cnd ) {
		return sql().delete( id( "delete" ), cnd );
	}

	public ApiKeyInfo get( ApiKeyCnd cnd ) {
			return sql().selectOne( id( "get" ), cnd );
	}

	public PageList<ApiKeyInfo> list( ApiKeyCnd cnd ) {
			return sql().queryForPageListAndTCount( id( "list" ), cnd );
	}

	public List<ApiKeyInfo> listAll( ApiKeyCnd cnd ) {
			return sql().selectList( id( "listAll" ), cnd );
	}

}
