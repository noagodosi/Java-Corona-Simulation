package Population;

import Location.*;
import Simulation.Clock;

import java.lang.Math; // importing java.lang package

import Country.Settlement;


//Noa Fadida, 209507680|| Adi Godosi , 316413780//
public class Vaccinated extends Person{
	
// Attributes 

	/**
	* vaccinationTime field of Vaccinated
	*/
	private long vaccinationTime;
		
		

// Methods 

	/**
    * a default constructor- no parameters
	*/
	public Vaccinated(){
		super();
		this.vaccinationTime = Clock.now();
	}


	
	/**
	* Constructor
	* 
	* @param age   - age of the Person object (int)
	* @param location -location of the person object (Point)
	* @param settlement - settlement of the person object (Settlement)
	* @param vaccinationTime -indicates the time the person was vaccinated(long)
	*/
	public Vaccinated(int age, Point location, Settlement settlement,long vaccinationTime) {
	super(age, location,settlement);
	this.vaccinationTime= vaccinationTime;
	}

		
	/**
	* method that return the contagion probability of vaccinated person
	* @return double 
	*/
	public double contagionProbability(){
		long t = Clock.now() - this.vaccinationTime;
		if (t<21) {
			return Math.min(1, 0.56+0.15*(Math.sqrt(21-t)));
		}
		return Math.max(0.05, (1.05/t-14));
	}
	
	
	/**
	 * Overloaded method that return a string of the Vaccinated object's details
	 * @return String
	 */
	public String toString() {
		return "Vaccinated " + super.toString() + " , vaccinationTime - " + vaccinationTime ;
	}	
	
}