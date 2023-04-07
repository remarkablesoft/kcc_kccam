package com.remarkablesoft.site.kccam.service.datasheet.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.Entity;

import com.remarkablesoft.site.kccam.service.AmConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;



/**                                                            
 * 	                                                           
 * @주시스템			:	framework-web 	                   
 * @서브 시스템		:	datasheet - datasheet
 * @프로그램 ID		:	DatasheetInfo
 * @프로그램 개요		:	Datasheet 정보 객체
 * 	                                                           
 * @변경이력    		                                       
 * ============================================================================
 * 1.0 2021. 02. 25. : 최원준 - 신규생성
 * ============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
public class DatasheetInfo extends Entity {

    private static final long serialVersionUID = -3301401059828757832L;

    private String oid;					                    // OID
    private String stdDatasheetOid;					        // 기준 Datasheet OID
    private String datasheetType;					        // Datasheet 타입
    private String title;					                // 제목
    private String descr;					                // 설명

    private String inputUser;					            // 등록자
    private LocalDateTime inputDate = LocalDateTime.now();	// 등록일
    private String modUser;					                // 수정자
    private LocalDateTime modDate;					        // 수정일
    private String delUser;					                // 삭제자
    
    private LocalDateTime delDate;					        // 삭제일

	List<DatasheetItemInfo> datasheetItemList = new ArrayList<>();	// 데이터시트 아이템 리스트

	public static String getObjectType() {
		return AmConstants.OBJECT_AM_TYPE_DATASHEET.getKey();
	}	

	public void addDatasheetItem( DatasheetItemInfo datasheetItemInfo ) {
		this.datasheetItemList.add( datasheetItemInfo );
	}
	
}
