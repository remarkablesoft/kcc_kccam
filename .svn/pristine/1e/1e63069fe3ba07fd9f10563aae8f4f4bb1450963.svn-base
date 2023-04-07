package com.remarkablesoft.framework.web.module.storage.thumbnail;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.storage.StorageUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

/**
 * <pre>
 * 설명 : 썸네일을 만드는 컨트롤러
 * </pre>
 * @author James
 * @since 2016. 7. 8.
 *
 */
@WEBLog
@Controller
public class ThumbnailController {

		private static final Logger logger = LoggerFactory.getLogger( "STORAGE" );

		final static URL NO_IMAGE_URL = ThumbnailController.class.getResource( "/images/no_image.png" );

		public static final String NO_IMAGE = "NO_IMAGE";
		public static HashMap<String, byte[]> cacheImage = new HashMap<>();

		public static final long MEGA_BYTE = 1024 * 1024 * 1;

		@Autowired
		ThumbnailUtils thumbnailUtil;

		//	@RequestMapping( value = "/thumbnail_view2/{storageFileUid}/{fileExt}/{thumbSize}/{isCrop}" )
		//	public void view2( @PathVariable String storageFileUid, @PathVariable String fileExt, @PathVariable String thumbSize, @PathVariable String isCrop, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		//	스크립트에서 사용 : $el.find( "img" ).attr( "src", "/thumbnail_view2/" + item.fileList[0].storageFileUid + "/" + item.fileList[0].fileExt + "/" + RF_CONSTANT.THUMB_SIZE.SIZE_24_24 + "/false"  )

		// http://127.0.0.1:8070/thumbnail_view?storageFileUid=20171010000000000027&fileExt=jpg&thumbSize=24_24&isCrop=false
		@RequestMapping( value = "/thumbnail_view" )
		public void view( @RequestParam String storageFileUid, @RequestParam String fileExt, @RequestParam(
								defaultValue = "140_100" ) String thumbSize, @RequestParam(
														defaultValue = "false" ) String isCrop, HttpServletRequest request, HttpServletResponse response ) throws Exception {

				thumbnailProcess( storageFileUid, fileExt, thumbSize,  isCrop, request, response );
		}

		// "/thumbnail_view/" + data.file.storageFileUid + "/" + data.file.fileExt + "/100_100"/
		@RequestMapping( value = "/thumbnail_view/{storageFileUid}/{fileExt}/{thumbSize}" )
		public void thumbView( @PathVariable String storageFileUid, @PathVariable String fileExt, @PathVariable (required = false) String thumbSize, HttpServletRequest request, HttpServletResponse response ) throws Exception {


				if ( StringUtils.isEmpty(thumbSize)) {
					thumbSize = "100_100";
				}

				thumbnailProcess( storageFileUid, fileExt, thumbSize, "false", request, response );
		}

		
		// /thumbnail_image/{storageFileUid}/{thumbSize}/{isCrop}
		@RequestMapping( value = "/thumbnail_image/{storageFileUid}/{thumbSize}/{isCrop}" )
		public void image( @PathVariable String storageFileUid, @PathVariable (required = false) String thumbSize, @PathVariable (required = false) String isCrop, HttpServletRequest request, HttpServletResponse response ) throws Exception {

				if ( StringUtils.isEmpty(thumbSize)) {
					thumbSize = "100_100";
				}
				
				if ( StringUtils.isEmpty(isCrop)) {
						isCrop = "false";
				}

				thumbnailProcess( storageFileUid, "png", thumbSize, isCrop, request, response );
		}
		
