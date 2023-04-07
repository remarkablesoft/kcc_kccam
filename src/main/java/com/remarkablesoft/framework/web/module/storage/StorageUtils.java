package com.remarkablesoft.framework.web.module.storage;

import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.framework.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 설명 : 파일저장 Utils
 * 썸네일 2가지 사용. 일반 이미지 파일은 Thumbnails을 이용 https://code.google.com/p/thumbnailator/wiki/Examples
 * InputStream은 프레임웍내의 ThumbnailUtil을 이용함.
 * 
 * 2020.01.06 james 이중화 처리
 *
 * @author james
 * @since 2014. 5. 16.
 *
 */
@Slf4j
public class StorageUtils {

		private static final Logger logger = LoggerFactory.getLogger( "STORAGE" );

		public static final String SERVER_UPLOAD_FILE_PATH = ApplicationPropertiesUtils.getValue( "server.upload.file.path" );
		public static final String SERVER_UPLOAD_FILE_TEMP_PATH = ApplicationPropertiesUtils.getValue( "server.upload.temp.path" );
		public static final String SERVER_UPLOAD_FILE_THUMBNAIL_PATH = ApplicationPropertiesUtils.getValue( "server.upload.thumbnail.path" );
		public static final String STORAGE_FILE_EXTENDTION = ApplicationPropertiesUtils.getValue( "storage.file.extendtion", ".db" );

		/**
		 * storageFolder의 Root와 yyyy > mm > dd까지의 폴더 path를 반환
		 *
		 * @author james
		 * @return
		 */
		private static String findStroageRootWithDateFolder() {

				String systemDate = DateUtils.getCurrentDate( DateUtils.DF_YYYYMMDD );
				return SERVER_UPLOAD_FILE_PATH + File.separator + systemDate.substring( 0, 4 ) + File.separator + systemDate.substring( 4, 6 ) + File.separator + systemDate.substring( 6, 8 );
		}

		/**
		 * 제일 마지막 스토리지 파일을 찾아낸다
		 *
		 * @param folderPath
		 * @return
		 */
		public static String findLastStorageFile() {

				return findLastStorageFile( findStroageRootWithDateFolder() );
		}

		/**
		 * yyyy > mm > dd 다음에 나오는 첫번째 폴더
		 * 서버키에 따라 폴더가 달라질수 있어서 첫번째 폴더를 찾아서 반환
		 *
		 * @author james
		 * @return
		 */
		public static String findFirstFolder() {

				File file = new File( findStroageRootWithDateFolder() );

				if ( file.isDirectory() ) {
						return findMaxFolder( file.list() );
				}

				return "";
		}

		/**
		 * 제일 마지막 스토리지 파일을 찾아낸다
		 *
		 *
		 * @param folderPath  yyyy,mm,dd가 있는 폴더 path
		 * @return
		 */
		public static String findLastStorageFile( String folderPath ) {

				if ( StringUtils.isEmpty( folderPath ) ) {
						return "";
				}

				File file = new File( folderPath );

				if ( file.isDirectory() ) {
						String maxFolder = findMaxFolder( file.list() );
						if ( StringUtils.isNotEmpty( maxFolder ) ) {
								return findLastStorageFile( file.getAbsolutePath() + File.separator + maxFolder );
						}
				}
				else {
						return removeExtName( file.getName() );
				}

				return "";
		}

		/**
		 * 파일 확장자 제거
		 *
		 * @param name
		 * @return
		 */
		private static String removeExtName( String name ) {

				if ( name == null || name.length() <= 0 )
						return "";

				if ( name.indexOf( "." ) > 0 ) {
						return name.substring( 0, name.lastIndexOf( "." ) );
				}

				return "";
		}

		/**
		 * 폴더에서 제일 높은 순번을 찾는다
		 *
		 * @param folders
		 * @return
		 */
		public static String findMaxFolder( String[] folders ) {

				if ( folders == null || folders.length < 1 )
						return "";

				int index = 0;
				for ( int i = 0; i < folders.length; i++ ) {

						if ( folders[i].compareTo( folders[index] ) >= 0 ) {
								index = i;
						}
				}

				return folders[index];
		}

		public static String uploadFile( MultipartFile multiPartfile ) {
				return uploadFile( multiPartfile, StorageFileUIDGenerator.generateUID() );
		}

		public static File getThumnailFile( String storageFileUid ) {
				return getThumnailFile( storageFileUid, "" );
		}

