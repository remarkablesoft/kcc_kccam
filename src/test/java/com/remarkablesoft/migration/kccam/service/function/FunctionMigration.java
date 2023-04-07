package com.remarkablesoft.migration.kccam.service.function;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.util.AssertUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.migration.kccam.service.MigrationUtil;
import com.remarkablesoft.site.kccam.service.AmConstants;
import com.remarkablesoft.site.kccam.service.classification.model.impl.ClassificationBLO;
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
 * @서브 시스템			:   migration - function
 * @프로그램 ID			:   FunctionMigration.java
 * @프로그램 개요			:   Function 마이그레이션
 * @변경이력 
 * ============================================================================
 * 1.0 2021-03-23 : 최원준 - 신규생성
 * ============================================================================
 */
public class FunctionMigration extends BaseModelTest {
	
	@Autowired
	protected ClassificationBLO classificationBLO;
	
	@Autowired
	protected MaterialBLO materialBLO;
	
	@Test
	@Transactional
	public void Function_마이그레이션() {
		
		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\03Function.csv";
		
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
				info.setCategoryType( ClassificationInfo.AM_CLASSIFICATION_TYPE_FUNCTION );
				info.setParentOid( AmConstants.ROOT_TREE_OID_FUNCTION );
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
		
		// Function 명 필수
		if ( index == 1 && StringUtils.isEmpty( field ) ) {
			return false;
		}
		
		switch ( index ) {
			case 1: {
				info.setName( field );
				break;
			}
			case 2: {
				info.setDescr( MigrationUtil.parseText( field ) );
				break;
			}
			case 3 : {
				fillMaterial( info, field );
			}
			default: {
				break;
			}
		}
		
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
