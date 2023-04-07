package com.remarkablesoft.web.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remarkablesoft.config.AppConfigTest;
import com.remarkablesoft.site.kccam.common.ExcelJsonCnd;
import com.remarkablesoft.site.kccam.web.api.ExcelController;

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
public class ExcelControllerTest {

		private MockMvc mockMvc;    				// controller에 request를 수행해주는 mock 객체
		ObjectMapper mapper; 						// 객체를 json 형식으로 변경 시 사용

		@Autowired
		ExcelController excelController;			// 테스트를 진행할 controller

		@Autowired
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;

		@Before
		public void setUp() {

				this.mockMvc = MockMvcBuilders.standaloneSetup( excelController )
											  .setMessageConverters( jackson2HttpMessageConverter )
											  .addFilters( new CharacterEncodingFilter( "UTF-8", true ) )
											  .build();

				this.mapper = AppConfigTest.getObjectMapper();
		}

		@Test
		public void product_list_테스트() throws Exception {

				ExcelJsonCnd cnd = new ExcelJsonCnd();

				cnd.setFileName( "엑셀데이터!!.xlsx" );
				cnd.setSheetName( "시트명" );
				cnd.setDisplayHeader( "No.,AAA,BBB,CCC" );

				cnd.setJsonData( makeExcelSample() );

				String json = mapper.writeValueAsString( cnd );

				System.out.print( json );

				try {
						mockMvc.perform( get( "/kccam/api/excel/excel_makeWithJson" )
										      .contentType( MediaType.APPLICATION_JSON )
											  .content( json ) )
							   .andDo( print() )
							   .andExpect( status().isOk() );

				}
				catch ( Exception ex ) {
						ex.printStackTrace();
				}
		}

		private String makeExcelSample() throws JsonProcessingException {

				List<HashMap<String, String>> listExcelData = new LinkedList<>();
				LinkedHashMap<String, String> excelData = null;

				int nCount = 0;

				for ( int i = 0; i < 10; i++ ) {

					excelData = new LinkedHashMap<>();
					excelData.put("No.", Integer.toString( ++nCount ) );
					excelData.put("AAA", "AAA" + i );
					excelData.put("BBB", "BBB" + i );
					excelData.put("CCC", "CCC" + i );

					listExcelData.add( excelData );
				}

				return mapper.writeValueAsString( listExcelData );
		}


}
