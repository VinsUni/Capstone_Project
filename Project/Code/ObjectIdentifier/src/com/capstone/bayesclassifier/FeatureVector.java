package com.capstone.bayesclassifier;

public class FeatureVector {
	private double R;
	private double G;
	private double B;
	
	public FeatureVector(double[] rgb){
		this.R = rgb[0];
		this.G = rgb[1];
		this.B = rgb[2];
	}
	
	public double getR(){
		return R;
	}
	
	public double getG(){
		return G;
	}
	
	public double getB(){
		return B;
	}
}
