package com.remarkablesoft.migration.kccam.service.product;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.service.org.branch.model.impl.BranchBLO;
import com.remarkablesoft.framework.service.org.branch.vo.BranchCnd;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;
import com.remarkablesoft.framework.service.org.user.model.impl.UserDAO;
import com.remarkablesoft.framework.service.org.user.vo.UserCnd;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.AssertUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.migration.kccam.service.MigrationUtil;
import com.remarkablesoft.site.kccam.service.classification.model.impl.ClassificationBLO;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationCnd;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationInfo;
import com.remarkablesoft.site.kccam.service.material.model.impl.MaterialBLO;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialCnd;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;
import com.remarkablesoft.site.kccam.service.product.model.impl.ProductBLO;
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
 * @서브 시스템			:   migration - product
 * @프로그램 ID			:   ProductMigration.java
 * @프로그램 개요			:   제품 마이그레이션
 * @변경이력 
 * ============================================================================
 * 1.0 2021-03-23 : 최원준 - 신규생성
 * ============================================================================
 */
public class ProductMigration extends BaseModelTest {
	
	@Autowired
	protected ProductBLO productBLO;
	
	@Autowired
	protected MaterialBLO materialBLO;
	
	@Autowired
	protected ClassificationBLO classificationBLO;
	
	@Autowired
	protected BranchBLO branchBLO;
	
	@Autowired
	protected UserDAO userDAO;
	
	@Test
	@Transactional
	public void 제품_마이그레이션() {
		
		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\06제품.csv";
		
		try {
			List<String> errorLogList = new ArrayList<>();
			BufferedReader br = MigrationUtil.getFileBufferedReader( filePath );
			String line;
			
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
				
				ProductInfo info = new ProductInfo();
				
				boolean fillResult;
				for ( int i = 0; i < fieldArr.length; i++ ) {
					fillResult = fillInfo( info, i, fieldArr[ i ] );
//					AssertUtils.isTrue( !fillResult, "Migration Error! Error Row : " + row + ", Error Index : " + i );
					if ( !fillResult ) {
						errorLogList.add( "Migration Error! Error Row : " + row + ", Error Index : " + i );
					}
				}
				
				info = productBLO.insert( info );
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
	private boolean fillInfo( ProductInfo info, int index, String field ) {
		
		// 제품명 필수
		if ( index == 1 && StringUtils.isEmpty( field ) ) {
			return false;
		}
		// 소재명
		else if ( index == 3 && StringUtils.isEmpty( field ) ) {
			return false;
		}
		
		boolean fillResult = true;
		switch ( index ) {
			case 1: {	// 제품명
				info.setName( field );
				break;
			}
			case 2: {	// 아이템코드
				info.setProductCode( field );
				break;
			}
			case 3: {	// 소재명
				fillResult = fillMaterial( info, field );
				break;
			}
			case 4: {	// 제품구분명
				fillResult = fillProductClassification( info, field );
				break;
			}
			case 5: {	// Function
				fillResult = fillFunction( info, field );
				break;
			}
			case 6: {	// Main Function
				fillResult = fillMainFunction( info, field );
				break;
			}
			case 7: {	// 설명	
				info.setDescr( MigrationUtil.parseText( field ) );
				break;
			}
			case 8: {	// Contact Office
				fillContact( info, field );
				break;
			}
			case 9: {	// Contact Manager
				fillContactManager( info, field );
				break;
			}
			default: {
				break;
			}
		}
		
		return fillResult;
	}
	
	/**
	 * 소재정보를 채워줍니다.
	 *
	 * @param info
	 * @param field
	 * @author 최원준
	 */
	private boolean fillMaterial( ProductInfo info, String field ) {
		
		MaterialCnd materialCnd = new MaterialCnd();
		materialCnd.setName( field );
		MaterialInfo material = materialBLO.get( materialCnd );
		
		if ( material == null ) {
			return false;
		}
		
		info.setMaterialInfo( material );
		info.setMaterialOid( material.getOid() );
		
		return true;
	}
	
	/**
	 * 제품구분을 채워줍니다.
	 *
	 * @param info
	 * @return boolean
	 * @author 최원준
	 */
	private boolean fillProductClassification( ProductInfo info, String field ) {
		
		ClassificationCnd classificationCnd = new ClassificationCnd();
		classificationCnd.setName( field );
		classificationCnd.setCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_PRODUCT );
		ClassificationInfo productClassification = classificationBLO.get( classificationCnd );
		
		if ( productClassification == null ) {
			return false;
		}
		
		ProductRelInfo productRelInfo = new ProductRelInfo();
		productRelInfo.setTargetOid( productClassification.getOid() )
				.setTargetObject( ClassificationInfo.getProductObjectType() );
		
		List<ProductRelInfo> productRelList = new ArrayList<>();
		productRelList.add( productRelInfo );
		
		info.setProductRelList( productRelList );
		
		return true;
	}
	
	/**
	 * function을 채워줍니다.
	 *
	 * @param info
	 * @param field
	 * @return boolean
	 * @author 최원준
	 */
	private boolean fillFunction( ProductInfo info, String field ) {
		
		if ( StringUtils.isEmpty( field ) ) {
			return true;
		}
		
		String[] functionArr = field.split( MigrationUtil.MIGRATION_COMMA_DELIMITER );
		
		boolean functionAddResult = true;
		for ( String functionName : functionArr ) {
			
			ClassificationCnd classificationCnd = new ClassificationCnd();
			classificationCnd.setName( functionName );
			classificationCnd.setCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_FUNCTION );
			classificationCnd.setPartOid( info.getMaterialOid() );
			ClassificationInfo functionInfo = classificationBLO.get( classificationCnd );
			
			if ( functionInfo == null ) {
				functionAddResult = false;
				break;
			}
			
			ProductRelInfo productRelInfo = new ProductRelInfo();
			productRelInfo.setTargetOid( functionInfo.getOid() );
			productRelInfo.setTargetObject( ClassificationInfo.getFunctionObjectType() );
			
			info.addProductRelInfo( productRelInfo );
		}
		
		return functionAddResult;
	}
	
