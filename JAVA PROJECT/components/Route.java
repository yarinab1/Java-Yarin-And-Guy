package components;
import java.util.ArrayList;

/**
 * 
 * @version 1.3 29 april 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 *@see RouteParts
 */

public class Route implements RouteParts{
    private ArrayList<RouteParts> RouteParts = new ArrayList<>();
    private Vehicle vehicle;

    /**
     * Route constructor
     * @param start
     * @param vehicle
     */
    public Route(RouteParts start, Vehicle vehicle){
        this.vehicle = vehicle;
        RouteParts.add(start);
        RouteParts tempRoutPart = RouteParts.get(RouteParts.size()-1).findNextPart(vehicle);
        while(tempRoutPart != null && RouteParts.size() < 10){
            RouteParts.add(tempRoutPart);
            tempRoutPart = RouteParts.get(RouteParts.size()-1).findNextPart(vehicle);
        }
        checkIn(this.vehicle);
    }

    /**
     * calc the estimated time 
     * @return the time
     */
    @Override
    public double calcEstimatedTime(Object obj){
        if (obj instanceof Vehicle){
            Vehicle vehicle = (Vehicle) obj;
            double estimatedTime = 0;
            for(RouteParts tempRoutePart: RouteParts)
                estimatedTime += tempRoutePart.calcEstimatedTime(vehicle);
            return estimatedTime;
        }
        return -1;
    };
    
    /**
     * check if the car can leave in Route
     * return True Or Flase
     */
    public boolean canLeave(Vehicle vehicle){
        if(vehicle.getCurrentRoutePart().equals(RouteParts.get(RouteParts.size()-1)))
            return true;
        return false;
    };

    /**
	 * return the next RoutPart
	 * @param vehicle
	 */
    @Override
    public RouteParts findNextPart(Vehicle vehicle) {
        if(canLeave(vehicle)){
            if(vehicle.getCurrentRoutePart().findNextPart(vehicle) == null)
                return (new Route(RouteParts.get(0), vehicle)).RouteParts.get(0);
            else
                return (new Route(vehicle.getCurrentRoutePart(), vehicle)).RouteParts.get(0);
        }
        else{
            RouteParts routePart = null;
            int i=0;
            do{
                if(RouteParts.get(i).equals(vehicle.getCurrentRoutePart()))
                    routePart = RouteParts.get(i+1);
                i++;
            }while(routePart == null);
            return routePart;
        }
        
    }

    
    /**
     * Check in to the Route
     * @param vehicle
     */
    @Override
    public void checkIn(Vehicle vehicle) {
        vehicle.setCurrentRoute(this);
        vehicle.setCurrentRoutePart(RouteParts.get(0));
        vehicle.setTimeFromRouteStart(0);
        if(RouteParts.get(0) instanceof Road)
            vehicle.setLastRoad((Road) RouteParts.get(0));
        System.out.println("- is starting a new " + vehicle.getCurrentRoute());
    }

    
    /**
	 * check out from the route
	 * @param vehicle
	 */
    @Override
    public void checkOut(Vehicle vehicle) {
        vehicle.setStatus(null);
        System.out.println("- has finished the " + this + " Time spent on the route: " + vehicle.getTimeFromRouteStart());
    }

    /**
	 *
	 * @param vehicle
	 */
    @Override
    public void stayOnCurrentPart(Vehicle vehicle) {
        System.out.println("- is still moving on " + this);
    }

    /**
     * get a random road from roads
     * @return a random road from roads
     */
    public Road getRandomeRoad(){
        ArrayList<Road> roads = new ArrayList<>();
        for(RouteParts tempRP: RouteParts)
            if(tempRP instanceof Road)
                roads.add((Road) tempRP);
        return roads.get(getRandomInt(0, roads.size()));
                
    }

    /**
     * 
     * Setter&Getter for Route
     */
	public ArrayList<RouteParts> getRouteParts() {
		return RouteParts;
    }
    
	public void setRouteParts(ArrayList<RouteParts> routeParts) {
		RouteParts = routeParts;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
    }
    /***************************************/

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Route)) {
            return false;
        }
        Route route = (Route) o;
        
        boolean IsEqual = true;
		if(RouteParts.size() == route.RouteParts.size())
		{
			for(int i=0;i<RouteParts.size();i++)
				if(!RouteParts.get(i).equals(route.RouteParts.get(i))){
					IsEqual = false;
					break;
                }
                return IsEqual && vehicle.equals(route.vehicle);
		}else return false;
    }

    @Override
    public String toString() {
        return "Route from " + RouteParts.get(0) + " to " + RouteParts.get(RouteParts.size()-1) + ", estimated time for route: " + calcEstimatedTime(vehicle) + ".";
    }
}