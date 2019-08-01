package imganalysis;

import java.util.ArrayList;

public class Matrix {
    private int submatrixCode = 1000;
    
    private int tolerance;
    
    public Matrix(int tolerance) {
    	this.tolerance = tolerance;
    }
    
    public int[][] styleMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
        	for (int l = 0; l < matrix[0].length; l++) {
        		if (matrix[i][l] < 1000) {
        			matrix[i][l] = 0;
        		}
        	}
        }
		for (int i = 0; i < matrix.length; i++) {
        	for (int l = 0; l < matrix[0].length; l++) {
        		
        			matrix[i][l] = matrix[i][l] / 1000;
        	}
        }
		
		return matrix;
    }
    
    public int[][] findSubmatrix(int[][] matrix, int[][] submatrix) {
    	if (submatrix.length <= 1) return matrix;
    	int[][] newmatrix = matrix;
    	int mistakes = 0;
        for (int x1 = 0; x1 < matrix.length - submatrix.length + 1; ++x1)
        	loopY: for (int y1 = 0; y1 < matrix[0].length - submatrix[0].length + 1; ++y1)
        	{
        		for (int xx = 0; xx < submatrix.length; ++xx)
        			for (int yy = 0; yy < submatrix[0].length; ++yy)
        			{
        				if (!MathHelper.checkTolerance(matrix[x1 + xx][y1 + yy],submatrix[xx][yy], tolerance) && submatrix[xx][yy] != 0)
        					// 0 is whitespace
        				{
        					mistakes++;
        					continue loopY;
        				}
        			}
        		submatrixCode += 1000;
        		for (int xx = 0; xx < submatrix.length; ++xx) {
        			for (int yy = 0; yy < submatrix[0].length; ++yy)
        			{
        				if (submatrix[xx][yy] != 0) {
        					newmatrix[x1 + xx][y1 + yy] = submatrixCode;
        				}
        			}
        		}
        		
        		if (mistakes > (submatrix.length * submatrix[0].length)) {
        			return matrix;
        		}
        		return newmatrix;
        	}
        return matrix;
    }
    
    public int[][] subMatrix(ArrayList<Point> sub) {
    	int largestX = 0;
        int largestY = 0;
        
        int smallestX = 999;
        int smallestY = 999;
        // Figure out how big our object array needs to be
        for (Point e : sub) {
        	if (e.x > largestX) largestX = e.x;
        	if (e.y > largestY) largestY = e.y;
        	if (e.x < smallestX) smallestX = e.x;
        	if (e.y < smallestY) smallestY = e.y;
        }
        smallestX--;
        smallestY--;
        // Remove excessive whitespace from array
        int y = largestY - smallestY;
        int x = largestX - smallestX;
        
        int[][] object = new int[y][x];
        for (Point e : sub) {
        	object[e.y - 1 - smallestY][e.x - 1 - smallestX] = e.value;
        }
        
    	
    	return object;
    }
}
