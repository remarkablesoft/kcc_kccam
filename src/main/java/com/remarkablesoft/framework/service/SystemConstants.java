package com.remarkablesoft.framework.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.remarkablesoft.framework.util.StringUtils;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	common
 * 		@프로그램 ID		:	SystemConstants
 * 		@프로그램 개요 		:	시스템 상수
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018.  1. 24.		:	james	- 신규생성
 * 		1.1  2018.  4. 29		:	choi 	- OS추가
 * 		1.2  2019. 12. 25		:	james 	- 이름 변경 RemarkableConstract -> SystemConstract
 * 		1.2  2020. 08. 01		:	james 	- SystemConstract => SystemConstants, 패키지 위치 변경
 * 		============================================================================
 */
public enum SystemConstants {

		LANG_KOR( "KO", "한국어" ), LANG_ENG( "EN", "영어" ), LANG_CHN( "CN", "중국어" ), LANG_RUS( "RU", "러시아" ), LANG_TUR( "TU", "터키" ),

		USE_Y( "Y", "사용" ), USE_N( "N", "사용안함" ),

		CERT_Y( "Y", "승인" ), CERT_N( "N", "승인대기" ),

		OPEN_Y( "Y", "노출" ), OPEN_N( "N", "비노출" ),
		
		INSTALL_Y( "Y", "설치" ), INSTALL_N( "N", "미설치" ),

		OBJECT_FW_TYPE_USER( "FWUR", "사용자 객체" ), OBJECT_FW_TYPE_POSTING( "FWPO", "포스팅 객체" ), OBJECT_FW_TYPE_ROLE( "FWRO", "ROLE 객체" ),

		OBJECT_FW_TYPE_RIGHT( "FWRG", "RIGHT 객체" ), OBJECT_FW_TYPE_MENU( "FWMN", "MENU 객체" ), OBJECT_FW_TYPE_GROUP( "FWGR", "그룹객체" ),

		OBJECT_FW_TYPE_DEVICE( "FWDV", "DEVICE 객체" ), OBJECT_FW_TYPE_BOARD( "FWBO", "보드 객체" ), OBJECT_FW_TYPE_PGROUP( "FWPG", "프로젝트 그룹 객체" ),
		
		OBJECT_FW_TYPE_ITEM_CODE( "FWIC", "코드 객체" ), OBJECT_FW_TYPE_SYSTEM( "FWSY", "시스템 객체" ), OBJECT_FW_TYPE_APP_RELEASE( "FWAR", "앱 배포정보객체" ),

		OBJECT_FW_TYPE_SURVEY( "FWSS", "설문 객체" ), OBJECT_FW_TYPE_SURVEY_ANSWER( "FWSA", "설문 답변 객체" ), OBJECT_FW_TYPE_SURVEY_SECTION( "FWSE", "설문 섹션 객체" ), OBJECT_FW_TYPE_SURVEY_QUESTION( "FWSQ", "설문 질문 객체" ),

		OBJECT_FW_TYPE_POSTING_AUDIT_CHANGE( "FWPA", "포스팅 수정이력 객체" ),	OBJECT_FW_TYPE_COMMENT( "FWCM", "댓글 객체" ),

		OBJECT_FW_TYPE_APP_RELEASE_VERSION( "FWAV", "앱 배포 객체" ),

		OBJECT_FW_TYPE_CATEGORY( "FWCT", "카테고리 객체" ), OBJECT_FW_TYPE_EBOOK( "FWEB", "EBook 객체" ),
		OBJECT_FW_TYPE_EBOOK_INDEX( "FWEI", "EBook Index 객체" ), OBJECT_FW_TYPE_EBOOK_PAGE( "FWEP", "EBook Page 객체" ),
		
		OBJECT_FW_TYPE_PART( "FWPT", "PART 객체" ), OBJECT_FW_TYPE_BRANCH( "FWBR", "지점 객체" ),
		OBJECT_FW_TYPE_DOC( "FWDO", "문서 객체" ), OBJECT_FW_TYPE_MESSAGE( "FWMG", "메세지 객체" ),
	
