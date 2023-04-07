package com.remarkablesoft.site.kccam.service.lang.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;



/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	lang - lang
 * 		@프로그램 ID		:	LangInfo
 * 		@프로그램 개요		:	Lang 정보 객체
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
public class LangInfo extends Entity {

    private static final long serialVersionUID = 1585479856407167981L;

    private String oid;					// OID
    private String targetOid;					// 타겟 OID
    private String targetType;					// 타겟 타입
    private String lang;					// 언어
    private LocalDateTime inputDate = LocalDateTime.now();					// 등록일시

}
