package com.remarkablesoft.site.kccam.web.api;

import static com.remarkablesoft.framework.web.module.excel.ExcelMultiSheetView.PARAM_DATA;
import static com.remarkablesoft.framework.web.module.excel.ExcelMultiSheetView.PARAM_FILENAME;
import static com.remarkablesoft.framework.web.module.excel.ExcelMultiSheetView.PARAM_HEADER;
import static com.remarkablesoft.framework.web.module.excel.ExcelMultiSheetView.PARAM_SHEETNAME;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.controller.BaseController;
import com.remarkablesoft.framework.web.module.excel.ExcelMultiSheetView;
import com.remarkablesoft.site.kccam.common.ExcelJsonCnd;
import com.remarkablesoft.site.kccam.service.material.model.MaterialService;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialCnd;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;

@RestController
@RequestMapping( "/kccam/api/excel/" )
public class ExcelController extends BaseController {

	@Autowired
	protected MaterialService materialService;

	ObjectMapper mapper =  new ObjectMapper();

	/**
	 * 소재구분 리스트 엑셀.
	 *
	 * @param materialCnd
	 * @return HashMap<String, Object>
	 * @author 김웅기
	 */
	@RequestMapping( value = "/excel_materialList" )
	public ModelAndView	materialList( @ModelAttribute MaterialCnd materialCnd ) throws Exception {

			String fileName = "소재구분리스트.xlsx";

			fileName = URLEncoder.encode( fileName, "UTF-8" );

			List<String> sheetNameList = new LinkedList<>();
			sheetNameList.add( "소재구분 리스트" );
			String displayHeader = "No.,OID,SYSTEM_OID,NAME,CLASS_NAME,INPUT_USER,INPUT_DATE";

			LinkedHashMap<String, String> excelData = null;

			List<Map<String, String>> listExcelData = new LinkedList<>();

			List<MaterialInfo> list = materialService.listAll( materialCnd );

			int nCount = 0;

			for ( MaterialInfo info : list ) {
				excelData = new LinkedHashMap<>();
				excelData.put("No.", Integer.toString( ++nCount ) );
				excelData.put("OID", String.valueOf( info.getOid() ) );
				excelData.put("SYSTEM_OID", String.valueOf( info.getSystemOid() ) );
				excelData.put("NAME", String.valueOf( info.getName() ) );
				excelData.put("CLASS_NAME", String.valueOf( info.getClassName() ) );
				excelData.put("INPUT_USER", String.valueOf( info.getInputUser() ) );
				excelData.put("INPUT_DATE", String.valueOf( info.getInputDate() ) );

				listExcelData.add( excelData );
			}

			ModelAndView mv = new ModelAndView( new ExcelMultiSheetView() );
			mv.addObject( PARAM_FILENAME, fileName );
			mv.addObject( PARAM_SHEETNAME, sheetNameList );
			mv.addObject( PARAM_HEADER, displayHeader );
			mv.addObject( PARAM_DATA, listExcelData );

			return mv;

	}



	/**
	 * json 형태로 엑셀 다운로드 처리
	 *
	 * @param cnd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/excel_makeWithJson" )
	public ModelAndView	makeWithJson( @ModelAttribute ExcelJsonCnd cnd  ) throws Exception {

			String fileName = StringUtils.isEmpty( cnd.getFileName()) ?  "Material.xlsx" : cnd.getFileName() ;
			fileName = URLEncoder.encode( fileName, "UTF-8" );

			List<String> sheetNameList = new ArrayList<>();
			sheetNameList.add( cnd.getSheetName() );
			String displayHeader = cnd.getDisplayHeader();

			List<LinkedHashMap<String, String>> listExcelData = mapper.readValue( cnd.getJsonData(), new TypeReference<ArrayList<LinkedHashMap<String, String>>>(){});

			ModelAndView mv = new ModelAndView( new ExcelMultiSheetView() );
			mv.addObject( PARAM_FILENAME, fileName );
			mv.addObject( PARAM_SHEETNAME, sheetNameList );
			mv.addObject( PARAM_HEADER, displayHeader );
			mv.addObject( PARAM_DATA, listExcelData );

			return mv;

	}

}
