package components;

import java.util.TreeMap;
import java.util.Map;

/**
 * BigBrother - singeletone class, reports a vehicle that was speeding (->Mediator -> Moked)
 * @see VehicleListener
 * @see Vehicle
 * @see Mediator
 * @see Moked
 */

public class BigBrother implements VehicleListener {
    private static BigBrother bigBrother = null;
    private static int reportNumber = 0;
    private final Map<String, Integer> reportsTreeMap = new TreeMap<String, Integer>(); // TreeMap<String = report, Integer = vehicle Id>
    private final Mediator mediator = new Mediator(); 

    /**
     * default constructor
     */
    private BigBrother(){}

    /**
     * singeltone implemation
     * @return static BigBrother object
     */
    public static BigBrother getBigBrother(){
        if(bigBrother == null)
            synchronized(BigBrother.class){
                if (bigBrother == null){
                    bigBrother = new BigBrother();
                    return new BigBrother();
                }
            }
        return bigBrother;
    }

    /**
     * check overSpeed - true = write a report and send it to mediator operation, false = no action
     * @param vehicle - Vehicle
     */
    public void isOverSpeed(Vehicle vehicle){ // by vehicle (no event)
        if(vehicle.getVehicleType().getFixedAverageSpeed() > vehicle.getLastRoad().getFixedMaxSpeed()+5)
        {
            reportNumber++;
            String report = "Time - " + Driving.getDrivingTime() + ", Report number - " + reportNumber + ": vehicle id - " + vehicle.getVehicleId();
            reportsTreeMap.put(report ,vehicle.getVehicleId());
            mediator.sendReportToMoked(report);
        }
    }
    
    /**
     * a vehicle id getter - by report
     * @param report
     * @return vehicle id
     */
    public int getVehicleIdByReport(String report){
        return reportsTreeMap.get(report);
    }

    /**
     * check overSpeed - true = write a report and send it to mediator operation, false = no action
     * @param vehicle - VehicleEvent
     */
    @Override
    public void arivedToJunction(VehicleEvent vehicle) { // by vehicle event
        if((vehicle.isOverSpeed()))
        {
            reportNumber++;
            String report = "Time - " + vehicle.getDrivingTime() + ", Report number - " + reportNumber + ": vehicle id - " + vehicle.getVehicleId();
            reportsTreeMap.put(report ,vehicle.getVehicleId());
            mediator.sendReportToMoked(report);
        }
    }
}