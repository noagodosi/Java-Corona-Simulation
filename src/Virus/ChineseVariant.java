package Virus;

import Population.*;
import Simulation.Clock;
import Location.*;
import java.lang.Math; // importing java.lang package


//Noa Fadida, 209507680|| Adi Godosi , 316413780//
public class ChineseVariant implements IVirus{
	
// Attributes
	
	/**
	 * chanceOfInfection field of Chine�seVariant 
	 */
	private double chanceOfInfection = 0;
	
	
	/**
	 * chanceToDie field of Chine�seVariant 
	 */
	private double chanceToDie = 0;
	
	
	
// Final Static
	
	/**
	 * X1, X2,X3  - values of contagion Probability according to the age of person 
	 */
	private final static double X1 = 0.2;
	private final static double X2 = 0.5;
	private final static double X3 = 0.7;
	
	
	/**
	 * X4 X5 X6 - values of Probability to dying according to the age of person 
	 */
	private final static double X4 = 0.001;
	private final static double X5 = 0.05;
	private final static double X6 = 0.1;
	
	
// Methods 
	
	/**
	* Constructor 
	* @param p (Person)
	*/
	public ChineseVariant(Person p){
		if(p.getAge()>=0 && p.getAge()<18) {
			chanceOfInfection = X1;
			chanceToDie = X4;
		}
		if(p.getAge()>=18 && p.getAge()<=55) {
			chanceOfInfection = X2;
			chanceToDie = X5;
		}
		if (p.getAge()>55){
			chanceOfInfection = X3;
			chanceToDie = X6;
		}
	}
	
	
	/**
	* * a default constructor- no parameters
	*/
	public ChineseVariant(){
		chanceOfInfection = X1;
		chanceToDie = X4;
	}


	/**
	 * method that return the chanceToDie field of the Chine�seVariant
	 * @return double (chanceToDie)
	 */
	public double getChanceToDie() {return chanceToDie;}
	
	
	/**
	*A method that calculates the probability that the transferred person will be infected 
	*@param p -p (Person)
	*@return double value of contagion Probability
	*/
	public double contagionProbability(Person p) {
		ChineseVariant c = new ChineseVariant(p);
		double contagionOfPerson = p.contagionProbability(); //probability of a person getting infected  
		return c.chanceOfInfection * contagionOfPerson;
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
	**A boolean method that calculates the probability that the person being transferred will die from the disease, according to which returns whether the person was killed or not.
	*
	*@param s -s (Sick)
	*@return boolean value
	*/
	public boolean tryToKill(Sick s){
		ChineseVariant c = new ChineseVariant(s);
		long t=  Clock.now()-s.getContagiousTime() ;// t is the number of the days the person is sick
		double p = c.chanceToDie;
		double res = Math.max(0,(p - 0.01*p * Math.pow((t - 15),2)));
		double random = Math.random();
		return random < res;
	}
	
	
	
	/**
	 * Overloaded method that return a string of the ChineseVariant object's details
	 * 
	 * @return String
	 */
	public String toString() {
		return "Virus: ChineseVariant";
	}	

}
