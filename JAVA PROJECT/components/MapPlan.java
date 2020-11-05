package components;

import java.util.ArrayList;

/**
 * MapPlan - an interface to Map
 * @version 1.4 7 Jone 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see CityBuilder
 * @see CountryBuilder
 * @see MapCreator
 * @see MapBuilder
 * @see Map
 */

public interface MapPlan{
    /**
     * junction setter
     * @param junctions
     */
    public void setJunctions(ArrayList<Junction> junctions);
    
    /**
     * roads setter
     * @param junctions
     */
    public void setRoads(ArrayList<Road> roads);
    
    /**
     * traffic lights setter
     * @param junctions
     */
    public void setLights(ArrayList<TrafficLights> lights) ;
}