package com.remarkablesoft.framework.service.mgnt.config.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.exception.BLORuntimeException;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.mgnt.config.vo.EnvConfigInfo;


/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt-config
 * 		@프로그램 ID		:	EnvConfigBLO
 * 		@프로그램 개요 		:	환경설정 BLO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 6. 7.	:	james	-	신규생성
 * 		============================================================================
 */
@BLO
public class EnvConfigBLO {

	@Autowired
	protected EnvConfigDAO envConfigDAO;

	@CacheEvict( value = "envConfig", allEntries = true )
	public int insert( EnvConfigInfo info ) {

		if ( info == null) {
			return 0;
		}

		if ( envConfigDAO.exist( info )) {

				throw new BLORuntimeException( "이미 같은 키가 존재합니다." );
		}

		return envConfigDAO.insert(info);
	}

	@CacheEvict( value = "envConfig", allEntries = true )
	public int update( EnvConfigInfo info ) {
		return envConfigDAO.update(info);
	}

	@CacheEvict( value = "envConfig", allEntries = true )
	public int delete( String envKey ) {
		return envConfigDAO.delete(envKey);
	}

//	@Cacheable( value = "envConfig" , keyGenerator = "cacheKeyGenerator")
	public EnvConfigInfo get( String envKey ) {
		return envConfigDAO.get( envKey);
	}

//	@Cacheable( value = "envConfig" , keyGenerator = "cacheKeyGenerator")
	public List<EnvConfigInfo> listAll( ) {
		return envConfigDAO.listAll();
	}


	/**
	 * Y이면 true, 아니면 false
	 *
	 * @author james
	 * @param envKey
	 * @param defaltVal
	 * @return
	 */
	@Cacheable( value = "envConfig" , keyGenerator = "cacheKeyGenerator")
	public boolean isValue( String envKey , String defaltVal) {

			EnvConfigInfo envConfig = get ( envKey);

			if ( envConfig == null) {
				return SystemConstants.FLAG_YES.equals( defaltVal);
			}

			return SystemConstants.FLAG_YES.equals( envConfig.getEnvValue());
	 }

	
//	@Cacheable( value = "envConfig" , keyGenerator = "cacheKeyGenerator")
	public String getValue( String envKey , String defaltVal) {

			EnvConfigInfo envConfig = get ( envKey);

			if ( envConfig == null) {
				return defaltVal;
			}

			return envConfig.getEnvValue();
	 }
	
//	@Cacheable( value = "envConfig" , keyGenerator = "cacheKeyGenerator")
	public int getValue( String envKey , int defaltVal) {

			EnvConfigInfo envConfig = get ( envKey);

			if ( envConfig == null) {
				return defaltVal;
			}

			return Integer.parseInt( envConfig.getEnvValue());
	 }
	
	
	/**
	 * 자동 배정 환경설정 객체를 전부 가지고 옵니다.
	 * 
	 * @return settingList
	 */
	public List<EnvConfigInfo> listAllAssignTeacherSet() {
			
			List<EnvConfigInfo> envList = listAll();
			
			if ( CollectionUtils.isEmpty( envList ) ) {
					return null;
			}
			
			List<EnvConfigInfo> settingList = envList.stream().filter( env -> env.getEnvKey()
															  .contains( "system.assign.teacher" ) )
															  .collect( Collectors.toList() );
			
			if ( CollectionUtils.isEmpty( settingList ) ) {
					return null;
			}
			
			
			return settingList;
	}
	
	
	/**
	 * 자동 배정 환경설정 객체 리스트를 업데이트합니다.
	 * 
	 * @return settingList
	 */
	public int updateAssignTeacherSetList( List<EnvConfigInfo> list ) {
			
			int nResult = 0;
			
			if ( CollectionUtils.isEmpty( list ) ) {
					return nResult;
			}
			
			for( EnvConfigInfo envConfig : list ) {
					
					nResult += update( envConfig );
			}
			
			return nResult;
	}


}
