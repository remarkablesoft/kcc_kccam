package com.remarkablesoft.framework.service.mgnt.part.vo;

import java.time.LocalDateTime;
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
 * 		@서브 시스템		:	mgnt - part
 * 		@프로그램 ID		:	PartCnd
 * 		@프로그램 개요 		:	Part 검색객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 11. 2	:	james	-	신규생성
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class PartCnd extends SearchCnd {


		/**
		 *
		 */
		private static final long serialVersionUID = -6976620424904355926L;

		private String oid ;								// OID
		private String systemOid;							// SYSTEM OID
		private String name;								// 파트명
		private String className;							// part 분류명 - 자신이 분류한 분류명으로 구분 ex) 원격연수, 교직원집합연수
		private String inputUser;							// 등록자
		private LocalDateTime inputDate;					// 등록일

		// KCC AM 추가 조건
		private List<String> oidList;						// OID List
		
}
