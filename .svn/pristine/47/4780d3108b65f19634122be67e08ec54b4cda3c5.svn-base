package com.remarkablesoft.framework.service.mgnt.batch.model.impl;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.mgnt.batch.vo.BatchProcessCnd;
import com.remarkablesoft.framework.service.mgnt.batch.vo.BatchProcessInfo;

@DAO
public class BatchProcessDAO extends BaseDAO {

	public int insert( BatchProcessInfo info ) {

		return sql().insert( id( "insert" ), info );
	}


	public boolean exist( BatchProcessCnd cnd ){

		return convertIntegerToBoolean( sql().selectOne( id( "exist" ), cnd ));
	}
}
