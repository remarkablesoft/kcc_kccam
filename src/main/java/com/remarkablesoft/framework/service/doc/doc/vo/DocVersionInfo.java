package com.remarkablesoft.framework.service.doc.doc.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	doc - doc
 * 		@프로그램 ID		:	DocInfo
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
public class DocVersionInfo extends Entity {

    private static final long serialVersionUID = -7311207007550447199L;

    private String oid;					                    // OID
    private String docOid;					                // 문서 OID
    private String docNo;					                // 문서 번호
    private String version;					                // 문서버전
    private String statusTypeFlag;			                // 문서 상태 Y 배포, S 중지, E 만료, B 차단

    private String outLinkUrl;                              // 외부 문서 다운로드 링크 URL
    private String descr;                                   // 비고
    private String inputUser;					            // 등록자
    private LocalDateTime inputDate = LocalDateTime.now();	// 등록일
    
    private String modUser;					                // 수정자
    private LocalDateTime modDate;					        // 수정일
    
    private FileInfo docFileInfo;                         	// 문서 파일정보
    private FileInfo iconFileInfo;							// 문서 대표이미지파일
    
}
