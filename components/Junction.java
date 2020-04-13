package components;

import java.util.ArrayList;
import java.util.Random;

import utilities.Point;

public class Junction {
	private String junctionName;
	private Point location;// location of the junction on the map
	private int delay;// delay time in seconds
	private boolean hasLight=false;// checks if the junction has traffic lights.
	private ArrayList<Road> enteringRoads;// holds the list of the roads that enter to the junction.
	private ArrayList<Road> exitingRoad;// holds the list of the roads that exitthe junction.
	private ArrayList<Road> vehicles;//list of entering roads with cars waiting on the junction
	public int nextEnterRoad=0;
	
	public Junction (String name, Point loc)
	{
		junctionName=name;
		location=loc;
		if(enteringRoads.size() <= 10)
			delay = enteringRoads.size();
		else {
			Random rand = new Random();
			delay = 1 + rand.nextInt(10);
		} 
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

	public void setLightsOn(){hasLight=true;}

	public int getNextEnterRoad() {
		return nextEnterRoad;
	}

	public void getExitingRoads(ArrayList<Road> exitingRoad) {
		this.exitingRoad = exitingRoad;
	}
	public ArrayList<Road> getExitingRoads() {
		return exitingRoad;
	}

	public ArrayList<Road> getEnteringRoads() {
		return enteringRoads;
	}
	public void setenteringRoads(ArrayList<Road> enteringRoads) {
		this.enteringRoads = enteringRoads;
	}
	public ArrayList<Road> getVehicles() {
		return vehicles;
	}
	public void setVehicles(ArrayList<Road> vehicles) {
		this.vehicles = vehicles;
	}
	
	public void changeLights()//make the next entering road in the list green (open) and all the others (exiting only) red (closed).
	{
		enteringRoads.get(nextEnterRoad).setLight(true);
		for(int i=0;i<exitingRoad.size();i++)
				exitingRoad.get(i).setLight(false);
				
		if(nextEnterRoad + 1 < enteringRoads.size())
			nextEnterRoad++;
		else
			nextEnterRoad = 0;
	}

	public boolean checkAvailability(Road r){ //make the next entering road in the list green (open)and all the others (exiting only) red (closed).
		if(hasLight)
			if(r.getLight() == true)
				return false;
			else
				return true;
		else if(vehicles.indexOf(r) == 0)
			return false;
		else return true;
	}
	
	public String toString()
	{
		return "the Junction Name is: "+ junctionName +"\n";
	}

	public boolean equals(Junction J){
		boolean roadsIsEqual = true;
		if(enteringRoads.size() == J.enteringRoads.size() && exitingRoad.size() == J.exitingRoad.size() && vehicles.size() == J.vehicles.size())
		{
			for(int i=0;i<enteringRoads.size();i++)
				if(!enteringRoads.get(i).equals(J.enteringRoads.get(i))){
					roadsIsEqual = false;
					break;
				}
			if(roadsIsEqual){
				for(int i=0;i<enteringRoads.size();i++)
					if(!exitingRoad.get(i).equals(J.exitingRoad.get(i))){
						roadsIsEqual = false;
						break;
					}
				if(roadsIsEqual)
					for(int i=0;i<enteringRoads.size();i++)
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
}
