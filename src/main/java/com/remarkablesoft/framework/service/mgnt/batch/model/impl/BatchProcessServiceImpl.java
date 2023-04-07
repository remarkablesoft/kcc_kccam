package com.remarkablesoft.framework.service.mgnt.batch.model.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.mgnt.batch.model.BatchProcessService;
import com.remarkablesoft.framework.service.mgnt.batch.vo.BatchProcessCnd;
import com.remarkablesoft.framework.service.mgnt.batch.vo.BatchProcessInfo;

@Service
@Transactional
public class BatchProcessServiceImpl implements BatchProcessService {

		@Autowired
		protected BatchProcessBLO batchProcessBLO;

		@Override
		public int insert( BatchProcessInfo info ) {

				info.setOid( OIDGenerator.generateOID() );
				return batchProcessBLO.insert( info );
		}

		@Override
		public boolean exist( BatchProcessCnd cnd ) {

				return batchProcessBLO.exist( cnd );
		}




}
