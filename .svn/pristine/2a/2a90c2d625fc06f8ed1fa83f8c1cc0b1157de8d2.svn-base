package com.remarkablesoft.framework.web.module.excel;

import static com.remarkablesoft.framework.web.module.excel.ExcelMultiSheetView.PARAM_AUTO_SIZING_COL;
import static com.remarkablesoft.framework.web.module.excel.ExcelMultiSheetView.PARAM_FILENAME;
import static com.remarkablesoft.framework.web.module.excel.ExcelMultiSheetView.PARAM_FILEPATH;
import static com.remarkablesoft.framework.web.module.excel.ExcelMultiSheetView.PARAM_HEADER;
import static com.remarkablesoft.framework.web.module.excel.ExcelMultiSheetView.PARAM_HEADER_DELIMITERS;
import static com.remarkablesoft.framework.web.module.excel.ExcelMultiSheetView.PARAM_MERGE_ADDRESS;
import static com.remarkablesoft.framework.web.module.excel.ExcelMultiSheetView.PARAM_MERGE_CELL;
import static com.remarkablesoft.framework.web.module.excel.ExcelMultiSheetView.PARAM_NUM_HEADER;
import static com.remarkablesoft.framework.web.module.excel.ExcelMultiSheetView.PARAM_SHEETNAME;
import static com.remarkablesoft.framework.web.module.excel.ExcelMultiSheetView.PARAM_START_ROW;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.util.StringUtils;

/**
 * 		@주시스템			:	Refinish
 * 		@서브 시스템			:	manager
 * 		@프로그램 ID		:	ExcelUtils.java
 * 		@프로그램 개요 		:	엑셀의 유틸클래스
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2017. 11. 25.	:	이균환	-	신규생성
 * 		1.1  2018. 12. 06.  :	최원준	-	엑셀 다중 sheet, merge 기능 추가
 * 		============================================================================
 */
@SuppressWarnings( "unchecked" )
public class ExcelMultiSheetUtils {

