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
* 		@프로그램 ID		:	MessageGroupUserRelInfo
* 		@프로그램 개요 		:	메시지 그룹과 사용자를 연결
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
public class MessageGroupUserRelInfo extends Entity {

	/**
	 *
	 */
	private static final long serialVersionUID = -8433796426168881207L;

	private String messageGroupOid;
	private String userOid;
	private LocalDateTime inputDate = LocalDateTime.now();

}
