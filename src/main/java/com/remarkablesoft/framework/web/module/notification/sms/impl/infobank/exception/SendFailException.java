package com.remarkablesoft.framework.web.module.notification.sms.impl.infobank.exception;

import org.springframework.core.NestedCheckedException;

/**
 * 		@주시스템			:	roltech
 * 		@서브 시스템		:
 * 		@프로그램 ID		:	SendFailException.java
 * 		@프로그램 개요 	:	발송실패 예외
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 3. 8.	:	james	-	신규생성
 * 		============================================================================
 */
public class SendFailException extends NestedCheckedException {

		/**
		 *
		 */
		private static final long serialVersionUID = 8428197917990687003L;

		/**
		 * 생성자
		 *
		 * @param msg
		 */
		public SendFailException( String msg ) {
				super( msg );
		}

		/**
		 * 생성자
		 *
		 * @param msg
		 * @param cause
		 */
		public SendFailException( String msg, Throwable cause ) {
				super( msg, cause );
		}
}
