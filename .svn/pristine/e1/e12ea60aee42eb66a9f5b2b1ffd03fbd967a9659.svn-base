package com.remarkablesoft.framework.service.mgnt.part.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.part.model.PartService;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartCnd;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt - part
 * 		@프로그램 ID		:	MapServiceImpl
 * 		@프로그램 개요 		:	맵은 지도, 노선의 느낌으로 카테고리의 부모로 사용
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	hong	-	신규생성
 * 		============================================================================
 */
@Service
@Transactional
public class PartServiceImpl implements PartService {

		@Autowired
		protected PartBLO partBLO;
		
		@Override
		public PartInfo insert( PartInfo info ) {
				
				return partBLO.insert( info );
		}

		@Override
		public PartInfo get( PartCnd cnd ) {
				
				return partBLO.get( cnd );
		}

		@Override
		public PartInfo update( PartInfo info ) {
				
				return partBLO.update( info );
		}

		@Override
		public int delete( PartCnd cnd ) {
				
				return partBLO.delete( cnd );
		}

		@Override
		public PageList<PartInfo> list( PartCnd cnd ) {
				
				return partBLO.list( cnd );
		}

		@Override
		public List<PartInfo> listAll( PartCnd cnd ) {
				
				return partBLO.listAll( cnd );
		}

}
