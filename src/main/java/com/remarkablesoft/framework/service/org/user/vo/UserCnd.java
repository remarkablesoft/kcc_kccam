package com.remarkablesoft.framework.service.org.user.vo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.remarkablesoft.framework.model.vo.SearchCnd;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 설명 : 유저검색 검색을 하는 방법엔 검색어로 검색하는 방법과 <br>
 * 해당객체 자체에 맵핑해서 검색하는 방법이 존재.
 *
 * @author james
 * @since 2014. 5. 9.
 *
 */

@Getter
@Setter
@Accessors( chain = true)
@ToString
@EqualsAndHashCode (callSuper = true)
public class UserCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = -7170596476485530683L;
		
		// 기본적으로 검색은 like검색 모드
		public UserCnd() {
			isLikeSearch = true;
		}
				

		private boolean isUseUserConfig;

		private List<String> messageTypeList = new ArrayList<String>();		// 비트썸검색
		private String messageTypeKey ;										// 문자수신타입( ex- FWMGTKAT )

		private String oid ;
		private String statusTypeFlag;		 								// 사용자 상태
		private String searchStr;											// 검색어

		private HashSet<String> roleOidList = new HashSet<String>();
		
		private String pwd;													// PASSWORD
		private String pwd2;												// 인증, 간편비밀번호 PWD_AUTH로 변경예정
		private String nick;												// 닉네임
		private String joinType;											// 가입 경로( WEB(W), APP(A) )
		private String descr;												// 비고
		
		private String createDateFrom;										// 작성일 From
		private String createDateTo;										// 작성일 To
		private String updateDateFrom;										// 회원정보수정일 from
		
		private String snsId;												// sns id
		private String snsJoinTypeFlag;									

		private String userId;													// 사용자 아이디
		private String name;												// 사용자 이름
		private String groupName;											// groupName
		
		private String targetObject;										// 타겟 객체
		private String targetOid;											// 타겟 oid

		private String companyOid;
		private String organizationCode;	
		private String organizationName;	
		
		private String groupOid;

		private String hireDateFrom;
		private String hireDateTo;

		private String phonePart1 ;
		private String phonePart2 ;
		private String phonePart3 ;

		private String birthYear ;
		private String birthMonth ;
		private String birthDay ;

		private List<String> userOidList = new ArrayList<>();
		private List<String> targetOidList = new ArrayList<>();			// targetOid 리스트
		private List<String> branchOidList = new ArrayList<>();
		private List<String> groupOidList = new ArrayList<>();
		private List<String> positionList = new ArrayList<>();
		
		private List<String> statusTypeList = new ArrayList<>();
		private List<String> companyOidList = new ArrayList<>();
		
		private String customField1;
		private String customField2;
		private String customField3;
		private String customField4;
		private String customField5;
		
		/** 검색시 like 검색을 할것인지 여부  */
		private boolean isLikeSearch = false;
		
		/** 프로파일(사진) 정보를 채울지 여부 */
		private boolean isProfileSearch = false;

		private boolean isPositionSearch = false;

		/** 기기정보 데이터를 채울지 여부 - 기기정보 사용 시 뷰에서 데이터를 가져옵니다. */
		private boolean isDeviceSearch = false;

		/** 사용자 그룹정보를 여부 */
		private boolean isGroupSearch = false;

		/** 등록된 기기 수를 가져올 지 여부 */
		private boolean isDeviceCntSearch = false;

		/** 본사,지점정보를 채울지 여부 */
		private boolean isBranchSearch = false;

		/** 법인회사 정보를 채울지 여부 */
		private boolean isCompanySearch = false;

		/** 직급 정보를 채울 지 여부 */
		private boolean isRankSearch = false;

		/** 권한정보를 채울지 여부 */
		private boolean isRoleSearch = false;
		
		/** ebook 정보를 채울지 여부 */
		private boolean isEbookSearch = false;
		
		/** 최근 접속 정보 채울지 여부 */
		private boolean isLastAuditLoginSearch = false;
		
		/** 직급 검색 */
		private String rank ;

		/** 직책 검색 */
		private String position ;

		/** 휴대폰 전체번호 */
		private String phone ;

		/** 이메일 */
		private String email ;

		/** 사원번호*/
		private String empNo ;

		/** 사원번호 리스트 */
		private List<String> empNoList = new ArrayList<String>();

		/** 유저타입 */
		private String userType ;
		private List<String> userTypeList = new ArrayList<String>();

		private String branchOid ;

		/** 유저 이력 테이블 유저 OID */
		private String userOid ;
		
		/** 권한 oid */
		private String roleOid ;
		
		/** 제외할 사용자 상태 타입 */
		private String exceptStatusTypeFlag;
		


		public void addRoleOid( String roleOid ) {
				this.roleOidList.add( roleOid );
		}

		public void addMessageType( String messageType ) {
			this.messageTypeList.add( messageType );
		}

		public void addUserType( String userType ) {
			this.userTypeList.add( userType );
		}


		public void addEmpNoList( String empNo ) {
			if( !empNoList.contains( empNo ) ) {
				empNoList.add( empNo );
			}
		}

		
		public void addUserOid( String userOid ) {
			if( !this.userOidList.contains( userOid ) ) {
				this.userOidList.add( userOid );
			}
		}


		public void addPosition( String position) {
				this.positionList.add( position );
		}

		
		public void addStatusType( String statusTypeFlag ) {
				this.statusTypeList.add( statusTypeFlag );
		}

		public void addTargetOid( String targetOid ) {
				this.targetOidList.add( targetOid );
		}
		
		

}