		public void thumbnailProcess( String storageFileUid, String fileExt, String thumbSize, String isCrop, HttpServletRequest request, HttpServletResponse response ) throws Exception {

				logger.debug( "storageFileUid : " + storageFileUid );

				// 파일Path가 없으면 삭제
				if ( storageFileUid == null ) {
						return;
				}

				boolean bCrop = Boolean.parseBoolean( isCrop );

				File thumbnail = null;
				File stroageFile = StorageUtils.getStorageFile( storageFileUid );

				if ( stroageFile == null ) {
						return;
				}

				logger.debug( "fileExt1 : " + fileExt );
				if ( StringUtils.isEmpty(fileExt)) {
					fileExt = FilenameUtils.getExtension( stroageFile.getName());
				}

				logger.debug( "fileExt2 : " + fileExt );
				logger.debug( "stroageFile : " + stroageFile.getAbsolutePath() );

				String realThumbnailFilePath = thumbnailUtil.getRealThumbNailFilePath( stroageFile, thumbSize );

				logger.debug( "realThumbnailFilePath : " + realThumbnailFilePath );

				// 1. thumbnail 있다면 해당 thumbnail 반환
				File realThumbnailFile = new File( realThumbnailFilePath );

				// 썸네일 파일 있으면 썸네일 파일 반환
				if ( realThumbnailFile.exists() && realThumbnailFile.length() != 0L ) {
						thumbnail = realThumbnailFile;
						logger.debug( "썸네일 파일 존재  : " + thumbnail );
				}
				// 썸네일 없다면 생성해서 반환
				else {
						thumbnail = thumbnailUtil.makeThumbnail( stroageFile, fileExt, thumbSize, bCrop );

						logger.debug( "썸네일 파일 생성  : " + (thumbnail != null ? thumbnail.getAbsolutePath() : "null") );
						// 해당 원본파일이 없으면 썸네일도 null이 된다.
						// 썸네일이 없을 경우 noimage 파일을 보여준다.
						if ( thumbnail == null ) {

								if ( cacheImage.containsKey( NO_IMAGE ) ) {
										FileCopyUtils.copy( cacheImage.get( NO_IMAGE ), response.getOutputStream() );
								}
								else {
										File file = new File( NO_IMAGE_URL.getPath() );
										byte[] noImageByte = IOUtils.toByteArray( new FileInputStream( file ) );
										cacheImage.put( NO_IMAGE, noImageByte );
										FileCopyUtils.copy( noImageByte, response.getOutputStream() );
								}

								return;
						}

						// 용량이 크면 삭제처리
						else if ( thumbnail.length() > MEGA_BYTE ) {

								// 썸네일이 1메가 넘는다는건 문제가 있는 것. 삭제처리
								long fileSize = thumbnail.length() / 1024; // kilo byte
								fileSize = fileSize / 1024; // mega byte

								logger.error( "용량이 큰 파일Path  : " + stroageFile.getAbsolutePath() );
								logger.error( "용량이 크면 삭제 처리 사이즈 : " + fileSize + "MB" );
								thumbnail.delete();
						}
				}

				InputStream in = null;
				try {
						if ( thumbnail != null && thumbnail.isFile() ) {
								in = new FileInputStream( thumbnail );
								FileCopyUtils.copy( IOUtils.toByteArray( in ), response.getOutputStream() );
						}
				}
				catch ( IOException e ) {
						logger.error( e.getMessage() );
				}
				finally {
						if ( in != null ) {
								in.close();
						}
				}
		}

		//		@RequestMapping( value = "/storage_thumView/{storageFileUid}/{thumSize}" )
		//		public void fileView( @PathVariable( "storageFileUid" ) String storageFileUid, @PathVariable( "thumSize" ) String thumSize, HttpServletResponse response ) throws IOException {
		//
		//				// 파일키가 없거나 잘못된 경우에 처리
		//				if ( storageFileUid == null || storageFileUid.isEmpty() ) {
		//						return;
		//				}
		//
		//				File thumbnail = StorageUtils.getThumnailFile( storageFileUid, thumSize );
		//
		//				if ( thumbnail == null ) {
		//						return;
		//				}
		//
		//				try {
		//						InputStream in = new FileInputStream( thumbnail );
		//						// response.setContentType("text/plain; charset=x-user-defined");
		//						// response.setHeader("Content-disposition", "attachment; filename=\"" + "test.stl" + "\"");
		//						FileCopyUtils.copy( IOUtils.toByteArray( in ), response.getOutputStream() );
		//				}
		//				catch ( IOException e ) {
		//						e.printStackTrace();
		//				}
		//		}

}
