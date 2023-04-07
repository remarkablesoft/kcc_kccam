package com.remarkablesoft.site.kccam.service.material.model.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialCnd;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;

/**
 * @주시스템            :	kccam
 * @서브 시스템        :   material
 * @프로그램 ID        :   MaterialBLOTest.java
 * @프로그램 개요        :   소재 BLO 테스트
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-03-16 : 최원준 - 신규생성
 * ============================================================================
 */
public class MaterialBLOTest extends BaseModelTest {
		
		@Autowired
		MaterialBLO materialBLO;
		
		@Autowired
		MaterialLangDAO materialLangDAO;
		
		@Test
		public void insert_테스트() {
				
				//        MaterialInfo materialInfo = new MaterialInfo();
				//        materialInfo.setName( "EMC" );
				//        materialInfo.setClassName( "Epoxy Molding Compounds" );
				//
				//        String descr = "실리콘 칩/드와이어/리드프레임 등의 반도체 소재를 열/수분/외부 충격 등으로부터 " +
				//                               "보호하기 위해 밀봉하는 재료로서 필러/에폭시 수지/페놀수지/" +
				//                               "카본블랙/난연제 등 10여 가지의 원료가 사용되는 유/무기 복합소재";
				//        ContentsInfo mainContents = SystemFactory.getContentsInfo();
				//        mainContents.setContents( descr );
				//        materialInfo.setMainContents( mainContents );
				//
				//        materialBLO.insert( materialInfo );
				
//				MaterialInfo materialInfo = new MaterialInfo();
//				materialInfo.setName( "Adhesive" );
//				materialInfo.setClassName( "Die Attach Adhesive/Underfill/UV몰딩재" );
//				
//				String descr = "반도체 Chip과 PCB 기판 or Lead Frame를 접착/고정시키는 소재 " +
//										   "Flip Chip 반도체 등에서 chip(Die)와 Substrate기판 사이의 Solder bump 사이를 채우는 소재" +
//										   "주요 Device의 다양한 패턴의 디자인을 구현하기 위해 UV를 통해 몰딩시키틑 아크릴 레진";
//				ContentsInfo mainContents = SystemFactory.getContentsInfo();
//				mainContents.setContents( descr );
//				materialInfo.setMainContents( mainContents );
//				
//				materialBLO.insert( materialInfo );
				
		}
		
		@Test
		public void list_테스트() {
			
			MaterialCnd materialCnd = new MaterialCnd();
			List<MaterialInfo> list = materialBLO.list( materialCnd );
			System.out.println("===================list 테스트입니다============");
			list.forEach( material -> System.out.println( material.getName() + " /// " + material.getApplicationList()));
		}
		
		@Test
		public void listAll_테스트() {
				
				MaterialCnd materialCnd = new MaterialCnd();
				List<MaterialInfo> list = materialBLO.listAll( materialCnd );
				list.forEach( material -> System.out.println( material.getName() ) );
				
		}
		
		@Test
		public void 캐스팅_테스트() {
				
				MaterialInfo info = new MaterialInfo();
				info = materialBLO.insert( info );
				
				System.out.println( info.toString() );
				
		}
		
		@Test
		public void peek_테스트() {
				
				Stream.of( "one", "two", "three", "fourfive" )
							.filter( e -> e.length() > 3 )
							.peek( e -> System.out.println( "Filtered value: " + e ) )
							.map( String::toUpperCase )
							.peek( e -> System.out.println( "Mapped value: " + e ) )
							.collect( Collectors.toList() );
				
		}
		
		@Test
		public void insert_관련정보() {
				
				//CONTENT
				//mainImg
				//Doc
				//classification: app , 소재 , 상품
				
		}
		
		@Test
		public void get_Test() {
				
				MaterialCnd cnd = new MaterialCnd();
				cnd.setOid( "1SWKGVwR004" );
				cnd.setFillProductCnt(true);
				
				MaterialInfo info = materialBLO.get( cnd );
				info.getProductClassificationList().forEach( product -> System.out.println( product.getName() ) );
				info.getApplicationList().forEach( application -> System.out.println( application.getName() ) );
				
				System.out.println( "MaterialInfo : " + info );
				System.out.println( "MaterialInfo.doclist : " + info.getDocList() );
		}
		
		@Test
		public void get_Test2() {
			MaterialCnd cnd = new MaterialCnd();
			cnd.setOid( "1SWKGVwR008" );
			cnd.setFillProductCnt(true);
			cnd.setFillLangList( true );
			
			MaterialInfo info = materialBLO.get( cnd );
			
			System.out.println(  "MaterialInfo : " + info );
		}
		
		@Test
		public void list_테스트2() {
			
			MaterialCnd materialCnd = new MaterialCnd();
			materialCnd.setFillProductCnt( true );
			PageList<MaterialInfo> list = materialBLO.list( materialCnd );
			System.out.println("===================list 테스트입니다============");
			list.forEach( material -> System.out.println( material ) );
		}
		
		@Test
		public void 다국어_확인_테스트() {
			MaterialCnd materialCnd = new MaterialCnd();
			
			List<MaterialInfo> list = materialBLO.listAll( materialCnd );
			
			List<String> oidList = list.stream().map( MaterialInfo :: getOid ).collect( Collectors.toList() );
			
			oidList.forEach( oid -> {
				MaterialCnd langCnd = new MaterialCnd();
				
				materialLangDAO.exist( langCnd );
			});
		}
		
}
