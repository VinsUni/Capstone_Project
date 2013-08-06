package com.capstone.bayesclassifier;

/**
 * Interface defining method to calculate probability of a feature
 * @author jdaniel
 *
 * @param <F> feature
 * @param <C> category
 */
public interface IFeatureProbability<F,C> {
	public double featureProbability(F feature, C category);
}
