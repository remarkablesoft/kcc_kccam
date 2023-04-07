package com.remarkablesoft.web.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remarkablesoft.config.AppConfigTest;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.web.api.ProductController;

/**
 *
 * 		@주시스템			:	kcc am
 * 		@서브 시스템		:	product - product
 * 		@프로그램 ID		:	ProductControllerTest
 * 		@프로그램 개요 	:	ProductController 테스트
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2021. 5. 19.	:	james	-	신규생성
 * 		============================================================================
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ComponentScan( basePackages = { "com.remarkablesoft", }, useDefaultFilters = true )
@Import( { AppConfigTest.class } )
@WebAppConfiguration	// WebApplicationContext를 생성할 수 있도록 하는 어노테이션
public class ProductControllerTest {

		private MockMvc mockMvc;    				// controller에 request를 수행해주는 mock 객체
		ObjectMapper mapper; 						// 객체를 json 형식으로 변경 시 사용

		@Autowired
		ProductController productController;		// 테스트를 진행할 controller

		@Autowired
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;

		@Before
		public void setUp() {
				this.mockMvc = MockMvcBuilders.standaloneSetup( productController )
											  .setMessageConverters( jackson2HttpMessageConverter )
											  .addFilters( new CharacterEncodingFilter( "UTF-8", true ) )
											  .build();

				this.mapper = AppConfigTest.getObjectMapper();
		}

		@Test
		public void product_list_테스트() throws Exception {

				ProductCnd cnd = new ProductCnd();
				String json = mapper.writeValueAsString( cnd );

				try {
						mockMvc.perform( get( "/kccam/api/product/product_list" )
										      .contentType( MediaType.APPLICATION_JSON )
											  .content( json ) )
							   .andDo( print() )
							   .andExpect( status().isOk() );

				}
				catch ( Exception ex ) {
						ex.printStackTrace();
				}
		}

}
