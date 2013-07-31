package com.capstone.objectidentifier;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceView;
import android.view.WindowManager;

public class HelloOpenCvActivity extends Activity{
	
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
		           	     Mat img = Highgui.imread("/images/red.jpg");
		        	     AlertDialog ad = new AlertDialog.Builder(HelloOpenCvActivity.this).create();
		        	     if(img == null){
		        	    	 ad.setMessage("Can't open image file!");
		        	     }
		        	     else ad.setMessage("Successfully read image file!");
	                } break;
	                default:
	                {
	                    super.onManagerConnected(status);
	                } break;
	            }
	    }
    };
}
