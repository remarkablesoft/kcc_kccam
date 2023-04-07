package com.remarkablesoft.framework.service.notification.message.template.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageCnd;
import com.remarkablesoft.framework.service.notification.message.template.vo.MessageTemplateInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	notification - message - template
 * 		@프로그램 ID		:	MessageTemplateService
 * 		@프로그램 개요 		:	메세지 템플릿 서비스
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 1. 3.	:	최원준	-	신규생성
 * 		============================================================================
 */
public interface MessageTemplateService {

		/**
		 * 메세지 템플릿을 삭제합니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public int delete( MessageCnd cnd );
		
		/**
		 * 메세지 템플릿을 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public MessageTemplateInfo get( MessageCnd cnd );
		
		/**
		 * 메세지 템플릿 페이지리스트를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public PageList<MessageTemplateInfo> list( MessageCnd cnd );
		
		/**
		 * 메세지 템플릿 리스트를 가져옵니다.
		 * 
		 * @author 최원준
		 * @param cnd
		 * @return
		 */
		public List<MessageTemplateInfo> listAll( MessageCnd cnd);
		
		
		
		/**
		 * 메세지 템플릿 저장 및 수정하기
		 * 
		 * @param info
		 * @return
		 */
		public MessageTemplateInfo insertOrUpdate( MessageTemplateInfo info );
		
}