		public static File getThumnailFile( String storageFileUid, String thumbnailSize ) {
				if ( StringUtils.isEmpty( storageFileUid ) )
						return null;

				String filePath = findFilePath( SERVER_UPLOAD_FILE_THUMBNAIL_PATH, storageFileUid );

				File target = new File( filePath );

				logger.debug( "썸네일 파일 위치 : " + target.getAbsolutePath() );

				return target.isFile() ? target : null;
		}

		/**
		 * 실시간으로 파일 삭제.
		 *
		 * @param storageFileUid
		 */
		public static void remove( String storageFileUid ) {
				File storageFile = StorageUtils.getStorageFile( storageFileUid );
				if ( storageFile != null ) {
						storageFile.delete();
				}

				File thumbnail = StorageUtils.getThumnailFile( storageFileUid );
				if ( thumbnail != null ) {
						thumbnail.delete();
				}
		}

		/**
		 * 실시간 파일 삭제 (리스트)
		 * @param storageFileUidList
		 */
		public static void removeList( List<String> storageFileUidList ) {
				if ( CollectionUtils.isEmpty( storageFileUidList ) ) {
						return;
				}
				for ( String storageFileUid : storageFileUidList ) {
						remove( storageFileUid );
				}
		}

		private static String findFilePath( String serverUploadFilePath, String storageFileUid ) {

				String year = storageFileUid.substring( 0, 4 );
				String month = storageFileUid.substring( 4, 6 );
				String day = storageFileUid.substring( 6, 8 );

				String firstFolder = storageFileUid.substring( 8, 12 );
				String secondFolder = storageFileUid.substring( 12, 16 );

				String filePath = serverUploadFilePath + File.separator + year + File.separator + month + File.separator + day;
				filePath += File.separatorChar + firstFolder + File.separatorChar + secondFolder + File.separator + storageFileUid + STORAGE_FILE_EXTENDTION;

				return filePath;
		}

		public static File getStorageFile( String storageFileUid ) {
				if ( StringUtils.isEmpty( storageFileUid ) )
						return null;

				String filePath = findFilePath( SERVER_UPLOAD_FILE_PATH, storageFileUid );
				
				log.trace( "filePath1 : " + filePath );

				File target = new File( filePath );
				
				log.trace( "target.isFile()  : " + target.isFile()  );

				return target.isFile() ? target : null;
		}
		
		
		/**
		 *  storageFileUid로 파일을 찾아서 fileName으로 copy해서 파일을 반환
		 *  
		 */
		public static File getStorageFileWithCopy( String storageFileUid, String fileName ) {
				
				if ( StringUtils.isEmpty( storageFileUid ) )
						return null;

				String filePath = findFilePath( SERVER_UPLOAD_FILE_PATH, storageFileUid );

				File target = new File( filePath );
				
				if ( !target.isFile()) {
						
						return null;
				}
				
				File newFile = null;
				
				try {
						newFile = new File( SERVER_UPLOAD_FILE_TEMP_PATH + File.separator + fileName);
						FileUtils.copyFile( target, newFile );
				}
				catch ( IOException e ) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				
				return newFile;
		}

