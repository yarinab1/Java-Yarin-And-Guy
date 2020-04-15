
// Guy Cohen - 205579808, Yarin Abraham - 208401166

package components;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

public class Road {
	
	private Junction fromJunc;
	private Junction toJunc;
	private ArrayList<VehicleType> allowedVehicles = new ArrayList<>();// holds the list of vehicle types
									 //that are allowed to move on the road.
	private boolean isOpen;// True when the light is green.
	private boolean isEnabled;//appears on the map
	private double length;// the distance between the two junctions.
	private int maxSpeed;

	
	public Road(Junction from,Junction to)
	{
		fromJunc=from;
		toJunc=to;
		length = countLength();
		Random rand = new Random();

		allowedVehicles = VehicleType.getRandomVehicleTypes();

		isOpen = rand.nextBoolean();
		isEnabled = rand.nextBoolean();

		maxSpeed = 10 * (rand.nextInt(13) + 1); // {10,20,30,40,50,60,70,80,90,100,110,120,130}

		length = countLength();

		from.addExitingRoads(this);
		to.addEnteringRoads(this);

		System.out.println("Road from "+ fromJunc.getJunctionName() +" to "+ toJunc.getJunctionName() + " has been created.");
	}
	
	public Road(Junction from,Junction to, ArrayList<VehicleType> allowed,boolean open, boolean enabled)
	{
		Random rand = new Random();

		fromJunc=from;
		toJunc=to;
		allowedVehicles.addAll(allowed);
		isOpen=open;
		isEnabled=enabled;
		length = countLength();
		maxSpeed = 10 * (rand.nextInt(12) + 1); // {10,20,30,40,50,60,70,80,90,100,110,120,130}

		from.addExitingRoads(this);
		to.addEnteringRoads(this);

		System.out.println("Road from "+ fromJunc.getJunctionName() +" to "+ toJunc.getJunctionName() + " has been created.");
	}
	
	public Road(Road R){
		fromJunc = R.fromJunc;
		toJunc = R.toJunc;
		allowedVehicles = R.allowedVehicles;
		isOpen = R.isOpen;
		isEnabled = R.isEnabled;
		length = R.length;
		maxSpeed = R.maxSpeed;
	}//cctor
	public boolean getLight() {return isOpen;}//check if the light is green or not
	public boolean getIsEnabled() {return isEnabled;}
	public double getLength() {return length;}
	public int getMaxSpeed() {return maxSpeed;}
	public Junction getFromJunc() {return fromJunc;}
	public ArrayList<VehicleType> getAllowedVehicles() {return allowedVehicles;}
	public Junction getToJunc() {return toJunc;}
	
	public void setFromJunc(Junction fromJunc) {this.fromJunc = fromJunc;}
	public void setToJunc(Junction toJunc) {this.toJunc = toJunc;}
	public void setLight(boolean TorF){ isOpen=TorF; }
	public void setAppears(boolean TorF){ isEnabled=TorF; }
	public void setLength(double len){ length=len; }
	public void setMaxSpeed (int max){ maxSpeed=max; }

	public void addVehicleType(VehicleType type) { allowedVehicles.add(type); }
	
	public double countLength()//calculates the length of the road using the coordinates
	{
		return Math.sqrt(Math.pow(toJunc.getX()-fromJunc.getX(), 2)+Math.pow(toJunc.getX()-fromJunc.getX(), 2));
	}
	
	public String toString()
	{
		if(isOpen)
			return "Road from "+ fromJunc.getJunctionName()+" to "+toJunc.getJunctionName() + ": green light";
		else return "Road from "+ fromJunc.getJunctionName()+" to "+toJunc.getJunctionName() + ": red light";
	}

	public boolean equals(Road R){
		boolean isAllowedVehiclesEquals = true;
		if(allowedVehicles.size() == R.allowedVehicles.size())
		{
			for(int i=0;i<allowedVehicles.size();i++)
				if(!allowedVehicles.get(i).equals(R.allowedVehicles.get(i))){
					isAllowedVehiclesEquals = false;
					break;
				}
		}else return false;

		if(fromJunc.getJunctionName() == R.fromJunc.getJunctionName()
		&& toJunc.getJunctionName() == R.toJunc.getJunctionName() && 
		isAllowedVehiclesEquals &&
		 isOpen == R.isOpen && 
		 isEnabled == R.isEnabled && 
		 length == R.length && 
		 maxSpeed == R.maxSpeed)
			return true;
		return false;
	}; 
}
