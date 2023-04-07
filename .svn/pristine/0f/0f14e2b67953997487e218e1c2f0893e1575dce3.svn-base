package com.remarkablesoft.framework.service.mgnt.category.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.category.model.CategoryService;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryCnd;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryInfo;
import com.remarkablesoft.framework.service.mgnt.treenode.model.TreeNodeService;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board-category
 * 		@프로그램 ID		:	CategoryServiceImpl
 * 		@프로그램 개요 		:	카테고리 객체. 단독으로 사용하고 board의 부모로도 사용
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	hong	-	신규생성
 * 		============================================================================
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService, TreeNodeService{

		@Autowired
		protected CategoryBLO categoryBLO;

		@Override
		public CategoryInfo insert( CategoryInfo info ) {

				return categoryBLO.insert( info );
		}

		@Override
		public CategoryInfo get( CategoryCnd cnd ) {

				return categoryBLO.get( cnd );
		}

		@Override
		public CategoryInfo update( CategoryInfo info ) {

				return categoryBLO.update( info );
		}


		@Override
		public int delete( String oid ) {

				return categoryBLO.delete( oid );
		}

		@Override
		public PageList<CategoryInfo> list( CategoryCnd cnd ) {

				return categoryBLO.list( cnd );
		}

		@Override
		public List<CategoryInfo> listAll( CategoryCnd cnd ) {

				return categoryBLO.listAll( cnd );
		}

		@Override
		public CategoryInfo insertOrUpdate( CategoryInfo info ) {

				return categoryBLO.insertOrUpdate( info );
		}

		@Override
		public void move(String nodeOid, String targetOid, String direction) {

				move(nodeOid, targetOid, direction);
		}

		@Override
		public List<CategoryInfo> listAllTreeChilds( CategoryCnd cnd ) {

				return categoryBLO.listAllTreeChilds( cnd );
		}

}
