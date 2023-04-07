
package com.remarkablesoft.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.remarkablesoft.framework.common.constants.PaymentMethodType;
import com.remarkablesoft.framework.common.constants.PointRuleType;
import com.remarkablesoft.framework.common.constants.PointType;
import com.remarkablesoft.framework.common.constants.PostingActionType;
import com.remarkablesoft.framework.common.constants.StatusType;
import com.remarkablesoft.framework.common.constants.TargetType;
import com.remarkablesoft.framework.common.constants.TempType;
import com.remarkablesoft.framework.common.mybatis.RemarkableSqlSessionTemplate;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 		@주시스템			:	Heylaw
 * 		@서브 시스템		:	config
 * 		@프로그램 ID		:	MariaConfig.java
 * 		@프로그램 개요 		:   MyBatis에서 오라클에서 기본 Null을 허용하기 위해 jdbcTypeForNull을 Null 로 셋팅해주어야한다.
 * 							https://stackoverflow.com/questions/32081252/mybatis-jdbctypefornull-oracle
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 1. 12.	:	james	-	신규생성
 * 		1.1  2020. 8. 02.	:	james	-	PaymentMethodType, MoneyTypeHandler 추가
 * 		============================================================================
 */

@EnableTransactionManagement
@Profile( "local")
public class DBConfigTest {

		@Autowired
		ApplicationContext applicationContext;

		@Autowired
		@Qualifier ( "dataSource")
		DataSource dataSource;

//		@Autowired
//		@Qualifier ( "targetDataSource")
//		DataSource targetDataSource;

		/**
		 * Tx manager.
		 *
		 * @return the platform transaction manager
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		@Bean
		@Primary
		public PlatformTransactionManager transactionManager() throws IOException {

				DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
				transactionManager.setDataSource( dataSource );
				return transactionManager;
		}

		/*@Bean
		public PlatformTransactionManager otherTransactionManager() throws IOException {

			DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
			transactionManager.setDataSource( targetDataSource);
			return transactionManager;
		}*/

		/**
		 *
		 * Xml에서 쓰는 아래 항목을 Java로 변경
		 <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		 	<property	name="dataSource" ref="dataSource" />
		 	<property name="mapperLocations"	value="classpath:com/...../*SQL.xml"/>
		 </bean>
		 *
		 * @return
		 * @throws Exception
		 */
		@Bean ( name="sqlSessionFactory")
		public SqlSessionFactory sqlSessionFactory() throws Exception {
				SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

			sessionFactory.setDataSource(dataSource);
//			sessionFactory.setMapperLocations( applicationContext.getResources( "classpath*:com/remarkablesoft/**/mysql/*SQL.xml" ) );
			sessionFactory.setMapperLocations( applicationContext.getResources( "classpath*:com/remarkablesoft/**/oracle/*SQL.xml" ) );

				sessionFactory.setTypeHandlers( new TypeHandler[] {
        				new TempType.TypeHandler(),
        				new StatusType.TypeHandler(),
        				new PaymentMethodType.TypeHandler(),
        				new PostingActionType.TypeHandler(),
						new PointType.TypeHandler(),
						new PointRuleType.TypeHandler(),
        				new com.remarkablesoft.framework.common.mybatis.MoneyTypeHandler(),
        				new com.remarkablesoft.framework.common.mybatis.PointTypeHandler()
        		} );

				SqlSessionFactory sqlSessionFactory = sessionFactory.getObject();
				sqlSessionFactory.getConfiguration().setJdbcTypeForNull( JdbcType.NULL );

				return sqlSessionFactory;
		}

//		@Bean ( name="targetSqlSessionFactory")
//		public SqlSessionFactory targetSqlSessionFactory() throws Exception {
//				SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//
//				sessionFactory.setDataSource( targetDataSource );
////				sessionFactory.setMapperLocations( applicationContext.getResources( "classpath*:com/remarkablesoft/**/oracle/*SQL.xml" ) );
//				sessionFactory.setMapperLocations( applicationContext.getResources( "classpath*:com/remarkablesoft/**/mysql/*SQL.xml" ) );
//
//				sessionFactory.setTypeHandlers( new TypeHandler[] {
//					new TempType.TypeHandler(),
//					new StatusType.TypeHandler(),
//					new PaymentMethodType.TypeHandler(),
//					new PostingActionType.TypeHandler(),
//					new PointType.TypeHandler(),
//					new PointRuleType.TypeHandler(),
//					new TargetType.TypeHandler(),
//					new com.remarkablesoft.framework.common.mybatis.MoneyTypeHandler(),
//					new com.remarkablesoft.framework.common.mybatis.PointTypeHandler()
//				} );
//
//
//				SqlSessionFactory sqlSessionFactory = sessionFactory.getObject();
//				sqlSessionFactory.getConfiguration().setJdbcTypeForNull( JdbcType.NULL );
//
//				return sqlSessionFactory;
//		}


		/**
		 *
		 *  Xml에서 쓰는 아래 항목을 Java로 변경
		 *  		<bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
		 *  				<constructor-arg index="0"	ref="sqlSessionFactory"/>
		 *  		</bean>
		 *
		 * @param sqlSessionFactory
		 * @return
		 */
		@Bean ( name="baseSqlSession")
		public RemarkableSqlSessionTemplate baseSqlSession( SqlSessionFactory sqlSessionFactory ) {

			return new RemarkableSqlSessionTemplate( sqlSessionFactory );
		}


//		@Bean ( name="targetSqlSession")
//		public RemarkableSqlSessionTemplate targetSqlSession( SqlSessionFactory targetSqlSessionFactory ) {
//
//			return new RemarkableSqlSessionTemplate( targetSqlSessionFactory );
//		}

//		@Bean( destroyMethod = "close" )
//		public DataSource targetDataSource() throws IOException {
//
//				HikariConfig hikariConfig = new HikariConfig();
//				hikariConfig.setDriverClassName( "net.sf.log4jdbc.DriverSpy" );
//
//				hikariConfig.setJdbcUrl( "jdbc:oracle:thin:@192.168.0.117:1521:orcl" );
//
//				hikariConfig.setUsername( "kccemc" );
//				hikariConfig.setPassword( "kccemc" );
//
//				hikariConfig.setMaximumPoolSize( 5 );
//				hikariConfig.setConnectionTestQuery( "SELECT 1" );
//				hikariConfig.setPoolName( "springHikariCP" );
//
//				hikariConfig.addDataSourceProperty( "dataSource.cachePrepStmts", "true" );
//				hikariConfig.addDataSourceProperty( "dataSource.prepStmtCacheSize", "250" );
//				hikariConfig.addDataSourceProperty( "dataSource.prepStmtCacheSqlLimit", "2048" );
//				hikariConfig.addDataSourceProperty( "dataSource.useServerPrepStmts", "true" );
//
//				HikariDataSource dataSource = new HikariDataSource( hikariConfig );
//
//				return dataSource;
//		}


}
