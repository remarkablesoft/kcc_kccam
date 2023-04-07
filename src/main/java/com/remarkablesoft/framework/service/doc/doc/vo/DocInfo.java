package com.remarkablesoft.framework.service.doc.doc.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.SystemConstants;



/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		    :	doc - doc
 * 		@프로그램 ID		    :	DocInfo
 * 		@프로그램 개요		:	Doc 정보 객체
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
public class DocInfo extends Entity {

	public static final String FILE_TYPE_GENERAL = "FWDO00GL";					// 일반 첨부파일 리스트
	public static final String FILE_TYPE_ICON = "FWDO00IC";						// 대표 이미지 아이콘

	
    private static final long serialVersionUID = -7311207007550447199L;

    private String oid;					            // OID
    private String companyOid;					    // 회사 OID
    private String systemOid;					    // 시스템 OID
    private String categoryOid;					    // 카테고리 OID
    private String title;					        // 제목

    private String docType; 					    // 문서타입
    private String currentVersionOid;   		    // 현재 버전 문서 OID
    private String secretYn;					    // 비밀글 여부
    private String pwd;					            // 비밀글 비밀번호
    private String inputUserInfoList;			    // 등록자의 사용자정보
    
    private String inputUser;					    // 등록자
    private LocalDateTime inputDate = LocalDateTime.now();					// 등록일
    private String modUser;					        // 수정자
    private LocalDateTime modDate;					// 수정일
    private String delUser;					        // 삭제자
    private LocalDateTime delDate;					// 삭제일
    
    private String customField1;					// 커스텀 필드1
    private String customField2;					// 커스텀 필드2
    private String customField3;					// 커스텀 필드3
    private String customField4;					// 커스텀 필드4
    private String customField5;					// 커스텀 필드5
    
    private boolean isOnlyDocRel = false;           // 문서 등록이 아닌 관계정보만 저장 시
    
    // --------------------- view Column --------------------------
    private String targetOid;                       // 문서 타겟 OID
    private String targetObject;                    // 문서 타겟 객체
    private LocalDateTime lastOpenDate;             // 마지막 열람 일시
    // --------------------- view Column End ----------------------
    
    private DocRelInfo docRelInfo;                  // 문서 관계정보
    private List<DocRelInfo> docRelList;            // 문서 관계정보 리스트
    private DocVersionInfo currentDocVersionInfo;   // 현재 버전정보
    private List<DocVersionInfo> docVersionList = new ArrayList<>(); // 문서 버전정보 리스트

    private String shareEmailAddress;               //문서 공유할 메일주소
    private String shareDocLink;                    //문서 공유 링크
    private long docFileSize = 0;                     //문서 파일 사이즈
    
    public static String getObjectType() {
        return SystemConstants.OBJECT_FW_TYPE_DOC.getKey();
    }
    
}
