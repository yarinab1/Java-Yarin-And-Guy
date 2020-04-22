
// Guy Cohen - 205579808, Yarin Abraham - 208401166

package components;

import java.util.ArrayList;
import java.util.Random;

import utilities.Point;

public class Junction {
	private String junctionName;
	private Point location;// location of the junction on the map
	private int delay;// delay time in seconds
	private boolean hasLight=false;// checks if the junction has traffic lights.
	private ArrayList<Road> enteringRoads = new ArrayList<>(); // holds the list of the roads that enter to the junction.
	private ArrayList<Road> exitingRoads = new ArrayList<>(); // holds the list of the roads that exitthe junction.
	private ArrayList<Road> vehicles = new ArrayList<>(); //list of entering roads with cars waiting on the junction
	public int nextEnterRoad=0;
	
	public Junction (String name, Point loc)//Junction constructor
	{
		junctionName=name;
		location= new Point(loc);
		
		Random rand = new Random();
		delay = 1 + rand.nextInt(10);
		System.out.println("Junction " + junctionName + " has been created.");
	}

	public double getX() {
		return location.getX();
	}

	public double getY() {
		return location.getY();
	}
	
	//set&get to JunctionName()
	public String getJunctionName() {
		return junctionName;
	}
	
	public void setJunctionName(String junctionName) {
		this.junctionName = junctionName;
	}

	
	//set&get to junctionName()
	public Point getLocation()
	{
		return location;
	}

	public void setLocation(Point p )
	{
		location=p;
	}
	
	//set&get to delay
	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public boolean getHasLight() {
		return hasLight;
	}

	public void setHasLight(boolean hasLight) {
		this.hasLight = hasLight;
	}

	public void setLightsOn(){ 
		for(Road item:enteringRoads)
			item.setLight(true);
		for(Road item:exitingRoads)
			item.setLight(true);
	 }

	public int getNextEnterRoad() {
		return nextEnterRoad;
	}

	public void setExitingRoads(ArrayList<Road> exitingRoads) {
		this.exitingRoads = exitingRoads;
	}
	public ArrayList<Road> getExitingRoads() {
		return exitingRoads;
	}

	public ArrayList<Road> getEnteringRoads() {
		return enteringRoads;
	}
	public void setEnteringRoads(ArrayList<Road> enteringRoads) {
		this.enteringRoads = enteringRoads;
	}
	public ArrayList<Road> getVehicles() {
		return vehicles;
	}
	public void setVehicles(ArrayList<Road> vehicles) {
		this.vehicles = vehicles;
	}
	
	public void changeLights() // make the next entering road in the list green (open) and all the others (exiting only) red (closed).
	{
		if(!enteringRoads.isEmpty()){
			enteringRoads.get(nextEnterRoad).setLight(true);
			
			for(int i=0;i<exitingRoads.size();i++)
					exitingRoads.get(i).setLight(false);
					
			if(nextEnterRoad + 1 < enteringRoads.size())
				nextEnterRoad++;
			else
				nextEnterRoad = 0;
		}//else System.out.println("Junction "+ junctionName +": No entering roads, traffic lights can't be turned on."); // for junction sample
	}

	public boolean checkAvailability(Road r){ //for vehicle that arrived to the junction from road r, checks if there are some other vehicles on the roads with a higher traffic priority on the junction.
		if(!vehicles.isEmpty() && vehicles.get(0).equals(r))
			for(int i=0; i<vehicles.size();i++)
				if(vehicles.get(i).getIsEnabled() && getHasLight() && i < vehicles.indexOf(r))
					return true;
		
		return false;
	}
	
	public String toString()
	{
		if(getHasLight())
			return "Junction " + junctionName+ ": traffic lights ON. Delay time: " + delay ;
		else return "Junction " + junctionName+ ": traffic lights OFF Delay time: " + delay ;
	}

	public boolean equals(Junction J){
		boolean roadsIsEqual = true;
		if(enteringRoads.size() == J.enteringRoads.size() && exitingRoads.size() == J.exitingRoads.size() && vehicles.size() == J.vehicles.size())
		{
			for(int i=0;i<enteringRoads.size();i++)
				if(!enteringRoads.get(i).equals(J.enteringRoads.get(i))){
					roadsIsEqual = false;
					break;
				}
			if(roadsIsEqual){
				for(int i=0;i<exitingRoads.size();i++)
					if(!exitingRoads.get(i).equals(J.exitingRoads.get(i))){
						roadsIsEqual = false;
						break;
					}
				if(roadsIsEqual)
					for(int i=0;i<vehicles.size();i++)
						if(!vehicles.get(i).equals(J.vehicles.get(i))){
							roadsIsEqual = false;
							break;
						}
			}
		}else return false;

		if(junctionName == J.junctionName && location.equals(J.location) && delay == J.delay && hasLight == J.hasLight && roadsIsEqual)
			return true;
		
		return false;
	};

	public void addEnteringRoads(Road R){ 
		enteringRoads.add(R); 
		Random rand = new Random();
		nextEnterRoad =rand.nextInt(enteringRoads.size());
	}
	public void addExitingRoads(Road R){ exitingRoads.add(R); }
	public void addToVehicles(Road R){ vehicles.add(R); }
	public void removefromVehicles(Road R){ vehicles.remove(R); }

}
