package Population;

//Noa Fadida, 209507680|| Adi Godosi , 316413780//
import Location.*;
import Virus.*;

import Country.Settlement;



public class Convalescent extends Person{
	
	private final static double contagionProbability = 0.2;
	
//Attributes 

	/**
	* virus field of Convalescent 
	*/
	private IVirus virus;
		
		
//Methods 
		
	/**
	* a default constructor- no parameters
	*/
	public Convalescent(){
		super();
		this.virus =  new ChineseVariant () ;//default
	}
		

		
	/**
	* Constructor
	* @param age   - age of the Person object (int)
	* @param location -location of the person object (Point)
	* @param settlement - settlement of the person object (Settlement)
    * @param virus - the virus in which the recovered person was infected (IVirus)
    */
	public Convalescent(int age, Point location, Settlement settlement,IVirus virus) {
		super(age, location,settlement);
		this.virus= virus;
	}
		
		
		
	/**
	* method that return the virus- field of the Convalescent 
	* @return IVirus
	*/
	public IVirus getVirus(){return virus;}
		

		
	/**
	* method that return the contagion probability of recovered person
	* @return double 
	*/
	public double contagionProbability(){
		return contagionProbability;
	}
	
	
	
	/**
	 * Overloaded method that return a string of the Convalescent object's details
	 * @return String
	 */
	public String toString() {
		return "Convalescent " + super.toString() + " , virus - " + virus ;
	}	
	
}