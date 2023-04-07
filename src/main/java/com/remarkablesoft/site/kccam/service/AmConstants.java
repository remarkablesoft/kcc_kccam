package com.remarkablesoft.site.kccam.service;

import com.remarkablesoft.framework.util.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @주시스템		    :	kccam
 * @서브 시스템		:   service
 * @프로그램 ID		:   AmConstants.java
 * @프로그램 개요	    :   KCC AM에서 사용하는 상수
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-03-16 : 최원준 - 신규생성
 * ============================================================================
 */
public enum AmConstants {


		OBJECT_AM_TYPE_MATERIAL( "AMMT", "소재 정보 객체" ), OBJECT_AM_TYPE_PRODUCT( "AMPR", "제품 객체" ),
		OBJECT_AM_TYPE_CLASSIFICATION( "AMCL", "분류 정보 객체" ), OBJECT_AM_TYPE_DATASHEET( "AMDS", "데이터시트 객체" ),
		OBJECT_AM_CLASSIFICATION_PRODUCT( "AMCP", "제품구분 정보 객체" ), OBJECT_AM_CLASSIFICATION_APPLICATION( "AMCA", "Application구분 정보 객체" ),
		OBJECT_AM_CLASSIFICATION_MARKET( "AMCM", "마켓구분 정보 객체" ), OBJECT_AM_CLASSIFICATION_FUNCTION( "AMCF", "Function구분 정보 객체" ),
		OBJECT_AM_ONE_TO_ONE( "AMOT", "1대1 문의 정보 객체" ), OBJECT_AM_NEWSROOM( "AMNR", "Newsroom 정보 객체" ),
	
		AM_CONTACT_AREA_KOREA( "AMCA00KO", "대한민국" ), AM_CONTACT_AREA_CHINA( "AMCA00CH", "중국" ),
		AM_CONTACT_AREA_JAPAN( "AMCA00JP", "일본" ),
	
		AM_USER_TYPE_CONTACT_MANAGER( "AMUTCTMG", "유저타입 Contact 매니저" ), AM_USER_TYPE_CLIENT( "AMUTCLNT", "유저타입 고객" ),
		AM_USER_TYPE_ADMIN( "AMUTADMN", "유저타입 관리자" )
		;
	
		public static final String ROOT_TREE_OID_PRODUCT = "00000000001";
		public static final String ROOT_TREE_OID_APPLICATION = "00000000002";
		public static final String ROOT_TREE_OID_MARKET = "00000000003";
		public static final String ROOT_TREE_OID_FUNCTION = "00000000004";
		public static final String HYPHEN = "-";
		public static final String COOKIE_LANG_NAME = "kccam_i18n";
		
		private static final Map<String, String> itemToValue = new HashMap<>();
		private String key;
		private String value;

		static {
				for ( AmConstants value : values() ) {
						itemToValue.put( value.getKey(), value.getValue() );
				}
		}

		public static String getValue( String key ) {
				if ( itemToValue.containsKey( key ) ) {
						return itemToValue.get( key );
				}
				return "";
		}

		public static String getValue( String key, String defaultVal ) {
			if ( itemToValue.containsKey( key ) ) {
					return itemToValue.get( key );
			}
			return defaultVal;
		}

		public static String getKey( String value ) {

				String key = "";

				Iterator<Map.Entry<String, String>> iterator = itemToValue.entrySet().iterator();
				while (iterator.hasNext()) {
						Map.Entry<String, String> entry = iterator.next();
						if ( entry.getValue().equals( value ) ) {
								key = entry.getKey();
								break;
						}
				}
			
				return key;

		}
		
		AmConstants( String key, String value ) {
				this.key = key;
				this.value = value;
		}

		public String getKey() {
				return key;
		}

		public String getValue() {
				return value;
		}

		public void setKey( String key ) {
				this.key = key;
		}

		public void setValue( String value ) {
				this.value = value;
		}

}