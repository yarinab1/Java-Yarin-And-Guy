package components;

import utilities.VehicleType;

/**
 * TenWheelsVehicle - a factory for vehicle whith ten wheels
 * @version 1.4 7 June 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see VehicleType
 * @see VehicleCatch
 * @see Factory
 */
public class TenWheelsVehicle implements VehicleFactory{
    @Override
    public VehicleType getVehicle(String typeName){
        switch(typeName){
            case "public": return VehicleType.tram;
            case "work": return VehicleType.semitrailer;
            default: return null;
        }
    }

}
