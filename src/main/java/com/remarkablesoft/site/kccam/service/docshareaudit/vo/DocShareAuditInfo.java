package com.remarkablesoft.site.kccam.service.docshareaudit.vo;

import com.remarkablesoft.framework.model.vo.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @주시스템 :	kccam
 * @서브 시스템        :   docShareAudit
 * @프로그램 ID        :   DocShareAudit.java
 * @프로그램 개요        :   문서 공유/다운 로그 객체
 * @변경이력 ============================================================================
 * 1.0 2021-06-10 : 황지영 - 신규생성
 * ============================================================================
 */
@Getter
@Setter
@Accessors( chain = true )
@ToString
@NoArgsConstructor
public class DocShareAuditInfo extends Entity {
	
		public static final String SHARE_TYPE_SHARE = "FWDS000S";                // 문서 공유하기 : targetOidList = docVersionOid
		public static final String SHARE_TYPE_DOWNLOAD = "FWDS000D";             // 문서 다운로드 : targetOidList = docVersionOid
		public static final String SHARE_TYPE_EXCEL_DATASHEET = "FWDS000E";      // 제품 정보 데이터 시트 엑셀 다운 : targetOidList = productOidList
		private static final long serialVersionUID = 7867876112811360441L;
		
		private String oid;                                                      // OID
		private String targetOidList;                                            // DOC_VERSION OID (공유 문서 OID)
		private String email;                                                    // 공유,다운 이용자 email
		private LocalDateTime inputDate = LocalDateTime.now();                   // 등록일시
		private String shareType;                                                // 공유인지 다운인지 타입
		
		private String accessUrl;                                                // 접근 URL;
		private String userIp;                                                   // 사용자 IP
		
		private Map<String,Object> targetInfoMap;                                // 타겟 정보 맵
		
}
