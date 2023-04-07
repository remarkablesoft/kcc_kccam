package com.remarkablesoft.framework.service.link.relationship.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.service.link.relationship.vo.RelationshipCnd;
import com.remarkablesoft.framework.service.link.relationship.vo.RelationshipInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	link - relationship
 * 		@프로그램 ID		:	RelationshipBLO
 * 		@프로그램 개요 		:	관련정보 로직
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 17.	:	최원준	-	신규생성
 * 		============================================================================
 */
@BLO
public class RelationshipBLO {

		@Autowired
		protected RelationshipDAO relationshipDAO;

		/**
		 * 관련정보를 저장합니다.
		 * @author 최원준
		 * @param info
		 * @return
		 */
		public RelationshipInfo insert( RelationshipInfo info ) {
				if( StringUtils.isEmpty( info.getOid() ) || StringUtils.isEmpty( info.getRelationshipOid() ) ) {
						return null;
				}
				int result = relationshipDAO.insert( info );
				return result == 1 ? info : null;
		}

		/**
		 * 관련정보를 삭제합니다.
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public int delete( RelationshipCnd cnd ) {
				if( StringUtils.isEmpty( cnd.getOid() ) ) {
						return 0;
				}
				return relationshipDAO.delete( cnd );
		}

		/**
		 * 관련정보 리스트를 가져옵니다.
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public List<RelationshipInfo> listAll( RelationshipCnd cnd ) {
				List<RelationshipInfo> list = relationshipDAO.listAll( cnd );
				return list;
		}

}
