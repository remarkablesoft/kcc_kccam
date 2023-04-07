package com.remarkablesoft.site.kccam.web.api;

import java.util.HashMap;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.web.controller.BaseController;
import com.remarkablesoft.site.kccam.service.datasheet.model.DatasheetService;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetCnd;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetInfo;

@WEBLog
@RestController
@RequestMapping( "/kccam/api/datasheet" )
public class DatasheetController extends BaseController {

	@Autowired
	protected DatasheetService datasheetService;

	/**
	 * 소재구분 리스트를 가져옵니다.
	 *
	 * @param datasheetCnd
	 * @return HashMap<String, Object>
	 * @author 최원준
	 */
	@RequestMapping( value = "/datasheet_list" )
	public ResponseEntity<HashMap<String, Object>> list( @RequestBody DatasheetCnd datasheetCnd ) {

		HashMap<String, Object> resultMap = new HashMap<>();
		PageList<DatasheetInfo> list = datasheetService.list(datasheetCnd);

		resultMap.put( "list" , list);
		resultMap.put( "totalCount" , CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );

		return ResponseEntity.ok( resultMap );
	}


}
