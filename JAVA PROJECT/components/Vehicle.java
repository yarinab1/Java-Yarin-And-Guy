package components;
import java.awt.Color;

import utilities.*;

/**
 * 
 * @version 1.3 29 april 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 *@see Utilities
 *@see Timer
 */
public class Vehicle extends Thread implements Utilities, Timer{
    private int id;
    private VehicleType vehicleType;
    private Route currentRoute;
    private RouteParts currentRoutePart;
    private int timeFromRouteStart = 0;
    private int timeOnCurrentPart = 0;
    private static int objectsCount = 1;
    private Road lastRoad;
    private String status = null;
    private double currentX;
    private double currentY;
    private Color vehicleColor = Color.blue;
    private boolean isStoped = false; // indicate the run() - while
    private BigBrother bigBrother;

    /**
     * Vehicles constructor
     * @param road
     */
    public Vehicle(Road road){
        super();
        id = objectsCount;
        objectsCount++;
        lastRoad = road;
        vehicleType = road.getVehicleTypes()[getRandomInt(0,road.getVehicleTypes().length)];
        successMessage(toString());
        currentRoute = new Route(road,this);
        currentX = ((Road)currentRoutePart).getStartJunction().getX();
        currentY = ((Road)currentRoutePart).getStartJunction().getY();
        bigBrother = BigBrother.getBigBrother();
    }

    /**
     * Vehicle constructor
     * @param road
     * @param vehicleType
     */
    public Vehicle(Road road, VehicleType vehicleType){
        super();
        id = objectsCount;
        objectsCount++;
        lastRoad = road;
        this.vehicleType = vehicleType;
        successMessage(toString());
        currentRoute = new Route(road,this);
        currentX = ((Road)currentRoutePart).getStartJunction().getX();
        currentY = ((Road)currentRoutePart).getStartJunction().getY();
        bigBrother = BigBrother.getBigBrother();
    }

    /**
     * Vehicle copy constructor
     * @param road
     */
    public Vehicle(Vehicle other){
        super();
        id = other.getVehicleId();
        vehicleType = other.getVehicleType();
        currentRoute = other.currentRoute;
        currentRoutePart = other.getCurrentRoutePart();
        timeFromRouteStart = other.getTimeFromRouteStart();
        timeOnCurrentPart = other.getTimeOnCurrentPart();
        lastRoad = other.getLastRoad();
        status = other.getStatus();
        currentX = other.getCurrentX();
        currentY = other.getCurrentY();
        vehicleColor = other.vehicleColor;
        isStoped = other.isStoped; // indicate the run() - while
        bigBrother = other.bigBrother;
    }

    /**
     * move the car on the map
     */
    public void move(){
        if(currentRoutePart.canLeave(this)){
            currentRoutePart.checkOut(this);
            if(currentRoute.getRouteParts().get(0).equals(currentRoute)) // if currentRoaut has changed
                return;
            else currentRoute.findNextPart(this).checkIn(this); // at junction checkIn - create a VehicleEvent and react by bigBrother event action 
        }
        else
            currentRoutePart.stayOnCurrentPart(this);
    }

