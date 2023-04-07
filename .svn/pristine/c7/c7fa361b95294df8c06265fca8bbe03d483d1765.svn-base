package com.remarkablesoft.framework.service.app.device.vo;

import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.SearchCnd;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	WizMdm
 * 		@서브 시스템		:	device
 * 		@프로그램 ID		:	DeviceCnd.java
 * 		@프로그램 개요 	:	기기관리에서 사용하는 검색조건
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 4. 5.	:	remarkable	-	신규생성
 * 		1.1	 2021. 1. 30.	:	james - 패키지를 APP으로 이동.
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class DeviceCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = -6188459702725542550L;

		private String oid ;
		private String osTypeFlag ;												// OS 타입
		private String uuid ;													// 안드로이드, ios 고유 ID
		private String userId ;													// 사용자 ID
		private String userOid ;												// 사용자 OID

		private String searchSelect ;											// 검색 종류
		private String searchText ;												// 검색어
		private String useYn ;													// 기기 사용여부
		private String exceptUserOid ;											// 관계 업데이트에서 자신의 것은 제외하기 휘애

		private String pushToken ;												// 푸시토큰

		private List<String> uuidList = new ArrayList<>();						//	기기 uuid 리스트
		private List<String> groupOidList = new ArrayList<>();					// 부서 OID 리스트
		private List<String> userOidList = new ArrayList<>();

		private String groupOid ;
//		private boolean isUserGroupSearch = false;								// 유저 소속 그룹 검색 여부 . 그룹검색 안되도록 변경  by james
		private boolean isDeviceCntSearch = false;								// 기기수 검색 여부
		private boolean isTableSearch = false;									// 테이블 뷰 검색할지 말지.

		
		
		public void addUuid ( String uuid ) {
				this.uuidList.add( uuid );
		}
		
		public void addUserOid( String userOid ) {
				this.userOidList.add( userOid );
		}
		
		public void addGroupOid( String groupOid ) {
				this.groupOidList.add( groupOid );
		}
		

}
