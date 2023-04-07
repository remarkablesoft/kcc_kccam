package com.remarkablesoft.framework.service.storage.storagefile.model.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.service.storage.storagefile.model.StorageFileService;
import com.remarkablesoft.framework.service.storage.storagefile.vo.StorageFileCnd;
import com.remarkablesoft.framework.service.storage.storagefile.vo.StorageFileInfo;

/**
 * 		@주시스템			:	framwork-web
 * 		@서브 시스템		:	storage - storagefile
 * 		@프로그램 ID		:	StorageFileServiceImpl
 * 		@프로그램 개요 		:	스토리지 파일 Service 구현객체
 * 							파일 업로드시 스토리지파일 테이블에서 hash값을 체크해서 같은 hash값이 있다면 같은 파일로 처리.
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 06.	:	james	-	신규생성
 * 		============================================================================
 */
@Service
@Transactional
public class StorageFileServiceImpl implements StorageFileService {

		@Autowired
		protected StorageFileBLO storageFileBLO;

		@Override
		public StorageFileInfo insert( StorageFileInfo storageFileInfo ) {

				return storageFileBLO.insert( storageFileInfo );
		}

		@Override
		public StorageFileInfo get( StorageFileCnd cnd ) {

				return storageFileBLO.get( cnd );
		}

		@Override
		public StorageFileInfo getByStorageFileUid( String storageFileUid ) {

				return storageFileBLO.getByStorageFileUid( storageFileUid );
		}

		@Override
		public StorageFileInfo getByHashValue( String hashValue ) {

				return storageFileBLO.getByHashValue( hashValue );
		}

		@Override
		public StorageFileInfo update( StorageFileInfo info ) {

				return storageFileBLO.update( info );
		}

		@Override
		public boolean exist( StorageFileCnd cnd ) {

				return storageFileBLO.exist( cnd );
		}


}
