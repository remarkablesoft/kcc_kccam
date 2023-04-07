package com.remarkablesoft.framework.service.notification.message.group.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - group
* 		@프로그램 ID		:	MessageGroupInfo
* 		@프로그램 개요 		:	메시지 그룹 객체
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
public class MessageGroupInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = 5594999990728187575L;

		private String oid;
		private String branchOid;					// 지점 OID
		private String partOid;						// PART OID
		private String name;						// 메세지 그룹명
		private String descr;						// 설명
	
		private int orderNo;						// 그룹정렬순서
		private String inputUser;					// 생성자
		private LocalDateTime inputDate;			// 생성일

}
