package com.remarkablesoft.framework.service.mgnt.part.model.impl;

import java.util.List;

import com.remarkablesoft.framework.web.util.AutheUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartCnd;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartInfo;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt - part
 * 		@프로그램 ID		:	PartBLO
 * 		@프로그램 개요 		:	Part BLO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	hong	-	신규생성
 * 		============================================================================
 */

@BLO
public class PartBLO {

		@Autowired
		protected PartDAO partDAO;

		public PartInfo insert( PartInfo info ) {
			
				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}
				
				if ( StringUtils.isEmpty( info.getInputUser() ) ) {
						info.setInputUser( AutheUtils.getLoginUserOid() );
				}

				return partDAO.insert( info ) > 0 ? info : null;
		}

		public PartInfo get( PartCnd cnd ) {

				return partDAO.get( cnd );
		}

		public PartInfo update( PartInfo info ) {

				partDAO.update( info );

				return info;
		}

		public int delete( PartCnd cnd ) {

				return partDAO.delete( cnd );
		}

		public PageList<PartInfo> list( PartCnd cnd ) {

				return partDAO.list( cnd );
		}

		public List<PartInfo> listAll( PartCnd cnd ) {

				return partDAO.listAll( cnd );
		}

}
