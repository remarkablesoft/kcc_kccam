package com.remarkablesoft.framework.service.mgnt.treenode.vo;

import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.SearchCnd;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.util.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framwork-web
 * 		@서브 시스템		:	mgnt-treeNode
 * 		@프로그램 ID		:	TreeNodeCnd.java
 * 		@프로그램 개요 		:	트리구조 검색 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 9. 29.	:	james	-	신규생성
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class TreeNodeCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = -6098261749716233028L;

		private TreeNodeCnd() {
		}

		public TreeNodeCnd( String tableName ) {
				this.tableName = tableName;
		}

		protected String oid;
		protected String tableName;
		protected String parentOid;
		protected String fullPathIndex;
		protected boolean isFillFullPathName = false;
		private List<String> fullPathIndexList = new ArrayList<String>();		// 찾을 카테고리 fullPathIndex 모음

		public String getTableName() {
				return tableName;
		}

		public void setTableName( String tableName ) {
				this.tableName = tableName;
		}
		
		public void addFullPathIndexList( String fullPathIndex ) {
				this.fullPathIndexList.add( fullPathIndex );
		}
		
		/**
		 * <pre>
		 * fullPathIndex를 분리하는 작업
		 * 0|0027|000|를 OR로 검색하기 위해 아래처럼 분리해야함
		 *  
		 * 0|0027|000|000|
		 * 0|0027|000|
		 * 0|0027|
		 * 
		 * SELECT 
			LISTAGG( NAME, '>') WITHIN GROUP(ORDER BY FULL_PATH_INDEX) 
			FROM TB_CATEGORY 
			WHERE FULL_PATH_INDEX LIKE '0|0027|' OR  
			FULL_PATH_INDEX LIKE '0|0027|000|' OR  
			FULL_PATH_INDEX LIKE '0|0027|000|000|' ORDER BY  FULL_PATH_INDEX;
		 * </pre>
		 * 
		 * @author james
		 * @param fullPathIndex
		 */
		
		public void calculateFullPath ( String fullPathIndex ) {
				
				if (  StringUtils.isEmpty( fullPathIndex )) {
					return;
				}
				
				String fullPath = fullPathIndex;
				
				int nDepth = StringUtils.countMatches( fullPath, SystemConstants.FULL_PATH_INDEX_DELIMITER );
				nDepth = nDepth - 1;
				
				for ( int n = 0; n < nDepth ; n++ ) {
						
					 fullPath =	fullPath.substring( 0, fullPath.lastIndexOf( SystemConstants.FULL_PATH_INDEX_DELIMITER ) );
					 addFullPathIndexList( fullPath + SystemConstants.FULL_PATH_INDEX_DELIMITER );
//					 System.out.println(  n + " : " + fullPath );
				}
		}


}
