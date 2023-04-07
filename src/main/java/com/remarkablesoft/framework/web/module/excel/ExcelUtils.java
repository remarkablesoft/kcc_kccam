package com.remarkablesoft.framework.web.module.excel;

import static com.remarkablesoft.framework.web.module.excel.ExcelView.PARAM_FILENAME;
import static com.remarkablesoft.framework.web.module.excel.ExcelView.PARAM_FILEPATH;
import static com.remarkablesoft.framework.web.module.excel.ExcelView.PARAM_HEADER;
import static com.remarkablesoft.framework.web.module.excel.ExcelView.PARAM_HEADER_DELIMITERS;
import static com.remarkablesoft.framework.web.module.excel.ExcelView.PARAM_SHEETNAME;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 		@주시스템			:	Refinish
 * 		@서브 시스템			:	manager
 * 		@프로그램 ID		:	ExcelUtils.java
 * 		@프로그램 개요 		:	엑셀의 유틸클래스
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2017. 11. 25.	:	이균환	-	신규생성
 * 		============================================================================
 */
@WEBLog
public class ExcelUtils {

		private static final Logger logger = LoggerFactory.getLogger( "EXCEL" );
	
		public static List<String> checkHeader( Map model ) {

				List<String> displayHeaderList = null;

				String displayHeader = (String) model.get( PARAM_HEADER );

				//System.out.println( "displayHeader : " + displayHeader);
				if ( StringUtils.hasText( displayHeader ) ) {
						displayHeaderList = Arrays.asList( displayHeader.split( PARAM_HEADER_DELIMITERS ) );
				}

				return displayHeaderList;
		}

		/**
		 * Response Header 셋팅
		 *
		 * @param req
		 * @param res
		 * @param fileName
		 * @throws UnsupportedEncodingException
		 */
		public static void setResponseHeader( HttpServletRequest req, HttpServletResponse res, String fileName ) throws UnsupportedEncodingException {

				String userAgent = req.getHeader( "User-Agent" );

				if ( userAgent.indexOf( "MSIE" ) > -1 ) {
						fileName = URLEncoder.encode( fileName, "utf-8" );
				}
				else {
						fileName = new String( fileName.getBytes( "utf-8" ), "iso-8859-1" );
				}

				res.setHeader( "Content-Disposition", "attachment; filename=\"" + fileName + "\";" );
				res.setHeader( "Content-Transfer-Encoding", "binary" );
		}

		/**
		 * 시트이름을 체크한다.
		 *
		 * @param sheetName
		 * @param excelMap
		 */
		public static String checkSheetName( Map modal ) {

				String sheetName = (String) modal.get( PARAM_SHEETNAME );
				if ( !StringUtils.hasText( sheetName ) ) {
						sheetName = "sheet1";
				}

				return sheetName;
		}

		/**
		 * 파일이름을 체크한다.
		 *
		 * @param fileName
		 * @param modeal
		 */
		public static String checkFileName( Map modeal ) {

				String fileName = (String) modeal.get( PARAM_FILENAME );

				if ( !StringUtils.hasText( fileName ) ) {
						fileName = "excel_list.xlsx";
				}
				else {
						// 엑셀확장자가 빠져있다면 넣어준다.
						if ( fileName.indexOf( ".xlsx" ) < 0 ) {
								fileName += ".xlsx";
						}
				}

				return fileName;
		}

		public static SXSSFSheet createFirstSheet( SXSSFWorkbook workbook, String sheetName ) {
				SXSSFSheet sheet = workbook.createSheet();
				workbook.setSheetName( 0, sheetName );
				//				sheet.setColumnWidth( 1, 256 * 30 );
				return sheet;
		}

		public static void createDisplayHeader( SXSSFWorkbook workbook, SXSSFSheet sheet, List<String> columnHeader ) {
				SXSSFRow firstRow = sheet.createRow( 0 );

				SXSSFCell cell = null;
				CellStyle headerStyle = getHeaderStyle( workbook );

				int nRow = 0;

				for ( String column : columnHeader ) {
						cell = firstRow.createCell( nRow++ );
						cell.setCellValue( column );
						cell.setCellStyle( headerStyle );
				}

		}

