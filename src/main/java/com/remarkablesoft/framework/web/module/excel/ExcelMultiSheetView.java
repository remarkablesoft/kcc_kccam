package com.remarkablesoft.framework.web.module.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

/**
 *
 * 참고 : https://poi.apache.org/spreadsheet/quick-guide.html#TextExtraction
 *
 * @author James
 *
 */
@SuppressWarnings( "unchecked" )
public class ExcelMultiSheetView extends AbstractXlsView {

		public final static String PARAM_FILENAME = "excel_filename";								// 파일이름
		public final static String PARAM_SHEETNAME = "excel_sheetname";						// 시트이름
		public final static String PARAM_HEADER = "excel_header";									// 테이블 헤더
		public final static String PARAM_HEADER_DELIMITERS = ",";								// 헤더 구분자
		public final static String PARAM_DATA = "excel_data";										// 실제 보여줄 data

		public final static String PARAM_FILEPATH = "excel_filePath";								// 파일위치가 있으면 해당 위치에 파일을 만든다.
		public final static String PARAM_MERGE = "excel_merge";									// 셀 병합 영역
		public final static String PARAM_START_ROW = "excel_startRow";							// 엑셀 본문 시작 줄번호
		public final static String PARAM_MERGE_ADDRESS = "excel_mergeAddress";			// 병합 셀 시트 및 좌표
		public final static String PARAM_MERGE_CELL = "excel_mergeCell";						// 병합 셀 내용
		
		public final static String PARAM_STYLE_ROW = "excel_styleRow";							//  스타일 적용 특정 줄 번호
		public final static String PARAM_LAST_ROW_STYLE_YN = "excel_lastRow_styleYn";	// 마지막줄 강조 유무
		public final static String PARAM_AUTO_SIZING_COL = "excel_autoSizing_col";			// 셀 크기 자동조절 
		public final static String PARAM_NUM_HEADER = "excel_num_header";					// 헤더 항목중 같은 내용이 있을 시 번호매김으로 구분한 번호 헤더
		public final static String PARAM_CREATE_BY_NUM = "excel_create_by_num";			// 헤더번호로 엑셀 데이터 그리는 여부

		@Override
		protected void buildExcelDocument( Map<String, Object> model, Workbook workbookBase, HttpServletRequest req, HttpServletResponse res ) throws Exception {

				if ( model == null )
						return;

				String fileName = "";
				List<String> sheetNameList = new ArrayList<>();
				List<Map<String, String>> excelData = null;
				List<String> header = null;
				int startRow = 0;
				
				Map<String, Object> mergeMap = null;
				boolean bLastRowStyleYn = false;
				List<Integer> autoSizingList = null;
				List<String> headerNumList = null;
				
				//	HSSFWorkbook workbook = (HSSFWorkbook) workbookBase;
				SXSSFWorkbook workbook = new SXSSFWorkbook( 1000 );

				fileName = ExcelMultiSheetUtils.checkFileName( model );
				sheetNameList = ExcelMultiSheetUtils.checkSheetName( model );
				header = ExcelMultiSheetUtils.checkHeader( model );
				excelData = (List<Map<String, String>>) model.get( PARAM_DATA );
				startRow = ExcelMultiSheetUtils.checkStartRow( model );
				mergeMap = ExcelMultiSheetUtils.checkMergeMap( model );
				autoSizingList = ExcelMultiSheetUtils.checkAutoSizingList( model );
				headerNumList = ExcelMultiSheetUtils.checkNumHeader( model );
										
				ExcelMultiSheetUtils.setResponseHeader( req, res, fileName );
				// sheet생성
				List<SXSSFSheet> sheetList = ExcelMultiSheetUtils.createSheet( workbook, sheetNameList );
				// 셀 병합
				ExcelMultiSheetUtils.mergeCell( workbook, sheetList, mergeMap );

				// 엑셀 헤더 나타내기
				if( CollectionUtils.isNotEmpty( headerNumList ) ) {
						ExcelMultiSheetUtils.createDisplayHeader( workbook, sheetList, headerNumList, startRow );
				}
				else {
						ExcelMultiSheetUtils.createDisplayHeader( workbook, sheetList, header, startRow );
				}
				
				int nRow = startRow;
				int index = 1;
				for ( Map<String, String> map : excelData ) {
						index++;
						bLastRowStyleYn = false;
						// 엑셀 마지막줄 강조 유무
						if( model.get( PARAM_LAST_ROW_STYLE_YN ) != null && ( index == map.size() ) ) {
								bLastRowStyleYn = (boolean) model.get( PARAM_LAST_ROW_STYLE_YN );
						}
						// 엑셀 Row마다 컬럼 내용 나타내기
						if( model.get( PARAM_CREATE_BY_NUM ) != null ) {
								if( (boolean)model.get( PARAM_CREATE_BY_NUM ) ) {
										ExcelMultiSheetUtils.createPageRowByNum( workbook, sheetList, map, nRow++, bLastRowStyleYn, autoSizingList );
								}
								else {
										ExcelMultiSheetUtils.createPageRow( workbook, sheetList, map, nRow++, bLastRowStyleYn, autoSizingList );
								}
						}
						else {
								ExcelMultiSheetUtils.createPageRow( workbook, sheetList, map, nRow++, bLastRowStyleYn, autoSizingList );
						}
				}

				if( CollectionUtils.isNotEmpty( headerNumList ) ) {
						ExcelMultiSheetUtils.createDisplayHeader( workbook, sheetList, header, startRow );
				}
				
				workbook.write( res.getOutputStream() );
				res.flushBuffer();

		}

}