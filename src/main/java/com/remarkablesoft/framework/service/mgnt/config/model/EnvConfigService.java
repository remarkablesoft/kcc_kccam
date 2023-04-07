package com.remarkablesoft.framework.service.mgnt.config.model;

import java.util.List;

import com.remarkablesoft.framework.service.mgnt.config.vo.EnvConfigInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt-config
 * 		@프로그램 ID		:	EnvConfigService
 * 		@프로그램 개요 		:	환경설정 서비스
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 6. 7.	:	james	-	신규생성
 * 		=============================================================================
 */
public interface EnvConfigService {

	public int insert( EnvConfigInfo info );

	public int update( EnvConfigInfo info ) ;

	public int delete( String envKey );

	public EnvConfigInfo get( String envKey );
	
	public String getValue( String envKey , String defaltVal);
	
	public int getValue( String envKey , int defaltVal);

	public List<EnvConfigInfo> listAll( );

	/**
	 * 자동 배정 환경설정 객체를 전부 가지고 옵니다.
	 * 
	 * @return
	 */
	public List<EnvConfigInfo> listAllAssignTeacherSet();

	/**
	 * 자동 배정 환경설정 객체 리스트를 업데이트합니다.
	 * 
	 * @return
	 */
	public int updateAssignTeacherSetList( List<EnvConfigInfo> list );

}
