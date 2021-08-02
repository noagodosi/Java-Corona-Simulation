package Location;

//Noa Fadida, 209507680|| Adi Godosi , 316413780//
public class Point {
	
	/**
	 * x field of the point
	 */
	private final int x;


	/**
	 * y field of the point
	 */
	private final int y;


	/**
	 * Constructor
	 * @param x of point
	 * @param y of point
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * default constructor
	 */
	public Point() {
		this.x = 0;
		this.y = 0;
	}



	/**
	 * method that return the x field of the Point object
	 * @return x
	 */
	public int getX() {return x;}
	
	
	/**
	 * method that return the y field of the Point object
	 * @return y
	 */
	public int getY(){return y;}
	
	
	/**
	 * Overloaded method that return a string of the Point object's details
	 * @return String
	 */
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
	
	
	/**
	 * Overloaded boolean method. return true if the points are equals, false if not
	 * @param other point
	 * @return boolean value
	 */
	public boolean equals(Point other) {
		return (x == other.getX()) && (y == other.getY());
	}
	
	
	/**
	 *  help static method that calculate the distance of 2 points
	 * @param p1 (Point)
	 * @return theDistance
	 */
	public static double calcDistance(Point p1, Point p2) {
		double theDistance;
		theDistance = Math.sqrt((p1.getY() - p2.getY()) * (p1.getY() - p2.getY())
				+ (p1.getX() - p2.getX()) * (p1.getX() - p2.getX()));
		return theDistance;
	}
}
