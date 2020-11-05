package components;

import java.util.ArrayList;

/**
 * 
 * @version 1.3 29 april 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 *@see TrafficLights
 */

public class RandomTrafficLights extends TrafficLights{
    /**
     * constructor
     * @param roads
     */
    public RandomTrafficLights(ArrayList<Road> roads){
        super(roads);
    };

    @Override
    public void changeIndex() { super.setGreenLightIndex(getRandomInt(0, super.getRoads().size())); }

    @Override
    public String toString() {
        return "Random " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RandomTrafficLights))
            return false;
        return super.equals((RandomTrafficLights) o) ;
    }
}