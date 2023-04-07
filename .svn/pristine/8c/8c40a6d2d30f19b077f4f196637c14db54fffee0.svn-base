package com.remarkablesoft.framework.service.mgnt.code.vo;

import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.SearchCnd;
import com.remarkablesoft.framework.service.SystemConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	mgnt-code
* 		@프로그램 ID		:	CodeCnd
* 		@프로그램 개요 		:	공통코드 검색 객체
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
@EqualsAndHashCode( callSuper = true )
public class CodeCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = -7617441843004611830L;

		private String oid;										// OID
		private String code;									// 코드
		private String codeName;								// 코드 타입
		private String codeValue;								// 코드 값
		private int codeLevel = 0;								// 코드 레벨
		
		private String codeSearch;								// 코드검색용
		private String parentCode;								// 부모코드
		private String codeType;								// 코드 타입
		private String useYn;									// 사용 여부
		private String deleteYn = SystemConstants.FLAG_NO;		// 삭제 여부

		private int orderNo = 0;								// 정렬순서

		private String customField1;							// 커스텀 필드1
		private String customField2;							// 커스텀 필드2
		private String customField3;							// 커스텀 필드3
		private String customField4;							// 커스텀 필드4
		private String customField5;							// 커스텀 필드5

		private List<String> codeList = new ArrayList<String>();
		private List<String> oidList = null;						// 옵션 OID 리스트
		private List<String> itemTypeList = null;					// 아이템 타입 리스트

		public void addCode( String code ) {
				this.codeList.add( code );
		}
		

}
