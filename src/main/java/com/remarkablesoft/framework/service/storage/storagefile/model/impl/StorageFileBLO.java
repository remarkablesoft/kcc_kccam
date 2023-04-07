package com.remarkablesoft.framework.service.storage.storagefile.model.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.exception.BLORuntimeException;
import com.remarkablesoft.framework.service.storage.storagefile.vo.StorageFileCnd;
import com.remarkablesoft.framework.service.storage.storagefile.vo.StorageFileInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 		@주시스템			:	framwork-web
 * 		@서브 시스템		:	storage - storagefile
 * 		@프로그램 ID		:	StorageFileBLO
 * 		@프로그램 개요 		:	스토리지 파일 BLO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 06.	:	james	-	신규생성
 * 		============================================================================
 */
@BLO
public class StorageFileBLO {

		@Autowired
		protected StorageFileDAO storageFileDAO;


		/**
		 * 등록
		 *
		 * @author james
		 * @param storageFileInfo
		 * @return
		 */
		public StorageFileInfo insert( StorageFileInfo storageFileInfo ) {

				if ( StringUtils.isEmpty( storageFileInfo.getStorageFileUid() )) {
						throw new BLORuntimeException( "스토리지파일UID가 없습니다." );
				}

				if ( StringUtils.isEmpty( storageFileInfo.getHashValue() )) {
						throw new BLORuntimeException( "스토리지의 hash값이 없습니다." );
				}

				if ( storageFileInfo.getInputDate() == null) {
						storageFileInfo.setInputDate( LocalDateTime.now() );
				}

				if ( storageFileInfo.getFileCount() == 0) {
						storageFileInfo.setFileCount( 1 );
				}

				int result = storageFileDAO.insert( storageFileInfo );

				return result > 0 ? storageFileInfo : null;
		}

		public StorageFileInfo update( StorageFileInfo info ) {

				int result = storageFileDAO.update( info );

				return result > 0 ? info : null;
		}

		/**
		 * 스토리지의 파일카운트를 감소
		 *
		 * @author james
		 * @param storageFileUid
		 * @return
		 */
		public int fileCountDecrease ( String storageFileUid ) {

				int result = 0;
				// 스토리지 파일에서 파일카운트를 하나 낮춘다
				StorageFileInfo storage = getByStorageFileUid( storageFileUid );
				if ( storage != null && storage.getFileCount() > 0 ) {
					storage.setFileCount( storage.getFileCount() - 1 );
					update( storage );
					result = 1;
				}

				return result;
		}


		/**
		 * 스토리지파일리스트를 찾아서 스토리지의 파일카운트를 감소
		 *
		 * @author james
		 * @param storageFileUidList
		 * @return
		 */
		public int fileCountDecrease ( List<String> storageFileUidList ) {

				if ( CollectionUtils.isEmpty( storageFileUidList )) {
						return 0;
				}

				int result = 0;
				for ( String storageFileUid : storageFileUidList ) {
					result += fileCountDecrease( storageFileUid );
				}

				return result;
		}

		public StorageFileInfo get(  StorageFileCnd cnd ) {

				return storageFileDAO.get( cnd );
		}

		/**
		 * storageFileUid로 가져오기
		 *
		 * @author james
		 * @param storageFileUid
		 * @return
		 */
		public StorageFileInfo getByStorageFileUid( String storageFileUid ) {

				StorageFileCnd cnd = new StorageFileCnd();
				cnd.setStorageFileUid( storageFileUid );

				return storageFileDAO.get( cnd );
		}

		/**
		 * hashValue로 가져오기
		 *
		 * @author james
		 * @param hashValue
		 * @return
		 */
		public StorageFileInfo getByHashValue( String hashValue ) {

				StorageFileCnd cnd = new StorageFileCnd();
				cnd.setHashValue( hashValue );

				return storageFileDAO.get( cnd );
		}
		
		/**
		 * 스토리지 파일이 있는지 체크
		 * 
		 * @author james
		 * @param cnd
		 * @return
		 */
		public boolean exist( StorageFileCnd cnd ) {
				
				return storageFileDAO.exist( cnd );
		}
}
