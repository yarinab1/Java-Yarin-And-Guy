package components;

import java.util.ArrayList;

/**
 * 
 * @version 1.3 29 april 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 *@see TrafficLights
 */

public class SequentialTrafficLights extends TrafficLights{
    private static int increment = 1;
    
    /**
     * SequentialTrafficLights constructor
     * @param roads
     */
    public SequentialTrafficLights (ArrayList<Road> roads){
        super(roads);
    }
    
    /**
     * change the index of the green lighted road
     */
    @Override
    public void changeIndex(){ 
        if(super.getGreenLightIndex() < super.getRoads().size())
            super.setGreenLightIndex(super.getGreenLightIndex() + increment);
        else
            super.setGreenLightIndex(0);
    }

    @Override
    public String toString() {
        return "Sequential " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SequentialTrafficLights))
            return false;
        return super.equals(o);
    }
}