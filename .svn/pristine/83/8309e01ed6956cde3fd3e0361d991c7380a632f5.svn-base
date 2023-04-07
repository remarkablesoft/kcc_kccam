package com.remarkablesoft.framework.service.org.branch.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.remarkablesoft.framework.model.vo.Entity;

import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors( chain = true)
@ToString
public class BranchInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = -6867150186162706948L;

		private String oid ;														// OID
		private String companyOid;													// 회사 OID
		private String areaCode;													// 지역코드
		private String owner;														// 오너
		private String name;														// 지점명

		private String addr;														// 주소
		private String postCode;													// 우편번호
		private String tel;															// 전화번호
		private String descr;														// 비고
		private String inputUser;													// 등록자

		private LocalDateTime inputDate = LocalDateTime.now();						// 등록일
		private int orderNo = 0;													// 정렬순서

		private Map<String, String> displayInfoMap = new HashMap<String, String>(); // 브렌치에 필요한 데이터를 보여줄때 사용.
		private List<BranchInfo> branchList = new ArrayList<BranchInfo>();			// 추가 및 수정시 사용
		private List<BranchInfo> deleteBranchList = new ArrayList<BranchInfo>();	// 삭제시 사용
		private List<UserInfo> branchUserList = new ArrayList<>();					// 지점 소속 유저 리스트
		private FileInfo iconFile = null;											// office 이미지
		
		public static String getObjectType() {
			return SystemConstants.OBJECT_FW_TYPE_BRANCH.getKey();
		}

}
