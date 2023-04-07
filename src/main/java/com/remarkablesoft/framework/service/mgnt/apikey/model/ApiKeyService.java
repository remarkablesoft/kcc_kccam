package com.remarkablesoft.framework.service.mgnt.apikey.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.apikey.vo.ApiKeyCnd;
import com.remarkablesoft.framework.service.mgnt.apikey.vo.ApiKeyInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	hist - apikey
 * 		@프로그램 ID		:	ApiKeyHistService
 * 		@프로그램 개요 		:	apikey 발급 이력
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 10. 08.	:	안희홍	-	신규생성
 * 		============================================================================
 */
public interface ApiKeyService {

	/**
	 * api key 발급 이력 저장하기.
	 *
	 * @param info
	 * @return
	 */
	public ApiKeyInfo insert( ApiKeyInfo info );

	/**
	 * api key 발급 이력 수정하기.
	 *
	 * @param info
	 * @return
	 */
	public ApiKeyInfo update( ApiKeyInfo info );

	/**
	 * api key 발급 이력 삭제하기.
	 *
	 * @param oid
	 * @return
	 */
	public int delete( String oid );

	/**
	 * api key 발급 이력 가져오기.
	 *
	 * @param cnd
	 * @return
	 */
	public ApiKeyInfo get( ApiKeyCnd cnd );

	/**
	 * api key 발급 이력 페이징 리스트.
	 *
	 * @param cnd
	 * @return
	 */
	public PageList<ApiKeyInfo> list( ApiKeyCnd cnd );

	/**
	 * api key 발급 이력 리스트.
	 *
	 * @param cnd
	 * @return
	 */
	public List<ApiKeyInfo> listAll( ApiKeyCnd cnd );


	/**
	 * 이미 사용중인 API KEY가 있다면 사용여부 N으로 업데이트 하고 새로 발급.
	 *
	 * @param info
	 * @return
	 */
//	public ApiKeyInfo save( ApiKeyInfo info );

	/**
	 * API KEY 유효성을 체크하고. APIKEY를 반환합니다.
	 *
	 * @param cnd
	 * @return
	 */
	public ApiKeyInfo getApiKey( ApiKeyCnd cnd );
}
