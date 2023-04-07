package com.remarkablesoft.framework.service.board.contents.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsCnd;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;

/**
 * <pre>
 * 설명 : 컨텐츠 DAO 객체
 * </pre>
 *
 * @author James
 * @since 2016. 2. 04.
 *
 */
@DAO
public class ContentsDAO extends BaseDAO {

		public int insert( ContentsInfo info ) {
				return sql().insert( id( "insert" ), info );
		}
		
		public int insertBulk( List<ContentsInfo> list ) {
				return sql().insert( id( "insertBulk" ), list );
		}

		public ContentsInfo get( ContentsInfo info ) {
				return (ContentsInfo) sql().selectOne( id( "get" ), info );
		}

		public String getContents( ContentsInfo info ) {
				return (String) sql().selectOne( id( "getContents" ), info );
		}

		public List<ContentsInfo> list( ContentsCnd cnd ) {
				return sql().selectList( id( "list" ), cnd );
		}

		public int update( ContentsInfo info ) {

				return sql().update( id( "update" ), info );
		}

		public int delete( ContentsCnd cnd ) {

			return sql().delete( id( "delete" ), cnd );
		}

}
