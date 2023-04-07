package com.remarkablesoft.config;

import com.remarkablesoft.framework.module.log.APILoggingAspect;
import com.remarkablesoft.framework.module.log.ServiceAPILoggingAspect;
import com.remarkablesoft.framework.module.log.WEBLoggingAspect;
import com.remarkablesoft.framework.web.module.batch.BatchAspect;
import com.remarkablesoft.framework.web.module.notification.MessageTemplateProvider;
import com.remarkablesoft.framework.web.module.notification.message.template.impl.MessageTemplateProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.awt.image.BufferedImage;

/**
 * 
 * 내부적인 Bean 설정.
 * BLO는 로그가 너무 많이 남겨져서 제외
 * 
 * @author james
 *
 */
@Configuration
@EnableAspectJAutoProxy ( proxyTargetClass=true)
public class BeanConfig {

		@Bean
	    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
	        return new BufferedImageHttpMessageConverter();
	    }

		 
		 
		@Bean
		public ServiceAPILoggingAspect ServiceAPILoggingAspect() {
			return new ServiceAPILoggingAspect();
		}

		/*
		 * @Bean
		 * public BusinessAPILoggingAspect BusinessAPILoggingAspect() {
		 * return new BusinessAPILoggingAspect();
		 * }
		 */

		@Bean
		public APILoggingAspect APILoggingAspect() {
			return new APILoggingAspect();
		}

		@Bean
		public WEBLoggingAspect WEBLoggingAspect() {
			return new WEBLoggingAspect();
		}
		
		@Bean
		public MessageTemplateProvider messageTemplateProvider() {
			return new MessageTemplateProviderImpl();
		}
		
		/**
		 * Batch처리를 위해 등록.
		 * 
		**/
		@Bean
		public BatchAspect batchAspect() {
			return new BatchAspect();
		}

}