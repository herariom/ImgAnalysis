package imganalysis;

import java.util.ArrayList;

public class Match {
	int tolerance = 0;
	int targetValue = 40;
	int buffer = 0;

	private int size;

	public ArrayList<Point> temp = new ArrayList<>();

	public Match(int tolerance) {
		this.tolerance = tolerance;
	}

	public int[][] getMatches(int[][] image1, int[][] image2, int[] entryPoints, double similarity) {
		int col = image1.length;
		int row = image1[0].length;

		Matrix max = new Matrix(tolerance);

		DFSArray sf = new DFSArray(row, col, tolerance);

		ArrayList<ArrayList<Point>> w = sf.getMap(image1, entryPoints);

		int[][][] maps = new int[w.size()][][];

		// Check for similarity so that we don't match duplicates

		for (int i = 0; i < w.size(); i++) {
			ArrayList<Point> e = w.get(i);
			double f = MathHelper.checkSimilarity(w.get(i), w, i);
			if (f > similarity) {
				maps[i] = new int[0][];
				w.get(i).clear();
				continue;
			}
			maps[i] = max.subMatrix(e);
		}

		for (int i = 0; i < maps.length; i++) {
			if (maps[i].length <= 1)
				continue;
			image2 = max.findSubmatrix(image2, maps[i]);
		}
		// Style matrix for display
		image2 = max.styleMatrix(image2);

		return image2;
	}
}
