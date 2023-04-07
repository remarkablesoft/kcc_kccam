package com.remarkablesoft.framework.service.storage.file.vo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.SearchCnd;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	storage - file
 * 		@프로그램 ID		:	FileICnd
 * 		@프로그램 개요 		:	파일 검색 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 1. 25.	:	james	-	신규생성
 * 		============================================================================
 */


@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class FileCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = 7931108915045801435L;

		private List<String> targetOidList = new ArrayList<>(); 			// 타겟 객체의 Oids
		private String targetOid; 											// 타겟 객체의 Oid
		private String targetObject; 										// 타겟 객체의 타입
		private String fileType;											// 내부적인 파일타입
		private long fileSize = 0;
		private String fileName;
	
		private String oid;													// 자신의 oid
		private String containerOid;										// 컨테이너 OID - Posting일 경우 boardOid
		
		private String downAuditOid;										// 파일 다운로드 이력 oid
		private String boardOid;											// 파일 다운로드 이력 찾을 시 boardOid
		private String postingOid;											// 파일 다운로드 이력 찾을 시 postingOid
		private String downUser;											// 파일 다운로드 이력 찾을 시 다운받은 사용자
		
		private List<String> storageFileUidList = new ArrayList<String>();	// 스토리지 파일 리스트
		private String storageFileUid;										// 스토리지파일  아이디

}
