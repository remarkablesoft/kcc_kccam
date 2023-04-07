package com.remarkablesoft.site.kccam.service.classification.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.site.kccam.service.AmConstants;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationCnd;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductRelInfo;


/**
 * @주시스템			:	kccam
 * @서브 시스템		:	classification
 * @프로그램 ID		:	ClassificationBLOTest
 * @프로그램 개요		:	분류정보 BLO Test
 * @변경이력 
 * ============================================================================
 * 1.0 2021. 02. 25. : 최원준 - 신규생성
 * ============================================================================
 */

public class ClassificationBLOTest extends BaseModelTest {
	
	@Autowired
	protected ClassificationBLO classificationBLO;
	
	@Test
	public void SYSTEM_Root_등록() {
		
		ClassificationInfo info = new ClassificationInfo();
		info.setOid( SystemConstants.ROOT_TREE_OID );
		info.setName( "Root Category" );
		info.setFullPathIndex( "0|" );
		classificationBLO.insert( info );
		
	}
	
	@Test
	public void Classification_Root_등록() {
		
		ClassificationInfo info = new ClassificationInfo();
		info.setOid( AmConstants.ROOT_TREE_OID_PRODUCT );
		info.setParentOid( SystemConstants.ROOT_TREE_OID );
		info.setCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_PRODUCT );
		info.setName( "Product Root Category" );
		classificationBLO.insert( info );
		
