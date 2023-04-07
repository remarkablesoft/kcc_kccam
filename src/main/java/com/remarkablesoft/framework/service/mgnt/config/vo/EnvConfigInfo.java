package com.remarkablesoft.framework.service.mgnt.config.vo;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt-config
 * 		@프로그램 ID		:	EnvConfigInfo
 * 		@프로그램 개요 		:	환경설정 객체
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
@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class EnvConfigInfo extends Entity {

	 /**
	 *
	 */
	private static final long serialVersionUID = -2875326851510820397L;

	private String envKey ;				// 환경설정 KEY
	private String envValue ;			// 환경설정 VALUE



}
