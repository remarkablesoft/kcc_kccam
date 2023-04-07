package com.remarkablesoft.site.kccam.service.material.model;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialCnd;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;

import java.util.List;

/**
 * @주시스템 :	kccam
 * @서브 시스템        :   service - material
 * @프로그램 ID        :   MaterialService.java
 * @프로그램 개요        :   소재 정보 서비스
 * @변경이력 ============================================================================
 * 1.0 2021-03-23 : 최원준 - 신규생성
 * ============================================================================
 */
public interface MaterialService {
	
	/**
	 * 소재 정보를 수정합니다.
	 *
	 * @param info
	 * @return MaterialInfo
	 * @author 최원준
	 */
	MaterialInfo update( MaterialInfo info );
	
	/**
	 * 소재 구분 정보를 가져옵니다.
	 *
	 * @param cnd
	 * @return MaterialInfo
	 * @author 최원준
	 */
	MaterialInfo get( MaterialCnd cnd );
	
	/**
	 * 소재 정보 리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return PageList<MaterialInfo>
	 * @author 황지영
	 */
	PageList<MaterialInfo> list( MaterialCnd cnd );
	
	/**
	 * 소재 정보 리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return List<MaterialInfo>
	 * @author 최원준
	 */
	List<MaterialInfo> listAll( MaterialCnd cnd );
	
	/**
	 * 메뉴구조의 소재정보 리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return List<MaterialInfo>
	 * @author 최원준
	 */
	List<MaterialInfo> menuList( MaterialCnd cnd );
	
}
