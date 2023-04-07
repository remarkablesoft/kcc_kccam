package com.remarkablesoft.config;

import javax.annotation.PostConstruct;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.remarkablesoft.framework.module.log.CommonLogger;
import com.remarkablesoft.framework.module.log.CommonLoggerFactory;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.web.module.security.SecurityContext;
import com.remarkablesoft.framework.web.module.security.SecurityContextHolder;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { AppConfigTest.class },
						loader = AnnotationConfigContextLoader.class )
@Commit
public class BaseModelTest
{

	public static CommonLogger log = CommonLoggerFactory.getLogger( "com.remarkablesoft.test" );

	@PostConstruct
	public void init() {

			dummyLoginUser();
	}


	public void dummyLoginUser() {

			UserInfo user = SystemFactory.getUserInfo();
			user.setOid( "admin" );

			SecurityContext securityContext = new SecurityContext( user );
			SecurityContextHolder.setSecurityContext( securityContext );

	}
}
