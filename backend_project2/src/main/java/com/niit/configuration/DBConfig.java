package com.niit.configuration;

import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.Friend;
import com.niit.model.Job;
import com.niit.model.ProfilePicture;
import com.niit.model.Users;



@Configuration
@EnableTransactionManagement
public class DBConfig {
	//create an instance
	@Bean
	public SessionFactory sessionFactory() {
	LocalSessionFactoryBuilder lsf=
	new LocalSessionFactoryBuilder(getDataSource());
	Properties hibernateProperties=new Properties();
	hibernateProperties.setProperty(
	"hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
	hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
	hibernateProperties.setProperty("hibernate.show_sql", "true");
	lsf.addProperties(hibernateProperties);
	Class classes[]=new Class[]{Users.class,Job.class,BlogPost.class,BlogComment.class,Friend.class,ProfilePicture.class};
	return lsf.addAnnotatedClasses(classes).buildSessionFactory();
	}
	@Bean
	public DataSource getDataSource() {
	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
	dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE"); //see the doc and change the url if it is different
	dataSource.setUsername("NAFISA"); //change the value if it is different
	dataSource.setPassword("sa"); //change the password if it is different
	return dataSource;
	}
	@Bean
	public HibernateTransactionManager hibTransManagement(){
	return new HibernateTransactionManager(sessionFactory());
	}
}
