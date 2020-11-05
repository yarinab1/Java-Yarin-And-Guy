package components;

/**
 * LightedJunction - a Junction that have a TrafficLights (RoutePart) in a Route
 * @version 1.3 29 april 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see Jucntion
 * @see TrafficLights
 */

public class LightedJunction extends Junction{
    private TrafficLights lights;

    /**
     * LightedJunction default constructor
     */
    public LightedJunction(){
		super();
		boolean isRandom = getRandomBoolean(); // choose randomly the TrafficLights type (true-> RandomTrafficLights, false-> SequentialTrafficLights)
		if(isRandom)
			lights = new RandomTrafficLights(super.getEnteringRoads());
		else
			lights = new SequentialTrafficLights(super.getEnteringRoads());
	};

	/**
	 * LightedJunction constructor
	 * @param name
	 * @param x
	 * @param y
	 * @param sequential
	 * @param lightsOn
	 */
	public LightedJunction(String name, double x, double y, boolean sequential, boolean lightsOn){
		super(name, x, y);
		if(sequential)
			lights = new SequentialTrafficLights(super.getEnteringRoads());
		else
			lights = new RandomTrafficLights(super.getEnteringRoads());
		
		lights.setTrafficLightsOn(lightsOn);
	}

	@Override
    public double calcEstimatedTime(Object obj){
		return lights.getDelay()*(super.getEnteringRoads().size()-1) + 1;
	}

	@Override
    public boolean canLeave(Vehicle vehicle){
    	if(lights.isTrafficLightsOn() && super.getExitingRoads().size() != 0)
    		return true;
    	return false;
    }

	/**
	 * 
	 * @return lights
	 */
	public TrafficLights getLights() {
		return lights;
	}

	/**
	 * 
	 * @param lights
	 */
	public void setLights(TrafficLights lights) {
		this.lights = lights;
	}
	

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof LightedJunction)) {
			return false;
		}
		LightedJunction lightedJunction = (LightedJunction) o;
		return lights.equals(lightedJunction.lights);
	}
	
	@Override
	public String toString() {
		return "Junction "+ super.getJunctionName() + " (Lighted)" ;
	}
}