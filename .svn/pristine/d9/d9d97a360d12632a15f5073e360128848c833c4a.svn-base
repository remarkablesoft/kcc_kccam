package com.remarkablesoft.framework.service.doc.doc.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.remarkablesoft.framework.service.doc.doc.model.DocService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.notification.email.impl.kcc.KccEmailSendProvider;

/**
 *
 *        @주시스템            :	framework-web
 *        @서브 시스템        :	doc - doc
 *        @프로그램 ID        :	DocServiceImpl
 *        @프로그램 개요        :	Doc Service Impl
 *
 *        @변경이력
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

@Service
public class DocServiceImpl implements DocService {
		
		@Autowired
		protected DocBLO docBLO;
		
		@Override
		public DocInfo insert( DocInfo info ) {
				return docBLO.insert( info );
		}
		
		@Override
		public DocInfo update( DocInfo info ) {
				return docBLO.update( info );
		}
		
		@Override
		public DocInfo insertOrUpdate( DocInfo info ) {
				return docBLO.insertOrUpdate( info );
		}
		
		@Override
		public int delete( String oid ) {
				return docBLO.delete( oid );
		}
		
		@Override
		public DocInfo get( DocCnd cnd ) {
				return docBLO.get( cnd );
		}
		
		@Override
		public PageList<DocInfo> list( DocCnd cnd ) {
				return docBLO.list( cnd );
		}
		
		@Override
		public List<DocInfo> listAll( DocCnd cnd ) {
				return docBLO.listAll( cnd );
		}
		
		@Override
		public int sendEmailWithDocFile( HashMap<String, Object> paramMap ) {
				
				return docBLO.sendEmailWithDocFile( paramMap );
		}
		
		@Override
		public void sendEmailWithDocLink( DocInfo info ) {
				docBLO.sendEmailWithDocLink( info );
		}
		
}
