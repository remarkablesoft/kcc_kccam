package com.remarkablesoft.framework.service.mgnt.apikey.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.mgnt.apikey.vo.ApiKeyCnd;
import com.remarkablesoft.framework.service.mgnt.apikey.vo.ApiKeyInfo;
import com.remarkablesoft.framework.service.mgnt.system.vo.SystemInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.EncryptUtils;
import com.remarkablesoft.framework.util.StringUtils;


/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	link - apikey
 * 		@프로그램 ID		:	ApiKeyBLO
 * 		@프로그램 개요 	:	apikey 로직
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 10. 08.	:	안희홍	-	신규생성
 * 		1.1  2019. 12. 19. :	최원준	-	타입을 가지고 시스템과 메세지 설정과 함께 사용되도록 변경
 * 		============================================================================
 */
@BLO
public class ApiKeyBLO {

		@Autowired
		protected ApiKeyDAO apiKeyDAO;

		/**
		 * api key 저장하기.
		 *
		 * @param info
		 * @return
		 */
		public ApiKeyInfo insert( ApiKeyInfo info ) {
				
				if ( info == null) {
					return null;
				}

				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}

				if( StringUtils.isEmpty( info.getApiKey() ) ) {
						String result = EncryptUtils.encryptHmacSHA256( info.getOid() );
						info.setApiKey( result );
				}

				int result = apiKeyDAO.insert( info );
				return result > 0 ? info : null;
		}


		/**
		 * ApiKey 리스트를 저장합니다.
		 *
		 * @author 최원준
		 * @param list
		 * @return
		 */
		public int insertList( List<ApiKeyInfo> list ) {

				int result = 0;
				if( CollectionUtils.isEmpty( list ) ) {
						return result;
				}

				for( ApiKeyInfo info : list ) {
						result++;
						ApiKeyInfo resultInfo = insert( info );
						if( resultInfo == null ) {
								result = -1;
								break;
						}
				}

				return result;
		}

		/**
		 * api key 수정하기.
		 *
		 * @param info
		 * @return
		 */
		public ApiKeyInfo update( ApiKeyInfo info ) {

				if( StringUtils.isEmpty( info.getOid() ) ) {
						return null;
				}

				int result = apiKeyDAO.update( info );
				return result > 0 ? info : null;
		}

		/**
		 * API KEY 정보 리스트를 업데이트합니다.
		 *
		 * @author 최원준
		 * @param list
		 * @return
		 */
		public int updateList( List<ApiKeyInfo> list ) {

				int result = 0;
				if( CollectionUtils.isEmpty( list ) ) {
						return result;
				}

				for( ApiKeyInfo info : list ) {
						result++;
						ApiKeyInfo resultInfo = update( info );
						if( resultInfo == null ) {
								result = -1;
								break;
						}
				}

				return result;
		}

		/**
		 * 이미 사용중인 API KEY가 있다면 사용여부 N으로 업데이트 하고 새로 발급.
		 *
		 * @param info
		 * @return
		 */
