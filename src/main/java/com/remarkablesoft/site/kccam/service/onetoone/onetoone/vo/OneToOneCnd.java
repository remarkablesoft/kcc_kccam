package com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo;

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
 * 		@서브 시스템		:	onetoone - onetoone
 * 		@프로그램 ID		:	OneToOneCnd
 * 		@프로그램 개요		:	OneToOne 검색 정보 객체
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
public class OneToOneCnd extends SearchCnd {

    private static final long serialVersionUID = -6428133590817550382L;

    private String oid;                             // OID
    private String title;				            // 제목
    private String configOid;                       // 문의설정 OID
    private String inputUser;					    // 문의자 OID
    private String userEmail;                       // 문의자 이메일
    
    private String createDateFrom;					// 작성일 From
    private String createDateTo;					// 작성일 To
    private String otoOid;                          // 1대1 문의 상세에서 부모 1대1문의 OID
    private String detailType;                      // 1대1 문의 상세 항목 타입
    private String customField1;					// 커스텀 필드1
    
    private String customField2;					// 커스텀 필드2
    private String customField3;					// 커스텀 필드3
    private String customField4;					// 커스텀 필드4
    private String customField5;					// 커스텀 필드5
    
    private boolean isLikeSearch = false;           // 검색 시 like 사용 여부
    private boolean isTotalSearch = false;          // 검색 시 전체 검색 여부
    private boolean isFillDetail = false;           // 추가정보 조회 여부
    
    private List<String> oidList = new ArrayList<>();
    
    public void addOidList( String oid ) {
    	this.oidList.add ( oid ); 
    }
}
