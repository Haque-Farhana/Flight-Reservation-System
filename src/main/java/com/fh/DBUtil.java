package com.fh;

import java.sql.Connection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DBUtil {
	@Bean
	@Scope("prototype")
	public static Connection getConnection() throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext("dbProperties.xml");
		DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("dataSource");
		Connection con = dataSource.getConnection();
		return con;
	}

}
