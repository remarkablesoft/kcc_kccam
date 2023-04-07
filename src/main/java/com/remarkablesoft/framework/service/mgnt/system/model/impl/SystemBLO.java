package com.remarkablesoft.framework.service.mgnt.system.model.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.exception.BLORuntimeException;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.mgnt.apikey.model.impl.ApiKeyBLO;
import com.remarkablesoft.framework.service.mgnt.apikey.vo.ApiKeyCnd;
import com.remarkablesoft.framework.service.mgnt.system.vo.SystemCnd;
import com.remarkablesoft.framework.service.mgnt.system.vo.SystemDetailInfo;
import com.remarkablesoft.framework.service.mgnt.system.vo.SystemInfo;
import com.remarkablesoft.framework.service.org.company.model.impl.CompanyBLO;
import com.remarkablesoft.framework.service.org.company.vo.CompanyCnd;
import com.remarkablesoft.framework.service.org.company.vo.CompanyInfo;
import com.remarkablesoft.framework.service.org.user.model.impl.UserBLO;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.web.util.AutheUtils;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	mgnt - system
* 		@프로그램 ID		:	SystemBLO
* 		@프로그램 개요 		:	시스템객체 BLO
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 4.	:	james	-	신규생성
* 		1.1  2019. 12. 20.	:	최원준	-	SystemDetailBLO를 사용하도록 로직 수정
* 		============================================================================
*/
@BLO
public class SystemBLO {

		
		@Autowired
		protected SystemDAO systemDAO;
		
		@Autowired
		protected SystemDetailDAO systemDetailDAO;
		
		@Autowired
		protected UserBLO userBLO;
		
		@Autowired
		protected CompanyBLO companyBLO;
		
		@Autowired
		protected ApiKeyBLO apiKeyBLO;

		/**
		 * 시스템 정보를 저장합니다.
		 *
		 * @author 최원준
		 * @param info
		 * @return
		 */
		@CacheEvict( value = "system", allEntries = true )
		public SystemInfo insert( SystemInfo info ) {

				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}

				if ( info.getInputDate() == null ) {
						info.setInputDate( LocalDateTime.now() );
				}
				// 관리자 OID가 없는경우 등록자의 OID가 저장됩니다.
				if( StringUtils.isEmpty( info.getManagerOid() ) ) {
						info.setManagerOid( AutheUtils.getLoginUserOid() );
				}

				// 시스템 정보에 속해있는 세부정보를 저장합니다.
				insertSystemDetail( info );

				// 법인별 순서대로 정렬
				SystemCnd cnd = new SystemCnd();
				cnd.setCompanyOid( info.getCompanyOid() );
				info.setOrderNo( systemDAO.getMaxOrderNo( cnd ) + 1 );

