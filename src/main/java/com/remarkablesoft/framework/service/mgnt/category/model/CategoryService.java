package com.remarkablesoft.framework.service.mgnt.category.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryCnd;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board-category
 * 		@프로그램 ID		:	CategoryService
 * 		@프로그램 개요 		:	카테고리 객체. 단독으로 사용하고 board의 부모로도 사용
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	hong	-	신규생성
 * 		============================================================================
 */
public interface CategoryService {

		/**
		 * 카텍고리 저장
		 *
		 * @author pc
		 * @param info
		 * @return
		 */
		public CategoryInfo insert( CategoryInfo info );

		/**
		 * 카테고리 가져오기
		 *
		 * @author pc
		 * @param cnd
		 * @return
		 */
		public CategoryInfo get( CategoryCnd cnd );

		/**
		 * 카테고리 수정하기
		 *
		 * @author pc
		 * @param info
		 * @return
		 */
		public CategoryInfo update( CategoryInfo info );


		/**
		 * 카테고리 삭제하기
		 *
		 * @author pc
		 * @param oid
		 * @return
		 */
		public int delete( String oid );


		/**
		 * 카테고리 페이징 리스트 가져오기
		 *
		 * @author pc
		 * @param cnd
		 * @return
		 */
		public PageList<CategoryInfo> list( CategoryCnd cnd );

		/**
		 * 카테고리 리스트 가져오기
		 *
		 * @author pc
		 * @param cnd
		 * @return
		 */
		public List<CategoryInfo> listAll( CategoryCnd cnd );



		/**
		 * 루트카테고리 밑으로 자신의 자식리스트를 트리형태로 담아서 반환
		 *
		 * @author james
		 * @param cnd
		 * @return
		 */
		public List<CategoryInfo> listAllTreeChilds( CategoryCnd cnd );

		/**
		 * 카테고리 insert or update
		 *
		 * @author pc
		 * @param info
		 * @return
		 */
		public CategoryInfo insertOrUpdate( CategoryInfo info );


		/**
		 * 노드 간 이동하기
		 *
		 * @param nodeOid
		 * @param targetOid
		 * @param direction
		 */
		public void move(String nodeOid, String targetOid, String direction);


}
