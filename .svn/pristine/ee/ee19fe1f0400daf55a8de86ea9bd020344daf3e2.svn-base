package com.remarkablesoft.site.kccam.service.newsroom.model.impl;

import com.remarkablesoft.framework.annotation.DAO;
import com.remarkablesoft.framework.model.BaseDAO;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.newsroom.vo.NewsroomCnd;
import com.remarkablesoft.site.kccam.service.newsroom.vo.NewsroomInfo;

import java.util.List;

@DAO
public class NewsroomDAO extends BaseDAO {
		
		int insert( NewsroomInfo info ) { return sql().insert( id( "insert" ), info ); }
		
		int update( NewsroomInfo info ) { return sql().update( id( "update" ), info ); }
		
		int incrementViewCount( NewsroomInfo info ) {
			return sql().update( id("incrementViewCount"), info );
		}
		
		int deleteFlagUpdate( NewsroomInfo info ) { return sql().update( id("deleteFlagUpdate" ), info ); }
		
		int delete( String oid ) { return sql().delete( "delete", oid ); }
		
		NewsroomInfo get( NewsroomCnd cnd ) { return sql().selectOne( id( "get" ), cnd ); }
		
		NewsroomInfo getPrev( NewsroomCnd cnd ) { return (NewsroomInfo) sql().selectOne( id( "getPrev" ), cnd ); }
		
		NewsroomInfo getNext( NewsroomCnd cnd ) { return (NewsroomInfo) sql().selectOne( id( "getNext" ), cnd ); }
		
		PageList<NewsroomInfo> list( NewsroomCnd cnd ) { return sql().queryForPageListAndTCount( id("list"), cnd ); }
		
		List<NewsroomInfo> listAll( NewsroomCnd cnd ) { return sql().selectList( id("listAll"), cnd ); }
		
		boolean exist( String oid ) {
				Object obj = sql().selectOne( id( "exist" ), oid );
				
				return convertIntegerToBoolean( obj );
		}
}
