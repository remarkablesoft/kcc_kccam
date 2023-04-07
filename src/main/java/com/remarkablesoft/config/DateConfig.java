package com.remarkablesoft.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.remarkablesoft.framework.util.localdate.LocalDateDeSerializer;
import com.remarkablesoft.framework.util.localdate.LocalDateSerializer;
import com.remarkablesoft.framework.util.localdate.LocalDateTimeDeSerializer;
import com.remarkablesoft.framework.util.localdate.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 
 * localDateTime의 포맷을 맞추기 위해.
 * 
 * @author james
 *
 */
@Configuration
public class DateConfig  implements WebMvcConfigurer {

	    /**
		 * model.addAttribute에서 localDateTime을 format을 맞주기 위해
		 *
		 * localDateTime은 date형태와 달리  객체형태로 보여주기 때문에
		 * 기존 date형태처럼 날짜형태로 나오게 하기 위해
		 * localDateTime객체 => "2019-12-15T18:58:49"
		 *
		 * @author james
		 * @return
		 */
		public ObjectMapper objectMapper() {

				/*
				 * return Jackson2ObjectMapperBuilder.json()
				 * .featuresToDisable( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS )
				 * .modules( new JavaTimeModule() )
				 * .simpleDateFormat( dateTimeFormat )
				 * .serializers( new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)) )
				 * .build();
				 */
				
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
		

		/**
		 * @ResponseBody에서 localDateTime을 format을 맞주기 위해
		 *
		 * @author james
		 * @return
		 */
		@Bean
	    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter(){
	        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	        converter.setObjectMapper( objectMapper());

	        return converter;
	    }

	    @Override
	    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	        converters.add(jackson2HttpMessageConverter());
	    }
}