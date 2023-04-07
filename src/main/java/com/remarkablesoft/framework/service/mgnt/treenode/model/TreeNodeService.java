package com.remarkablesoft.framework.service.mgnt.treenode.model;

/**
 * 		@주시스템			:	framwork-web
 * 		@서브 시스템		:	mgnt-treeNode
 * 		@프로그램 ID		:	TreeNodeService.java
 * 		@프로그램 개요 		:	트리쪽 서비스
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 9. 29.	:	james	-	신규생성
 * 		============================================================================
 */
public interface TreeNodeService {

		/**
		 * <pre>
		 * 그룹 이동 - 이동할 폴더OID와 타겟의 폴더 OID로 이동,
		 * 방향(TREE_NODE_MOVE_UP, TREE_NODE_MOVE_DOWN, TREE_NODE_MOVE_CHILD)
		 *
		 * 1. 내폴더를 타겟 폴더의 위로 이동.
		 * treeNodeBLO.move( oid, targetOid, SystemConstants.TREE_NODE_MOVE_UP );
		 *
		 * 2. 내폴더를 타겟 폴더의 아래로 이동.
		 * treeNodeBLO.move( oid, targetOid, SystemConstants.TREE_NODE_MOVE_DOWN );
		 *
		 * 3. 내폴더를 타겟 폴더의 자식으로 이동.
		 * treeNodeBLO.move( oid, targetOid, SystemConstants.TREE_NODE_MOVE_CHILD );
		 *
		 * 4. 만약 자식으로 첫번째로 생성하고 싶을때에는 첫번째 자식의 OID를 이용하여 UP
		 * treeNodeBLO.move( oid, 들어가고 싶은 자식의 첫번째 그룹OID, SystemConstants.TREE_NODE_MOVE_UP );
		 *
		 * 부모가 같을 경우 그룹의 FullPathIndex를 바꿈.
		 * 부모가 다를 경우 계산하여 처리.
		 * </pre>
		 *
		 * @author James
		 *
		 * @param nodeOid  이동할 Node OID
		 * @param targetOid 타겟의 Node OID
		 * @param direction 위나 아래( SystemConstants.TREE_NODE_MOVE_UP, SystemConstants.TREE_NODE_MOVE_DOWN, SystemConstants.TREE_NODE_MOVE_CHILD)
		 */
		public void move ( String nodeOid , String targetOid , String direction ) ;

}

