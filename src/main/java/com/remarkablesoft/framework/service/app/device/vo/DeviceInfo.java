package com.remarkablesoft.framework.service.app.device.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.org.user.vo.NullUserInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	storage - device
 * 		@프로그램 ID		:	DeviceInfo
 * 		@프로그램 개요 		:	기기관리에서 사용하는 정보
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0  2018. 4. 5.	:	최원준	-   기본객체 생성
 *      1.1  2019. 9. 24.	:	최원준	-	PUSH_TOKEN, PUSH_YN 추가
 *      1.2	 2021. 1. 30.	:	james 	- 	패키지를 APP으로 이동.
 * 		============================================================================
 */
@Getter
@Setter
@Accessors( chain = true )
@ToString
public class DeviceInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = -6722283464009912450L;

		private String oid ;									// OID
		private String deviceModelName ;						// 기기명
		private String serialNum ;								// 시리얼 넘버
		private String imei ;									// IMEI
		private String buildVersion ;							// BUILD_VERSION

		private String osTypeFlag ;								// 안드로이드 A, 아이폰 I - ex) SystemConstract.OS_ANDROID
		private String appVersion ;								// 앱 버전
		private String osVersion ;								// OS 버전
		private String uuid ;									// 안드로이드, IOS 고유 ID
		private String pushToken ;								// Push용 PUSH TOKEN

		private String pushYn ;									// PUSH 수신설정 여부
		private LocalDateTime inputDate = LocalDateTime.now(); 	// 등록일

		// 기기관리 상세 페이지 항목
		private int deviceCnt = 0;								// 등록된 기기 수
		
		
		//  DB에서 사용하지 않는 컬럼
		private String useYn ;									// 기기 사용여부
		private UserInfo user = new NullUserInfo();				// 기기의 사용자 정보
		private String userOid ;								// 기기의 사용자 oid =>  만약 해당 기기를 여러명이 쓸경우 어떻게 할것인가?
		private String userId ;									// 기기의 사용자 id

		public DeviceInfo setUser( UserInfo user ) {
				
				this.user = user;

				if ( user != null ) {
						setUserOid( user.getOid() );
				}
				return this;
		}


		/*
		 * 삭제시 사용 : oid로 체크.
		 */
		@Override
		public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((oid == null) ? 0 : oid.hashCode());
				return result;
		}

		/*
		 * 삭제시 사용 : oid로 체크.
		 */
		@Override
		public boolean equals( Object obj ) {
				if ( this == obj ) {
						return true;
				}
				if ( obj == null ) {
						return false;
				}
				if ( !(obj instanceof DeviceInfo) ) {
						return false;
				}
				DeviceInfo other = (DeviceInfo) obj;
				if ( oid == null ) {
						if ( other.oid != null ) {
								return false;
						}
				}
				else if ( !oid.equals( other.oid ) ) {
						return false;
				}
				return true;
		}

		/**
		 * 자신의 타입을 반환
		 *
		 * @return
		 */
		public static String getObjectType() {
				return SystemConstants.OBJECT_FW_TYPE_DEVICE.getKey();
		}

}
