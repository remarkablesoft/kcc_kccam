package com.remarkablesoft.framework.service.mgnt.apikey.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.SearchCnd;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt - apikey
 * 		@프로그램 ID		:	HistApiKeyCnd
 * 		@프로그램 개요 		:	apikey 발급 이력 검색객체
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
@EqualsAndHashCode( callSuper = true )
public class ApiKeyCnd extends SearchCnd{

		/**
		 *
		 */
		private static final long serialVersionUID = -3877321296645210886L;

		private String oid;									// OID
		private String targetObject;						// 타겟 객체
		private String targetOid;							// 타겟 OID
		private String apiKey;								// API KEY
		private String apiKeyType;							// API KEY 타입

		private String useYn;								// 사용여부
		private LocalDateTime inputDate;					// 등록일

		List<String> targetOidList = new ArrayList<>();		// 타겟 OID 리스트




}
