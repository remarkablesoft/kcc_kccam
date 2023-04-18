package com.remarkablesoft.site.kccam.web.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remarkablesoft.config.AppConfigTest;
import com.remarkablesoft.framework.service.board.popup.vo.PopupInfo;
import com.remarkablesoft.framework.web.util.VisitWebUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

@RunWith( SpringJUnit4ClassRunner.class )
@ComponentScan( basePackages = { "com.remarkablesoft", }, useDefaultFilters = true )
@Import( { AppConfigTest.class } )
@WebAppConfiguration    // WebApplicationContext를 생성할 수 있도록 하는 어노테이션
public class PopupApiControllerTest {
		
		private MockMvc mockMvc;				// controller에 request를 수행해주는 mock 객체
		ObjectMapper mapper;					// 객체를 json 형식으로 변경 시 사용
		
		@Autowired
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;
		
		@Autowired
		PopupApiController popupApiController;		// 테스트를 진행할 controller
		
		@Autowired
		VisitWebUtils visitWebUtils;
		
		@Before
		public void setUp() {
				this.mockMvc = MockMvcBuilders.standaloneSetup( popupApiController )
										   .setMessageConverters( jackson2HttpMessageConverter )
										   .addFilters( new CharacterEncodingFilter( "UTF-8", true) )
										   .build();
				
				this.mapper = AppConfigTest.getObjectMapper();
				
		}
		
		@Test
		public void test() throws JsonProcessingException {
				ObjectMapper objectMapper = new ObjectMapper();
				
				PopupInfo popupInfo = objectMapper.readValue( "{\"oid\":\"\",\"popupContentsTypeFlag\":\"E\",\"popupViewTypeFlag\":\"G\",\"useYn\":\"N\",\"linkTypeFlag\":\"N\",\"centerAlignmentYn\":\"Y\",\"fileList\":[],\"name\":\"test\",\"width\":300,\"height\":300,\"contents\":\"<p>123</p>\",\"startDate\":\"2023-04-19\",\"endDate\":\"2023-04-19\",\"inputUser\":\"\"}", PopupInfo.class );
				System.out.println( "popupInfo = " + popupInfo );
		}
}