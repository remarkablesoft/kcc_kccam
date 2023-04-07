package com.remarkablesoft.framework.service.storage.file.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.storage.file.vo.FileCnd;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;


@DAO
public class FileDAO extends BaseDAO {

		public int insert( FileInfo fileInfo ) {
				return sql().insert( id( "insert" ), fileInfo );
		}

		public FileInfo getByCnd( FileCnd cnd ) {
				return (FileInfo) sql().selectOne( id( "getByCnd" ), cnd );
		}

		public List<FileInfo> listAll( FileCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}
		
		public List<FileInfo> listByStorageFileUid( FileCnd cnd ) {
				return sql().selectList( id( "listByStorageFileUid" ), cnd );
		}

		public int deleteByCnd( FileCnd cnd ) {
				return sql().delete( id( "deleteByCnd" ), cnd );
		}
		
		public int exist( FileCnd cnd ) {
				return sql().selectOne( id( "exist" ) , cnd );
		}
}
