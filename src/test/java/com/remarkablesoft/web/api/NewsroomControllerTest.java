package com.remarkablesoft.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remarkablesoft.config.AppConfigTest;
import com.remarkablesoft.site.kccam.service.newsroom.vo.NewsroomCnd;
import com.remarkablesoft.site.kccam.service.newsroom.vo.NewsroomInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import com.remarkablesoft.site.kccam.web.api.NewsroomController;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * 		@주시스템			:	kcc am
 * 		@서브 시스템		:	newsroom
 * 		@프로그램 ID		:	NewsroomControllerTest
 * 		@프로그램 개요 	:	NewsroomController 테스트
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2021. 6. 17.	:	zero	-	신규생성
 * 		============================================================================
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ComponentScan( basePackages = { "com.remarkablesoft", }, useDefaultFilters = true )
@Import( { AppConfigTest.class } )
@WebAppConfiguration    // WebApplicationContext를 생성할 수 있도록 하는 어노테이션
public class NewsroomControllerTest {
		
		private MockMvc mockMvc;    				// controller에 request를 수행해주는 mock 객체
		ObjectMapper mapper; 						// 객체를 json 형식으로 변경 시 사용
		
		@Autowired 
		NewsroomController newsroomController;          //테스트 진행할 컨트롤러
		
		@Autowired
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;
		
		@Before
		public void setUp() {
				this.mockMvc = MockMvcBuilders.standaloneSetup( newsroomController )
							               .setMessageConverters( jackson2HttpMessageConverter )
							               .addFilters( new CharacterEncodingFilter( "UTF-8", true ) )
							               .build();
				
				this.mapper = AppConfigTest.getObjectMapper();
		}
		
		@Test
		public void 뉴스정보저장_insertOrUpdate_테스트() throws Exception {
				
				NewsroomInfo info = new NewsroomInfo();
				info.setTitle( "controller test" );
				info.setLang( "KO" );
				
				List<ProductInfo> productList = new ArrayList<>();
				ProductInfo product1 = new ProductInfo();
				product1.setOid( "1SWKK0D1024" );
				
				productList.add( product1 );
				
				ProductInfo product2 = new ProductInfo();
				product2.setOid( "1SWKK0D102B" );
				
				productList.add( product2 );
				
				info.setProductList( productList );
				
				info.setProductList( productList );
				
				String json = mapper.writeValueAsString( info );
				
				try {
						mockMvc.perform( get( "/kccam/api/newsroom/newsroom_insertOrUpdate" )
									                 .contentType( MediaType.APPLICATION_JSON )
									                 .content( json ) )
													 .andDo( print() )
													 .andExpect( status().isOk() );
				}
				catch ( Exception e ){
						e.printStackTrace();
				}
		}
		
		@Test
		public void 뉴스정보삭제_deleteFlagUpdate_테스트() throws Exception {
				
				NewsroomInfo info = new NewsroomInfo();
				info.setOid( "1SaU72ap000" );
				info.setLang( "CN" );
				
				String json = mapper.writeValueAsString( info );
				
				try {
						mockMvc.perform( get( "/kccam/api/newsroom/newsroom_deleteFlagUpdate" )
									                 .contentType( MediaType.APPLICATION_JSON )
									                 .content( json ) )
													 .andDo( print() )
													 .andExpect( status().isOk() );
				}
				catch ( Exception e ){
						e.printStackTrace();
				}
		}
		
		@Test
		public void 뉴스정보가져오기_get_테스트() throws Exception {
				
				NewsroomCnd cnd = new NewsroomCnd();
				cnd.setLang( "KO" );
				cnd.setOid( "1SaZG8EH000" );
				
				String json = mapper.writeValueAsString( cnd );
				
				try {
						mockMvc.perform( get( "/kccam/api/newsroom/newsroom_get" )
									                 .contentType( MediaType.APPLICATION_JSON )
									                 .content( json ) )
													 .andDo( print() )
													 .andExpect( status().isOk() );
				}
				catch ( Exception e ){
						e.printStackTrace();
				}
		}
		
		@Test
		public void 뉴스정보가져오기_조회수증가_이전다음글포함() throws Exception {
				
				NewsroomCnd cnd = new NewsroomCnd();
				cnd.setLang( "KO" );
				cnd.setOid( "1SaYNH8Z000" );
				
				String json = mapper.writeValueAsString( cnd );
				
				try {
						mockMvc.perform( get( "/kccam/api/newsroom/newsroom_viewWithPrevAndNext" )
									                 .contentType( MediaType.APPLICATION_JSON )
									                 .content( json ) )
									.andDo( print() )
									.andExpect( status().isOk() );
				}
				catch ( Exception e ){
						e.printStackTrace();
				}
		}
		
		@Test
		public void 뉴스정보페이지리스트() throws Exception {
				
				NewsroomCnd cnd = new NewsroomCnd();
				cnd.setStartIndex( 1 );
				cnd.setPageSize( 10 );
				cnd.setLang( "KO" );
				
				String json = mapper.writeValueAsString( cnd );
				
				try {
						mockMvc.perform( get( "/kccam/api/newsroom/newsroom_list" )
									                 .contentType( MediaType.APPLICATION_JSON )
									                 .content( json ) )
									.andDo( print() )
									.andExpect( status().isOk() );
				}
				catch ( Exception e ){
						e.printStackTrace();
				}
		}
		
}
