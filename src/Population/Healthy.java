package Population;

//Noa Fadida, 209507680|| Adi Godosi , 316413780//
import Location.*;
import Simulation.*;
import Country.*;


public class Healthy extends Person {
	
	private final static double contagionProbability = 1;
			
	// Methods 
		
	/**
	* a default constructor- no parameters
	*/
	public Healthy(){
		super();
	}
		
		
	/**
	* Constructor
	* @param age  - age of the Person object (int)
	* @param location -location of the person object (Point)
	* @param settlement - settlement of the person object (Settlement)
    */
	public Healthy(int age, Point location, Settlement settlement) {
		super(age, location,settlement);
	}
		

		
	/**
	* method that return the contagion probability of healthy person
	* @return double 
	*/
	public double contagionProbability(){
		return contagionProbability;
	}
	
	
	/**
	* a method that returns a copy of the current person who is vaccinated
	* @return Person 
	*/
	public Person vaccinate() {
		return new Vaccinated(this.getAge(),this.getLocation(),this.getSettlement(),Clock.now());
	}
	
	
	
	/**
	 * Overloaded method that return a string of the Healthy object's details
	 * @return String
	 */
	public String toString() {
		return "Healthy " + super.toString();
	}	
}