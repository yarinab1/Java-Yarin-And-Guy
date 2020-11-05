package components;

import java.util.ArrayList;
import utilities.*;

/**
 * 
 * @version 1.3 29 april 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see Timer
 * @see Utilities
 * @see LightedJunction
 */
public abstract class TrafficLights extends Thread implements Timer, Utilities,Runnable{
    private static int objectsCount = 1;
    private int delay = 0;
    private int greenLightIndex = -1;
    private int id;
    private final int minDelay = 2;
    private final int maxDelay = 6;
    private ArrayList<Road> roads = new ArrayList<>();
    private boolean trafficLightsOn = false;
	private int workingTime = 0;

    /**
     * TrafficLights constructor
     * @param roads
     */
    public TrafficLights(ArrayList<Road> roads){
		id = objectsCount;
		objectsCount++;
		this.roads = roads;
		changeIndex();
	};

    public abstract void changeIndex();
    
    /**
     * Change the Traffic Light in the Junction
     */
    public void changeLights(){
		changeIndex();
		for(int i=0;i<roads.size();i++)
			if(i==greenLightIndex){
				roads.get(i).setGreenlight(true);
				System.out.println(toString() + " turned ON, delay time: " + delay);
				System.out.println("- " + roads.get(greenLightIndex) + ": green light.\n");
			}
			else 
				roads.get(i).setGreenlight(false);
	};

	@Override
	public void run() {
		try{
			Thread.sleep(delay*100);
			changeLights();
		}catch(InterruptedException e){}
	}

	/**
	 * increment time to this Driving
	 */
    public void incrementDrivingTime() { 
		if(delay > maxDelay || delay < minDelay){
			delay = getRandomInt(minDelay, maxDelay);
			/*changeLights(); - from past virsion*/
		}else if(workingTime != delay){
			System.out.println(this);
			System.out.println("- on delay\n");
			workingTime++;
		}else { workingTime = 0; /*changeLights(); - from past virsion*/ }
	}

    /**
     * 
     * Setter&Getter for TrafficLights
     */
	public int getObjectsCount() {
		return objectsCount;
	}

	public int getGreenLightIndex() {
		return greenLightIndex;
	}

	public void setGreenLightIndex(int greenLightIndex) {
		this.greenLightIndex = greenLightIndex;
	}

	public int getTrafficLightsId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Road> getRoads() {
		return roads;
	}

	public void setRoads(ArrayList<Road> roads) {
		this.roads = roads;
	}

	public int getDelay() {
		return this.delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getMinDelay() {
		return this.minDelay;
	}


	public int getMaxDelay() {
		return this.maxDelay;
	}

	public boolean isTrafficLightsOn() {
		return trafficLightsOn;
	}

	public void setTrafficLightsOn(boolean trafficLightsOn) {
		if(trafficLightsOn){
			delay = getRandomInt(minDelay, maxDelay);
			changeLights();
		}
		else
			delay = 0;
		this.trafficLightsOn = trafficLightsOn;
	}

	public int getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(int workingTime) {
		this.workingTime = workingTime;
	}
    /***************************************/
	
	@Override
	public String toString() {
		return "trafic lights " + id;
	}


	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof TrafficLights)) {
			return false;
		}
		TrafficLights trafficLights = (TrafficLights) o;

		boolean IsEqual = true;
		if(roads.size() == trafficLights.roads.size())
		{
			for(int i=0;i<roads.size();i++)
				if(!roads.get(i).equals(trafficLights.roads.get(i))){
					IsEqual = false;
					break;
				}
		}else return false;

		return delay == trafficLights.delay && greenLightIndex == trafficLights.greenLightIndex &&
				 id == trafficLights.id && minDelay == trafficLights.minDelay && maxDelay == trafficLights.maxDelay &&
				  IsEqual && trafficLightsOn == trafficLights.trafficLightsOn && workingTime == trafficLights.workingTime;
	}

}
