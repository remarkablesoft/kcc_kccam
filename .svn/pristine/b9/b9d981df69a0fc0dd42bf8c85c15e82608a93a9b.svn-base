package com.remarkablesoft.framework.service.org.user.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.remarkablesoft.framework.common.constants.StatusType;
import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.app.device.vo.DeviceInfo;
import com.remarkablesoft.framework.service.authority.role.vo.RoleInfo;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;
import com.remarkablesoft.framework.service.org.company.vo.CompanyInfo;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.framework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	org - user
 * 		@프로그램 ID		:	UserInfo
 * 		@프로그램 개요 		:	사용자객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2014. 5. 9.	:	이균환	-	신규생성
 * 		1.1  2017. 9.27.	:	이균환	-	Display용 객체 추가
 * 		1.2  2018. 1.11.	:	이균환	-	userRole 추가
 * 		1.3  2018. 1.15.	:	이균환	-	불필요한 컬럼 삭제 및 컬럼명 변경
 *                                              ( emailAcceptFlag, fourteenOverFlag, shopOpenFlag => 삭제처리,  status => statusType 변경 )
 *  	1.4  2019. 1.21.	:	최원준	-	framworkv2용 칼럼 추가
 *  	1.5  2019. 5.01.	:	이균환	-	사용자 첨부파일을 프로파일과 일반 파일로 나누어서 저장
 *  	1.6  2019. 5.10.	:	이균환	-	사용자의 권한정보를 ObjectRel을 통해서가 아닌 ROLE_OID 컬럼을 추가해서 처리
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true)
@ToString(exclude = {"userRoleList", "profile", "fileList", "extraInfoMap", "statusTypeList", "userOidList", "deviceList"})
public class UserInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = -1526026057207610470L;

		public static final String JOIN_TYPE_WEB = "W";						// 가입 경로 web
		public static final String JOIN_TYPE_APP = "A";						// 가입 경로 app

		public static final String SALARY_TYPE_MONTH = "FWUS000M";			// 월급
		public static final String SALARY_TYPE_TIME = "FWUS000T";			// 시급

		public static final String FILE_TYPE_PROFILE = "FWUR00PR";			// Profile
		public static final String FILE_TYPE_NOMAL = "FWUR00NM";			// 일반 파일

		public static final String SEX_TYPE_MAN = "M";
		public static final String SEX_TYPE_WOMAN = "W";

		private boolean isUseUserConfig = false;							// 사용자 설정객체 사용여부

		private String oid;													// OID
		private String userId;												// ID
		private String name;												// 이름
		private String engName;												// 영문 이름
		private String pwd;													// PASSWORD
		private String pwd2;												// 인증, 간편비밀번호 PWD_AUTH로 변경예정

		private String snsId;												// SNS ID

		//sns type( "K"-kakao, "G"-google, "F"-facebook, "N"-naver, "A-apple" )
		private String snsJoinTypeFlag;										// SystemConstants에서 SNS_TYPE_FLAG_을 이용

		private String userType;											// 유저타입(고객/회원/직원)
		private String email;												// E-mail
		private String telPart1;											// 전화 앞번호
		private String telPart2;											// 전화 중간번호
		private String telPart3;											// 전화 뒷번호
	
		private String tel;													// 집전화
		private String phonePart1;											// 휴대전화 앞번호
		private String phonePart2;											// 휴대전화 중간번호
		private String phonePart3;											// 휴대전화 뒷번호
		private String phone;												// 핸드폰
		private String nick;												// 닉네임
		
		private String birthYear;											// 생년월일 년
		private String birthMonth;											// 생년월일 월
		private String birthDay;											// 생년월일 일
		private String postCode;											// 우편번호
		private String addrPart1;											// 주소1
		private String addrPart2;											// 주소2

		// U 사용중, N 사용안함, C 승인취소, L 탈퇴, W 대기
		private String statusTypeFlag = StatusType.USE.getKey();

		private String joinType;											// 가입 경로( WEB(W), APP(A) )
		private String joinPathOptOid;										// 가입경로( 방문, 홍보, 전화 등 )
		private String joinPathDescr;										// 가입경로 내용
		private LocalDateTime joinDate;										// 회원가입 일시

		private String companyOid;											// 회사 OID - 시스템 내부에서 company를 관리할때 사용
		private String companyName;											// 회사 명
		private String organizationCode;									// 조직(기관) 코드 - 외부에서 조직을 받아서 관리할때 사용
		private String organizationName;									// 조직(기관) 이름

		private String groupOid;											// 그룹 OID
		private String groupName;											// 그룹 이름
		private String branchOid;											// 지점 OID
		private String branchName;											// 지점 이름
		private String rank;												// 직위, 직급
		private String rankName;											// 직위,직급명

		private String position;											// 직책
		private String positionName;										// 직책명
		private String job;													// 직무
		private LocalDateTime inputDate = LocalDateTime.now();				// 등록일
		private LocalDateTime updateDate = null;							// 수정날짜=> 탈퇴시에 바로 탈퇴되는 것이 아닌 경우 필요
		
		private String inputUser;											// 등록자

		private LocalDateTime hireDate = null;								// 입사일
		private LocalDateTime retireDate = null;							// 퇴사일
		private String descr;												// 비고
		private String empNo;												// 사원번호
		private String sex ;												// 성별 남:"M", 여:"W", 기타:"E"

		private String region;												// 지역코드
		private String idNo;												// 주민등록번호
		private String bank;												// 은행코드
		private String accountNo;											// 계좌번호
		private String salaryType;											// 급여타입

		private int salary = 0;												// 급여
		private String workFromTime;										// 업무 시작시간
		private String workToTime;											// 업무 종료시간
		private Date recentDate = null;										// 최근 날짜

		private String customField1;										// 커스텀 필드1
		private String customField2;										// 커스텀 필드2
		private String customField3;										// 커스텀 필드3
		private String customField4;										// 커스텀 필드4
		private String customField5;										// 커스텀 필드5
	
		private CompanyInfo company = null;									// 회사 객체
		private BranchInfo branch = null;									// 지사정보
		private List<RoleInfo> userRoleList = new ArrayList<RoleInfo>();	// 사용자 롤 리스트 - 롤을 여러개 가질 수 있다. 보통은 하나만 이용.

		private String roleOids = null;										// "00000000000,11111111111" concat으로 권한 oid를 연결한 값
		
		private FileInfo profile = null;									// 프로파일 - 사진정보
		private List<FileInfo> fileList = new ArrayList<FileInfo>();		// 일반파일

		private HashMap<String, String> extraInfoMap = new HashMap<>();		// 부가적인 정보가 필요시. - 커스텀된 사이트에서 사용

		private List<String> statusTypeList = new ArrayList<>();			// 사용자 상태 리스트
		private List<String> userOidList = new ArrayList<String>();			// 사용자 OID 리스트

		private int userCount = 0;											// 사람수를 셀때 사용 - UI용
		private int deviceCount = 0;										// 등록 기기 수
		private List<DeviceInfo> deviceList = new ArrayList<>();
		
		public void addStatusType( String statusType ) {
				this.statusTypeList.add( statusType );
		}
		
		public void addUserOid( String userOid ) {
				this.userOidList.add( userOid );
		}
		
		public void addExtraInfo( String key, String value ) {
				this.extraInfoMap.put( key, value );
		}
		
		public void addUserRole( RoleInfo info ) {
				this.userRoleList.add( info );
		}
		

		public void addDeviceInfo( DeviceInfo deviceInfo ) {
				this.deviceList.add( deviceInfo );
		}
		
		
		public void addRole( RoleInfo roleInfo ) {
				this.userRoleList.add( roleInfo );
		}
		
		
		public void addFile( FileInfo fileInfo ) {
				fileList.add( fileInfo );
		}
		

		public String getTel() {

				/* part가 비어있으면 빈값으로 return */
				if ( StringUtils.hasText( telPart1 ) && StringUtils.hasText( telPart2 ) && StringUtils.hasText( telPart3 ) ) {
						return telPart1 + "-" + telPart2 + "-" + telPart3;
				}

				return this.tel;
		}

		public String getPhone() {

				if ( StringUtils.hasText( phonePart1 ) && StringUtils.hasText( phonePart2 ) && StringUtils.hasText( phonePart3 ) ) {
						return phonePart1 + "-" + phonePart2 + "-" + phonePart3;
				}

				return this.phone;
		}

	
		/**
		 * 자신의 타입을 반환. 
		 *
		 * @return
		 */
		public static String getObjectType() {
				return SystemConstants.OBJECT_FW_TYPE_USER.getKey();
		}

}
