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
	
	
	/**
	 * Increments count of feature in category. Feature observed for category
	 * @param feature
	 * @param category
	 */
	public void featureOccurredInCategory(F feature, C category){
		//get feature occurrences for category
		Dictionary<F, Integer> features = featureCountPerCategory.get(category);
		
		if(features == null){
			featureCountPerCategory.put(category, new Hashtable<F, Integer>());
			features = featureCountPerCategory.get(category);
		}
		
		//get feature count
		Integer count = features.get(feature);
		
		if(count == null){
			features.put(feature,0);
			count = features.get(feature);
		}
		
		//increment count of feature in category
		features.put(feature, ++count);
		
		//how many times does feature occur overall?
		Integer totalFeatureCount = featureOccurrence.get(feature);
		
		if(totalFeatureCount == null){
			featureOccurrence.put(feature, 0);
			totalFeatureCount = featureOccurrence.get(feature);
		}
		
		//increment overall feature count
		featureOccurrence.put(feature, ++totalFeatureCount);
	}
	
	/**
	 * Increments count of category. Category observed
	 * @param category
	 */
	public void categoryOccurred(C category){
		//get category count
		Integer categoryCount = categoryOccurrence.get(category);
		
		if(categoryCount == null){
			categoryOccurrence.put(category, 0);
			categoryCount = categoryOccurrence.get(category);
		}
		
		//increment count of category
		categoryOccurrence.put(category, ++categoryCount);
	}
}
