package com.remarkablesoft.framework.web.module.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.remarkablesoft.framework.common.SystemConfig;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.storage.file.model.FileService;
import com.remarkablesoft.framework.service.storage.storagefile.model.StorageFileService;
import com.remarkablesoft.framework.service.storage.storagefile.vo.StorageFileCnd;
import com.remarkablesoft.framework.service.storage.storagefile.vo.StorageFileInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.controller.BaseController;

import lombok.extern.slf4j.Slf4j;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module - storage
 * 		@프로그램 ID		:	StorageFileController
 * 		@프로그램 개요 		:	StorageFileController는 일단 스토리지에 파일을 올리고 해당 키파일을 가지고 DB에 업데이트 하는 형식</br>
 * 							 - FileUpload는 일단 스토리지에 파일을 올리고 해당 키파일을 가지고 DB에 업데이트 하는 형식</br>
 *							 - 일반적인 input Type으로 파일 업로드해서 쓰실려면 file_upload2를 호출해서 사용</p>
 * 							순서</br>
 *							1. 파일 업로드시 해당 해시값을 추출해서  storageFileService에서 해시값으로 스토리지 파일이 있는지 확인</br>
 *							2. 파일이 존재하면 기존 스토리지아이디를 이용</br>
 *							3. 파일이 없다면 storageFileInfo 생성</br>
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 7.	:	james	-	신규생성
 * 		============================================================================
 */
@Slf4j
@Controller
@CrossOrigin
@RequestMapping( "/storage" )
public class StorageFileController extends BaseController {

		@Autowired
		FileService fileService;

		@Autowired
		StorageFileService storageFileService;

		/**
		 *
		 * @param file
		 * @param model
		 * @throws Exception
		 */
		@RequestMapping( value = "/storageFile_upload2.json" )
		public void upload2( MultipartFile file, Model model ) throws Exception {

				getFileMap( file );
		}

		/**
		 * 파일 업로드
		 *
		 * @param file
		 * @param model
		 * @throws Exception
		 */
		@RequestMapping( value = "/storageFile_upload.json" )
		public @ResponseBody List<Map<String, Object>> upload( MultipartHttpServletRequest request, HttpServletResponse response ) {

				Map<String, MultipartFile> fileMap = request.getFileMap();

				List<Map<String, Object>> fileList = new ArrayList<>();

				for ( MultipartFile multipartFile : fileMap.values() ) {

						if ( SystemConfig.IS_LOCAL || SystemConfig.IS_DEV ) {

								fileList.add( getFileMapForLocal( multipartFile ) );
						}
						else {
								fileList.add( getFileMap( multipartFile ) );
						}

				}

				return fileList;

		}

		/**
		 * 파일 삭제
		 * 실제 파일 삭제는 배치로 처리
		 *
		 * @param storageFileUid
		 * @param model
		 * @throws Exception
		 */
		@RequestMapping( value = "/storageFile_remove.json" )
		public void remove( @RequestParam( "storageFileUid" ) String storageFileUid, Model model ) throws Exception {

				//				StorageUtils.remove( storageFileUid );
				if ( storageFileUid == null || storageFileUid.isEmpty() ) {
						return;
				}

				fileService.deleteByStorageFileUid( storageFileUid );
		}


		/**
		 * 파일 리스트로 삭제
		 * 실제 파일 삭제는 배치로 처리
		 * storageFileUids는 ,로 이루어진 storageFileUid
		 *
		 * @param storageFileUidList
		 * @throws Exception
		 */
		@RequestMapping( value = "/storageFile_removeList.json" )
		public void removeList( @RequestParam( "storageFileUids" ) String storageFileUids, Model model ) throws Exception {

				if ( storageFileUids == null ) {
						return;
				}

				String[] targets = StringUtils.tokenizeToStringArray( storageFileUids, "," );
				List<String> target = CollectionUtils.arrayToList( targets );
				CollectionUtils.nullRemove( target );

				fileService.deleteByStorageFileUidList( target );
		}

