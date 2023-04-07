package com.remarkablesoft.framework.service.notification.message.message.model.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.board.contents.model.impl.ContentsBLO;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageCnd;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.vo.MessageSendHistInfo;
import com.remarkablesoft.framework.service.notification.message.template.model.impl.MessageTemplateBLO;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.util.AutheUtils;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - message
* 		@프로그램 ID		:	MessageBLO
* 		@프로그램 개요 		:	메시지 BLO
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 4.	:	james	-	신규생성
* 		============================================================================
*/
@BLO
public class MessageBLO {

		@Autowired
		protected MessageDAO messageDAO;

		@Autowired
		protected MessageTemplateBLO messageTemplateBLO;

		@Autowired
		protected ContentsBLO contentsBLO;
		
		/**
		 * 메시지 정보를 저장합니다.
		 * 
		 * @param info
		 * @return
		 */
		public MessageInfo insert( MessageInfo info ) {

				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}

				if ( StringUtils.isEmpty( info.getInputUser() ) ) {

						String loginUser = StringUtils.isNotEmpty( AutheUtils.getLoginUserOid() ) ? AutheUtils.getLoginUserOid() : SystemConstants.DEFAULT_SYSTEM_ACCOUNT;
						info.setInputUser( loginUser );
				}
				if ( info.getInputDate() == null ) {
						info.setInputDate( LocalDateTime.now() );
				}
			
				ContentsInfo contentsInfo = SystemFactory.getContentsInfo();
				contentsInfo.setTargetOid( info.getOid() ).setTargetObject( MessageInfo.getObjectType() )
							.setContents( info.getContents() ).setInputUser( info.getInputUser() );
				
				contentsBLO.insert( contentsInfo );

				info.setContents( "" );
				int result = messageDAO.insert( info );
				
				info.setContents( contentsInfo.getContents() );
				return result == 0 ? null : info;
		}

		/**
		 * 메시지 정보를 수정합니다.
		 * 
		 * @param info
		 * @return
		 */
		public MessageInfo update( MessageInfo info ) {

				if ( StringUtils.isEmpty( info.getOid() ) ) {
						return null;
				}

				int result = messageDAO.update( info );
				return result == 0 ? null : info;
		}

		/**
		 * 메시지 정보를 가져옵니다.
		 * @param cnd
		 * @return
		 */
		public MessageInfo get( MessageCnd cnd ) {

				MessageInfo info = messageDAO.get( cnd );
				if ( info == null ) {
						return null;
				}
				
				// 템플릿의 데이터를 치환하는 역활
				messageTemplateBLO.fillTemplateMessage( info, null );

				return info;
		}

		/**
		 * 조건에 맞는 메시지 정보 페이지 리스트를 가져옵니다.
		 * @param cnd
		 * @return
		 */
		public PageList<MessageInfo> list( MessageCnd cnd ) {
				
				PageList<MessageInfo> list = messageDAO.list( cnd );
				return list;
		}

		/**
		 * 조건에 맞는 메시지 정보 리스트를 가져옵니다.
		 * @param cnd
		 * @return
		 */
		public List<MessageInfo> listAll( MessageCnd cnd ) {
				
				List<MessageInfo> list = messageDAO.listAll( cnd );
				return list;
		}


		
		/**
		 * 메세지 이력에 메세지 정보를 채워줍니다.
		 * 
		 * @author 최원준
		 * @param list
		 */
		public void fillMessage( List<MessageSendHistInfo> list ) {

				if ( CollectionUtils.isEmpty( list ) ) {
						return;
				}

				List<String> messageOidList = list.stream().map( MessageSendHistInfo::getMessageOid ).collect( Collectors.toList() );

				MessageCnd cnd = new MessageCnd();
				cnd.setOidList( messageOidList );
				List<MessageInfo> messageList = listAll( cnd );

				if ( CollectionUtils.isEmpty( messageList ) ) {
						return;
				}

				list.forEach( hist -> {
						messageList.stream()
								   .filter( msg -> msg != null && StringUtils.hasText( hist.getMessageOid() ) )
								   .filter( msg -> hist.getMessageOid().equals( msg.getOid() ) )
								   .forEach( msg -> hist.setMessageInfo( msg ) );
				} );

		}

}
