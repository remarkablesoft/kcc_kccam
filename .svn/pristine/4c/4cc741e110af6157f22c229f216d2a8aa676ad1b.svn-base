package com.remarkablesoft.framework.service.notification.message.group.vo;

import java.util.List;

import com.remarkablesoft.framework.model.vo.SearchCnd;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - group
* 		@프로그램 ID		:	MessageGroupCnd
* 		@프로그램 개요 		:	메시지 그룹 검색 객체
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 4.	:	james	-	신규생성
* 		============================================================================
*/

@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class MessageGroupCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = 2567787169378238025L;

		private String oid;											// OID
		private String branchOid;									// 지점 OID
		private String partOid;										// PART OID
		private String name;										// 그룹 명
		private String messageGroupOid;								// 그룹 OID( GROUP - USER REL에서 사용 )
	
		private String userOid;										// 유저 OID( GROUP - USER REL에서 사용 )

		private List<String> oidList = null;						// 그룹 OID 리스트
		
		
}
