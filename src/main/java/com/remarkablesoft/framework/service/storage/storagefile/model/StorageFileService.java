package com.remarkablesoft.framework.service.storage.storagefile.model;

import com.remarkablesoft.framework.service.storage.storagefile.vo.StorageFileCnd;
import com.remarkablesoft.framework.service.storage.storagefile.vo.StorageFileInfo;


/**
 * 		@주시스템			:	framwork-web
 * 		@서브 시스템		:	storage - storagefile
 * 		@프로그램 ID		:	StorageFileService
 * 		@프로그램 개요 		:	스토리지 파일 Service
 * 							파일 업로드시 스토리지파일 테이블에서 hash값을 체크해서 같은 hash값이 있다면 같은 파일로 처리.
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 06.	:	james	-	신규생성
 * 		============================================================================
 */
public interface StorageFileService {

		public StorageFileInfo insert( StorageFileInfo storage ) ;

		public StorageFileInfo get( StorageFileCnd cnd ) ;

		public StorageFileInfo getByStorageFileUid( String storageFileUid );

		public StorageFileInfo getByHashValue( String hashValue );

		public StorageFileInfo update( StorageFileInfo storage );
		
		/**
		 * 스토리지 파일이 있는지 체크
		 * 
		 * @author james
		 * @param cnd
		 * @return
		 */
		public boolean exist( StorageFileCnd cnd );
}
