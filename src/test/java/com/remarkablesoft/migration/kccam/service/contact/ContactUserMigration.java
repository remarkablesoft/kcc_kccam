package com.remarkablesoft.migration.kccam.service.contact;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.service.org.branch.model.impl.BranchBLO;
import com.remarkablesoft.framework.service.org.branch.vo.BranchCnd;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;
import com.remarkablesoft.framework.service.org.user.model.impl.UserBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.AssertUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.migration.kccam.service.MigrationUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;

/**
 * @주시스템         :	kccam
 * @서브 시스템        :   migration - contact
 * @프로그램 ID        :   ContactUserMigration.java
 * @프로그램 개요        :   Contact 직원 마이그레이션
 * @변경이력 ============================================================================
 * 1.0 2021-03-23 : 최원준 - 신규생성
 * ============================================================================
 */
public class ContactUserMigration extends BaseModelTest {
	
	@Autowired
	protected BranchBLO branchBLO;
	
	@Autowired
	protected UserBLO userBLO;
	
	@Test
	@Transactional
	public void Contact_직원_마이그레이션() {
		
		String filePath = "D:\\KCCEMC\\마이그레이션\\작성완료템플릿\\05Contact직원.csv";
		
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
				
				UserInfo info = new UserInfo();
				
				boolean fillResult;
				for ( int i = 0; i < fieldArr.length; i++ ) {
					fillResult = fillInfo( info, i, fieldArr[ i ] );
					AssertUtils.isTrue( !fillResult, "Migration Error! Error Row : " + row + ", Error Index : " + i );
				}
				
				info = userBLO.insert( info );
				AssertUtils.isTrue( info == null || StringUtils.isEmpty( info.getOid() ), "Migration Error! Error Row : " + row );
				
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
	private boolean fillInfo( UserInfo info, int index, String field ) {
		
		// 이름 필수
		if ( index == 1 && StringUtils.isEmpty( field ) ) {
			return false;
		}
		
		// Contact Office 필수
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
				fillResult = fillContactOffice( info, field );
				break;
			}
			case 3: {
				info.setEmail( field );
				break;
			}
			case 4: {
				String[] telArr = field.split( "\\)" );
				info.setTelPart1( telArr[0] );
				String[] telArr2 = telArr[1].split( "-" );
				info.setTelPart2( telArr2[0] );
				info.setTelPart3( telArr2[1] );
				break;
			}
			case 5: {
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
	 * Contact 지점정보를 채워줍니다.
	 *
	 * @param info
	 * @return boolean
	 * @author 최원준
	 */
	private boolean fillContactOffice( UserInfo info, String field ) {
		if ( StringUtils.isEmpty( field ) ) {
			return false;
		}
		
		BranchCnd branchCnd = new BranchCnd();
		branchCnd.setName( field );
		
		BranchInfo branch = branchBLO.get( branchCnd );
		if ( branch == null ) {
			return false;
		}
		
		info.setBranchOid( branch.getOid() );
		
		return true;
	}
	
	
}
