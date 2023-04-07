package com.remarkablesoft.framework.service.mgnt.system.vo;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.mgnt.apikey.vo.ApiKeyInfo;
import com.remarkablesoft.framework.service.org.company.vo.CompanyInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	link - system
 * 		@프로그램 ID		:	SystemInfo
 * 		@프로그램 개요 	:	시스템객체 - 외부에서 각 회사마다 사용하는 시스템과 내부에서 여러 시스템을 구분하는 용도로 사용
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 4.	:	james	-	신규생성
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class SystemInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = 9195602672797194613L;

		private String oid;
		private String companyOid;															// 회사 oid
		private String name;																// 시스템명
		private String descr;																// 설명
		private int orderNo;																// 정렬순서

		private String useYn;																// 사용여부
		private String managerOid;															// 관리자 OID
		private String inputUser;															// 등록자
		private LocalDateTime inputDate;													// 등록일

		private String inputUserNameVC;														// 등록자 명
		private String companyNameVC;														// 법인 회사 명

		private CompanyInfo companyInfo ;													// 법인 회사 정보
		private UserInfo managerInfo;														// 관리자 정보

		private SystemDetailInfo systemDetailInfo;											// 시스템 상세객체
		private List<ApiKeyInfo> apiKeyList;												// api key 정보 리스트
		private List<String> groupOidList;													// 그룹 OID 리스트

		
		/**
		 * 자신의 타입을 반환.
		 *
		 * @return
		 */
		public static String getObjectType() {
				return SystemConstants.OBJECT_FW_TYPE_SYSTEM.getKey();
		}

}
