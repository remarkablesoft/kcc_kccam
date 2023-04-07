package com.remarkablesoft.framework.service.mgnt.batch.model.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.service.mgnt.batch.vo.BatchProcessCnd;
import com.remarkablesoft.framework.service.mgnt.batch.vo.BatchProcessInfo;

/**
 * 		@주시스템			:
 * 		@서브 시스템		:
 * 		@프로그램 ID		:	BatchProcessBLO.java
 * 		@프로그램 개요 		:
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 6. 11.	:	james	-	신규생성
 * 		============================================================================
 */
@BLO
public class BatchProcessBLO {


		@Autowired
		protected BatchProcessDAO batchProcessDAO;

		public int insert( BatchProcessInfo info ) {

			return batchProcessDAO.insert( info );
		}


		public boolean exist( BatchProcessCnd cnd ){


			return batchProcessDAO.exist( cnd );
		}

}