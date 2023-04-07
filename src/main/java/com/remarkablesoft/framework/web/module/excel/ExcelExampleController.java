package com.remarkablesoft.framework.web.module.excel;

import static com.remarkablesoft.framework.web.module.excel.ExcelView.PARAM_DATA;
import static com.remarkablesoft.framework.web.module.excel.ExcelView.PARAM_FILENAME;
import static com.remarkablesoft.framework.web.module.excel.ExcelView.PARAM_HEADER;
import static com.remarkablesoft.framework.web.module.excel.ExcelView.PARAM_SHEETNAME;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.service.org.user.model.impl.UserBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.web.controller.BaseController;

@WEBLog
@Controller
@RequestMapping( "/core_excel/" )
public class ExcelExampleController extends BaseController {

		@Autowired
		UserBLO userBLO;
	
		@RequestMapping( value = "/excel_example1" )
		protected ModelAndView example1() throws Exception {

				String fileName = "바로배치_리스트.xls";
				fileName = URLEncoder.encode( fileName, "UTF-8" );

				String sheetName = "한글시트";
				String displayHeader = "제목,나이,내용";

				LinkedHashMap<String, String> excelData = null;
				List<Map<String, String>> listExcelData = new ArrayList<>();

				String[] arrTitle = { "바보", "축구", "배구" };
				String[] arrAge = { "11", "22", "33" };
				String[] arrContents = { "내용1", "내용2", "내용3" };

				for ( int i = 0; i < 3; i++ ) {
						excelData = new LinkedHashMap<>();
						excelData.put( "제목", arrTitle[i] );
						excelData.put( "나이", arrAge[i] );
						excelData.put( "내용", arrContents[i] );
						listExcelData.add( excelData );
				}

				ModelAndView mv = new ModelAndView( new ExcelView() );
				mv.addObject( PARAM_FILENAME, fileName );
				mv.addObject( PARAM_SHEETNAME, sheetName );
				mv.addObject( PARAM_HEADER, displayHeader );
				mv.addObject( PARAM_DATA, listExcelData );
				return mv;
		}
		
		/**
		 * 엑셀 다중 시트 필요시 사용
		 * 
		 * @return
		 * @throws Exception
		 */
		@RequestMapping( value = "/excel_example2" )
		public ModelAndView example2() throws Exception {
				
				String fileName = "대상자목록_리스트.xlsx";

				fileName = URLEncoder.encode( fileName, "UTF-8" );

				List<String> sheetNameList = new ArrayList<>();
				sheetNameList.add( "대상자목록 리스트" );
				String displayHeader = "순번,이름,이메일,휴대폰번호";

				LinkedHashMap<String, String> excelData = null;
				List<Map<String, String>> listExcelData = new ArrayList<>();

//				List<SurveyTargetRelInfo> list = new ArrayList<>();
//				if( cnd.getPageSize() > 0 ) {
//						list = surveyService.targetRelList( cnd );
//				}
//				else {
//						list = surveyService.targetRelListAll( cnd );
//				}

				int nCount = 0;
//				for ( SurveyTargetRelInfo surveyTargetRelInfo : list ) {
//						
//						excelData = new LinkedHashMap<>();
//
//						excelData.put( "순번", String.valueOf( ++nCount ) );
//						
//						String userInfoList = surveyTargetRelInfo.getTargetUserInfoList();
//						UserInfo user = userBLO.convertUserInfo( userInfoList );
//						
//						excelData.put( "이름", user.getName() );
//						
//						listExcelData.add( excelData );
//				}

				ModelAndView mv = new ModelAndView( new ExcelView() );
				mv.addObject( PARAM_FILENAME, fileName );
				mv.addObject( PARAM_SHEETNAME, sheetNameList );
				mv.addObject( PARAM_HEADER, displayHeader );
				mv.addObject( PARAM_DATA, listExcelData );
				
				return mv;
		}	

}