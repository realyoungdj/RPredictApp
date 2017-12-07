package com.r.predict.bean;

import java.util.List;

public class CarBean {
	private String name;
	private List<Double> data;
	
	public CarBean(String name, List<Double> data) {
		this.name = name;
		this.data = data;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Double> getData() {
		return this.data;
	}
	
	public void setMileage(List<Double> data) {
		this.data = data;
	}

}
