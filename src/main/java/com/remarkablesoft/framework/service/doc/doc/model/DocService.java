package com.remarkablesoft.framework.service.doc.doc.model;

import java.util.HashMap;
import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;

/**
 *
 *        @주시스템            :	framework-web
 *        @서브 시스템        :	doc - doc
 *        @프로그램 ID        :	DocService
 *        @프로그램 개요        :	Doc Service
 *
 *        @변경이력
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

public interface DocService {
		
		/**
		 * 문서 정보를 저장합니다.
		 *
		 * @param info
		 * @return DocInfo
		 * @author 최원준
		 */
		public DocInfo insert( DocInfo info );
		
		/**
		 * 문서 정보를 수정합니다.
		 *
		 * @param info
		 * @return DocInfo
		 * @author 최원준
		 */
		public DocInfo update( DocInfo info );
		
		/**
		 * 문서 정보를 insert or update 합니다.
		 *
		 * @param info
		 * @return
		 */
		public DocInfo insertOrUpdate( DocInfo info );
		
		/**
		 * 문서 정보를 삭제합니다.
		 *
		 * @param oid
		 * @return int
		 * @author 최원준
		 */
		public int delete( String oid );
		
		/**
		 * 문서 정보를 가져옵니다.
		 *
		 * @param cnd
		 * @return DocInfo
		 * @author 최원준
		 */
		public DocInfo get( DocCnd cnd );
		
		/**
		 * 문서 정보 페이지리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return PageList<DocInfo>
		 * @author 최원준
		 */
		public PageList<DocInfo> list( DocCnd cnd );
		
		/**
		 * 문서 정보 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return List<DocInfo><
		 * @author 최원준
		 */
		public List<DocInfo> listAll( DocCnd cnd );
		
		public int sendEmailWithDocFile( HashMap<String, Object> paramMap );
		
		/**
		 * 문서 파일 다운로드 링크를 메일로 보냅니다
		 *
		 * @param info
		 * @autor 황지영
		 */
		public void sendEmailWithDocLink( DocInfo info );
}
