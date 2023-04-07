package com.remarkablesoft.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.remarkablesoft.framework.common.CacheKeyGenerator;


/**
 *
 * 캐시설정
 *
 * @author james
 *
 */
@Configuration
@EnableCaching
public class CacheConfig {


		@Bean
	    public EhCacheCacheManager cacheManager() {

	        return new EhCacheCacheManager( ehCacheCacheManager().getObject() );
	    }

	    @Bean
	    public EhCacheManagerFactoryBean ehCacheCacheManager() {

	        EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
    		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
	        cmfb.setShared(true);
	        return cmfb;

	    }

	    @Bean("cacheKeyGenerator")
	    public KeyGenerator keyGenerator() {
	        return new CacheKeyGenerator();
	    }


}