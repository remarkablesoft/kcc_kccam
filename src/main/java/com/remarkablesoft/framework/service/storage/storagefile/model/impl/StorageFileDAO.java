package com.remarkablesoft.framework.service.storage.storagefile.model.impl;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.storage.storagefile.vo.StorageFileCnd;
import com.remarkablesoft.framework.service.storage.storagefile.vo.StorageFileInfo;


/**
 * 		@주시스템			:	framwork-web
 * 		@서브 시스템		:	storage - storagefile
 * 		@프로그램 ID		:	StorageFileDAO
 * 		@프로그램 개요 		:	스토리지 파일 DAO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 06.	:	james	-	신규생성
 * 		============================================================================
 */
@DAO
public class StorageFileDAO extends BaseDAO {

		public int insert( StorageFileInfo storageFileInfo ) {
				return sql().insert( id( "insert" ), storageFileInfo );
		}

		public StorageFileInfo get(  StorageFileCnd cnd ) {
				return (StorageFileInfo) sql().selectOne( id( "get" ), cnd );
		}

		public int update( StorageFileInfo info ) {
				return sql().update( id( "update" ), info );
		}
		
		public boolean exist( StorageFileCnd cnd ) {
				Object obj = sql().selectOne( id( "exist" ), cnd );
				return convertIntegerToBoolean( obj );
		}

}
