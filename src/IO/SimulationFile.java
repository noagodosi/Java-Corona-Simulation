package IO;
 

import Country.*;
import Location.Location;
import Location.Point;
import Location.Size;
import Population.Healthy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//Noa Fadida, 209507680|| Adi Godosi , 316413780//
public class SimulationFile {

	public Map loadFileFunc(String path) {
			List<Settlement> settlements = new ArrayList<>();
			List<String[]> arrayOfConnected = new ArrayList<>();
		try {
				FileReader fr = new FileReader(path);
				BufferedReader br = new BufferedReader(fr);
				String line = br.readLine();
				while(line != null) {
					if (line.startsWith("#")) {
						arrayOfConnected.add(fileLineConnect(line));
					}
					else {
						Settlement settlement = fileLineToSettlement(line);
						settlements.add(settlement);
					}
					line = br.readLine();
				}
				br.close();
				fr.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("File not found | Error msg: " + e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("IO exception | Error msg: " + e.getMessage());
			}

			// ArrayList to Array
			Settlement[] settlementsArray = new Settlement[settlements.size()];
			settlementsArray = settlements.toArray(settlementsArray);

			Settlement temp1 = null;
			Settlement temp2 = null;
			for (int i = 0; i< arrayOfConnected.size(); i++) {
				for(Settlement settlement: settlements){
					if(settlement.getName().equals(arrayOfConnected.get(i)[0])){
						temp1=settlement;
					}
					if(settlement.getName().equals(arrayOfConnected.get(i)[1])){
						temp2=settlement;
					}
				}
				if(temp1 != null && temp2 != null){
					temp1.setConnectedSettlements(temp2);
					temp2.setConnectedSettlements(temp1);
				}
			}
			return new Map(settlementsArray);
	}

	private String[] fileLineConnect(String line)
	{
		String[] lineNames = line.split(";");
		String[] toConnect = new String[2];
		try {
			toConnect[0] =lineNames[1];
			toConnect[1]=lineNames[2];

		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println("Line text file format error | Error msg: " + e.getMessage());
		}
		return toConnect;
	}

	private Settlement fileLineToSettlement(String line) {
		Settlement settlement = null;
		Random rand = new Random();
		String[] settlementVariables = line.split("; ");
		try {
			String settlementType = settlementVariables[0];
			String settlementName = settlementVariables[1];
			Point settlementPoint = pointStringToPointObject(settlementVariables[2]);
			Size settlementSize = sizeStringToSizeObject(settlementVariables[3]);
			int settlementNumOfPeople = Integer.parseInt(settlementVariables[4]);
			int maxPeople = (int) (settlementNumOfPeople*1.3);
			settlement = SettelmentFactory.createSettelment(settlementType,settlementName, new Location(settlementPoint, settlementSize), new ArrayList<>(), RamzorColor.GREEN, maxPeople);

			for(int i=0; i<settlementNumOfPeople; i++) {
				settlement.addPerson(new Healthy(5*(6*(int)rand.nextGaussian() + 9) + rand.nextInt(5), settlement.randomLocation(), settlement));
			}

		}catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println("Line text file format error | Error msg: " + e.getMessage());
		}

		return settlement;
	}

	private Point pointStringToPointObject(String pointString) {
		Point point = null;
		try {
			String[] stringPointArray = pointString.split(";"); 
			point = new Point(Integer.parseInt(stringPointArray[0]), Integer.parseInt(stringPointArray[1]));
		}catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println("Point text file format error | Error msg: " + e.getMessage());
		}
		return point;
	}
	
	private Size sizeStringToSizeObject(String sizeString) {
		Size size = null;
		try {
			String[] stringSizeArray = sizeString.split(";"); 
			size = new Size(Integer.parseInt(stringSizeArray[0]), Integer.parseInt(stringSizeArray[1]));
		}catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println("Size text file format error | Error msg: " + e.getMessage());
		}
		return size;
	}
}
