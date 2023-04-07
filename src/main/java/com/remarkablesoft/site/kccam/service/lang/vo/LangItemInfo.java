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
 * 		@서브 시스템		:	lang - langItem
 * 		@프로그램 ID		:	LangItemInfo
 * 		@프로그램 개요		:	LangItem 정보 객체
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
public class LangItemInfo extends Entity {

    private static final long serialVersionUID = 1986024483033913592L;

    private String langOid;					// 다국어 테이블 OID
    private String langKey;					// 다국어 키
    private String langVal;					// 다국어 값
}
