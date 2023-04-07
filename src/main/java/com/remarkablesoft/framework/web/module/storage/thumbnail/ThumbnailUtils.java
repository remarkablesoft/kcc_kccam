package com.remarkablesoft.framework.web.module.storage.thumbnail;

import com.remarkablesoft.framework.util.ApplicationPropertiesUtils;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.filters.Canvas;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.apache.sanselan.ImageReadException;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.jcodec.scale.ColorUtil;
import org.jcodec.scale.Transform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 설명 : 썸네일 Utils
 *
 * 썸네일 이미지 파일은 Thumbnails을 이용 참고) https://code.google.com/p/thumbnailator/wiki/Examples
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2016. 11. 23.	:	james		-	static의 util클래스가 아닌 스프링의 싱글톤으로 사용하도록 변경
 * 											비동기로 호출하다보니 static으로 호출하면 여러문제가 발생하여 변경처리.
 * 		1.1  2020. 11. 30.	:	james		-	스토리지에 썸네일 사이즈가 먼저나오도록 처리
 * 											ex)  D:\data\ebook\storage\thumbnail\200_200\2020\11\29\0000\0000\20201129000000000005
 * 		============================================================================
 *
 * @author james
 * @since 2016. 7. 08.
 *
 */
@Component
public class ThumbnailUtils {

		private static final Logger logger = LoggerFactory.getLogger( "STORAGE" );

		private static String fileRootPath = ApplicationPropertiesUtils.getValue( "server.upload.thumbnail.path" );

		public static final String FILE_EXT_PDF = "pdf";
		public static final String FILE_EXT_VIDEO = "mp4";
		public static final String SPLIT_CHAR = "_";

		public static final int MP4_THUMB_SECOND = 1; 			// 동영상 캡쳐할 썸네일 시간

		public static boolean isImage( String fileExt ) {

				if ( StringUtils.isEmpty( fileExt ) ) {
						return false;
				}

				fileExt = fileExt.toLowerCase();

				if ( "jpg".equals( fileExt ) || "jpeg".equals( fileExt ) || "png".equals( fileExt ) || "gif".equals( fileExt ) ) {
						return true;
				}

				return false;
		}

		/**
		 * @param file
		 * @param fileExt
		 * @param thumSize
		 * @param isCrop
		 * @return
		 * @throws Exception
		 */
		public File makeThumbnail( File file, String fileExt, String thumSize, boolean isCrop ) throws Exception {

				fileExt = fileExt.toLowerCase();

				String orgFilePath = file.getAbsolutePath();

				File orgFile = new File( orgFilePath );
				if ( orgFile == null || !orgFile.isFile() ) {
						return null;
				}

				String realThumbnailFilePath = getRealThumbNailFilePath( file, thumSize );
				File thumbnail = null;

				if ( FILE_EXT_PDF.equals( fileExt ) ) {

						thumbnail = makeTempFile( realThumbnailFilePath );
						pdfToThumbnail( orgFilePath, thumbnail, thumSize, isCrop );

						// 임시파일 삭제처리
						//orgFile.delete();
				}
				else if ( FILE_EXT_VIDEO.equals( fileExt ) ) {

						// 동영상 썸네일 위치는 어디에 둘것인가?
						// 일단 이것도 external 에 두기로 한다.
						thumbnail = makeTempFile( realThumbnailFilePath );
						mp4ToThumbnail( orgFilePath, thumbnail, thumSize, isCrop );

						// 임시파일 삭제처리
						//orgFile.delete();
				}
				else if ( isImage( fileExt ) ) {

						thumbnail = makeTempFile( realThumbnailFilePath );

						try {
								imageToThumbnailSimple( orgFilePath, thumbnail, thumSize, isCrop );
						}
						catch ( Exception e ) {

								//e.printStackTrace();
								logger.error( e.getMessage() );
								// 썸네일 만들다가 오류가 났는데 만약 jpg 이미지라면 원본을 보여준다.
								thumbnail = orgFile;
						}
				}

				return thumbnail;

		}



