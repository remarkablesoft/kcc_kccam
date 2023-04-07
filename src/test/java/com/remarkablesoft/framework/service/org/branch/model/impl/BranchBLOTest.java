package com.remarkablesoft.framework.service.org.branch.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.service.SystemFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.org.branch.vo.BranchCnd;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	org - branch
 * 		@프로그램 ID		:	BranchBLOTest
 * 		@프로그램 개요		:	BRANCH BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 05. : 안병현 - 신규생성( 테스트 RUN X )
 *      ============================================================================
 */

public class BranchBLOTest extends BaseModelTest{
	
	@Autowired
	BranchBLO branchBLO;
	
	@Test
	public void insert_테스트() {
		
		BranchInfo info = SystemFactory.getBranchInfo();
		
		info.setOid( OIDGenerator.generateOID() );
		info.setCompanyOid( OIDGenerator.generateOID() );
		info.setAreaCode( "1" );
		info.setOwner( "김이최" );
		info.setName( "문래점" );
		
		info.setAddr( "address" );
		info.setPostCode( "12345" );
		info.setTel( "01012345678" );
		info.setDescr( "Description" );
		info.setInputUser( OIDGenerator.generateOID() );
		
		info.setOrderNo( 10 );
		
		info = branchBLO.insert( info );
		
		System.out.println( info.toString() );
		
	}
	
	@Test
	public void update_테스트() {
		
		BranchInfo info = SystemFactory.getBranchInfo();
		info.setOid( "1SRDpoZb000" );
		info.setOwner( "손오공" );
		
		int result = branchBLO.update( info );
		
		String resultStr = (result > 0) ? "수정완료" : "수정실패";
		
		System.out.println( resultStr );
		
	}
	
	@Test
	public void get_OID_테스트() {
		
		BranchInfo info = branchBLO.get( "1SRDpoZb000" );
		System.out.println( info.toString() );
		
	}
	
	@Test
	public void list_테스트() {
		BranchCnd cnd = new BranchCnd();
		cnd.setName("문래점");
		
		PageList<BranchInfo> list = branchBLO.list( cnd );
		for( BranchInfo info : list ){
			System.out.println( list );
		}
	}
	
	@Test
	public void listAll_테스트() {
		BranchCnd cnd =  new BranchCnd();
		
		List<BranchInfo> list =  branchBLO.listAll( cnd );
		
		list.forEach( System.out::println ); 
	}

	
	@Test
	public void delete_테스트() {
		
		int delResult = branchBLO.delete( "1SRDqvKa000" );
		
		String resultStr = (delResult > 0) ? "삭제완료" : "삭제실패";
		
		System.out.println( resultStr );
		
	}

}
