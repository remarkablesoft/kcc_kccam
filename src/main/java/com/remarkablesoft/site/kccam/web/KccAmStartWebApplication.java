package com.remarkablesoft.site.kccam.web;

import com.remarkablesoft.config.AppConfig;
import com.remarkablesoft.framework.util.ApplicationPropertiesUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan( basePackages = "com.remarkablesoft" )
@Import( { AppConfig.class } )
public class KccAmStartWebApplication {

	/**
	 * 구동하는 방법
	 * 설문은 -vm 파라메터로
	 * -Dspring.profiles.active=surveyLocal -Dspring.config.location=src/main/resources/envConfig/surveyLocal/application-surveyLocal.properties
	 *
	 * 로컬은
	 * -Dspring.profiles.active=local -Dspring.config.location=src/main/resources/envConfig/local/application-local.properties
	 *
	 * 개발은
	 * -Dspring.profiles.active=dev -Dspring.config.location=src/main/resources/envConfig/dev/application-dev.properties
	 *
	 *
	 * @author james
	 * @param args
	 */
	public static void main( String[] args ) {

		try {
			String profile = System.getProperty( "spring.profiles.active", "local" );
			System.setProperty( "spring.profiles.active", profile );

			String config = System.getProperty( "spring.config.location" );
			System.out.println( "config : " + config );

			System.out.println( "####################################" );
			System.out.println( "spring.profiles.active : " + System.getProperty( "spring.profiles.active" ) );
			System.out.println( "server.upload.file.path : " + ApplicationPropertiesUtils.getValue( "server.upload.file.path" ) );
			System.out.println( "####################################" );

			// 이클립스에서 SilentExitException 안나오게 처리
			System.setProperty( "spring.devtools.restart.enabled", "false" );

			ApplicationPropertiesUtils2.setSystemPropertis();

			SpringApplication.run( KccAmStartWebApplication.class, args );
		}
		catch( Throwable t ) {
			t.printStackTrace();
		}

	}

//	@Override
//	protected SpringApplicationBuilder configure( SpringApplicationBuilder application ) {
//
//		return application.sources( StartWebApplication.class );
//	}
}
