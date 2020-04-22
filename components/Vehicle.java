
// Guy Cohen - 205579808, Yarin Abraham - 208401166

package components;

public class Vehicle{
	private int id;
	private VehicleType type;
	private int speed; //average speed for this type of vehicle.
	private Route currentRoute;
	private Junction lastJunction;//current junction or last junction where the vehicle visited.
	private Road lastRoad;
	private boolean moveNow = false;//True if the vehicle is on the road between the junctions.
	private double spentTime = 0;//time passed from the beginning of movement on the route.
	
	//Vehicle constructor
	public Vehicle(int id,VehicleType type,Junction lastJunction){
		this.id = id;
		this.type = type;
		this.lastJunction = lastJunction;
		speed = type.getSpeed();
		System.out.println(toString() + " has been created and placed at junction " + lastJunction.getJunctionName());
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

	public void setCurrentRoute(Route currentRoute) { this.currentRoute = currentRoute; spentTime = this.currentRoute.getDelay(); }

	public void setLastJunction(Junction lastJunction) { this.lastJunction = lastJunction; }

	public void setLastRoad(Road lastRoad) { this.lastRoad = lastRoad; }

	public void setMoveNow(boolean moveNow) { this.moveNow = moveNow; }

	public void setSpentTime(double spentTime) { this.spentTime = spentTime; }

	// others
	public void move(){
		
			if(currentRoute.getStart().getVehicles().isEmpty() || currentRoute.getStart().equals(currentRoute.getEnd())){
				System.out.println(this + " stays at Junction " + currentRoute.getStart().getJunctionName() + " - no exiting roads.");
			}else {
				/*if(currentRoute.getStart().checkAvailability(lastRoad))
					System.out.println(this + " waiting for his priority at Junction " + this.getLastJunction().getJunctionName() + ".");*/
				if(lastJunction.equals(currentRoute.getStart()))
					System.out.println(this + " is starting route from Junction " + currentRoute.getStart().getJunctionName() + " to Junction " + currentRoute.getEnd().getJunctionName());
				
				if(lastRoad.getLight())
				{
					System.out.println(this + " has left Junction " + lastJunction.getJunctionName() + ".");
					lastJunction.removefromVehicles(lastRoad);
					if(currentRoute.getRoads().size()-1 != currentRoute.getRoads().indexOf(lastRoad)){
						
						System.out.println(this + " is moving on Road from " + lastRoad.getFromJunc().getJunctionName() + " to " + lastRoad.getToJunc().getJunctionName() + ". Delay time: " + (double) lastRoad.getToJunc().getDelay() + ".");
						lastJunction = lastRoad.getToJunc();
						checkIn();
						setLastRoad(currentRoute.getNextRoad(lastRoad));
						moveNow = true;
						lastJunction.addToVehicles(lastRoad);
					}else{
						checkIn();
						moveNow = false;
					}
				}else{
					moveNow = false;
					System.out.println(this + " is waiting for a green light at Junction " + currentRoute.getStart().getJunctionName() + ".");
				}
			}
	} // wait for the current point delay time and move to the next point of the route.(for now just print delay time)

	public void status(){
		System.out.println(toString() + ". Position: Junction "+ lastJunction.getJunctionName() + ". Current Route: from Junction " + currentRoute.getStart().getJunctionName()+" to Junction " 
		+ currentRoute.getEnd().getJunctionName() + ". Time spent on the route is - " + spentTime);
		
	} //prints the details about the vehicle including current position, time spent on the route and the first and last junctions on the route.

	public void checkIn(){
		System.out.println(this + " has arrived to Junction " + currentRoute.getEnd().getJunctionName() + ".");
		spentTime += currentRoute.getDelay();
		currentRoute.calcDelay();
	}//if arrived to a junction, update the junction waiting list and calculate the delay time before the next move.

	public String toString() {
		return type.toString() + ",  ID: " + id;
	}
}
