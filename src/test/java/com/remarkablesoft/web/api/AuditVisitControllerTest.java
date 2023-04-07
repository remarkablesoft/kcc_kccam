package com.remarkablesoft.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remarkablesoft.config.AppConfigTest;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitCnd;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitInfo;
import com.remarkablesoft.framework.web.util.VisitWebUtils;
import com.remarkablesoft.site.kccam.web.api.HistVisitController;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * 		@주시스템		:	KCC AM
 * 		@서브 시스템		:	audit - visit
 * 		@프로그램 ID		:	AuditVisitControllerTest
 * 		@프로그램 개요 	:	방문자의 이력 ( IP, 시간, URL ) 이력 남기는 프로그램 테스트
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2021. 7. 21.	:	남윤재	-	신규생성
 * 		============================================================================
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ComponentScan( basePackages = { "com.remarkablesoft", }, useDefaultFilters = true )
@Import( { AppConfigTest.class } )
@WebAppConfiguration    // WebApplicationContext를 생성할 수 있도록 하는 어노테이션
public class AuditVisitControllerTest {
		
		private MockMvc mockMvc;				// controller에 request를 수행해주는 mock 객체
		ObjectMapper mapper;					// 객체를 json 형식으로 변경 시 사용
		
		@Autowired
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;
		
		@Autowired
		HistVisitController histVisitController;		// 테스트를 진행할 controller
		
		@Autowired
		VisitWebUtils visitWebUtils;
		
		@Before
		public void setUp() {
				this.mockMvc = MockMvcBuilders.standaloneSetup( histVisitController )
							.setMessageConverters( jackson2HttpMessageConverter )
							.addFilters( new CharacterEncodingFilter( "UTF-8", true) )
							.build();
				
				this.mapper = AppConfigTest.getObjectMapper();
				
		}
		
		@Test
		public void Audit_insert_테스트() throws Exception {
				
				AuditVisitInfo info = SystemFactory.getAuditVisitInfo();
				info.setDefaultInfo();
				info.setUserIp( "127.0.0.1" );
				info.setVisitURL( "http://localhost:3000/kccam/user/main/user_main" );
				
				String json = mapper.writeValueAsString( info );
				try {
						mockMvc.perform( get( "/kccam/api/histVisit/histVisit_insert")
											.contentType( MediaType.APPLICATION_JSON)
											.content( json ) )
											.andDo( print() )
											.andExpect( status().isOk() );
				}
				catch( Exception e ) {
					e.printStackTrace();
				}
		}
		
		@Test
		public void Audit_list_페이지() throws Exception {
				
				AuditVisitCnd cnd = new AuditVisitCnd();
				cnd.setPageSize( 10 );
				cnd.setStartIndex( 1 );
				
				String json = mapper.writeValueAsString( cnd );
				try {
						mockMvc.perform( get ( "/kccam/api/histVisit/histVisit_list")
						.contentType( MediaType.APPLICATION_JSON )
						.content( json ) )
						.andDo( print() )
						.andExpect( status().isOk() );
				}
				catch( Exception e ) {
						e.printStackTrace();
				}
		}
}
