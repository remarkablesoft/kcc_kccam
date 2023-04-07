package com.remarkablesoft.site.kccam.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.remarkablesoft.site.kccam.service.classification.model.ClassificationService;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationCnd;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationInfo;

@WEBLog
@RestController
@RequestMapping( "/kccam/api/classification" )
public class ClassificationController extends BaseController {

	@Autowired
	protected ClassificationService classificationService;

	/**
	* 분류 정보를 추가합니다.
	*
	* @param ClassificationInfo
	* @return ClassificationInfo
	* @author 황지영
	*/
	@RequestMapping( value = "/classification_insert" )
	public ResponseEntity<ClassificationInfo> insert( @RequestBody ClassificationInfo info ){

		info = classificationService.insert(info);
		return ResponseEntity.ok( info );
	}

	/**
	* 분류 정보를 수정합니다
	*
	* @param ClassificationInfo
	* @return ClassificationInfo
	* @author 황지영
	*/
	@RequestMapping( value = "/classification_update" )
	public ResponseEntity<ClassificationInfo> update( @RequestBody ClassificationInfo info ){

		info = classificationService.update( info );
		return ResponseEntity.ok( info );
	}

	/**
	 * 분류정보 단건을 가져옵니다.
	 *
	 * @param cnd
	 * @return ClassificationInfo
	 * @author 최원준
	 */
	@RequestMapping( value = "/classification_get" )
	public ResponseEntity<ClassificationInfo> get( HttpServletRequest request, @RequestBody ClassificationCnd cnd ) {
		
		String cookieLang = "";
		
		cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );

		if ( StringUtils.isNotEmpty( cookieLang ) ) {			
			cnd.setLang( cookieLang.toUpperCase() );
		};
		

		ClassificationInfo info = classificationService.get( cnd );
		return ResponseEntity.ok( info );
	}

	/**
	 * 분류정보 페이지리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return HashMap<String, Object>
	 * @author 최원준
	 */
	@RequestMapping( value = "/classification_list" )
	public ResponseEntity<HashMap<String, Object>> list( HttpServletRequest request, @RequestBody ClassificationCnd cnd ) {
		
		String cookieLang = "";
		
		cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );

		if ( StringUtils.isNotEmpty( cookieLang ) ) {			
			cnd.setLang( cookieLang.toUpperCase() );
		};

		HashMap<String, Object> resultMap = new HashMap<>();
		PageList<ClassificationInfo> list = classificationService.list( cnd );

		resultMap.put( "list", list );
		resultMap.put( "totalCount", CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );

		return ResponseEntity.ok( resultMap );
	}

	/**
	 * 분류정보 리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return List<MaterialInfo>
	 * @author 최원준
	 */
	@RequestMapping( value = "/classification_listAll" )
	public ResponseEntity<List<ClassificationInfo>> listAll( HttpServletRequest request, @RequestBody ClassificationCnd cnd ) {
		
		String cookieLang = "";
		
		cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );

		if ( StringUtils.isNotEmpty( cookieLang ) ) {			
			cnd.setLang( cookieLang.toUpperCase() );
		};
		
		List<ClassificationInfo> list = classificationService.listAll( cnd );
		return ResponseEntity.ok( list );
	}

	/**
	 * FullPathIndex를 가지고 트리구조의 리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return List<ClassificationInfo>
	 * @author Woong
	 */
	@RequestMapping( value = "/classification_listAllTreeChildren" )
	public ResponseEntity<List<ClassificationInfo>> listAllTreeChildren( @RequestBody ClassificationCnd cnd ) {

		List<ClassificationInfo> list = classificationService.listAllTreeChildren( cnd );
		return ResponseEntity.ok( list );
	}

	/**
	* 동일한 소재 하위의 분류정보를 가져옵니다.
	*
	* @param cnd
	* @return List<ClassificationInfo>
	* @author 최원준
	*/
	@RequestMapping( value = "/classification_sameMaterialList" )
	public ResponseEntity<List<ClassificationInfo>> sameParentList( HttpServletRequest request, @RequestBody ClassificationCnd cnd ) {
		
		String cookieLang = "";
		
		cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );

		if ( StringUtils.isNotEmpty( cookieLang ) ) {			
			cnd.setLang( cookieLang.toUpperCase() );
		};

		List<ClassificationInfo> list = classificationService.sameMaterialList( cnd );
		return ResponseEntity.ok( list );
	}

	/**
	* breadcrumb에서 사용할 맵을 반환합니다.
	*
	* @param cnd
	* @return Map<String, List<ClassificationInfo>>
	* @author 최원준
	*/
	@RequestMapping( value = "/classification_getBreadcrumbMap" )
	public ResponseEntity<Map<String, List<ClassificationInfo>>> getBreadcrumbMap( HttpServletRequest request, @RequestBody ClassificationCnd cnd ) {
		
		String cookieLang = "";
		
		cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );

		if ( StringUtils.isNotEmpty( cookieLang ) ) {			
			cnd.setLang( cookieLang.toUpperCase() );
		};


		Map<String, List<ClassificationInfo>> resultMap = classificationService.getBreadcrumbMap( cnd );
		return ResponseEntity.ok( resultMap );
	}

	/**
	* 제품 정보를 포함한 제품분류정보를 가져옵니다.
	*
	* @param cnd
	* @return ClassificationInfo
	* @author 최원준
	*/
	@RequestMapping( value = "/classification_getInfoIncludeProduct" )
	public ResponseEntity<ClassificationInfo> getInfoIncludeProduct( HttpServletRequest request, @RequestBody ClassificationCnd cnd ) {
		
		String cookieLang = "";
		
		cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );

		if ( StringUtils.isNotEmpty( cookieLang ) ) {			
			cnd.setLang( cookieLang.toUpperCase() );
		};
		
		ClassificationInfo info = classificationService.getInfoIncludeProduct( cnd );
		return ResponseEntity.ok( info );
	}

	/**
	* 분류 정보 delete를 처리합니다
	*
	* @param ClassificationInfo
	* @return HashMap<String, Integer>
	* @author 황지영
	*/
	@RequestMapping( value = "/classification_delete" )
	public ResponseEntity<HashMap<String, Integer>> delete( @RequestBody ClassificationCnd cnd ){

		HashMap<String, Integer> resultMap = new HashMap<>();
		int result = classificationService.delete( cnd);

		resultMap.put("success", result);

		return ResponseEntity.ok( resultMap );
	}

}
