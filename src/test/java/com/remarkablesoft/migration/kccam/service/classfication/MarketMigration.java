package com.remarkablesoft.migration.kccam.service.classfication;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.mgnt.category.model.impl.CategoryBLO;
import com.remarkablesoft.framework.util.AssertUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.migration.kccam.service.MigrationUtil;
import com.remarkablesoft.site.kccam.service.AmConstants;
import com.remarkablesoft.site.kccam.service.classification.model.impl.ClassificationBLO;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationCnd;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationInfo;
import com.remarkablesoft.site.kccam.service.material.model.impl.MaterialBLO;
import com.remarkablesoft.site.kccam.service.product.model.impl.ProductBLO;
import com.remarkablesoft.site.kccam.service.product.model.impl.ProductRelBLO;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductRelInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @주시스템 :	kccam
 * @서브 시스템        :   migration - classification
 * @프로그램 ID        :   MarketMigration.java
 * @프로그램 개요        :   마켓 정보 마이그레이션
 * @변경이력 :
 * ============================================================================
 * 1.0 2021-03-23 : 최원준 - 신규생성
 * ============================================================================
 */
public class MarketMigration extends BaseModelTest {
	
	@Autowired
	protected ClassificationBLO classificationBLO;
	
	@Autowired
	protected MaterialBLO materialBLO;
	
	@Autowired
	protected ProductBLO productBLO;
	
	@Autowired
	protected ProductRelBLO productRelBLO;
	
	@Autowired
	protected CategoryBLO categoryBLO;
	
	@Test
	@Transactional
	public void Market_마이그레이션() {
		
		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\15Market.csv";
		
		try {
			
			BufferedReader br = MigrationUtil.getFileBufferedReader( filePath );
			String line;
			List<String> errorLogList = new ArrayList<>();
			List<String> productNameList = new ArrayList<>();
			int row = 0;
			
			while ( ( line = br.readLine() ) != null ) {
				
				row++;
				if ( row == 1 ) {
					continue;
				}
				
				String[] fieldArr = line.split( MigrationUtil.MIGRATION_CSV_DELIMITER, -1 );
				if ( fieldArr.length == 0 ) {
					continue;
				}
				
				ClassificationInfo info = new ClassificationInfo();
				info.setOid( OIDGenerator.generateOID() );
				info.setCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_MARKET );
				
				boolean fillResult;
				for ( int i = 0; i < fieldArr.length; i++ ) {
					fillResult = fillInfo( info, i, fieldArr[ i ], errorLogList, productNameList );
//					AssertUtils.isTrue( !fillResult, "Migration Error! Error Row : " + row + ", Error Index : " + i );
					if ( !fillResult ) {
						errorLogList.add( "Migration Error! Error Row : " + row + ", Error Index : " + i );
					}
				}
				
//				info = classificationBLO.insert( info );.
				categoryBLO.insert( info );
				
				for( ProductRelInfo rel : info.getProductRelList() ) {
					ProductCnd existProductCnd = new ProductCnd();
					existProductCnd.setProductOid( rel.getProductOid() ).setTargetOid( rel.getTargetOid() );
					if ( !productRelBLO.exist( existProductCnd ) ) {
						productRelBLO.insert( rel );
					}
				}
				
				AssertUtils.isTrue( info == null, "Migration Error! Error Row : " + row );
			}
			
			errorLogList.forEach( System.out::println );
			System.out.println( "======= migration Rel ===========" );
			productNameList.forEach( System.out::println );
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * info 정보를 채워줍니다.
	 *
	 * @param info
	 * @param index
	 * @param field
	 * @author 최원준
	 */
	private boolean fillInfo( ClassificationInfo info, int index, String field, List<String> errorLogList, List<String> productNameList ) {
		
		// Application명 필수
		if ( index == 1 && StringUtils.isEmpty( field ) ) {
			return false;
		}
		
		boolean fillResult = true;
		switch ( index ) {
			case 1: {
				info.setName( field );
				break;
			}
			case 3: {
				fillResult = fillParentClassification( info, field );
				break;
			}
			case 5: {
				fillResult = fillProductRel( info, field, errorLogList, productNameList );
				break;
			}
			case 6: {
				info.setDescr( MigrationUtil.parseText( field ) );
				break;
			}
			default: {
				break;
			}
		}
		
		return fillResult;
	}
	
	/**
	 * 상위 구분정보를 채워줍니다.
	 *
	 * @param info
	 * @param parentClassificationName
	 * @author 최원준
	 */
	private boolean fillParentClassification( ClassificationInfo info, String parentClassificationName ) {
		
		if ( StringUtils.isEmpty( parentClassificationName ) ) {
			info.setParentOid( AmConstants.ROOT_TREE_OID_MARKET );
			return true;
		}
		
		ClassificationCnd cnd = new ClassificationCnd();
		cnd.setName( parentClassificationName );
		cnd.setCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_MARKET );
		ClassificationInfo parentInfo = classificationBLO.get( cnd );
		
		if ( parentInfo == null ) {
			return false;
		}
		
		info.setParentOid( parentInfo.getOid() );

//        int parentInfoDepth = parentInfo.getFullPathIndex().split( "|" ).length;
//        info.setDepthVC( parentInfoDepth );
		
		return true;
	}
	
	/**
	 * Market 과 연관된 제품 정보를 저장합니다.
	 *
	 * @param info
	 * @param field
	 * @return boolean
	 * @author 최원준
	 */
	private boolean fillProductRel( ClassificationInfo info, String field, List<String> errorLogList, List<String> productNameList ) {
		if ( StringUtils.isEmpty( field ) ) {
			return true;
		}
		//5900GUD\5500GB
		String[] productNameArr = field.split( "\\\\" );
		
		for ( String productName : productNameArr ) {
			ProductCnd productCnd = new ProductCnd();
			productCnd.setName( productName );
			ProductInfo product = productBLO.get( productCnd );
			
			if ( product == null ) {
				errorLogList.add( productName );
				continue;
			}
			
			ProductRelInfo productRel = new ProductRelInfo();
			productRel.setProductOid( product.getOid() );
			productRel.setTargetObject( ClassificationInfo.getMarketObjectType() );
			productRel.setTargetOid( info.getOid() );
			info.addProductRelInfo( productRel );
			productNameList.add( "OID : " + product.getOid() + ", TargetOid : " + info.getOid() + ", TargetObject : " + ClassificationInfo.getMarketObjectType() + ", productName : " + productName );
		}
		
		return true;
	}
	
	
}
