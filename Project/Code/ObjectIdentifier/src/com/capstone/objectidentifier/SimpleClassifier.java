package com.capstone.objectidentifier;

import java.util.List;

import com.capstone.bayesclassifier.FeatureVector;

public class SimpleClassifier {
	private double error;
	private List<FeatureVector> first, second;
	
	public SimpleClassifier(List<FeatureVector> first, List<FeatureVector> second){
		this.first = first;
		this.second = second;
	}
	
	public double classify(){
		for(int i = 0; i < first.size(); i++){
			FeatureVector firstRGB = first.get(i);
			FeatureVector secondRGB = second.get(i);
			double rDiff = Math.abs(firstRGB.getR()-secondRGB.getR());
			double gDiff = Math.abs(firstRGB.getG()-secondRGB.getG());
			double bDiff = Math.abs(firstRGB.getB()-secondRGB.getB());
			error+= rDiff+gDiff+bDiff;
		}
		return error;
	}
}
