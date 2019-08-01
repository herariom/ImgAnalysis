package test;

import imganalysis.ImgAnalysis;
import imganalysis.graphics.GUI;
import imganalysis.image.LoadImage;

public class Test {
	public static void main(String[] args) {
	  
	    int[][] img1 = LoadImage.loadImage("res/test-image.png");
	    int[][] img2 = LoadImage.loadImage("res/test-image-modified.png");
	    
	    new GUI(img1, img2, (new ImgAnalysis().run(img1, img2, 15, 32, 0.7)));
	}
	
	
}
