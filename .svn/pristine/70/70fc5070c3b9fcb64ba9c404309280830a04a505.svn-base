package com.remarkablesoft.framework.service.app.device.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.app.device.vo.DeviceCnd;
import com.remarkablesoft.framework.service.app.device.vo.DeviceInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	device
 * 		@프로그램 ID		:	DeviceService
 * 		@프로그램 개요 	:	기기관리 서비스 - 사용자의 다운받은 앱정보
 *
 * 		@변경이력
 *      ============================================================================
  *		1.0		2018. 4.  9.	:	choi - 기본 메소드 생성
  *		1.1		2020. 9.  3.	:	james - 메소드명 변경 및 삭제
  *		1.2		2021. 1. 30.	:	james - 패키지를 APP으로 이동.
 * 		============================================================================
 */
public interface DeviceService {

		/**
		 * 등록하거나 수정
		 * 
		 * @author james
		 * @param info
		 * @return
		 */
		public DeviceInfo insertOrUpdate( DeviceInfo info );

		/**
		 * 기기 삭제
		 *
		 * @param cnd
		 * @return
		 */
		public int delete( DeviceCnd cnd );


		/**
		 * 디바디이스 정보만 가져올때
		 *
		 * @param cnd
		 * @return
		 */
		public DeviceInfo get( DeviceCnd cnd );
		
		/**
		 * 디바이스 뷰 테이블에서 디바이스 정보를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public DeviceInfo view( DeviceCnd cnd );
		
		/**
		 * 기기 페이지리스트를 가져옵니다.
		 * 
		 * @param cnd
		 * @return
		 */
		public PageList<DeviceInfo> list( DeviceCnd cnd );

		/**
		 * 기기 리스트를 가져옵니다. 
		 * 
		 * @param cnd
		 * @return
		 */
		public List<DeviceInfo> listAll( DeviceCnd cnd );

}
