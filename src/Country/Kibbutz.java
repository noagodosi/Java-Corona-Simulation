package Country;

import Location.Location;
import Population.Person;
import java.util.List;

//Noa Fadida, 209507680|| Adi Godosi , 316413780//
public class Kibbutz extends Settlement {
	
// Attributes
	/**
	 * C  field of Kibbutz - the current value of the color of the settlement
	 */
	private double C;


	// Methods 

	/**
	*  default constructor - no parameters
	*/
	public Kibbutz(){
		super();
		this.C = 0.4;
	}


	/**
	 * Constructor
	 * @param name (String)
	 * @param location (Location)
	 * @param people (List<Person>)
	 * @param ramzorColor (RamzorColor)
	 * @param maxPeople (int)
	 */
	public Kibbutz(String name,Location location,List<Person> people,RamzorColor ramzorColor,int maxPeople){
		super(name,location,people ,ramzorColor,maxPeople);
		this.C = 0.4;
	}


	/**
	 * method that return the ramzorColorr of the settlement
	 * @return RamzorColor
	 */
	@Override
	public RamzorColor calculateRamzorGrade() {
		double P = this.contagiousPercent();
		this.C= (0.45 + Math.pow((Math.pow(1.5, this.C)*(P-0.4)),3));// to StatisticTable C
		this.setRamzorColor(C);// to StatisticTable ramzorColor
		return this.ramzorColor;
	}

	/**
	 * Overloaded method that return a string of the Kibbutz object's details
	 * @return String
	 */
	public String toString() {
		System.out.println("Settlement Type: |KIBBUTZ|");
		return super.toString();
	}	
}
