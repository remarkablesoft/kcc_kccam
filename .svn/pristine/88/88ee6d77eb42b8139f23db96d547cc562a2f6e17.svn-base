package com.remarkablesoft.framework.service.mgnt.treenode.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.exception.BLORuntimeException;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.mgnt.treenode.vo.TreeNodeCnd;
import com.remarkablesoft.framework.service.mgnt.treenode.vo.TreeNodeInfo;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 		@주시스템			:	framwork-web
 * 		@서브 시스템		:	mgnt-treeNode
 * 		@프로그램 ID		:	TreeNodeInfo.java
 * 		@프로그램 개요 		:	트리쪽 BLO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 9. 29.	:	james	-	신규생성
 * 		============================================================================
 */
@BLO
public class TreeNodeBLO  {


		@Autowired
		protected TreeNodeDAO treeNodeDAO;


		/**
		 *
		 *
		 * @param fullPathIndex
		 * @param parentFullPathIndex
		 * @return
		 */
		public int getMyOrderOfFullPathIndex ( String fullPathIndex, String parentFullPathIndex) {

				fullPathIndex = StringUtils.replace( fullPathIndex, parentFullPathIndex, "" );

				int nTarget = fullPathIndex.indexOf( SystemConstants.FULL_PATH_INDEX_DELIMITER );

				if ( nTarget < 0 ) {
					return 0;
				}

				fullPathIndex = fullPathIndex.substring( 0, nTarget);
				return Integer.parseInt( fullPathIndex);
		}


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
		 * @param nodeOid   이동할 Node OID
		 * @param targetOid 타겟의 Node OID
		 * @param direction 위나 아래( SystemConstants.TREE_NODE_MOVE_UP, SystemConstants.TREE_NODE_MOVE_DOWN, SystemConstants.TREE_NODE_MOVE_CHILD)
		 * @param tableName 변경할 테이블명
		 */
		public void move ( String groupOid , String targetOid , String direction, String tableName ) {
				
				move ( groupOid, targetOid, direction, tableName, SystemConstants.FULL_PATH_SHORT_LENGTH , "", "" );
		}


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
		 * @param nodeOid   
		 * @param targetOid 
		 * @param direction 
		 * @param tableName 
		 * @param fullPathLength 
		 */
		/**
		 * 
		 * @author james
		 * @param nodeOid				이동할 Node OID
		 * @param targetOid				타겟의 Node OID
		 * @param direction				위나 아래 방향 ( SystemConstants.TREE_NODE_MOVE_UP, SystemConstants.TREE_NODE_MOVE_DOWN, SystemConstants.TREE_NODE_MOVE_CHILD)
		 * @param tableName				변경할 테이블명
		 * @param fullPathLength		fullPath 사이즈 ex) %03d, %05d
		 * @param paramRootColName		루트컬럼명 - 타겟외에 루트가 있다면 해당 루크 컬럼명 - EBook의 목차라면 EbookOid, 설문의 질문이라면   설문OID
		 * @param paramRootOid			루트OID  - 해당 루트의 OID
		 */
		public void move ( String nodeOid , String targetOid , String direction, String tableName, String fullPathLength, String paramRootColName, String paramRootOid ) {

				if ( !( SystemConstants.TREE_NODE_MOVE_UP.equals( direction ) || SystemConstants.TREE_NODE_MOVE_DOWN.equals( direction ) || SystemConstants.TREE_NODE_MOVE_CHILD.equals( direction ))) {

						throw new BLORuntimeException( "Node는 타겟그룹의 위, 아래, 자식으로만 이동 할수 있습니다." );
				}

				TreeNodeInfo nodeInfo = treeNodeDAO.get ( nodeOid, tableName);
				TreeNodeInfo targetNode = treeNodeDAO.get ( targetOid, tableName);
				TreeNodeInfo targetParentNode = treeNodeDAO.get ( targetNode.getParentOid(), tableName);

				if ( nodeInfo == null || targetNode == null ) {
						return;
				}

				// 부모는 자신의 하위폴더로 갈수는 없다.
				if ( targetNode.getFullPathIndex().indexOf( nodeInfo.getFullPathIndex()) >= 0) {

					throw new BLORuntimeException( "부모노드가 하위노드로 이동할 수는 없습니다." );
				}

				// 자식으로 들어갈때에는 항상 맨 밑으로 가도록.
				if ( SystemConstants.TREE_NODE_MOVE_CHILD.equals(  direction )) {

						targetNode.setSubLastIndex( targetNode.getSubLastIndex() + 1 );
						treeNodeDAO.update( targetNode );

						String oldFullPathIndex = nodeInfo.getFullPathIndex();
						nodeInfo.setFullPathIndex( targetNode.getFullPathIndex() +  ( String.format( fullPathLength, targetNode.getSubLastIndex() + 1)) + SystemConstants.FULL_PATH_INDEX_DELIMITER );
						nodeInfo.setParentOid( targetNode.getOid() );
						treeNodeDAO.update ( nodeInfo);

						treeNodeDAO.updateForAllSubNode ( nodeInfo.getFullPathIndex(), oldFullPathIndex, tableName, paramRootColName, paramRootOid );
						return;
				}


				// 1. 내가 가야할 타겟폴더의 리스트 전체를 구한다.
				TreeNodeCnd cnd = new TreeNodeCnd( tableName);
				cnd.setParentOid( targetParentNode.getOid() );
				cnd.setFullPathIndex( targetParentNode.getFullPathIndex() );
				List<TreeNodeInfo> targetList = treeNodeDAO.listAll( cnd );

				// 2. 내가 가야할 타겟의 fullPathIndex의 Order 확인
				int nTargetOrder = getMyOrderOfFullPathIndex( targetNode.getFullPathIndex(),  targetParentNode.getFullPathIndex());
				List<TreeNodeInfo> resultBigList = new ArrayList<TreeNodeInfo>();

				
				for ( TreeNodeInfo info : targetList ) {

					 // 3. 타겟 대상중 내가 이동해야할 대상보다 큰것은 담는다.
					 int nMySubLastIndex = getMyOrderOfFullPathIndex( info.getFullPathIndex(),  targetParentNode.getFullPathIndex());		
					
					 if ( nTargetOrder <= nMySubLastIndex ) {
							 resultBigList.add( info );
					 }
				}

			//	System.out.println( resultBigList);

				//  4. 만약 리스트에 자신의 것이 있다면 빼도록 한다.
				int nSubLastIndex = nTargetOrder;

				// 4-1. 아래로 가는것은 타겟폴더에서 +1
				if ( SystemConstants.TREE_NODE_MOVE_DOWN.equals( direction )) {
						nSubLastIndex = nTargetOrder + 1 ;
				}


				// 5. 타겟 폴더 리스트중 내가 가야할 대상보다 큰 fullPathIndex는 +1해서 업데이트
				int nApplySubLastIndex = 0;
				int maxApplySubLastIndex = 0;

				String updatedOldFullPathIndex = "";
				for ( int i = resultBigList.size() -1 ; i >= 0 ; i-- ) {

						TreeNodeInfo group = resultBigList.get( i );
						String oldFullPathIndex = group.getFullPathIndex();

						int nMySubLastIndex = getMyOrderOfFullPathIndex( group.getFullPathIndex(),  targetParentNode.getFullPathIndex());					
//						System.out.println( "nMySubLastIndex" + nMySubLastIndex );

						nApplySubLastIndex = nMySubLastIndex  + resultBigList.size() ;
						if ( nApplySubLastIndex > maxApplySubLastIndex) {
								maxApplySubLastIndex = nApplySubLastIndex;
						}

						if ( nTargetOrder == nMySubLastIndex ) {

								// 5-1. 아래로 가야하는것은  적용할 fullPathIndex를 2번의 타겟의 Order를 이용
								if ( SystemConstants.TREE_NODE_MOVE_DOWN.equals( direction )) {
										nApplySubLastIndex = nTargetOrder ;
								}
						}

						String newFullPathIndex = targetParentNode.getFullPathIndex() + ( String.format( fullPathLength, nApplySubLastIndex)) + SystemConstants.FULL_PATH_INDEX_DELIMITER ;

						if ( nodeInfo.getOid().equals( group.getOid() ) ) {
								updatedOldFullPathIndex	 = newFullPathIndex;
						}

			//			System.out.println( "newFullPathIndex" + newFullPathIndex );
			//			System.out.println( "oldFullPathIndex" + oldFullPathIndex );


						treeNodeDAO.updateForAllSubNode ( newFullPathIndex, oldFullPathIndex, tableName, paramRootColName, paramRootOid );
				}


				// 6. 타겟의 부모는 추가된 subLastIndex에서 젤 큰값보다 +1
				targetParentNode.setSubLastIndex( maxApplySubLastIndex + 1 );
				treeNodeDAO.update ( targetParentNode);


				// 7. 내 폴더의 FullPath를 가야할 SubLastIndex로 업데이트
				String oldFullPathIndex = nodeInfo.getFullPathIndex();
				if ( StringUtils.isEmpty( updatedOldFullPathIndex )) {
						updatedOldFullPathIndex = oldFullPathIndex;
				}

				String newFullPathIndex = targetParentNode.getFullPathIndex() + ( String.format( fullPathLength, nSubLastIndex) ) + SystemConstants.FULL_PATH_INDEX_DELIMITER;
				nodeInfo.setFullPathIndex( newFullPathIndex );
				nodeInfo.setParentOid( targetParentNode.getOid() );
				treeNodeDAO.update ( nodeInfo);
				
				//	System.out.println( "|||||||||||||||||||||||||||||||||||||||||||||||||||||" );
				//	System.out.println( "newFullPathIndex" + newFullPathIndex );
				//	System.out.println( "updatedOldFullPathIndex" + updatedOldFullPathIndex );


				treeNodeDAO.updateForAllSubNode ( newFullPathIndex, updatedOldFullPathIndex, tableName, paramRootColName, paramRootOid );

		}



}
