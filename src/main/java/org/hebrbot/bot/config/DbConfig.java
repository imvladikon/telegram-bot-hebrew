package org.hebrbot.bot.config;

import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DbConfig {

	@Autowired
	Environment env;

	@Bean
	DataSource getDataSource() {
		return DataSourceBuilder.create()
				.driverClassName("org.postgresql.Driver")
				.url("jdbc:postgresql://localhost:5432/telegrambot?useUnicode=yes&characterEncoding=UTF-8")
				.username("postgres")
				.password("123")
				.build();
	}

	@Bean(name = "sessionFactory")
	@DependsOn("flyway")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setPackagesToScan(new String[] { "org.hebrbot.bot.model" });
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	@Bean(initMethod = "migrate")
	Flyway flyway() {
		return new Flyway(Flyway.configure()
				.validateOnMigrate(true)
				.baselineOnMigrate(true)
				.dataSource(getDataSource())
				.placeholderPrefix("##[[")
				.placeholderSuffix("]]"));
	}

	Properties getHibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.hbm2ddl.auto", "none");
				setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
				setProperty("hibernate.globally_quoted_identifiers", "true");
//				setProperty(org.hibernate.cfg.AvailableSettings.VALIDATION_MODE, "none");
//				setProperty(AvailableSettings.HBM2DDL_DATABASE_ACTION, "none");
			}
		};
	}

//	@Bean
//	Properties hibernateProperties() {
//		Properties hibernateProperties = new Properties();
//		hibernateProperties.putAll(ImmutableMap.<Object, Object> builder()
//				.put(org.hibernate.cfg.Environment.AUTOCOMMIT, "false")
//				.put(org.hibernate.cfg.Environment.DIALECT, env.getProperty("hibernate.dialect"))
//				.put(org.hibernate.cfg.Environment.QUERY_SUBSTITUTIONS, "true 'T', false 'F'")
//				.put(org.hibernate.cfg.Environment.SHOW_SQL, env.getProperty("hibernate.showSql"))
//				.put(	org.hibernate.cfg.Environment.CACHE_REGION_FACTORY,
//						org.hibernate.cache.ehcache.EhCacheRegionFactory.class.getName())
//				.put(org.hibernate.cfg.Environment.USE_QUERY_CACHE, env.getProperty("hibernate.useQueryCache"))
//				.put(	org.hibernate.cfg.Environment.USE_SECOND_LEVEL_CACHE,
//						env.getProperty("hibernate.useSecondLevelCache"))
//				.put(AvailableSettings.AUTODETECTION, "class")
//				.put(AvailableSettings.VALIDATION_MODE, "none")
//				.put(	org.hibernate.cfg.Environment.GENERATE_STATISTICS,
//						env.getProperty("hibernate.generate_statistics"))
//				.build());
//		return hibernateProperties;
//	}


	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

}
