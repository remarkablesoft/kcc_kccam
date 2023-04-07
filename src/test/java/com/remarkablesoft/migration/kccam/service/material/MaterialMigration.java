package com.remarkablesoft.migration.kccam.service.material;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;
import com.remarkablesoft.framework.util.AssertUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.migration.kccam.service.MigrationUtil;
import com.remarkablesoft.site.kccam.service.material.model.impl.MaterialBLO;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;

/**
 * @주시스템				:	kccam
 * @서브 시스템			:   migration - material
 * @프로그램 개요			:   소재 마이그레이션
 * @변경이력 
 * ============================================================================
 * 1.0 2021-03-16 : 최원준 - 신규생성
 * ============================================================================
 */
public class MaterialMigration extends BaseModelTest {
	
	@Autowired
	protected MaterialBLO materialBLO;
	
	@Test
	@Transactional
	public void 소재구분_마이그레이션() {
		
		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\01소재구분.csv";
		
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
				
				MaterialInfo info = new MaterialInfo();
				boolean fillResult;
				for ( int i = 0; i < fieldArr.length; i++ ) {
					fillResult = fillMaterialInfo( info, i, fieldArr[ i ] );
					AssertUtils.isTrue( !fillResult, "Migration Error! Error Row : " + row + ", Error Index : " + i );
				}
				
				info = materialBLO.insert( info );
				AssertUtils.isTrue( info == null, "Migration Error! Error Row : " + row );
			}
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * MatrialInfo 정보를 채워줍니다.
	 *
	 * @param info
	 * @param index
	 * @param field
	 * @author 최원준
	 */
	private boolean fillMaterialInfo( MaterialInfo info, int index, String field ) {
		
		if ( StringUtils.isEmpty( field ) ) {
			return false;
		}
		
		switch ( index ) {
			case 1: {
				info.setName( field );
				break;
			}
			case 2: {
				info.setClassName( MigrationUtil.parseText( field ) );
				break;
			}
			case 3: {
				ContentsInfo contentsInfo = SystemFactory.getContentsInfo();
				contentsInfo.setContents( MigrationUtil.parseText( field ) );
				info.setMainContents( contentsInfo );
				break;
			}
			default: {
				break;
			}
		}
		
		return true;
	}
	
}