		/**
		 * 이미지파일을 썸네일로 변환
		 *
		 * @param orgFilePath
		 * @param filePath
		 * @param fileName
		 * @param thumSize
		 * @return
		 * @throws IOException
		 * @throws ImageReadException
		 */
		public void imageToThumbnailSimple( String orgFilePath, File thumbnail, String thumSize, boolean isCrop ) throws Exception {

				String[] arrSize = thumSize.split( "_" );
				int width = Integer.parseInt( arrSize[0] );
				int height = Integer.parseInt( arrSize[1] );

				File targetFile = new File( orgFilePath );

				if ( targetFile == null || !targetFile.isFile() || !targetFile.canWrite() ) {
						return;
				}

				// 크롭시에는 위에서부터 자르도록
				if ( isCrop ) {
						Thumbnails.of( orgFilePath ).crop( Positions.TOP_CENTER ).size( width, height ).toFile( thumbnail );
				}
				else {
						// 전체는 공백화면 안나오도록 처리.
						//Thumbnails.of( orgFilePath ).size( width, height ).addFilter( new Canvas( width, height, Positions.CENTER, Color.WHITE ) ).toFile( thumbnail );
						Thumbnails.of( orgFilePath ).size( width, height ).toFile( thumbnail );
				}

		}
		
		
		/**
		 * pdf파일을 썸네일로 변환
		 *
		 * @param orgFilePath
		 * @param filePath
		 * @param fileName
		 * @param thumSize
		 * @return
		 * @throws IOException
		 * @throws ImageReadException
		 */
		public void pdfToThumbnail( String orgFilePath, File thumbnail, String thumSize, boolean isCrop ) {

				PDDocument document = null;
				try {
						document = PDDocument.load( new File( orgFilePath ) );

						PDFRenderer pdfRenderer = new PDFRenderer( document );

						//				System.out.println( "전체페이지 수 : " + document.getNumberOfPages() );

						// 첫페이지만 썸네일로 변환
						BufferedImage bim = pdfRenderer.renderImageWithDPI( 0, 100, ImageType.RGB );

						// suffix in filename will be used as the file format
						ImageIOUtil.writeImage( bim, thumbnail.getAbsolutePath(), 100 );
						imageToThumbnail( thumbnail.getAbsolutePath(), thumbnail, thumSize, isCrop );
						document.close();

				}
				catch ( Exception e ) {
						//						e.printStackTrace();
						logger.error( e.getMessage() );
				}
				finally {
						if ( document != null ) {
								try {
										document.close();
								}
								catch ( IOException e ) {
								}
						}
				}

		}
		
		
		/**
		 * mp4파일을 썸네일로 변환
		 *
		 * @param orgFilePath
		 * @param filePath
		 * @param fileName
		 * @param thumSize
		 * @return
		 * @throws IOException
		 * @throws ImageReadException
		 */
		public void mp4ToThumbnail( String orgFilePath, File thumbnail, String thumSize, boolean isCrop ) throws Exception {

				Picture frame = null;
				try {
						frame = FrameGrab.getNativeFrame( new File( orgFilePath ), MP4_THUMB_SECOND );
				}
				catch ( JCodecException e ) {
						//						e.printStackTrace();
						logger.error( e.getMessage() );
						return;
				}

				Transform transform = ColorUtil.getTransform( frame.getColor(), ColorSpace.RGB );
				Picture rgb = Picture.create( frame.getWidth(), frame.getHeight(), ColorSpace.RGB );
				transform.transform( frame, rgb );
				BufferedImage bi = AWTUtil.toBufferedImage( rgb );
				ImageIO.write( bi, "png", thumbnail );

				imageToThumbnail( thumbnail.getAbsolutePath(), thumbnail, thumSize, isCrop );

		}

		/**
		 * 이미지파일을 썸네일로 변환
		 *
		 * @param orgFilePath
		 * @param filePath
		 * @param fileName
		 * @param thumSize
		 * @return
		 * @throws IOException
		 * @throws ImageReadException
		 */
		public void imageToThumbnail( String orgFilePath, File thumbnail, String thumSize, boolean isCrop ) throws Exception {

				String[] arrSize = thumSize.split( "_" );
				int width = Integer.parseInt( arrSize[0] );
				int height = Integer.parseInt( arrSize[1] );

				BufferedImage bfImage = null;

				File targetFile = new File( orgFilePath );

				if ( targetFile == null || !targetFile.isFile() || !targetFile.canWrite() ) {
						return;
				}

				CustomImageReader imageReader = new CustomImageReader();

				// 특정 jpg에서 안되는 문제 해결하기 위해
				BufferedImage image = imageReader.readImage( targetFile );

				if ( isCrop ) {
						bfImage = Thumbnails.of( image ).crop( Positions.CENTER ).size( width, height ).asBufferedImage();
				}
				else {
						bfImage = Thumbnails.of( image ).size( width, height ).addFilter( new Canvas( width, height, Positions.CENTER, Color.WHITE ) ).asBufferedImage();
				}

				if ( thumbnail == null || !thumbnail.isFile() || !thumbnail.canWrite() ) {
						return;
				}

				ImageIO.write( bfImage, "png", thumbnail );
		}

		/**
		 *
		 * @param file
		 * @param thumSize
		 * @return
		 */
		public String getRealThumbNailFilePath( File file, String thumSize ) throws Exception {

				String realThumbnailFilePath = "";

				String fileName = FilenameUtils.getBaseName( file.getName() );
				fileName = fileName + ".png";
				String filePath = file.getParent();

				String[] pathArr = StringUtils.tokenizeToStringArray( filePath, File.separator );
				int pathArrLength = pathArr.length;

				String Year = pathArr[pathArrLength - 5];
				String Month = pathArr[pathArrLength - 4];
				String Day = pathArr[pathArrLength - 3];
				String firstPad = pathArr[pathArrLength - 2];
				String secondPad = pathArr[pathArrLength - 1];

				realThumbnailFilePath = fileRootPath + File.separator + thumSize + File.separator + Year + File.separator + Month + File.separator + Day + File.separator + firstPad + File.separator + secondPad + File.separator + fileName;

				return realThumbnailFilePath;
		}
		
	

		private File makeTempFile( String realThumbnailFilePath ) {

				File thumbnail = new File( realThumbnailFilePath );
				String parent = thumbnail.getParent();
				try {
						FileUtils.forceMkdir( new File( parent ) );
						thumbnail.createNewFile();
				}
				catch ( IOException e ) {
						e.printStackTrace();
				}
				return thumbnail;
		}

}