import java.awt.Color;
import java.awt.image.BufferedImage;


public class FeatureVector {
	private Color[][] pixels;
	private double percentPixelsSameColor;
	private int numEdgePixels;
	
	public FeatureVector(BufferedImage img){
		percentPixelsSameColor = 0.0;
		numEdgePixels = 0;
		
		pixels = new Color[8][8];
		
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				pixels[x][y] = new Color(img.getRGB(x,y));
			}
		}
		
		findPercentPixelsNotWhite();
		findNumEdgePixels();
	}
	
	private void findPercentPixelsNotWhite(){
		int totalWhitePixels = 0;
		
		for(int x = 0; x < pixels.length; x++){
			for(int y = 0; y < pixels[x].length; y++){				
				if(isPixelWhite(pixels[x][y]))
					totalWhitePixels++;
			}
		}
		
		percentPixelsSameColor = (64-totalWhitePixels)/64.0;
	}
	
	private void findNumEdgePixels(){
		for(int x = 0; x < pixels.length; x++){
			for(int y = 0; y < pixels[x].length; y++){
				if(!isPixelWhite(pixels[x][y])){
					if(isEdgePixel(x,y))
						numEdgePixels++;
				}
			}
		}
	}
	
	private boolean isPixelWhite(Color pixel){
		return (pixel.getRed() == 255 && pixel.getGreen() == 255 && pixel.getBlue() == 255);
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
		
		if(x == 0 || y == 0 || x == 7 || y == 7)
			isEdge = true;
		else if(x > 0 && isPixelWhite(pixels[x-1][y]))
			isEdge = true;
		else if(x < 7 && isPixelWhite(pixels[x+1][y]))
			isEdge = true;
		else if(y > 0 && isPixelWhite(pixels[x][y-1]))
			isEdge = true;
		else if(y < 7 && isPixelWhite(pixels[x][y+1]))
			isEdge = true;
		return isEdge;
		
	}
}
