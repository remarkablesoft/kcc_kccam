package com.remarkablesoft.site.kccam.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.service.mgnt.part.model.PartService;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartCnd;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartInfo;
import com.remarkablesoft.framework.web.controller.BaseController;



@WEBLog
@RestController
@RequestMapping( "/kccam/api/part" )
public class PartController extends BaseController {

	@Autowired
	protected PartService partService;

	/**
	* 소재 리스트를 불러옵니다.
	*
	* @param cnd
	* @return List<PartInfo>
	* @author 남윤재
	*/
	@RequestMapping( value = "/part_listAll" )
	public ResponseEntity<List<PartInfo>> listAll( @RequestBody PartCnd cnd ) {

		List<PartInfo> listAll = partService.listAll( cnd );
		return ResponseEntity.ok( listAll );
	}
}
