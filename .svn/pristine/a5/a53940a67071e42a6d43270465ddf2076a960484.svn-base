package com.remarkablesoft.site.kccam.service.classification.model;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationCnd;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationInfo;

import java.util.List;
import java.util.Map;

public interface ClassificationService {

	/**
	 * 분류정보를 저장합니다.
	 *
	 * @param info
	 * @return ClassificationInfo
	 * @author 최원준
	 */
	ClassificationInfo insert( ClassificationInfo info );

	/**
	 * 분류정보 단건을 가져옵니다.
	 *
	 * @param cnd
	 * @return ClassificationInfo
	 * @author 최원준
	 */
	ClassificationInfo get( ClassificationCnd cnd );

	/**
	 * 분류정보 페이지리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return PageList<ClassificationInfo>
	 * @author 최원준
	 */
	PageList<ClassificationInfo> list( ClassificationCnd cnd );

	/**
	 * 분류정보 리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return List<ClassificationInfo>
	 * @author 최원준
	 */
	List<ClassificationInfo> listAll( ClassificationCnd cnd );

	/**
	 * FullPathIndex를 가지고 트리구조의 리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return List<ClassificationInfo>
	 * @author 최원준
	 */
	List<ClassificationInfo> listAllTreeChildren( ClassificationCnd cnd );

	/**
	 * 동일한 소재 하위의 분류정보를 가져옵니다.
	 *
	 * @param cnd
	 * @return List<ClassificationInfo>
	 * @author 최원준
	 */
	List<ClassificationInfo> sameMaterialList( ClassificationCnd cnd );

	/**
	* Breadcrumb에 사용되는 Map을 반환합니다.
	*
	* @param cnd
	* @return Map<String, List<ClassificationInfo>>
	* @author 최원준
	*/
	Map<String, List<ClassificationInfo>> getBreadcrumbMap( ClassificationCnd cnd );

	/**
	* 제품 정보를 포함한 제품분류정보를 가져옵니다.
	*
	* @param cnd
	* @return ClassificationInfo
	* @author 최원준
	*/
	ClassificationInfo getInfoIncludeProduct( ClassificationCnd cnd );

	/**
	 * 분류정보를 수정합니다.
	 *
	 * @param info
	 * @return ClassificationInfo
	 * @author 황지영
	 */
	ClassificationInfo update( ClassificationInfo info );

	/**
	 * 분류정보를 수정합니다.
	 *
	 * @param info
	 * @return ClassificationInfo
	 * @author 황지영
	 */
	int delete( ClassificationCnd cnd );
}
