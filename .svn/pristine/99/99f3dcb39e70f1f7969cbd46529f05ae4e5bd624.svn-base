package com.remarkablesoft.site.kccam.service.datasheet.vo;

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
 * 		@서브 시스템		:	datasheet - datasheet
 * 		@프로그램 ID		:	DatasheetCnd
 * 		@프로그램 개요		:	Datasheet 검색 정보 객체
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
public class DatasheetCnd extends SearchCnd {

    private static final long serialVersionUID = -6443176664455984274L;

    private String oid;					    // OID
    private String stdDatasheetOid;			// 기준 Datasheet OID
    private String datasheetType;			// Datasheet 타입
    private String title;					// 제목
    private String descr;					// 설명

    private String inputUser;				// 등록자
    private String delUser;					// 삭제자
    private LocalDateTime delDate;			// 삭제일

    private String datasheetOid;            // 데이터시트 OID
    private String targetOid;               // 타겟 OID
    private String targetObject;            // 타겟 객체
    
    private List<String> oidList = new ArrayList<>();
    private List<String> datasheetOidList = new ArrayList<>();
    
    
    public void addOidList( String oid ) {
    	this.oidList.add ( oid ); 
    }
}
