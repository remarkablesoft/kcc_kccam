package com.remarkablesoft.config.env;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

// import org.apache.tomcat.jdbc.pool.DataSource;
// import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.remarkablesoft.framework.model.config.DBConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Profile( "real" )
public class DBRealConfig implements DBConfig {

		@PostConstruct
		public void init() {
				System.out.println( "중요!!! 운영서버용입니다! " );
		}

		@Override
		@Bean( destroyMethod = "close" )
		public DataSource dataSource() {
				HikariConfig hikariConfig = new HikariConfig();
				hikariConfig.setDriverClassName( "net.sf.log4jdbc.DriverSpy" );
				// 운영
				hikariConfig.setJdbcUrl( "jdbc:log4jdbc:oracle:thin:@70.11.11.80:1521/KAIS" );
				hikariConfig.setUsername( "AM_MATERIAL_PUB" );
				hikariConfig.setPassword( "am3480pub@!" );
				// 로컬
//				hikariConfig.setJdbcUrl( "jdbc:log4jdbc:oracle:thin:@13.124.157.214:1521:orcl" );
//				hikariConfig.setUsername( "kccemc" );
//				hikariConfig.setPassword( "kccemc" );
			
				hikariConfig.setMinimumIdle( 32 );
				hikariConfig.setMaximumPoolSize( 32 );
				// 쿼리가 /* ping */ 으로 시작하면 MYSQL JDBC 드라이버는 이 쿼리를 실제로 날리지 않고, MySQL 서버에 살아있는지 여부만 체크하는 통신을 수행한다
//				hikariConfig.setConnectionTestQuery( "/* ping */ SELECT 1" );
				hikariConfig.setPoolName( "springHikariCP" );
				hikariConfig.setValidationTimeout( SECONDS.toMillis(10) );
				hikariConfig.setConnectionTimeout( SECONDS.toMillis(60) );


				hikariConfig.addDataSourceProperty( "dataSource.cachePrepStmts", "true" );
				hikariConfig.addDataSourceProperty( "dataSource.prepStmtCacheSize", "250" );
				hikariConfig.addDataSourceProperty( "dataSource.prepStmtCacheSqlLimit", "2048" );
				hikariConfig.addDataSourceProperty( "dataSource.useServerPrepStmts", "true" );


				HikariDataSource dataSource = new HikariDataSource( hikariConfig );
				
				try {
						System.out.println( "DB 접속 URL : " +  dataSource.getConnection().getMetaData().getURL() );
						System.out.println( "DB 접속 유저 명 : " +  dataSource.getUsername() );
				}
				catch ( SQLException e ) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				
				return dataSource;
		}
}
