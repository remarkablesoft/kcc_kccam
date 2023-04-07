package com.remarkablesoft.framework.service.mgnt.apikey.vo;

import com.remarkablesoft.framework.model.vo.Entity;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt - apikey
 * 		@프로그램 ID		:	ApiKeyHistInfo
 * 		@프로그램 개요 		:	apikey 발급 이력
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 10. 08.	:	안희홍	-	신규생성
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class ApiKeyInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = -7767724734963383709L;

		public static final String STATE_VALID = "S";
		public static final String STATE_INVALID = "F";

		public static final String VALID_MESSAGE = "유효한 API KEY 입니다.";
		public static final String INVALID_MESSAGE = "유효하지 않은 API KEY 입니다.";

		private String oid;											// OID
		private String targetObject;								// 타겟 객체
		private String targetOid;									// 타겟 OID
		private String apiKey;										// API KEY
		private String apiKeyType;									// API KEY 타입

		private String sendId;										// 발신자 ID
		private String sendPhone;									// 발신자번호
		private String useYn;										// 사용여부
		private LocalDateTime inputDate = LocalDateTime.now();		// 등록일

		private String msgVC;										// 메세지
		private String resultVC;									// 결과 값


}
