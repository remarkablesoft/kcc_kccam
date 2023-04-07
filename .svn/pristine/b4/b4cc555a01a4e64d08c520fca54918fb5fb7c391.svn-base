package com.remarkablesoft.framework.service.mgnt.part.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.SystemConstants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt - part
 * 		@프로그램 ID		:	PartInfo
 * 		@프로그램 개요 		:	시스템 하위 분류. 카테고리의 부모.
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 11. 2	:	james	-	신규생성
 * 		============================================================================
 */
@Getter
@Setter
@Accessors( chain = true)
@ToString
@NoArgsConstructor
public class PartInfo extends Entity {

		

		/**
		 * 
		 */
		private static final long serialVersionUID = -2479595007259319354L;
		
		
		private String oid;													// OID
		private String systemOid;
		private String name;												// 이름
		private String className;											// part 분류명 - 자신이 분류한 분류명으로 구분 ex) 원격연수, 교직원집합연수
	
		private String inputUser;											// 등록자
		private LocalDateTime inputDate = LocalDateTime.now();				// 등록일


		/**
		 * 자신의 타입을 반환.
		 *
		 * @return
		 */
		public static String getObjectType() {
				return SystemConstants.OBJECT_FW_TYPE_PART.getKey();
		}
}
