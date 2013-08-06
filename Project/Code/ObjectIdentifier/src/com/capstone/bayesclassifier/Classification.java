package com.capstone.bayesclassifier;

public class Classification<F, C> {
	
	private F featureVector;
	private C category;
	private double probability;
	
	public Classification(F features, C category){
		this.featureVector = features;
		this.category = category;
		probability = 1.0;
	}
	
	public Classification(F features, C category, double probability){
		this.featureVector = features;
		this.category = category;
		this.probability = probability;
	}
	
	public F getFeatureVector(){
		return featureVector;
	}
	
	public C getCategory(){
		return category;
	}
	
	public double getProbability(){
		return probability;
	}
}
