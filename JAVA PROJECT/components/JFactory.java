package components;

import java.util.Random;

/**
 * JFactory - a factory for junction by type of map
 * @version 1.4 7 June 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see CityBuilder
 * @see CountryBuilder
 * @see Junction
 * @see LightedJunction
 */

public class JFactory{
    public static Junction getJunctiom(String x){
        switch(x){
            case "city": return new LightedJunction();
            case "country": if(new Random().nextBoolean()) return new LightedJunction();
            default: return new Junction();
        }
    }
}