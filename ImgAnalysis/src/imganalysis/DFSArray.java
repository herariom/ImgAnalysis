package imganalysis;

import java.util.ArrayList;

public class DFSArray {

	private int targetValue;
	private int tolerance;
	private int ROW;
	private int COL;
	private int size;

	private ArrayList<ArrayList<Point>> temp;

	public DFSArray(int ROW, int COL, int tolerance) {
		this.ROW = ROW;
		this.COL = COL;
		this.tolerance = tolerance;
		temp = new ArrayList<>();
	}

	private boolean isSafe(int M[][], int row, int col, boolean visited[][]) {
		return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL)
				&& (MathHelper.checkTolerance(M[row][col], targetValue, tolerance) && !visited[row][col]);
	}

	private void DepthFirstSearch(int M[][], int row, int col, boolean visited[][]) {
		int rowNbr[] = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 };
		int colNbr[] = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };

		temp.get(temp.size() - 1).add(new Point(col + 1, row + 1, M[row][col]));

		visited[row][col] = true;
		size++;
		// Recur for all connected neighbors
		for (int k = 0; k < 8; ++k)
			if (isSafe(M, row + rowNbr[k], col + colNbr[k], visited)) {
				DepthFirstSearch(M, row + rowNbr[k], col + colNbr[k], visited);
			}
	}

	public ArrayList<ArrayList<Point>> getMap(int M[][], int[] entryPoints) {
		boolean visited[][] = new boolean[M.length][M[0].length];

		int count = 0;

		for (int i = 0; i < entryPoints.length; i++)
			for (int j = 0; j < entryPoints.length; j++)
				if (true) // Removed checking for visited because we don't want 100% unique islands.
				{
					targetValue = M[entryPoints[i]][entryPoints[j]];
					visited = new boolean[M.length][M[0].length];

					temp.add(new ArrayList<>());

					size = 0;

					DepthFirstSearch(M, entryPoints[i], entryPoints[j], visited);

					++count;
				}
		return temp;
	}

}
