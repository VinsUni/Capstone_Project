import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.junit.BeforeClass;


public class BoundingBoxTest {

	private static BoundingBox appleBox, chairBox, staplerBox;
	
	@BeforeClass
	public static void testSetup(){
		BufferedImage apple = null, chair = null, stapler = null;
		try{
			apple = ImageIO.read(new File("apple.jpg"));
			chair = ImageIO.read(new File("chair.jpg"));
			stapler = ImageIO.read(new File("stapler.jpg"));
		}
		catch(IOException e){
			System.out.println("Image file could not be read.");
		}
		
		appleBox = new BoundingBox(apple);
		chairBox = new BoundingBox(chair);
		staplerBox = new BoundingBox(stapler);
	}
	@Test
	public void testGetTopBoundary() {
		assertEquals(6,appleBox.getTopBoundary());
		assertEquals(7,chairBox.getTopBoundary());
		assertEquals(47,staplerBox.getTopBoundary());
	}

	@Test
	public void testGetBottomBoundary() {
		assertEquals(194,appleBox.getBottomBoundary());
		assertEquals(195,chairBox.getBottomBoundary());
		assertEquals(153,staplerBox.getBottomBoundary());
	}

	@Test
	public void testGetLeftBoundary() {
		assertEquals(1,appleBox.getLeftBoundary());
		assertEquals(30,chairBox.getLeftBoundary());
		assertEquals(38,staplerBox.getLeftBoundary());
	}

	@Test
	public void testGetRightBoundary() {
		assertEquals(194,appleBox.getRightBoundary());
		assertEquals(144,chairBox.getRightBoundary());
		assertEquals(179,staplerBox.getRightBoundary());
	}

	@Test
	public void testGetHeight() {
		assertEquals(188,appleBox.getHeight());
		assertEquals(188,chairBox.getHeight());
		assertEquals(106,staplerBox.getHeight());
	}

	@Test
	public void testGetWidth() {
		assertEquals(193,appleBox.getWidth());
		assertEquals(114,chairBox.getWidth());
		assertEquals(141,staplerBox.getWidth());
	}

}
