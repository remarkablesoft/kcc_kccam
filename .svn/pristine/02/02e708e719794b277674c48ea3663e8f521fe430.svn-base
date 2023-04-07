package com.remarkablesoft.framework.service.app.device.model.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.service.app.device.vo.DeviceCnd;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	app - deivce
 * 		@프로그램 ID		:	DeviceBLO
 * 		@프로그램 개요 	:	기기관리 BLO
 *
 * 		@변경이력
 *      ============================================================================
 *		1.0		2018. 4. 9.		:	choi - 기본 메소드 생성
 *		1.1		2021. 1. 30.	:	james - 패키지를 APP으로 이동.
 * 		============================================================================
 */
@BLO
public class DeviceRelBLO {

		@Autowired
		protected DeviceRelDAO deviceRelDAO;

		/**
		 * 해당 사용자에 얽힌 device rel 삭제
		 * @param userOid
		 * @return
		 */
		public int deleteByUserOid( String userOid ) {

			DeviceCnd deviceCnd = new DeviceCnd();
			deviceCnd.setUserOid( userOid );
			return delete( deviceCnd );
		}

		/**
		 * 기기 - 사용자 rel 삭제
		 * @param deviceCnd
		 * @return
		 */
		public int delete( DeviceCnd deviceCnd ) {

			//	명확한 조건이 있어야 삭제 가능
			if( StringUtils.isEmpty( deviceCnd.getUuid() ) &&
					StringUtils.isEmpty( deviceCnd.getUserOid() ) &&
					CollectionUtils.isEmpty( deviceCnd.getUserOidList() ) ) {

				return 0;
			}

			return deviceRelDAO.delete( deviceCnd );
		}
}