		SEARCH_DATE_TYPE_DAY( "FWPOSC1D", "최근 하루" ),
		SEARCH_DATE_TYPE_WEEK( "FWPOSC1W", "최근 일주일" ),
		SEARCH_DATE_TYPE_MONTH( "FWPOSC1M", "최근 한 달" ),
		SEARCH_DATE_TYPE_3MONTH( "FWPOSC3M", "최근 세 달" ),
		
		OS_ANDROID( "A", "Android" ), OS_IOS( "I", "IOS" ),
		OS_UNKNOWN( "U", "Unkown" ),
		
		DEVICE_TYPE_MOBILE ( "M", "Mobile"),
		DEVICE_TYPE_TABLET ( "T", "Tablet"),
		DEVICE_TYPE_NORMAL ( "N", "Normal"),

		SAMPLE_MEMU( "", "" ),

		BATCH_PERIOD_TIME ( "TIME", "배치주기 시간단위"),
		BATCH_PERIOD_DAY ( "DAY", "배치주기 일단위"),
		BATCH_PERIOD_WEEK ( "WEEK", "배치주기 주간단위"),
		BATCH_PERIOD_MONTH ( "MONTH", "배치주기 월단위"),

		IP_TARGET_TYPE_BRANCH( "ITTB", "지점" ),

		TREE_FRONT( "front", "앞노드" ), TREE_BACK( "back", "뒷노드" ),TREE_CHILD( "child", "자식노드" ),

		//공통옵션타입
		ITEM_CODE_TYPE_RANK( "FWICRANK", "직급" ),
		ITEM_CODE_TYPE_POSITION( "FWICPOSI", "직책" ),

		// 시스템 타입
		SYSTEM_POSTING_DISPLAY_COUNT( "system.posting.display.count", "게시물 DISPLAY 수" ),
		SYSTEM_POSTING_LATEST_PERIOD( "system.posting.latest.period", "게시물 최신(new)을 나타낼 일자" ),
		SYSTEM_POSTING_USE_AUDIT_CHANGECONTENTS ( "system.posting.use.audit.changecontents", "게시물 변경이력을 남길지여부"),
		SYSTEM_AUDIT_VISIT_EXPIRATION_PERIOD ( "system.audit.visit.expiration.period", "방문 쿠키 유효 시간"),
		SYSTEM_JWT_REFRESH_TOKEN_EXPIRATION_PERIOD ( "system.jwt.refresh.token.expiration.period", "리플래시 토큰 유효 시간"), // 60 * 60 * 24 * 14 = 1209600
		SYSTEM_AUDIT_VIEW_EXPIRATION_PERIOD ( "system.audit.view.expiration.period", "조회 유효 기간  단위는 시간 " ),
		
		;
		
		
		
		public static final String ENCODING = "UTF-8";
		public static final String LANG = "LANG";

		public static final String LOGIN_USER_ROLE = "LUR";
		public static final String LOGIN_MENU_MANAGER = "/manager/";
		public static final String LOGIN_MENU_USER = "/user/";

		public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		public static final String DATE_FORMAT_MIX = "yyyyMMdd";

		public static final String ACTION_TYPE_SAVE = "save";
		public static final String ACTION_TYPE_DELETE = "delete";

		public static final String FLAG_YES = "Y";
		public static final String FLAG_NO = "N";

		public static final int SESSION_TIMEOUT = 3600 * 6;

		public static final String DEFAULT_OID = "00000000000";
		public static final String ROOT_TREE_OID = "00000000000";
		public static final String FULL_PATH_INDEX_DELIMITER = "|";
		
		public static final String DEFULAT_FULL_PATH_INDEX = "0" + SystemConstants.FULL_PATH_INDEX_DELIMITER;
		

		public static final String TREE_NODE_MOVE_UP = "up";     	// 타겟노드의 위로 이동
		public static final String TREE_NODE_MOVE_DOWN = "down";	// 타겟노드의 아래로 이동
		public static final String TREE_NODE_MOVE_CHILD = "child";	// 타겟노드의 자식으로 이동

