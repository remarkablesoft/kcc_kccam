package com.remarkablesoft.framework.service.doc.doc.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
 * 		@서브 시스템		:	doc - doc
 * 		@프로그램 ID		:	DocCnd
 * 		@프로그램 개요		:	Doc 검색 정보 객체
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      1.1 2021. 03. 07. : 안병현 - 수정 : DocRel 조회를 위한 문서 docOid, targetOid, targetObject, targetOidList 추가 
 *      ============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class DocCnd extends SearchCnd {

    private static final long serialVersionUID = 2176767894317948019L;

    private String oid;					    // OID
    private String companyOid;				// 회사 OID
    private String systemOid;				// 시스템 OID
    private String categoryOid;				// 카테고리 OID
    private String title;					// 제목
    
    private String docOid;					//문서 OID
    private String targetOid;				//타겟 OID
    private String targetObject;			//타겟 객체명
    
    private String docType;					// 문서타입
    private String docNo;					// 문서번호
    private String descr;					// 설명
    private String currentVersionOid;		// 현재 버전 문서 OID
    private String secretYn;				// 비밀글 여부

    private String pwd;					    // 비밀글 비밀번호
    private String outLinkUrl;				// 외부 문서 다운로드 링크 URL
    private String inputUserInfoList;		// 등록자의 사용자정보
    private String inputUser;				// 등록자
    private String delUser;					// 삭제자
    private LocalDateTime delDate;			// 삭제일
    
    private String searchDateType;          // 검색시 등록일인지 최종 수정일인지
    private String fromDate;                // 검색 시작 일자
    private String toDate;                  // 검색 종료 일자
    
    private String customField1;			// 커스텀 필드1
    private String customField2;			// 커스텀 필드2
    private String customField3;			// 커스텀 필드3
    private String customField4;			// 커스텀 필드4
    private String customField5;			// 커스텀 필드5
    
    private String statusTypeFlag;          // 문서 상태 R 배포, S 중지, E 만료, B 차단
    private String searchText;              //검색어
    private String inputDateFrom;			// 작성일 From
    private String inputDateTo;				// 작성일 To
    
    private boolean isFillFile = false;             // 파일정보 채움 여부
    private boolean isOnlyCurrentVersion = false;   // 현재버전만 채우는지 여부
    private boolean isLikeSearch = false;           // likeSearch 여부
    
    private List<String> oidList = new ArrayList<>();
    private List<String> targetOidList = new ArrayList<>();
    private List<String> docOidList = new ArrayList<>();
    
    public void addOidList( String oid ) {
    	this.oidList.add ( oid ); 
    }
    
    public void addTargetOidList( String targetOid ) {
        this.targetOidList.add( targetOid );
    }
    
    public void addDocOidList( String docOid ) {
        this.docOidList.add( docOid );
    }
    
}

