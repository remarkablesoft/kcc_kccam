package com.remarkablesoft.site.kccam.common;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.web.util.WebUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @주시스템		    :	kccam
 * @서브 시스템		:   common
 * @프로그램 ID		:   ListConverter.java
 * @프로그램 개요	    :   상속관계 혹은 유사한 컬럼 구조를 가지고 있는 두 객체 리스트 변환 로직
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-03-17 : 최원준 - 신규생성
 * ============================================================================
 */
public final class ListConverter {
	
	
		/**
		* 매개변수로 전달받은 페이지 리스트를 매개변수로 전달받은 returnArrClass의 Object Class 페이지 리스트로 반환합니다.
		* 동일한 컬럼명을 가진 데이터만 변환됩니다.
		*
		* ex) PageList<UserInfo> userPageList를 PageList<KccAmUserInfo>로 변환할 때
		* => PageList<KccAmUserInfo> list = PageListConverter.convertPageList( userPageList, KccAmUserInfo[].class );
		*
		* @param list
		* @param returnArrClazz
		* @return PageList<A>
		* @author 최원준
		*/
		public static <T, R, A> PageList<A> convertPageList( PageList<T> list, Class<R> returnArrClazz ) {
			
			if ( CollectionUtils.isEmpty( list ) ) {
				return null;
			}
			
			PageList<A> returnList = new PageList<>( convertList( list, returnArrClazz ) );
			returnList.setTotalCount( list.getTotalCount() );
			
			return returnList;
		}
	
		/**
		* 매개변수로 전달받은 리스트를 매개변수로 전달받은 returnArrClass의 Object Class 리스트로 반환합니다.
		* 동일한 컬럼명을 가진 데이터만 변환됩니다.
		*
		* @param list
		* @param returnArrClazz
		* @return List<A>
		* @author 최원준
		*/
		public static <T, R, A> List<A> convertList( List<T> list, Class<R> returnArrClazz ) {
			
			if ( CollectionUtils.isEmpty( list ) ) {
				return null;
			}
			
			return Arrays.asList( getListArr( list, returnArrClazz ) );
		}
		
		/**
		* 기존 리스트를 WebUtils.GSON을 통해 리턴할 타입의 제너릭 배열을 얻습니다
		*
		* @param list
		* @return returnArrClazz
		* @author 최원준
		*/
		private static <T, R, A> A[] getListArr( List<T> list, Class<R> returnArrClazz ) {
			String originListJson = WebUtils.GSON.toJson( list );
			return WebUtils.GSON.fromJson( originListJson, ( Type ) returnArrClazz );
		}
		
}
