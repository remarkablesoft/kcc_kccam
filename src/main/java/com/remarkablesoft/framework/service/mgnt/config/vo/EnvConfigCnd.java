package com.remarkablesoft.framework.service.mgnt.config.vo;

import com.remarkablesoft.framework.model.vo.SearchCnd;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt-config
 * 		@프로그램 ID		:	EnvConfigCnd
 * 		@프로그램 개요 		:	환경설정 검색 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 6. 7.	:	james	-	신규생성
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class EnvConfigCnd extends SearchCnd {


	/**
	 *
	 */
	private static final long serialVersionUID = 6058408856951011387L;

	private String envKey ;				// 환경설정 KEY




}
