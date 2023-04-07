package com.remarkablesoft.framework.service.mgnt.apikey.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.apikey.model.ApiKeyService;
import com.remarkablesoft.framework.service.mgnt.apikey.vo.ApiKeyCnd;
import com.remarkablesoft.framework.service.mgnt.apikey.vo.ApiKeyInfo;


/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	hist - apikey
 * 		@프로그램 ID		:	ApiKeyServiceImpl
 * 		@프로그램 개요 		:	apikey
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 10. 08.	:	안희홍	-	신규생성
 * 		============================================================================
 */
@Service
@Transactional
public class ApiKeyServiceImpl implements ApiKeyService{


	@Autowired
	protected ApiKeyBLO apiKeyBLO;

	public ApiKeyInfo insert( ApiKeyInfo info ) {
		return apiKeyBLO.insert(info);
	}

	public ApiKeyInfo update( ApiKeyInfo info ) {
			return apiKeyBLO.update(info);
	}

	public int delete( String oid ) {
			return apiKeyBLO.delete(oid);
	}

	public ApiKeyInfo get( ApiKeyCnd cnd ) {
			return apiKeyBLO.get(cnd);
	}

	public PageList<ApiKeyInfo> list( ApiKeyCnd cnd ) {
			return apiKeyBLO.list(cnd);
	}

	public List<ApiKeyInfo> listAll( ApiKeyCnd cnd ) {
			return apiKeyBLO.listAll(cnd);
	}

//	public ApiKeyInfo save( ApiKeyInfo info ) {
//			return apiKeyBLO.save( info );
//	}

	public ApiKeyInfo getApiKey( ApiKeyCnd cnd ) {

			return apiKeyBLO.getApiKey( cnd );
	}
}
