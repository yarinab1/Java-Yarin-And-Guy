package components;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import utilities.VehicleType;

/**
 * VehicleCatch - a prototype for vehicle
 * @version 1.4 7 June 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see Vehicle
 * @see VehicleType
 */

public class VehicleCatch{
    private static Hashtable<String, Vehicle> vehicleMap = new Hashtable<String, Vehicle>();

    /**
     * 
     * @param vahicleTypeName
     * @return a new copy vehicle
     */
    public static Vehicle getVehicleByTypeName(String vahicleTypeName) {
        Vehicle cachedVehicle = vehicleMap.get(vahicleTypeName); // vehicle to clone and change
        return getVehicle(cachedVehicle);
    }

    public static Vehicle getVehicle(Vehicle vehicle) {
        try{
            Vehicle clonedVehicle = (Vehicle) vehicle.clone();
            clonedVehicle.setId(clonedVehicle.getObjectsCount()); // id update
            clonedVehicle.setObjectsCount(clonedVehicle.getObjectsCount() + 1); // object count update
            new Route(clonedVehicle.getCurrentRoute().getRandomeRoad(),clonedVehicle).checkIn(clonedVehicle); // set a random Route from a randome road of the vehicle route
            clonedVehicle.getCurrentRoutePart().checkIn(clonedVehicle);
            System.out.println(clonedVehicle.toString() + " has been created (by clone of vehicle - " + vehicle.getVehicleId() + ").");
            return clonedVehicle;
        }catch(CloneNotSupportedException e) { e.printStackTrace(); } 
        return null;
    }

    /**
     * load the vehicles to catch
     * @param map
     * @param allowedvehicleTypes
     */
    public static void loadCache(Map map,VehicleType[] allowedvehicleTypes) { // add vehicles that Match to the allowd VehicleTypes
        Random rand = new Random();
        boolean HaveMatchVehicleToTheRoad;
        // load
        for (int i = 0; i < allowedvehicleTypes.length ; i++) {
            ArrayList<Road> tempRoadList;
            int j = 0;
            do {
                j++;
                tempRoadList = map.getJunctions().get(rand.nextInt(map.getJunctions().size())).getExitingRoads(); // get exiting roads list of a random junction in the map
                if(tempRoadList.size() != 0)
                    for(int k = 0; k < tempRoadList.size() ;k++) //check if in temp road ther is a road that allow the current VehicleType
                    {
                        HaveMatchVehicleToTheRoad = false;
                        for(VehicleType tempVehicleType: tempRoadList.get(k).getVehicleTypes())
                            if(tempVehicleType.equals(allowedvehicleTypes[i])){
                                HaveMatchVehicleToTheRoad = true;
                                break;
                            }
                        if(!HaveMatchVehicleToTheRoad)
                            tempRoadList.remove(tempRoadList.get(k));// remove the road that not allow the current VehicleType
                    } 
            } while (tempRoadList.size() == 0 || (j < map.getJunctions().size()));

            if(tempRoadList.size() != 0 ){ // if there is a road at the map that allow allow the current VehicleType (else ther is no need to create that VehicleType)
                Vehicle vehicle = new Vehicle(tempRoadList.get(rand.nextInt(tempRoadList.size())),allowedvehicleTypes[i]); // set random road from tempRoadList to vehicle constructor
                vehicleMap.put(vehicle.getVehicleType().name(),vehicle);
                map.getVehicles().add(vehicle);
            }
        }
    }
}
