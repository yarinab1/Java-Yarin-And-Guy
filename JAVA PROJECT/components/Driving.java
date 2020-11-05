package components;

import java.util.ArrayList;

import GUI.DrivingGameFrame;
import utilities.*;

/**
 * 
 * @version 1.3 29 april 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see Utilities
 * @see Timer
 */

public class Driving extends Thread implements Utilities, Timer {
    private Map map;
    private ArrayList<Vehicle> vehicle = new ArrayList<>();
    private static int drivingTime = 0;
    private ArrayList<Timer> allTimedElements = new ArrayList<>();
    private DrivingGameFrame frame;
    private int statIndicator = 0; // 0 - didn't started, 1 - running, 2 - stoped, 3 - resumed

    public Driving(Map map, DrivingGameFrame frame){
        super();
        this.frame = frame;
        this.map = map;
        vehicle = map.getVehicles();
        allTimedElements.addAll(vehicle);
        for (Junction junction : map.getJunctions()) {
            if (junction instanceof LightedJunction)
                allTimedElements.add(((LightedJunction) junction).getLights());
        }
    }
    
    /**
     * 
     * @param numOfJunctions
     * @param numOfVehicles
     */
    public Driving(int numOfJunctions, int numOfVehicles) {
        super();
        map = new Map(numOfJunctions);
        System.out.println("================= CREATING VEHICLES =================\n");
        for (int i = 0; i < numOfVehicles; i++) {
            ArrayList<Road> tempRoadList;
            do {
                tempRoadList = map.getJunctions().get(getRandomInt(0, map.getJunctions().size())).getExitingRoads(); // get exiting roads list of a random junction in the map
            } while (tempRoadList.size() == 0);
            vehicle.add(new Vehicle(tempRoadList.get(getRandomInt(0, tempRoadList.size())))); // set random road from tempRoadList to vehicle constructor
        }

        allTimedElements.addAll(vehicle);
        for (Junction junction : map.getJunctions()) {
            if (junction instanceof LightedJunction)
                allTimedElements.add(((LightedJunction) junction).getLights());
        }
    };

     /**
     * 
     * @param numOfJunctions
     * @param numOfVehicles
     * @param frame
     */
    public Driving(int numOfJunctions, int numOfVehicles, DrivingGameFrame frame) {
        super();
        this.frame = frame;
        map = new Map(numOfJunctions);
        System.out.println("================= CREATING VEHICLES =================\n");
        for (int i = 0; i < numOfVehicles; i++) {
            ArrayList<Road> tempRoadList;
            do {
                tempRoadList = map.getJunctions().get(getRandomInt(0, map.getJunctions().size())).getExitingRoads(); // get exiting roads list of a random junction in the map
            } while (tempRoadList.size() == 0);
            vehicle.add(new Vehicle(tempRoadList.get(getRandomInt(0, tempRoadList.size())))); // set random road from tempRoadList to vehicle constructor
        }

        allTimedElements.addAll(vehicle);
        for (Junction junction : map.getJunctions()) {
            if (junction instanceof LightedJunction)
                allTimedElements.add(((LightedJunction) junction).getLights());
        }
    };


	/**
     * 
     * @param numOfTurns
     */
    public void drive(int numOfTurns) {
        System.out.println("\n================= START DRIVING =================");
        for (int i = 0; i < numOfTurns; i++) {
            System.out.println("\n*************** TURN " + ((int) i + 1) + " ***************");
            incrementDrivingTime();
        }
    }

    @Override
    public void run() {
        setStatIndicator(1);
        System.out.println("\n================= START DRIVING =================");
        for(Vehicle veh: vehicle)
            veh.start();
        for(int i=0 ; i<frame.getNumOfDrivingIteration();i++){
            if(statIndicator == 3){ // resumed
                statIndicator = 1;
                for(Vehicle veh: vehicle)
                    veh.resumeVehicleThread();
            }
            else if(statIndicator == 2){ //stoped
                for(Vehicle veh: vehicle)
                    veh.resumeVehicleThread();;
                stopDrivingThread();
            }
            System.out.println("\n*************** TURN " + ((int) i + 1) + " ***************");
            for(Timer item:allTimedElements)
                if(item instanceof TrafficLights)
                    new Thread((TrafficLights) item).start();
            incrementDrivingTime();
            try {
                sleep(100);
                frame.repaint();
            } catch (InterruptedException e) {}
            if(statIndicator == 0)
                break;
        }
        for(Vehicle veh: vehicle)
            veh.setStoped(true);
    }

    public synchronized void resumeDrivingThread(){ notify(); }
    public synchronized void stopDrivingThread(){ try{ wait(); }catch(InterruptedException e){} }

    @Override
    public void incrementDrivingTime() {
        drivingTime++;
        for(Timer item:allTimedElements){
            item.incrementDrivingTime();
            if(!(item instanceof TrafficLights))
                System.out.println();
        }
    }
    public void addToAllTimeElements(Vehicle newVehicle) {
        allTimedElements.add(newVehicle);
	}

    public int getStatIndicator() {
        return statIndicator;
    }

    public void setStatIndicator(int statIndicator) {
        this.statIndicator = statIndicator;
    }
	
    /**
     * 
     * @return map
     */
	public Map getMap() {
		return map;
    }
    
    /**
     * 
     * @param map
     */
	public void setMap(Map map) {
		this.map = map;
    }
    
    /**
     * 
     * @return vehicle
     */
	public ArrayList<Vehicle> getVehicle() {
		return vehicle;
	}

    /**
     * 
     * @param vehicle
     */
	public void setVehicle(ArrayList<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}

    /**
     * 
     * @return drivingTime
     */
	public static int getDrivingTime() {
		return drivingTime;
	}

    /**
     * 
     * @param drivingTime
     */
	public void setDrivingTime(int drivingTime) {
		this.drivingTime = drivingTime;
    }


    
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
			return true;
		if (!(obj instanceof Junction)) {
			return false;
		}
		Driving D = (Driving) obj;
        
        boolean vehiclesIsEqual = true;
		if(vehicle.size() == D.vehicle.size())
		{
			for(int i=0;i<vehicle.size();i++)
				if(!vehicle.get(i).equals(D.vehicle.get(i))){
					vehiclesIsEqual = false;
					break;
                }
            if(vehiclesIsEqual){
                for(int i=0;i<vehicle.size();i++)
                    if(!vehicle.get(i).equals(D.vehicle.get(i))){
                        vehiclesIsEqual = false;
                        break;
                    }
            }else return false;
        }
        return map.equals(D.getMap()) && vehiclesIsEqual;
    }

    @Override
    public String toString() {
        return "Vehicles: " + vehicle +"\n\nAll Timed Elements: " + allTimedElements;
    }
	
}