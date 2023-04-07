package com.remarkablesoft.framework.service.mgnt.code.vo;

import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.SystemConstants;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt-code
 * 		@프로그램 ID		:	CodeInfo
 * 		@프로그램 개요 		:	공통코드 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 10. 3.	:	james	-	신규생성
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class CodeInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = -881100309972615324L;

		private String oid;										// OID
		private String code;										// 코드
		private String codeName;									// 코드명
		private String codeValue;									// 코드값
		private int codeLevel = 0;									// 코드 레벨

		private String parentCode;									// 부모코드
		private String codeType;									// 코드 타입
		private String useYn = SystemConstants.FLAG_YES;		// 사용 여부
		private String deleteYn = SystemConstants.FLAG_NO;		// 삭제 여부
		private int orderNo = 0;									// 정렬순서

		private String customField1;								// 커스텀 필드1
		private String customField2;								// 커스텀 필드2
		private String customField3;							// 커스텀 필드3
		private String customField4;							// 커스텀 필드4
		private String customField5;							// 커스텀 필드5

		private List<CodeInfo> codeList = new ArrayList<CodeInfo>();			// no db column  추가 및 수정시 사용
		private List<CodeInfo> deleteCodeList = new ArrayList<CodeInfo>();	// no db column  삭제시 사용

	
		/**
		 * 자신의 타입을 반환. 대분류,중분류,소분류 User, ..., ...
		 *
		 * @return
		 */
		public static String getObjectType() {
				return SystemConstants.OBJECT_FW_TYPE_USER.getKey();
		}

}
