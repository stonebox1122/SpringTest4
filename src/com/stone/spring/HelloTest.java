package com.stone.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloTest {
	public static void main(String[] args) {
		//1、创建Spring的IoC容器
		//ApplicationContext：代表IoC容器
		//ClassPathXmlApplicationContext：是ApplicationContext接口的实现类，该实现类从类路径下加载配置文件
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		//2、从IoC容器中获取Bean实例
		//利用id定位到IoC容器中的Bean
		HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
		
		//利用列席返回IoC容器中的Bean，但要求IoC容器中只能有一个该类型的Bean
		//HelloWorld helloWorld = context.getBean(HelloWorld.class);
		
		helloWorld.getMessage();
		
		Car car = (Car) context.getBean("car");
		System.out.println(car);
		
		car = (Car) context.getBean("car2");
		System.out.println(car);
		
		Person person = (Person) context.getBean("person2");
		System.out.println(person);
	}
}