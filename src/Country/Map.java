package Country;



import UI.*;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

//Noa Fadida, 209507680|| Adi Godosi , 316413780//


public class Map implements Observer {
	// Attributes 


	/**
	 * settlements field of the Map
	 */
	private final List<Thread> threads;

	private final CyclicBarrier cyclicBarrier;

	private ArrayIterator iterator;





	// Methods
	
	/**
	* Constructor
	 * @param settlements (array of settlements)
	 */
	public Map(Settlement[] settlements){
		synchronized (this) {
			iterator = new ArrayIterator(settlements, settlements.length);
			this.threads = new ArrayList<Thread>();
			while (iterator.hasNext()) {
				Settlement settlement = (Settlement) iterator.next();
				settlement.setMap(this);
				threads.add(new Thread(settlement));
			}
		/*for(int i=0; i<settlements.length; i ++) {
			this.settlements[i]=settlements[i];
			threads.add(new Thread(settlements[i]));
		}*/
			this.cyclicBarrier = new CyclicBarrier(threads.size());
		/*for (Settlement settlement : settlements) {
			settlement.setMap(this);
		}*/
		}

	}

	/*
	* a method that return settlements field of Map
	* @return Settlement[] 
	*/
	public Settlement[] getSettlement(){return (Settlement[]) this.iterator.getArr();}


	public List<Thread> getThreads() {
		return threads;
	}


	public CyclicBarrier getCyclicBarrier() {
		return cyclicBarrier;
	}


	public void stop(){
		iterator = new ArrayIterator(getSettlement(), getSettlement().length);
		while (iterator.hasNext()) {
			Settlement settlement = (Settlement) iterator.next();
			settlement.flag = true;
			settlement.play = false;
		}
	}

	public void pause(){
		iterator = new ArrayIterator(getSettlement(), getSettlement().length);
		while (iterator.hasNext()) {
			Settlement settlement = (Settlement) iterator.next();
			settlement.flag = true;
		}
	}

	public void play(){
		iterator = new ArrayIterator(getSettlement(), getSettlement().length);
		while (iterator.hasNext()) {
			Settlement settlement = (Settlement) iterator.next();
			settlement.flag=false;
		}

		/*for (Thread thread:threads){
			System.out.println(thread.getState());
		}*/
	}

	
	
	/**
	 * Overloaded method that return a string of the Map object's details
	 * @return String
	 */
	public String toString() {
		iterator=new ArrayIterator(getSettlement(), getSettlement().length);
		System.out.println("\n" + "			 |The Map|				" );
		System.out.println("_____________________________________________________________________" + "\n");
		while (iterator.hasNext()){
			Settlement settlement=(Settlement)iterator.next();
			System.out.println(settlement.toString() + "_____________________________________________________________________" + "\n" + "\n");
		}
		return "\n";

	}

	@Override
	public void update(Observable o, Object arg) {
		iterator=new ArrayIterator(getSettlement(), getSettlement().length);
		while(iterator.hasNext()){
			Settlement settlement=(Settlement) iterator.next();
			settlement.setRamzorColor(settlement.contagiousPercent());
			mainFrame.getInstance().add(new PanelDrawing(mainFrame.getInstance()), BorderLayout.CENTER);
			mainFrame.getInstance().statisticsFrame = new StatisticsFrame(mainFrame.getInstance());
			mainFrame.getInstance().mapDrawing.repaint();
			mainFrame.getInstance().repaint();
		}
	}
}
