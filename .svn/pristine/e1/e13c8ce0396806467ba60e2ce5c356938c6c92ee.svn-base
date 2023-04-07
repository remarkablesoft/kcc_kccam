package com.remarkablesoft.framework.service.org.company.vo;

import com.remarkablesoft.framework.model.vo.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 법인객체
 *
 * @author james
 *
 */

@Getter
@Setter
@Accessors( chain = true)
@ToString
public class CompanyInfo extends Entity {


		/**
		 *
	 	*/
		private static final long serialVersionUID = -7065988022232642569L;

		private String oid ;													// OID
		private String business;												// 업종
		private String companyName ;											// 업체명
		private String ownerName ;												// 대표자명
		private String companyNo ;												// 사업자등록번호

		private String telPart1 ;												// 업체 전화번호 1
		private String telPart2 ;												// 업체 전화번호 2
		private String telPart3 ;												// 업체 전화번호 3
		private String postCode ;												// 우편번호
		private String addrPart1 ;												// 주소1
		private String addrPart2 ;												// 주소2

		private String descr ;													// 비고
		private String inputUser ;												// 등록자
		private LocalDateTime inputDate = LocalDateTime.now();					// 등록일

		private int orderNo = 0;												// 정렬순서


}
