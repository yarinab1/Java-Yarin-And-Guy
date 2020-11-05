package components;

import utilities.VehicleType;

/**
 * VehicleFactory - a factory interface for Vehicle
 * @version 1.4 7 June 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see VehicleType
 * @see Factory
 */

public interface VehicleFactory {
    /**
     * get name of VehicleType and return the match VehicleType object
     * @param typeName
     * @return match VehicleType object
     */
    public VehicleType getVehicle(String typeName);
}