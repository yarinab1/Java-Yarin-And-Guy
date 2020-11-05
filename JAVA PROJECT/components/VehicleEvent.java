package components;

import java.util.EventObject;

/**
 * VehicleEvent - an vehicle EventObject
 * @version 1.4 7 June 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see Vehicle
 * @see BigBrother
 * @see Junction
 */

public class VehicleEvent extends EventObject{
    private int vehicleId;
    private int vehicleAvarageSpeed;
    private int roadMaxSpeed;
    private int drivingTime;

    /**
     * constructor
     * @param source
     */
    public VehicleEvent(Object source) {
        super(source);
    }

    /**
     * roadMaxSpeed setter
     * @param roadMaxSpeed
     */
    public void setRoadMaxSpeed(int roadMaxSpeed) {
        this.roadMaxSpeed = roadMaxSpeed;
    }

    /**
     * VehicleAvarageSpeed setter
     * @param vehicleAvarageSpeed
     */
    public void setVehicleAvarageSpeed(int vehicleAvarageSpeed) {
        this.vehicleAvarageSpeed = vehicleAvarageSpeed;
    }

    /**
     * roadMaxSpeed getter
     * @return roadMaxSpeed
     */
    public int getRoadMaxSpeed() {
        return roadMaxSpeed;
    }

    /**
     * vehicleAvarageSpeed getter
     * @return vehicleAvarageSpeed
     */
    public int getVehicleAvarageSpeed() {
        return vehicleAvarageSpeed;
    }

    /**
     * vehicleId setter
     * @param vehicleId
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * vehicleId getter
     * @return vehicleId
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * drivingTime getter
     * @return drivingTime
     */
    public int getDrivingTime() {
        return drivingTime;
    }

    /**
     * drivingTime setter
     * @param drivingTime
     */
    public void setDrivingTime(int drivingTime) {
        this.drivingTime = drivingTime;
    }

    /**
     * cheeck if the vehicle is over speeding
     * @return boolean
     */
     public boolean isOverSpeed(){ return vehicleAvarageSpeed > roadMaxSpeed; }
}
