package com.remarkablesoft.framework.service.board.board.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.common.constants.StatusType;
import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.SystemConstants;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board-board
 * 		@프로그램 ID		:	BoardInfo
 * 		@프로그램 개요 	:	board는 포스팅의 부모로만 사용
 *                           - 포스팅 카운트, 컨텐츠 사이즈, file 카운트, file Size는 통계에서 구하기로 변경
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	james	-	신규생성
 * 		============================================================================
 */
@Getter
@Setter
@Accessors( chain = true )
@ToString
public class BoardInfo extends Entity {


		public static final int BOARD_MODE_NOTICE						= 0x00000001;	// 공지 사용여부
		public static final int BOARD_MODE_NOTICE_MUST 					= 0x00000002;	// 공지 필독 여부
		public static final int BOARD_MODE_ANONYMOUS 					= 0x00000004;	// 익명 사용여부
		public static final int BOARD_MODE_SECRET 						= 0x00000008;	// 비밀글 사용여부
		public static final int BOARD_MODE_COMMENT 						= 0x00000010;	// 코멘트, 댓글 사용여부
		public static final int BOARD_MODE_COMMENT_REPLY				= 0x00000020;	// 코멘트, 댓글의 답글허용
		
		public static final int BOARD_MODE_REPLY 						= 0x00000040;	// 답글 사용여부
		public static final int BOARD_MODE_REPLY_REPLY					= 0x00000080;	// 답글의 답글허용 여부
		public static final int BOARD_MODE_RECOMMENT					= 0x00000100;	// 추천 사용여부
		public static final int BOARD_MODE_FILE							= 0x00000200;	// 첨부파일 사용여부
		
		public static final int BOARD_MODE_TAG 							= 0x00000400;	// 태그 사용여부
		public static final int BOARD_MODE_LIKE 						= 0x00000800;	// 좋아요 사용여부
		
		
		
		// 일반적인 게시판 모드
		public static final int BOARD_MODE_GENERAL 						= 0xFFFFFFFF;
	
	
		public static final String BOARD_TYPE_FLAG_GENERAL = "G";                // 일반 게시판
		public static final String BOARD_TYPE_FLAG_NOTICE 				= "N";				// 공지 게시판
		public static final String BOARD_TYPE_FLAG_MOVIE 				= "M";				// 동영상 게시판 - 멀티미디어
		public static final String BOARD_TYPE_FLAG_IMAGE 				= "I";				// 이미지 게시판
		public static final String BOARD_TYPE_FLAG_QUESTION 			= "Q";				// Q&A 게시판 - Q&A, 상담, 채택하는 게시판
		public static final String BOARD_TYPE_FLAG_DATA 				= "D";				// 자료실 게시판
		
		
		
		public static final String VIEW_TYPE_FLAG_LIST 					= "L";				// 리스트 - 일반적인 리스트 형태
		public static final String VIEW_TYPE_FLAG_IMAGE 				= "I";				// 이미지 - 썸네일만 보이는 형태 
		public static final String VIEW_TYPE_FLAG_CARD 					= "C";				// 카드형태 - 이미지와 내용이 같이 나오는 형태
		
		
		/**
		 *
		 */
		private static final long serialVersionUID = -1377249701860783552L;

		private String oid;																	// OID
		private String targetOid; 															// 타겟 객체의 Oid
		private String targetObject; 														// 타겟 객체의 타입
		private String boardTypeFlag;														// 보드 형태(일반, Q&A, 동영상 등등)
	
		private String viewType;															// 보기모드를 순서대로 저장 EX) L,I,C or C,I
		private StatusType statusTypeFlag;													// 사용중 U, 중지 S, 폐쇄 X
		
		private String name;																// 이름
		private String descr;																// 설명
		private String ownerUser;															// 소유자, 담당자
		private LocalDateTime ownerDate;													// 소유일시

		private String inputUser;															// 등록자
		private LocalDateTime inputDate;													// 등록일
		private String modUser;																// 수정자
		private LocalDateTime modDate = null;												// 수정일
		private String delUser;																// 삭제자
		private LocalDateTime delDate = null;												// 삭제일		

		private String customField1;														// 커스텀 필드1
		private String customField2;														// 커스텀 필드2
		private String customField3;														// 커스텀 필드3
		private String customField4;														// 커스텀 필드4
		private String customField5;														// 커스텀 필드5
		
		public static String getObjectType() {
				return SystemConstants.OBJECT_FW_TYPE_BOARD.getKey();
		}
}
