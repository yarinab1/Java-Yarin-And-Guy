package components;

import java.util.ArrayList;

import utilities.Point;

public class Junction {
	private String junctionName;
	private Point location;// location of the junction on the map
	private int delay;// delay time in seconds
	private boolean hasLight=false;// checks if the junction has traffic lights.
	private ArrayList<Road> enteringRoad;// holds the list of the roads that enter to the junction.
	private ArrayList<Road> exitingRoad;// holds the list of the roads that exitthe junction.
	private ArrayList<Road> vehicles;//list of entering roads with cars waiting on the junction
	public int nextEnterRoad=0;
	
	public Junction (String name, Point loc)
	{
		junctionName=name;
		location=loc;
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

	public int getNextEnterRoad() {
		return nextEnterRoad;
	}
	
	
	public void setExitingRoad(ArrayList<Road> exitingRoad) {
		this.exitingRoad = exitingRoad;
	}
	public ArrayList<Road> getExitingRoad() {
		return exitingRoad;
	}

	public ArrayList<Road> getEnteringRoad() {
		return enteringRoad;
	}
	public void setEnteringRoad(ArrayList<Road> enteringRoad) {
		this.enteringRoad = enteringRoad;
	}
	public ArrayList<Road> getVehicles() {
		return vehicles;
	}
	public void setVehicles(ArrayList<Road> vehicles) {
		this.vehicles = vehicles;
	}
	
	public void changeLight()//make the next entering road in the list green (open) and all the others (exiting only) red (closed).
	{
		enteringRoad.get(nextEnterRoad).setLight(true);
		for(int i=0;i<exitingRoad.size();i++)
				exitingRoad.get(i).setLight(false);
				
		if(nextEnterRoad + 1 < enteringRoad.size())
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
		if(enteringRoad.size() == J.enteringRoad.size() && exitingRoad.size() == J.exitingRoad.size() && vehicles.size() == J.vehicles.size())
		{
			for(int i=0;i<enteringRoad.size();i++)
				if(!enteringRoad.get(i).equals(J.enteringRoad.get(i))){
					roadsIsEqual = false;
					break;
				}
			if(roadsIsEqual){
				for(int i=0;i<enteringRoad.size();i++)
					if(!exitingRoad.get(i).equals(J.exitingRoad.get(i))){
						roadsIsEqual = false;
						break;
					}
				if(roadsIsEqual)
					for(int i=0;i<enteringRoad.size();i++)
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
