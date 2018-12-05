package com.stone.spring.cycle;

public class Car {
	private String brand;
	public Car() {
		System.out.println("Car's Constructor...");
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		System.out.println("setBrand...");
		this.brand = brand;
	}
	public void init() {
		System.out.println("init...");
	}
	public void destroy() {
		System.out.println("destroy...");
	}
}
