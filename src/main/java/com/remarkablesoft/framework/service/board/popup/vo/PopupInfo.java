package com.remarkablesoft.framework.service.board.popup.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board - popup
 * 		@프로그램 ID		:	PopupInfo
 * 		@프로그램 개요		:	Popup 정보 객체
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 03. 05. : 안희홍 - 신규생성
 *      ============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
public class PopupInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = 3936966447375256142L;

		public static final String POPUP_CONTENTS_TYPE_FLAG_IMAGE = "I";
		public static final String POPUP_CONTENTS_TYPE_FLAG_EDITOR = "E";

		private String oid;													// OID
		private String partOid;												// 파트 OID
		private String name;												// 팝업명
		private LocalDate startDate;										// 팝업 시작일
		private LocalDate endDate;											// 팝업 종료일

		private int width = -1;												// 팝업 넓이 (가로 사이즈)
		private int height = -1;											// 팝업 높이 (세로 사이즈)
		private String popupContentsTypeFlag;								// 팝업 콘텐츠 타입  ex) 이미지 팝업 - dropzone(I), 에디터 팝업 - editor(E)
		private String popupViewTypeFlag;									// 팝업 보기 타입  ex) 리스트 타입(L), 일반 타입(G) - 위치, 사이즈 지정
		private String linkUrl;												// 연결 URL
		private String linkTypeFlag;										// 링크 타입 (새창 띄우기(N), 페이지 이동(P))

		private String useYn;												// Y 사용중, N 사용안함
		private String inputUser;											// 등록자
		private LocalDateTime inputDate = LocalDateTime.now();				// 등록일
		private int positionX = -1;											// 가로 위치
		private int positionY = -1;											// 세로 위치
		private String centerAlignmentYn;									// 가운데 정렬 적용 여부 (Y,N)

		private String customField1;										// 커스텀 필드1
		private String customField2;										// 커스텀 필드2
		private String customField3;										// 커스텀 필드3
		private String customField4;										// 커스텀 필드4
		private String customField5;										// 커스텀 필드5

		private String contents;											// 내용
		private List<FileInfo> fileList = new ArrayList<FileInfo>();		// 첨부파일 리스트

		private PopupInfo prevPopupInfo;									// 이전 팝업
		private PopupInfo nextPopupInfo;									// 이후 팝업
		private Integer orderNo; 											// 정렬 순서 (숫자)

		private HashMap<String, Object> extraInfoMap = new HashMap<>();		// 부가적인 정보가 필요시 - ex) ~VC가 필요할 때 사용


		public PopupInfo addFile( FileInfo info ) {

				this.fileList.add( info );
				return this;
		}

		public PopupInfo setExtraInfoMap( String key, Object value ) {

				this.extraInfoMap.put( key, value );
				return this;
		}


		/**
		 * 자신의 타입을 반환.
		 *
		 * @return
		 */
		public static String getObjectType() {
				return SystemConstants.OBJECT_FW_TYPE_POPUP.getKey();
		}
}



