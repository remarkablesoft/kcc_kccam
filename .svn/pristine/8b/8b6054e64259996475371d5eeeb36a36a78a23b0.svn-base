package com.remarkablesoft.framework.service.board.posting.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.board.posting.vo.PostingCnd;
import com.remarkablesoft.framework.service.board.posting.vo.PostingInfo;

/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	KCCAM - Posting
 * 		@프로그램 ID		:	PostingLangDAO
 * 		@프로그램 개요		:	Posting 다국어 DAO
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 05. 27. : Woong - 신규생성
 *      ============================================================================
 */

@DAO
public class PostingLangDAO extends BaseDAO {

		public int insert( PostingInfo postingInfo ) {
				return sql().insert( id( "insert" ), postingInfo );
		}

		public int update( PostingInfo postingInfo ) {
				return sql().update( id( "update" ), postingInfo );
		}

		public boolean exist( PostingCnd cnd ) {
				return convertIntegerToBoolean( sql().selectOne( id( "exist" ), cnd ) );
		}

		public int delete( PostingCnd cnd ) {
				return sql().delete( id( "delete" ), cnd );
		}
		
		public int deleteAll( String oid ) {
			return sql().delete( id( "deleteAll" ), oid );
	}
		
		public List<PostingInfo> listAll( String oid ) {
			return sql().selectList( id( "listAll" ), oid );
	}

}
