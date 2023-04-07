package com.remarkablesoft.site.kccam.common;

import com.google.gson.internal.Primitives;
import com.remarkablesoft.framework.web.util.WebUtils;

/**
 * @주시스템 			:	kccam
 * @서브 시스템        	:   common
 * @프로그램 ID        	:   InfoConverter.java
 * @프로그램 개요        	:   상속관계 혹은 유사한 컬럼 구조를 가지고 있는 두 객체 변환 로직
 * @변경이력 :
 * ============================================================================
 * 1.0 2021-03-24 : 최원준 - 신규생성
 * ============================================================================
 */
public final class InfoConverter {
	
	/**
	 * GSON을 통해 상속관계 혹은 유사한 컬럼 구조를 가지고 있는 두 객체를 변환합니다.
	 *
	 * @param info
	 * @param returnInfoClazz
	 * @return T
	 * @author 최원준
	 */
	public static <T> T convertInfo( Object info, Class<T> returnInfoClazz ) {
		
		if ( info == null ) {
			return null;
		}
		
		String infoJson = WebUtils.GSON.toJson( info );
		Object returnObject = WebUtils.GSON.fromJson( infoJson, returnInfoClazz );
		
		return Primitives.wrap( returnInfoClazz ).cast( returnObject );
	}
}
