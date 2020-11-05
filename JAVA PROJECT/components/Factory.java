package components;

/**
 * Factory - a factory abstract class for VehicleFactory (number of wheels)
 * @version 1.4 7 June 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see TwoWheelsVehicle
 * @see FourWheelsVehicle
 * @see TenWheelsVehicle
 * @see VehicleCatch
 * @see VehicleFactory
 */

public abstract class Factory {
    public static VehicleFactory getFactory(int numberOfWheels){
        switch(numberOfWheels){
            case 2: return new TwoWheelsVehicle();
            case 4: return new FourWheelsVehicle();
            case 10: return new TenWheelsVehicle();
            default: return null;
        }
    }
}