package com.remarkablesoft.framework.service.storage.file.model;

import java.util.List;

import com.remarkablesoft.framework.service.storage.file.vo.FileCnd;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;

public interface FileService {

		public FileInfo insert( FileInfo fileInfo );

		public FileInfo get( String fileOid );

		public FileInfo getByStorageFileUid( String storageFileUid );
		
		public FileInfo getByCnd( FileCnd cnd );
		
		public List<FileInfo> listAll( FileCnd cnd );

		public void deleteByStorageFileUid( String storageFileUid );

		public int deleteByStorageFileUidList( List<String> storageFileUidList );

		public int deleteByStorageFileUids( String[] storageFileUids );

}