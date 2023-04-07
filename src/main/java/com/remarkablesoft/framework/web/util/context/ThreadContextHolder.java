package com.remarkablesoft.framework.web.util.context;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.NamedInheritableThreadLocal;

import com.remarkablesoft.framework.web.module.security.SecurityContextHolder;

/**
 * 설명 : Thhread내에서 공유하고 싶은 데이터를 보관하는 컨텍스트 객체.
 *
 * @author james
 * @since 2014. 5. 13.
 *
 */
public final class ThreadContextHolder
{

		/** Thread 정보를 보관하는 ThreadLocal 변수. */
		private static final NamedInheritableThreadLocal<Map<String, Object>> CONTEXT_HOLDER = new NamedInheritableThreadLocal<Map<String, Object>>( SecurityContextHolder.class + "_CONTEXT_HOLDER" ) {

				@Override
				protected Map<String, Object> initialValue() {
						return new HashMap<String, Object>();
				}
		};



		/**
		 * 객체 생성 방지.
		 */
		private ThreadContextHolder() {


		}

		/**
		 * get
		 */
		public static Object get( String key)
		{
				return CONTEXT_HOLDER.get().get( key );
		}

		/**
		 * put
		 */
		public static void put( String key, Object value)
		{
				CONTEXT_HOLDER.get().put( key, value );
		}

		/**
		 * put
		 */
		public static boolean exist( String key)
		{
				return CONTEXT_HOLDER.get().containsKey( key );
		}


}
