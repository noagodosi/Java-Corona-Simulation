package Location;

//Noa Fadida, 209507680|| Adi Godosi , 316413780//
public class Location {

	
	/**
	 * position field of the location
	 */
	private final Point position;
	
	
	/**
	 * size field of the location
	 */
	private final Size size;
	
	
	/**
	 * Constructor call the Constructor of Point and call the Constructor of Size
	 * @param position field of the location
	 * @param size field of the location
	 */
	public Location(Point position, Size size) {
		this.position =  new Point(position.getX(),position.getY());
		this.size = new Size(size.getWidth(), size.getHeight());
	}

	
	/**
	 * default constructor
	 */
	public Location() {
		this.position =  new Point();
		this.size = new Size();
	}


	/**
	 * method that return the position field of the Location object
	 * @return position
	 */
	public Point getPosition() {return position;}
	
	
	/**
	 * method that return the size field of the Location object
	 * @return height
	 */
	public Size getSize(){return size;}
	
	
	/**
	 * Overloaded method that return a string of the Location object's details
	 * 
	 * @return String
	 */
	public String toString() {
		return "LOCATION: {Position:" + position + "} , {Size: " + size +"}" ;
	}

}
