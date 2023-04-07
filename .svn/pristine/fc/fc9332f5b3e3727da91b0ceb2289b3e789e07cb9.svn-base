package com.remarkablesoft.framework.common;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.junit.Test;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

public class RemarkableFactoryTest extends BaseModelTest {


	  public void 테스트() {

		  System.out.println( "11 : " + System.getProperty("remarkablesoft.InfoObjectFactory.UserInfo"));

		  System.setProperty( "remarkablesoft.RemarkableFactory.UserInfo", "com.remarkablesoft.framework.common.TestUserInfo");

		  UserInfo userInfo = SystemFactory.getUserInfo();


		  System.out.println( "22 : " + System.getProperty("remarkablesoft.InfoObjectFactory.UserInfo"));


		  System.out.println( userInfo);

		  System.out.println( "일반 유저 : " + (userInfo instanceof UserInfo));
		  System.out.println( "테스트 유저 : " + (userInfo instanceof TestUserInfo));



		  //ServerConfig.LOCAL
		  System.out.println(  "ServerConfig.LOCAL : " + SystemConfig.IS_LOCAL );
		  System.out.println(  "ServerConfig.DEV : " + SystemConfig.IS_DEV );
	  }

	  
	  
	  @Test
	  public void 프로퍼티_테스트() throws IllegalAccessException, InvocationTargetException {

		  System.setProperty( "bb", "온달");
		  
		  Properties a = new Properties();
		  a.put( "aa", "바보");
		  
		  
		  for(Object key : a.keySet()){
              
			  System.setProperty( String.valueOf( key ), String.valueOf( a.get( key)));
          }


	
//		  System.setProperties(a);
		  
		  
		  System.out.println( System.getProperty("bb"));
		  System.out.println( System.getProperty("aa"));
		  System.out.println( a.getProperty("aa"));
		  System.out.println( a.getProperty("bb"));
		  
		  
		  
		  
		  
	  }
}
