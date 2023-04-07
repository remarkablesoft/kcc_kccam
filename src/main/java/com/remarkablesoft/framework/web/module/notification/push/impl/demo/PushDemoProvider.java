package com.remarkablesoft.framework.web.module.notification.push.impl.demo;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.web.module.notification.MessageSendProvider;

@BLO
public class PushDemoProvider implements MessageSendProvider {

		@Override
		public void send( MessageInfo message, UserInfo user ) {

				System.out.println( "Push Send - " + message.getContents() );
		}

}
