package com.remarkablesoft.framework.service.board.posting.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.remarkablesoft.framework.model.vo.SearchCnd;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.util.ApplicationPropertiesUtils;
import com.remarkablesoft.framework.util.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 설명 : 포스팅 검색
 *
 * @author james
 * @since 2015. 4. 14.
 *
 */
@Getter
@Setter
@Accessors( chain = true)
@ToString
@EqualsAndHashCode (callSuper = true)
public class PostingCnd extends SearchCnd {

		private static final long serialVersionUID = 3010856961325883327L;

		public final static String SEARCH_QUERY_TYPE_ALL = "FWPOSQ00";								// 전체
		public final static String SEARCH_QUERY_TYPE_TITLE = "FWPOSQ01";							// 제목검색
		public final static String SEARCH_QUERY_TYPE_CONTENTS = "FWPOSQ02";							// 내용검색
		public final static String SEARCH_QUERY_TYPE_CREATEUSER = "FWPOSQ03"; 						// 작성자검색
		public final static String SEARCH_QUERY_TYPE_RECEIVEUSER = "FWPOSQ04"; 						// 수신자검색
		public final static String SEARCH_QUERY_TYPE_TITLE_USER = "FWPOSQ05"; 						// 제목 AND 작성자 검색
		public final static String SEARCH_QUERY_TYPE_TITLE_CONTENTS_USER_RECEIVEUSER = "FWPOSQ06"; 	// 제목 AND 내용 AND 작성자 AND 수신자 검색
		public final static String SEARCH_QUERY_TYPE_TITLE_CONTENTS = "FWPOSQ07"; 					// 제목 AND 내용 검색
		public final static String SEARCH_QUERY_TYPE_TITLE_PRODUCT = "FWPOSQ08";					// 관련 제품 제목 검색
		public final static String SEARCH_QUERY_TYPE_TITLE_OR_TITLE_PRODUCT = "FWPOSQ09";			// 제목 or 관련 제품 제목 검색
		
		public final static String SEARCH_DATE_TYPE_CREATE_WEEK = "FWPOSC1W";				// 1주
		public final static String SEARCH_DATE_TYPE_CREATE_ONEMONTH = "FWPOSC1M";			// 1달
		public final static String SEARCH_DATE_TYPE_CREATE_THREEMONTH = "FWPOSC3M";			// 3달

		private String userId;

		private String oid;
		
		private String boardOid;										// 부모 보드 Oid 
		private String inputDateFrom;									// 작성일 From 
		private String inputDateTo;										// 작성일 To 
		private String searchQueryType;									// 질의어 검색 타입 
		private String searchQuery;										// 질의어 
		private String searchDateType;									// 날짜 검색 타입
		private String lang = SystemConstants.LANG_KOR.getKey();		// 언어

		/** inputDate가 변수값만큼 과거로 가 그 이후에 만들어진 거면 newYn값을 Y로 반환(newPeriod가 2이면 이틀전부터 생성된 것은 Y로반환)
		 *  1일 때 당일, 2일때 어제오늘 이틀
		 * */
		private int newPeriod = 0;

		private boolean isCmtSearch = false;							 // 코멘트 정보 채울지 여부
		private boolean isReceiverInfoSearch = false;					 // 수신자( 대상자 ) 정보를 함께 가져올 시
		private boolean isInputUserSearch = false;						 // 등록자로 검색해서 가져올시 
		private boolean isThumbnailSearch = false;						 // 해당 포스트에 대해 썸네일 정보를 가져올지
		private boolean isLikeSearch = false;							 // 해당 포스트에 대해 좋아요 정보를 가져올지
		private boolean isFillContents = false;							 // 내용을 채울지 여부 
		private boolean isFillContentsWithoutHtmlTag = false;			 // Posting List에서 컨텐츠의 html 태그를 없앨지 여부.
		private boolean isFillInputUserProfile = false;					 // 등록자의 프로파일을 채울지 여부 
		private boolean isUserSearch = false;							 // 등록자 inputUserInfoList가 아닌 실제 데이터로 채울것인지 여부 
		
		private List<String> boardOidList = new ArrayList<String>();  	 // boardOidList 
		private List<String> threadOidList = new ArrayList<String>();  	 //	답변 OID LIST
		private List<String> postingOidList = new ArrayList<>();		 // 포스팅 oids  해당포스팅들만 가져오기 위해서 
		private List<String> inputUserOidList = new ArrayList<String>(); // 등록자 리스트 
		private List<String> receiverOidList = new ArrayList<String>();  // 수신 대상자 검색 

		private PostingInfo postingInfo = null;

		private String viewUserOid;										// 열람자
		private String inputUser;										// 등록자
		private String delUser;											// 삭제자

		private boolean isReadYnSearch = false;							// 읽음 여부
		private boolean isFillPickedPosting = false;					// 원글에서 채택한 답변을 채울지 여부

		private String threadOid; 										// 스레드 OID - 답변시 사용
		private String threadFullPathOid;								// 스레드 전체 OID Path - 답변 삭제시 사용
		private String threadParentOid;									// 스레드 부모 OID - 답변시 사용
		private int threadDepth;										// 원글 검색시 사용

		private boolean isCurrentPostingSearch = false;					// 현재 포스팅의 하위 정보 리스트 가져올지(답변게시판) 여부

		private String mustYn = SystemConstants.FLAG_NO;				// 필독여부
		private String notiYn = SystemConstants.FLAG_NO;				// 공지 여부
		private String tempYn = SystemConstants.FLAG_NO;				// 임시저장 여부
		private String pickYn ;											// 채택 여부

		private int likeCount;											// 조회수
		private int limit;												// 검색 갯수
		private int searchCount;										// 순위 데이터 검색 갯수
		private String customField1;									// 커스텀필드1
		private String customField2;                           			// 커스텀필드2
		private String customField3;                            		// 커스텀필드3
		private String customField4;                            		// 커스텀필드4
		private String customField5;                            		// 커스텀필드5

		
		private boolean isFillProductInfo = false;						// 관련 제품 채움 여부
		private boolean isFillIconFile = false;							// 대표이미지 채움 여부
		private boolean isFillLangList = false;							// 다국어 채우기 여부
		
		private Map<String, String> dateMap = new HashMap<>(); 			// 순위 데이터 검색 갯수
		
		// 순위 제외 유저 목록
		private String exceptUserOids = ApplicationPropertiesUtils.getValue( "user.rank.except.useroid" , "" );
		private List<String> exceptUserOidList = Arrays.asList( StringUtils.splitPreserveAllTokens( exceptUserOids, "," ));
		

		
		
}
