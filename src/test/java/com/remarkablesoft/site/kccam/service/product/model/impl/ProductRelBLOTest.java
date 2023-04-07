package com.remarkablesoft.site.kccam.service.product.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.mgnt.part.vo.PartInfo;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.site.kccam.service.product.vo.ProductRelInfo;


/**
 * 	                                                           
 * 		@주시스템		:	kccam
 * 		@서브 시스템		:	product - productRel
 * 		@프로그램 ID		:	ProductRelBLOTest
 * 		@프로그램 개요	:	ProductRel BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

public class ProductRelBLOTest extends BaseModelTest {

    @Autowired
    protected ProductRelBLO productRelBLO;

    @Test
    public void insert_테스트() {

    	ProductRelInfo info = new ProductRelInfo();
    	
    	info.setProductOid( "1STDTtod001" );
    	info.setTargetOid( "1STIn3vT000" );
        info.setTargetObject( ClassificationInfo.getMarketObjectType() );
        
        info = productRelBLO.insert( info );
        System.out.println( info.toString() );
    
    }

    @Test
    public void deleteCnd_테스트() {
        ProductCnd cnd =  new ProductCnd();
        cnd.setOid( "1SRG2dsw000" );
        cnd.setTargetOid( "1SRG2dsw001" );
        int result = productRelBLO.delete( cnd );
        System.out.println( result );
    }

    @Test
    public void get_테스트() {

        ProductCnd cnd = new ProductCnd();
        cnd.setOid( "1SRG2dsw000" );
        cnd.setTargetOid( "1SRG2dsw001" );
        
        ProductRelInfo info = productRelBLO.get( cnd );
        System.out.println( info.toString() );
    
    }

    @Test
    public void listAll_테스트() {
    
		ProductCnd cnd =  new ProductCnd();
		List<ProductRelInfo> list =  productRelBLO.listAll( cnd );

		list.forEach( System.out::println ); 
    }
    
    @Test
    public void insertAll_테스트() {
    	List< ProductRelInfo > pList = new ArrayList<ProductRelInfo>();
    	
    	ProductRelInfo info = new ProductRelInfo();
    	
    	info.setProductOid( OIDGenerator.generateOID() );
    	info.setTargetOid( "1ST1pWPm000" );
        info.setTargetObject( PartInfo.getObjectType() );
        
        pList.add( info );
        int i = productRelBLO.insertBulk( pList );
        System.out.println( info.toString() );
    }
    

}
