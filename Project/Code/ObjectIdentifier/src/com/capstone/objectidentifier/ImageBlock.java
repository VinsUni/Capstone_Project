package com.capstone.objectidentifier;

import java.util.ArrayList;
import java.util.List;

public class ImageBlock {
	private List<double[]> pixelRGBs;
	
	public ImageBlock(){
		pixelRGBs = new ArrayList<double[]>();
	}
	
	public void addRGB(double[] rgb){
		pixelRGBs.add(rgb);
	}
	
	public double[] getAverageRGB(){
		double[] sumRGB = new double[3];
		for(double[] pixel: pixelRGBs){
			sumRGB[0] += pixel[0];
			sumRGB[1] += pixel[1];
			sumRGB[2] += pixel[2];
		}
		sumRGB[0] /= pixelRGBs.size();
		sumRGB[1] /= pixelRGBs.size();
		sumRGB[2] /= pixelRGBs.size();
		
		return sumRGB;
	}
	
	public int getNumPixels(){
		return pixelRGBs.size();
	}
}
