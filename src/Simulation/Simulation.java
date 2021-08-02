package Simulation;

import Country.Map;
import Country.Settlement;
import IO.SimulationFile;
import Population.Person;
import UI.PanelDrawing;
import UI.mainFrame;
import Virus.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Random;

//Noa Fadida, 209507680|| Adi Godosi , 316413780//

public class Simulation {
	public JSlider speedSlider;
	private List<Thread> threads;
	private Settlement[] settlementArray;
	public Boolean stop = false;
	public Boolean isAlive = true;
	public Map map;
	private SimulationFile sm;
	private File file;

	public Simulation(JSlider speedSlider) {
		file = loadFileFunc();
		sm = new SimulationFile();
		assert file != null;
		map = sm.loadFileFunc(file.getPath());
		threads = map.getThreads();
		settlementArray = map.getSettlement();
		this.speedSlider = speedSlider;
		First();
	}

	public Settlement[] getSettlementArray() { return settlementArray; }

	public void First() {
		int numOfPerson;
		int numOfSick;
		Random rand = new Random();
		Random randVirus = new Random();
		IVirus[] randomVirusArr = new IVirus[3];
		BritishVariant b = new BritishVariant();
		SouthAfricanVariant s = new SouthAfricanVariant();
		ChineseVariant c = new ChineseVariant();
		randomVirusArr[0] = b;
		randomVirusArr[1] = s;
		randomVirusArr[2] = c;
		for (Settlement settlement : settlementArray) {
			numOfPerson = settlement.getPeople().size();
			numOfSick = (int) (numOfPerson * 0.1);
			for (int j = 0; j < numOfSick; j++) {
				numOfPerson = settlement.getPeople().size();
				int randomIndex = rand.nextInt(numOfPerson);
				Person current = settlement.getPeople().remove(randomIndex);
				int rVIndex = randVirus.nextInt(3);
				settlement.addPerson(current.contagion(randomVirusArr[rVIndex]));
			}
		}
	}

	public static File loadFileFunc() {
		FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.LOAD);
		fd.setVisible(true);
		if (fd.getFile() == null)
			return null;
		File f = new File(fd.getDirectory(), fd.getFile());
		return f;
	}


	public void simulationRepeat(mainFrame menu) {
		for (Settlement settlement:settlementArray){
			settlement.slider=speedSlider;
			settlement.setMenu(menu);
		}
		//while (!stop && isAlive) {
		synchronized (this) {
			for (Thread thread : threads) {
				synchronized (thread) {
					System.out.println(thread.getName());
					thread.start();
				}
			}
		}
		//}
	}

	/*public void stop(){
		for (Settlement settlement : settlementArray) {
			settlement.flag = true;
			settlement.play = false;
		}
	}

	public void pause(){
		for (Settlement settlement:settlementArray) {
			settlement.flag = true;
		}
	}

	public void play(){
		for (Settlement settlement:settlementArray){
			settlement.flag=false;
		}

		/*for (Thread thread:threads){
			System.out.println(thread.getState());
		}*/
	//}
}












