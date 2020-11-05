package components;
import java.util.ArrayList;
import utilities.*;

/**
 * Map - a map in driving game
 * @version 1.4 7 Jone 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see Utilities
 * @see MapPlan
 */

public class Map implements Utilities,MapPlan{
    private ArrayList<Junction> junctions = new ArrayList<>();
    private ArrayList<Road> roads = new ArrayList<>();
    private ArrayList<TrafficLights> lights = new ArrayList<>();
    private ArrayList<Vehicle> vehicles = null; // to set and pass to Driving class in case of CountryMap or CityMap - initialize at default constructor that
                                                // calld by CountryMap or CityMap builder

    /**
     * Map constructor
     */
    public Map(){ vehicles = new ArrayList<>(); }

    /**
     * Map constructor
     * @param numOfJunctions
     */
    public Map (int numOfJunctions){
        System.out.println("================= CREATING JUNCTIONS =================");
        for(int i=0;i<numOfJunctions;i++)
        {
            boolean isLighted = getRandomBoolean();
            if(isLighted)
                junctions.add(new LightedJunction());
            else
                junctions.add(new Junction()); 
            successMessage(junctions.get(i).toString());
        }
        SetAllRoads();
        turnLightsOn();
        System.out.println("\n================= GAME MAP HAS BEEN CREATED =================\n\n");       
    }
    
	/**
	 * Set all the Roads in Map
	 */
    public void SetAllRoads(){
        System.out.println("\n================= CREATING ROADS =================");
        for(int i=0;i<junctions.size();i++)
            for(int j=0;j<junctions.size();j++)
                if(i!=j)
                    roads.add(new Road(junctions.get(i), junctions.get(j)));
    };

    /**
     * turn light of traffic on
     */
    public void turnLightsOn(){
        System.out.println("\n================= TRAFFIC LIGHTS TURN ON =================");
        for(Junction junction: junctions)
            if(junction instanceof LightedJunction)
                ((LightedJunction) junction).getLights().setTrafficLightsOn(getRandomBoolean()); 
    };

    /**
     * 
     * Setter&Getter for Map
     */
    public ArrayList<Junction> getJunctions() {
        return this.junctions;
    }

    public void setJunctions(ArrayList<Junction> junctions) {
        this.junctions = junctions;
    }

    public ArrayList<Road> getRoads() {
        return this.roads;
    }

    public void setRoads(ArrayList<Road> roads) {
        this.roads = roads;
    }

    public ArrayList<TrafficLights> getLights() {
        return this.lights;
    }

    public void setLights(ArrayList<TrafficLights> lights) {
        this.lights = lights;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
    /***************************************/
    
    @Override
    public String toString() {
        return "{" +
            " junctions='" + junctions + "'" +
            ", roads='" + roads + "'" +
            ", lights='" + lights + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Map)) {
            return false;
        }
        Map map = (Map) o;

		boolean IsEqual = true;
		if(junctions.size() == map.junctions.size() && roads.size() == map.roads.size())
		{
			for(int i=0;i<junctions.size();i++)
				if(!junctions.get(i).equals(map.junctions.get(i))){
					IsEqual = false;
					break;
				}
			if(IsEqual){
				for(int i=0;i<roads.size();i++)
					if(!roads.get(i).equals(map.roads.get(i))){
						IsEqual = false;
						break;
                    }
            if(IsEqual){
                for(int i=0;i<lights.size();i++)
                    if(!lights.get(i).equals(map.lights.get(i))){
                        IsEqual = false;
                        break;
                    }
                }
            }else return false;

            return IsEqual;
        }
        return false;
    }
}