package com.remarkablesoft.framework.service.org.user.model;

import java.util.List;
import java.util.Map;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.org.user.vo.UserCnd;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	org-user
 * 		@프로그램 ID		:	UserService.java
 * 		@프로그램 개요 		:	사용자 서비스 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2014. 5. 12.	:	james	-	신규생성
 * 		============================================================================
 */
public interface UserService {

		/**
		 * insertOrUpdate 메소드는 해당 유저가 있으면 업데이트
		 * 없으면 Insert 하는 구조.
		 *
		 * @param user
		 * @return
		 */
		public UserInfo insertOrUpdate( UserInfo user );

		public UserInfo insert( UserInfo user );

		public int delete( String oid );

		public int deleteById( String id );

		public int deleteByCnd( UserCnd cnd );

		public int updateStatusTypeFlagByUserOidList( UserInfo user );

		public UserInfo update( UserInfo user );

		public UserInfo getById( String id );

		public UserInfo getUser( String oid );

		public UserInfo getUser( UserCnd cnd );
	
		public UserInfo login( UserInfo user );
	
		public PageList<UserInfo> list( UserCnd cnd );
		
		public int listAllCount( UserCnd userCnd );
	

		/**
		 * 유저리스트
		 * @param cnd
		 * @return
		 */
		public List<UserInfo> listAll( UserCnd cnd );

		/**
		 * Role별 사용자 리스트
		 *
		 * @param cnd
		 * @return
		 */
		public PageList<UserInfo> listRoleUser( UserCnd cnd ) ;
		
		
		/**
		 * 유저 - 롤 뷰 리스트를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public List<UserInfo> listAllRoleUser( UserCnd cnd );

		/**
		 * 유저 수 구하기. 검색
		 *
		 * @param cnd
		 * @return
		 */
		public int getCount( UserCnd cnd );
		
		/**
		 * statusTypeFlag별 개수를 가져옵니다.
		 * 
		 * @param cnd
		 * @return
		 */
		public Map<String, Integer> getStatusCount( UserCnd cnd );
	
		/**
		 * 유저의 존재 여부를 검색합니다.
		 *
		 * @param id
		 * @return
		 */
		public boolean existById( UserInfo info );


}
