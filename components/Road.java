
// Guy Cohen - 205579808, Yarin Abraham - 208401166

package components;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

public class Road {
	
	private Junction fromJunc;
	private Junction toJunc;
	private ArrayList<VehicleType> allowedVehicles = new ArrayList<>();// holds the list of vehicle types that are allowed to move on the road.
	private boolean isOpen;// True when the light is green.
	private boolean isEnabled;//appears on the map
	private double length;// the distance between the two junctions.
	private int maxSpeed;

	//Road constructor
	public Road(Junction from,Junction to)
	{
		fromJunc=from;
		toJunc=to;
		length = countLength();
		Random rand = new Random();

		setAllowedVehiclesFrom();

		isOpen = rand.nextBoolean();
		isEnabled = rand.nextBoolean();

		maxSpeed = 10 * (rand.nextInt(13) + 1); // {10,20,30,40,50,60,70,80,90,100,110,120,130}

		length = countLength();

		from.addExitingRoads(this);
		to.addEnteringRoads(this);

		System.out.println("Road from "+ fromJunc.getJunctionName() +" to "+ toJunc.getJunctionName() + " has been created.");

		if(isOpen){
			to.setHasLight(true);
			System.out.println(to + "\n" + this);
		}
	}
	//Road constructor
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

		if(isOpen){
			to.setHasLight(true);
			System.out.println(to + "\n" + this);
		}
	}
	//Copy Constructor
	public Road(Road R){
		fromJunc = R.fromJunc;
		toJunc = R.toJunc;
		allowedVehicles = R.allowedVehicles;
		isOpen = R.isOpen;
		isEnabled = R.isEnabled;
		length = R.length;
		maxSpeed = R.maxSpeed;
	}
	//Sets And Gets to Road
	public boolean getLight() {return isOpen;}//check if the light is green or not
	public boolean getIsEnabled() {return isEnabled;}
	public double getLength() {return length;}
	public int getMaxSpeed() {return maxSpeed;}
	public Junction getFromJunc() {return fromJunc;}
	public ArrayList<VehicleType> getAllowedVehicles() {return allowedVehicles;}
	public Junction getToJunc() {return toJunc;}
	public void setFromJunc(Junction fromJunc) {this.fromJunc = fromJunc;}
	public void setToJunc(Junction toJunc) {this.toJunc = toJunc;}
	public void setLight(boolean TorF){ 
		isOpen=TorF; 
		if(TorF==true)
			System.out.println(this);	
		}
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
		if(this!=null && R!=null){
		boolean isAllowedVehiclesEquals = true;
			if(allowedVehicles.size() == R.allowedVehicles.size())
			{
				for(int i=0;i<allowedVehicles.size();i++)
					if(!allowedVehicles.get(i).equals(R.allowedVehicles.get(i))){
						isAllowedVehiclesEquals = false;
						break;
					}
			}else return false;

			if(fromJunc.getJunctionName() == R.fromJunc.getJunctionName() && toJunc.getJunctionName() == R.toJunc.getJunctionName() 
						&& isAllowedVehiclesEquals &&isOpen == R.isOpen && isEnabled == R.isEnabled && length == R.length && maxSpeed == R.maxSpeed)
				return true;
		}
		return false;
	}; 

	public void setAllowedVehicles(ArrayList<VehicleType> allowedVehicles) { this.allowedVehicles = allowedVehicles; }

	public void setAllowedVehiclesFrom(){ //set a a random anount of AllowedVehicles from an random array of vehicle types
			//make an random index list to take set an random allowedVehicles list
			ArrayList<Integer> randIndex = new ArrayList<>();
			Random rand = new Random();
			int sizeOfRandIndex = rand.nextInt(VehicleType.getRandomVehicleTypes().size())+1; // +1 to prevent 0
			for(int i = 0; i<sizeOfRandIndex;i++)
			{
				int tempIndex;
				do{
					tempIndex = rand.nextInt(VehicleType.getRandomVehicleTypes().size());
				}while(randIndex.contains(tempIndex));
	
				randIndex.add(tempIndex);
			}
			for(int i = 0; i<sizeOfRandIndex;i++)
				allowedVehicles.add(VehicleType.getRandomVehicleTypes().get(randIndex.get(i))); // 40 - 130 (average speed)
		}
}
