package com.remarkablesoft.site.kccam.service.datasheet.vo;

import com.remarkablesoft.framework.model.vo.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * @주시스템			:	framework-web
 * @서브 시스템		:	datasheet - datasheetItem
 * @프로그램 ID		:	DatasheetItemInfo
 * @프로그램 개요		:	DatasheetItem 정보 객체
 * @변경이력
 * ============================================================================
 * 1.0 2021. 02. 25. : 최원준 - 신규생성
 * ============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
public class DatasheetItemInfo extends Entity {
	
	private static final long serialVersionUID = -4573369975541794074L;
	
	private String oid;                         // OID
	private String datasheetOid;                // Datasheet OID
	private String name;						// 이름
	private String itemValue;            		// 아이템 값
	private String itemUnit;                    // 아이템 단위
	
	private String itemType;                    // 아이템 타입(라디오, 체크박스 등)
	private String itemGroupCode;               // 아이템 그룹코드
	private String typical;                     // 물성 전형
	private String testMethod;					// 시험방법
	
	private String xLoc;                        // X 좌표
	private String yLoc;                        // Y 좌표
	
}
