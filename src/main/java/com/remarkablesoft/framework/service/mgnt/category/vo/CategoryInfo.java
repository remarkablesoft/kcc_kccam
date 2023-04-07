package com.remarkablesoft.framework.service.mgnt.category.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;
import com.remarkablesoft.framework.service.mgnt.treenode.vo.TreeNodeInfo;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt - category
 * 		@프로그램 ID		:	CategoryInfo
 * 		@프로그램 개요 		:	카테고리 객체. 단독으로 사용하고 board의 부모로도 사용
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	james	-	신규생성
 * 		============================================================================
 */
@Getter
@Setter
@Accessors( chain = true )
@ToString ( callSuper = true )
public class CategoryInfo extends TreeNodeInfo {

		public CategoryInfo() {
				super( TABLE_NAME );
		}


		public static final String CATEGORY_TYPE_GENERAL 			= "FWCA00GL";			// 일반 카테고리
		public static final String CATEGORY_TYPE_MY					= "FWCA00MY";			// MY 카테고리
		public static final String CATEGORY_TYPE_BOARD 				= "FWCA00BD";			// 보드 카테고리
		public static final String CATEGORY_TYPE_SURVEY 			= "FWCA00SU";			// 설문 카테고리
		public static final String CATEGORY_TYPE_SCHEDULE 			= "FWCA00SC";			// 스케줄 카테고리
		public static final String CATEGORY_TYPE_CLASS 				= "FWCALSCL";			// 강좌 카테고리
		public static final String CATEGORY_TYPE_LECTURE 			= "FWCALSLE";			// 강의 카테고리


		/**
		 *
		 */
		private static final long serialVersionUID = -1377249701860783552L;

		public static final String TABLE_NAME = "TB_CATEGORY";

		private String lang = SystemConstants.LANG_KOR.getKey();							// 언어설정
		private String partOid;																// PART OID
		private String categoryType;														// 카테고리 타입
		private String name;																// 이름
		private int orderNo = 0;															// orderNo
		private String descr;																// 설명

		private String inputUser;															// 등록자
		private LocalDateTime inputDate;													// 등록일
		private String modUser;																// 수정자
		private LocalDateTime modDate;														// 수정일
		private String displayYn = SystemConstants.OPEN_Y.getKey();

		private String customField1;														// 커스텀 필드1
		private String customField2;														// 커스텀 필드2
		private String customField3;														// 커스텀 필드3
		private String customField4;														// 커스텀 필드4
		private String customField5;														// 커스텀 필드5

		private FileInfo iconFile = null;													// 카테고리 아이콘

		protected List<CategoryInfo> childCategoryList = new ArrayList<CategoryInfo>();	            // 하위 카테고리 리스트
		protected List<CategoryInfo> langCategoryList = new ArrayList<CategoryInfo>();                 // 카테고리 다국어 리스트
		
		private List<ContentsInfo> langAddContentsList = new ArrayList<>();                 // 다국어 별 추가 컨텐츠 내용
		private String contentsTargetObject;                                                // content의 targetObject(다국어 별 추가 컨텐츠 내용 사용시)

		/**
		 * 자신의 타입을 반환.
		 *
		 * @return
		 */
		public static String getObjectType() {
				return SystemConstants.OBJECT_FW_TYPE_CATEGORY.getKey();
		}


		public void addChildCategory( CategoryInfo categoryInfo ){

			this.childCategoryList.add( categoryInfo );
		}

}
