package com.remarkablesoft.framework.service.app.device.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.app.device.vo.DeviceCnd;
import com.remarkablesoft.framework.service.app.device.vo.DeviceInfo;
import com.remarkablesoft.framework.service.app.device.vo.DeviceRelInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.restapi.FailMessage;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	deivce
 * 		@프로그램 ID		:	DeviceBLO
 * 		@프로그램 개요 	:	기기관리 BLO
 *
 * 		@변경이력
 *      ============================================================================
 *		1.0		2018. 4. 9.		:	choi - 기본 메소드 생성
 *		1.1		2020. 9. 20.	:	james - 기존 accessToken을 이용하지 않고 신규로 생성된 RefreshToken을 사용하도록 변경
 *		1.2		2021. 1. 30.	:	james - 패키지를 APP으로 이동.
 * 		============================================================================
 */
@BLO
public class DeviceBLO {

		@Autowired
		protected DeviceDAO deviceDAO;

		@Autowired
		protected DeviceRelDAO deviceRelDAO;

		public DeviceInfo insertOrUpdate( DeviceInfo info ) {

				//디바이스 정보가 비어있으면 null을 return합니다.
				if ( info == null || StringUtils.isEmpty( info.getUuid() ) ) {
						return null;
				}

				DeviceInfo target = getByUuid( info.getUuid() );

				if ( target == null ) {
						return insert( info );
				}

				return update( info );

		}

		public DeviceInfo insert( DeviceInfo info ) {

				if ( info == null ) {
						return null;
				}

				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}

				// 유저정보 맵핑
				if ( StringUtils.hasText( info.getUserOid() ) ) {
						checkRelUserDevice( info, info.getUserOid() );
				}

				int result = deviceDAO.insert( info );

