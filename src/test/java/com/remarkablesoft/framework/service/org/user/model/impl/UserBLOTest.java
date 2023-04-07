package com.remarkablesoft.framework.service.org.user.model.impl;

import com.remarkablesoft.site.kccam.service.onetoone.onetoone.model.impl.OneToOneBLO;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneCnd;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.org.user.vo.UserCnd;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;


/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	org - user
 * 		@프로그램 ID		:	UserBLOTest
 * 		@프로그램 개요		:	USER BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      1.1 2021. 03. 05. : 안병현 - 테스트 코드 작성( 테스트 RUN X )
 *      ============================================================================
 */

public class UserBLOTest extends BaseModelTest {
	
	@Autowired
	UserBLO userBLO;
	
	@Autowired
	OneToOneBLO oneToOneBLO;
	
	@Test
	public void insert_테스트() {
		
			for ( int  i = 0; i < 10; i++ ) {
					
					UserInfo user = SystemFactory.getUserInfo();
					user.setOid( OIDGenerator.generateOID() );
//					user.setUserId( "admin" );
//					user.setPwd( "dkagh3480!" );
					user.setName( "testClient" + i );
					user.setEmail( "test " + i + "@remarkablesoft.com" );
					user.setTelPart1( "010" );
					user.setTelPart2( "1231" );
					user.setTelPart3( "011" + i );
					user.setOrganizationName( "remarkablesoft" );
					user.setUserType( "AMUTCLNT" );
					
//					user.setInputUser( "00000000000" );
					
					userBLO.insert( user );
//					System.out.println( user.toString() );
			}
		
		
	}
	
	@Test
	public void update_테스트() {
		
		UserInfo user = SystemFactory.getUserInfo();
		
		user.setOid( "1SRE8O3c000" );
		user.setPwd( "TEST2021" );
		user.setName( "김길동" );
		user.setSnsJoinTypeFlag( SystemConstants.SNS_TYPE_FLAG_GOOGLE );
		user.setPostCode( "14000" );
		
		user = userBLO.update( user );
		System.out.println( user.toString() );
	}
	
	@Test
	public void get_테스트() {
		
		UserInfo user = userBLO.getOnlyUser( "1SRE8O3c000" );
		System.out.println( user.toString() );
	}
	
	@Test
	public void list_테스트() {
		
		UserCnd cnd = new UserCnd();
		PageList<UserInfo> list = userBLO.listUser( cnd );
		
		for( UserInfo info : list ) {
			System.out.println( info );
		}
	}
	
	/*
	 * 문제 有: 위에 수정 필요 사항 참고
	 * */
	@Test
	public void delete_테스트() {
		
		System.out.println( userBLO.delete( "1SRE9Bd7000" ) );
		
//		UserCnd cnd = new UserCnd();
//		List<String> oidList = new ArrayList<String>();
//		oidList.add( "1SRE8O3c000" );
//		cnd.setUserOidList( oidList );
//
//		System.out.println( userBLO.deleteByCnd( cnd ) );
		
	}
	
	@Test
	public void delete_1대1문의도같이_테스트() {
		
			System.out.println( userBLO.delete( "1SYJLbf5002" ) );
			
	}
}
