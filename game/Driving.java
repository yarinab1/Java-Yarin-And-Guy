
// Guy Cohen - 205579808, Yarin Abraham - 208401166

package game;
import java.util.ArrayList;
import components.*;
import java.util.Random;

public class Driving {
    private int numOfJuncs;
    private int numOfVehicles;
    private Map currentMap;
    private ArrayList<Vehicle> currentVehicles = new ArrayList<>();;
    private double drivingTime; // time passed from the beginning of driving session
    private int maxTime; // total round time

    public Driving(int juncs, int vehicles, int maxTime)
    {
        VehicleType.setRandomVehicleTypes();
        setNumOfJuncs(juncs);
        setNumOfVehicles(vehicles);
        this.setMaxTime(maxTime);
		    addMapAs();
		    addVehiclesAs();
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

    public ArrayList<Vehicle> getCurrentVehicles() {
        return currentVehicles;
    }

    public void setCurrentVehicles(ArrayList<Vehicle> currentVehicles) {
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

    public void addMapAs(){ //creates a map with junctions quantity as nunOfJuncs amount.
        currentMap = new Map(numOfJuncs);
    }

    public void addMap()//creates a map with random (10-25) junctions quantity.
    {
        Random rand = new Random();
        numOfJuncs = 10 + rand.nextInt(16);
        
        currentMap = new Map(numOfJuncs);
    }

    public void addVehicles(){ //creates random number (2-8) of vehicles of different types.
        Random rand = new Random();
        numOfVehicles = 2 + rand.nextInt(7); //make an random index list to take set an random allowedVehicles list

        ArrayList<Junction> mapJunctions =  currentMap.getJunctions();

		for(int i = 0; i<numOfVehicles;i++){
            Junction tempJunction = mapJunctions.get(rand.nextInt(mapJunctions.size()));
            ArrayList<Road> tempJRoads = tempJunction.getExitingRoads();
            ArrayList<VehicleType> tampJVehicleTypes = tempJRoads.get(rand.nextInt(tempJRoads.size())).getAllowedVehicles();
            currentVehicles.add(new Vehicle(i,tampJVehicleTypes.get(rand.nextInt(tampJVehicleTypes.size())),tempJunction));
        }

        ArrayList<Road> tempRoads = new ArrayList<>();
        ArrayList<Junction> tempJunctions = new ArrayList<>();
        for(Vehicle Vitem: currentVehicles)
        {
            boolean thereIsAnExitRoad = true;
            tempJunctions.add(Vitem.getLastJunction());
            do{
                thereIsAnExitRoad = true;
                for(Road road: tempJunctions.get(tempJunctions.size()-1).getExitingRoads())
                    {
                        if(road.getIsEnabled() && road.getAllowedVehicles().contains(Vitem.getType())){
                            tempRoads.add(road);
                            tempJunctions.add(road.getToJunc());
                            road.getToJunc().addToVehicles(road);
                            break;
                        }else if(tempJunctions.get(tempJunctions.size()-1).getExitingRoads().indexOf(road) == tempJunctions.get(tempJunctions.size()-1).getExitingRoads().size()-1)
                            thereIsAnExitRoad = false;
                    }
            }while(thereIsAnExitRoad && tempJunctions.size()!=10 && tempRoads.size()!=9);

            Vitem.setCurrentRoute(new Route(tempJunctions, tempRoads, Vitem.getType()));
            tempRoads.clear();
            tempJunctions.clear();
        }
    }

    public void addVehiclesAs(){ //creates vehicles of different types as the amount of numOfVehicles.
        Random rand = new Random();
        ArrayList<Junction> mapJunctions =  currentMap.getJunctions();

		for(int i = 0; i<numOfVehicles;i++){
            Junction tempJunction = mapJunctions.get(rand.nextInt(mapJunctions.size()));
            ArrayList<Road> tempJRoads = tempJunction.getExitingRoads();
            ArrayList<VehicleType> tampJVehicleTypes = tempJRoads.get(rand.nextInt(tempJRoads.size())).getAllowedVehicles();
            currentVehicles.add(new Vehicle(i,tampJVehicleTypes.get(rand.nextInt(tampJVehicleTypes.size())),tempJunction));
        }

        
        ArrayList<Road> tempRoads = new ArrayList<>();
        ArrayList<Junction> tempJunctions = new ArrayList<>();
        for(Vehicle Vitem: currentVehicles) // set a vihecle route
        {
            boolean thereIsAnExitRoad = true;
            tempJunctions.add(Vitem.getLastJunction());
            do{
                thereIsAnExitRoad = true;
                for(Road road: tempJunctions.get(tempJunctions.size()-1).getExitingRoads())
                    {
                        if(road.getIsEnabled() && road.getAllowedVehicles().contains(Vitem.getType())){
                            tempRoads.add(road);
                            tempJunctions.add(road.getToJunc());
                            road.getToJunc().addToVehicles(road);
                            break;
                        }else if(tempJunctions.get(tempJunctions.size()-1).getExitingRoads().indexOf(road) == tempJunctions.get(tempJunctions.size()-1).getExitingRoads().size()-1)
                            thereIsAnExitRoad = false;
                    }
            }while(thereIsAnExitRoad && tempJunctions.size()!=10 && tempRoads.size()!=9);

            if(!tempRoads.isEmpty())
                Vitem.setLastRoad(tempRoads.get(0));
            Vitem.setCurrentRoute(new Route(tempJunctions, tempRoads, Vitem.getType()));
            tempRoads.clear();
            tempJunctions.clear();
        }
    } 
    
    public void startDrive(int maxTime)
    {
    	/*for(int i=0;i<maxTime;i++)
    	{
    		for(int j=0;j<numOfVehicles;j++)
                currentVehicles.get(j).move();
                

    		ArrayList<String> Roads = new ArrayList<>();
    		for(int h=0;h<currentVehicles.size();h++)
    		{
    			currentVehicles.get(h).getLastJunction().changeLights();
    			for(int y=0;y<currentVehicles.get(h).getLastJunction().getVehicles().size();y++)
    				if(currentVehicles.get(h).getLastJunction().getVehicles().get(y).getLight() && !Roads.contains(currentVehicles.get(h).getLastJunction().getVehicles().get(y).toString()))
    				{
    					System.out.println(currentVehicles.get(h).getLastJunction().getVehicles().get(y)+ " -green Light ");
    					Roads.add(currentVehicles.get(h).getLastJunction().getVehicles().get(y).toString());
    				}
    		}
        }
        
    	for(int i=0;i<currentVehicles.size();i++)
            currentVehicles.get(i).status();*/
            
            for(int i=0; i<maxTime; i++)
            {
                System.out.println("\nTURN " + String.valueOf(i+1));

                for(Vehicle vehicle:currentVehicles)
                {
                    boolean isWaiting = false;
                    for(Vehicle tempV: currentVehicles)
                        if(tempV.equals(vehicle))
                            break;
                        else if(!vehicle.getMoveNow() && vehicle.getLastRoad() != null &&vehicle.getLastRoad().equals(tempV.getLastRoad()))
                            {
                                tempV.setMoveNow(false);
                                isWaiting = true;
                            }
                    if(!isWaiting)
                        vehicle.move();
                    else
                        System.out.println(vehicle + " waiting for his priority at Junction " + vehicle.getLastJunction().getJunctionName() + ".");
                }

                for(Junction junction: currentMap.getJunctions())
                    junction.changeLights();
            }
            for(Junction junction: currentMap.getJunctions())
                junction.printOpenRoads();

            System.out.println("\nSTATUS");
            for(Vehicle vehicle:currentVehicles)
                vehicle.status();
    }
    
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