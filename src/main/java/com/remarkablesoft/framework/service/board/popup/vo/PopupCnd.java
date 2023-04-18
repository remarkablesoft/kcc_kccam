package com.remarkablesoft.framework.service.board.popup.vo;

import java.time.LocalDate;
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
 * 		@서브 시스템		:	board - popup
 * 		@프로그램 ID		:	PopupCnd
 * 		@프로그램 개요		:	Popup 검색 정보 객체
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 03. 05. : 이균환 - 신규생성
 *      ============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class PopupCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = -1107612365246923852L;

		private String oid;							// OID
		private String partOid;						// 파트 OID
		private String name;						// 팝업명
		private LocalDate startDate;				// 팝업 노출 시작일
		private LocalDate endDate;					// 팝업 노출 종료일

		private String width;						// 팝업 넓이
		private String height;						// 팝업 높이
		private String popupContentsTypeFlag;		// 팝업 콘텐츠 타입  ex) 이미지 팝업 - dropzone(I), 본문 팝업 - editor(C)
		private String popupViewTypeFlag;			// 팝업 보기 타입  ex) 리스트 타입(L), 일반 타입(G) - 위치, 사이즈 지정
		private String linkUrl;						// 연결 URL
		private String linkTypeFlag;				// 링크 타입 (새창 띄우기(N), 페이지 이동(P))

		private String useYn;						// Y 사용중, N 사용안함
		private String inputUser;					// 등록자

		private Integer orderNo; 					// 정렬 순서 (숫자)

		private String customField1;				// 커스텀 필드1
		private String customField2;				// 커스텀 필드2
		private String customField3;				// 커스텀 필드3
		private String customField4;				// 커스텀 필드4
		private String customField5;				// 커스텀 필드5

		private LocalDate today;					// 오늘 날짜로 노출 기간 검색
		private LocalDate expirationDate;			// 기간 만료된 팝업 검색
		private List<String> popupViewTypeFlagList;	// 팝업 보기 타입 목록

		private List<String> oidList = new ArrayList<>();

		public PopupCnd addOidList( String oid ) {

				this.oidList.add( oid );
				return this;
		}
}
