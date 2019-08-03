package imganalysis;

public class Point {
	public int x, y, value;

	public Point(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}

	@Override
	public boolean equals(Object p) {
		if (!(p instanceof Point))
			return false;
		Point f = (Point) p;
		if (f.value == this.value && f.x == this.x && f.y == this.y)
			return true;

		return false;
	}
}