		public static void copyFile( String srcStorageFileUid, String destStorageFileUid ) {

				if ( StringUtils.isEmpty( srcStorageFileUid ) || StringUtils.isEmpty( destStorageFileUid ) )
						return;

				String orgFilePath = findFilePath( SERVER_UPLOAD_FILE_PATH, srcStorageFileUid );
				String targetFilePath = findFilePath( SERVER_UPLOAD_FILE_PATH, destStorageFileUid );

				File srcFile = new File( orgFilePath );
				File destFile = new File( targetFilePath );

				try {
						FileUtils.copyFile( srcFile, destFile );
				}
				catch ( IOException e ) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}

		}
		

		public static String getStorageFilePath( String storageFileUid ) {
				if ( StringUtils.isEmpty( storageFileUid ) )
						return "";

				File result = getStorageFile( storageFileUid );
				if ( result == null )
						return "";

				return result.getPath();
		}

		/**
		 * file을 스토리지에 등록하고 StorageFileUid를 반환
		 * 
		 * @author james
		 * @param file
		 * @param isOrgFileMove
		 * @return
		 */
		public static String uploadFile( File file, boolean isOrgFileMove ) {

				return uploadFile( file, StorageFileUIDGenerator.generateUID(), isOrgFileMove );
		}
		
		
		/**
		 * file을 스토리지에 등록하고 FileInfo객체로 반환
		 * 
		 * @author james
		 * @param file
		 * @param isOrgFileMove
		 * @param targetObject
		 * @param targetOid
		 * @param fileType
		 * @return
		 */
		public static FileInfo uploadFile( File file, boolean isOrgFileMove, String targetObject, String targetOid, String fileType ) {

				if ( file == null) {
						return null;
				}
				
				String storageFileUid = uploadFile( file, isOrgFileMove);
				
				if ( StringUtils.isEmpty( storageFileUid )) {
						return null;
				}
				
				FileInfo fileInfo = SystemFactory.getFileInfo();
				fileInfo.setFileName( StorageUtils.getFileOrgName( file.getName() ) );
				fileInfo.setFileExt( StorageUtils.getFileExt( file.getName() ) );
				fileInfo.setFileSize( file.length() );
				fileInfo.setTargetObject( targetObject );
				fileInfo.setTargetOid( targetOid );
				fileInfo.setFileType( fileType );
				fileInfo.setStorageFileUid( storageFileUid );
				
				return fileInfo;
		}
		
		/**
		 * 여러파일을 스토리지에 등록하고 FileInfo 리스트를 반환
		 * 
		 * @author james
		 * @param fileList
		 * @param isOrgFileMove
		 * @param targetObject
		 * @param targetOid
		 * @param fileType
		 * @return
		 */
		public static List<FileInfo> uploadFile( List<File> fileList, boolean isOrgFileMove, String targetObject, String targetOid, String fileType, String containerOid ) {

				if ( CollectionUtils.isEmpty( fileList )) {
						return null;
				}
				
				
				List<FileInfo> resultList = new LinkedList<>();
				
				fileList.forEach( file -> {
						
						String storageFileUid = uploadFile( file, isOrgFileMove);
						
						if ( StringUtils.isNotEmpty( storageFileUid )) {
								
								FileInfo fileInfo = SystemFactory.getFileInfo();
								fileInfo.setFileName( StorageUtils.getFileOrgName( file.getName() ) );
								fileInfo.setFileExt( StorageUtils.getFileExt( file.getName() ) );
								fileInfo.setFileSize( file.length() );
								fileInfo.setTargetObject( targetObject );
								fileInfo.setTargetOid( targetOid );
								fileInfo.setFileType( fileType );
								fileInfo.setContainerOid( containerOid );
								fileInfo.setStorageFileUid( storageFileUid );
								
								resultList.add( fileInfo );
						}
						

				});
				
				return resultList;
		}
		

		public static String uploadFile( File file, String storageFileUid, boolean isOrgFileMove ) {

				if ( file == null )
						return "";

				File tempFile = makeTempFile( storageFileUid );

				if ( tempFile == null )
						return "";

				try {

						FileUtils.copyFile( file, tempFile );

						if ( isOrgFileMove ) {
								file.delete();;
						}

				}
				catch ( Exception ex ) {
						ex.printStackTrace();
						return "";
				}

				return storageFileUid;
		}

		public static String uploadFile( MultipartFile multiPartfile, String storageFileUid ) {

				if ( multiPartfile == null )
						return "";
				
				File tempFile = makeTempFile( storageFileUid );
				
				if ( tempFile == null )
						return "";

				
				try {

						// jeus 서버에서는 이상하게 동작하여 별도의 메소드로 호출처리.
//						multiPartfile.transferTo( tempFile );
						FileCopyUtils.copy(multiPartfile.getBytes(), tempFile);

				}
				catch ( Exception ex ) {

						ex.printStackTrace();
						return ex.getMessage();
				}

				return storageFileUid;
		}

		public static boolean isStl( String fileExt ) {

				return "stl".equalsIgnoreCase( fileExt );
		}

		public static boolean isImage( MultipartFile multipartfile ) {

				if ( multipartfile == null )
						return false;

				String ext = getFileExt( multipartfile.getOriginalFilename() );

				return isImage( ext );
		}

		public static boolean isImage( String fileExt ) {

				if ( "jpg".equalsIgnoreCase( fileExt ) || "jpeg".equalsIgnoreCase( fileExt ) || "png".equalsIgnoreCase( fileExt ) || "gif".equalsIgnoreCase( fileExt ) || "bmp".equalsIgnoreCase( fileExt ) || "eps".equalsIgnoreCase( fileExt ) || "pict".equalsIgnoreCase( fileExt ) || "tiff".equalsIgnoreCase( fileExt ) ) {
						return true;
				}

				return false;
		}

		public static File makeTempFile( String storageFileUid ) {
				return makeTempFile( SERVER_UPLOAD_FILE_PATH, storageFileUid );
		}

		public static File makeTempFile( String root, String storageFileUid ) {

				if ( root == null || storageFileUid == null || storageFileUid.length() != 20 )
						return null;

				String year = storageFileUid.substring( 0, 4 );
				String month = storageFileUid.substring( 4, 6 );
				String day = storageFileUid.substring( 6, 8 );

				String firstFolder = storageFileUid.substring( 8, 12 );
				String secondFolder = storageFileUid.substring( 12, 16 );
				boolean result = false;
				File file = null;

				String filePath = root + File.separator + year + File.separator + month + File.separator + day;
				filePath += File.separatorChar + firstFolder + File.separatorChar + secondFolder;

				try {
						FileUtils.forceMkdir( new File( filePath ) );

						file = new File( filePath + File.separator + storageFileUid + STORAGE_FILE_EXTENDTION );
						
						result = file.createNewFile();

						if ( !result ) {
								file.delete();
								result = file.createNewFile();
						}

				}
				catch ( IOException e ) {
						e.printStackTrace();
				}

				return result ? file : null;
		}

		public static String getFileExt( String fileName ) {

				if ( fileName == null || fileName.length() <= 0 )
						return "";

				if ( fileName.lastIndexOf( "." ) <= 0 )
						return "";

				String ext = fileName.substring( fileName.lastIndexOf( "." ) + 1, fileName.length() );

				return ext;
		}

		public static String getFileOrgName( String fileName ) {

				if ( fileName == null || fileName.length() <= 0 )
						return "";

				if ( fileName.lastIndexOf( "." ) <= 0 )
						return "";

				String fileOriginName = fileName.substring( 0, fileName.lastIndexOf( "." ) );

				return fileOriginName;
		}

		/**
		 * <pre>
		 * 해당 파일의 hash값을 구한다
		 * EncryptUtils.calculateMD5 을 이용한다. 32byte의 문자열을 반환
		 * 100 메가 기준
		 * sha256 : 1856
		 * md5 :     651
		 *
		 * </pre>
		 *
		 * @author james
		 * @param fileName
		 * @return
		 */
		public static String getFileHash( byte[] bytes ) {

				String result = "";
				try {
						result = EncryptUtils.calculateMD5( bytes );
				}
				catch ( Exception e ) {
						e.printStackTrace();
				}

				return result;
		}
		

		/**
		 * 파일 다운로드 Url을 생성
		 * 
		 * @author james
		 * @param FileName
		 * @param storageFileUid
		 * @return
		 */
		public static String makeDownloadUrl( String FileName, String storageFileUid ) {

				return "/storage/storageFile_fileDown/" + FileName + "/" + storageFileUid;
		}

		/**
		 * 썸네일 Url을 생성
		 * ex) "/thumbnail_view/" + data.file.storageFileUid + "/" + data.file.fileExt + "/100_100"/
		 * 
		 * @author james
		 * @param storageFileUid
		 * @param fileExt
		 * @param thumbSize
		 * @return
		 */
		public static String makeThumbnailUrl( String storageFileUid, String fileExt, String thumbSize ) {

				if ( StringUtils.isEmpty(  thumbSize )) {
						thumbSize = "100_100";
				}
				
				return "/thumbnail_view/" +  storageFileUid + "/" +  fileExt + "/" + thumbSize;
		}


		
		/**
		 * 파일보기 Url을 생성
		 * 
		 * @author james
		 * @param storageFileUid
		 * @return
		 */
		public static String makeFileView( String storageFileUid ) {

				return  "/storage/storageFile_fileView/" +  storageFileUid;
		}

		/**
		 * fileList에서 스토리지 아이디만 추출
		 * 
		 * @author james
		 * @param fileList
		 * @return
		 */
		public static List<String> getStroageFileUidList( List<FileInfo> fileList ) {
		
				if ( CollectionUtils.isEmpty( fileList )) {
						return null;
				}
				
				return fileList.stream().map( FileInfo::getStorageFileUid ).collect( Collectors.toList() );
		}
		
		/**
		 * fileList에서 확장자만 추출
		 * 
		 * @author woong
		 * @param fileList
		 * @return
		 */
		public static List<String> getFileExtList( List<FileInfo> fileList ) {
		
				if ( CollectionUtils.isEmpty( fileList )) {
						return null;
				}
				
				return fileList.stream().map( FileInfo::getFileExt ).collect( Collectors.toList() );
		}
		
}