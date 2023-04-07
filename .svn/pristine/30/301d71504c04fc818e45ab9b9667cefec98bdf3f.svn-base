package com.remarkablesoft.framework.web.module.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class ExcelView extends AbstractXlsView {

		public final static String PARAM_FILENAME = "excel_filename";		// 파일이름
		public final static String PARAM_SHEETNAME = "excel_sheetname";		// 시트이름
		public final static String PARAM_HEADER = "excel_header";			// 테이블 헤더
		public final static String PARAM_HEADER_DELIMITERS = ",";			// 헤더 구분자
		public final static String PARAM_DATA = "excel_data";				// 실제 보여줄 data
		public final static String PARAM_FILEPATH = "excel_filePath";		// 파일위치가 있으면 해당 위치에 파일을 만든다.

		@Override
		protected void buildExcelDocument( Map<String, Object> model, Workbook workbookBase, HttpServletRequest req, HttpServletResponse res ) throws Exception {

				if ( model == null )
						return;

				String fileName = "";
				String sheetName = "";
				List<Map<String, String>> excelData = null;
				List<String> header = null;

				//		HSSFWorkbook workbook = (HSSFWorkbook) workbookBase;
				SXSSFWorkbook workbook = new SXSSFWorkbook( 1000 );

				fileName = ExcelUtils.checkFileName( model );
				sheetName = ExcelUtils.checkSheetName( model );
				header = ExcelUtils.checkHeader( model );
				excelData = (List<Map<String, String>>) model.get( PARAM_DATA );

				ExcelUtils.setResponseHeader( req, res, fileName );

				SXSSFSheet sheet = ExcelUtils.createFirstSheet( workbook, sheetName );

				// 엑셀 헤더 나타내기
				ExcelUtils.createDisplayHeader( workbook, sheet, header );

				int nRow = 0;
				for ( Map<String, String> map : excelData ) {

						// 엑셀 Row마다 컬럼 내용 나타내기
						ExcelUtils.createPageRow( sheet, map, nRow++ );
				}

				workbook.write( res.getOutputStream() );
				res.flushBuffer();

		}

}