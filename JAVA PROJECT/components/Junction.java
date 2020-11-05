package components;
import java.util.ArrayList;
import utilities.*;

/**
 * Junction - a RoutePart in a Route
 * @version 1.4 10 Jone 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see Point
 * @see RouteParts
 * @see Vehicle
 */

public class Junction extends Point implements RouteParts{
    private static int  objectsCount=1;
    private ArrayList<Road> enteringRoads;
    private ArrayList<Road> exitingRoads;
    private String junctionName;
   
    /**
     * default constructor
     */
    public Junction(){
    	super();
    	junctionName = "" + objectsCount;
    	objectsCount++;
    	enteringRoads = new ArrayList<>();
		exitingRoads = new ArrayList<>();
    };

    /**
     * 
     * Junction constructor
     * @param junctionName
     * @param x
     * @param y
     */
    Junction(String junctionName, double x, double y){
    	super(x,y);
		this.setJunctionName(junctionName);
    };
    
    /**
     * 
     * Setter&Getter for Junction
     */
	public static int getObjectsCount() {
		return objectsCount;
	}

	public static void setObjectsCount(int objectsCount) {
		Junction.objectsCount = objectsCount;
	}

	public String getJunctionName() {
		return junctionName;
	}

	public void setJunctionName(String junctionName) {
		this.junctionName = junctionName;
	}


	public ArrayList<Road> getEnteringRoads() {
		return this.enteringRoads;
	}

	public void setEnteringRoads(ArrayList<Road> enteringRoads) {
		this.enteringRoads = enteringRoads;
	}

	public ArrayList<Road> getExitingRoads() {
		return this.exitingRoads;
	}

	public void setExitingRoads(ArrayList<Road> exitingRoads) {
		this.exitingRoads = exitingRoads;
	}
    /***************************************/
    
    
	/**
	 * add road to EnteringRoad
	 * @param road
	 */
	public void addEnteringRoad(Road road){ enteringRoads.add(road); }

	/**
	 * add road to ExitingRoad
	 * @param road
	 */
	public void addExitingRoad(Road road){ exitingRoads.add(road); }
	
	/**
	 * 
	 * @param Object // Should be Vehicle
	 */
    public double calcEstimatedTime(Object obj){
			if (obj instanceof Vehicle){
				Vehicle vehicle = (Vehicle) obj;
				for(int i = 0; i<enteringRoads.size();i++)
					if(enteringRoads.get(i).equals(vehicle.getLastRoad()))
						return i+1;
			}
			return -1;
		}

    public boolean canLeave(Vehicle vehicle){
    	return checkAvailability(vehicle);
	};
	
    /**
     *
     * @param vehicle
     * @return true of false
     */
    public boolean checkAvailability(Vehicle vehicle){
		RouteParts tempRouteParts = vehicle.getCurrentRoutePart().findNextPart(vehicle);
		if(tempRouteParts != null && tempRouteParts instanceof Road && ((Road) tempRouteParts).getWaitingVehicles().size() > 0)
			if(((Road) tempRouteParts).getWaitingVehicles().get(0).equals(vehicle) && exitingRoads.size() != 0)
				return true;
    	return false;	
    }
  
    /**
     * check in vehicle to the junction
	 * @param vehicle
     * 
     */
    public void checkIn(Vehicle vehicle){
		vehicleEventCreateAndAct(vehicle);
		vehicle.setCurrentRoutePart(this);
		vehicle.setCurrentX(getX());
		vehicle.setCurrentY(getY());
		System.out.println("- has arrived to " + this);
	}
	
	/**
	 * create a VehicleEvent and react by bigBrother event action 
	 * @param vehicle
	 */
	public void vehicleEventCreateAndAct(Vehicle vehicle){
		VehicleEvent event = new VehicleEvent(vehicle);
		event.setRoadMaxSpeed(vehicle.getLastRoad().getFixedMaxSpeed());
		event.setVehicleAvarageSpeed(vehicle.getVehicleType().getFixedAverageSpeed());
		event.setVehicleId(vehicle.getVehicleId());
		event.setDrivingTime(Driving.getDrivingTime());
		BigBrother.getBigBrother().arivedToJunction(event);
	}
    /**
	 * check out vehicle to the junction
     * @param vehicle
     */
    public void checkOut(Vehicle vehicle){
		vehicle.setTimeOnCurrentPart(0);
		System.out.println(vehicle + "- has left the " + this);
    }

    /**
     *  @param vehicle
     *  @return Road Or Null
     */
    public RouteParts findNextPart(Vehicle vehicle){
    	ArrayList<Road> tempRoadList = new ArrayList<>();
    	for(Road road:exitingRoads)
    		for(int i=0;i<road.getVehicleTypes().length;i++)
				if(road.getVehicleTypes()[i].equals(vehicle.getVehicleType()) && road.isEnable())
				{
					if(vehicle.getCurrentRoute() != null && vehicle.getCurrentRoute().getRouteParts().contains(road) &&
						road.getStartJunction().equals(this) && road.getEndJunction().equals(vehicle.getLastRoad().getEndJunction()))
							return (RouteParts) road;
					tempRoadList.add(road);
				}
    	if(tempRoadList.size() != 0)
			return (RouteParts) tempRoadList.get(getRandomInt(0, tempRoadList.size()));
    	return null;
	}
	
    /**
     * @param vehicle
     */
	public void stayOnCurrentPart(Vehicle vehicle){
		vehicle.setStatus("- is waiting at "+ this +" - there are previous cars on the same road");
    	System.out.println(vehicle.toString() +"\n" + vehicle.getStatus());
    }

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Junction)) {
			return false;
		}
		Junction J = (Junction) obj;
		boolean roadsIsEqual = true;
		if(enteringRoads.size() == J.enteringRoads.size() && exitingRoads.size() == J.exitingRoads.size())
		{
			for(int i=0;i<enteringRoads.size();i++)
				if(!enteringRoads.get(i).equals(J.enteringRoads.get(i))){
					roadsIsEqual = false;
					break;
				}
			if(roadsIsEqual){
				for(int i=0;i<exitingRoads.size();i++)
					if(!exitingRoads.get(i).equals(J.exitingRoads.get(i))){
						roadsIsEqual = false;
						break;
					}
			}
		}else return false;

		return junctionName == J.junctionName && super.equals(J) && roadsIsEqual;
	}

	@Override
	public String toString()
	{
		return "Junction " + junctionName;
	}
}