		public static List<String> checkHeader( Map<String, Object> model ) {

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

				if ( StringUtils.isEmpty( userAgent ) || userAgent.indexOf( "MSIE" ) > -1 ) {
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
		public static List<String> checkSheetName( Map<String, Object> model ) {

				List<String> sheetNameList = (List<String>) model.get( PARAM_SHEETNAME );

				int index = 0;
				for( String sheetName : sheetNameList ) {
						if ( !StringUtils.hasText( sheetName ) ) {
								index++;
								sheetName = "sheet" + String.valueOf( index );
						}
				}

				return sheetNameList;
		}

		/**
		 * 파일이름을 체크한다.
		 *
		 * @param fileName
		 * @param modeal
		 */
		public static String checkFileName( Map<String, Object> model ) {

				String fileName = (String) model.get( PARAM_FILENAME );

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

		/**
		 * 셀 병합정보를 체크합니다.
		 * @param model
		 * @return
		 */
		public static Map<String, Object> checkMergeMap( Map<String, Object> model ) {

				Map<String, Object> mergeMap = new HashMap<>();
				List<int[]> mergeAddressList = (List<int[]>) model.get( PARAM_MERGE_ADDRESS );
				List<String> cellValueList = (List<String>) model.get( PARAM_MERGE_CELL );

				if( mergeAddressList != null && cellValueList != null ) {
						mergeMap.put( PARAM_MERGE_ADDRESS, mergeAddressList );
						mergeMap.put( PARAM_MERGE_CELL, cellValueList );
				}

				return mergeMap;
		}

		/**
		 * 엑셀 헤더와 데이터가 시작되는 Row를 체크합니다.
		 * @param model
		 * @return
		 */
		public static int checkStartRow( Map<String, Object> model ) {
				int startRow = 0;
				if( model.get( PARAM_START_ROW ) != null ) {
						startRow = ( int )model.get( PARAM_START_ROW );
				}
				return startRow;
		}

		/**
		 * 셀 크기 자동조절 컬럼 내용 체크
		 * @param model
		 * @return
		 */
		public static List<Integer> checkAutoSizingList( Map<String, Object> model ) {
				List<Integer> autoSizingList = (List<Integer>)model.get( PARAM_AUTO_SIZING_COL );

				if( autoSizingList == null ) {
						autoSizingList = new ArrayList<>();
				}

				return autoSizingList;
		}

		public static List<String> checkNumHeader( Map<String, Object> model ) {
				List<String> numHeaderList = (List<String>)model.get( PARAM_NUM_HEADER );
				if( numHeaderList == null ) {
						numHeaderList = new ArrayList<>();
				}

				return numHeaderList;
		}

		/**
		 * 시트 만들기
		 * @param workbook
		 * @param sheetNameList
		 * @return
		 */
		public static List<SXSSFSheet> createSheet( SXSSFWorkbook workbook, List<String> sheetNameList ) {

				List<SXSSFSheet> sheetList = new ArrayList<>();
				int index = 0;
				for( String sheetName : sheetNameList ) {
						SXSSFSheet sheet = workbook.createSheet();
						workbook.setSheetName( index, sheetName );
						//				sheet.setColumnWidth( 1, 256 * 30 );
						sheetList.add( sheet );
						index++;
				}

				return sheetList;
		}

		/**
		 * 셀 병합
		 * @param sheetList
		 * @param mergeMap
		 */
		public static void mergeCell( SXSSFWorkbook workbook, List<SXSSFSheet> sheetList, Map<String, Object> mergeMap ) {

				if( mergeMap.size() == 0 ) {
						return;
				}

				CellStyle style = workbook.createCellStyle();
				int index = 0;
				for( SXSSFSheet sheet : sheetList ) {
						List<int[]> mergeAddressList = (List<int[]>) mergeMap.get( PARAM_MERGE_ADDRESS );
						List<String> cellValueList = (List<String>) mergeMap.get( PARAM_MERGE_CELL );

						SXSSFRow mergeRow = null;
						for( int i = 0; i<mergeAddressList.size(); i++ ) {
								if( mergeAddressList.get( i )[0] == index ) {
										CellRangeAddress mergeCellAddress = new CellRangeAddress( mergeAddressList.get( i )[1], mergeAddressList.get( i )[2], mergeAddressList.get( i )[3], mergeAddressList.get( i )[4] );
										sheet.addMergedRegion( mergeCellAddress );

										if( i == 0 ) {
												mergeRow = sheet.createRow( mergeAddressList.get( i )[1] );
										}
										else {
												if( mergeAddressList.get( i - 1 )[1] != mergeAddressList.get( i )[1] ) {
														mergeRow = sheet.createRow( mergeAddressList.get( i )[1] );
												}
										}

										SXSSFCell mergeCell = mergeRow.createCell( mergeAddressList.get( i )[3] );
										mergeCell.setCellValue( cellValueList.get( i ) );
										RegionUtil.setBorderTop( BorderStyle.THIN, mergeCellAddress, sheet );
										RegionUtil.setBorderBottom( BorderStyle.THIN, mergeCellAddress, sheet );
										RegionUtil.setBorderRight( BorderStyle.THIN, mergeCellAddress, sheet );
										RegionUtil.setBorderLeft( BorderStyle.THIN, mergeCellAddress, sheet );
										style.setAlignment( HorizontalAlignment.CENTER );
										getBasicStyle( style );
										mergeCell.setCellStyle( style );
								}
						}
						index++;
				}
		}

		/**
		 * 엑셀 데이터 헤더를 만듭니다.
		 * @param workbook
		 * @param sheetList
		 * @param columnHeader
		 * @param startRow
		 */
		public static void createDisplayHeader( SXSSFWorkbook workbook, List<SXSSFSheet> sheetList, List<String> columnHeader, int startRow ) {

				CellStyle headerStyle = getHeaderStyle( workbook );

				for( SXSSFSheet sheet : sheetList ) {
						SXSSFRow firstRow = sheet.createRow( startRow );

						SXSSFCell cell = null;

						int nRow = 0;

						for ( String column : columnHeader ) {
								cell = firstRow.createCell( nRow++ );
								cell.setCellValue( column );
								cell.setCellStyle( headerStyle );
						}
				}

		}

		/**
		 * 엑셀 데이터를 나타내고 마지막줄을 강조합니다.
		 * @param workbook
		 * @param sheetList
		 * @param map
		 * @param rowNum
		 * @param bLastRowStyleYn
		 */
		public static void createPageRow( SXSSFWorkbook workbook, List<SXSSFSheet> sheetList, Map<String, String> map, int rowNum, boolean bLastRowStyleYn, List<Integer> autoSizingList ) {

				CellStyle style = workbook.createCellStyle();

				for( SXSSFSheet sheet : sheetList ) {
						SXSSFRow row = sheet.createRow( rowNum + 1 );
						SXSSFCell cell = null;

						for( Integer col : autoSizingList ) {
								sheet.trackColumnForAutoSizing( col );
						}

						int nRow = 0;
						for ( Map.Entry<String, String> entry : map.entrySet() ) {
								cell = row.createCell( nRow++ );
								cell.setCellValue( entry.getValue() );
								getBasicStyle( style );
								// 마지막줄 강조 시
								if( bLastRowStyleYn ) {
										CellStyle lastLowStyle = getLastRowStyle( workbook );
										cell.setCellStyle( lastLowStyle );
								}
						}

						for( Integer col : autoSizingList ) {
								sheet.autoSizeColumn( col );
						}
				}

		}

		/**
		 * 칼럼에 지정된 번호(0~끝번호)로 엑셀 데이터를 나타내고 마지막줄을 강조합니다.
		 * 선행조건으로 엑셀 헤더를 0~끝번호로 지정하여 채워넣어야합니다.
		 * 이후 칼럼 번호에 맞게 데이터를 넣은 후 엑셀 헤더를 원하는 내용으로 다시 채워 넣습니다.
		 * 엑셀 헤더에 중복된 칼럼이 있는경우 사용하거나 excelData.put을 순서대로가 아닌 임의로 할때 사용합니다.
		 * @param workbook
		 * @param sheetList
		 * @param map
		 * @param rowNum
		 * @param bLastRowStyleYn
		 */
		public static void createPageRowByNum( SXSSFWorkbook workbook, List<SXSSFSheet> sheetList, Map<String, String> map, int rowNum, boolean bLastRowStyleYn, List<Integer> autoSizingList ) {

				CellStyle style = workbook.createCellStyle();

				for( SXSSFSheet sheet : sheetList ) {
						SXSSFRow row = sheet.createRow( rowNum + 1 );
						SXSSFCell cell = null;

						for( Integer col : autoSizingList ) {
								sheet.trackColumnForAutoSizing( col );
						}

						int nRow = 0;
						for( int i=0; i<map.size(); i++ ) {
								cell = row.createCell( nRow++ );
								cell.setCellValue( (String)map.get( String.valueOf( i ) ) );
								getBasicStyle( style );
								// 마지막줄 강조 시
								if( bLastRowStyleYn ) {
										CellStyle lastLowStyle = getLastRowStyle( workbook );
										cell.setCellStyle( lastLowStyle );
								}
						}

						for( Integer col : autoSizingList ) {
								sheet.autoSizeColumn( col );
						}
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
				getBasicStyle( style );

				style.setFillForegroundColor( IndexedColors.GREY_25_PERCENT.getIndex() );
				style.setFillPattern( FillPatternType.FINE_DOTS );
				style.setFillPattern( FillPatternType.SOLID_FOREGROUND );

				Font font = workbook.createFont();
				font.setColor( IndexedColors.BLACK.getIndex() );
				font.setBoldweight( HSSFFont.BOLDWEIGHT_BOLD );
				font.setFontName( "맑은고딕" );
				style.setFont( font );
				style.setAlignment( HorizontalAlignment.CENTER );
				style.setBorderBottom( BorderStyle.THICK );

				return style;
		}

		/**
		 * 마지막줄 셀 스타일을 설정합니다.
		 * @param workbook
		 * @return
		 */
		public static CellStyle getLastRowStyle( SXSSFWorkbook workbook ) {
				CellStyle style = workbook.createCellStyle();
				getBasicStyle( style );

				style.setFillForegroundColor( IndexedColors.LIGHT_YELLOW.getIndex() );
				// 해당 FillPattern이 있어야 색이 들어감
				style.setFillPattern( FillPatternType.SOLID_FOREGROUND );
				style.setBorderTop( BorderStyle.THICK );

				return style;
		}

		/**
		 * 셀의 기본 스타일을 설정합니다.
		 * @param style
		 * @return
		 */
		public static CellStyle getBasicStyle( CellStyle style ) {

				style.setBorderBottom( BorderStyle.THIN );
				style.setBorderTop( BorderStyle.THIN );
				style.setBorderLeft( BorderStyle.THIN );
				style.setBorderRight( BorderStyle.THIN );

				return style;

		}

		/**
		 * 파일경로를 체크한다.
		 *
		 * @param fileName
		 * @param modeal
		 */
		public static String checkFilePath( Map<String, Object> modeal ) {

				String filePath = (String) modeal.get( PARAM_FILEPATH );

				if ( StringUtils.isEmpty( filePath ) ) {
						return "";
				}

				return filePath;

		}

}