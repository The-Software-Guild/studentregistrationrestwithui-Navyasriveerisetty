package com.wileyedge.student;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@SpringBootApplication
@ComponentScan(basePackages = "com.wileyedge.student")
@PropertySource("classpath:mysql.properties")
public class StudentRegistrationWebApplApplication 
{

	@Autowired
	private Environment env;

	public static void main(String[] args) 
	{
		SpringApplication.run(StudentRegistrationWebApplApplication.class, args);
	}

	@Bean
	@ConditionalOnMissingBean
	public DataSource dataSource() 
	{
		System.out.println("Inside dataSource method");

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3307/StudentRegistration?createDatabaseIfNotExist=true");
		dataSource.setUsername("root");
		dataSource.setPassword("root");

		return dataSource;
	}

	@Bean(name = "entityManagerFactory")
	@ConditionalOnBean(name = "dataSource")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() 
	{
		System.out.println("Inside EntityManagerFactory method");
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("com.wileyedge.*");

		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		em.setJpaProperties(additionalProperties());
		return em;
	}

	public Properties additionalProperties() 
	{
		System.out.println("Inside additionalProperties method");

		Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("mysql-hibernate.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("mysql-hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("mysql-hibernate.show_sql"));

		return hibernateProperties;
	}
}
