package Country;

import Location.Location;
import Population.Person;

import java.util.List;

//Noa Fadida, 209507680|| Adi Godosi , 316413780//
public class City extends Settlement {
	
// Attributes 
		
	/**
	 * C  field of City - the current value of the color of the settlement
	 */
	private double C;


// Methods 

	/**
	*  default constructor - no parameters
	*/
	public City(){
		super();
		this.C=0.4;
	}


	/**
	 * Constructor
	 * @param name (String)
	 * @param location (Location)
	 * @param  people (List<Person>)
	 * @param ramzorColor (RamzorColor)
	 * @param maxPeople (int)
	 */
	public City(String name,Location location,List<Person> people,RamzorColor ramzorColor,int maxPeople){
		super(name,location,people ,ramzorColor,maxPeople);
		this.C=0.4;
	}


	/**
	 * method that return the ramzorColorr of the settlement
	 * @return RamzorColor
	 */
	public RamzorColor calculateRamzorGrade() {
		double P = this.contagiousPercent();
		this.C= (0.2*(Math.pow(4, 1.25*P)));//to StatisticTable C
		this.setRamzorColor(this.C);//to StatisticTable ramzorColor
		return this.ramzorColor;
	}

	/**
	 * Overloaded method that return a string of the City object's details
	 * @return String
	 */
	public String toString() {
		System.out.println("Settlement Type: |CITY|");
		return super.toString();
	}

}
	