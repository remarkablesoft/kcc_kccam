package com.remarkablesoft.site.kccam.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.doc.doc.model.DocService;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.storage.file.model.FileService;
import com.remarkablesoft.framework.service.storage.file.vo.FileCnd;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.framework.web.controller.BaseController;

/**
 * @주시스템            :	kccam
 * @서브 시스템        :   api
 * @프로그램 ID        :   DocController.java
 * @프로그램 개요        :   문서 서비스 호출 컨트롤러
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-04-29 : 최원준 - 신규생성
 * ============================================================================
 */

@WEBLog
@RestController
@RequestMapping( "/kccam/api/doc" )
public class DocController extends BaseController {
		
		@Autowired
		protected DocService docService;
		
		@Autowired
		protected FileService fileService;
		
		/**
		 * 문서 정보를 가져옵니다.
		 *
		 * @param cnd
		 * @return
		 */
		@RequestMapping( value = "doc_get" )
		public ResponseEntity<DocInfo> get( @RequestBody DocCnd cnd ) {
				
				DocInfo info = docService.get( cnd );
				
				return ResponseEntity.ok( info );
		}
		
		/**
		 * 문서 정보를 저장합니다.
		 *
		 * @param info
		 * @return
		 */
		@RequestMapping( value = "doc_save" )
		public ResponseEntity<DocInfo> save( @RequestBody DocInfo info ) {
				
				info = docService.insertOrUpdate( info );
				
				return ResponseEntity.ok( info );
		}
		
		/**
		 * 문서 페이지리스트를 가져옵니다.
		 *
		 * @param
		 * @return
		 * @author 최원준
		 */
		@RequestMapping( value = "/doc_list" )
		public ResponseEntity<HashMap<String, Object>> list( @RequestBody DocCnd cnd ) {
				
				HashMap<String, Object> resultMap = new HashMap<>();
				PageList<DocInfo> list = docService.list( cnd );
				
				resultMap.put( "list", list );
				resultMap.put( "totalCount", CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );
				
				return ResponseEntity.ok( resultMap );
		}
		
		/**
		 * 문서 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return List<DocInfo>
		 * @author 최원준
		 */
		@RequestMapping( value = "/doc_listAll" )
		public ResponseEntity<List<DocInfo>> listAll( @RequestBody DocCnd cnd ) {
				
				List<DocInfo> listAll = docService.listAll( cnd );
				return ResponseEntity.ok( listAll );
		}
		
		/**
		 * 문서를 삭제합니다.
		 * 
		 * @param oid
		 * @return
		 */
		@RequestMapping( value = "/doc_delete" )
		public ResponseEntity<Integer> delete( @RequestBody DocCnd cnd ) {
				int result = docService.delete( cnd.getOid() );
				
				return ResponseEntity.ok( result );
		}
		
		/**
		 * 문서 파일 정보가 존재하는지 체크하고 있으면 반환합니다.
		 *
		 * @param cnd
		 * @return FileInfo
		 * @author 최원준
		 */
		@RequestMapping( value = "/doc_existFile" )
		public ResponseEntity<FileInfo> existFile( @RequestBody FileCnd cnd ) {
				
				FileInfo fileInfo = fileService.getByCnd( cnd );
				return ResponseEntity.ok( fileInfo );
		}
		
		@RequestMapping( value = "/doc_userRegisterAndSendEmailWithDocFile" )
		public ResponseEntity<Map<String, Object>> userRegisterAndSendEmailWithDocFile( @RequestBody HashMap<String, Object> paramMap ) {
				
				int mailCount = docService.sendEmailWithDocFile( paramMap );
				
				Map<String, Object> resultMap = new HashMap<String, Object>();
				
				if ( 0 < mailCount ) {
					resultMap.put( "success", mailCount);
				}

				return ResponseEntity.ok ( resultMap );
		}
		
		@RequestMapping( value = "/doc_userRegisterAndSendEmailsWithDocFile" )
		public ResponseEntity<Map<String, Object>> userRegisterAndSendEmailsWithDocFile( @RequestBody List<HashMap<String, Object>> paramMapList ) {
				
				int mailCount = 0;
			
				for( HashMap<String, Object> paramMap : paramMapList ){
					mailCount += docService.sendEmailWithDocFile( paramMap );
				}
				
				Map<String, Object> resultMap = new HashMap<String, Object>();
				
				if ( 0 < mailCount ) {
					resultMap.put( "success", mailCount);
				}
				
				return ResponseEntity.ok ( resultMap );
		}
		
		/**
		 * 파일 다운로드 링크를 메일로 보냅니다
		 *
		 * @param info
		 * @return int
		 * @author 황지영
		 */
		@RequestMapping( value = "/doc_sendEmailWithDocLink" )
		public void sendEmailWithDocLink( @RequestBody DocInfo info ) {
				
				docService.sendEmailWithDocLink( info );
		}
		
}
