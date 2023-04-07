package com.remarkablesoft.framework.service.notification.message.group.model.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.group.vo.MessageGroupCnd;
import com.remarkablesoft.framework.service.notification.message.group.vo.MessageGroupInfo;
import com.remarkablesoft.framework.service.notification.message.group.vo.MessageGroupUserRelInfo;
import com.remarkablesoft.framework.service.org.user.model.impl.UserBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserCnd;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - group
* 		@프로그램 ID		:	MessageGroupBLO
* 		@프로그램 개요 		:	메시지 그룹 BLO
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 4.	:	james	-	신규생성
* 		============================================================================
*/
@BLO
public class MessageGroupBLO {

		@Autowired
		protected MessageGroupDAO messageGroupDAO;

		@Autowired
		protected MessageGroupUserRelDAO messageGroupUserRelDAO;
		
		@Autowired
		protected UserBLO userBLO;
		
		/**
		 * 메시지 그룹 정보를 저장합니다.
		 * 
		 * @param info
		 * @return
		 */
		public MessageGroupInfo insert( MessageGroupInfo info ) {
				if( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}

				int result = messageGroupDAO.insert( info );
				return result == 0 ? null : info;
		}

		/**
		 * 메시지 그룹 정보를 수정합니다.
		 * 
		 * @param info
		 * @return
		 */
		public MessageGroupInfo update( MessageGroupInfo info ) {
				if( StringUtils.isEmpty( info.getOid() ) ) {
						return null;
				}
				int result = messageGroupDAO.update( info );
				return result == 0 ? null : info;
		}

		/**
		 * 메시지 그룹 정보를 삭제합니다.
		 * 
		 * @param cnd
		 * @return
		 */
		 public int delete( MessageGroupCnd cnd ) {
				 if( StringUtils.isEmpty( cnd.getOid() ) ) {
						 return 0;
				 }
				 return messageGroupDAO.delete( cnd );
		 }

		/**
		 *  메시지 그룹 정보를 가져옵니다.
		 *  
		 * @param cnd
		 * @return
		 */
		public MessageGroupInfo get( MessageGroupCnd cnd ) {

				MessageGroupInfo info = messageGroupDAO.get( cnd );

				return info;
		}

		/**
		 * 조건에 맞는 메시지 그룹 정보 리스트를 가져옵니다.
		 * 
		 * @param cnd
		 * @return
		 */
		public List<MessageGroupInfo> listAll( MessageGroupCnd cnd ) {
				List<MessageGroupInfo> list = messageGroupDAO.listAll( cnd );
				return list;
		}
		
		
		/**
		 * 메세지 그룹정보에 속해있는 유저 페이지 리스트를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public PageList<UserInfo> groupUserList( MessageGroupCnd cnd ) {
				
				if( StringUtils.isEmpty( cnd.getMessageGroupOid() ) && CollectionUtils.isEmpty( cnd.getOidList() ) ) {
						return null;
				}
				
				List<MessageGroupUserRelInfo> relList = messageGroupUserRelDAO.listAll( cnd );
				if( CollectionUtils.isEmpty( relList ) ) {
						return null;
				}
				List<String> userOidList = relList.stream().map( MessageGroupUserRelInfo::getUserOid ).collect( Collectors.toList() );

				UserCnd userCnd = new UserCnd();
				userCnd.setUserOidList( userOidList );
				PageList<UserInfo> userList = userBLO.listUser( userCnd );
				return userList;
				
		}
		
}
