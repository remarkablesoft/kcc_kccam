package com.remarkablesoft.framework.service.mgnt.system.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.system.model.SystemService;
import com.remarkablesoft.framework.service.mgnt.system.vo.SystemCnd;
import com.remarkablesoft.framework.service.mgnt.system.vo.SystemInfo;


/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	link - system
* 		@프로그램 ID		:	SystemServiceImpl
* 		@프로그램 개요 		:	시스템객체 서비스 구현객체
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 4.	:	james	-	신규생성
* 		============================================================================
*/
@Service
@Transactional
public class SystemServiceImpl implements SystemService {

		@Autowired
		protected SystemBLO systemBLO;

		@Override
		public SystemInfo get( SystemCnd cnd ) {

				return systemBLO.get( cnd );
		}

		@Override
		public PageList<SystemInfo> list( SystemCnd cnd ) {

				return systemBLO.list( cnd );
		}

		@Override
		public List<SystemInfo> listAll( SystemCnd cnd ) {

				return systemBLO.list( cnd );
		}

		@Override
		public SystemInfo insertOrUpdate( SystemInfo info ) {

				return systemBLO.insertOrUpdate( info );
		}

		@Override
		public int delete( String oid ) {

				return systemBLO.delete( oid );
		}

		@Override
		public void moveTop( SystemCnd cnd ) {

				systemBLO.moveTop( cnd );
		}

}
