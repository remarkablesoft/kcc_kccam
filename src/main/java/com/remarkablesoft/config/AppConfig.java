package com.remarkablesoft.config;

import com.remarkablesoft.config.env.DBDevConfig;
import com.remarkablesoft.config.env.DBLocalConfig;
import com.remarkablesoft.config.env.DBRealConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * 애플리케이션 설정.
 * 
 * @author james
 *
 */
@EnableScheduling
@ImportResource("classpath:/factory-info.xml")
@Import( { DBLocalConfig.class, DBDevConfig.class, DBRealConfig.class} )
public class AppConfig implements ApplicationContextAware {

		@Override
		public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException {
		}

}