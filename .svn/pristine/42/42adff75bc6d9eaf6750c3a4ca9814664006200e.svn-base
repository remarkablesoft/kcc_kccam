package com.remarkablesoft.site.kccam.service.onetoone.config.vo;

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
 * 		@서브 시스템		:	onetoone - config
 * 		@프로그램 ID		:	OneToOneConfigCnd
 * 		@프로그램 개요		:	OneToOneConfig 검색 정보 객체
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
@EqualsAndHashCode( callSuper = true )
public class OneToOneConfigCnd extends SearchCnd {

    private static final long serialVersionUID = 7038534721994154498L;

    private String oid;					// OID
    private String parentOid;           // PARENTOID
    private String configType;          // 설정 타입
    private String lang;                // 언어
    private String useYn;              // 사용 여부
    
    private List<String> oidList = new ArrayList<>();
    private List<String> parentOidList = new ArrayList<>();
    
    public void addOidList( String oid ) {
    	this.oidList.add ( oid ); 
    }
}