//		public ApiKeyInfo save( ApiKeyInfo info ) {
//
//				ApiKeyCnd cnd = new ApiKeyCnd();
//				cnd.setTargetObject( info.getTargetObject() );
//				cnd.setTargetOid( info.getTargetOid() );
//				cnd.setUseYn( RemarkableConstract.FLAG_YES );
//
//				ApiKeyInfo apiKeyInfo = get( cnd );
//
//				if( apiKeyInfo != null ) {
//						ApiKeyInfo apiKeyHistInfoUpdate = new ApiKeyInfo();
//						apiKeyHistInfoUpdate.setOid( apiKeyInfo.getOid() );
//						apiKeyHistInfoUpdate.setUseYn( RemarkableConstract.FLAG_NO );
//
//						update( apiKeyHistInfoUpdate );
//				}
//
//				return insert( info );
//
//		}

		/**
		 * OID로 api key 삭제하기.
		 *
		 * @param oid
		 * @return
		 */
		public int delete( String oid ) {
				ApiKeyCnd cnd = new ApiKeyCnd();
				cnd.setOid( oid );
				return delete( cnd );
		}

		/**
		 * 타겟 정보로 API KEY 정보를 삭제합니다.
		 *
		 * @author 최원준
		 * @param targetOid
		 * @param targetObject
		 * @return
		 */
		public int deleteByTarget( String targetOid, String targetObject ) {
				ApiKeyCnd cnd = new ApiKeyCnd();
				cnd.setTargetOid( targetOid );
				cnd.setTargetObject( targetObject );
				return delete( cnd );
		}

		/**
		 * 조건에 맞는 API KEY 정보를 삭제합니다.
		 *
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public int delete( ApiKeyCnd cnd ) {
				if( StringUtils.isEmpty( cnd.getOid() ) && StringUtils.isEmpty( cnd.getTargetOid() )
					&& CollectionUtils.isEmpty( cnd.getTargetOidList() ) ) {
						return 0;
				}

				return apiKeyDAO.delete( cnd );
		}

		/**
		 * api key 가져오기.
		 *
		 * @param cnd
		 * @return
		 */
		public ApiKeyInfo get( ApiKeyCnd cnd ) {

				if( cnd.getApiKey() == null || StringUtils.isEmpty( cnd.getApiKey() ) ) {
						return null;
				}

				return apiKeyDAO.get( cnd );
		}

		/**
		 * API KEY 유효성을 체크하고. API KEY를 반환합니다.
		 *
		 * @param cnd
		 * @return
		 */
		public ApiKeyInfo getApiKey( ApiKeyCnd cnd ) {

				ApiKeyInfo info = get (cnd );

				if( info == null ) {
						return null;
				}

				if ( SystemConstants.FLAG_YES.equals( info.getUseYn() ) ) {
						info.setResultVC( ApiKeyInfo.STATE_VALID );
						info.setMsgVC( ApiKeyInfo.VALID_MESSAGE );
				}
				else if ( SystemConstants.FLAG_NO.equals( info.getUseYn() ) ) {
						info.setResultVC( ApiKeyInfo.STATE_INVALID );
						info.setMsgVC( ApiKeyInfo.INVALID_MESSAGE );
				};

				return info;
		}

		/**
		 * api key 페이징 리스트.
		 *
		 * @param cnd
		 * @return
		 */
		public PageList<ApiKeyInfo> list( ApiKeyCnd cnd ) {
				return apiKeyDAO.list( cnd );
		}

		/**
		 * api key 리스트.
		 *
		 * @param cnd
		 * @return
		 */
		public List<ApiKeyInfo> listAll( ApiKeyCnd cnd ) {
				return apiKeyDAO.listAll( cnd );
		}

		/**
		 * 시스템 상세정보의 API KEY 정보를 채워줍니다.
		 *
		 * @author 최원준
		 * @param info
		 */
		public void fillSystemApiKey( SystemInfo systemInfo ) {

				if ( systemInfo == null ) {
						return;
				}

				ApiKeyCnd cnd = new ApiKeyCnd();
				cnd.setTargetOid( systemInfo.getOid() );
				cnd.setTargetObject( SystemInfo.getObjectType() );

				List<ApiKeyInfo> apiKeyList = list( cnd );
				if ( CollectionUtils.isEmpty( apiKeyList ) ) {
						return;
				}

				systemInfo.setApiKeyList( apiKeyList );
		}

		/**
		 * 시스템 상세정보의 API KEY 정보를 채워줍니다.
		 *
		 * @author 최원준
		 * @param info
		 */
		public void fillSystemApiKey( List<SystemInfo> systemList ) {

				if ( CollectionUtils.isEmpty( systemList ) ) {
						return;
				}

				List<String> targetOidList = systemList.stream().map( SystemInfo::getOid ).collect( Collectors.toList() );

				ApiKeyCnd cnd = new ApiKeyCnd();
				cnd.setTargetOidList( targetOidList );
				cnd.setTargetObject( SystemInfo.getObjectType() );

				List<ApiKeyInfo> apiKeyList = list( cnd );
				if ( CollectionUtils.isEmpty( apiKeyList ) ) {
						return;
				}

				for( SystemInfo system : systemList ) {
						List<ApiKeyInfo> detailApiKeyList = new ArrayList<>();

						apiKeyList.stream()
						.filter( apiKey -> apiKey != null )
						.filter( apiKey -> system.getOid().equals( apiKey.getTargetOid() ) )
						.forEach( apiKey -> detailApiKeyList.add( apiKey ) );

						system.setApiKeyList( detailApiKeyList );
				}

		}

}
