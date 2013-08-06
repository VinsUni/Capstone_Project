package com.capstone.objectidentifier;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;

public class ImageBlocks {
	private List<ImageBlock> blocks;
	
	public ImageBlocks(Mat img){
		blocks = new ArrayList<ImageBlock>();
		int currentRow = 0;
		int currentCol = 0;
		while(currentRow < img.rows()){//img.rows is 256
			while(currentCol < img.cols()){//img.cols is 384
				ImageBlock block = null;
				for(int row = currentRow; row < currentRow+4; row++){
					for(int col = currentCol; col < currentCol+4; col++){
						block = new ImageBlock();
						block.addRGB(img.get(row,col));
					}
				}
				blocks.add(block);
				currentCol+=4;
			}
			currentRow+=4;
			currentCol = 0;
		}
	}
	
	public int getNumBlocks(){
		return blocks.size();//there should be 96*64=6144 blocks
	}
}
