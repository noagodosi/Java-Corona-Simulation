package Country;

import Location.Location;
import Location.Point;
import Population.Healthy;
import Population.Person;
import Population.Sick;
import Population.Vaccinated;
import Simulation.Clock;
import UI.PanelDrawing;
import UI.StatisticsFrame;
import UI.mainFrame;
import Virus.IVirus;
import Virus.VirusManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.logging.Logger;

import static java.util.Arrays.asList;


//Noa Fadida, 209507680|| Adi Godosi , 316413780//
public abstract class Settlement extends Observable implements Runnable {

	static Logger LOGGER ;

// Attributes 
	/**
	* name field of Settlement (name of the settlement) 
    */
	private final String name;
			
			
	/**
	* location field of Settlement 
	*/
	private final Location location;
			
			
	/**
	* people field of Settlement -array list of healthy people
	*/
	private volatile List<Person> people;

	/**
	 * sickPeople field of Settlement - array list of sick people in the settlement
	 */
	private volatile List<Person> sickPeople;


	/**
	* ramzorColor field of Settlement 
	*/
	protected RamzorColor ramzorColor;


	/**
	 * maxPeople field of Settlement
	 */
	private final int maxPeople;


	/**
	 * numberOfVaccines field of Settlement
	 */
	private int numberOfVaccinesDoses;


	/**
	 * connectedSettlements field of Settlement - array of settlements that connected to current settlement
	 */
	private Settlement[] connectedSettlements = null;


	private Map map;

	public volatile  Boolean flag=false;

	public volatile  Boolean play=true;

	private mainFrame menu;

	public volatile int countDie=0;

	private volatile List<Person> allPeople;

	public volatile JSlider slider;


// Methods 
	
	/**
	*  default constructor - no parameters
	*/
	public Settlement(){
		this.name= "Ashdod";
		this.location = new Location();
		this.people.add(new Healthy());
		this.sickPeople=null;
		this.ramzorColor = RamzorColor.GREEN;
		this.maxPeople = 200;
		this.numberOfVaccinesDoses=0;
	}
	
	
	/**
	 * Constructor
	 * @param name (String)
	 * @param location (Location)
	 * @param people (List<Person)
	 * @param ramzorColor (RamzorColor)
	 * @param  maxPeople (int)
	 *
	*/
	public Settlement(String name,Location location,List<Person> people,RamzorColor ramzorColor,int maxPeople){
		this.name= name;
		this.location = new Location(location.getPosition(),location.getSize());
		this.people = new ArrayList<>();
		this.sickPeople= new ArrayList<>();
		this.allPeople=new ArrayList<>();
		for (Person person : people){
			if (person instanceof Sick){
				this.sickPeople.add(person);
			} else this.people.add(person);
		}
		this.ramzorColor = ramzorColor;
		this.maxPeople = maxPeople;
		this.numberOfVaccinesDoses= 0;
		this.allPeople.addAll(people);
		this.allPeople.addAll(sickPeople);
	}

	public static Logger getLOGGER(){return Settlement.LOGGER;}

	public static void setLOGGER(Logger LOGGER) {
		Settlement.LOGGER = LOGGER;
	}


	/**
	 * method that return the name of the settlement 
	 * @return String
	 */
	public String getName() {return name;}



	/**
	 * method that return the location field of the settlement
	 * @return Settlement
	 */
	public Location getLocation() {return location;}

	

	/**
	 * method that return the people field of the settlement
	 * @return List<Person>
	 */
	public List<Person> getPeople(){return people;}


	/**
	 * method that return the sickPeople field of the settlement
	 * @return List<Person>
	 */
	public List<Person> getSickPeople(){return sickPeople;}


	/**
	 * method that return the ramzorColor field of the settlement
	 * @return RamzorColor
	 */
	public RamzorColor getRamzorColor() {return ramzorColor;}

	/**
	 * method that return the maximum number of people in the settlement
	 * @return maxPeople
	 */
	public int getMaxPeople() {return maxPeople;}


	/**
	 * method that return the number of vaccine doses in the settlement
	 * @return numberOfVaccines
	 */
	public int getNumberOfVaccinesDoses() {return numberOfVaccinesDoses;}

	public Settlement[] getConnectedSettlement(){return connectedSettlements;}

	public Map getMap()
	{return map;}


	public void setMenu(mainFrame menu){
		this.menu=menu;
	}
	public void setMap(Map map) {
		this.map = map;
	}


