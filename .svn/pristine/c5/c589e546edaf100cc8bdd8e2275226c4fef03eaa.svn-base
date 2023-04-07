package com.remarkablesoft.framework.service.storage.thumbnail.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.storage.thumbnail.vo.ThumbnailCnd;
import com.remarkablesoft.framework.service.storage.thumbnail.vo.ThumbnailInfo;

@DAO
public class ThumbnailDAO extends BaseDAO {

		public void insert( ThumbnailInfo info ) {
				sql().insert( id( "insert" ), info );
		}

//		public int update( ThumbnailInfo info ) {
//				return sql().update( id( "update" ), info );
//		}

		public int deleteByTargetOid( String targetOid ) {
				return sql().delete( id( "deleteByTargetOid" ), targetOid );
		}

		public ThumbnailInfo get( ThumbnailCnd cnd ) {
				return (ThumbnailInfo) sql().selectOne( id( "get" ), cnd );
		}

		public List<ThumbnailInfo> listAll( ThumbnailCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}


}
