
// Guy Cohen - 205579808, Yarin Abraham - 208401166

package components;

import java.util.ArrayList;
import java.util.Random;

public class VehicleType {
    private String typeName;
    private int speed; //average speed of vehicle type

    public VehicleType(String name,int speed){
        typeName = name;
        this.speed = speed;
    }

    public String toString() {
        return typeName + ", avarage speed: "+speed;
    }
    
    public boolean equals(VehicleType V){
        if(typeName == V.typeName && speed == V.speed)
            return true;
        return false;
    };

    public int getSpeed() { return speed; }

    public static ArrayList<VehicleType> getRandomVehicleTypes(){ 
        String[] vehiclesTypes = {"Truck","Motorcycle","Bikes","Private","Tricycle","Ambulance","jeep","SUV","Tractor"};
        //make an random index list to take set an random allowedVehicles list
        ArrayList<Integer> randIndex = new ArrayList<>();
        ArrayList<VehicleType> allowedVehicles = new ArrayList<>();
        Random rand = new Random();
        int sizeOfRandIndex = rand.nextInt(vehiclesTypes.length)+1; // +1 to prevent 0
        for(int i = 0; i<sizeOfRandIndex;i++)
        {
            int tempIndex;
            do{
                tempIndex = rand.nextInt(vehiclesTypes.length);
            }while(randIndex.contains(tempIndex));

            randIndex.add(tempIndex);
        }
        for(int i = 0; i<sizeOfRandIndex;i++)
            allowedVehicles.add(new VehicleType(vehiclesTypes[randIndex.get(i)],10 * (rand.nextInt(12) + 6))); // 60 - 180 (average speed)
        return allowedVehicles;
    }

}