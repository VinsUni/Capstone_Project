package com.capstone.bayesclassifier;

public class Classification<F, C> {
	
	private F[][] features;
	private C category;
	private double probability;
	
	public Classification(F[][] features, C category){
		this.features = features;
		this.category = category;
		probability = 1.0;
	}
	
	public Classification(F[][] features, C category, double probability){
		this.features = features;
		this.category = category;
		this.probability = probability;
	}
	
	public F[][] getFeatures(){
		return features;
	}
	
	public C getCategory(){
		return category;
	}
	
	public double getProbability(){
		return probability;
	}
}
