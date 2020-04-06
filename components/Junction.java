package components;

import utilities.Point;

public class Junction {
	private String JunctionName;
	private Point Location;// location of the junction on the map
	private int delay;// delay time in seconds
	private boolean HasLight=false;// checks if the junction has traffic lights.
	private Road[] enteringRoad;// holds the list of the roads that enter to the junction.
	private Road[] exitingRoad;// holds the list of the roads that exitthe junction.
	private Road[] Vehicles;//list of entering roads with cars waiting on the junction
	public static int nextEnterRoad=0;
	
	
	public Junction (String name, Point loc)
	{
		JunctionName=name;
		Location=loc;
		
	}
	
	

	public double getX() {
		
		return Location.getX();
	}
	public double getY() {
			
			return Location.getY();
	}
	
	//set&get to JunctionName()
	public String getJunctionName() {
		return JunctionName;
	}
	public void setJunctionName(String junctionName) {
		JunctionName = junctionName;
	}
	
	//set&get to junctionName()
	public Point getLocation()
	{
		return Location;
	}
	public void setLocation(Point p )
	{
		Location=p;
	}
	
	//set&get to delay
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public boolean isHasLight() {
		return HasLight;
	}
	public void setHasLight(boolean hasLight) {
		HasLight = hasLight;
	}
	
	
	public void setExitingRoad(Road[] exitingRoad) {
		this.exitingRoad = exitingRoad;
	}
	public Road[] getExitingRoad() {
		return exitingRoad;
	}
	
	
	
	public Road[] getEnteringRoad() {
		return enteringRoad;
	}
	public void setEnteringRoad(Road[] enteringRoad) {
		this.enteringRoad = enteringRoad;
	}
	public Road[] getVehicles() {
		return Vehicles;
	}
	public void setVehicles(Road[] vehicles) {
		Vehicles = vehicles;
	}
	
	public void changeLight()//make the next entering road in the list green (open) and all the others (exiting only) red (closed).
	{
		enteringRoad[nextEnterRoad].SetLight(true);
		for(int i=0;i<enteringRoad.length;i++)
		{
			if(i!=nextEnterRoad)
				enteringRoad[i].SetLight(false);
		}
		nextEnterRoad++;
	}
}
