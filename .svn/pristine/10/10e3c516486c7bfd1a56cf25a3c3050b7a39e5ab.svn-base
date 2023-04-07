package com.remarkablesoft.framework.service.app.device.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.app.device.model.DeviceService;
import com.remarkablesoft.framework.service.app.device.vo.DeviceCnd;
import com.remarkablesoft.framework.service.app.device.vo.DeviceInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	device
 * 		@프로그램 ID		:	DeivceServiceImpl
 * 		@프로그램 개요 	:	기기관리 서비스 Impl
 *
 * 		@변경이력
 *      ============================================================================
  *		1.0		2018. 4. 9.	:	choi - 기본 메소드 생성
  *		1.1		2021. 1. 30.	:	james - 패키지를 APP으로 이동.
 * 		============================================================================
 */
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

		@Autowired
		protected DeviceBLO deviceBLO;

		@Override
		public DeviceInfo insertOrUpdate( DeviceInfo info ) {
				
				return deviceBLO.insertOrUpdate( info );
		}

		@Override
		public DeviceInfo get( DeviceCnd cnd ) {
				return deviceBLO.get( cnd );
		}

		@Override
		public DeviceInfo view( DeviceCnd cnd ) {
				return deviceBLO.view( cnd );
		}
		
		@Override
		public PageList<DeviceInfo> list( DeviceCnd cnd ) {
				return deviceBLO.list( cnd );
		}

		@Override
		public List<DeviceInfo> listAll( DeviceCnd cnd ) {

				return deviceBLO.listAll( cnd );
		}

		@Override
		public int delete(DeviceCnd cnd) {

			return deviceBLO.delete ( cnd);
		}


}
