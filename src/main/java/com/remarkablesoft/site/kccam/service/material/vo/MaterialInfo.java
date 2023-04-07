package com.remarkablesoft.site.kccam.service.material.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.site.kccam.service.AmConstants;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @주시스템 :	kccam
 * @서브 시스템        :   material
 * @프로그램 ID        :   MaterialInfo.java
 * @프로그램 개요        :   소재 정보 객체
 * @변경이력 ============================================================================
 * 1.0 2021-03-16 : 최원준 - 신규생성
 * 1.1 2021-05-25 : 김웅기 - PartCnd를 상속받지 않도록 변경
 * ============================================================================
 */
@Getter
@Setter
@Accessors( chain = true )
@ToString
@NoArgsConstructor
public class MaterialInfo extends Entity {
	
	private static final long serialVersionUID = -1287499895478254850L;
	
	public static final String MATERIAL_FILE_TYPE_MAIN_IMG = "KAMT00MI";    			// 소재구분 메인 이미지
	public static final int MAIN_CONTENTS_ORDER_NO = 0;             					// 본문내용 orderNo
	
	
	private String oid;																	// OID
	private String systemOid;
	private String lang;																// 다국어
	private String name;																// 소재 구분명
	private String className;															// 소재 구분 부제
	private String descr;																// 소재 구분 설명
	private String inputUser;															// 등록자
	private LocalDateTime inputDate = LocalDateTime.now();								// 등록일
	private String modUser;																// 수정자
	private LocalDateTime modDate = LocalDateTime.now();								// 수정일
	
	private int productCnt = 0;															// 제품 수
	private int productClassificationCnt = 0;											// 제품 구분 수
	private int applicationCnt = 0;														// Application 수
	private int functionCnt = 0;														// function 수
	
	private ContentsInfo mainContents;                              					// 본문내용
	private FileInfo mainImg;                                       					// 소재대표 이미지
	
	private List<ContentsInfo> addContentsList = new ArrayList<>(); 					// 추가내용
	private List<DocInfo> docList = new ArrayList<>();              					// 관련문서, 참고자료 리스트
	private List<ClassificationInfo> productClassificationList = new ArrayList<>();     // 제품 분류정보 리스트
	private List<ClassificationInfo> applicationList = new ArrayList<>();    			// Application 리스트
	private List<ClassificationInfo> functionList = new ArrayList<>();    				// Funtion 리스트
	private List<MaterialInfo> langMaterialList = new ArrayList<>();					// 소재 구분 다국어 리스트
	
	private List<ContentsInfo> langAddContentsList = new ArrayList<>();                 // 다국어 별 추가 컨텐츠 내용
	
	public static String getObjectType() {
		return AmConstants.OBJECT_AM_TYPE_MATERIAL.getKey();
	}
	
	public void addContentsInfo( ContentsInfo contentsInfo ) {
		this.addContentsList.add( contentsInfo );
	}
	
}
