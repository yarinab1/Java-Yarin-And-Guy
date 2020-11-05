package components;
import java.util.ArrayList;
import utilities.*;

/**
 * 
 * @version 1.3 29 april 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see RouteParts
 */

public class Road implements RouteParts {
    private static int [] allowedSpeedOptions = {30,40,50,55,60,70,80,90};
    private boolean enable;
    private Junction startJunction;
    private Junction endJunction;
    private boolean greenlight = false;
    private double length;
    private int maxSpeed;
    private VehicleType[] vehicleTypes;
    private ArrayList<Vehicle> waitingVehicles;

    /**
     * Road constructor
     * @param start
     * @param end
     */
    public Road (Junction start, Junction end){
		enable = getRandomBoolean();
		start.addExitingRoad(this);
		end.addEnteringRoad(this);
		startJunction = start;
		endJunction = end;
		length = calcLength();
		maxSpeed = allowedSpeedOptions[getRandomInt(0, allowedSpeedOptions.length)];
		waitingVehicles = new ArrayList<>();

		vehicleTypes = new VehicleType[getRandomInt(1, VehicleType.values().length)]; //  minVal = 1, to prevent 0
		
		ArrayList<Integer> randIndex = getRandomIntArray(0, VehicleType.values().length, vehicleTypes.length);

		for(int i=0;i<vehicleTypes.length;i++)
			vehicleTypes[i] = VehicleType.values()[randIndex.get(i)];

		successMessage(toString());
	};

	/**
	 * Road constructor
	 * @param start
	 * @param end
	 * @param MapvehicleTypes
	 */
	public Road (Junction start, Junction end,VehicleType[] MapvehicleTypes){ // set road to match CityMap and CountryMap
		enable = getRandomBoolean();
		start.addExitingRoad(this);
		end.addEnteringRoad(this);
		startJunction = start;
		endJunction = end;
		length = calcLength();
		maxSpeed = allowedSpeedOptions[getRandomInt(0, allowedSpeedOptions.length)];
		waitingVehicles = new ArrayList<>();

		vehicleTypes = new VehicleType[getRandomInt(1, MapvehicleTypes.length)]; //  minVal = 1, to prevent 0
		
		ArrayList<Integer> randIndex = getRandomIntArray(0, MapvehicleTypes.length, vehicleTypes.length);

		for(int i=0;i<vehicleTypes.length;i++)
			vehicleTypes[i] = MapvehicleTypes[randIndex.get(i)];

		successMessage(toString());
	};

	

	/**
     * Road constructor
     * @param start
     * @param end
     * @param isEnable
     */
	public Road (Junction start, Junction end, boolean isEnable){
		enable = isEnable;
		start.addExitingRoad(this);
		end.addEnteringRoad(this);
		startJunction = start;
		endJunction = end;
		length = calcLength();
		maxSpeed = allowedSpeedOptions[getRandomInt(0, allowedSpeedOptions.length)];
		waitingVehicles = new ArrayList<>();

		vehicleTypes = new VehicleType[getRandomInt(1, VehicleType.values().length)]; //  minVal = 1, to prevent 0
		
		ArrayList<Integer> randIndex = getRandomIntArray(0, VehicleType.values().length, vehicleTypes.length);

		for(int i=0;i<vehicleTypes.length;i++)
			vehicleTypes[i] = VehicleType.values()[randIndex.get(i)];

		successMessage(toString());
	};
    
    /**
	 * add Vehicle To WaitingVehicles 
	 * @param vehicle
	 */
	public void  addVehicleToWaitingVehicles(Vehicle vehicle){ waitingVehicles.add(vehicle); }

	/**
	 * calc the estimated time on the road
	 * @param Object // Should be Vehicle 
	 */
    public double calcEstimatedTime(Object obj){
		if (obj instanceof Vehicle){
			Vehicle vehicle = (Vehicle) obj;
			if(vehicle.getVehicleType().getFixedAverageSpeed()<=vehicle.getLastRoad().getFixedMaxSpeed())
				return (double) Math.round(vehicle.getLastRoad().getLength()/vehicle.getVehicleType().getFixedAverageSpeed());
			else 
				return (double) Math.round(vehicle.getLastRoad().getLength()/vehicle.getLastRoad().getFixedMaxSpeed()); 
		}
		return -1;
	} 

	/**
	 * calc the road length
	 * @return double
	 */
	public double calcLength(){ return startJunction.calcDistance(endJunction); }
	
	/**
	 * check if the car can leave the road
	 * @param vehicle
	 */
    public boolean canLeave(Vehicle vehicle) {
		if(vehicle.getTimeOnCurrentPart() >= calcEstimatedTime(vehicle))
			return true;
		return false;
	}

