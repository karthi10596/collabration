package com.person.config;

import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@ComponentScan("com.person")
public class ApplicationContextConfig {
public static Logger logger = Logger.getLogger("ApplicationContextConfig");
		
	@Bean(name = "myDataSource")
	public DataSource getDataSource() {
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("system");
		dataSource.setPassword("admin");
		System.out.println("oracle database connection");
		return dataSource;
	}
	
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean  getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionBuilder = new LocalSessionFactoryBean();
		sessionBuilder.setDataSource(getDataSource());
		sessionBuilder.setHibernateProperties(getHibernateProperties());
		sessionBuilder.setPackagesToScan(new String[] {"com.person.entity"});
		return sessionBuilder;
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
}