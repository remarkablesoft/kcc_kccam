package com.remarkablesoft.framework.service.mgnt.part.model.impl;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartCnd;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class PartBLOTest extends BaseModelTest {

	@Autowired
	PartBLO partBLO;
	
	@Test
	public void insert_테스트() {
		
		PartInfo info = SystemFactory.getPartInfo();
		info.setName( "EMC" );
		info.setClassName( "EMC" );
		info.setInputUser( OIDGenerator.generateOID() );
		
		info = partBLO.insert( info );
		
		System.out.println( info.toString() );
	}
	
	@Test
	public void get_테스트() {
		PartCnd cnd = new PartCnd();
		cnd.setOid( "1SRABTtj001" );
		
		PartInfo info = partBLO.get( cnd );
		System.out.println( info.toString() );
		
	}
	
	@Test
	public void update_테스트() {
		PartInfo info = SystemFactory.getPartInfo();
		info.setOid( "1SRADUtQ001" );
		info.setName( "modified" );
		info.setClassName( "modified" );
		
		info = partBLO.update( info );
		System.out.println( info.toString() );
	}
	
	@Test
	public void delete_테스트() {
		
		PartCnd cnd = new PartCnd();
		cnd.setOid( "1SRABTtj001" );
		
		int result = partBLO.delete( cnd );
		System.out.println( result );
		
	}
	
	@Test
	public void list_테스트() {
		PartCnd cnd = new PartCnd();
		List<PartInfo> list = partBLO.list( cnd );
		list.forEach( item -> System.out.println( item.toString() ) );
	}
	
	@Test
	public void listAll_테스트() {
		PartCnd cnd = new PartCnd();
		List<PartInfo> list = partBLO.listAll( cnd );
		list.forEach( item -> System.out.println( item.toString() ) );
	}

}
