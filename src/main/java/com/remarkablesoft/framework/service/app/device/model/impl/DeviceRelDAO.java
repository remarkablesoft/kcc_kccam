package com.remarkablesoft.framework.service.app.device.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.app.device.vo.DeviceCnd;
import com.remarkablesoft.framework.service.app.device.vo.DeviceRelInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	app - device
 * 		@프로그램 ID		:	DeviceRelDAO
 * 		@프로그램 개요 	:	기기 유저정보 관계 DAO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  	2018. 4. 25.	:	choi	-	신규생성
 * 		1.1		2021. 1. 30.	:	james - 패키지를 APP으로 이동.
 * 		============================================================================
 */
@DAO
public class DeviceRelDAO extends BaseDAO {

		public int insert( DeviceRelInfo info ) {
				return sql().insert( id( "insert" ), info );
		}

		public int delete( DeviceCnd cnd ) {
				return sql().delete( id( "delete" ), cnd );
		}

		public DeviceRelInfo get( DeviceCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}

		public List<DeviceRelInfo> list( DeviceCnd cnd ) {
				return sql().selectList( id( "list" ), cnd );
		}

		public int listCnt( DeviceCnd cnd ) {
				return sql().selectOne( id( "list_count" ), cnd );
		}

		/**
		 * 디바이스리스트에서 사용안함으로 수정.
		 *
		 * @param deviceList
		 * @return
		 */
		public int cutOffRel( DeviceCnd cnd ) {

				return sql().update( id( "cutOffRel" ), cnd );
		}

		
		/**
		 * 디바이스리스트에서 사용으로 수정.
		 *
		 * @param deviceList
		 * @return
		 */
		public int linkRel( DeviceCnd cnd ) {

				return sql().update( id( "linkRel" ), cnd );
		}


}
