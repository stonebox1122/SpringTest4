package com.stone.spring.generic.di;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Test {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-generic-di.xml");
		UserService userService = (UserService) ctx.getBean("userService");
		userService.add();
	}
}