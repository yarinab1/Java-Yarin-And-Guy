package components;

import utilities.VehicleType;

/**
 * FourWheelsVehicle - a factory for vehicle whith four wheels
 * @version 1.4 7 June 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see VehicleType
 * @see VehicleCatch
 * @see Factory
 */

public class FourWheelsVehicle  implements VehicleFactory{
    @Override
    public VehicleType getVehicle(String typeName){
        switch(typeName){
            case "private": return VehicleType.car;
            case "work": return VehicleType.truck;
            case "public": return VehicleType.bus;
            default: return null;
        }
    }

}
