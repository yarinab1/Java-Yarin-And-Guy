package components;

import java.util.EventListener;

/**
 * VehicleListener - a EventListener interface for vehicle (used by BigBrother)
 * @version 1.4 7 June 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see Factory
 */
public interface VehicleListener extends EventListener{
    
    /**
     * action for a VehicleEvent for vehicle that get to a junction
     * @param vehicle
     */
    public void arivedToJunction(VehicleEvent vehicle);
}
