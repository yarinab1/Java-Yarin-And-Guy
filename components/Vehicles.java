package components;

public class Vehicles{
	private int id;
	private String type;
	private int speed; //average speed for this type of vehicle.
	private Route currentRoute;
	private Junction lastJunction;//current junction or last junction where the vehicle visited.
	private Road lastRoad;
	private boolean moveNow;//True if the vehicle is on the road between the junctions.
	private double spentTime;//time passed from the beginning of movement on the route.

	public Vehicles(int id,String type,Junction lastJunction){
		this.id = id;
		this.type = type;
		this.lastJunction = lastJunction;
	}

	// getters

	public int getId() { return id; }

	public String getType() { return type; }

	public int getSpeed() { return speed; }

	public Route getCurrentRoute() { return currentRoute; }

	public Junction getLastJunction() { return lastJunction; }

	public Road getLastRoad() { return lastRoad; }

	public boolean getMoveNow(){ return moveNow; }

	public double getSpentTime() { return spentTime;}

	//setters

	public void setId(int id) { this.id = id; }

	public void setType(String type) { this.type = type; }

	public void setSpeed(int speed) { this.speed = speed; }

	public void setCurrentRoute(Route currentRoute) { this.currentRoute = currentRoute; }

	public void setLastJunction(Junction lastJunction) { this.lastJunction = lastJunction; }

	public void setLastRoad(Road lastRoad) { this.lastRoad = lastRoad; }

	public void setMoveNow(boolean moveNow) { this.moveNow = moveNow; }

	public void setSpentTime(double spentTime) { this.spentTime = spentTime; }

	// others
	void move(){} // wait for the current point delay time and move to the next point of the route.
	void status(){} //prints the details about the vehicle including current
					//position, time spent on the route and the first and last junctions
					//on the route.
	void checkIn(){
		
	}//if arrived to a junction, update the junction waiting list
					//and calculate the delay time before the next move.
	
}
