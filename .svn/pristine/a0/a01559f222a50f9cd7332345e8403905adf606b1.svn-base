package com.remarkablesoft.framework.service.link.relationship.model.impl;

import java.util.List;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.service.link.relationship.vo.RelationshipCnd;
import com.remarkablesoft.framework.service.link.relationship.vo.RelationshipInfo;

@DAO
public class RelationshipDAO extends BaseDAO {

		public int insert ( RelationshipInfo info ) {
				return sql().insert( id( "insert" ), info );
		}
		
		public int delete( RelationshipCnd cnd ) {
				return sql().delete( id( "delete" ), cnd );
		}
		
		public List<RelationshipInfo> listAll( RelationshipCnd cnd ) {
				return sql().selectList( id( "listAll" ), cnd );
		}
		
}
