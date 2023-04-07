package com.remarkablesoft.migration.kccam.service;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @주시스템		    :	kccam
 * @서브 시스템		:   migration
 * @프로그램 ID		:   MigrationUtil.java
 * @프로그램 개요	    :   마이그레이션 관련 기능
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-03-16 : 최원준 - 신규생성
 * ============================================================================
 */
public final class MigrationUtil {
    
    public static final String MIGRATION_CSV_DELIMITER = ",";   		// CVS 파일 구분자
    public static final String MIGRATION_COMMA_DELIMITER = "\\\\";		// 다수 항목 구분자
    public static final String MIGRATION_COMMA_REPLACE_TEXT = "||";		// 콤마 대체 문자
    public static final String MIGRATION_ENTER_REPLACE_TEXT = "**";		// 엔터 대체 문자
	
	public static final String ENTER_TEXT = "\n";	// 엔터 문자열
	
    /**
    * 파일경로에서 BufferedReader로 파일을 읽어들입니다.
    *
    * @param filePath
    * @return BufferedReader
    * @author 최원준
    */
    public static BufferedReader getFileBufferedReader( String filePath ) throws Exception {
        
        BufferedReader br = new BufferedReader( new FileReader( filePath ) );
        
        return br;
    }
    
    /**
    * 문자열을 알맞게 파싱합니다.
    *
    * @param text
    * @return String
    * @author 최원준
    */
    public static String parseText( String text ) {
    
    	text = text.replace( MIGRATION_COMMA_REPLACE_TEXT, MIGRATION_CSV_DELIMITER );
    	text = text.replace( MIGRATION_ENTER_REPLACE_TEXT, ENTER_TEXT );
    	
    	return text;
	}
    
}
