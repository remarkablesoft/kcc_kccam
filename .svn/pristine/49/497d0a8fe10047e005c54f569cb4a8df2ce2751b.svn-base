package com.remarkablesoft.framework.service.board.board.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.board.board.vo.BoardCnd;
import com.remarkablesoft.framework.service.board.board.vo.BoardInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board-board
 * 		@프로그램 ID		:	BoardDAO
 * 		@프로그램 개요 		:	board는 포스팅의 부모로만 사용
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	hong	-	신규생성
 * 		============================================================================
 */
@DAO
public class BoardDAO extends BaseDAO{

		public int insert( BoardInfo info ) {
				return sql().insert( id( "insert" ), info );
		}

		public BoardInfo get( BoardCnd cnd ) {
				return sql().selectOne( id( "get" ), cnd );
		}

		public int update( BoardInfo info ) {
				return sql().update( id( "update" ), info );
		}

		public int delete( String oid ) {
				return sql().delete( id( "delete" ), oid );
		}
		
		public int deleteFlagUpdate( BoardInfo info ) {
				return sql().update( id( "deleteFlagUpdate" ), info );
		}

		public PageList<BoardInfo> list( BoardCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "list" ), cnd );
		}

		public List<BoardInfo> listAll( BoardCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}

}