		info = new ClassificationInfo();
		info.setOid( AmConstants.ROOT_TREE_OID_APPLICATION );			//00001
		info.setParentOid( SystemConstants.ROOT_TREE_OID );			//0000
		info.setCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_APPLICATION );
		info.setName( "Application Root Category" );
		classificationBLO.insert( info );
		
		info = new ClassificationInfo();
		info.setOid( AmConstants.ROOT_TREE_OID_MARKET );
		info.setParentOid( SystemConstants.ROOT_TREE_OID );
		info.setCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_MARKET );
		info.setName( "Market Root Category" );
		classificationBLO.insert( info );
		
		info = new ClassificationInfo();
		info.setOid( AmConstants.ROOT_TREE_OID_FUNCTION );
		info.setParentOid( SystemConstants.ROOT_TREE_OID );
		info.setCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_FUNCTION );
		info.setName( "Function Root Category" );
		classificationBLO.insert( info );
		
	}
	
	@Test
	public void insert_테스트() {
		
		ClassificationInfo info = new ClassificationInfo();
	}
	
	@Test
	public void update_테스트() {
	
	
	}
	
	@Test
	public void split_테스트() {
		String[] arr = "0|00003|".split( "\\|" );
		System.out.println( arr.length );
	}
	
	/**
	 * CLASSIFICATION BLO TEST
	 * ClassificationInfo는 3개의 LIST를 포함 : category, doc , content , productRel
	 * TB_DOC , TB_CONTENTS , TB_PRODOCT_REL
	 * */
	/**
	 * DOC INSERT : docInfoList , ClassificationInfoOid, type
	 * */
	
	//분류정보의 모든 정보를 INSERT하는 테스트
	@Test
	public void insert_All_List_테스트() {
		
		ClassificationInfo info = new ClassificationInfo();
		DocInfo doc = new DocInfo();
		ContentsInfo cont = SystemFactory.getContentsInfo();
		ProductRelInfo rel = new ProductRelInfo();
		
		//categoryInfo
		info = new ClassificationInfo();
		info.setName( "TEST INSERT" );
		info.setDescr( "CALSSIFICATION 테스트를 위한 저장" );
		//docInfo
		doc.setTitle( "CALSSIFICATION에 연관된 DOC" );
		doc.setSecretYn( SystemConstants.FLAG_YES );
		
		//contentInfo
		cont.setContents("CALSSIFICATION에 연관된 <br> DOC");
		cont.setContentsSize( 22 );
		//productRelList
		
		//LIST 
		List< DocInfo > dList = new ArrayList<DocInfo>();
		List< ContentsInfo > cList = new ArrayList<ContentsInfo>();
		List< ProductRelInfo > pList = new ArrayList<ProductRelInfo>();
		
		dList.add(doc);
		cList.add(cont);
		pList.add(rel);
		
		info.setDocList(dList);
		info.setAddContentsList(cList);
		info.setProductRelList(pList);
		classificationBLO.insert( info );
	}
	
	@Test
	public void get_테스트() {
		
		ClassificationCnd cnd = new ClassificationCnd();
		cnd.setOid( "1SWKYFME002" );
		ClassificationInfo info = classificationBLO.get(cnd);
		System.out.println( info );
		System.out.println( "==================" );
		//info.getDocList().forEach( e -> System.out.println( e )  );
		System.out.println( "==================" );
		//info.getAddContentsList().forEach( e -> System.out.println( e )  );
		
	}
	
	@Test
	public void getBreadCrumbMap_테스트() {
		
		ClassificationCnd cnd = new ClassificationCnd();
		cnd.setPartOid( "1SSTrIJO000" );
		cnd.setOid( "1SSZGi8q001" );
		
		Map<String, List<ClassificationInfo>> resultMap = classificationBLO.getBreadcrumbMap( cnd );
		
		for ( String key : resultMap.keySet() ) {
			System.out.println( key );
			List<ClassificationInfo> keyList = resultMap.get( key );
			keyList.forEach( val -> System.out.println( val.getName() ) );
		}
		
	}
	
	@Test
	public void getInfoIncludeProduct_테스트() {
		
		ClassificationCnd cnd = new ClassificationCnd();
		cnd.setOid( "1SWKH467000" );
		cnd.setFillChildClassification( true );
		cnd.setFillRelateMaterial( true );
		cnd.setGroupByMaterial( true );
		
		ClassificationInfo info = classificationBLO.getInfoIncludeProduct( cnd );
		
		System.out.println( info.toString() );
	}
	
	@Test
	public void groupedMap테스트() {
		
		ClassificationCnd classificationCnd = new ClassificationCnd();
		classificationCnd.setPartOid( "1ST7e1vx000" );
		List<ClassificationInfo> classificationList = classificationBLO.listAllTreeChildren( classificationCnd );
		if ( CollectionUtils.isEmpty( classificationList ) ) {
			return;
		}
		
		Map<String, List<ClassificationInfo>> groupedMap = classificationList.stream().collect( Collectors.groupingBy( ClassificationInfo::getCategoryType, Collectors.toList() ) );
		
		List<ClassificationInfo> productList = groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_PRODUCT );
		List<ClassificationInfo> applicationList = groupedMap.get( ClassificationInfo.AM_CLASSIFICATION_TYPE_APPLICATION );
		
		productList.forEach( product -> System.out.println( product.getName() ) );
		applicationList.forEach( application -> System.out.println( application.getName() ) );
		
	}	
	
	@Test
	public void listAll_테스트() {
		ClassificationCnd cnd = new ClassificationCnd();
		cnd.setFillProduct( true );
//		cnd.setFillMaterial( true );
		cnd.setCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_MARKET );
		
		List<ClassificationInfo> list = classificationBLO.listAll( cnd );
		list.forEach( clsf -> clsf.getRelateMaterialList()
									  .forEach( material -> System.out.println( clsf.getName() + " : " + material.getName() ) ) );
		
	}
	
	@Test
	public void 다국어_테스트() {
		ClassificationInfo master = new ClassificationInfo();
		
		master.setOid( OIDGenerator.generateOID() );
		master.setParentOid( "00000000001" );
		master.setPartOid( "1SWKGVwR000" );
		master.setCategoryType( "AMCFPRDT" );
		
		List<CategoryInfo> langList = new ArrayList<>();
		
		CategoryInfo detail = new CategoryInfo();
		
		detail.setOid( master.getOid() );
		detail.setLang( SystemConstants.LANG_KOR.getKey() );
		detail.setName( "다국어 테스트 - KO" );
		
		langList.add( detail );
		
		detail = new CategoryInfo();
		
		detail.setOid( master.getOid() );
		detail.setLang( SystemConstants.LANG_ENG.getKey() );
		detail.setName( "다국어 테스트 - ENG" );
		
		langList.add( detail );
		
		detail = new CategoryInfo();
		
		detail.setOid( master.getOid() );
		detail.setLang( SystemConstants.LANG_CHN.getKey() );
		detail.setName( "다국어 테스트 - CHN" );
		
		langList.add( detail );
		
		master.setLangCategoryList( langList );
		
		classificationBLO.insert( master );
		

	}
}