	/**
	 * method that StatisticTable the ramzorColor field of the settlement
	 */
	public void setRamzorColor(double C) {
		if(C>=0 && C<=1) {
			this.ramzorColor= RamzorColor.getRamzorColor(C);
		}
	}

	public void setNumberOfVaccinesDoses(int number){
		if (number>=0){
			this.numberOfVaccinesDoses=number;
		}
	}


	public void setConnectedSettlements(Settlement s) {
		if (this.connectedSettlements == null) {
			this.connectedSettlements = new Settlement[1];
			this.connectedSettlements[0] = s;
		}
		List<Settlement> connectedList = new ArrayList<>(asList(this.connectedSettlements));
		connectedList.add(s);
		this.connectedSettlements=connectedList.toArray(connectedSettlements);
	}


	/**
	 * method that return the contagiousPercent of the settlement
	 * @return double
	 */
	public double contagiousPercent() {
		double countOfAllPeople= people.size()+sickPeople.size();
		double numOfSick = sickPeople.size();
		return numOfSick/countOfAllPeople;
	}
	
	

	/**
	 * abstract method that return the ramzor Color of the settlement
	 * @return RamzorColor
	 */
	public abstract RamzorColor calculateRamzorGrade();
	
	
	
	/**
	 * method that return the random location of the settlement
	 * @return Point
	 */
	public Point randomLocation() {
		//calculate the range of the settlement 
		int xMin = location.getPosition().getX();
		int xMax= xMin + location.getSize().getWidth();
		int yMax = location.getPosition().getY();
		int yMin = yMax - location.getSize().getHeight();
		// random point in the range of the settlement
		Random rand = new Random();
		int pX =rand.nextInt(xMax - xMin) + xMin;
		int pY =rand.nextInt(yMax- yMin) +yMin;
		return new Point(pX,pY);
	}
	
	
	
	/**
	 * method that add person to current settlement
	 * @param p (Person)
	 */
	public void addPerson(Person p) {
		if (p instanceof Sick) {
			sickPeople.add(p);
		} else {
			people.add(p);
		}
		allPeople.add(p);
	}


	/**
	 * method that return People array list
	 * @return String
	 */
	public String getPersonList() {
		if(people.size() == 0){
			return "No one healthy!" +"\n";
		}
		System.out.println("\n"+ "//  List of healthy people : //");
		// ArrayList to Array 
		Person[] peopleArray = new Person[people.size()];
		peopleArray = people.toArray(peopleArray);
		for (Person person : peopleArray) {
			System.out.println("{" + person.toString() + "}");
		}
		return "\n";
	}


	/**
	 * method that return People  array list
	 * @return String
	 */
	public String getSickList() {
		if(sickPeople.size() == 0){
			return "No one sick!" +"\n";
		}
		System.out.println("\n" +"//  List of sick people : //");
		// ArrayList to Array
		Person[] sickArray = new Sick[sickPeople.size()];
		sickArray = sickPeople.toArray(sickArray);
		for (Person person : sickArray) {
			System.out.println("{" + person.toString() + "}");
		}
		return "\n";
	}

	/**
	 * method that return Settlement array of ConnectedSettlements
	 * @return Settlement[]
	 */
	public Settlement[] getConnectedSettlements() { return connectedSettlements; }

	
	
	/**
	 * method that transfers the person from the current locality to the transferred locality
	 * @param p (Person)
	 * @param s (Settlement)
	 * @return boolean
	 */
	public boolean transferPerson(Person p , Settlement s) {
		if (((s.getPeople().size() + s.getSickPeople().size())) >= s.getMaxPeople() || s.getName().equals(p.getSettlement().getName())) {
			return false;
		}

		double x = RamzorColor.getTransition(s.getRamzorColor()) * RamzorColor.getTransition(p.getSettlement().getRamzorColor());
		double random = Math.random();
		if (random < x) {
			return false;
		}

		if (p instanceof Sick) {
			s.addPerson(p);
			p.getSettlement().getSickPeople().remove(p);
			return true;

		} else {
			s.addPerson(p);
			p.getSettlement().getPeople().remove(p);
			return true;
		}
	}

	
	
	/**
	 * Overloaded method that return a string of the settlement object's details
	 * @return String
	 */
	@Override
	public String toString() {
		System.out.println("NAME: //" + this.name + "//" + "\n" + this.location +"\n" + "RAMZOR COLOR: || " + this.ramzorColor +  " ||" +"\n\n" + "	--DETAILS OF PEOPLE--	");
		return this.getPersonList()+this.getSickList();
	}

