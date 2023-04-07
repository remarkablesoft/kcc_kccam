package com.remarkablesoft.framework.web.module.notification.email.impl.kcc.exception;

/**
 * @주시스템		    :	kccam
 * @서브 시스템		:   module - notification - email
 * @프로그램 ID		:   KccMailException.java
 * @프로그램 개요	    :   KCC 이메일 발송 Exception
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-05-07 : 최원준 - 신규생성
 * ============================================================================
 */
public class KccMailException extends RuntimeException{
	
	public KccMailException() {
		super();
	}
	
	public KccMailException( String msg) {
		super(msg);
	}
}
