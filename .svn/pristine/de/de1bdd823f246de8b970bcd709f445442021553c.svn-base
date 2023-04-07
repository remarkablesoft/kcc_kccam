package com.remarkablesoft.framework.service.notification.message.send.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.app.device.model.impl.DeviceBLO;
import com.remarkablesoft.framework.service.app.device.vo.DeviceInfo;
import com.remarkablesoft.framework.service.notification.message.message.model.impl.MessageBLO;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistInfo;
import com.remarkablesoft.framework.service.notification.message.template.model.impl.MessageTemplateBLO;
import com.remarkablesoft.framework.service.org.user.model.impl.UserBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.BitSumUtils;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.web.module.notification.MessageSendProvider;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - send
* 		@프로그램 ID		:	MessageSendBLO
* 		@프로그램 개요 		:	메시지 발송 BLO
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 4.	:	james	-	신규생성
* 		============================================================================
*/
@BLO
public class MessageSendBLO {

		@Autowired
		protected MessageBLO messageBLO;

		@Autowired
		protected MessageTemplateBLO messageTemplateBLO;

		@Autowired ( required = false)
		@Qualifier ( "pushSendProvider")
		protected MessageSendProvider pushSendProvider;

		@Autowired ( required = false)
		@Qualifier ( "kakaoNotiSendProvider")
		protected MessageSendProvider kakaoNotiSendProvider;
		
		@Autowired ( required = false)
		@Qualifier ( "kakaoFriendSendProvider")
		protected MessageSendProvider kakaoFriendSendProvider;
		
		@Autowired ( required = false)
		@Qualifier ( "smsSendProvider")
		protected MessageSendProvider smsSendProvider;

		@Autowired ( required = false)
		@Qualifier ( "kccEmailSendProvider")
		protected MessageSendProvider emailSendProvider;
		
		@Autowired
		protected DeviceBLO deviceBLO;

		@Autowired
		protected UserBLO userBLO;

		private static int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

		/**
		 * 단일 메세지를 발송합니다.
		 * 유저 정보가 없는 경우 기기정보로만 메세지를 발송합니다.
		 * 단일 메세지 발송의 경우 이력만 저장하고 전체 발송결과를 저장하지 않습니다.
		 * 
		 * @param info
		 * @return
		 */
		public void send( MessageInfo info, String userOid ) {

				if ( info == null ) {
						return;
				}

				UserInfo user = userBLO.getOnlyUser( userOid );

				send( info, user, null );
		}

		/**
		 * 단일 메세지를 발송합니다.
		 * 유저 정보가 없는 경우 기기정보로만 메세지를 발송합니다.
		 * 단일 메세지 발송의 경우 이력만 저장하고 전체 발송결과를 저장하지 않습니다.
		 * 
		 * @param info
		 * @return
		 */
		public void send( MessageInfo info, DeviceInfo device ) {

				if ( info == null ) {
						return;
				}

				send( info, null, device );
		}

		/**
		 * 단일 메세지를 발송합니다.
		 * 유저 정보가 존재하는경우 유저정보와 함께 기기정보로 메세지를 발송합니다.
		 * 단일 메세지 발송의 경우 이력만 저장하고 전체 발송결과를 저장하지 않습니다.
		 * 
		 * @param message
		 * @param user
		 * @param device
		 * @return
		 */
		public void send( MessageInfo message, UserInfo user, DeviceInfo device ) {

				if ( message == null ) {
						return;
				}

				if ( user == null ) {
						user = SystemFactory.getUserInfo();
				}

				message = messageBLO.insert( message );

				sendEachSendTypeMessage( message, user, device );

		}


		/**
		 * 해당 메세지의 관련된 설정을 찾아 모두 발송
		 *
		 * @author 최원준
		 * @param info
		 * @return
		 */
		public void sendAll( MessageInfo info, UserInfo ... receiveUsers ) {

				sendAll( info, java.util.Arrays.asList( receiveUsers ) );
		}