    @Override
    public void run() {
        while(!isStoped){
            if(currentRoutePart instanceof Road)
            {
                Road tempRoad = (Road) currentRoutePart;
                if(tempRoad.calcDistanceFromStart(this) < tempRoad.getLength() - 30){
                    double deltaX = tempRoad.getEndJunction().getX()-tempRoad.getStartJunction().getX(), deltaY = tempRoad.getEndJunction().getY()-tempRoad.getStartJunction().getY();
                    double d,newX,newY;
                    if(getVehicleType().getFixedAverageSpeed()<=getLastRoad().getFixedMaxSpeed())
                            d = getTimeOnCurrentPart()*getVehicleType().getFixedAverageSpeed();
                        else 
                            d = getTimeOnCurrentPart()*getLastRoad().getFixedMaxSpeed();
                    
                    newX = tempRoad.getStartJunction().getX()+(deltaX/tempRoad.getLength())*d;
                    newY = tempRoad.getStartJunction().getY()+(deltaY/tempRoad.getLength())*d;
                    setCurrentX(newX);
                    setCurrentY(newY);
                }
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {}
        }
    }

    /**
     * increment the driving time
     */
    @Override
    public void incrementDrivingTime(){ timeFromRouteStart++; timeOnCurrentPart++; move(); }

    /**
     * resume Thread
     */
    public synchronized void resumeVehicleThread(){ notify(); }

    /**
     * stop Thread
     */
    public synchronized void stopVehicleThread(){ try{ wait(); }catch(InterruptedException e){} }

    /**
     * isStop setter
     * @param isStoped
     */
    public void setStoped(boolean isStoped) {
        this.isStoped = isStoped;
    }

    /**
     * vehicleColor getter
     * @return vehicleColor
     */
    public Color getVehicleColor() {
        return vehicleColor;
    }
    
    /**
     * vehicleColor setter
     * @param vehicleColor
     */
    public void setVehicleColor(Color vehicleColor) {
        this.vehicleColor = vehicleColor;
    }
    /**
     * @return the id (vehicle)
     */
    public int getVehicleId() {
        return id;
    }
    
    /**
     * id setter (Thread)
     * @param id
     */
    public void setId(int id)
    {
    	this.id=id;
    }

    /**
     * @return the vehicleType
     */
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    /**
     * @return the currentRoute
     */
    public Route getCurrentRoute() {
        return currentRoute;
    }

    /**
     * @return the currentRoutePart
     */
    public RouteParts getCurrentRoutePart() {
        return currentRoutePart;
    }
    
    /**
     * currentRoutePart setter
     * @param routePart
     */
    public void setCurrentRoutePart(RouteParts routePart)
    {
        currentRoutePart=routePart;
    }

    /**
     * @return the timeFromRouteStart
     */
    public int getTimeFromRouteStart() {
        return timeFromRouteStart;
    }

    /**
     * @return the timeOnCurrentPart
     */
    public int getTimeOnCurrentPart() {
        return timeOnCurrentPart;
    }
    /**
     * @return the objectsCount
     */
    public int getObjectsCount() {
        return objectsCount;
    }
    
    /**
     * objectCount setter
     * @param obj
     */
    public void setObjectsCount(int obj)
    {
    	Vehicle.objectsCount=obj;
    }
    /**
     * last road getter
     * @return the lastRoad
     */
    public Road getLastRoad() {
        return lastRoad;
    } 
    
    /**
     * last road setter
     * @param road
     */
	public void setLastRoad(Road road) {
		lastRoad=road;
	}

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getCurrentX() {
        return this.currentX;
    }

    public void setCurrentX(double currentX) {
        this.currentX = currentX;
    }

    public double getCurrentY() {
        return this.currentY;
    }

    public void setCurrentY(double currentY) {
        this.currentY = currentY;
    }
    
    
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    public void setStatus(String Status) {
    	this.status=Status;
    }
    
    /**
     * @param timeFromRouteStart the timeFromRouteStart to set
     */
    public void setTimeFromRouteStart(int timeFromRouteStart) {
        this.timeFromRouteStart = timeFromRouteStart;
    }

    /**
     * @param timeOnCurrentPart the timeOnCurrentPart to set
     */
    public void setTimeOnCurrentPart(int timeOnCurrentPart) {
        this.timeOnCurrentPart = timeOnCurrentPart;
    }

    /**
     * @param currentRoute the currentRoute to set
     */
    public void setCurrentRoute(Route currentRoute) {
        this.currentRoute = currentRoute;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Vehicle)) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id && vehicleType.equals(vehicle.vehicleType) && currentRoute.equals(vehicle.currentRoute) 
                && currentRoutePart.equals(vehicle.currentRoutePart) && timeFromRouteStart == vehicle.timeFromRouteStart 
                && timeOnCurrentPart == vehicle.timeOnCurrentPart && lastRoad.equals(vehicle.lastRoad) && status.equals(vehicle.status);
    }


    @Override
    public String toString() {
        return "Vehicle "+ id +": " + vehicleType.name() + " , average speed: " + vehicleType.getAverageSpeed();
    }

	@Override
    protected Object clone() throws CloneNotSupportedException {
        return (Vehicle) new Vehicle(this);
    }
}