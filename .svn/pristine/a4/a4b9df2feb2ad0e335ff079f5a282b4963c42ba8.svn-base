package com.remarkablesoft.migration.kccam.service.contact;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.service.org.branch.model.impl.BranchBLO;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;
import com.remarkablesoft.framework.util.AssertUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.migration.kccam.service.MigrationUtil;
import com.remarkablesoft.site.kccam.service.AmConstants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;

/**
 * @주시스템			:	kccam
 * @서브 시스템		:   migration - contact
 * @프로그램 ID		:   ContactMigration.java
 * @프로그램 개요		:   Contact 마이그레이션
 * @변경이력 
 * ============================================================================
 * 1.0 2021-03-23 : 최원준 - 신규생성
 * ============================================================================
 */
public class ContactMigration extends BaseModelTest {
	
	@Autowired
	protected BranchBLO branchBLO;
	
	@Test
	@Transactional
	public void Contact_마이그레이션() {
		
		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\04Contact.csv";
		
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
				
				BranchInfo info = new BranchInfo();
				
				boolean fillResult;
				for ( int i = 0; i < fieldArr.length; i++ ) {
					fillResult = fillInfo( info, i, fieldArr[ i ] );
					AssertUtils.isTrue( !fillResult, "Migration Error! Error Row : " + row + ", Error Index : " + i );
				}
				
				info = branchBLO.insert( info );
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
	private boolean fillInfo( BranchInfo info, int index, String field ) {
		
		// ContactOffice 명 필수
		if ( index == 1 && StringUtils.isEmpty( field ) ) {
			return false;
		}
		
		// 지역명 필수
		if ( index == 2 && StringUtils.isEmpty( field ) ) {
			return false;
		}
		
		boolean fillResult = true;
		switch ( index ) {
			case 1: {
				info.setName( field );
				break;
			}
			case 2: {
				fillResult = fillArea( info, field );
				break;
			}
			case 3: {
				info.setAddr( MigrationUtil.parseText( field ) );
				break;
			}
			case 4: {
				info.setPostCode( field );
				break;
			}
			case 5: {
				info.setTel( field );
				break;
			}
			case 6: {
				info.setDescr( field );
				break;
			}
			default: {
				break;
			}
		}
		
		return fillResult;
	}
	
	/**
	 * 지역코드를 채워줍니다.
	 *
	 * @param info
	 * @param field
	 * @author 최원준
	 */
	private boolean fillArea( BranchInfo info, String field ) {
		
		String areaCode = AmConstants.getKey( field );
		if ( StringUtils.isEmpty( areaCode ) ) {
			return false;
		}
		
		info.setAreaCode( areaCode );
		return true;
		
	}
	
}
