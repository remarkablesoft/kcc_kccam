package com.remarkablesoft.framework.service.notification.message.send.model.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.app.device.vo.DeviceInfo;
import com.remarkablesoft.framework.service.notification.message.message.model.impl.MessageBLO;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistCnd;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - send
* 		@프로그램 ID		:	MessageSendHistBLO
* 		@프로그램 개요 		:	메시지 발송 이력 BLO
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 4.	:	james	-	신규생성
* 		============================================================================
*/
@BLO
public class MessageSendHistBLO {

		@Autowired
		protected MessageSendHistDAO messageSendHistDAO;

		@Autowired
		protected MessageBLO messageBLO;
		
		/**
		 * 메시지 발송 이력을 저장합니다.
		 * @param info
		 * @return
		 */
		public MessageSendHistInfo insert( MessageSendHistInfo info ) {
				if( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}
				int result = messageSendHistDAO.insert( info );
				return result > 0 ? info : null;
		}
		
		
		public MessageSendHistInfo insert( MessageInfo message, UserInfo user, String errorMessage, String messageType ) {
				
				MessageSendHistInfo hist = SystemFactory.getMessageSendHistInfo();
				hist.setMessageOid( message.getOid() );
				hist.setPartOid( message.getPartOid() );
				hist.setReceiverOid( user.getOid() );
				hist.setReceiverPhone( user.getPhone() );

				String uuid = "";
				if ( CollectionUtils.isNotEmpty(  user.getDeviceList() ) ) {
						DeviceInfo device = user.getDeviceList().get( 0 );
						
						if ( device != null ) {
								uuid = device.getUuid();
						}
				}
				hist.setReceiverUuid( uuid );
				String successYn = StringUtils.isEmpty( errorMessage ) ? SystemConstants.FLAG_YES : SystemConstants.FLAG_NO;
				hist.setSuccessYn( successYn );
				hist.setErrorMessage( errorMessage );
				hist.setMessageType( messageType );
				
				if( message.getInputDate() == null ) {
						message.setInputDate( LocalDateTime.now() );
				}
				hist.setInputDate( message.getInputDate() );
				
				return insert( hist );
		}
		
		/**
		 * 메시지 발송 이력을 수정합니다.
		 * @param info
		 * @return
		 */
		public MessageSendHistInfo update( MessageSendHistInfo info ) {
				if( StringUtils.isEmpty( info.getOid() ) ) {
						return null;
				}
				int result = messageSendHistDAO.update( info );
				return result > 0 ? info : null;
		}

		/**
		 * 메시지 발송 이력을 삭제합니다.
		 * @param cnd
		 * @return
		 */
		public int delete( MessageSendHistCnd cnd ) {
				if( StringUtils.isEmpty( cnd.getOid() ) ) {
						return 0;
				}
				return messageSendHistDAO.delete( cnd );
		}

		/**
		 * 조건에 맞는 메시지 발송이력 페이지 리스트를 가져옵니다.
		 * @param cnd
		 * @return
		 */
		public PageList<MessageSendHistInfo> list( MessageSendHistCnd cnd ) {

				PageList<MessageSendHistInfo> list = messageSendHistDAO.list( cnd );
				if( cnd.isFillMessage() ) {
						messageBLO.fillMessage( list );
				}
				
				return list;
		}

		/**
		 * 조건에 맞는 메시지 발송이력 리스트를 가져옵니다.
		 * @param cnd
		 * @return
		 */
		public List<MessageSendHistInfo> listAll( MessageSendHistCnd cnd ) {

				return messageSendHistDAO.listAll( cnd );
		}

		/**
		 * 메시지 OID로 GroupBy된 리스트를 가져옵니다.
		 * @param cnd
		 * @return
		 */
		public List<MessageSendHistInfo> listByMsgOid( MessageSendHistCnd cnd ) {
				
				return messageSendHistDAO.listByMsgOid( cnd );
		}
		
}
