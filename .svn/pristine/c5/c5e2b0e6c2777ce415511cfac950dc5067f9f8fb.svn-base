package com.remarkablesoft.framework.service.notification.message.message.model.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.message.model.MessageService;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageCnd;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
		
		@Autowired
		protected MessageBLO messageBLO;

		@Override
		public MessageInfo insert( MessageInfo info ) {
				return messageBLO.insert( info );
		}
		
		@Override
		public MessageInfo update( MessageInfo info ) {
				return messageBLO.update( info );
		}
		
		@Override
		public MessageInfo get( MessageCnd cnd ) {
				return messageBLO.get( cnd );
		}
		
		@Override
		public PageList<MessageInfo> list( MessageCnd cnd ) {
				return messageBLO.list( cnd );
		}

}