		@RequestMapping( value = "/storageFile_fileView/{storageFileUid}" )
		public void fileView( @PathVariable( "storageFileUid" ) String storageFileUid, HttpServletResponse response ) throws IOException {

				
				log.trace( "storageFileUid : " + storageFileUid );
				
				// 파일키가 없거나 잘못된 경우에 처리
				if ( storageFileUid == null || storageFileUid.isEmpty() ) {
						return;
				}

				File stlFile = StorageUtils.getStorageFile( storageFileUid );
				
				
				if ( stlFile == null ) {
						log.trace( "stlFile is null " );
						return;
				}
				
				log.trace( "stlFile : " + stlFile.getAbsolutePath() );
				

				try {
						InputStream in = new FileInputStream( stlFile );
						FileCopyUtils.copy( IOUtils.toByteArray( in ), response.getOutputStream() );
				}
				catch ( IOException e ) {
						e.printStackTrace();
				}
		}

		@RequestMapping( value = "/storageFile_fileDown/{fileName}/{storageFileUid}" )
		public void fileDown( @PathVariable( "fileName" ) String fileName, @PathVariable( "storageFileUid" ) String storageFileUid, HttpServletRequest request, HttpServletResponse response ) throws IOException {

//				System.out.println( fileName );

				// 파일키가 없거나 잘못된 경우에 처리
				if ( storageFileUid == null || storageFileUid.isEmpty() ) {
						return;
				}


				_fileDown( fileName, storageFileUid, request, response  );
		}
		
		
//		@RequestMapping( value = "/storageFile_fileDown/digitalSign/{digitalSignOid}" )
//		public void digitalSign( @PathVariable( "digitalSignOid" ) String digitalSignOid, HttpServletRequest request, HttpServletResponse response ) throws IOException {
//			
//			// 파일키가 없거나 잘못된 경우에 처리
//			if ( StringUtils.isEmpty( digitalSignOid ) ) {
//					return;
//			}
//			
//			//base64파일이 커서 oid로 검색해서 가져오기.
//			DigitalSignCnd digitalSignCnd = new DigitalSignCnd();
//			digitalSignCnd.setOid( digitalSignOid );
//			
//			DigitalSignInfo digitalSignInfo = digitalSignService.get( digitalSignCnd );
//			
//			if ( digitalSignInfo == null ) {
//				
//					return ;
//			}
//								
//			String fileName = "sign_" + digitalSignInfo.getTargetOid() + ".png";
//
//			_fileDown( fileName, null, digitalSignInfo, request, response );
//		}

		
		/**
		 * froala 파일 업로드
		 *
		 * @param file
		 * @param model
		 * @throws Exception
		 */
		@RequestMapping( value = "/storageFile_froalaUpload.json" )
		public @ResponseBody Map<String, Object> froalaUpload( MultipartHttpServletRequest request, HttpServletResponse response ) throws Exception {			
				String domainUrl = request.getRequestURL().toString().replace(request.getRequestURI(),"" );

				Map<String, MultipartFile> fileMap = request.getFileMap();

				Map<String, Object> map = null;

				for ( MultipartFile multipartFile : fileMap.values() ) {

						if ( SystemConfig.IS_LOCAL || SystemConfig.IS_DEV ) {

								map = getFileMapForLocal( multipartFile ) ;
						}
						else {
								map = getFileMap( multipartFile );
						}

				}
				
				Map<String, Object> linkObject = new HashMap<String, Object>();
				String szLink = "";
				if ( map != null) {
				
						szLink = map.get( "uploadedPath" ) + "";
				}
				
				linkObject.put("link", domainUrl + szLink );
				
				return linkObject;

		}
		
		/**
		 * froala 파일 업로드
		 * { link: 'path/to/image.jpg' }
		 *
		 * @param file
		 * @param model
		 * @throws Exception
		 */
		@RequestMapping( value = "/storageFile_froalaUpload2.json" )
		public @ResponseBody String froalaUpload2( MultipartHttpServletRequest request, HttpServletResponse response ) throws Exception {

				Map<String, MultipartFile> fileMap = request.getFileMap();

				Map<String, Object> map = null;

				for ( MultipartFile multipartFile : fileMap.values() ) {

						if ( SystemConfig.IS_LOCAL || SystemConfig.IS_DEV ) {

								map = getFileMapForLocal( multipartFile ) ;
						}
						else {
								map = getFileMap( multipartFile );
						}

				}
				
				Map<String, Object> linkObject = new HashMap<String, Object>();
				String szLink = "";
				if ( map != null) {
				
						szLink = map.get( "uploadedPath" ) + "";
				}
				
				linkObject.put("link", szLink );
				
				return "{ 'link' : '" + szLink + "' }";
		}