				int result = systemDAO.insert( info );
				return result > 0 ? info : null;
		}



		/**
		 * 시스템 정보를 수정합니다.
		 *
		 * @author 최원준
		 * @param info
		 * @return
		 */
		@CacheEvict( value = "system", allEntries = true )
		public SystemInfo update( SystemInfo info ) {

				if ( info == null ) {
						return null;
				}
				// 관리자 OID가 없는경우 등록자의 OID가 저장됩니다.
				if( StringUtils.isEmpty( info.getManagerOid() ) ) {
						info.setManagerOid( AutheUtils.getLoginUserOid() );
				}

				if ( info.getSystemDetailInfo() != null ) {
						int result = systemDetailDAO.update( info.getSystemDetailInfo() );
						if( result == 0 ) {
								throw new BLORuntimeException( "시스템 상세정보 수정 실패" );
						}
				}

				// API KEY 정보를 저장합니다.
				if( CollectionUtils.isNotEmpty( info.getApiKeyList() ) ) {
						int apiInsertResult = apiKeyBLO.updateList( info.getApiKeyList() );
						if( apiInsertResult == -1 ) {
								throw new BLORuntimeException( "API KEY 정보 수정 실패" );
						}
				}

				int result = systemDAO.update( info );
				return result > 0 ? info : null;
		}

		/**
		 * 시스템 정보를 삭제합니다.
		 *
		 * @author james
		 * @param oid
		 * @return
		 */
		@CacheEvict( value = "system", allEntries = true )
		public int delete( String oid ) {

				// 시스템 상세에 포함된 API KEY 정보를 함께 삭제합니다.
				ApiKeyCnd apiKeyCnd = new ApiKeyCnd();
				apiKeyCnd.setTargetOid( oid );
				apiKeyBLO.delete( apiKeyCnd );
				systemDetailDAO.delete( oid );

				return systemDAO.delete( oid );
		}

		/**
		 * 시스템정보를 저장하거나 수정합니다.
		 *
		 * @author 최원준
		 * @param info
		 * @return
		 */
		public SystemInfo insertOrUpdate( SystemInfo info ) {

				if ( info == null ) {
						return null;
				}

				SystemInfo resultInfo = SystemFactory.getSystemInfo();
				if ( StringUtils.isEmpty( info.getOid() ) ) {
						resultInfo = insert( info );
				}
				else {
						resultInfo = update( info );
				}

				return resultInfo;

		}

		/**
		 * 조건에 맞는 시스템 정보를 가져옵니다.
		 *
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "system" , keyGenerator = "cacheKeyGenerator")
		public SystemInfo get( SystemCnd cnd ) {

				SystemInfo info = systemDAO.get( cnd );

				if ( info == null ) {
					return null;
				}

				if( cnd.isFillDetailInfo() ) {
						info.setSystemDetailInfo( systemDetailDAO.get( cnd ) );
				}

				if( cnd.isFillManagerInfo() ) {
						info.setManagerInfo( userBLO.getUser( info.getManagerOid() ) );
				}

				if( cnd.isFillInputUser() ) {
						info.setInputUserNameVC( userBLO.getUser( info.getInputUser() ).getName() );
				}

				if( cnd.isFillCompanyInfo() ) {
						info.setCompanyInfo( companyBLO.get( info.getCompanyOid() ) );
				}

				if( cnd.isFillApiKey() ) {
						apiKeyBLO.fillSystemApiKey( info );
				}

				return info;
		}

		/**
		 * OID로 시스템 정보를 가져옵니다.
		 *
		 * @author 최원준
		 * @param oid
		 * @return
		 */
		@Cacheable( value = "system" , keyGenerator = "cacheKeyGenerator")
		public SystemInfo get( String oid ) {
				SystemCnd cnd = new SystemCnd();
				cnd.setOid( oid );
				return get( cnd );
		}

		/**
		 * 시스템 페이지 리스트를 가져옵니다.
		 *
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "system" , keyGenerator = "cacheKeyGenerator")
		public PageList<SystemInfo> list( SystemCnd cnd ) {

				PageList<SystemInfo> list = systemDAO.list( cnd );

				if( cnd.isFillDetailInfo() ) {
						fillSystemDetail( list );
				}

				if( cnd.isFillApiKey() ) {
						apiKeyBLO.fillSystemApiKey( list );
				}

				if( cnd.isFillInputUser() ) {
						userBLO.fillInputUsersName( list );
				}

				if( cnd.isFillManagerInfo() ) {
						userBLO.fillManagerInfo( list );
				}

				if( cnd.isFillCompanyInfo() ) {
						fillCompanyInfoList( list );
				}


				return list;
		}


		/**
		 * 시스템 리스트를 가져옵니다.
		 *
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "system" , keyGenerator = "cacheKeyGenerator")
		public List<SystemInfo> listAll( SystemCnd cnd ) {

				List<SystemInfo> list = systemDAO.listAll( cnd );

				if( cnd.isFillDetailInfo() ) {
						fillSystemDetail( list );
				}

				if( cnd.isFillApiKey() ) {
						apiKeyBLO.fillSystemApiKey( list );
				}

				if( cnd.isFillInputUser() ) {
						userBLO.fillInputUsersName( list );
				}

				if( cnd.isFillManagerInfo() ) {
						userBLO.fillManagerInfo( list );
				}

				if( cnd.isFillCompanyInfo() ) {
						fillCompanyInfoList( list );
				}

				return list;
		}

		/**
		 * 시스템 등록/관리 목록에서 최상위로 이동합니다.
		 * @param oid
		 */
		@CacheEvict( value = "system", allEntries = true )
		public void moveTop( SystemCnd cnd ) {

				SystemInfo info = SystemFactory.getSystemInfo();

				info.setOid( cnd.getOid() );
				info.setOrderNo( 1 );

				systemDAO.update( info );

				int orderNo = 2;

				SystemCnd systemCnd = new SystemCnd();
				systemCnd.setCompanyOid( cnd.getCompanyOid() );

				List<SystemInfo> list = listAll( systemCnd );

				for ( SystemInfo systemInfo : list ) {

						if ( !systemInfo.getOid().equals( cnd.getOid() ) ) {

								info = SystemFactory.getSystemInfo();
								info.setOid( systemInfo.getOid() );
								info.setOrderNo( orderNo++ );

								systemDAO.update( info );
						}
				}
		}


		/**
		 * 시스템에 속해있는 다른 세부정보를 저장합니다.
		 *
		 * @author 최원준
		 * @param info
		 */
		protected void insertSystemDetail( SystemInfo info ) {

				// 시스템 상세정보를 저장합니다.
				if( info.getSystemDetailInfo() != null ) {
						SystemDetailInfo detail = info.getSystemDetailInfo();
						detail.setSystemOid( info.getOid() );
						int result = systemDetailDAO.insert( detail );
						if ( result == 0 ) {
								throw new BLORuntimeException( "시스템 상세정보 저장 실패" );
						}
				}

				// API KEY 정보를 저장합니다.
				if( CollectionUtils.isNotEmpty( info.getApiKeyList() ) ) {
						int apiInsertResult = apiKeyBLO.insertList( info.getApiKeyList() );
						if( apiInsertResult == -1 ) {
								throw new BLORuntimeException( "API KEY 정보 저장 실패" );
						}
				}

				// 시스템 - 그룹 objectRel을 저장합니다.
				// 추후 시스템 기능 확대 시 적용(SystemBLOTest에서 테스트)
		}


		/**
		 * 시스템 상세를 채운다.
		 *
		 * @author james
		 * @param systemList
		 */
		protected void fillSystemDetail( List<SystemInfo> systemList ) {

				if ( CollectionUtils.isEmpty( systemList ) ) {
						return;
				}

				List<String> systemOidList = systemList.stream().map( SystemInfo::getOid ).collect( Collectors.toList() );

				SystemCnd cnd = new SystemCnd();
				cnd.setOidList( systemOidList );

				List<SystemDetailInfo> systemDetailList = systemDetailDAO.listAll( cnd );

				// list에서 null 제거
				CollectionUtils.nullRemove( systemList );

				systemList.forEach( system -> systemDetailList.stream()
															  .filter( detail -> system.getOid().equals( detail.getSystemOid() ) )
															  .forEach( detail -> system.setSystemDetailInfo( detail )) );

		}

		
		
		/**
		 * COMPANY 정보를 채워줍니다.
		 *
		 * @param systemList
		 */
		protected void fillCompanyInfoList( List<SystemInfo> systemList ) {

				if ( CollectionUtils.isEmpty( systemList ) ) {
						return;
				}

				List<String> companyOidList = systemList.stream().map( SystemInfo::getCompanyOid ).collect( Collectors.toList() );

				CompanyCnd cnd = new CompanyCnd();
				cnd.setCompanyOidList( companyOidList );

				List<CompanyInfo> companyList = companyBLO.listAll( cnd );

				// list에서 null 제거
				CollectionUtils.nullRemove( systemList );

				systemList.forEach( system -> companyList.stream().filter( company -> system.getCompanyOid().equals( company.getOid() ) ).forEach( company -> system.setCompanyInfo( company ) ) );

		}
		
}
