package com.remarkablesoft.framework.service.notification.message.group.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.group.model.MessageGroupService;
import com.remarkablesoft.framework.service.notification.message.group.vo.MessageGroupCnd;
import com.remarkablesoft.framework.service.notification.message.group.vo.MessageGroupInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

@Service
@Transactional
public class MessageGroupServiceImpl implements MessageGroupService {
		
		@Autowired
		protected MessageGroupBLO messageGroupBLO;
		
		@Override
		public int delete( MessageGroupCnd cnd ) {
				return messageGroupBLO.delete( cnd );
		}
		
		@Override
		public MessageGroupInfo get( MessageGroupCnd cnd ) {
				return messageGroupBLO.get( cnd );
		}
		
		@Override
		public List<MessageGroupInfo> listAll( MessageGroupCnd cnd ) {
				return messageGroupBLO.listAll( cnd );
		}

		@Override
		public PageList<UserInfo> groupUserList( MessageGroupCnd cnd ) {
				return messageGroupBLO.groupUserList( cnd );
		}
}
