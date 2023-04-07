package com.remarkablesoft.framework.service.mgnt.config.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.service.mgnt.config.model.EnvConfigService;
import com.remarkablesoft.framework.service.mgnt.config.vo.EnvConfigInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt-config
 * 		@프로그램 ID		:	EnvConfigServiceImpl
 * 		@프로그램 개요 		:	환경설정 서비스 구현 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 6. 7.	:	james	-	신규생성
 * 		============================================================================
 */
@Service
@Transactional
public class EnvConfigServiceImpl implements EnvConfigService {

		@Autowired
		protected EnvConfigBLO envConfigBLO;

		@Override
		public int insert( EnvConfigInfo info ) {

				return envConfigBLO.insert( info );
		}

		@Override
		public int update( EnvConfigInfo info ) {

				return envConfigBLO.update( info );
		}

		@Override
		public int delete( String envKey ) {

				return envConfigBLO.delete( envKey );
		}

		@Override
		public EnvConfigInfo get( String envKey ) {

				return envConfigBLO.get( envKey );
		}

		@Override
		public String getValue( String envKey, String defaltVal ) {

				return envConfigBLO.getValue( envKey, defaltVal );
		}

		@Override
		public int getValue( String envKey, int defaltVal ) {

				return envConfigBLO.getValue( envKey, defaltVal );
		}

		@Override
		public List<EnvConfigInfo> listAll() {

				return envConfigBLO.listAll();
		}

		@Override
		public List<EnvConfigInfo> listAllAssignTeacherSet() {
				
				return envConfigBLO.listAllAssignTeacherSet();
		}

		@Override
		public int updateAssignTeacherSetList( List<EnvConfigInfo> list ) {
				
				return envConfigBLO.updateAssignTeacherSetList( list );
		}

}
