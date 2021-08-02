package Location;

//Noa Fadida, 209507680|| Adi Godosi , 316413780//
public class Size {
	
	/**
	 * width field of the size
	 */
	private final int width;
	
	
	/**
	 * height field of the size
	 */
	private int height;
	
	/**
	 * Constructor
	 * @param width field of the size
	 * @param height field of the size
	 */
	public Size(int width, int height) {
		this.width = width;
		this.height = height;
	}

	
	/**
	 * default constructor
	 */
	public Size() {
		this.width = 0;
		this.height = 0;
	}


	/**
//	 * boolean set method that change the height, true if successes, false if not
	 * @param  height field of the size
	 * @return boolean value
	 */
	public boolean setHeight(int height) {
		if (width >= 0) {
			this.height= height;
			System.out.println("the value of height has changed");
			return true;
		} else {
			System.out.println("the value is invalid");
			return false;
		}
	}
	
	
	/**
	 * method that return the width field of the Size object
	 * @return width
	 */
	public int getWidth() {return width;}
	
	
	/**
	 * method that return the height field of the Size object
	 * @return height
	 */
	public int getHeight(){return height;}
	
	
	/**
	 * Overloaded method that return a string of the Size object's details
	 * @return String
	 */
	public String toString() {
		return "width = " + width + ",height = " + height ;
	}	
	
	
	/**
	 * Overloaded boolean method. return true if the sizes are equals, false if not
	 * @param other size
	 * @return boolean value
	 */
	public boolean equals(Size other) {
		return (width == other.width) && (height == other.height);
	}
}
