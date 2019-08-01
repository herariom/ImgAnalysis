package imganalysis;

import java.util.ArrayList;

public final class MathHelper {
	
	public static int tolerance = 0;
	
    // Check health of the unit map by:
    // * Overall size
    // * How irregular it is
    // * How much it acts like a border to the image
    public static double checkHealth(int[][] image, ArrayList<Point> map) {
    	int imageY = image.length;
    	int imageX = image[0].length;
    	
    	// Check size. Percentage with 1.0 being 100%
    	
    	double health = (double)map.size()/(imageX * imageY);
    	
        int largestX = 0;
        int largestY = 0;
        
        int smallestX = 999;
        int smallestY = 999;
        
        for (Point e : map) {
        	if (e.x > largestX) largestX = e.x;
        	if (e.y > largestY) largestY = e.y;
        	if (e.x < smallestX) smallestX = e.x;
        	if (e.y < smallestY) smallestY = e.y;
        }
        
    	// Add less weight to regularity.
        double regularity = (double)((largestX - smallestX) * (largestY - smallestY)) / 2;
        
        health += (double) regularity / (imageX * imageY);
        
    	// Figure out irregularity by finding area of map and compare it with area of map's largest and smallest x and y
        
        return health;
    }
	
    public static double checkSimilarity(ArrayList<Point> subMap, ArrayList<ArrayList<Point>> map, int index) {
    	double oldNumber = 0;
    	double newNumber = 0;
    	
    	for (int i = 0; i < map.size(); i++) {
    		ArrayList<Point> e = map.get(i);
    		if (e == map.get(index)) continue;
    		if (sameValues(subMap, e)) return 1;
    		newNumber = checkSimilarity(subMap, e);
    		if (newNumber == 1) return 1;
    		if (newNumber > oldNumber) oldNumber = newNumber;
    	}
    	return oldNumber;
    }
    
    private static boolean sameValues(ArrayList<Point> matrix1, ArrayList<Point> matrix2)
    {
        if(matrix1 == null && matrix2 == null)
            return true;
        if((matrix1 == null && matrix2 != null) || (matrix1 != null && matrix2 == null))
            return false;

        if(matrix1.size() != matrix2.size())
            return false;
        for(Object item: matrix1)
        {
            if(!matrix2.contains(item))
                return false;
        }

        return true;
    }
    private static double checkSimilarity(ArrayList<Point> firstMap, ArrayList<Point> secondMap) {
    	
    	ArrayList<Point> similar = new ArrayList<>();
    	for (Point p1 : firstMap) {
    		for (Point p2 : secondMap) {
    			if (p1.equals(p2)) {
    				similar.add(p1);
    			}
    		}	
    	}
    	
    	int mapSize = 0;
    	if (firstMap.size() > secondMap.size()) {
    		mapSize = firstMap.size();
    	} else {
    		mapSize = secondMap.size();
    	}
    	
    	return (double) similar.size() / mapSize;
    }
    
    // Checks if the given value is within the tolerance of the given target value
    public static boolean checkTolerance(int number, int targetValue, int tolerance) {
    	if (number == targetValue) return true;
    	
    	if (number < targetValue) {
    		return number + tolerance >= targetValue;
    	} else if (number > targetValue) {
    		return number - tolerance <= targetValue;
    	}
    	return false;
    	
    }
}