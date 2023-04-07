package com.remarkablesoft.framework.web.controller.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	web
 * 		@프로그램 ID		:	ErrorResponse
 * 		@프로그램 개요 	:	에러반환시 사용	
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2021. 1. 4.	:	james	-	신규생성
 * 		============================================================================
 */
@Getter
@NoArgsConstructor( access = AccessLevel.PROTECTED )
public class ErrorResponse {

		private String message;
		private int status;
		private String error;
		private String path;

		private ErrorResponse( String message, int status, String error, String path ) {
				this.message = message;
				this.status = status;
				this.error = error;
				this.path = path;
		}

		public static ErrorResponse of( String message, int status, String error, String path) {
				return new ErrorResponse( message, status, error, path );
		}

}
