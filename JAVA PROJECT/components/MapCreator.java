package components;

/**
 * MapCreator - create map by getting a builder
 * @version 1.4 7 Jone 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see CityBuilder
 * @see CountryBuilder
 * @see MapBuilder
 */

public class MapCreator {
    private MapBuilder mapBuilder;
    
    /**
     * get a MapBuilder to create a map by.
     * @param mapBuilder
     */
    public MapCreator(MapBuilder mapBuilder) {
      this.mapBuilder = mapBuilder;
    }

    /**
     * 
     * @return - reqested map
     */
    public Map getMap() { 
		  return mapBuilder.getMap(); 
    } 
    
    /**
     * construct map by MapBuilder functions
     */
    public void constructMap() { 
      mapBuilder.buildJunctions();
      mapBuilder.buildRoads();
      mapBuilder.bulidVehicles(); 
    } 
}