package com.remarkablesoft.site.kccam.service.docshareaudit.model.impl;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.docshareaudit.model.DocShareAuditService;
import com.remarkablesoft.site.kccam.service.docshareaudit.vo.DocShareAuditCnd;
import com.remarkablesoft.site.kccam.service.docshareaudit.vo.DocShareAuditInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DocShareAuditServiceImpl implements DocShareAuditService {
		
		@Autowired
		protected DocShareAuditBLO docShareAuditBLO;
		
		@Override
		public DocShareAuditInfo insert( DocShareAuditInfo info ) { return docShareAuditBLO.insert( info ); }
		
		@Override public PageList<DocShareAuditInfo> list( DocShareAuditCnd cnd ) { return docShareAuditBLO.list( cnd ); }
}
