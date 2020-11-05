package components;

import utilities.VehicleType;

/**
 * TwoWheelsVehicle - a factory for vehicle whith two wheels
 * @version 1.4 7 June 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see VehicleType
 * @see VehicleCatch
 * @see Factory
 */
public class TwoWheelsVehicle implements VehicleFactory{
    @Override
    public VehicleType getVehicle(String typeName){
        switch(typeName){
            case "fast": return VehicleType.motorcycle;
            case "slow": return VehicleType.bicycle;
            default: return null;
        }
    }
}
