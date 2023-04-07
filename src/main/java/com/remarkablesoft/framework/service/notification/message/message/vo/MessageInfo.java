package com.remarkablesoft.framework.service.notification.message.message.vo;

import java.time.LocalDateTime;
import java.util.HashMap;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.notification.message.booking.vo.MessageBookingInfo;
import com.remarkablesoft.framework.service.notification.message.template.vo.MessageTemplateInfo;
import com.remarkablesoft.site.kccam.service.AmConstants;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	notification - message - message
* 		@프로그램 ID		:	MessageInfo
* 		@프로그램 개요 		:	메시지 객체 - PUSH, SMS, 알림톡, 친구톡의 메시지를 담고 있는 객체
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
public class MessageInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = 3519474155788178666L;

		public static final String MESSAGE_SEND_TYPE_FLAG_IMMEDIATE = "I"; 			// 즉시 발송
		public static final String MESSAGE_SEND_TYPE_FLAG_BOOK 		= "B";			// 예약 발송
		
		public static final String MESSAGE_CLASS_TYPE_SYSTEM 		= "SYSTEM";		// SYSTEM이 내부적으로 보낼시 사용
		
		public static final int MESSAGE_TYPE_MODE_NORMAL 			= 0x00000001;	// 메세지 타입 일반 - 내부 메세지용
		public static final int MESSAGE_TYPE_MODE_PUSH 				= 0x00000002;	// 메세지 타입 PUSH
		public static final int MESSAGE_TYPE_MODE_KAKAO_NOTI 		= 0x00000004;	// 메세지 타입 알림톡
		public static final int MESSAGE_TYPE_MODE_KAKAO_FRIEND 		= 0x00000008;	// 메세지 타입 친구톡
		public static final int MESSAGE_TYPE_MODE_SMS 				= 0x00000010;	// 메세지 타입 SMS
		public static final int MESSAGE_TYPE_MODE_EMAIL 			= 0x00000020;	// 메세지 타입 EMAIL
		
		
		
		public static final long MESSAGE_TYPE_MODE_ALL = MESSAGE_TYPE_MODE_PUSH  
								| MESSAGE_TYPE_MODE_KAKAO_NOTI	| MESSAGE_TYPE_MODE_KAKAO_FRIEND 
								| MESSAGE_TYPE_MODE_SMS | MESSAGE_TYPE_MODE_EMAIL
								| MESSAGE_TYPE_MODE_NORMAL ;						// 메세지 타입 전체
		

		public final static String MESSAGE_TYPE_NORMAL 				= "MSTP0001";	// 일반 - 내부 메세지용
		public final static String MESSAGE_TYPE_PUSH 				= "MSTP0002";	// PUSH
		public final static String MESSAGE_TYPE_NOTI 				= "MSTP0004";	// 알림톡
		public final static String MESSAGE_TYPE_FRIEND 				= "MSTP0008";	// 친구톡
		public final static String MESSAGE_TYPE_SMS 				= "MSTP0010";	// SMS
		public final static String MESSAGE_TYPE_EMAIL 				= "MSTP0020";	// EMAIL
		public final static String MESSAGE_TYPE_ALL 				= "ALL";		// ALL
		
	
		
		private String oid;
		private String systemOid;													// 시스템 OID
		private String partOid	;													// Part OID
		private int msgTypeMode = -1;													// 메시지타입(친구톡/알림톡/SMS/Push/....)
		private String classType;													// 분류코드 코드값을 지정해서 사용 ex) 활동보고, 공지사항
		
		private String name;														// 메세지명
		private String msgTemplateOid;												// 메세지 템플릿 OID
		private String msgBookingOid;												// 메세지 예약 OID
		private String sendTypeFlag;												// 발송방법 즉시발송 I, 예약발송 B
		private String replaceMsgYn;												// 대체문자 발송
		
		private String contents;													// 메세지 내용
		private String imageUrl1;													// 이미지 Url1
		private String imageUrl2;													// 이미지 Url2
		private String imageUrl3;													// 이미지 Url3

		private String linkUrl1;													// 링크 Url1
		private String linkUrl2;													// 링크 Url2
		private String linkUrl3;													// 링크 Url3

		private String inputUser;													// 생성자, 발신자
		private LocalDateTime inputDate;											// 생성일자

		private String customField1;												// 커스텀필드1
		private String customField2;												// 커스텀필드2
		private String customField3;												// 커스텀필드3

		private MessageTemplateInfo messageTemplateInfo = null;						// 메세지에 설정된 템플릿 정보
		private MessageBookingInfo messageBookingInfo = null;						// 메세지에 설정된 예약 정보
		
		private HashMap<String, Object> extraInfoMap = new HashMap<>();				// 부가적인 정보가 필요시. - 커스텀된 사이트에서 사용
	
		public static String getObjectType() {
			return SystemConstants.OBJECT_FW_TYPE_MESSAGE.getKey();
		}		
}
