package com.remarkablesoft.framework.service.app.device.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	device
 * 		@프로그램 ID		:	DeviceRelInfo
 * 		@프로그램 개요 		:	유저 디바이스 관계정보 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 4. 25.	:	choi	-	신규생성
 * 		1.1	 2021. 1. 30.	:	james 	- 	패키지를 APP으로 이동.
 * 		============================================================================
 */
@Getter
@Setter
@Accessors( chain = true )
@ToString
public class DeviceRelInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = -2188808885123728478L;

		private String userOid ;										// 유저 OID
		private String uuid ;											// 기기 고유 ID
		private LocalDateTime inputDate = LocalDateTime.now();			// 등록일시
		private String useYn ;											// 기기사용 여부

		private String installYn ;										// 앱설치 여부


}
