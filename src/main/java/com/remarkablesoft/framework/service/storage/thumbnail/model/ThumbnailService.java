package com.remarkablesoft.framework.service.storage.thumbnail.model;

import java.util.List;

import com.remarkablesoft.framework.service.storage.thumbnail.vo.ThumbnailCnd;
import com.remarkablesoft.framework.service.storage.thumbnail.vo.ThumbnailInfo;



public interface ThumbnailService {

		/**
		 * 썸네일을 저장합니다.
		 * @param thumbBase64Info
		 */
		public ThumbnailInfo insert( ThumbnailInfo info );

		/**
		 * 썸네일 리스트를 저장합니다.
		 * @param thumbBase64InfoList
		 */
		public int insert( String targetObject, String targetOid, List<ThumbnailInfo> list );

		/**
		 * 썸네일 정보를 업데이트 합니다.
		 * @param thumbBase64Info
		 */
//		public int update( ThumbnailInfo info );

		/**
		 * 썸네일을 가져옵니다.
		 * @param oid
		 * @return
		 */
		public ThumbnailInfo get( ThumbnailCnd cnd );

		/**
		 * 타겟 아이디로 썸네일 리스트를 가져옵니다.
		 * @param cnd required targetOid
		 * @return
		 */
		public List<ThumbnailInfo> listAll( ThumbnailCnd cnd );


		/**
		 * 타켓 아이디로 썸네일을 삭제합니다.
		 * @param targetOid
		 * @return
		 */
		public int deleteByTargetOid( String targetOid );

}