		/**
		 * 해시값이 있으면 해당 해시값의 파일을 찾아서 사용.
		 * 해시값이 없다면 등록.
		 * 
		 * 개발용일시 스토리지값이 동일값이 만들어져서 중복 오류가 날수 있다.
		 * 
		 * @author james
		 * @param multipartFile
		 * @return
		 */
		protected Map<String, Object> getFileMap( MultipartFile multipartFile ) {

				String storageFileUid = "";
				try {
						String hashValue = StorageUtils.getFileHash( multipartFile.getBytes() );
						StorageFileInfo storageFileInfo = storageFileService.getByHashValue( hashValue );

						if ( storageFileInfo != null ) {
								storageFileUid = storageFileInfo.getStorageFileUid();

								storageFileInfo.setStorageFileUid( storageFileUid );
								storageFileInfo.setFileCount( storageFileInfo.getFileCount() + 1 );

								storageFileService.update( storageFileInfo );
						}
						else {

								storageFileUid = StorageUtils.uploadFile( multipartFile );

								storageFileInfo = SystemFactory.getStorageFileInfo();
								storageFileInfo.setStorageFileUid( storageFileUid );
								storageFileInfo.setHashValue( hashValue );

								storageFileService.insert( storageFileInfo );
						}
				}
				catch ( IOException e ) {
						e.printStackTrace();
				}

				Map<String, Object> map = new HashMap<>();
				String fileExt = StorageUtils.getFileExt( multipartFile.getOriginalFilename() );
				map.put( "name", multipartFile.getOriginalFilename() );											// 원본 파일명
				map.put( "type", fileExt );				// 원본 파일 확장자
				map.put( "fileSize", String.valueOf( multipartFile.getSize() ) );								// 원본 파일 사이즈
				map.put( "storageFileUid", storageFileUid );															// 서버 파일 KEY(StorageFileUid)

				map.put( "uploadedPath", StorageUtils.makeDownloadUrl( multipartFile.getOriginalFilename(), storageFileUid ) );

				if ( StorageUtils.isImage( multipartFile ) ) {
						map.put( "thumbUrl", StorageUtils.makeThumbnailUrl( storageFileUid, fileExt, null ));
				}

				return map;
		}

		/**
		 * 해시값이 있으면 해당 해시값의 파일을 찾아서 사용.
		 * 해시값이 없다면 등록.
		 * 
		 * 개발용일시 스토리지값이 동일값이 만들어져서 중복 오류가 날수 있어서 처리.
		 * 
		 * @author james
		 * @param multipartFile
		 * @return
		 */
		protected Map<String, Object> getFileMapForLocal( MultipartFile multipartFile ) {

				String storageFileUid = "";
				try {
						
						
						String hashValue = StorageUtils.getFileHash( multipartFile.getBytes() );
						StorageFileInfo storageFileInfo = storageFileService.getByHashValue( hashValue );

						
						if ( storageFileInfo != null ) {
								storageFileUid = storageFileInfo.getStorageFileUid();
								
								File file = StorageUtils.getStorageFile ( storageFileUid );
								
								// 해시값이 있는데 없다면 내 로컬에 파일을 다시 만든다.
								if ( file == null ) {
										storageFileUid = StorageUtils.uploadFile( multipartFile , storageFileUid);
								}

								storageFileInfo.setStorageFileUid( storageFileUid );
								storageFileInfo.setFileCount( storageFileInfo.getFileCount() + 1 );

								storageFileService.update( storageFileInfo );
						}
						else {

								storageFileUid = StorageUtils.uploadFile( multipartFile );

								String destStorageFileUid = fileUniqueStorageFileUid( storageFileUid );

								// 같지 않다면 DB에 있는 StorageFileUID 만큼 키가 늘어난 상태
								if ( !storageFileUid.equals( destStorageFileUid ) ) {

									StorageUtils.copyFile( storageFileUid, destStorageFileUid );
								}

								storageFileInfo = SystemFactory.getStorageFileInfo();
								storageFileInfo.setStorageFileUid( destStorageFileUid );
								storageFileInfo.setHashValue( hashValue );

								storageFileService.insert( storageFileInfo );
						}
				}
				catch ( IOException e ) {
						e.printStackTrace();
				}

				Map<String, Object> map = new HashMap<>();
				String fileExt = StorageUtils.getFileExt( multipartFile.getOriginalFilename() );
				map.put( "name", multipartFile.getOriginalFilename() );											// 원본 파일명
				map.put( "type", fileExt );				// 원본 파일 확장자
				map.put( "fileSize", String.valueOf( multipartFile.getSize() ) );								// 원본 파일 사이즈
				map.put( "storageFileUid", storageFileUid );															// 서버 파일 KEY(StorageFileUid)

				map.put( "uploadedPath", StorageUtils.makeDownloadUrl( multipartFile.getOriginalFilename(), storageFileUid ) );

				if ( StorageUtils.isImage( multipartFile ) ) {
						map.put( "thumbUrl", StorageUtils.makeThumbnailUrl( storageFileUid, fileExt, null ));
				}

				return map;
		}

