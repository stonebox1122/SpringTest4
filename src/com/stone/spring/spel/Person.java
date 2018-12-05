package com.stone.spring.spel;

public class Person {
	
	private String name;
	//引用address bean的city属性
	private String city;
	//根据car的price确定info：price大于等于30w，金领，否则为白领
	private String info;
	private Car car;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", city=" + city + ", info=" + info + ", car=" + car + "]";
	}
}