		/**
		 * 해당 메세지 설정에 따라 모두 발송
		 *
		 * @author 최원준
		 * @param info
		 * @param msgConfigInfo
		 */
		public void sendAll( MessageInfo info, List<UserInfo> receiveUserList ) {

				if ( CollectionUtils.isEmpty( receiveUserList ) ) {
						return;
				}

//				List<Callable<Void>> tasks = new ArrayList<Callable<Void>>();

				MessageInfo message = messageBLO.insert( info );
				
				receiveUserList.forEach( user -> {
						sendEachSendTypeMessage( message, user, null );
				} );

//				receiveUserList.forEach( user -> {
//						tasks.add( new Callable<Void>() {
//
//								@Override
//								public Void call() throws Exception {
//										sendEachSendTypeMessage( message, user, null );
//										return null;
//								}
//						} );
//				} );
//
//				ExecutorService es = Executors.newFixedThreadPool( AVAILABLE_PROCESSORS );
//				try {
//
//						es.invokeAll( tasks );
//
// 						// 발송 결과를 저장합니다.
//						for( Future<MessageSendHistInfo> future : resultList ) {
//								MessageSendHistInfo hist = future.get();
//								if( hist != null && SystemConstants.FLAG_NO.equals( hist.getSuccessYn() ) ) {
//										sendFailHistList.add( hist );
//								}
//						}
//				}
//				catch ( InterruptedException e ) {
//						e.printStackTrace();
//				}
//				finally {
//						es.shutdown();
//				}

				// TODO 발송결과는 따로 배치로 처리 할것
				// TODO 실패내역의 대체문자는 보내고 나서 바로 처리해야할듯.
				// 발송 결과를 저장 후 반환합니다.
//				MessageSendResultInfo sendResult = messageSendResultBLO.insert( info, info.getUserList().size(), sendFailHistList.size() );
//				
//				// 실패내역에 대해 대체문자를 발송합니다.
//				sendReplaceMsg( info, msgConfigInfo, sendFailHistList );
//				
//				return sendResult;
		}


		/**
		 * 각각의 발송타입에 맞는 메세지를 발신합니다.
		 *
		 * @author 최원준
		 * @param message
		 * @param messageConfig
		 * @param user
		 * @parma device
		 */
		protected void sendEachSendTypeMessage( MessageInfo message, UserInfo user, DeviceInfo device ) {

				// 템플릿이 있는지 확인하고 템플릿이 있다면 message의 컨텐츠를 변경한다
				messageTemplateBLO.fillTemplateMessage( message, user );

				if ( BitSumUtils.getCheck( message.getMsgTypeMode(), MessageInfo.MESSAGE_TYPE_MODE_PUSH )) {
						if ( device == null ) {
								device = deviceBLO.getPossiblePush( user.getOid() );
						}

						if ( device != null ) {
								user.addDeviceInfo( device );
								pushSendProvider.send( message, user );
						}
				}
				
				if ( BitSumUtils.getCheck( message.getMsgTypeMode(), MessageInfo.MESSAGE_TYPE_MODE_SMS )) {

						smsSendProvider.send( message, user );
				}
				
				if ( BitSumUtils.getCheck( message.getMsgTypeMode(), MessageInfo.MESSAGE_TYPE_MODE_KAKAO_NOTI )) {

						kakaoNotiSendProvider.send( message, user );
				}

				if ( BitSumUtils.getCheck( message.getMsgTypeMode(), MessageInfo.MESSAGE_TYPE_MODE_KAKAO_FRIEND )) {

						kakaoFriendSendProvider.send( message, user );
				}
				
				if ( BitSumUtils.getCheck( message.getMsgTypeMode(), MessageInfo.MESSAGE_TYPE_MODE_EMAIL )) {

						emailSendProvider.send(message, user);
				}
				
				
		}


		protected void sendReplaceMsg( MessageInfo info, List<MessageSendHistInfo> sendFailList ) {


		}


}
