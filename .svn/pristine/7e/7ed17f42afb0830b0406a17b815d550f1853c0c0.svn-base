package com.remarkablesoft.framework.service.mgnt.treenode.vo;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framwork-web
 * 		@서브 시스템		:	mgnt-treeNode
 * 		@프로그램 ID		:	TreeNodeInfo.java
 * 		@프로그램 개요 		:	트리를 구현할려면 fullPathIndex와 subLastIndex를 구성해야 함.
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
public class TreeNodeInfo extends Entity {


		/**
		 *
		 */
		private static final long serialVersionUID = 2746740228566355108L;


		protected String oid ; 						// 계층형 oid
		protected String parentOid ;				// 부모 OID

		/**
		 * TreeNodeInfo.FullPathIndex <BR>
		 * Tree 구조를 가지는 Object의 경우 자신의 FullPathIndex 값.
		 * Tree 구조를 가지는 Obejct은 항상 FullPathIndex와 SubLastIndex 두 Field를 가져야 한다.
		 * <BR>예) 0|1|1|0|
		 * <BR>Root의 FullPathIndex는 항상 "0|" 이다.
		 */
		protected String fullPathIndex ;
		
		/**
		 * TreeNodeInfo.FullPathName <BR>
		 * Tree 구조를 가지는 Object의 경우 전체  FullPathName
		 * <BR>예) 전사 > 개발
		 */
		protected String fullPathNameVC ;

		/**
		 * TreeNodeInfo.SubLastIndex <BR>
		 * Tree구조를 가지는 Object가 FullPathIndex를 구성하기 위하여 가지는 값이다.
		 * Object(O1) 아래 SubObject(O2)가 하나 추가될 때, 그 SubObject의 FullPathIndex는 아래와 같다.
		 * <BR> O2.FullPathIndex = concat( O1.FullPathIndex, (O1.SubLastIndex+1), | )
		 * <BR>Default 값은 -1.
		 * Tree 구조를 가지는 Obejct은 항상 FullPathIndex와 SubLastIndex 두 Field를 가져야 한다.
		 */
		protected int	subLastIndex = -1;

		/**
		 * 테이블명
		 */
		protected String tableName ;


		/**
		 * Depth 깊이
		 */
		protected int depthVC = 0;


		private TreeNodeInfo() {
		}


		public TreeNodeInfo( String tableName ) {
				this.tableName = tableName;
		}

}