	@Override
	public void run() {
		while (play) {
			while (!flag) {
				synchronized (this) {
					//System.out.println(Thread.currentThread().getId());
					int numOfSick = (int) (0.2 * this.getSickPeople().size());
					for (int x = 1; x <= numOfSick; x++) {
						Random rand = new Random();
						for (int j = 1; j < 4; j++) {
							int numberOfSick = this.getSickPeople().size();
							int randomIndexOfSick = rand.nextInt(numberOfSick);
							if (this.getPeople().size() > 0) {
								int randomIndexToSick = rand.nextInt(this.getPeople().size());
								IVirus currentVirus = (((Sick) this.getSickPeople().get(randomIndexOfSick)).getVirus());
								boolean ans = currentVirus.tryToContagion(this.getSickPeople().get(randomIndexOfSick), this.getPeople().get(randomIndexToSick));
								if (ans) {
									Person current = this.getPeople().remove(randomIndexToSick);
									IVirus virus=VirusManager.getRandomDevelopmentOf(currentVirus);
									if(virus!=null){
										this.addPerson(current.contagion(VirusManager.getRandomDevelopmentOf(currentVirus)));
									}
								}
							}
						}
					}
					for (int j = 0; j < this.getSickPeople().size(); j++) {
						if ((Clock.numberOfDaysPassed(((Sick) this.getSickPeople().get(j)).getContagiousTime())) > 25) {
							Sick healthy = (Sick) this.getSickPeople().remove(j);
							healthy.recover();
							this.getPeople().add(healthy);
							this.setRamzorColor(this.contagiousPercent());
						}
					}


					int peopleToRandomSettlement = (int) (0.03 * (this.getPeople().size() + this.getSickPeople().size()));
					Random rand = new Random();
					if (peopleToRandomSettlement > 0) {
						this.allPeople = new ArrayList<>(this.getPeople());
						this.allPeople.addAll(this.getSickPeople());
						for (int p = 0; p < peopleToRandomSettlement; p++) {
							int randX = rand.nextInt(this.allPeople.size());
							if (this.getConnectedSettlements() != null) {
								int randSett = rand.nextInt(this.getConnectedSettlements().length);
								if(allPeople.get(randX)!=null){
									boolean ans= transferPerson(allPeople.get(randX), this.connectedSettlements[randSett]);
									if(ans){
										this.allPeople.remove(allPeople.get(randX));
									}
								}
							}
						}
					}

					if (this.getPeople().size() > 0 && this.getNumberOfVaccinesDoses() > 0) {
						for (int b = 0; b < this.getNumberOfVaccinesDoses(); b++) {
							Random random = new Random();
							int randomPerson = random.nextInt(this.getPeople().size());
							Person healthy = this.getPeople().get(randomPerson);
							if (healthy instanceof Healthy) {
								Vaccinated vaccinated = (Vaccinated) ((Healthy) healthy).vaccinate();
								this.getPeople().remove(healthy);
								this.getPeople().add(vaccinated);
								this.setNumberOfVaccinesDoses(this.getNumberOfVaccinesDoses() - 1);
							}
						}
					}

					for(int i=0; i<this.getSickPeople().size();i++){
						Sick currentSick= (Sick) this.getSickPeople().get(i);
						boolean ans= currentSick.getVirus().tryToKill(currentSick);
						if(ans){
							this.getSickPeople().remove((Person) currentSick);
							this.countDie=this.countDie+1;
						}
					}
					if(this.countDie>=0.01*(this.getSickPeople().size()+this.getPeople().size())){
						String logMessage=  LocalDateTime.now() +"\n"+
								this.getName()+"\nThe number of sick "+this.getSickPeople().size()+
								"\nThe number of death: " +this.countDie;
						LOGGER.info(logMessage);
					}
					try {
						if (map.getCyclicBarrier().getParties() - 1 == map.getCyclicBarrier().getNumberWaiting()) {
							Thread.sleep(slider.getValue()*20L);
							Clock.nextTick();
							System.out.println("Simulation Speed: " +slider.getValue()*20L + " millis seconds");
							System.out.println("Value of the clock: " +Clock.now());
						}
						//System.out.println("wait: " + map.getCyclicBarrier().getNumberWaiting());
						this.getMap().getCyclicBarrier().await();
					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}

					this.map.update(this, this);
					notifyObservers();
				}
			}
		}
	}
}









