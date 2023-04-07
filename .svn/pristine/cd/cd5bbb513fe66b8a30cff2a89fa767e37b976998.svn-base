package com.remarkablesoft.framework.web.info;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.web.controller.BaseController;
import net.sf.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 *        @주시스템            :	framework-web
 *        @서브 시스템        :	web
 *        @프로그램 ID        :	CacheInfoController
 *        @프로그램 개요    :	캐시 정보를 반환 및 삭제처리
 *
 *        @변경이력
 *      ============================================================================
 * 		1.0  2021. 01. 14.	:	james	-	접속한 서버 아이피 반환
 * 		============================================================================
 */
@WEBLog
@RestController
@RequestMapping( "/cache" )
public class CacheInfoController extends BaseController {
		
		@Autowired( required = false )
		protected EhCacheCacheManager ehCacheManager;
		
		public HashMap<String, String> printAllCache() {
				
				HashMap<String, String> hmResult = new HashMap<>();
				
				String[] cacheNames = ehCacheManager.getCacheManager().getCacheNames();
				
				for ( String cacheName : cacheNames ) {
						
						List strArr = ehCacheManager.getCacheManager().getCache( cacheName ).getKeys();
						
						for ( Object string : strArr ) {
								hmResult.put( cacheName, string.toString() );
						}
				}
				
				return hmResult;
		}
		
		public HashMap<String, String> printStatCache() {
				
				LinkedHashMap<String, String> hmResult = new LinkedHashMap<>();
				
				String[] cacheNames = ehCacheManager.getCacheManager().getCacheNames();
				
				for ( String cacheName : cacheNames ) {
						
						Cache cache = ehCacheManager.getCacheManager().getCache( cacheName );
						
						//리소스 사용량
						int cacheSize = cache.getSize();
						long cacheLocalHeapSize = cache.getStatistics().getLocalHeapSize();
						long hitCount = cache.getStatistics().cacheHitCount();
						long evitCount = cache.getStatistics().cacheEvictedCount();
						long missCount = cache.getStatistics().cacheMissCount();
						
						hmResult.put( cacheName + "-cacheSize", String.valueOf( cacheSize ) );
						hmResult.put( cacheName + "-cacheLocalHeapSize", String.valueOf( cacheLocalHeapSize ) );
						hmResult.put( cacheName + "-hitCount", String.valueOf( hitCount ) );
						hmResult.put( cacheName + "-evitCount", String.valueOf( evitCount ) );
						hmResult.put( cacheName + "-missCount", String.valueOf( missCount ) );
						hmResult.put( cacheName + "-########", "" );
				}
				
				return hmResult;
		}
		
		@RequestMapping( value = "/getCacheInfo", method = RequestMethod.GET )
		public ResponseEntity<?> getCacheInfo() {
				
				HashMap<String, String> hmResult = printAllCache();
				
				return new ResponseEntity<>( hmResult, HttpStatus.OK );
		}
		
		@RequestMapping( value = "/getCacheStat", method = RequestMethod.GET )
		public ResponseEntity<?> getCacheStat() {
				
				HashMap<String, String> hmResult = printStatCache();
				
				return new ResponseEntity<>( hmResult, HttpStatus.OK );
		}
		
		@RequestMapping( value = "/removeAllCache", method = RequestMethod.GET )
		public ResponseEntity<?> removeAllCache() {
				
				String[] cacheNames = ehCacheManager.getCacheManager().getCacheNames();
				
				String szResult = "";
				for ( String cacheName : cacheNames ) {
						
						List keys = ehCacheManager.getCacheManager().getCache( cacheName ).getKeys();
						
						for ( Object key : keys ) {
								ehCacheManager.getCache( cacheName ).evict( key );
						}
						
						szResult += cacheName + ",";
				}
				
				int nIdx = szResult.lastIndexOf( "," );
				if ( nIdx > -1 ) {
						szResult = szResult.substring( 0, nIdx );
				}
				
				szResult = szResult + "의 캐시전체가 삭제되었습니다.";
				
				return new ResponseEntity<>( szResult, HttpStatus.OK );
		}
		
		@RequestMapping( value = "/removeCache/{cache}", method = RequestMethod.GET )
		public ResponseEntity<?> removeCache( @PathVariable( "cache" ) String cacheName ) {
				
				List keys = ehCacheManager.getCacheManager().getCache( cacheName ).getKeys();
				
				for ( Object key : keys ) {
						ehCacheManager.getCache( cacheName ).evict( key );
				}
				
				String szResult = cacheName + "캐시가 삭제되었습니다.";
				
				return new ResponseEntity<>( szResult, HttpStatus.OK );
		}
		
}
