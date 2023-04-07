	package com.remarkablesoft.framework.service.org.branch.vo;

import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.SearchCnd;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class BranchCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = 1524517404891877720L;

		private String oid;									// OID
		private String name;								// 지점명
		private String companyOid;							// 회사 OID
		private String areaCode;							// 지역코드
		
		private boolean isFillUser = false;

		private List<String> branchOidList = new ArrayList<>(); /* 지점OID 리스트 */

		
		public void addBranchOid ( String branchOid) {

				branchOidList.add( branchOid );
		}
		

}
