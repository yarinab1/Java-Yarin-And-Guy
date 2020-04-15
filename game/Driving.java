
// Guy Cohen - 205579808, Yarin Abraham - 208401166

package game;
import java.util.ArrayList;
import components.*;
import java.util.Random;

public class Driving {
    private int numOfJuncs;
    private int numOfVehicles;
    private Map currentMap;
    private ArrayList<Vehicle> currentVehicles = new ArrayList<>();
    private double drivingTime = 0;// time passed from the beginning of driving session
    private int maxTime; // total round time

    public Driving(int juncs, int vehicles, int maxTime)
    {
        setNumOfJuncs(juncs);
        setNumOfVehicles(vehicles);
        setMaxTime(maxTime);
        currentMap = new Map();
    }

    public int getNumOfJuncs() { return numOfJuncs; }

    public void setNumOfJuncs(int numOfJuncs) { this.numOfJuncs = numOfJuncs; }

    public int getNumOfVehicles() { return numOfVehicles; }

    public void setNumOfVehicles(int numOfVehicles) { this.numOfVehicles = numOfVehicles; }

    public Map getCurrentMap() { return currentMap; }

    public void setCurrentMap(Map currentMap) { this.currentMap = currentMap; }

    public ArrayList<Vehicle> getCurrentVehicles() { return currentVehicles; }

    public void setCurrentVehicles(ArrayList<Vehicle> currentVehicles) { this.currentVehicles = currentVehicles; }

    public double getDrivingTime() { return drivingTime; }

    public void setDrivingTime(double drivingTime) { this.drivingTime = drivingTime; }

    public int getMaxTime() { return maxTime; }

    public void setMaxTime(int maxTime) { this.maxTime = maxTime; }


    public void addMap()//creates a map with random (10-25) junctions quantity.
    {
        Random rand = new Random();
        numOfJuncs = 10 + rand.nextInt(1);
        
        currentMap = new Map(numOfJuncs);
    }

    public void addVehicles(){//creates random number (2-8) of vehicles of different types.
        Random rand = new Random();
        numOfVehicles = 2 + rand.nextInt(7);

		//make an random index list to take set an random allowedVehicles list
        ArrayList<Junction> mapJunctions = new ArrayList<>();
        mapJunctions.addAll(currentMap.getJunctions());

		for(int i = 0; i<numOfVehicles;i++){
            ArrayList<Road> tempJRoads = new ArrayList<>();
            ArrayList<VehicleType> tampJVehicleTypes = new ArrayList<>();
            Junction tempJunction = mapJunctions.get(rand.nextInt(mapJunctions.size()));
            tempJRoads.addAll(tempJunction.getExitingRoads());
            tempJRoads.addAll(tempJunction.getEnteringRoads());
            if(!tempJRoads.isEmpty())
                tampJVehicleTypes.addAll(tempJRoads.get(rand.nextInt((tempJRoads.size()/2)+1)).getAllowedVehicles());
            else
                tampJVehicleTypes.addAll(VehicleType.getRandomVehicleTypes());
            currentVehicles.add(new Vehicle(i,tampJVehicleTypes.get(rand.nextInt(tampJVehicleTypes.size())),tempJunction));
        }
    } 

    public ArrayList<Vehicle> getVehicles(){ return currentVehicles; }

    public void startDrive(int maxTime){
        this.maxTime = maxTime;
    }//TODO:
    
    public String toString() {
        return "There is " + numOfJuncs + " junctions and " + numOfVehicles + " vehicles at this Driving";
    }
    
    public boolean equals(Driving D) {
        boolean currentVehiclesEquals = true;
        if(currentVehicles.size() == D.currentVehicles.size())
		{
			for(int i=0;i<currentVehicles.size();i++)
				if(!currentVehicles.get(i).equals(D.currentVehicles.get(i)))
                currentVehiclesEquals = false;
        }else currentVehiclesEquals = false;
        
        if(numOfJuncs == D.numOfJuncs && numOfVehicles == D.numOfVehicles && currentMap.equals(D.currentMap) && currentVehiclesEquals && drivingTime == D.drivingTime && maxTime == D.maxTime)
            return true;
        return false;
    }
}