		public static void createPageRow( SXSSFSheet sheet, Map<String, String> map, int rowNum ) {

				SXSSFRow row = sheet.createRow( rowNum + 1 );
				SXSSFCell cell = null;

				int nRow = 0;
				for ( Map.Entry<String, String> entry : map.entrySet() ) {
						cell = row.createCell( nRow++ );
						cell.setCellValue( entry.getValue() );
				}
		}

		/**
		 * Cell Style을 설정한다.
		 * 참고 : http://thinktibits.blogspot.kr/2012/12/excel-cell-fill-color-java-poi-example.html
		 *
		 * @param workbook
		 * @return
		 */
		public static CellStyle getHeaderStyle( SXSSFWorkbook workbook ) {

				CellStyle style = workbook.createCellStyle();

				style.setFillForegroundColor( new HSSFColor.GREY_25_PERCENT().getIndex() );
				style.setFillPattern( HSSFCellStyle.FINE_DOTS );
				style.setFillPattern( CellStyle.SOLID_FOREGROUND );

				Font font = workbook.createFont();
				font.setColor( IndexedColors.BLACK.getIndex() );
				font.setBoldweight( HSSFFont.BOLDWEIGHT_BOLD );
				font.setFontName( "맑은고딕" );
				style.setFont( font );

				return style;
		}

		/**
		 * 파일경로를 체크한다.
		 *
		 * @param fileName
		 * @param modeal
		 */
		public static String checkFilePath( Map modeal ) {

				String filePath = (String) modeal.get( PARAM_FILEPATH );

				if ( StringUtils.isEmpty( filePath ) ) {
						return "";
				}

				return filePath;

		}
		
		/**
		 * 엑셀 파일 정보를 받아서 JSON형태로 반환한다.
		 * 
		 * @param file
		 * @return
		 */
		public static String excelDataExtractToJson( File file ) {
						
			String filePath = file.getAbsolutePath();
			BufferedReader br = null;
			String line;
			String delimeter = ",";	

			List<String> headerList = new ArrayList<>();
			List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
			
			try {
					FileInputStream input = new FileInputStream( filePath );
					InputStreamReader reader = new InputStreamReader( input, "euc-kr" );				
					br = new BufferedReader( reader );
					
					int index = 0;
					while ( ( line = br.readLine() ) != null ) {
							String[] field = line.split( delimeter, -1 );
							if ( field.length > 0 ) {
																
								HashMap<String,Object> map = new HashMap<>();
								int cellIndex = 0;
								
								for( String cell : field ) {
									
									if( index == 0 ) {
				                		
				                		headerList.add( cell );
				                	}
									else {
										
										map.put( headerList.get(cellIndex), cell );									
									}																		
									cellIndex++;
								}
								
								if( !map.isEmpty() ) {
									list.add( map );	
								}
								index++;
							}
					}

			}
			catch ( FileNotFoundException e ) {
					e.printStackTrace();
					logger.debug( "FileNotFoundException : " + e.getMessage() );
			}
			catch ( IOException e ) {
					e.printStackTrace();
					logger.debug( "FileNotFoundException : " + e.getMessage() );
			}
			finally {
					if ( br != null ) {
							try {
									br.close();									
							}
							catch ( IOException e ) {
									e.printStackTrace();
							}
					}
			}
			
			
			JSONArray jsonArray = new JSONArray();

			for (Map<String, Object> map : list) {

				jsonArray.add(convertMapToJson(map));
			}

			return jsonArray.toString();
		}
		
		public static JSONObject convertMapToJson(Map<String, Object> map) {

			JSONObject json = new JSONObject();

			for (Map.Entry<String, Object> entry : map.entrySet()) {

				String key = entry.getKey();
				Object value = entry.getValue();

				json.put(key, value);
			}

			return json;
		}


}