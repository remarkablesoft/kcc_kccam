package com.remarkablesoft.framework.service.mgnt.system.vo;

import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.SearchCnd;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	mgnt - system
* 		@프로그램 ID		:	SystemInfo
* 		@프로그램 개요 	:	시스템 검색 객체
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
@EqualsAndHashCode( callSuper = true )
public class SystemCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = -6230034541245027616L;

		private String oid;										// OID
		private String companyOid;								// 법인 OID
		private String serviceUrl;								// 서비스 URL
		private Integer sendTypeMode;							// 전송 방식 BITSUM방식
		private String useTargetType;							// 사용할 대상

		private String systemOid;								// 시스템 OID
		private String useYn;									// 사용여부
		private String searchQuery;								// 검색어
		private String companyName;								// 회사명 검색
		private String apiKey;									// api키

		private List<String> oidList = new ArrayList<String>();

		private boolean isFillDetailInfo = false;				// 시스템 상세정보 fill 여부
		private boolean isFillManagerInfo = false;				// 관리자 정보 fill 여부
		private boolean isFillInputUser = false;				// 등록자 정보 fill 여부
		private boolean isFillCompanyInfo = false;				// 업체정보 fill 여부
		private boolean isFillApiKey = false;					// API KEY 정보 fill 여부

		public void addOidList( String oid ) {
				if ( this.oidList.contains( oid ) ) {
						return;
				}
				this.oidList.add( oid );
		}


}