				return result > 0 ? info : null;

		}

		public DeviceInfo update( DeviceInfo info ) {

				if ( info == null ) {
						return null;
				}

				// 유저정보 맵핑
				if ( StringUtils.hasText( info.getUserOid() ) ) {

						checkRelUserDevice( info, info.getUserOid() );
				}

				deviceDAO.update( info );

				return info;
		}

		/**
		 * 유저_디바이스 관계 등록
		 *
		 * @param info
		 * @param userOid
		 */
		private void checkRelUserDevice( DeviceInfo info, String userOid ) {

				deviceRelDAO.cutOffRel( convertCutoffDeviceCnd( info ) );  //모두 N으로 처리

				DeviceCnd cnd = new DeviceCnd();
				cnd.setUserOid( userOid );
				cnd.setUuid( info.getUuid() );

				//사용중인 DEVICE REL이 없다면
				DeviceRelInfo relInfo = deviceRelDAO.get( cnd );

				if ( relInfo == null ) {
						relInfo = SystemFactory.getDeviceRelInfo();
						relInfo.setUseYn( SystemConstants.USE_Y.getKey() );
						relInfo.setUserOid( userOid );
						relInfo.setUuid( info.getUuid() );

						//	관계 재등록
						deviceRelDAO.insert( relInfo );
				}
				else {

						//연결하기.
						deviceRelDAO.linkRel( cnd );
				}

		}

		private DeviceCnd convertCutoffDeviceCnd( DeviceInfo info ) {

				DeviceCnd cnd = new DeviceCnd();
				cnd.setUuid( info.getUuid() );
				//cnd.setExceptUserOid( info.getUserOid());
				cnd.setUserOid( info.getUserOid() );

				return cnd;
		}

		/**
		 * 디바이스 정보를 뷰에서 가져옵니다( 사용자 정보를 포함하여 가져옵니다. )
		 */
		public DeviceInfo view( DeviceCnd cnd ) {

				DeviceInfo info = deviceDAO.view( cnd );

				if ( info == null ) {
						return null;
				}

				if ( cnd.isDeviceCntSearch() ) {
						DeviceCnd relCnd = new DeviceCnd();
						relCnd.setUserOid( info.getUserOid() );
						info.setDeviceCnt( deviceRelDAO.listCnt( relCnd ) );
				}

				return info;

		}

		/**
		 * 디바이스 정보만 가져옵니다. 
		 * 사용자 쪽에서 해당사용자가 사용하는 디바이스정보를 가져올 때 사용합니다.
		 */
		public DeviceInfo get( DeviceCnd cnd ) {

				return deviceDAO.get( cnd );
		}
		
		/**
		 * PUSH를 발송할 수 있는 사용자의 기기를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param user
		 * @param device
		 * @return
		 */
		public DeviceInfo getPossablePush( DeviceCnd cnd ) {

				DeviceInfo device = get( cnd );

				// OS 따른 PUSH를 보낼수 있도록 로직 수정
				if ( device == null || StringUtils.isEmpty( device.getPushToken()) ) {
						return null;
				}

				return device;
		}
		
		/**
		 * PUSH를 발송할 수 있는 사용자의 기기를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param user
		 * @param device
		 * @return
		 */
		public DeviceInfo getPossiblePush( String userOid ) {
				
				if ( StringUtils.isEmpty( userOid )) {
						return null;
				}

				DeviceCnd deviceCnd = new DeviceCnd();
				deviceCnd.setTableSearch( true );
				deviceCnd.setUserOid( userOid );
				deviceCnd.setUseYn( SystemConstants.FLAG_YES );
				DeviceInfo device = get( deviceCnd );

				// OS 따른 PUSH를 보낼수 있도록 로직 수정
				if ( device == null || StringUtils.isEmpty( device.getPushToken()) ) {
						return null;
				}

				return device;
		}


		/**
		 * 기기 uuid로 기기정보를 가져옵니다.
		 * @param uuid
		 * @return
		 */
		public DeviceInfo getByUuid( String uuid ) {

				if ( StringUtils.isEmpty( uuid ) ) {
						return null;
				}
				DeviceCnd cnd = new DeviceCnd();
				cnd.setUuid( uuid );
				return get( cnd );
		}


		public List<DeviceInfo> listAll( DeviceCnd cnd ) {

				return deviceDAO.listAll( cnd );
		}

		public PageList<DeviceInfo> list( DeviceCnd cnd ) {

				if ( StringUtils.hasText( cnd.getGroupOid() ) ) {
						cnd.addGroupOid( cnd.getGroupOid() );
				}

				PageList<DeviceInfo> list = deviceDAO.list( cnd );

				return list;

		}

		/**
		 * 기기 삭제
		 *
		 *
		 * @param cnd
		 * @return
		 */
		public int delete( DeviceCnd cnd ) {

				//	사용자와 device의 관계 삭제
				deviceRelDAO.delete( cnd );

				//	device정보나 user정보가 없으면 삭제하지 않음
				if ( StringUtils.isEmpty( cnd.getOid() ) && StringUtils.isEmpty( cnd.getUuid() ) && StringUtils.isEmpty( cnd.getUserOid() ) && StringUtils.isEmpty( cnd.getUserId() ) ) {
						return 0;
				}
				// 관계테이블 전체 삭제.
				//cnd.setUserOid( "");

				return deviceDAO.delete( cnd );
		}

		/**
		 * 기기의 관계만 끊기
		 *
		 * @param cnd
		 * @return
		 */
		public int cutOffRel( DeviceCnd cnd ) {

				return deviceRelDAO.cutOffRel( cnd );
		}

		/**
		 * 기기의 관계 삭제
		 *
		 * @param cnd
		 * @return
		 */
		public int deleteRel( DeviceCnd cnd ) {

				return deviceRelDAO.delete( cnd );
		}

		// device에서 그룹을 찾을수 없도록 변경 by james. 2020.08.19
		//		private void fillGroupName( List<DeviceInfo> list ) {
		//
		//				if ( CollectionUtils.isEmpty( list ) ) {
		//						return;
		//				}
		//
		//				GroupCnd groupCnd = new GroupCnd();
		//				groupCnd.setIsUserCntSearch( false );
		//				List<GroupInfo> groupList = groupBLO.listAll( groupCnd );
		//
		//				if ( CollectionUtils.isEmpty( groupList ) ) {
		//						return;
		//				}
		//
		//				for ( DeviceInfo deviceInfo : list ) {
		//						for ( GroupInfo groupInfo : groupList ) {
		//								if ( deviceInfo.getUser().getGroupOid().equals( groupInfo.getOid() ) ) {
		//										UserInfo userInfo = deviceInfo.getUser();
		//										userInfo.setGroup( groupInfo );
		//										break;
		//								}
		//						}
		//				}
		//		}

		/** 기기의 최근 사용자를 가져옵니다. */
		public DeviceInfo getLatestRegister( String uuid ) {

				DeviceCnd cnd = new DeviceCnd();
				cnd.setUuid( uuid );
				cnd.setUseYn( SystemConstants.USE_Y.getKey() );
				return deviceDAO.getLatestRegister( cnd );
		}

		public List<UserInfo> listDeviceCount( DeviceCnd deviceCnd ) {
				return deviceDAO.listDeviceCount( deviceCnd );
		}

		/**
		 * 사용자리스트를 가져올때 해당 사용자마다 사용중인 기기의 수를 채워줍니다.
		 * @param list
		 */
		public void fillDeviceCnt( List<UserInfo> userList, List<String> userOids ) {

				DeviceCnd deviceCnd = new DeviceCnd();
				deviceCnd.setUserOidList( userOids );
				List<UserInfo> deviceCntList = listDeviceCount( deviceCnd );

				if ( CollectionUtils.isEmpty( deviceCntList ) ) {
						return;
				}

				userList.forEach( user -> deviceCntList.stream()
													   .filter( deviceCntInfo -> deviceCntInfo.getOid() != null && StringUtils.hasText( deviceCntInfo.getOid() ) )
													   .filter( deviceCntInfo -> deviceCntInfo.getOid().equals( user.getOid() ) )
													   .forEach( deviceCntInfo -> user.setDeviceCount( deviceCntInfo.getDeviceCount() ) ) );

		}

		/** 해당사용자가 사용중인 기기의 수를 채워줍니다. */
		public void fillDeviceCnt( UserInfo userInfo ) {

				DeviceCnd deviceCnd = new DeviceCnd();
				deviceCnd.addUserOid( userInfo.getOid() );
				List<UserInfo> deviceCntList = listDeviceCount( deviceCnd );
				if ( CollectionUtils.isEmpty( deviceCntList ) ) {
						return;
				}

				userInfo.setDeviceCount( deviceCntList.get( 0 ).getDeviceCount() );

		}

		/** 유저정보 하나를 가져올 때 기기정보를 채워줍니다. */
		public void fillDevice( UserInfo userInfo ) {

				DeviceCnd deviceCnd = new DeviceCnd();
				deviceCnd.addUserOid( userInfo.getOid() );
				List<DeviceInfo> deviceList = listAll( deviceCnd );

				if ( CollectionUtils.isEmpty( deviceList ) ) {
						return;
				}

				for ( DeviceInfo deviceInfo : deviceList ) {
						userInfo.addDeviceInfo( deviceInfo );
				}

		}

		/** 유저리스트를 가져올 때 기기정보를 채워줍니다. */
		public void fillDevice( List<UserInfo> userList, List<String> userOids ) {

				DeviceCnd deviceCnd = new DeviceCnd();
				deviceCnd.setUserOidList( userOids );
				deviceCnd.setUseYn( SystemConstants.USE_Y.getKey() );
				List<DeviceInfo> deviceList = listAll( deviceCnd );

				if ( CollectionUtils.isEmpty( deviceList ) ) {
						return;
				}

				// list에서 null 제거
				CollectionUtils.nullRemove( userList );
				CollectionUtils.nullRemove( deviceList );

				// device테이블이 유저 테이블을 Join하여 유저정보를 가지고있음 추후 deviceInfo 안 UserInfo를 없애며 같이 수정
				userList.forEach( user -> deviceList.stream()
													.filter( device -> device.getUser() != null && StringUtils.hasText( device.getUser()
																															  .getOid() ) )
													.filter( device -> device.getUser().getOid().equals( user.getOid() ) )
													.forEach( device -> user.addDeviceInfo( device ) ) );

		}

		/**
		 * 기기가 PUSH를 보내기 위한 유효한 상태인지 체크합니다.
		 *
		 * @author 최원준
		 * @param deviceInfo
		 * @return
		 */
		public String validationDevice( DeviceInfo deviceInfo ) {

				String msg = "";

				// 해당 deviceInfo가 없다는 것은 앱을 설치하지 않았다는 의미
				if ( deviceInfo == null || StringUtils.isEmpty( deviceInfo.getPushToken() ) ) {
						msg = FailMessage.PUSH_ERROR_APP_INSTALL.getMessage();
				}

				return msg;
		}

		/**
		 * 메세지 발송을 위해 필수적인 정보로 디바이스 객체를 생성합니다.
		 * 
		 * @param osTypeFlag
		 * @param uuid
		 * @param pushToken
		 * @param pushYn
		 * @return
		 */
		public DeviceInfo makeMessageDevice( String osTypeFlag, String uuid, String pushToken, String pushYn ) {

				DeviceInfo device = SystemFactory.getDeviceInfo();

				device.setOsTypeFlag( osTypeFlag );
				device.setUuid( uuid );
				device.setPushToken( pushToken );
				device.setPushYn( pushYn );

				return device;
		}
}
