package com.remarkablesoft.framework.service.storage.thumbnail.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.storage.thumbnail.vo.ThumbnailCnd;
import com.remarkablesoft.framework.service.storage.thumbnail.vo.ThumbnailInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 썸네일을 관리합니다.
 * @author HONG
 * @since 2020. 07. 28.
 *
 */
@BLO
public class ThumbnailBLO {

		@Autowired
		protected ThumbnailDAO thumbnailDAO;

		public ThumbnailInfo insert( ThumbnailInfo info ) {

				if ( info == null ) {
						return null;
				}

				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}

				thumbnailDAO.insert( info );

				return info;

		}

		public int insert( String targetObject, String targetOid, List<ThumbnailInfo> list ) {

				if ( CollectionUtils.isEmpty( list ) ) {
						return 0;
				}

				for ( ThumbnailInfo thumbnailInfo : list ) {

						thumbnailInfo.setTargetObject( targetObject );
						thumbnailInfo.setTargetOid( targetOid );
						insert( thumbnailInfo );
				}

				return list.size();

		}

//		public int update( ThumbnailInfo info ) {
//
//				if ( info == null || StringUtils.isEmpty( info.getTargetOid() ) ) {
//						return 0;
//				}
//
//				return thumbnailDAO.update( info );
//		}

		public int deleteByTargetOid( String targetOid ) {

				if ( StringUtils.isEmpty( targetOid ) ) {
						return 0;
				}

				return thumbnailDAO.deleteByTargetOid( targetOid );

		}

		public ThumbnailInfo get( ThumbnailCnd cnd ) {

				return thumbnailDAO.get( cnd );
		}

		public List<ThumbnailInfo> listAll( ThumbnailCnd cnd ) {

				return thumbnailDAO.listAll( cnd );
		}

		public int deleteAndInsert( String targetOid, String targetObject, List<ThumbnailInfo> list ) {

				deleteByTargetOid( targetOid );

				return insert( targetObject, targetOid, list );
		}



}