		// 아이폰 푸시 인증서 OID -- 
		public static final String PUSH_CONFIG_IOS_OID = "PUSH00000I1";
		// 안드로이드 푸시 인증서 OID
		public static final String PUSH_CONFIG_ANDROID_OID = "PUSH00000A2";

		public static final String DELIMETER_DEFAULT = ",";
		public static final String DELIMETER_USERINFO = "|";


		// 삭제된 부서 코드 ROOT
		public static final String DELETED_GROUP_OID_ROOT = "D0000000000";

		// 디폴트 시스템유저 계정
		public static final String DEFAULT_SYSTEM_ACCOUNT = "admin";
		
		
		// SNS TYPE
		public static final String SNS_TYPE_FLAG_KAKAO = "K";			// 카카오
		public static final String SNS_TYPE_FLAG_NAVER = "N";			// 네이버
		public static final String SNS_TYPE_FLAG_FACEBOOK = "F";		// 페이스북
		public static final String SNS_TYPE_FLAG_GOOGLE = "G";			// 구글
		public static final String SNS_TYPE_FLAG_APPLE = "A";			// 애플
		public static final String SNS_TYPE_FLAG_TWITTER = "T";         // 트위터
		public static final String SNS_TYPE_FLAG_MAIL = "M";            // 메일
		
		/**
		 * fullPathIndex의 사이즈.
		 * 일반적으로는 %3d로 3칸만 하면 된다. 그럼 999 대략 1000개.
		 * 데이터가 많이 쌓일것 같은것은 좀 더 길게 해야함.
		 * ex) 포스팅, 질문 : %5d 100000
		 */
		public static final String FULL_PATH_LONG_LENGTH = "%05d";

		/**
		 * fullPathIndex의 사이즈.
		 * 일반적으로는 %3d로 3칸만 하면 된다. 그럼 999 대략 1000개.
		 * 데이터가 많이 쌓일것 같은것은 좀 더 길게 해야함.
		 * ex) 그룹, 메뉴 : %3d 1000
		 */
		public static final String FULL_PATH_SHORT_LENGTH = "%03d";
		
		
		/**
		 * 리스트 분할 단위 - 커밋에 사용
		 */
		public static final int LIST_PARTITION_LIMIT = 100;
		
		
		

		private static final Map<String, String> itemToValue = new HashMap<>();
		private String key;
		private String value;

		static {
				for ( SystemConstants value : values() ) {
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
		
		/**
		 * snsTypeFlag 값을 실제 시스템명으로 변경처리
		 * 
		 * F,K,M,T --> 페이스북,카카오,메일,트위터
		 * 
		 * @param snsTypeFlag 
		 * @return
		 */
		public static String getSnsTypeName ( String snsTypeFlag ) {
			
			 if ( snsTypeFlag == null || snsTypeFlag.length() == 0) {
				 
				 return "";
			 }
			 
			 snsTypeFlag = StringUtils.replace(  snsTypeFlag, SystemConstants.SNS_TYPE_FLAG_APPLE, "애플");
			 snsTypeFlag = StringUtils.replace(  snsTypeFlag, SystemConstants.SNS_TYPE_FLAG_GOOGLE, "구글");
			 snsTypeFlag = StringUtils.replace(  snsTypeFlag, SystemConstants.SNS_TYPE_FLAG_KAKAO, "카카오");
			 snsTypeFlag = StringUtils.replace(  snsTypeFlag, SystemConstants.SNS_TYPE_FLAG_FACEBOOK, "페이스북");
			 snsTypeFlag = StringUtils.replace(  snsTypeFlag, SystemConstants.SNS_TYPE_FLAG_TWITTER, "트위터");
			 snsTypeFlag = StringUtils.replace(  snsTypeFlag, SystemConstants.SNS_TYPE_FLAG_MAIL, "메일");
			 
			 return snsTypeFlag;
			
		}

		SystemConstants( String key, String value ) {
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