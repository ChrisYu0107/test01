package com.zjpii.zjkeweb.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zjpii.zjkfweb.service.UserService;




public class MyTest {

	protected static Log logger = LogFactory.getLog(MyTest.class);
	
	
	private static ApplicationContext ctx = null ;
	
	@BeforeClass
	public static void iniAC(){
		ctx = new ClassPathXmlApplicationContext("spring-base.xml");
	}
	
	@Autowired
	private UserService userService;
	
	@Test
	public void test1() throws SQLException {
		DataSource ds = (DataSource) ctx.getBean("dataSource");
		System.out.println(ds.getConnection());
		UserService u =  (UserService) ctx.getBean("userService");
		u.getUser();
	}
}
