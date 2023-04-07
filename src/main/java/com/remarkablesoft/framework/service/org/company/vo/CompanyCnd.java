package com.remarkablesoft.framework.service.org.company.vo;

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
public class CompanyCnd extends SearchCnd{

	/**
	 *
	 */
	private static final long serialVersionUID = -350404671191543553L;

	private String oid ;													// OID
	private String business;												// 업종
	private String companyName ;											// 업체명
	private String ownerName ;												// 대표자명
	private String companyNo ;												// 사업자등록번호

	private String addrPart1 ;												// 주소1
	private String addrPart2 ;												// 주소2
	private String descr ;													// 비고
	private String inputUser ;												// 등록자

	private int orderNo = 0;												// 정렬순서

	private List< String > companyOidList = new ArrayList<>();				// 법인회사 OID 리스트



}
