package com.remarkablesoft.framework.service.mgnt.system.vo;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.mgnt.apikey.vo.ApiKeyInfo;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;


/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt - system
 * 		@프로그램 ID		:	SystemDetailInfo
 * 		@프로그램 개요 	:	시스템 상세 객체
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
public class SystemDetailInfo extends Entity {


		/**
		 *
		 */
		private static final long serialVersionUID = -645418892108426912L;

		private String systemOid;					// System OID
		private String serviceUrl;					// 서비스 주소
		private String successUrl;					// 접속 성공 URL
		private int sendTypeMode;					// 전송 방식 (친구톡/알림톡/SMS/Push/....)
		
		private int validSencondTime;				// 유효시간 초단위
		private int validCount;						// 로그인 시도 가능 횟수

		private List<ApiKeyInfo> apiKeyList;		// api key 정보 리스트
		
		
		
}
