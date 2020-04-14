
// Guy Cohen - 205579808, Yarin Abraham - 208401166

package components;

public class Vehicle{
	private int id;
	private VehicleType type;
	private int speed; //average speed for this type of vehicle.
	private Route currentRoute;
	private Junction lastJunction;//current junction or last junction where the vehicle visited.
	private Road lastRoad;
	private boolean moveNow;//True if the vehicle is on the road between the junctions.
	private double spentTime = 0;//time passed from the beginning of movement on the route.

	public Vehicle(int id,VehicleType type,Junction lastJunction){
		this.id = id;
		this.type = type;
		this.lastJunction = lastJunction;
		speed = type.getSpeed();
	}

	// getters

	public int getId() { return id; }

	public VehicleType getType() { return type; }

	public int getSpeed() { return speed; }

	public Route getCurrentRoute() { return currentRoute; }

	public Junction getLastJunction() { return lastJunction; }

	public Road getLastRoad() { return lastRoad; }

	public boolean getMoveNow(){ return moveNow; }

	public double getSpentTime() { return spentTime;}

	//setters

	public void setId(int id) { this.id = id; }

	public void setType(VehicleType type) { this.type = type; }

	public void setSpeed(int speed) { this.speed = speed; }

	public void setCurrentRoute(Route currentRoute) { this.currentRoute = currentRoute; }

	public void setLastJunction(Junction lastJunction) { this.lastJunction = lastJunction; }

	public void setLastRoad(Road lastRoad) { this.lastRoad = lastRoad; }

	public void setMoveNow(boolean moveNow) { this.moveNow = moveNow; }

	public void setSpentTime(double spentTime) { this.spentTime = spentTime; }

	// others
	public void move(){System.out.println("Waiting " + lastJunction.getDelay() + " seconds.");} // wait for the current point delay time and move to the next point of the route.(for now just print delay time)

	public void status(){
		System.out.println("Vehicle details: "+type.toString() + "\ncurrent position: "+ lastJunction.getLocation().toString() +"\nThe time spent on the route is - " + spentTime
						 +"First junction name: "+currentRoute.getStart().getJunctionName()+"\nLast junction name: " + currentRoute.getEnd().getJunctionName());
	} //prints the details about the vehicle including current position, time spent on the route and the first and last junctions on the route.

	public void checkIn(){
		currentRoute.calcDelay();
	}//if arrived to a junction, update the junction waiting list and calculate the delay time before the next move.

	public String toString() {
		return type.toString() + ", " + id;
	}
}