	/**
	 * Main Function을 채워줍니다.
	 *
	 * @param info
	 * @param field
	 * @return boolean
	 * @author 최원준
	 */
	private boolean fillMainFunction( ProductInfo info, String field ) {
		
		if ( StringUtils.isEmpty( field ) ) {
			return true;
		}
		
		ClassificationCnd classificationCnd = new ClassificationCnd();
		classificationCnd.setName( field );
		classificationCnd.setCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_FUNCTION );
		classificationCnd.setPartOid( info.getMaterialOid() );
		ClassificationInfo mainFunctionInfo = classificationBLO.get( classificationCnd );
		
		if ( mainFunctionInfo == null ) {
			return false;
		}
		
		info.getProductRelList().forEach( productRel -> {
			if ( productRel.getTargetOid().equals( mainFunctionInfo.getOid() ) ) {
				info.setMainFuncOid( mainFunctionInfo.getOid() );
			}
		} );
		
		return true;
	}
	
	/**
	 * Contact 지점 정보를 채워줍니다.
	 *
	 * @param info
	 * @param field
	 * @return boolean
	 * @author 최원준
	 */
	private boolean fillContact( ProductInfo info, String field ) {
		
		if ( StringUtils.isEmpty( field ) ) {
			return true;
		}
		
		BranchCnd branchCnd = new BranchCnd();
		branchCnd.setName( field );
		BranchInfo branchInfo = branchBLO.get( branchCnd );
		
		if ( branchInfo == null ) {
			return false;
		}
		
		ProductRelInfo productRelInfo = new ProductRelInfo();
		productRelInfo.setTargetOid( branchInfo.getOid() );
		productRelInfo.setTargetObject( BranchInfo.getObjectType() );
		
		info.addProductRelInfo( productRelInfo );
		
		return true;
	}
	
	/**
	* Contact 매니저 정보를 채워줍니다.
	*
	* @param info
	* @param field
	* @return boolean
	* @author 최원준
	*/
	private boolean fillContactManager( ProductInfo info, String field ) {
		
		if ( StringUtils.isEmpty( field ) ) {
			return true;
		}
		
		UserCnd userCnd = new UserCnd();
		userCnd.setName( field );
		UserInfo user = userDAO.getUser( userCnd );
		
		ProductRelInfo productRelInfo = new ProductRelInfo();
		productRelInfo.setTargetOid( user.getOid() );
		productRelInfo.setTargetObject( UserInfo.getObjectType() );
		
		info.addProductRelInfo( productRelInfo );
		
		return true;
	}
	
}