		/**
		 * local에서는 스토리지 파일이 중복될수 있으므로 체크해서 중복 없는 스토리지 파일을 반환.
		 * 
		 * @author james
		 * @param storageFileUid
		 * @return
		 */
		protected String fileUniqueStorageFileUid( String storageFileUid ) {

				StorageFileCnd cnd = new StorageFileCnd();
				cnd.setStorageFileUid( storageFileUid );
				boolean result = storageFileService.exist( cnd );

				if ( result ) {
					storageFileUid = StorageFileUIDGenerator.generateUID();
					storageFileUid = fileUniqueStorageFileUid( storageFileUid );
				}

				return storageFileUid;
		}

		
		
		/**
		 * 파일다운로드 공통으로 사용하기 위해.
		 * 
		 */
		protected void _fileDown( String fileName, String storageFileUid, HttpServletRequest request, HttpServletResponse response  ) throws IOException {

				ServletOutputStream out = response.getOutputStream();

				FileInputStream fis = null;
				try {

						response.setHeader( "Content-Transfer-Encoding", "binary" );
						response.setContentType( "application/x-download" );
						// response.setHeader("Content-Disposition", (bView ? "filename=" : "attachment; filename=") + szFileName);
						// response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

						String userAgent = request.getHeader( "User-Agent" );

						if ( userAgent.indexOf( "Chrome" ) > -1 || userAgent.indexOf( "Firefox" ) > -1 ) {
								response.setHeader( "Content-Disposition", "attachment;filename=\"" + new String( fileName.getBytes( "UTF-8" ), "ISO-8859-1" ) + "\";" );
						}
						else if ( userAgent.indexOf( "MSIE 5.5" ) > -1 ) { // MS IE 5.5 이하
								String decodeName = URLDecoder.decode( fileName, "UTF-8" );
								String encodeName = URLEncoder.encode( decodeName, "UTF-8" );
								response.setHeader( "Content-Disposition", "filename=" + encodeName.replaceAll( "\\+", "\\ " ) + ";" );
						}
						else if ( userAgent.indexOf( "MSIE" ) > -1 ) { // MS IE (보통은 6.x 이상 가정)

								// log.info( "처음 - fileName : " + fileName);
								String decodeName = URLDecoder.decode( fileName, "UTF-8" );
								// log.info( "디코드 - fileName : " + decodeName);
								String encodeName = URLEncoder.encode( decodeName, "UTF-8" );
								// log.info( "인코드 - fileName : " + encodeName);

								response.setHeader( "Content-Disposition", "attachment; filename=" + encodeName.replaceAll( "\\+", "\\ " ) + ";" );
						}
						else { // 모질라나 오페라
								response.setHeader( "Content-Disposition", "attachment; filename=" + new String( fileName.getBytes( "euc-kr" ), "latin1" ).replaceAll( "\\+", "\\ " ) + ";" );
						}

						File data = null;
						// 일반 파일
						if ( StringUtils.isNotEmpty( storageFileUid )) {

								data = StorageUtils.getStorageFile( storageFileUid );
						}

						if ( data == null ) {
							return;
						}
						fis = new FileInputStream( data );

						IOUtils.copy( fis, out );

						fis.close();
						out.flush();
						out.close();

				}
				catch ( Exception e ) {
						// System.out.println(e);
						e.printStackTrace();
				}
				finally {
						if ( fis != null )
								try {
										fis.close();
								}
								catch ( Exception e ) {
										e.printStackTrace();
								} ;
						if ( out != null )
								try {
										out.close();
								}
								catch ( Exception e ) {
										e.printStackTrace();
								} ;
				}
		}
		
}
