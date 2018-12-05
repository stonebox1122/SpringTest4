package com.stone.spring.relation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.stone.spring.autowire.*;


public class Test {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-relation.xml");
//		Address address = (Address) ctx.getBean("address");
//		System.out.println(address);
		
		Address address = (Address) ctx.getBean("address2");
		System.out.println(address);
		
		Person person = (Person) ctx.getBean("person");
		System.out.println(person);
	}
}