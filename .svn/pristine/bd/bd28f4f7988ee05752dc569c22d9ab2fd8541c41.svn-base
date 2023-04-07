package com.remarkablesoft.framework.service.notification.message.template.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageCnd;
import com.remarkablesoft.framework.service.notification.message.template.model.MessageTemplateService;
import com.remarkablesoft.framework.service.notification.message.template.vo.MessageTemplateInfo;

@Service
@Transactional
public class MessageTemplateServiceImpl implements MessageTemplateService {

		@Autowired
		protected MessageTemplateBLO messageTemplateBLO;
		
		@Override
		public int delete( MessageCnd cnd ) {
				return messageTemplateBLO.delete( cnd );
		}
		
		@Override
		public MessageTemplateInfo get( MessageCnd cnd ) {
				return messageTemplateBLO.get( cnd );
		}

		@Override
		public PageList<MessageTemplateInfo> list( MessageCnd cnd ) {
				return messageTemplateBLO.list( cnd );
		}
		
		@Override
		public List<MessageTemplateInfo> listAll( MessageCnd cnd ) {
				return messageTemplateBLO.listAll( cnd );
		}
				
		@Override
		public MessageTemplateInfo insertOrUpdate( MessageTemplateInfo info ) {
				return messageTemplateBLO.insertOrUpdate( info );
		}
		
}
