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
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;

/**
 * @주시스템				:	kccam
 * @서브 시스템			:   migration - classification
 * @프로그램 ID			:   ClassificationMigration.java
 * @프로그램 개요			:   제품구분 마이그레이션
 * @변경이력 
 * ============================================================================
 * 1.0 2021-03-16 : 최원준 - 신규생성
 * ============================================================================
 */
public class ClassificationMigration extends BaseModelTest {
	
	@Autowired
	protected ClassificationBLO classificationBLO;
	
	@Autowired
	protected MaterialBLO materialBLO;
	
	@Test
	@Transactional
	public void 제품구분_마이그레이션() {
		
		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\02제품구분.csv";
		
		try {
			
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
				
				ClassificationInfo info = new ClassificationInfo();
				info.setCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_PRODUCT );
				
				boolean fillResult;
				for ( int i = 0; i < fieldArr.length; i++ ) {
					fillResult = fillInfo( info, i, fieldArr[ i ] );
					AssertUtils.isTrue( !fillResult, "Migration Error! Error Row : " + row + ", Error Index : " + i );
				}
				
				info = classificationBLO.insert( info );
				AssertUtils.isTrue( info == null, "Migration Error! Error Row : " + row );
			}
			
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
		
		// 제품 구분명 필수
		if ( index == 1 && StringUtils.isEmpty( field ) ) {
			return false;
		}
		
		boolean fillResult = true;
		switch ( index ) {
			case 1: {
				info.setName( field );
				break;
			}
			case 2: {
				fillResult = fillParentClassification( info, field );
				break;
			}
			case 3: {
				fillResult = fillMaterial( info, field );
				break;
			}
			case 4: {
				info.setDescr( MigrationUtil.parseText( field ) );
				break;
			}
//			case 5: {
//				if ( StringUtils.hasText( info.getParentOid() ) ) {
//					fillResult = info.getDepthVC() == Integer.valueOf( field );
//				}
//				break;
//			}
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
			info.setParentOid( AmConstants.ROOT_TREE_OID_PRODUCT );
			info.setDepthVC( 2 );
			return true;
		}
		
		ClassificationCnd cnd = new ClassificationCnd();
		cnd.setName( parentClassificationName );
		cnd.setCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_PRODUCT );
		ClassificationInfo parentInfo = classificationBLO.get( cnd );
		
		if ( parentInfo == null ) {
			return false;
		}
		
		info.setParentOid( parentInfo.getOid() );
		
		int parentInfoDepth = parentInfo.getFullPathIndex().split( "\\|" ).length;
		info.setDepthVC( parentInfoDepth + 1 );
		
		return true;
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
	
	
}
