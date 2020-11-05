package components;

import utilities.Utilities;

/**
 * 
 * @version 1.3 29 april 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 *@see Utilities
 */

public interface RouteParts extends Utilities{
    /**
     * calc estimated time on current route part
     * @param obj
     * @return double
     */
    public double calcEstimatedTime(Object obj);
    /**
     * check if the vehicle can leav
     * @param vehicle
     * @return boolean
     */
    public boolean canLeave(Vehicle vehicle);
    /**
     * do check in to vehicle
     * @param vehicle
     */
    public void checkIn(Vehicle vehicle); 
    /**
     * do check out to vehicle
     * @param vehicle
     */
    public void checkOut(Vehicle vehicle); 
    /**
     * find the next route part for the vehicle
     * @param vehicle
     * @return RouteParts
     */
    public RouteParts findNextPart(Vehicle vehicle);
    /**
     * print resone if vehicle stay on current route part 
     * @param vehicle
     */
    public void stayOnCurrentPart(Vehicle vehicle);
}
