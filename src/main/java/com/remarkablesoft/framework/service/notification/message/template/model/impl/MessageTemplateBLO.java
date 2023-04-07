package com.remarkablesoft.framework.service.notification.message.template.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageCnd;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.template.vo.MessageTemplateInfo;
import com.remarkablesoft.framework.service.notification.message.template.vo.MessageTemplateItemInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.notification.MessageTemplateProvider;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	notification - message - template
 * 		@프로그램 ID		:	MessageTemplateBLO
 * 		@프로그램 개요		:	메세지 템플릿 로직
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 1. 3.	:	최원준	-	신규생성
 * 		============================================================================
 */
@BLO
public class MessageTemplateBLO {

		@Autowired
		protected MessageTemplateDAO messageTemplateDAO;

		@Autowired
		protected MessageTemplateItemDAO messageTemplateItemDAO;

		@Autowired
		protected MessageTemplateProvider messageTemplateProvider;

		public MessageTemplateInfo insertOrUpdate( MessageTemplateInfo info ) {
		
			if ( StringUtils.isEmpty( info.getOid() ) ) {
				
				return insert( info );
			}
			else {
								
				return update( info );
			}
					
		}
		
		/**
		 * 메세지 템플릿을 저장합니다.
		 *
		 * @author 최원준
		 * @param info
		 * @return
		 */
		public MessageTemplateInfo insert( MessageTemplateInfo info ) {

				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}

				if ( CollectionUtils.isNotEmpty( info.getTemplateItemList() ) ) {
						insertTemplateItem( info.getTemplateItemList(), info.getOid() );
				}

				int result = messageTemplateDAO.insert( info );
				return result > 0 ? info : null;
		}

		/**
		 * 템플릿의 속성 항목들을 저장합니다.
		 * 
		 * @author 최원준
		 * @param itemList
		 * @param templateOid
		 */
		public void insertTemplateItem( List<MessageTemplateItemInfo> itemList, String templateOid ) {
				
				itemList.forEach( ti -> {
						if ( StringUtils.isEmpty( ti.getOid() ) ) {
								ti.setOid( OIDGenerator.generateOID() );
						}
						ti.setMessageTemplateOid( templateOid );
						messageTemplateItemDAO.insert( ti );
				} );
		}

		/**
		 * 메세지 템플릿을 수정합니다.
		 *
		 * @author 최원준
		 * @param info
		 * @return
		 */
		public MessageTemplateInfo update( MessageTemplateInfo info ) {

				if ( StringUtils.isEmpty( info.getOid() ) ) {
						return null;
				}

				int result = messageTemplateDAO.update( info );
				
				MessageCnd messageCnd = new MessageCnd();
				messageCnd.setMsgTemplateOid( info.getOid() );
				messageTemplateItemDAO.delete( messageCnd );
				
				if ( CollectionUtils.isNotEmpty( info.getTemplateItemList() ) ) {
					insertTemplateItem( info.getTemplateItemList(), info.getOid() );
				}				
				
				return result > 0 ? info : null;
		}

		/**
		 * 메세지 템플릿을 삭제합니다.
		 *
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public int delete( MessageCnd cnd ) {

				if ( StringUtils.isEmpty( cnd.getOid() ) ) {
						return 0;
				}
				return messageTemplateDAO.delete( cnd );
		}

		/**
		 *	메세지 템플릿을 가져옵니다.
		 *
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public MessageTemplateInfo get( MessageCnd cnd ) {

				MessageTemplateInfo info = messageTemplateDAO.get( cnd );
				fillTemplateItem( info );
				return info;
		}

		/**
		 * 메세지 템플릿 페이지리스트를 가져옵니다.
		 *
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public PageList<MessageTemplateInfo> list( MessageCnd cnd ) {

				return messageTemplateDAO.list( cnd );
		}

		/**
		 * 메세지 템플릿 리스트를 가져옵니다.
		 *
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public List<MessageTemplateInfo> listAll( MessageCnd cnd ) {

				return messageTemplateDAO.listAll( cnd );
		}

		/**
		 * 템플릿 아이디와 템플릿 변수 항목 리스트로 템플릿 내용을 만듭니다.
		 * 
		 * @author 최원준
		 * @param templateId
		 * @param msgTemplateItemList
		 * @return
		 */
		public String makeTemplateContentsById( String templateId, List<MessageTemplateItemInfo> msgTemplateItemList ) {

				if ( StringUtils.isEmpty( templateId ) ) {
						return "";
				}

				MessageCnd cnd = new MessageCnd();
				cnd.setTemplateId( templateId );
				MessageTemplateInfo msgTemplate = get( cnd );
				if ( msgTemplate == null ) {
						return "";
				}

				msgTemplate.setTemplateItemList( msgTemplateItemList );

				return messageTemplateProvider.makeTemplateContents( msgTemplate );
		}

		/**
		 * 메세지에 설정된 템플릿 정보를 채워줍니다.
		 *
		 * @author 최원준
		 * @param message
		 */
		public void fillTemplateMessage( MessageInfo message, UserInfo user ) {

				if ( message == null || ( StringUtils.isEmpty( message.getMsgTemplateOid() ) && message.getMessageTemplateInfo() == null ) ) {
						return;
				}
				
				MessageTemplateInfo template = message.getMessageTemplateInfo();

				MessageCnd cnd = new MessageCnd();
				cnd.setOid( message.getMsgTemplateOid() );
				cnd.setTemplateId( template != null ? template.getTemplateId() : "");
				
				MessageTemplateInfo resultTemplate = get( cnd );
				if ( resultTemplate == null ) {
						return;
				}
				
				List<MessageTemplateItemInfo> templateItemList = template.getTemplateItemList();

				if ( CollectionUtils.isNotEmpty( templateItemList ) ) {
						resultTemplate.setTemplateItemList( templateItemList );
						String templateContents = messageTemplateProvider.makeTemplateContents( resultTemplate, user );
						resultTemplate.setContents( templateContents );
						
						message.setContents( templateContents );
				}

				
				message.setMsgTemplateOid( resultTemplate.getOid() );
				message.setMessageTemplateInfo( resultTemplate );
				
				return;
		}
	
	/**
	 * 메세지 템플릿 아이템을 채워줍니다.
	 *
	 * @param info
	 * @author 최원준
	 */
	public void fillTemplateItem( MessageTemplateInfo info ) {
		
		if ( info == null ) {
			return;
		}
		
		MessageCnd cnd = new MessageCnd();
		cnd.setMsgTemplateOid( info.getOid() );
		
		List<MessageTemplateItemInfo> templateItemList = messageTemplateItemDAO.listAll( cnd );
		if ( CollectionUtils.isEmpty( templateItemList ) ) {
						return;
				}

				info.setTemplateItemList( templateItemList );

		}
}
