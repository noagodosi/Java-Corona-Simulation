package Virus;

import Population.*;

//Noa Fadida, 209507680|| Adi Godosi , 316413780//
public interface IVirus {
	
	/**
	*A method that calculates the probability that the transferred person will be infected 
	*@param p (Person)
	*@return double value contagion Probability
	*/
	double contagionProbability(Person p);
	

	/**
	*A boolean method that return true if the second person is healthy (the first is certainly sick!)
	*@param p1 (person)
	*@param p2 (person)
	*@return boolean value 
	*/
	boolean tryToContagion(Person p1, Person p2);
	
	
	/**
	*A boolean method that calculates the probability that the person being transferred will die from the disease, according to which returns whether the person was killed or not.
	*@param s  - (Sick)
	*@return boolean value 
	*/
	 boolean tryToKill(Sick s);
	
	 double getChanceToDie();
}