	/**
	 * check in to the road
	 * @param vehicle
	 */
    public void checkIn(Vehicle vehicle){
		addVehicleToWaitingVehicles(vehicle);
		vehicle.setLastRoad(this);
		vehicle.setCurrentRoutePart(this);
		vehicle.setCurrentX(startJunction.getX());
		vehicle.setCurrentY(startJunction.getY());
		System.out.println("- is starting to move on " + this + ", time to finish: " + this.calcEstimatedTime(vehicle));
	}

	/**
	 * check out from the road
	 * @param vehicle
	 */
	@Override
	public void checkOut(Vehicle vehicle){
		removeVehicleFromWaitingVehicles(vehicle);
		System.out.println( vehicle + "\n- has finished " + this + ", time spent on the road: " + vehicle.getTimeOnCurrentPart());
		vehicle.setTimeOnCurrentPart(0);
		endJunction.checkIn(vehicle);
	}
	
	/**
	 * return the next RoutPart
	 * @param vehicle
	 */
    public RouteParts findNextPart(Vehicle vehicle){
		return endJunction;
	}

	/**
	 * remove vehicle from waiting vehicles ArrayList
	 * @param vehicle
	 */
	public void removeVehicleFromWaitingVehicles(Vehicle vehicle){ waitingVehicles.remove(vehicle); }
	
	/**
	 *
	 * @param vehicle
	 */
    public void stayOnCurrentPart(Vehicle vehicle){
		vehicle.setStatus("- is still moving on " + this + ", time to arrive: " + (this.calcEstimatedTime(vehicle) - vehicle.getTimeOnCurrentPart()));
		System.out.println(vehicle + "\n- is still moving on " + this + ", time to arrive: " + (this.calcEstimatedTime(vehicle) - vehicle.getTimeOnCurrentPart()));
	}
	
	/**
	 * calc the distance from the start of the road
	 * @param vehicle
	 * @return
	 */
	public double calcDistanceFromStart(Vehicle vehicle){
        return  Math.sqrt(Math.pow(this.startJunction.getX()-vehicle.getCurrentX(), 2) + Math.pow(this.startJunction.getY()- vehicle.getCurrentY(), 2));
	}
	
    /**
     * 
     * Setter&Getter for Road
     */
	public boolean isGreenlight() {
		return greenlight;
	}


	public void setGreenlight(boolean greenlight) {
		this.greenlight = greenlight;
	}


	public VehicleType[] getVehicleTypes() {
		return vehicleTypes;
	}


	public void setVehicleTypes(VehicleType[] vehicleTypes) {
		this.vehicleTypes = vehicleTypes;
	}


	public int [] getAllowedSpeedOptions() {
		return allowedSpeedOptions;
	}

	public boolean isEnable() {
		return enable;
	}


	public void setEnable(boolean enable) {
		this.enable = enable;
	}


	public Junction getStartJunction() {
		return startJunction;
	}


	public void setStartJunction(Junction startJunction) {
		this.startJunction = startJunction;
	}


	public Junction getEndJunction() {
		return endJunction;
	}


	public void setEndJunction(Junction endJunction) {
		this.endJunction = endJunction;
	}


	public double getLength() {
		return length;
	}


	public void setLength(double length) {
		this.length = length;
	}


	public int getMaxSpeed() {
		return maxSpeed;
	}

	public int getFixedMaxSpeed() {
		return maxSpeed/10;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}


	public ArrayList<Vehicle> getWaitingVehicles() {
		return waitingVehicles;
	}


	public void setWaitingVehicles(ArrayList<Vehicle> waitingVehicles) {
		this.waitingVehicles = waitingVehicles;
	}
    /***************************************/
	
	@Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Road)) {
            return false;
        }
		Road road = (Road) o;
		boolean IsEqual = true;
		if(waitingVehicles.size() == road.waitingVehicles.size())
		{
			for(int i=0;i<waitingVehicles.size();i++)
				if(!waitingVehicles.get(i).equals(road.waitingVehicles.get(i))){
					IsEqual = false;
					break;
				}
		}else return false;

		return enable == road.enable && startJunction.getJunctionName().equals(road.startJunction.getJunctionName()) && endJunction.getJunctionName().equals(road.endJunction.getJunctionName()) 
				&& greenlight == road.greenlight && length == road.length && maxSpeed == road.maxSpeed &&
				 vehicleTypes.equals(road.vehicleTypes) && IsEqual;
    }

	@Override
	public String toString() {
		return "Road from " + startJunction + " to " + endJunction + ", length: " + (int) length + " max speed " + maxSpeed;
	}

}
