package com.remarkablesoft.framework.web.module.notification.email.impl.kcc;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

/**
 * @주시스템		    :	kccam
 * @서브 시스템		:   module - notification - email
 * @프로그램 ID		:   KccEmailSendProviderTest.java
 * @프로그램 개요	    :   KCC 이메일 발송 테스트
 *
 * @변경이력
 *  ============================================================================
 * 1.0 2021-05-11 : 최원준 - 신규생성
 * ============================================================================
 */
public class KccEmailSendProviderTest extends BaseModelTest {
	
	@Autowired
	KccEmailSendProvider kccEmailSendProvider;
	
	/**
	* KCC 이메일 발송 테스트를 합니다.
	* KCC 내부망에서만 테스트 가능합니다. 
	* KCC Glass VPN 접속하여 테스트 가능합니다.
	*
	* @author 최원준
	*/
	@Test
	public void KCC이메일발송_테스트() {
		
		String name = "KCC Advanced Materials";
		String from = "kccmaterials@kccworld.co.kr";
		String to = "hha717@naver.com";
		
		String cc = "";
		String title = "메일 테스트 제목입니다.";
		String content = "내용입니다.";
		String tar = "";
		
		List<String> fileList = new ArrayList();
		fileList.add( "C:/Users/hha71/Pictures/9.jpg" );
		
		String[] filenames = fileList.toArray( new String[fileList.size()]);
		
		kccEmailSendProvider.sendMail( name, from, to, cc, title, content, tar, filenames );
		
	}
	
	
}