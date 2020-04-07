package components;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

public class Road {
	
	private Junction fromJunc;
	private Junction toJunc;
	private ArrayList<String> allowedVehicles;// holds the list of vehicle types
									 //that are allowed to move on the road.
	private boolean isOpen;// True when the light is green.
	private boolean isEnabled;//appears on the map
	private double lenght;// the distance between the two junctions.
	private int maxSpeed;

	
	public Road(Junction from,Junction to)
	{
		fromJunc=from;
		toJunc=to;
		lenght = countLength();
		Random rand = new Random();

		String[] vehiclesTypes = {"Truck","Motorcycle","Bikes","Private","Tricycle","Ambulance","jeep","SUV","Tractor"};
		//make an random index list to take set an random allowedVehicles list
		ArrayList<Integer> randIndex = new ArrayList<>();
		int sizeOfRandIndex = rand.nextInt(vehiclesTypes.length)+1; // +1 to prevent 0
		for(int i = 0; i<sizeOfRandIndex;i++)
		{
			int tempIndex;
			do{
				tempIndex = rand.nextInt(vehiclesTypes.length);
			}while(randIndex.contains(tempIndex));

			randIndex.add(tempIndex);
		}
		for(int i = 0; i<sizeOfRandIndex;i++)
			allowedVehicles.add(vehiclesTypes[randIndex.get(i)]);

		isOpen = rand.nextBoolean();
		isEnabled = rand.nextBoolean();

		maxSpeed = 10 * (rand.nextInt(12) + 1); // {10,20,30,40,50,60,70,80,90,100,110,120,130}
	}
	
	public Road(Junction from,Junction to, ArrayList<String> allowed,boolean open, boolean enabled)
	{
		fromJunc=from;
		toJunc=to;
		allowedVehicles=new ArrayList<String>();
		for(int i=0;i<allowed.size();i++)
			allowedVehicles.add(allowed.get(i));
		isOpen=open;
		isEnabled=enabled;
		lenght = countLength();
	}
	
	public boolean getLight() {return isOpen;}//check if the light is green or not
	public boolean getAppears() {return isEnabled;}
	public double getLenght() {return lenght;}
	public int getMaxSpeed() {return maxSpeed;}
	public Junction getFromJunc() {return fromJunc;}
	public ArrayList<String> getAllowedVehicles() {return allowedVehicles;}
	public Junction getToJunc() {return toJunc;}

	public void setFromJunc(Junction fromJunc) {
		this.fromJunc = fromJunc;
	}

	public void setToJunc(Junction toJunc) {
		this.toJunc = toJunc;
	}

	public void setLight(boolean TorF)//true or false
	{
		isOpen=TorF;
	}
	
	public void setAppears(boolean TorF)//true or false
	{
		isEnabled=TorF;
	}
	public void setLenght(double len)
	{
		lenght=len;
	}
	public void setMaxSpeed (int max)
	{
		maxSpeed=max;
	}

	
	public void addVehicleType(String type) { allowedVehicles.add(type); }
	
	public double countLength()//calculates the length of the road using the coordinates
	{
		return Math.sqrt(Math.pow(toJunc.getX()-fromJunc.getX(), 2)+Math.pow(toJunc.getX()-fromJunc.getX(), 2));
	}
	
	public String toString()
	{
		return "the Road is from "+ fromJunc.getJunctionName()+" to "+toJunc.getJunctionName()+"\n";
	}

	public boolean equals(Road R){
		boolean isAllowedVehiclesEquals = true;
		if(allowedVehicles.size() == R.allowedVehicles.size())
		{
			for(int i=0;i<allowedVehicles.size();i++)
				if(!allowedVehicles.get(i).equals(R.allowedVehicles.get(i)))
					isAllowedVehiclesEquals = false;
		}else isAllowedVehiclesEquals = false;
		if(fromJunc.equals(R.fromJunc) && toJunc.equals(R.toJunc) && isAllowedVehiclesEquals && isOpen == R.isOpen && isEnabled == R.isEnabled && lenght == R.lenght && maxSpeed == R.maxSpeed)
			return true;
		return false;
	}; 
}
