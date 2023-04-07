package com.remarkablesoft.framework.service.notification.message.send.model.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistCnd;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendResultInfo;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	notification - message - send
 * 		@프로그램 ID		:	MessageSendResultBLO
 * 		@프로그램 개요 		:	메세지 발신 결과 로직
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 1. 13.	:	최원준	-	신규생성
 * 		============================================================================
 */
@BLO
public class MessageSendResultBLO {

		@Autowired
		protected MessageSendResultDAO messageSendResultDAO;
		
		/**
		 * 메세지 발신 결과를 저장합니다.
		 * 
		 * @author 최원준
		 * @param info
		 * @return
		 */
		public MessageSendResultInfo insert( MessageSendResultInfo info ) {
				if( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}
				int result = messageSendResultDAO.insert( info );
				return result > 0 ? info : null;
		}
		
		/**
		 * 발송 메세지와 성공실패 카운트로 메세지 발송 결과를 저장합니다.
		 * 
		 * @author 최원준
		 * @param message
		 * @param totalCnt
		 * @param failCnt
		 * @return
		 */
		public MessageSendResultInfo insert( MessageInfo message, int totalCnt, int failCnt ) {
				
				if( message == null ) {
						return null;
				}
				
				MessageSendResultInfo info = SystemFactory.getMessageSendResultInfo();
				info.setMessageOid( message.getOid() );
				info.setPartOid( message.getPartOid() );
				info.setSendTotalCount( totalCnt );
				info.setSendSuccessCount( totalCnt - failCnt );
				info.setSendFailCount( failCnt );
				
				info.setInputUser( message.getInputUser() );
				if( message.getInputDate() == null ) {
						message.setInputDate( LocalDateTime.now() );
				}
				info.setInputDate( message.getInputDate() );
				
				return insert( info );
				
		}
		
		/**
		 * 메세지 발신 결과를 수정합니다.
		 * 
		 * @author 최원준
		 * @param info
		 * @return
		 */
		public MessageSendResultInfo update( MessageSendResultInfo info ) {
				if( StringUtils.isEmpty( info.getOid() ) ) {
						return null;
				}
				int result = messageSendResultDAO.update( info );
				return result > 0 ? info : null;
		}

		/**
		 * 메세지 발신 결과를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public MessageSendResultInfo get( MessageSendHistCnd cnd ) {
				
				return messageSendResultDAO.get( cnd );
		}
		
		/**
		 * 메세지 발신결과 페이지 리스트를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public PageList<MessageSendResultInfo> list( MessageSendHistCnd cnd ) {
				
				return messageSendResultDAO.list( cnd );
		}

		/**
		 * 메세지 발신결과 리스트를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public List<MessageSendResultInfo> listAll( MessageSendHistCnd cnd ) {
				
				return messageSendResultDAO.listAll( cnd );
		}
		
}
