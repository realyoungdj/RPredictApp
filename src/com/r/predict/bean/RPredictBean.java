package com.r.predict.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import com.google.gson.Gson;

@ManagedBean(name="rPredictBean")
public class RPredictBean {
	private RConnection rc;
	private List<CarBean> carData;
	private double carWeight;
	private String inputCarWeightString;
	private String showCarWeightString;
	private String predictResult;
	
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
		this.carData = new ArrayList<>();
		this.inputCarWeightString = "Input Car Weight: ";
		this.showCarWeightString = "";
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
	
	public void setShowCarWeightString(String showCarWeightString) {
		this.showCarWeightString = showCarWeightString;
	}
	
	public String getShowCarWeightString() {
		return this.showCarWeightString;
	}
	
	public void setPredictResult(String predictResult) {
		this.predictResult = predictResult;
	}
	
	public String getPredictResult() {
		try {
			rc.eval("new.df <- data.frame(Weight=c(1.54, 2.33, 1.70))");
			REXP x = rc.eval("predict(model, new.df)");
			if (x.isVector())
			{
				double tmp[] = x.asDoubles();
				for (int i = 0; i < tmp.length; i++) {
					String c = "car" + i;
					this.carData.add(new CarBean(c, tmp[i]));
				}
				
			}
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String rt = new Gson().toJson(this.carData);
		return rt;
	}
	
	public String deleteAll() {
		this.carData.clear();
		this.inputCarWeightString = "Input Car Weight: ";
		this.showCarWeightString = "";
		return null;
	}
	
	public String addCarWeight() {
		this.inputCarWeightString += this.getCarWeight() + ", ";
		if (this.showCarWeightString.equals(""))
			this.showCarWeightString += this.getCarWeight();
		else
			this.showCarWeightString += "," + this.getCarWeight();
		
		return null;
	}


	

}
