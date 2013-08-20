import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class MainClass {
	public static void main(String[] args) {
		MainClass main = new MainClass();
		main.demoFullImages();
	}
	
	public void demoFullImages(){
		BufferedImage apple = null, chair = null, stapler = null;
		try{
			apple = ImageIO.read(new File("apple.jpg"));
			chair = ImageIO.read(new File("chair.jpg"));
			stapler = ImageIO.read(new File("stapler.jpg"));
		}
		catch(IOException e){
			System.out.println("Image file could not be read.");
		}
		
		BoundingBox appleBox = new BoundingBox(apple);
		BoundingBox chairBox = new BoundingBox(chair);
		BoundingBox staplerBox = new BoundingBox(stapler);
		
		System.out.println("Apple Bounding Box Pecent WhiteSpace:");
		System.out.println("Upper Left: " + appleBox.getPercentWhitePixelsUpperLeft() + "\nUpper Right: " + appleBox.getPercentWhitePixelsUpperRight() + 
				"\nLower Left: " + appleBox.getPercentWhitePixelsLowerLeft() + "\nLower Right: "+ appleBox.getPercentWhitePixelsLowerRight()
				+"\nTotal: " + appleBox.getPercentWhitePixelsTotal());
		
		System.out.println();
		
		System.out.println("Chair Bounding Box Pecent WhiteSpace:");
		System.out.println("Upper Left: " + chairBox.getPercentWhitePixelsUpperLeft() + "\nUpper Right: " + chairBox.getPercentWhitePixelsUpperRight() + 
				"\nLower Left: " + chairBox.getPercentWhitePixelsLowerLeft() + "\nLower Right: "+ chairBox.getPercentWhitePixelsLowerRight()
				+"\nTotal: " + chairBox.getPercentWhitePixelsTotal());
		
		System.out.println();
		
		System.out.println("Stapler Bounding Box Pecent WhiteSpace:");
		System.out.println("Upper Left: " + staplerBox.getPercentWhitePixelsUpperLeft() + "\nUpper Right: " + staplerBox.getPercentWhitePixelsUpperRight() + 
				"\nLower Left: " + staplerBox.getPercentWhitePixelsLowerLeft() + "\nLower Right: "+ staplerBox.getPercentWhitePixelsLowerRight()
				+"\nTotal: " + staplerBox.getPercentWhitePixelsTotal());
	}
	
	public void demo8By8Images(){
		BufferedImage square1 = null, square2 = null, square3 = null, flag = null, line = null;
   	    try {
			square1 = ImageIO.read(new File("square1.bmp"));
			square2 = ImageIO.read(new File("square2.bmp"));
			square3 = ImageIO.read(new File("square3.bmp"));
			flag = ImageIO.read(new File("flag.bmp"));
			line = ImageIO.read(new File("line.bmp"));
			
		} catch (IOException e) {
			System.out.println("Image file could not be read.");
		}

   	    FeatureVector square1Vector = new FeatureVector(square1);
   	    FeatureVector square2Vector = new FeatureVector(square2);
   	    FeatureVector square3Vector = new FeatureVector(square3);
   	    FeatureVector flagVector = new FeatureVector(flag);
   	    FeatureVector lineVector = new FeatureVector(line);
   	    
   	    if(square1Vector.compareTo(square2Vector) == 0)
   	    	System.out.println("Square1 and Square2 are the same object.");
   	    else System.out.println("Square1 and Square2 are not the same object.");
   	    
   	    if(square1Vector.compareTo(square3Vector) == 0)
   	    	System.out.println("Square1 and Square3 are the same object.");
   	    else System.out.println("Square1 and Square3 are not the same object.");
   	    
   	    if(square1Vector.compareTo(flagVector) == 0)
	    	System.out.println("Square1 and Flag are the same object.");
	    else System.out.println("Square1 and Flag are not the same object.");
   	    
   	    if(square1Vector.compareTo(lineVector) == 0)
	    	System.out.println("Square1 and Line are the same object.");
	    else System.out.println("Square1 and Line are not the same object.");
   	    
   	    if(flagVector.compareTo(lineVector) == 0)
	    	System.out.println("Flag and Line are the same object.");
	    else System.out.println("Flag and Line are not the same object.");
	}
}
