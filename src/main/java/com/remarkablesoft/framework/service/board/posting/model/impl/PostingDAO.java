package com.remarkablesoft.framework.service.board.posting.model.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.board.posting.vo.PostingCnd;
import com.remarkablesoft.framework.service.board.posting.vo.PostingInfo;

/**
 * <pre>
 * 설명 : 포스팅 DAO 객체
 * </pre>
 *
 * @author James
 * @since 2015. 4. 13.
 *
 */
@DAO
public class PostingDAO extends BaseDAO {

		public int insert( PostingInfo info ) {
				return sql().insert( id( "insert" ), info );
		}

		public PostingInfo get( PostingCnd cnd ) {
				return (PostingInfo) sql().selectOne( id( "get" ), cnd );
		}

		public PostingInfo getPrev( PostingCnd cnd ) {
				return (PostingInfo) sql().selectOne( id( "getPrev" ), cnd );
		}

		public PostingInfo getNext( PostingCnd cnd ) {
				return (PostingInfo) sql().selectOne( id( "getNext" ), cnd );
		}

		public int update( PostingInfo info ) {
				return sql().update( id( "update" ), info );
		}

		public int updateCount( PostingInfo info ) {
				return sql().update( id( "updateCount" ), info );
		}

		public int updateCommentCount( PostingInfo info ) {
				return sql().update( id( "updateCommentCount" ), info );
		}

		public int updateReplyCount( PostingInfo info ) {
				return sql().update( id( "updateReplyCount" ), info );
		}

		public int updateThread( PostingInfo info ) {
				return sql().update( id( "updateThread" ), info );
		}

		public int updateThreadOrderNo( String threadOid, int orderNo ) {

				HashMap<String, String> map = new HashMap<>();
				map.put( "threadOid", threadOid );
				map.put( "threadOrderNo", orderNo + "" );

				return sql().update( id( "updateThreadOrderNo" ), map );
		}
		
		/**
		 * 포스팅의 보기카운트 증가
		 * 
		 * @author james
		 * @param info
		 * @return
		 */
		public int incrementViewCount( PostingInfo info ) {
				return sql().update( id( "incrementViewCount" ), info );
		}

		public boolean exist( String oid ) {
				Object obj = sql().selectOne( id( "exist" ), oid );

				return convertIntegerToBoolean( obj );
		}

		public int deleteFlagUpdate( PostingInfo info ) {
				return sql().update( id( "deleteFlagUpdate" ), info );
		}

		public int delete( String oid ) {
				return sql().delete( id( "delete" ), oid );
		}

		/**
		 * 포스팅묶음 전체를 삭제 업데이트 처리
		 *
		 * @param info
		 * @return
		 */
		public int deleteFlagUpdateByThreadOid( PostingInfo info ) {
				return sql().update( id( "deleteFlagUpdateByThreadOid" ), info );
		}

		/**
		 * 포스팅묶음 전체를 복원처리
		 * threadOid로 delUser = null, delDate = null 처리
		 *
		 * @author james
		 * @param info
		 * @return
		 */
		public int restoreByThreadOid( String threadOid ) {
				return sql().update( id( "restoreByThreadOid" ), threadOid );
		}

		/**
		 * 해당 포스팅 복원처리
		 * oid로 delUser = null, delDate = null 처리
		 *
		 * @author james
		 * @param posting
		 * @return
		 */
		public int restorePosting( String oid ) {

				return sql().update( id( "restorePosting" ), oid );
		}

		/**
		 * 게시판묶음 전체를 삭제 업데이트 처리
		 *
		 * @param info
		 * @return
		 */
		public int deleteByThreadOid( String threadOid ) {
				return sql().delete( id( "deleteByThreadOid" ), threadOid );
		}

		public PageList<PostingInfo> list( PostingCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "list" ), cnd );
		}

		public List<PostingInfo> listAll( PostingCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}

		public int listCount( PostingCnd cnd ) {
				return sql().selectOne( id( "list_count" ), cnd );
		}

		public int getNumber( PostingCnd cnd ) {
				return sql().selectOne( id( "getNumber" ), cnd );
		}

		public boolean checkAnonymousPwd( PostingCnd cnd ) {

				Object obj = sql().selectOne( id( "checkAnonymousPwd" ), cnd );

				return convertIntegerToBoolean( obj );
		}

		public List<Map<String, Object>> listRank( PostingCnd cnd ) {

				return sql().selectList( id( "listRank" ), cnd );
		}

		public PageList<PostingInfo> listMyAnswerOfParent( PostingCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "listMyAnswerOfParent" ), cnd );
		}
		
		public PageList<PostingInfo> listPick( PostingCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "listPick" ), cnd );
		}
}
