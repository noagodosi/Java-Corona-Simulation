package Population;

import Country.City;
import Country.Settlement;
import Location.Point;
import Virus.IVirus;

import static Simulation.Clock.now;


//Noa Fadida, 209507680|| Adi Godosi , 316413780//
/**
 * An abstract class, its a superclass for many inherits classes, and gives tools to create an Person object
 *
 */
public abstract class Person {
	
//Attributes 

	/**
	 * age field of the person
	 */
	private int age;
	
	
	/**
	 * location field of the person
	 */
	private Point location;
	
	
	/**
	 * Settlement field of the person
	 */
	private Settlement settlement;
	
	
	
 // Methods 
	
	/**
	 * a default constructor- no parameters
	 */
	public Person() {
		this.age=0;
		this.location= new Point();
		this.settlement=new City();//default
	}
	
	
	/**
	 * Constructor
	 * @param age   - age of the Person object (int)
	 * @param location -location of the person object (Point)
	 * @param s - settlement of the person object (Settlement)
	 */
	public Person(int age, Point location, Settlement s) {
		this.location= new Point(location.getX(), location.getY());
		this.settlement= s;
		this.age = Math.max(age, 0);
	}
	
	
	/**
	 * method that return the age- field of the Person object
	 * @return age
	 */
	public int getAge(){return age;}
	
	
	/**
	 * method that return the location- field of the Person object
	 * @return location
	 */
	public Point getLocation(){return location;}
	
	
	/**
	 * method that return the settlement- field of the Person object
	 * @return settlement
	 */
	public Settlement getSettlement(){return settlement;}
	
	
	/**
	 * method that return the contagion probability of person
	 * @return double 
	 */
	public abstract double contagionProbability();

	
	/**
	 * a method that returns a copy of the current person who is sick with the transmitted virus
	 * @return Person 
	 */
	public Person contagion (IVirus virus) {
		return new Sick(this.getAge(), this.getLocation(),this.getSettlement(), now(),virus);
	}
	
	
	/**
	 * Overloaded boolean method. return true if the Person are equals, false if not
	 * @param other Person
	 * @return boolean value
	 */
	public boolean equals(Person other) {
		return (age == other.getAge()) && (location == other.getLocation());
	}
	
	
	/**
	 * Overloaded method that return a string of the Person object's details
	 * @return String
	 */
	public String toString() {
		return "Person <age:" + this.age + "> |location:" + this.location + "|";
	}	 
}
