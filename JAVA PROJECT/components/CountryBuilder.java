package components;

import java.util.Hashtable;
import utilities.Utilities;
import utilities.VehicleType;

/**
 * CountryBuilder - a builder for a country map
 * @version 1.4 7 June 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 * @see MapBuilder
 * @see Utilities
 */

public class CountryBuilder implements MapBuilder,Utilities{
    private Map map = new Map();
    private final int numOfJunctions = 6;
    private final double exraSpeedMulofCountryMap = getRandomDouble(1, 1.3);
    private final double exraSpeedMulOfCityMap = getRandomDouble(1, 1.5);
    private VehicleType[] allowedvehicleTypes = new VehicleType[VehicleType.values().length - 2];
    private final Hashtable<Integer, String[]> vehicleTypHashtable = new Hashtable<Integer, String[]>() { // <numberOfWheels,array of all vehicle kind that match to numberOfWheels>
                                        {put(2 ,new String[] {"fast"}); put(4 ,new String[]{"work","private","public"}); put(10 ,new String[] {"work"});}
                                    };

    @Override
    public void buildJunctions() {
        System.out.println("================= CREATING COUNTRY MAP =================");
        System.out.println("================= CREATING JUNCTIONS =================");
        for(int i=0;i<numOfJunctions;i++)
        {
            map.getJunctions().add(JFactory.getJunctiom("country"));
            successMessage(map.getJunctions().get(i).toString());
        }
        map.turnLightsOn();
    }

    @Override
    public void buildRoads() {
        System.out.println("\n================= CREATING ROADS =================");
        for(int i=0;i<map.getJunctions().size();i++)
            for(int j=0;j<map.getJunctions().size();j++)
                if(i!=j)
                    map.getRoads().add(new Road(map.getJunctions().get(i), map.getJunctions().get(j)));
    }

    @Override
    public void bulidVehicles(){
        int i=0;
        Object[] vehicleWeelsNumbers = vehicleTypHashtable.keySet().toArray();
        for(int j = 0; j < vehicleWeelsNumbers.length; j++){
            int numberOfWheels = (int) vehicleWeelsNumbers[j];
            String[] tempVehicleKinds = vehicleTypHashtable.get(numberOfWheels); 
            for(String vehicleKind: tempVehicleKinds){
                allowedvehicleTypes[i] = Factory.getFactory(numberOfWheels).getVehicle(vehicleKind);
                allowedvehicleTypes[i].setFixedAverageSpeedByPercents(exraSpeedMulOfCityMap); // set avarageSpeed as CityBulder (maximum 50% more then at regular Map)
                allowedvehicleTypes[i].setFixedAverageSpeedByPercents(exraSpeedMulofCountryMap); // set avarageSpeed as CountryBulder (maximum 30% more then at CityBulder)
                i++;
            }
        }
        VehicleCatch.loadCache(map,allowedvehicleTypes);
        System.out.println("================= CREATING VEHICLES =================\n");
        int numOfVehiclesToMake = getRandomInt(numOfJunctions, numOfJunctions*2);
        for(i = map.getVehicles().size() ; i < numOfVehiclesToMake; i++)
        {
            Vehicle vehicle;
            do{
                vehicle = VehicleCatch.getVehicleByTypeName(allowedvehicleTypes[getRandomInt(0, allowedvehicleTypes.length)].name());
            }while(vehicle == null); // possible that ther is no Road that allow that VehicleType even that the map allow it.

            map.getVehicles().add(vehicle);
        }
    }; 

    @Override
    public Map getMap() {
        return map;
    } 
}