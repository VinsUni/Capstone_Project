import java.awt.Color;
import java.awt.image.BufferedImage;


public class BoundingBox {

	private int leftBoundary, rightBoundary, topBoundary, bottomBoundary;
	private double percentWhitePixelsUpperRight, percentWhitePixelsUpperLeft, percentWhitePixelsLowerRight, percentWhitePixelsLowerLeft, percentWhitePixelsTotal;
	private BufferedImage img;
	
	public BoundingBox(BufferedImage img){
		this.img = img;
		leftBoundary = img.getWidth();
		rightBoundary = 0;
		topBoundary = img.getHeight();
		bottomBoundary = 0;
		
		for(int i = 0; i < img.getWidth(); i++){
			for(int j = 0; j < img.getHeight(); j++){
				if(!isPixelWhite(new Color(img.getRGB(i, j)))){
					if(i < leftBoundary)
						leftBoundary = i;
					else if(i > rightBoundary)
						rightBoundary = i;
					if(j < topBoundary)
						topBoundary = j;
					else if(j > bottomBoundary)
						bottomBoundary = j;
				}
			}
		}
		
		calculatePercentWhitePixels();
	}
	
	private void calculatePercentWhitePixels(){
		//calculate percent white pixels in upper left
		int numWhitePixels = 0;
		int totalPixels = 0;
		int runningTotalWhitePixels = 0;
		int runningTotalPixels = 0;
		
		for(int i = leftBoundary; i < leftBoundary+getWidth()/2; i++){
			for(int j = topBoundary; j < topBoundary+getHeight()/2; j++){
				totalPixels++;
				if(isPixelWhite(new Color(img.getRGB(i,j))))
					numWhitePixels++;
			}
		}
		runningTotalWhitePixels += numWhitePixels;
		runningTotalPixels += totalPixels;
		percentWhitePixelsUpperRight = (1.0*numWhitePixels/totalPixels);
		
		//calculate percent white pixels in upper right
		numWhitePixels = 0;
		totalPixels = 0;
		for(int i = leftBoundary+getWidth()/2; i <= rightBoundary; i++){
			for(int j = topBoundary; j < topBoundary+getHeight()/2; j++){
				totalPixels++;
				if(isPixelWhite(new Color(img.getRGB(i,j))))
					numWhitePixels++;
			}
		}
		runningTotalWhitePixels += numWhitePixels;
		runningTotalPixels += totalPixels;
		percentWhitePixelsUpperLeft = (1.0*numWhitePixels/totalPixels);
		
		//calculate percent white pixels in lower left
		numWhitePixels = 0;
		totalPixels = 0;
		for(int i = leftBoundary; i < leftBoundary+getWidth()/2; i++){
			for(int j = topBoundary+getHeight()/2; j <= bottomBoundary; j++){
				totalPixels++;
				if(isPixelWhite(new Color(img.getRGB(i,j))))
					numWhitePixels++;
			}
		}
		runningTotalWhitePixels += numWhitePixels;
		runningTotalPixels += totalPixels;
		percentWhitePixelsLowerLeft = (1.0*numWhitePixels/totalPixels);
		
		//calculate percent white pixels in lower right
		numWhitePixels = 0;
		totalPixels = 0;
		for(int i = leftBoundary+getWidth()/2; i <= rightBoundary; i++){
			for(int j = topBoundary+getWidth()/2; j <= bottomBoundary; j++){
				totalPixels++;
				if(isPixelWhite(new Color(img.getRGB(i,j))))
					numWhitePixels++;
			}
		}
		runningTotalWhitePixels += numWhitePixels;
		runningTotalPixels += totalPixels;
		percentWhitePixelsLowerRight = (1.0*numWhitePixels/totalPixels);
		
		//calculate total percent white pixels
		percentWhitePixelsTotal = (1.0*runningTotalWhitePixels/runningTotalPixels);
	}
	
	public double getPercentWhitePixelsTotal(){
		return percentWhitePixelsTotal;
	}
	
	public double getPercentWhitePixelsUpperLeft(){
		return percentWhitePixelsUpperLeft;
	}
	
	public double getPercentWhitePixelsUpperRight(){
		return percentWhitePixelsUpperRight;
	}
	
	public double getPercentWhitePixelsLowerLeft(){
		return percentWhitePixelsLowerLeft;
	}
	
	public double getPercentWhitePixelsLowerRight(){
		return percentWhitePixelsLowerRight;
	}
	
	private boolean isPixelWhite(Color pixel){
		return (pixel.getRed() >= 210 && pixel.getGreen() >= 210 && pixel.getBlue() >= 210);
	}
	
	public int getTopBoundary(){
		return topBoundary;
	}
	
	public int getBottomBoundary(){
		return bottomBoundary;
	}
	
	public int getLeftBoundary(){
		return leftBoundary;
	}
	
	public int getRightBoundary(){
		return rightBoundary;
	}
	
	public int getHeight(){
		return (bottomBoundary - topBoundary);
	}
	
	public int getWidth(){
		return (rightBoundary - leftBoundary);
	}
}
