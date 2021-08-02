package Virus;

import Population.Person;
import Population.Sick;
import Simulation.Clock;
import Location.Point;
import java.lang.Math; // importing java.lang package

//Noa Fadida, 209507680|| Adi Godosi , 316413780//
public class SouthAfricanVariant implements IVirus{
	
// Attributes 	
	/**
	* chanceOfInfection field of SouthAfricanVariant 
	*/
	private double chanceOfInfection = 0;
		
		
	/**
	* chanceToDie field of SouthAfricanVariant 
	*/
	private double chanceToDie = 0;
		
		
		
// Final Static
	
	/**
	 * A1,A2 - values of contagion Probability according to the age of person 
	 */
	final static double A1 = 0.6;
	final static double A2 = 0.5;
	
	/**
	 * A3,A4 - values of Probability to dying according to the age of person 
	 */
	final static double A3 = 0.05;
	final static double A4 = 0.08;
	
	
// Methods
	
	/**
	* Constructor 
	* @param p (Person)
	*/
	public SouthAfricanVariant(Person p){
	  if(p.getAge()>=0 && p.getAge()<18) {
		  chanceOfInfection = A1;
		  chanceToDie = A3;
	  }
	  if(p.getAge()>=18) {
		  chanceOfInfection = A2;
		  chanceToDie = A4;
	  }
	}
		
		
	/**
	* a default constructor- no parameters
	*/
	public SouthAfricanVariant(){
		chanceOfInfection = A1;
		chanceToDie = A3;
	}


	/**
	 * method that return the chanceToDie field of the SouthAfricanVariant
	 * @return chanceToDie
	 */
	public double getChanceToDie() {return chanceToDie;}
	
	
	/**
	*A method that calculates the probability that the transferred person will be infected 
	*@param p -p (Person)
	*@return double value of contagion Probability
	*/
	public double contagionProbability(Person p) {
		SouthAfricanVariant a = new SouthAfricanVariant(p);
		double contagionOfPerson = p.contagionProbability(); //probability of a person getting infected  
		double res = a.chanceOfInfection * contagionOfPerson;
		return res;	
	}
	
	
	
	/**
	*A boolean method that return true if the second person is healthy(the first is certainly sick!) 
	*@param p1 -first person (person)
	*@param p2 - second person(person)
	*@return boolean value 
	*/
	public boolean tryToContagion(Person p1, Person p2) {
		if(p2 instanceof Sick)
			return false;
		long p1Days = Clock.numberOfDaysPassed(((Sick)p1).getContagiousTime());
		if(p1Days<1){return false;}
		double d = Point.calcDistance(p1.getLocation(), p2.getLocation());// d - the distance between tow people
		double x = Math.min(1,(0.14* (Math.pow(Math.E, (2-0.25*d)))));
		double res = x*contagionProbability(p2);
		double random = Math.random();
		return random < res;
	}
	
	
	/**
	*A boolean method that calculates the probability that the person being transferred will die from the disease, according to which returns whether the person was killed or not.
	*@param s -s (Sick)
	*@return boolean value
	*/
	public boolean tryToKill(Sick s){
		SouthAfricanVariant a = new SouthAfricanVariant(s);
		long t=  Clock.now()-s.getContagiousTime() ;// t is the number of the days the person is sick
		double p = a.chanceToDie;
		double res = Math.max(0,(p - 0.01*p * Math.pow((t - 15),2)));
		double random = Math.random();
		return random < res;
	}
	
	
	/**
	 * Overloaded method that return a string of the SouthAfricanVariant object's details
	 * @return String
	 */
	public String toString() {
		return "Virus: SouthAfricanVariant";
	}	

}