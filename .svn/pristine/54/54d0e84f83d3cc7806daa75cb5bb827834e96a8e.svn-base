package com.remarkablesoft.config;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.remarkablesoft.config.env.OracleConfig;
import com.remarkablesoft.framework.util.ApplicationPropertiesUtils;
import com.remarkablesoft.framework.util.localdate.LocalDateDeSerializer;
import com.remarkablesoft.framework.util.localdate.LocalDateSerializer;
import com.remarkablesoft.framework.util.localdate.LocalDateTimeDeSerializer;
import com.remarkablesoft.framework.util.localdate.LocalDateTimeSerializer;

/**
 * 애플리케이션 설정.
 *
 * @author james
 * @since 2015-08-08
 */
@ImportResource( "classpath:/factory-info.xml" )
@EnableAspectJAutoProxy( proxyTargetClass = true )
@EnableAsync
@Import( { OracleConfig.class, BeanConfig.class, DBConfigTest.class } )
@ComponentScan( basePackages = { "com.remarkablesoft", }, useDefaultFilters = true )
public class AppConfigTest  {

		static {

			    Properties prop = new Properties();
			    try {
			    		prop.load(AppConfigTest.class.getClassLoader().getResourceAsStream("application-test.properties"));
						ApplicationPropertiesUtils.setProperties( prop );

				}
				catch ( IOException e ) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
		}


	public static ObjectMapper getObjectMapper() {

			// client에서 넘어오는 json타입의 날짜타입을 맞추기 위해
			ObjectMapper mapper =  Jackson2ObjectMapperBuilder.json()
											  .featuresToDisable( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS )
											  .serializerByType( LocalDateTime.class, new LocalDateTimeSerializer() )
											  .serializerByType( LocalDate.class, new LocalDateSerializer() )
											  .deserializerByType( LocalDateTime.class, new LocalDateTimeDeSerializer() )
											  .deserializerByType( LocalDate.class, new LocalDateDeSerializer() )
											  .modules( new JavaTimeModule() )
											  .build();
			return mapper;
	}

}