package com.r.predict.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

@ManagedBean(name="rPredictBean")
public class RPredictBean {
	private RConnection rc;
	private List<Double> carWeightList;
	private double carWeight;
	private String inputCarWeightString;
	
	public RPredictBean() {
		this.createRConnection();
		this.initRData();
		this.initData();
	}
	
	private void createRConnection() {
		try {
			rc = new RConnection();
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void initRData() {
		String rdata = "df <- read.table(text = '\n" + 
				"     Weight	MPG\n" + 
				"1     1.27	30\n" + 
				"2     1.50	27\n" + 
				"3     2.12	20\n" + 
				"4     1.34	26\n" + 
				"5     1.10	35\n" + 
				"6     2.45	19\n" + 
				"7     1.56	22\n" + 
				"8     0.98	38\n" + 
				"9     1.26	30\n" + 
				"10    1.39	30\n" + 
				"11    2.11	21\n" + 
				"12    1.46	28\n" + 
				"13    1.98	22\n" + 
				"14    2.40	19\n" + 
				"15    2.45	17\n" + 
				"16    1.75	24\n" + 
				"17    2.21	19\n" + 
				"18    1.05	34\n" + 
				"19    0.95	36\n" + 
				"20    1.84	23\n" + 
				"21    1.73	23', header=TRUE)";
		
		try {
			rc.eval(rdata);
			rc.eval("str(df)");
			rc.eval("model <- lm(MPG ~ Weight, data=df)");
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	private void initData() {
		
	}
	
	
	
	public void setCarWeight(double carWeight) {
		this.carWeight = carWeight;
		
	}
	
	public double getCarWeight() {
		return this.carWeight;
	}
	
	public void setInputCarWeightString(String inputCarWeightString) {
		this.inputCarWeightString = inputCarWeightString;
	}
	
	public String getInputCarWeightString() {
		return this.inputCarWeightString;
	}
	
	
	public String predict() {
		return null;
	}
	
	public String deleteAll() {
		return null;
	}
	
	public String addCarWeight() {
		return null;
	}
	

}
