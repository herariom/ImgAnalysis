package test;

import imganalysis.ImgAnalysis;
import imganalysis.graphics.GUI;
import imganalysis.image.LoadImage;

public class Test {
	public static void main(String[] args) {

		int[][] img1 = LoadImage.loadImage("res/test-image-larger.png");
		int[][] img2 = LoadImage.loadImage("res/test-image-larger-modified.png");

		new GUI(img1, img2, (new ImgAnalysis().run(img1, img2, 25, 16, 0.8)));
	}

}
