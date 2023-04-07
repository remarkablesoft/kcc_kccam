package com.remarkablesoft.site.kccam.service.lang.vo;

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
 * 		@서브 시스템		:	lang - lang
 * 		@프로그램 ID		:	LangCnd
 * 		@프로그램 개요		:	Lang 검색 정보 객체
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
public class LangCnd extends SearchCnd {

    private static final long serialVersionUID = -364798857971866337L;

    private String oid;					// OID
    private String targetOid;					// 타겟 OID
    private String targetType;					// 타겟 타입
    private String lang;					// 언어

    private String langOid;             // lang OID
    private String langKey;             // lang item key
    private String langVal;             // lang item value

    private List<String> oidList = new ArrayList<>();
    public void addOidList( String oid ) {
    	this.oidList.add ( oid ); 
    }

    private List<String> targetOidList = new ArrayList<>();
    public void addTargetOidList( String targetOid ) {
    	this.targetOidList.add ( targetOid ); 
    }
}
