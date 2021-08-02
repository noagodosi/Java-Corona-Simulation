package Population;

import Virus.*;
import Location.*;
import Country.*;

//Noa Fadida, 209507680|| Adi Godosi , 316413780//
public class Sick extends Person{
	
	private final static double contagionProbability=1;
	
// Attributes 

	/**
	 * contagiousTime of a sick person
	 */
	private long contagiousTime;
	
	
	/**
	 * location field of the person
	 */
	private IVirus virus;
	

	
 // Methods 
	
	/**
	 * a default constructor- no parameters
	 */
	public Sick() {
		super();
		this.contagiousTime = 1;
		this.virus = new ChineseVariant();
	}
	
	
	/**
	 * Constructor
	 * @param age   - age of the Person object (int)
	 * @param location -location of the person object (Point)
	 * @param settlement - settlement of the person object (Settlement)
	 * @param contagiousTime - the time a person(sick) is infected with a virus (long)
	 * @param virus - the virus in which the sick person is infected (IVirus)
	 */
	public Sick(int age, Point location, Settlement settlement,long contagiousTime,IVirus virus) {
		super(age, location,settlement);
		this.contagiousTime= contagiousTime;
		this.virus = virus;
	}
	
	
	/**
	 * method that return the contagiousTime- field of the Sick 
	 * @return long
	 */
	public long getContagiousTime(){return contagiousTime;}
	
	
	/**
	 * method that return the virus- field of the Sick 
	 * @return IVirus
	 */
	public IVirus getVirus(){return virus;}
	

	/**
	 * method that return the contagion probability of person who is sick, default value is 1
	 * @return double 
	 */
	public double contagionProbability(){return contagionProbability;}
	
	
	/**
	 * a method that returns a copy of the current person who is recover
	 * @return Person 
	 */
	public Person recover() {
		return new Convalescent(this.getAge(), this.getLocation(),this.getSettlement(),virus);
	}

	
	/**
	 * Overloaded method that return a string of the Sick object's details
	 * @return String
	 */
	public String toString() {
		return "Sick " + super.toString() + " //contagiousTime:" + contagiousTime + "//  //" + virus + "//" ;
	}	
}