package com.remarkablesoft.migration.kccam.service.classfication;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.util.AssertUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.migration.kccam.service.MigrationUtil;
import com.remarkablesoft.site.kccam.service.AmConstants;
import com.remarkablesoft.site.kccam.service.classification.model.impl.ClassificationBLO;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationCnd;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationInfo;
import com.remarkablesoft.site.kccam.service.material.model.impl.MaterialBLO;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialCnd;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;
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
 * @주시스템				:	kccam
 * @서브 시스템			:   migration - classification
 * @프로그램 ID			:   ApplicationMigration.java
 * @프로그램 개요			:   Application 정보 마이그레이션
 * @변경이력 
 * ============================================================================
 * 1.0 2021-03-23 : 최원준 - 신규생성
 * ============================================================================
 */
public class ApplicationMigration extends BaseModelTest {
	
	@Autowired
	protected ClassificationBLO classificationBLO;
	
	@Autowired
	protected MaterialBLO materialBLO;
	
	@Autowired
	protected ProductBLO productBLO;
	
	@Autowired
	protected ProductRelBLO productRelBLO;
	
	@Test
	@Transactional
	public void Application_마이그레이션() {
		
//		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\14_1Application_1depth.csv";
//		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\14_2Application_2depth.csv";
//		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\14_3Application_3depth.csv";
		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\14_4Application_4depth.csv";
		
		try {
			
			BufferedReader br = MigrationUtil.getFileBufferedReader( filePath );
			String line;
			
			List<String> errorLogList = new ArrayList<>();
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
				info.setCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_APPLICATION );
				
				boolean fillResult;
				for ( int i = 0; i < fieldArr.length; i++ ) {
					fillResult = fillInfo( info, i, fieldArr[ i ] );
//					AssertUtils.isTrue( !fillResult, "Migration Error! Error Row : " + row + ", Error Index : " + i );
					if ( !fillResult ) {
						errorLogList.add( "Migration Error! Error Row : " + row + ", Error Index : " + i );
					}
				}
				
				info = classificationBLO.insert( info );
				AssertUtils.isTrue( info == null, "Migration Error! Error Row : " + row );
			}
			
			errorLogList.forEach( System.out::println );
			
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
	private boolean fillInfo( ClassificationInfo info, int index, String field ) {
		
		// Application명 필수
		if ( index == 1 && StringUtils.isEmpty( field ) ) {
			return false;
		}
		
		boolean fillResult = true;
		switch ( index ) {
			case 1: {
				info.setName( MigrationUtil.parseText( field ) );
				break;
			}
			case 2: {
				fillResult = fillMaterial( info, field );
				break;
			}
			case 3: {
				int depth = 0;
				break;
			}
			case 4: {
				fillResult = fillParentClassification( info, field );
				break;
			}
			case 6: {
				fillResult = fillProductRel( info, field );
				break;
			}
			case 7: {
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
	 * 소재 정보를 채워줍니다.
	 *
	 * @param materialName
	 * @author 최원준
	 */
	private boolean fillMaterial( ClassificationInfo info, String materialName ) {
		
		if ( StringUtils.isEmpty( materialName ) ) {
			return false;
		}
		
		MaterialCnd materialCnd = new MaterialCnd();
		materialCnd.setName( materialName );
		MaterialInfo materialInfo = materialBLO.get( materialCnd );
		
		if ( materialInfo == null ) {
			return false;
		}
		
		info.setMaterialOid( materialInfo.getOid() );
		return true;
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
			info.setParentOid( AmConstants.ROOT_TREE_OID_APPLICATION );
			return true;
		}
		
		ClassificationCnd cnd = new ClassificationCnd();
		cnd.setName( MigrationUtil.parseText( parentClassificationName ) );
		cnd.setCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_APPLICATION );
		cnd.setPartOid( info.getPartOid() );
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
	 * Application과 연관된 제품 정보를 저장합니다.
	 *
	 * @param info
	 * @param field
	 * @return boolean
	 * @author 최원준
	 */
	private boolean fillProductRel( ClassificationInfo info, String field ) {
		if ( StringUtils.isEmpty( field ) ) {
			return true;
		}
		
		String[] productNameArr = field.split( "\\\\" );
		
		boolean returnResult = true;
		for ( String productName : productNameArr ) {
			ProductCnd productCnd = new ProductCnd();
			productCnd.setName( MigrationUtil.parseText( productName ) );
			
			ProductInfo product = productBLO.get( productCnd );
			
			if ( product == null ) {
				returnResult = false; 
				break;
			}
			
			ProductRelInfo productRel = new ProductRelInfo();
			productRel.setProductOid( product.getOid() );
			productRel.setTargetObject( ClassificationInfo.getApplicationObjectType() );
			info.addProductRelInfo( productRel );
			
		}
		
		return returnResult;
	}
	
	
}
