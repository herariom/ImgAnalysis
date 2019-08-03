package imganalysis;

public class ImgAnalysis {

	public int[][] run(int[][] firstImage, int[][] secondImage, int tolerance, int cuts, double similarity) {
		// Define entry points for the program to check. It will start its search for
		// identifiable objects at these points.

		if (firstImage.length != firstImage[0].length)
			throw new IllegalArgumentException("Image has to be square");
		if (secondImage.length != secondImage[0].length)
			throw new IllegalArgumentException("Image has to be square");

		if (cuts <= 1)
			throw new IllegalArgumentException("Cannot make less than 2 cuts");
		if (cuts > firstImage.length || cuts > secondImage.length)
			throw new IllegalArgumentException("Cannot make more cuts than pixels");
		int[] points = new int[cuts - 1];

		for (int p = 0; p < points.length; p++) {
			points[p] = (firstImage.length / cuts) + (((firstImage.length / cuts)) * p) - 1;
		}
		Match search = new Match(tolerance);

		int[][] secondImageCopy = new int[secondImage.length][];

		for (int i = 0; i < secondImage.length; i++) {
			secondImageCopy[i] = secondImage[i].clone();
		}

		return search.getMatches(firstImage, secondImageCopy, points, similarity);
	}
}
