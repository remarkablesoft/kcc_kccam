package com.remarkablesoft.config;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import com.remarkablesoft.framework.exception.BLOHandlerExceptionResolver;
import com.remarkablesoft.framework.exception.RunTimeHandlerExceptionResolver;
import com.remarkablesoft.framework.web.module.authentication.AuthenticationHandlerExceptionResolver;
import com.remarkablesoft.framework.web.module.authentication.HttpServletAuthenticationManager;
import com.remarkablesoft.framework.web.module.authentication.HttpServletAuthenticationTokenImpl;
import com.remarkablesoft.framework.web.module.authentication.HttpServletSecurityContextHandlerInterceptor;
import com.remarkablesoft.framework.web.module.authentication.annotation.HttpServletAuthentication;

/**
 * 웹 설정
 * Spring boot에서의 설정관련
 *
 * @author james
 * @since 2017-08-02
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

		@Autowired
		ApplicationContext applicationContext;

		@Autowired
		Environment env;

		private static final int BROWSER_CACHE_CONTROL = 3600 * 24 * 30; // 캐시는 기본적으로 한달로 정함

		@Bean
		public HttpServletAuthentication httpServletAuthentication() {

				HttpServletAuthenticationTokenImpl impl = new HttpServletAuthenticationTokenImpl();

				return impl;
		}

		@Bean
		public HttpServletAuthenticationManager authenticationManager() {
				return new HttpServletAuthenticationManager().setAuthentication( httpServletAuthentication() );
		}



		@Override
		public void addInterceptors( InterceptorRegistry registry ) {

				registry.addInterceptor( new HttpServletSecurityContextHandlerInterceptor().setAuthenticationManager( authenticationManager() ) );

				WebContentInterceptor webContentInterceptor = new WebContentInterceptor();

				// 썸네일 보기는 캐시설정을 한달로 처리
				webContentInterceptor.setCacheMappings( new Properties() {

						private static final long serialVersionUID = 1L;
						{
								put( "/thumbnail_view/**", String.valueOf( BROWSER_CACHE_CONTROL ) );
								put( "/thumbnail_image/**", String.valueOf( BROWSER_CACHE_CONTROL ) );
								put( "/resources/**", String.valueOf( BROWSER_CACHE_CONTROL ) );
						}
				} );

				registry.addInterceptor( webContentInterceptor );
		}



}
