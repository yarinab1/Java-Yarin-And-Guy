
// Guy Cohen - 205579808, Yarin Abraham - 208401166

package components;

import java.util.ArrayList;
import java.util.Random;

public class VehicleType {
    private String typeName;
    private int speed; //average speed of vehicle type
    private static ArrayList<VehicleType> randomVehicleTypes;

  //VehicleType Constructor	
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

    public static ArrayList<VehicleType> getRandomVehicleTypes() { return randomVehicleTypes; }

    public void setRandomVehicleTypesR(){ // cerates an arrray of a random vehicle types that can repeat with the same name and speed or the same name and diff speed.
        String[] vehiclesTypes = {"car","bicycle","bus","motorcycle"};
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
            allowedVehicles.add(new VehicleType(vehiclesTypes[randIndex.get(i)],10 * (rand.nextInt(9) + 4))); // 40 - 130 (average speed)
        randomVehicleTypes =  allowedVehicles;
    }

    public static void setRandomVehicleTypes(){ // cerates an arrray of a random vehicle types that can not repeat the same names.
        String[] vehiclesTypes = {"car","bicycle","bus","motorcycle"};
        //make an random index list to take set an random allowedVehicles list
        ArrayList<VehicleType> allowedVehicles = new ArrayList<>();
        Random rand = new Random();
        
        for(int i = 0; i<vehiclesTypes.length;i++)
            allowedVehicles.add(new VehicleType(vehiclesTypes[i],10 * (rand.nextInt(9) + 4))); // 40 - 130 (average speed)
        randomVehicleTypes = allowedVehicles;
    }

}
