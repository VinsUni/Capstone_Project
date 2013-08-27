package com.capstone.objectidentifier;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelloOpenCvActivity extends Activity{
	
	private void edgeDetection(){
		//get color image
		Mat colored = Highgui.imread("apple.jpg",Highgui.CV_LOAD_IMAGE_COLOR);
		
		//convert image to grayscale
		Mat gray = new Mat(colored.size(), CvType.CV_8U);
		
		Imgproc.cvtColor(colored,gray,Imgproc.COLOR_BGR2GRAY);
		
		//smooth image
		Mat smooth = new Mat(colored.size(), CvType.CV_8U);
		Imgproc.GaussianBlur(gray,smooth,new Size(9,9),2,2);
		
		Imgproc.threshold(gray,gray,155,255,Imgproc.THRESH_BINARY);
		
		//Detect edges
		int N = 7;
		int aperature_size = N;
		double lowThresh = 20;
		double highThresh = 40;
		Mat edges = new Mat();
		
		Imgproc.Canny(gray,edges,lowThresh*N*N,highThresh*N*N,aperature_size,false);
		Highgui.imwrite("appleEdges.jpg",edges);
		
	}
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.hello_open_cv_layout);
	     
	     if (!OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_2, this, mOpenCVCallBack)){
	         Log.e("TEST", "Cannot connect to OpenCV Manager");
	     }
	 }
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hello_open_cv, menu);
		return true;
	}
	
	private BaseLoaderCallback  mOpenCVCallBack = new BaseLoaderCallback(this) {
	    @Override
	    public void onManagerConnected(int status) {
	        switch (status) {
	                case LoaderCallbackInterface.SUCCESS:
	                {
		           	     LinearLayout layout = (LinearLayout) findViewById(R.id.myLayout);
		        	     TextView txt = new TextView(HelloOpenCvActivity.this);
		        	     layout.addView(txt);
		        	     
		        	     txt.setText("Processing Image...");
	                	edgeDetection();
	                	txt.setText("Done");
	                } break;
	                default:
	                {
	                    super.onManagerConnected(status);
	                } break;
	            }
	    }
    };
}
