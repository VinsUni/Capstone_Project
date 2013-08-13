import java.awt.image.BufferedImage;


public class FeatureVector {
	private int[][] pixels;
	private double percentPixelsSameColor;
	private int numEdgePixels;
	
	public FeatureVector(BufferedImage img){
		pixels = new int[8][8];
		int sumPixelsSameColor = 0;
		numEdgePixels = 0;
		
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				pixels[x][y] = img.getRGB(x,y);
				if(pixels[x][y] != -1){
					sumPixelsSameColor++;
					if(isEdgePixel(x,y))
						numEdgePixels++;
				}
			}
		}
		
		percentPixelsSameColor = sumPixelsSameColor/64.0;	
	}
	
	public double getPercentPixelsSameColor(){
		return percentPixelsSameColor;
	}
	
	public int getNumEdgePixels(){
		return numEdgePixels;
	}
	
	public int compareTo(Object other){
		if((FeatureVector)other != null){
			if(this.percentPixelsSameColor == ((FeatureVector) other).getPercentPixelsSameColor()
					&& this.numEdgePixels == ((FeatureVector) other).getNumEdgePixels())
				return 0;
		}
		return -1;
	}
	
	private boolean isEdgePixel(int x, int y){
		boolean isEdge = false;
		if(x > 0 && pixels[x-1][y] == -1)
			isEdge = true;
		else if(x < 7 && pixels[x+1][y] == -1)
			isEdge = true;
		else if(y > 0 && pixels[x][y-1] == -1)
			isEdge = true;
		else if(y < 7 && pixels[x][y+1] == -1)
			isEdge = true;
		return isEdge;
		
	}
}
