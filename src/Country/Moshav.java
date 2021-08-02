package Country;

import java.util.List;
import Location.*;
import Population.*;
import java.lang.Math; // importing java.lang package

//Noa Fadida, 209507680|| Adi Godosi , 316413780//
public class Moshav extends Settlement{
	
// Attributes 
		
	/**
	 * C  field of Moshav - the current value of the color of the settlement
	 */
	private double C;


// Methods 

	/**
	*  default constructor - no parameters
	*/
	public Moshav(){
		super();
		this.C=0.4;
	}


	/**
	 * Constructor
	 * @param name (String)
	 * @param location (Location)
	 * @param people (List<Person>)
	 * @param ramzorColor (RamzorColor)
	 * @param maxPeople (int)
	 */
	public Moshav(String name,Location location,List<Person> people,RamzorColor ramzorColor,int maxPeople){
		super(name,location,people ,ramzorColor,maxPeople);
		this.C=0.4;
	}


	/**
	 * method that return the ramzorColor of the settlement
	 * @return RamzorColor
	 */
	@Override
	public RamzorColor calculateRamzorGrade() {
		double P= this.contagiousPercent();
		this.C= (0.3 + 3*Math.pow(Math.pow(1.2, this.C)*(P-0.35),5));//to StatisticTable C
		this.setRamzorColor(C);//to StatisticTable ramzorColor
		return this.ramzorColor;
	}

	/**
	 * Overloaded method that return a string of the Moshav object's details
	 * @return String
	 */
	public String toString() {
		System.out.println("Settlement Type: |MOSHAV|");
		return super.toString();
	}	
}
