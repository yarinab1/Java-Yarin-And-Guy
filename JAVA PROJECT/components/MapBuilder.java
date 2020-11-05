package components;

/**
 * MapBuilder - an interface to CityBuilder and CountryBuilder
 * @version 1.4 7 Jone 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see CityBuilder
 * @see CountryBuilder
 * @see MapCreator
 */

public interface MapBuilder {
    /**
     * build junctions
     */
    public void buildJunctions(); 

    /**
     * build roads
     */
    public void buildRoads();
    
    /**
     * build vehicles
     */ 
    public void bulidVehicles(); 

    /**
     * return the reqested map
     */
    public Map getMap(); 
}