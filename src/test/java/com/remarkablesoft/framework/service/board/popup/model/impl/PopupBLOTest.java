package com.remarkablesoft.framework.service.board.popup.model.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.board.popup.vo.PopupCnd;
import com.remarkablesoft.framework.service.board.popup.vo.PopupInfo;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PopupBLOTest extends BaseModelTest  {
		
		@Autowired
		private PopupBLO popupBLO;
		
		@Test
		public void 팝업등록() {
				PopupInfo popupInfo = initInfo();
				popupBLO.insert( popupInfo );
		}
		@Test
		public void postPopupToSurvey() throws JsonProcessingException {
				PopupInfo info = initInfo();
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setContentType( MediaType.APPLICATION_JSON );
				
				MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule( new JavaTimeModule() );
				objectMapper.disable( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS );
				String json = objectMapper.writeValueAsString( info );
				System.out.println( "json = " + json );
				
				
				body.add( "popupInfo", json );
				HttpEntity<MultiValueMap<String, String>> stringHttpEntity = new HttpEntity<>( body, httpHeaders );
				
				//				URI uri = UriComponentsBuilder
//									  .fromUriString("")
//									  .path("/survey/api/v1/surveyPopupApi_savePopup")
//									  .build()
//									  .toUri();
//
				String uri = "http://localhost:8888/survey/api/v1/surveyPopupApi_savePopup";
				RestTemplate restTemplate = new RestTemplate();
//				System.out.println( "uri = " + uri.getPath() );
				System.out.println( "info = " + info );
//				json 에러
				ResponseEntity<PopupInfo> popupInfoResponseEntity = restTemplate.postForEntity( uri, stringHttpEntity, PopupInfo.class );
				System.out.println( "responseEntity = " + popupInfoResponseEntity.getBody() );
				/*
				 */
		}
		
		@Test
		public void testInfoJSON() {
				
				PopupInfo popupInfo = initInfo();
				String json = new Gson().toJson( popupInfo );
				System.out.println( "json = " + json );
		}
		
		private PopupInfo initInfo() {
				PopupInfo result = new PopupInfo();
				result.setOid( OIDGenerator.generateOID() );
				result.setName( "[LOCAL]Portal등록" );
				result.setStartDate( LocalDate.now() );
				result.setEndDate( LocalDate.now().plusMonths( 1L ) );
				result.setWidth( 300 );
				result.setHeight( 300 );
				result.setPopupContentsTypeFlag( PopupInfo.POPUP_CONTENTS_TYPE_FLAG_IMAGE );
				result.setPopupViewTypeFlag( "G" );
				result.setLinkTypeFlag( "N" );
				result.setUseYn( "Y" );
				result.setInputUser( "1SXvq2et04y" );
				result.setInputDate( LocalDateTime.now() );
				result.setCenterAlignmentYn( "Y" );

				List<FileInfo> fileList = new ArrayList<>();
				FileInfo fileInfo = new FileInfo();
				fileInfo.setOid( "1TEfULO901H" );
				fileInfo.setStorageFileUid( "20220816000000000016" );
				fileInfo.setFileName( "popup_test10.png" );
				fileInfo.setFileSize( (long) 689.0 );
				fileInfo.setFileExt( "png" );
				fileInfo.setTargetObject( "1TEfULO901G" );
				fileInfo.setTargetObject( "FWPU" );
				fileInfo.setOrderNo( 0 );
				fileInfo.setUseYn( "Y" );
				fileInfo.setThumbYn( "N" );
//				fileInfo.setInputDate( LocalDateTime.now() );
				result.setFileList( fileList );
				return result;
		}

		@Test
		public void popupBLO_listAll() {

			List<String> list = new ArrayList<>();
			list.add( "G" );
			list.add( "L" );

			PopupCnd cnd = new PopupCnd().setPopupViewTypeFlagList( list );
			cnd.setOrderNo(1);
			List<PopupInfo> popupInfos = popupBLO.listAll( cnd );

			popupInfos.forEach( System.out::println );
		}

}