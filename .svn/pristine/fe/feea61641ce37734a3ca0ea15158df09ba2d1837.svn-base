package com.remarkablesoft.framework.service.mgnt.category.vo;

import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.mgnt.treenode.vo.TreeNodeCnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @주시스템 :	framework-web
 * @서브 시스템        :	board-category
 * @프로그램 ID        :	CategoryCnd
 * @프로그램 개요        :	카테고리 객체. 단독으로 사용하고 board의 부모로도 사용
 * @변경이력 ============================================================================
 * 1.0  2019. 12. 11.	:	hong	-	신규생성
 * 1.1  2020. 04. 04.	:	james	-	isExceptDelete은 필요없이 삭제는 원래 나오면 안됨.
 * ============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class CategoryCnd extends TreeNodeCnd {

	public CategoryCnd() {
		super( CategoryInfo.TABLE_NAME );
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 6545577664286894790L;

	private String lang = SystemConstants.LANG_KOR.getKey();			// 언어설정
	private String partOid;                                            	// PART OID
	private String categoryType;                                    	// 카테고리 타입
	private String name;                                            	// 이름

	private int orderNo;                                            	// orderNo
	private String descr;                                           	// 설명
	private String inputUser;                                        	// 등록자
	private Date inputDate;                                             // 등록일

	private String displayYn;                                        	// 게시여부

	private String customField1;                                    	// 커스텀 필드1
	private String customField2;                                    	// 커스텀 필드2
	private String customField3;                                    	// 커스텀 필드3
	private String customField4;                                    	// 커스텀 필드4
	private String customField5;                                    	// 커스텀 필드5

	private List<String> categoryOidList = new ArrayList<>();        	// 찾을 카테고리 OID 리스트

	private boolean isFillIconFile = false;                            	// 카테고리 아이콘 채우기 여부
	private boolean isFillLangList = false;								// 다국어 리스트 채우기 여부
	private boolean isOrderByInputDate = false;							// 등록일 기준 정렬 조건 여부
	private String searchText;                                        	// 검색어
	private boolean isExceptDeleteData = true;                        	// 삭제한 데이터를 제외하고 가져올지 여부
	private boolean isFullPathNameSearch = true;                    	// FullPathName을 검색할지 여부

	private List<String> exceptCategoryOidList = new ArrayList<>();    	// 제외할 카테고리 OID 리스트
	private List<String> categoryTypeList = new ArrayList<>();        	// 카테고리 타입 리스트 KCC AM 추가
	private List<String> partOidList = new ArrayList<>();            	// part OID List KCC AM 추가
	private List<String> parentOidList = new ArrayList<>();				// parentOidList

	/**
	 * 카테고리 추가
	 *
	 * @param categoryOid
	 * @author james
	 */
	public void addCategoryOid( String categoryOid ) {
		this.categoryOidList.add( categoryOid );
	}

	/**
	 * 제외할 카테고리 추가
	 *
	 * @param categoryOid
	 * @author james
	 */
	public void addExceptCategoryOid( String categoryOid ) {
		this.exceptCategoryOidList.add( categoryOid );
	}

	/**
	 * 카테고리 타입 추가
	 *
	 * @param categoryType
	 * @author 최원준
	 */
	public void addCategoryType( String categoryType ) {
		this.categoryTypeList.add( categoryType );
	}

	public void addParentOid( String parentOid ) {
		this.parentOidList.add( parentOid );
	}
}
