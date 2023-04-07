package com.remarkablesoft.framework.service.doc.doc.model.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.remarkablesoft.framework.service.doc.doc.vo.DocVersionInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.doc.doc.model.DocService;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;

/**
 *
 *        @주시스템        :	framework-web
 *        @서브 시스템        :	doc - doc
 *        @프로그램 ID        :	DocBLOTest
 *        @프로그램 개요    :	Doc BLO
 *
 *        @변경이력
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      1.1 2021. 03. 05. : 안병현 - 테스트 코드 작성( 테스트 RUN X )
 *      ============================================================================
 */

public class DocBLOTest extends BaseModelTest {
		
		@Autowired
		protected DocBLO docBLO;
		
		@Autowired
		protected DocService docService;
		
		@Test
		public void insert_테스트() {
				
				DocInfo info = SystemFactory.getDocInfo();
				
				info.setCompanyOid( OIDGenerator.generateOID() );
				info.setSystemOid( OIDGenerator.generateOID() );
				info.setCategoryOid( OIDGenerator.generateOID() );
				info.setTitle( "TEST" );
				
				info.setDocType( "docs" );
				info.setCurrentVersionOid( OIDGenerator.generateOID() );
				info.setSecretYn( SystemConstants.FLAG_NO );
				info.setPwd( "1111" );
				info.setInputUserInfoList( "LIST" );
				
				info.setInputUser( OIDGenerator.generateOID() );
				
				info.setCustomField5( "커스텀필드5" );
				
				DocInfo insertResult = docBLO.insert( info );
				System.out.println( insertResult.toString() );
				
		}
		
		@Test
		public void update_테스트() {
				
				DocInfo info = SystemFactory.getDocInfo();
				info.setOid( "1SRKV17I005" );
				
				info.setCompanyOid( OIDGenerator.generateOID() );
				info.setSystemOid( OIDGenerator.generateOID() );
				info.setCategoryOid( OIDGenerator.generateOID() );
				info.setTitle( "수정된 TEST" );
				
				info.setDocType( "QSFV" );
				info.setCurrentVersionOid( OIDGenerator.generateOID() );
				info.setSecretYn( SystemConstants.FLAG_YES );
				info.setPwd( "2222" );
				info.setInputUserInfoList( "asdnfjklancvjkpqanuiepasdfnjk" );
				
				info.setModUser( OIDGenerator.generateOID() );
				info.setModDate( LocalDateTime.now() );
				
				info.setCustomField2( "asfd" );
				
				info = docBLO.update( info );
				System.out.println( info.toString() );
				
		}
		
		@Test
		public void delete_테스트() {
				
				int delResult = docBLO.delete( "1SRKV17I005" );
				String result = (delResult > 0) ? "삭제완료" : "삭제실패";
				
				System.out.println( result );
				
		}
		
		@Test
		public void get_테스트() {
				
				DocCnd cnd = new DocCnd();
				cnd.setOid( "1SY2yxMW005" );
				cnd.setFillFile( true );
				cnd.setOnlyCurrentVersion( true );
				DocInfo info = docBLO.get( cnd );
				
				System.out.println( info.toString() );
		}
		
		@Test
		public void list_테스트() {
				
				DocCnd cnd = new DocCnd();
				cnd.setStartIndex( 1 );
				cnd.setPageSize( 10 );
				cnd.setFillFile( true );
				cnd.setLikeSearch( true );
				cnd.setTargetObject( "AMMT" );
				cnd.setOnlyCurrentVersion( true );
				PageList<DocInfo> list = docBLO.list( cnd );
				
				list.forEach( System.out::println );
		}
		
		@Test
		public void listAll_테스트() {
				
				DocCnd cnd = new DocCnd();
				List<DocInfo> list = docBLO.listAll( cnd );
				
				list.forEach( System.out::println );
				
		}
		
		@Test
		public void ViewList_테스트() {
				
				DocCnd cnd = new DocCnd();
				cnd.setTargetOid( "1ST6THaA000" );
				List<DocInfo> list = docBLO.viewList( cnd );
				
				list.forEach( System.out::println );
				
		}
		
		@Ignore
		@Test
		public void 메일과파일_테스트() {
				
				HashMap<String, Object> paramMap = new HashMap();
				
				paramMap.put( "name", "KCC Advanced Materials" );
				paramMap.put( "from", "kccmaterials@kccworld.co.kr" );
				paramMap.put( "to", "zero@remarkablesoft.com" );
				paramMap.put( "cc", "" );
				paramMap.put( "title", "메일 테스트 제목" );
				paramMap.put( "content", "내용입니다." );
				paramMap.put( "tar", "html" );
				
				//		List<String> fileList = new ArrayList();
				//		fileList.add( "D:/temp/dog.jpg" );
				//
				//		paramMap.put( "fileList" , fileList );
				
				List<String> fileLinkList = new ArrayList<>();
				fileLinkList.add( "https://smartbook.kccworld.info/file_upload/doc/B07\\org\\B07F201601070011788.pdf" );
				
				paramMap.put( "fileLinkList", fileLinkList );
				
				docService.sendEmailWithDocFile( paramMap );
				
		}
		
		@Test
		public void 공유파일링크_메일_테스트() {
				DocInfo info = new DocInfo();
				info.setShareEmailAddress( "zero@remarkablesoft.com" );
				info.setTitle( "공유문서 파일 이름입니다" );
				
				//		DocVersionInfo currentDocInfo = new DocVersionInfo();
				//		currentDocInfo.setOutLinkUrl("https://smartbook.kccworld.info/file_upload/doc/D01/org/D01F201706230031149.pdf");
				
				info.setShareDocLink( "https://smartbook.kccworld.info/file_upload/doc/D01/org/D01F201706230031149.pdf" );
				
				docBLO.sendEmailWithDocLink( info );
				
		}
}
