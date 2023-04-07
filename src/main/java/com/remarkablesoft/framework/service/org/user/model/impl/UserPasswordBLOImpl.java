package com.remarkablesoft.framework.service.org.user.model.impl;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.service.org.user.model.UserPasswordBLO;
import com.remarkablesoft.framework.util.EncryptUtils;


/**
 * 
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	org - user
 * 		@프로그램 ID		:	UserPasswordBLOImpl
 * 		@프로그램 개요 	:	리마커블 패스워드 Impl
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 12. 10.	:	james	-	신규생성
 * 		============================================================================
 */
@BLO
public class UserPasswordBLOImpl implements UserPasswordBLO {

		@Override
		public String encrypt( String pwd ) {
				
				return EncryptUtils.encryptSHA256( pwd ) ;
		}

		@Override
		public int getEncryptCharLength( ) {

				return EncryptUtils.ENCRYPT_CHAR_LENGTH;
		}

}
