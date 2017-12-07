package com.r.predict.bean;

public class CarBean {
	private String name;
	private double mileage;
	
	public CarBean(String name, double mileage) {
		this.name = name;
		this.mileage = mileage;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getMileage() {
		return this.mileage;
	}
	
	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

}
