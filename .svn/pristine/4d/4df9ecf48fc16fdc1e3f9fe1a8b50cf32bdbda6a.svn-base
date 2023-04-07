package com.remarkablesoft.migration.kccam.service.datasheet;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.util.AssertUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.migration.kccam.service.MigrationUtil;
import com.remarkablesoft.site.kccam.service.datasheet.model.impl.DatasheetBLO;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetInfo;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetItemInfo;
import com.remarkablesoft.site.kccam.service.product.model.impl.ProductBLO;
import com.remarkablesoft.site.kccam.service.product.model.impl.ProductRelBLO;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductRelInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @주시스템 		:	kccam
 * @서브 시스템		:   migration - datasheet
 * @프로그램 ID		:   DatasheetMigration.java
 * @프로그램 개요		:   데이터시트 정보 마이그레이션
 * @변경이력 
 * ============================================================================
 * 1.0 2021-03-28 : 최원준 - 신규생성
 * ============================================================================
 */
public class DatasheetMigration extends BaseModelTest {
	
	@Autowired
	protected ProductBLO productBLO;
	
	@Autowired
	protected ProductRelBLO productRelBLO;
	
	@Autowired
	protected DatasheetBLO datasheetBLO;
	
	private static final int PRODUCT_START_INDEX = 3; // 5
	
	@Test
	@Transactional
	public void Datasheet_마이그레이션() {
		
//		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\07EMC_DATASHEET.csv";		// 3
//		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\08접착제_DATASHEET.csv";		// 3
//		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\09언더필_DATASHEET.csv";		// 3
//		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\10UV몰딩재_DATASHEET.csv";	// 3
//		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\11장섬유_DATASHEET.csv";		// 5
//		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\12Ceramic_Sub_DATASHEET.csv";	// 3
		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\13MetalizedCeramic_DATASHEET.csv";	// 3
		
		
		List<String> errorProductNameList = new ArrayList<>();
		
		try {
			
			BufferedReader br = MigrationUtil.getFileBufferedReader( filePath );
			String line;
			
			int row = 0;
			List<ProductRelInfo> productRelList = new ArrayList<>();
			List<DatasheetInfo> datasheetList = new ArrayList<>();
			
			while ( ( line = br.readLine() ) != null ) {
				
				row++;
				if ( row == 1 ) {
					checkProductName( line, errorProductNameList );
					boolean setResult = setProductList( line, productRelList, datasheetList );
					AssertUtils.isTrue( !setResult, "Migration Error! Error occurred at setProductList" );
					continue;
				}
				
				boolean fillResult = fillInfo( line, datasheetList );
				AssertUtils.isTrue( !fillResult, "Migration Error! Error Row : " + row );
				
			}
			
			boolean insertResult = insertInfo( productRelList, datasheetList );
			AssertUtils.isTrue( !insertResult, "Migration insert Error!" );
			
			errorProductNameList.forEach( System.out::println );
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
	}
	
	private void checkProductName( String line, List<String> errorProductNameList ) {
		
		String[] fieldArr = line.split( MigrationUtil.MIGRATION_CSV_DELIMITER, -1 );
		
		boolean isExistProduct = true;
		for( int i = PRODUCT_START_INDEX; i < fieldArr.length; i++ ) {
			ProductCnd productCnd = new ProductCnd();
			productCnd.setName( fieldArr[ i ] );
			ProductInfo productInfo = productBLO.get( productCnd );
			
			if ( productInfo == null || StringUtils.isEmpty( productInfo.getOid() ) ) {
				errorProductNameList.add( fieldArr[i] );
			}
		}
		
	}
	
	/**
	* 첫번째 줄을 기준으로 제품 리스트를 만들어줍니다.
	*
	* @param line				제품명이 들어있는 line
	* @param productRelList		제품 관계정보 리스트
	* @param datasheetList		데이터시트 리스트
	* @return boolean
	* @author 최원준
	*/
	private boolean setProductList( String line, List<ProductRelInfo> productRelList, List<DatasheetInfo> datasheetList ) {
		String[] fieldArr = line.split( MigrationUtil.MIGRATION_CSV_DELIMITER, -1 );
		
		boolean isExistProduct = true;
		for( int i = PRODUCT_START_INDEX; i < fieldArr.length; i++ ) {
			ProductCnd productCnd = new ProductCnd();
			productCnd.setName( fieldArr[i] );
			ProductInfo productInfo = productBLO.get( productCnd );
			
			if ( productInfo == null || StringUtils.isEmpty( productInfo.getOid() ) ) {
				isExistProduct = false;
				break;
			}
			
			DatasheetInfo datasheetInfo = new DatasheetInfo();
			String oid = OIDGenerator.generateOID();
			datasheetInfo.setOid( oid ).setTitle( productInfo.getName() + " Datasheet" ).setStdDatasheetOid( oid );
			datasheetList.add( datasheetInfo );
			
			ProductRelInfo productRelInfo = new ProductRelInfo();
			productRelInfo.setProductOid( productInfo.getOid() );
			productRelInfo.setTargetOid( datasheetInfo.getOid() ).setTargetObject( DatasheetInfo.getObjectType() );
			
			productRelList.add( productRelInfo );
		}
		
		return isExistProduct;
	}

	/**
	* 데이터시트 정보를 채워줍니다.
	*
	* @param line	데이터 row
	* @param datasheetList		데이터시트 리스트
	* @return boolean
	* @author 최원준
	*/
	private boolean fillInfo( String line, List<DatasheetInfo> datasheetList ) {
		
		String[] fieldArr = line.split( MigrationUtil.MIGRATION_CSV_DELIMITER, -1 );
		if ( fieldArr.length == 0 ) {
			return false;
		}
		
		String itemName = fieldArr[0];
		String unit = fieldArr[1];
		String typical = "";
		String testMethod = "";
		
		if ( PRODUCT_START_INDEX == 5 ) {
			typical = fieldArr[2];
			testMethod = fieldArr[3];
		}
		
		for( int i = PRODUCT_START_INDEX; i < fieldArr.length; i++ ) {
			
			if ( StringUtils.isEmpty( fieldArr[i] ) || "-".equals( fieldArr[i] ) ) {
				continue;
			}
			
			DatasheetItemInfo datasheetItemInfo = new DatasheetItemInfo();
			datasheetItemInfo.setName( itemName ).setItemUnit( unit ).setTypical( typical ).setTestMethod( testMethod );
			datasheetItemInfo.setItemValue( fieldArr[i] );
			
			DatasheetInfo datasheetInfo = datasheetList.get( i - PRODUCT_START_INDEX );
			datasheetInfo.addDatasheetItem( datasheetItemInfo );
			
		}
		
		return true;
	} 

	/**
	* 데이터시트 정보를 저장합니다.
	*
	* @param productRelList
	* @param datasheetList
	* @author 최원준
	*/
	private boolean insertInfo( List<ProductRelInfo> productRelList, List<DatasheetInfo> datasheetList ) {
		
		if ( CollectionUtils.isEmpty( productRelList ) || CollectionUtils.isEmpty( datasheetList ) || productRelList.size() != datasheetList.size() ) {
			return false;
		}
		
		boolean insertResult = true;
		for ( int i = 0; i < productRelList.size(); i++ ) {
			ProductRelInfo productRelInsertResult = productRelBLO.insert( productRelList.get( i ) );
			DatasheetInfo datasheetInsertResult = datasheetBLO.insert( datasheetList.get( i ) );
			
			if ( productRelInsertResult == null || datasheetInsertResult == null ) {
				insertResult = false;
				break;
			}
		}
		
		return insertResult;
	}
	
}
