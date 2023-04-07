package com.remarkablesoft.site.kccam.service.newsroom.vo;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.site.kccam.service.AmConstants;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Accessors( chain = true )
@ToString
@NoArgsConstructor
public class NewsroomInfo extends Entity {
		
		public static final String FILE_TYPE_ICON = "NEWS00IC";		// 뉴스 대표 이미지 아이콘
		private static final long serialVersionUID = -8085398878855491706L;
		
		private String oid;                                         // OID
		private String title;                                       // 제목
		private String newsroomContents;                            // 내용
		private String lang;                                        // 언어
		
		private String customField1;								// 커스텀 필드1
		private String customField2;								// 커스텀 필드2
		private String customField3;								// 커스텀 필드3
		private String customField4;								// 커스텀 필드4
		private String customField5;								// 커스텀 필드5
		
		private LocalDateTime inputDate = LocalDateTime.now();		// 등록일
		private LocalDateTime modDate = null;						// 수정일
		private LocalDateTime delDate = null;						// 삭제일
		
		private List<NewsroomInfo> currentNewsInfo = null;			// 현재글의 대한 정보
		private NewsroomInfo prevNewsInfo = null;					// 이전글 정보
		private NewsroomInfo nextNewsInfo = null;					// 다음글 정보
		
		private int viewCnt;                                        // 조회수
		
		private List<ProductInfo> productList;						// 관련 제품 정보
		private FileInfo iconFile;									// 뉴스 대표 이미지 아이콘
		
		private List<String> orderByList;                           // 정렬 기준 리스트\
		
		public static String getObjectType() { return AmConstants.OBJECT_AM_NEWSROOM.getKey(); }
}
