package com.remarkablesoft.framework.service.board.board.vo;

import java.util.Date;

import com.remarkablesoft.framework.common.constants.StatusType;
import com.remarkablesoft.framework.model.vo.SearchCnd;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board-board
 * 		@프로그램 ID		:	BoardCnd
 * 		@프로그램 개요 		:	board는 포스팅의 부모로만 사용
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	hong	-	신규생성
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true)
@ToString
@EqualsAndHashCode (callSuper = true)
public class BoardCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = 2726084611123857343L;

		private String oid;									// OID
		private String targetOid; 							// 타겟 객체의 Oid
		private String targetObject; 						// 타겟 객체의 타입
		private String boardTypeFlag;						// 보드 형태(일반, Q&A, 동영상 등등)

		private StatusType statusTypeFlag;					// 사용중 U, 중지 S, 폐쇄 X
		private String name;								// 이름
		private String descr;								// 설명

		private String inputUser;							// 등록자
		private Date inputDate;								// 등록일
		private String ownerUser;							// 소유자, 담당자
		private String modUser;								// 수정자

		private String customField1;						// 커스텀 필드1
		private String customField2;						// 커스텀 필드2
		private String customField3;						// 커스텀 필드3
		private String customField4;						// 커스텀 필드4
		private String customField5;						// 커스텀 필드5

		//BoardPropertyInfo 검색
		private String setKey ;								// 속성 key	

}
