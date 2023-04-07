package com.remarkablesoft.framework.service.mgnt.code.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.code.vo.CodeCnd;
import com.remarkablesoft.framework.service.mgnt.code.vo.CodeInfo;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	mgnt-code
* 		@프로그램 ID		:	CodeDAO
* 		@프로그램 개요 		:	공통코드 DAO
* 							updateBulk를 실행할경우 jdbc 연결시 &allowMultiQueries=true를 필히 넣어주어야 함
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 10. 3.	:	james	-	신규생성
* 		============================================================================
*/
@DAO
public class CodeDAO extends BaseDAO {

		public int insert( CodeInfo info ) {
				return sql().insert( id( "insert" ), info );
		}

		public int insertBulk( List<CodeInfo> list ) {

			 return sql().insert( id( "insertBulk" ), list );
		}

		public int update( CodeInfo info ) {
				return sql().update( id( "update" ), info );
		}

		public int updateBulk( List<CodeInfo> list) {
				return sql().update( id( "updateBulk" ), list );
		}

		public int delete( String oid ) {
				return sql().delete( id( "delete" ), oid );
		}

		public int deleteByOids( List<CodeInfo> list ) {
				return sql().delete( id( "deleteByOids" ), list );
		}

		public CodeInfo get( String oid ){

				return sql().selectOne( id( "get" ), oid );
		}
		
		public CodeInfo getByCnd( CodeCnd cnd ){
				
				return sql().selectOne( id( "getByCnd" ), cnd );
		}

		public List<CodeInfo> list( CodeCnd cnd ) {
				return sql().selectList( id( "list" ), cnd );
		}

		public PageList<CodeInfo> pageList( CodeCnd cnd ) {
				return sql().queryForPageListAndTCount( id( "pageList" ), cnd );
		}

}
