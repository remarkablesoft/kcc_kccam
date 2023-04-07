package com.remarkablesoft.site.kccam.service.product.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;



/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	product - productRel
 * 		@프로그램 ID		:	ProductRelInfo
 * 		@프로그램 개요		:	ProductRel 정보 객체
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
public class ProductRelInfo extends Entity {

    private static final long serialVersionUID = 4171982416682270942L;

    private String productOid;					// 관계정보 주체 OID
    private String targetOid;					// 관계정보 타겟 OID
    private String targetObject;					// 관계정보 타겟 오브젝트
    private LocalDateTime inputDate = LocalDateTime.now();					// 등록일시

    
}
