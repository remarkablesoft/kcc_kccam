package com.remarkablesoft.framework.service.doc.doc.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;



/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	doc - docRel
 * 		@프로그램 ID		:	DocRelInfo
 * 		@프로그램 개요		:	DocRel 정보 객체
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
public class DocRelInfo extends Entity {

    private static final long serialVersionUID = 828449469524239282L;

    private String docOid;					// 문서 OID
    private String targetOid;					// 문서 관계정보 OID
    private String targetObject;					// 문서 관계정보 객체
    private LocalDateTime inputDate = LocalDateTime.now();					// 등록일시
    private LocalDateTime lastOpenDate;					// 마지막 열람 일시

}
