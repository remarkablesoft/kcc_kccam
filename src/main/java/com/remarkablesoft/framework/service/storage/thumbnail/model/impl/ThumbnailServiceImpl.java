package com.remarkablesoft.framework.service.storage.thumbnail.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.service.storage.thumbnail.model.ThumbnailService;
import com.remarkablesoft.framework.service.storage.thumbnail.vo.ThumbnailCnd;
import com.remarkablesoft.framework.service.storage.thumbnail.vo.ThumbnailInfo;

@Service
@Transactional
public class ThumbnailServiceImpl implements ThumbnailService {

		@Autowired
		protected ThumbnailBLO thumbnailBLO;

		@Override
		public ThumbnailInfo insert( ThumbnailInfo info ) {

				return thumbnailBLO.insert( info );
		}

		@Override
		public int insert( String targetObject, String targetOid, List<ThumbnailInfo> list ) {

				return thumbnailBLO.insert( targetObject, targetOid, list );
		}

//		@Override
//		public int update( ThumbnailInfo info ) {
//
//				return thumbnailBLO.update( info );
//		}

		@Override
		public ThumbnailInfo get( ThumbnailCnd cnd ) {

				return thumbnailBLO.get( cnd );
		}

		@Override
		public List<ThumbnailInfo> listAll( ThumbnailCnd cnd ) {

				return thumbnailBLO.listAll( cnd );
		}

		@Override
		public int deleteByTargetOid( String targetOid ) {

				return thumbnailBLO.deleteByTargetOid( targetOid );
		}

}
