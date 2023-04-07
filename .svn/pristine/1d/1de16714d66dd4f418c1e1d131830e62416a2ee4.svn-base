package com.remarkablesoft.framework.web.util.context;

/**
 * 설명 : ThreadContextHolder을 사용하기 위한  Util 클래스
 *
 *
 * @author james
 * @since 2019. 12. 18.
 *
 */
public abstract class ContextUtils {

		public static Object get( String key) {

				return ThreadContextHolder.get( key );
		}

		public static void put( String key, Object value) {

				ThreadContextHolder.put( key, value );
		}

		public static boolean exist( String key) {

				return ThreadContextHolder.exist( key );
		}

}
