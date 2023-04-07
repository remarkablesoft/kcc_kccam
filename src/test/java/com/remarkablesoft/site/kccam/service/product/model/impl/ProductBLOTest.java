package com.remarkablesoft.site.kccam.service.product.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductRelInfo;


/**
 *
 * 		@주시스템		:	kccam
 * 		@서브 시스템		:	product - product
 * 		@프로그램 ID		:	ProductBLOTest
 * 		@프로그램 개요	:	제품정보 BLO
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

public class ProductBLOTest extends BaseModelTest {

    @Autowired
    protected ProductBLO productBLO;

    @Test
    public void insert_테스트() {

    	ProductInfo info = new ProductInfo();
    	info.setProductCode( "a1.test2" );
    	info.setName( "제품1" );
    	info.setDescr( "제품설명입니다!" );
    	info.setMaterialOid( OIDGenerator.generateOID() );
    	info.setReleaseYn( SystemConstants.FLAG_NO );

    	info.setMainFuncOid( OIDGenerator.generateOID() );
    	info.setInputUser( OIDGenerator.generateOID() );

    	info.setCustomField1( "cstm1" );
    	info.setCustomField2( "cstm2" );
    	info.setCustomField3( "cstm3" );
    	info.setCustomField4( "cstm4" );
    	info.setCustomField5( "cstm5" );

    	info = productBLO.insert( info );
        System.out.println( info.toString() );

    }

    @Test
    public void update_테스트() {

    	ProductInfo info = new ProductInfo();

    	info.setOid( "1SRFx2jU003" );

    	info.setProductCode( "KCC1.AQWW" );
    	info.setName( "이름22" );
    	info.setMaterialOid( OIDGenerator.generateOID() );
    	info.setReleaseYn( SystemConstants.FLAG_YES );
    	info.setMainFuncOid( OIDGenerator.generateOID() );

    	info.setModUser( OIDGenerator.generateOID() );
    	info.setCustomField1( "cstm5" );
    	info.setCustomField2( "cstm4" );
    	info.setCustomField3( "cstm3" );
    	info.setCustomField4( "cstm2" );
    	info.setCustomField5( "cstm1" );

    	info = productBLO.update( info );
        System.out.println( info.toString() );

    }

    @Test
    public void delete_테스트() {

        //int result = productBLO.delete( "1SRFx2jU003" );
        //System.out.println( result );

    }

    @Test
    public void get_테스트() {

        ProductCnd cnd = new ProductCnd();
        cnd.setOid( "1SRFx2jU003" );
        ProductInfo info = productBLO.get( cnd );
        System.out.println( info.toString() );

    }

    @Test
    public void list_테스트() {

		ProductCnd cnd =  new ProductCnd();
		PageList<ProductInfo> list =  productBLO.list( cnd );

		list.forEach( System.out::println );
    }

    @Test
    public void listAll_테스트() {

		ProductCnd cnd =  new ProductCnd();
		List<ProductInfo> list =  productBLO.listAll( cnd );

		list.forEach( System.out::println );
    }

    @Test
    public void exist_테스트() {
        ProductCnd cnd = new ProductCnd();
        cnd.setOid( "1SRFx2jU003" );
        boolean result = productBLO.exist( cnd );
        System.out.println( result );
    }


    @Test
	public void 정규식_테스트() {
    	String testText = "123\\123";

    	String[] testArr = testText.split( "\\\\" );

    	for( String text : testArr ) {
			System.out.println( text );
		}

	}


    /*
     * Product & ProductRel & Contents &  Doc INSERT
     * */
    @Test
    public void  insert_관련정보() {

    	//inst
    	ProductInfo info = new ProductInfo();
    	DocInfo doc = new DocInfo();
    	ProductRelInfo rel = new ProductRelInfo();
    	ContentsInfo con = new ContentsInfo();

    	List<ProductRelInfo> productRelList = new ArrayList<ProductRelInfo>();
    	List<DocInfo> docList = new ArrayList<DocInfo>();
    	List<ContentsInfo> addContentsList = new ArrayList<ContentsInfo>();


    	//Product
    	info.setOid( OIDGenerator.generateOID() );
    	info.setProductCode( "AMTEST" );
    	info.setName( "testProduct" );
    	info.setInputUser("12345");
    	info.setCustomField1( "cstm5" );
    	info.setCustomField2( "cstm4" );
    	info.setCustomField3( "cstm3" );
    	info.setCustomField4( "cstm2" );
    	info.setCustomField5( "cstm1" );

    	//rel
    	rel.setTargetOid( OIDGenerator.generateOID() );
    	rel.setTargetObject( info.getObjectType() );


    	//doc
    	doc.setDelUser( OIDGenerator.generateOID() );
    	doc.setCurrentVersionOid( OIDGenerator.generateOID() );
    	doc.setSecretYn( SystemConstants.FLAG_NO );
    	doc.setTitle( "TEST" );

    	//contents
    	con.setContents( "TEST" );
    	con.setContentsSize( 60 );
    	con.setContentsTitle( "" );
    	con.setOrderNo( 1 );
    	con.setInputUser( OIDGenerator.generateOID() );

    	productRelList.add(rel);
    	docList.add(doc);
    	addContentsList.add(con);

    	info.setProductRelList(productRelList);
    	info.setDocList(docList);
    	info.setAddContentsList(addContentsList);

    	ProductInfo newInfo = productBLO.insert(info);
    	System.out.println( newInfo );
    	System.out.println( "==============" );
    	newInfo.getProductRelList().forEach( System.out::println );
    	System.out.println( "==============" );
    	newInfo.getDocList().forEach( System.out::println );
    	System.out.println( "==============" );
    	newInfo.getAddContentsList().forEach( System.out::println );
    	System.out.println( "==============" );


    }

    @Ignore @Test
    public void fill_테스트_material제외() {

    	//1ST6THaA000
    	//Product + Doc + content

    	//get 호출 시 material이 없으면 모든 material을 get으로 호출한다: 따라서 2개 열이 나와서 오류
    	ProductCnd cnd = new ProductCnd();
    	cnd.setOid( "1ST6THaA000" );

    	ProductInfo newInfo = productBLO.get(cnd);
    	System.out.println( newInfo );
    	System.out.println( "==============" );
    	newInfo.getProductRelList().forEach( System.out::println );
    	System.out.println( "==============" );
    	newInfo.getDocList().forEach( System.out::println );
    	System.out.println( "==============" );
    	newInfo.getAddContentsList().forEach( System.out::println );
    	System.out.println( "==============" );
    }

    @Test
    public void fill_테스트_모든정보_get() {

    	ProductCnd cnd = new ProductCnd();
    	cnd.setOid( "1ST6THaA000" );
    	cnd.setMaterialOid( "1SSTsCb4000" );

    	ProductInfo newInfo = productBLO.get(cnd);
    	System.out.println( newInfo );
    	System.out.println( "==============" );
    	newInfo.getProductRelList().forEach( System.out::println );
    	System.out.println( "==============" );
    	newInfo.getDocList().forEach( System.out::println );
    	System.out.println( "==============" );
    	newInfo.getAddContentsList().forEach( System.out::println );
    	System.out.println( "==============" );
    	newInfo.getAddContentsList().forEach( System.out::println );
    	System.out.println( "==============" );
    	System.out.println( newInfo.getMaterialInfo() );

    }
}
