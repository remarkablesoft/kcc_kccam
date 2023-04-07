package com.remarkablesoft.framework.service.org.user.model;

import com.remarkablesoft.framework.annotation.BLO;

/**
 * 
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	org - user
 * 		@프로그램 ID		:	UserPasswordBLO
 * 		@프로그램 개요 	:	패스워드 구현체를 여러개 사용하기 위해	
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 12. 10.	:	james	-	신규생성
 * 		============================================================================
 */
@BLO
public interface UserPasswordBLO {


		/**
		 * password를 encrypt  
		 * 
		 * @author james
		 * @param pwd
		 * @return
		 */
		public String encrypt( String pwd );
		
		
		/**
		 * encrypt된 문자열 길이
		 * 
		 * @author james
		 * @param pwd
		 * @return
		 */
		public int getEncryptCharLength();
}
