package com.remarkablesoft.framework.service.mgnt.part.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartCnd;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt - part
 * 		@프로그램 ID		:	PartService
 * 		@프로그램 개요 		:	Part Service
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	hong	-	신규생성
 * 		============================================================================
 */
public interface PartService {

		public PartInfo insert( PartInfo info );

		public PartInfo get( PartCnd cnd );
		
		public PartInfo update( PartInfo info );
		
		public int delete( PartCnd cnd );
		
		public PageList<PartInfo> list( PartCnd cnd );

		public List<PartInfo> listAll( PartCnd cnd );
		
}
