package game;
import java.util.ArrayList;
import components.*;
import java.util.Random;

public class Driving {
	private int numOfJuncs;
	private int numOfVehicles;
	private Map currentMap;
	private ArrayList<Vehicles> currentVehicles;
	private double drivingTime;// time passed from the beginning of driving session
	private int maxTime;
	
	public Driving(int juncs, int vehicles, int maxTime)
	{
		setNumOfJuncs(juncs);
		setNumOfVehicles(vehicles);
		this.setMaxTime(maxTime);
		
	}

	public int getNumOfJuncs() {
		return numOfJuncs;
	}

	public void setNumOfJuncs(int numOfJuncs) {
		this.numOfJuncs = numOfJuncs;
	}

	public int getNumOfVehicles() {
		return numOfVehicles;
	}

	public void setNumOfVehicles(int numOfVehicles) {
		this.numOfVehicles = numOfVehicles;
	}

	public Map getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(Map currentMap) {
		this.currentMap = currentMap;
	}

	public ArrayList<Vehicles> getCurrentVehicles() {
		return currentVehicles;
	}

	public void setCurrentVehicles(ArrayList<Vehicles> currentVehicles) {
		this.currentVehicles = currentVehicles;
	}

	public double getDrivingTime() {
		return drivingTime;
	}

	public void setDrivingTime(double drivingTime) {
		this.drivingTime = drivingTime;
	}

	public int getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}
	
	
	public void addMap()//creates a map with random (10-25) junctions quantity.
	{
		Random rand = new Random();
		int rand1=rand.nextInt(26)+10;
		for(int i=0;i<rand1;i++)
		{
			
		}
	}
}	

