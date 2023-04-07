package com.remarkablesoft.framework.web.module.notification.message.template.impl;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.service.notification.message.template.vo.MessageTemplateInfo;
import com.remarkablesoft.framework.service.notification.message.template.vo.MessageTemplateItemInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.notification.MessageTemplateProvider;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module	-	notification	-	template
 * 		@프로그램 ID		:	MessageTemplateManagerImpl
 * 		@프로그램 개요 		:	템플릿 내용 구성 기능 기본 구현체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 7. 30.	:	최원준	-	신규생성
 * 		============================================================================
 */
@BLO
public class MessageTemplateProviderImpl implements MessageTemplateProvider {

		@Override
		public String makeTemplateContents( MessageTemplateInfo msgTemplate, UserInfo user ) {

				if ( CollectionUtils.isEmpty( msgTemplate.getTemplateItemList() ) ) {
						return "";
				}

				HashMap<String, String> itemMap = new HashMap<>();
				for ( MessageTemplateItemInfo itemInfo : msgTemplate.getTemplateItemList() ) {

						if ( user != null ) {

								if ( USER_NAME.equals( itemInfo.getItemValues() ) ) {
										itemMap.put( itemInfo.getItemKeys(), user.getName() );
								}
								else if ( USER_PHONE.equals( itemInfo.getItemValues() ) ) {
										itemMap.put( itemInfo.getItemKeys(), user.getPhone() );
								}
								else {
										itemMap.put( itemInfo.getItemKeys(), itemInfo.getItemValues() );
								}
						}
						else {
								itemMap.put( itemInfo.getItemKeys(), itemInfo.getItemValues() );
						}

				}

				return makeTemplateContents( msgTemplate, itemMap );
		}

		@Override
		public String makeTemplateContents( MessageTemplateInfo msgTemplate ) {

				if ( CollectionUtils.isEmpty( msgTemplate.getTemplateItemList() ) ) {
						return "";
				}

				Map<String, String> itemMap = msgTemplate.getTemplateItemList()
														 .stream()
														 .collect( Collectors.toMap( MessageTemplateItemInfo::getItemKeys, MessageTemplateItemInfo::getItemValues ) );

				return makeTemplateContents( msgTemplate, itemMap );
		}

		@Override
		public String makeTemplateContents( MessageTemplateInfo info, Map<String, String> itemMap ) {

				String contents = info.getContents();
				if ( StringUtils.isEmpty( contents ) || CollectionUtils.isEmpty( info.getTemplateItemList() ) || itemMap.size() == 0 ) {
						return "";
				}

				for ( MessageTemplateItemInfo item : info.getTemplateItemList() ) {
						if ( !StringUtils.contains( contents, item.getItemKeys() ) ) {
								continue;
						}
						contents = StringUtils.replace( contents, item.getItemKeys(), itemMap.get( item.getItemKeys() ) );
				}

				return contents;
		}

}
