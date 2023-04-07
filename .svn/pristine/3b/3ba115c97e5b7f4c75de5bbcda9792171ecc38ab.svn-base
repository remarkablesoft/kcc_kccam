package com.remarkablesoft.site.kccam.service.newsroom.vo;

import com.remarkablesoft.framework.model.vo.SearchCnd;
import com.remarkablesoft.framework.service.SystemConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Setter
@Getter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class NewsroomCnd extends SearchCnd {
		
		public final static String SEARCH_QUERY_TYPE_ALL = "FWPOSQ00";								// 전체
		public final static String SEARCH_QUERY_TYPE_TITLE = "FWPOSQ01";							// 제목검색
		public final static String SEARCH_QUERY_TYPE_CONTENTS = "FWPOSQ02";							// 내용검색
		public final static String SEARCH_QUERY_TYPE_TITLE_CONTENTS = "FWPOSQ07"; 					// 제목 AND 내용 검색
		public final static String SEARCH_QUERY_TYPE_TITLE_PRODUCT = "FWPOSQ08";					// 관련 제품 이름 검색
		public final static String SEARCH_QUERY_TYPE_TITLE_OR_TITLE_PRODUCT = "FWPOSQ09";			// 제목 or 관련 제품 제목 검색
		
		private String oid;                                         // OID
		private String title;                                       // 제목
		private String newsroomContents;                            // 내용
		private String lang;                                        // 언어
		private String searchQueryType;								// 질의어 검색 타입
		private String searchQuery;									// 질의어
		
		private String customField1;								// 커스텀 필드1
		private String customField2;								// 커스텀 필드2
		private String customField3;								// 커스텀 필드3
		private String customField4;								// 커스텀 필드4
		private String customField5;								// 커스텀 필드5
		
		private LocalDateTime inputDate;		                    // 등록일
		private LocalDateTime modDate;						        // 수정일
		private LocalDateTime delDate;						        // 삭제일
		
		private boolean isFillIconFile = false;                     //대표 이미지 채움 여부
		private boolean isFillProductList = false;                  // 관련 제품 채움 여부
		
		private boolean isAllLang = false;                          // 모든 언어 가져올지 여부
		private boolean isRemoveHtmlTag = false;                    // 컨텐츠의 html태그를 없앨지 여부
}
