package com.capstone.bayesclassifier;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Set;

public class NaiveBayesClassifier<F, C> implements IFeatureProbability<F,C>{
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
	
	/**
	 * Returns num occurrences of feature in category
	 * @param feature
	 * @param category
	 * @return
	 */
	public int featureOccurrenceInCategory(F feature, C category){
		//get feature occurrences for a category
		Dictionary<F, Integer> features = featureCountPerCategory.get(category);
		int numFeatureOccurrences;
		
		if(features == null)
			numFeatureOccurrences = 0;
		else numFeatureOccurrences = features.get(feature).intValue();
		
		return numFeatureOccurrences;
	}
	
	/**
	 * Returns num occurrences of category
	 * @param category
	 * @return
	 */
	public int categoryOccurrence(C category){
		Integer numCategoryOccurrence = categoryOccurrence.get(category);
		return (numCategoryOccurrence == null) ? 0 : numCategoryOccurrence.intValue();
	}
	
	/**
	 * return probability of category given feature
	 * @param feature
	 * @param category
	 * @return
	 */
	public double featureProbability(F feature, C category){
		double probabilityCategoryGivenFeature;
		
		if(categoryOccurrence(category) == 0)
			probabilityCategoryGivenFeature = 0;
		else probabilityCategoryGivenFeature = 
				(double)featureOccurrenceInCategory(feature, category)/(double)categoryOccurrence(category);
		
		return probabilityCategoryGivenFeature;
	}
	
	/**
	 * Calculates probability of feature given category with weight 1 and 
	 * assumed probability .5
	 * @param feature
	 * @param category
	 * @return
	 */
	public double featureWeightedAverage(F feature, C category){
		return featureWeightedAverage(feature, category, null, 1.0, .5);
	}
	
	/**
	 * Calculates probability of feature given category with weight 1
	 * and assumed probability .5 and uses calculator for probability calculation
	 * @param feature
	 * @param category
	 * @param calculator
	 * @return
	 */
	public double featureWeightedAverage(F feature, C category, IFeatureProbability<F,C> calculator){
		return featureWeightedAverage(feature,category,calculator,1.0,.5);
	}
	
	/**
	 * Calculates probability of feature given category with given weight
	 * and assumed probability .5 and uses calculator for probability calculation
	 * @param feature
	 * @param category
	 * @param calculator
	 * @param weight
	 * @return
	 */
	public double featureWeightedAverage(F feature, C category, IFeatureProbability<F,C> calculator, double weight){
		return featureWeightedAverage(feature, category, calculator, weight, .5);
	}
	
	/**
	 * Calculates probability of feature given category with given weight, given
	 * assumed probability and uses calculator for probability calculation
	 * @param feature
	 * @param category
	 * @param calculator
	 * @param weight
	 * @param assumedProbability
	 * @return
	 */
	public double featureWeightedAverage(F feature, C category, IFeatureProbability<F,C> calculator, double weight, double assumedProbability){
		//uses given calculator or default method to calculate probability that given feature occurred in given category
		double basicProbability = (calculator==null) ? this.featureProbability(feature,category)
				: calculator.featureProbability(feature, category);
		
		Integer totals = featureOccurrence.get(feature);
		
		if(totals == null)
			totals = 0;
		return (weight * assumedProbability + totals * basicProbability)/(weight + totals);
	}
}
