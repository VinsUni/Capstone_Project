package com.capstone.bayesclassifier;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Set;

public class NaiveBayesClassifier<F, C> {
	//map features to num occurrences in each category
	private Dictionary<C, Dictionary<F, Integer>> featureCountPerCategory;
	
	//map features to num total occurrences
	private Dictionary<F, Integer> featureOccurrence;
	
	//map categories to num total occurrences
	private Dictionary<C, Integer> categoryOccurrence;
	
	public NaiveBayesClassifier(){
		this.featureCountPerCategory = new Hashtable<C, Dictionary<F, Integer>>();
		this.featureOccurrence = new Hashtable<F, Integer>();
		this.categoryOccurrence = new Hashtable<C, Integer>();
	}
	
	public Set<F> getFeatures(){
		return ((Hashtable<F, Integer>)this.featureOccurrence).keySet();
	}
	
	public Set<C> getCategories(){
		return ((Hashtable<C, Integer>) this.categoryOccurrence).keySet();
	}
	
	public int getNumCategories(){
		return categoryOccurrence.size();
	}
	
	
}
