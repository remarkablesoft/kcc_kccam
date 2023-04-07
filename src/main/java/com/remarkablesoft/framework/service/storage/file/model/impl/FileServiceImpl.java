package com.remarkablesoft.framework.service.storage.file.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.service.storage.file.model.FileService;
import com.remarkablesoft.framework.service.storage.file.vo.FileCnd;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;


@Service
@Transactional
public class FileServiceImpl implements FileService {

		@Autowired
		protected FileBLO fileBLO;

		@Override
		public FileInfo insert( FileInfo fileInfo ) {

				return fileBLO.insert( fileInfo );
		}

		@Override
		public FileInfo get( String oid ) {

				return fileBLO.get( oid );
		}

		@Override
		public FileInfo getByStorageFileUid( String storageFileUid ) {

				return fileBLO.getByStorageFileUid( storageFileUid );
		}
	
		@Override
		public FileInfo getByCnd( FileCnd cnd ) {
				return fileBLO.getByCnd( cnd );
		}
		
		@Override
		public void deleteByStorageFileUid( String storageFileUid ) {

				fileBLO.deleteByStorageFileUid( storageFileUid );
		}

		@Override
		public List<FileInfo> listAll( FileCnd cnd ) {

				return fileBLO.listAll( cnd );
		}

		@Override
		public int deleteByStorageFileUidList( List<String> storageFileUidList ) {

				return fileBLO.deleteByStorageFileUidList( storageFileUidList );

		}

		@Override
		public int deleteByStorageFileUids( String[] storageFileUids ) {

				return fileBLO.deleteByStorageFileUids( storageFileUids );
		}

}
