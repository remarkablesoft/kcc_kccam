package com.remarkablesoft.framework.service.notification.message.booking.model.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.booking.vo.MessageBookingCnd;
import com.remarkablesoft.framework.service.notification.message.booking.vo.MessageBookingInfo;
import com.remarkablesoft.framework.service.notification.message.message.model.impl.MessageBLO;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageCnd;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.model.impl.MessageSendBLO;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 		@주시스템			:	remarkable-framework
 * 		@서브 시스템		:	notification - message - booking
 * 		@프로그램 ID		:	MessageBookingBLO.java
 * 		@프로그램 개요 	:	메세지 예약 정보 로직
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 6.	:	choi	-	신규생성
 * 		============================================================================
 */
@BLO
public class MessageBookingBLO {

		@Autowired
		protected MessageBookingDAO messageBookingDAO;

		@Autowired
		protected MessageBLO messageBLO;
		
		@Autowired
		protected MessageSendBLO messageSendBLO;
		
		/**
		 * 메세지 예약 정보를 입력합니다.
		 * @param info
		 * @return
		 */
		public MessageBookingInfo insert( MessageBookingInfo info ) {

				if( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}

				int result = messageBookingDAO.insert( info );
				return result == 0 ? null : info;
		}

		
		/**
		 * 메세지 예약 발송 시 정보를 저장합니다.
		 * 
		 * @author 최원준
		 * @param message
		 * @return
		 */
		public MessageBookingInfo bookingMessage( MessageInfo message ) {
		
				if( message == null || message.getMessageBookingInfo() == null ) {
						return null;
				}
				
				MessageBookingInfo info = message.getMessageBookingInfo();
				info.setMessageOid( message.getOid() );
				info.setProcessTypeFlag( MessageBookingInfo.PROCESS_TYPE_FLAG_WAIT );
				info.setInputUser( message.getInputUser() );
				info.setInputDate( LocalDateTime.now() );
				
				return insert( info );
		}
		
		/**
		 * 메세지 예약정보를 업데이트합니다.
		 * @param info
		 * @return
		 */
		public MessageBookingInfo update( MessageBookingInfo info ) {

				if( StringUtils.isEmpty( info.getOid() ) ) {
						return null;
				}

				int result = messageBookingDAO.update( info );
				return result == 0 ? null : info;
		}

		/**
		 * 메세지 예약 정보를 가져옵니다.
		 * @param cnd
		 * @return
		 */
		public MessageBookingInfo get( MessageBookingCnd cnd ) {
				MessageBookingInfo info = messageBookingDAO.get( cnd );
				return info;
		}

		/**
		 * 조건에 맞는 메세지 예약 페이지 리스트를 가져옵니다.
		 * @param cnd
		 * @return
		 */
		public PageList<MessageBookingInfo> list( MessageBookingCnd cnd ) {
				PageList<MessageBookingInfo> list = messageBookingDAO.list( cnd );
				return list;
		}

		/**
		 * 조건에 맞는 메세지 예약 리스트를 가져옵니다.
		 * @param cnd
		 * @return
		 */
		public List<MessageBookingInfo> listAll( MessageBookingCnd cnd ) {
				List<MessageBookingInfo> list = messageBookingDAO.listAll( cnd );
				return list;
		}
		
		/**
		 * 예약되어있는 메세지를 발송합니다.
		 * 
		 * @author 최원준
		 * @param scheduleDateTime
		 */
		public void sendBookMessage( LocalDateTime scheduleDateTime ) {
				
				if( scheduleDateTime == null ) {
						return;
				}

				LocalDate bookingDay = LocalDate.from( scheduleDateTime );
				LocalTime bookingTime = LocalTime.from( scheduleDateTime );
				
				MessageBookingCnd cnd = new MessageBookingCnd();
				cnd.setProcessTypeFlag( MessageBookingInfo.PROCESS_TYPE_FLAG_WAIT );
				cnd.setBookingDay( bookingDay );
				cnd.setBookingTime( bookingTime );
				List<MessageBookingInfo> bookingList = listAll( cnd );
				
				if( CollectionUtils.isEmpty( bookingList ) ) {
						return;
				}

				for( MessageBookingInfo booking : bookingList ) {
						MessageCnd messageCnd = new MessageCnd();
						messageCnd.setOid( booking.getMessageOid() );
						MessageInfo messageInfo = messageBLO.get( messageCnd );

						if( messageInfo == null ) {
								continue;
						}

//						List<UserInfo> userList = new ArrayList<>();
						// 여기에서 메세지 발송을 위한 수신자를 채워줘야합니다.
						
//						if( CollectionUtils.isEmpty( userList ) ) {
//								continue;
//						}
//						messageInfo.setUserList( userList );
//						messageSendBLO.sendAll( messageInfo );
				}
				
		}
		
		/**
		 * 메세지 정보에 예약정보를 채워줍니다.
		 * 
		 * @author 최원준
		 * @param info
		 */
		public void fillBookingMessage( MessageInfo info ) {
				
				if ( info == null || StringUtils.isEmpty( info.getMsgBookingOid() ) ) {
						return;
				}
				
				MessageBookingCnd cnd = new MessageBookingCnd();
				cnd.setOid( info.getMsgBookingOid() );
				MessageBookingInfo booking = get( cnd );
				
				if( booking == null ) {
						return;
				}
				info.setMessageBookingInfo( booking );
		}
		
}
