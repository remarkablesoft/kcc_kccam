package com.remarkablesoft.site.kccam.web.api;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.web.controller.BaseController;
import com.remarkablesoft.framework.web.util.CookieUtils;
import com.remarkablesoft.site.kccam.service.AmConstants;
import com.remarkablesoft.site.kccam.service.material.model.MaterialService;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialCnd;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;

@WEBLog
@RestController
@RequestMapping( "/kccam/api/material/" )
public class MaterialController extends BaseController {

	@Autowired
	protected MaterialService materialService;

	/**
	 * 소재구분 정보를 수정합니다.
	 *
	 * @param materialInfo
	 * @return MaterialInfo
	 * @author 최원준
	 */
	@RequestMapping( value = "/material_update" )
	public ResponseEntity<MaterialInfo> update( @RequestBody MaterialInfo materialInfo ) {

		materialInfo = materialService.update( materialInfo );
		return ResponseEntity.ok( materialInfo );
	}

	/**
	 * 소재구분 정보를 가져옵니다.
	 *
	 * @param materialCnd
	 * @return MaterialInfo
	 * @author 최원준
	 */
	@RequestMapping( value = "/material_get" )
	public ResponseEntity<MaterialInfo> get( HttpServletRequest request, @RequestBody MaterialCnd materialCnd ) {

		String cookieLang = "";
		
		cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );

		if ( StringUtils.isNotEmpty( cookieLang ) ) {			
			materialCnd.setLang( cookieLang.toUpperCase() );
		};
		
		MaterialInfo materialInfo = materialService.get( materialCnd );
		return ResponseEntity.ok( materialInfo );
	}

	/**
	 * 소재구분 리스트를 가져옵니다.
	 *
	 * @param materialCnd
	 * @return HashMap<String, Object>
	 * @author 황지영
	 */
	@RequestMapping( value = "/material_list" )
	public ResponseEntity<HashMap<String, Object>> list( HttpServletRequest request, @RequestBody MaterialCnd materialCnd ) {
		
		String cookieLang = "";
		
		cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );

		if ( StringUtils.isNotEmpty( cookieLang ) ) {			
			materialCnd.setLang( cookieLang.toUpperCase() );
		};
		
		HashMap<String, Object> resultMap = new HashMap<>();
		PageList<MaterialInfo> list = materialService.list(materialCnd);

		resultMap.put( "list" , list);
		resultMap.put( "totalCount" , CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );

		return ResponseEntity.ok( resultMap );
	}

	/**
	 * 소재구분 리스트를 가져옵니다.
	 *
	 * @param materialCnd
	 * @return List<MaterialInfo>
	 * @author 최원준
	 */
	@RequestMapping( value = "/material_listAll" )
	public ResponseEntity<List<MaterialInfo>> listAll( HttpServletRequest request, @RequestBody MaterialCnd materialCnd ) {

		String cookieLang = "";
		
		cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );

		if ( StringUtils.isNotEmpty( cookieLang ) ) {			
			materialCnd.setLang( cookieLang.toUpperCase() );
		};
		
		List<MaterialInfo> list = materialService.listAll( materialCnd );
		return ResponseEntity.ok( list );
	}

	/**
	 * 메뉴구조의 소재구분 리스트를 가져옵니다.
	 *
	 * @param materialCnd
	 * @return List<MaterialInfo>
	 * @author 최원준
	 */
	@RequestMapping( value = "/material_menuList" )
	public ResponseEntity<List<MaterialInfo>> menuList( HttpServletRequest request, @RequestBody MaterialCnd materialCnd ) {
		
		String cookieLang = "";
		
		cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );

		if ( StringUtils.isNotEmpty( cookieLang ) ) {			
			materialCnd.setLang( cookieLang.toUpperCase() );
		};
		
		List<MaterialInfo> list = materialService.menuList( materialCnd );
		return ResponseEntity.ok( list );
	}

}
