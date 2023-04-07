package com.remarkablesoft.framework.service.app.device.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.app.device.vo.DeviceCnd;
import com.remarkablesoft.framework.service.app.device.vo.DeviceInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

/**
 * 		@주시스템			:	framwork-web
 * 		@서브 시스템		:	app - deivce
 * 		@프로그램 ID		:	DeviceDAO
 * 		@프로그램 개요 	:	기기관리 DAO
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0		2018. 4. 9.	:	choi - 기본 메소드 생성
 *      1.1		2021. 1. 30.	:	james - 패키지를 APP으로 이동.
 * 		============================================================================
 */
@DAO
public class DeviceDAO extends BaseDAO {

		public int insert( DeviceInfo info ) {
				return sql().insert( id( "insert" ), info );
		}

		public int update( DeviceInfo info ) {
				return sql().update( id( "update" ), info );
		}

		public int delete( DeviceCnd cnd ) {
				return sql().delete( id( "delete" ), cnd );
		}

		public DeviceInfo view( DeviceCnd cnd ) {
				return sql().selectOne( id( "view" ), cnd );
		}

		/**
		 * 기기정보만 가져올때
		 *
		 * @param cnd
		 * @return
		 */
		public DeviceInfo get( DeviceCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}

		public DeviceInfo getLatestRegister( DeviceCnd cnd ) {
				return sql().selectOne( id( "getLatestRegister" ), cnd );
		}

		public PageList<DeviceInfo> list( DeviceCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "list" ), cnd );
		}

		public List<DeviceInfo> listAll( DeviceCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}

		public List<UserInfo> listDeviceCount( DeviceCnd deviceCnd ) {
				return sql().selectList( id( "listDeviceCount" ), deviceCnd );
		}

}
