package utilities;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @version 1.3 29 april 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 */

public interface Utilities {
    /**
     * 
     * @param val
     * @param min
     * @param max
     * @return
     */
    public default boolean checkValue(double val, double min, double max){
        if(val>=min && val<=max)
            return true;
        return false;
    }

    /**
     * 
     * @param wrongVal
     * @param correctVal
     * @param varName
     */
    public default void correctingMessage(double wrongVal, double correctVal, String varName){
        System.out.println("The value " + wrongVal + " is illegal for " + varName + ", therefore has been replaced with" + correctVal);
    }

    /**
     * 
     * @param wrongVal
     * @param varName
     */
    public default void errorMessage(double wrongVal, String varName){
        System.out.println("The value " + wrongVal + " is illegal for " + varName);
    }

    /**
     * 
     * @return random boolean
     */
    public default boolean getRandomBoolean(){
        return (new Random()).nextBoolean();
    }

    /**
     * 
     * @param min
     * @param max
     * @return random double between min to max
     */
    public default double getRandomDouble(double min, double max){
        Random rand = new Random();
        if((int) (max-min)!=0)
            return min + rand.nextDouble() + rand.nextInt((int) (max-min));
        else
            return rand.nextDouble()*(max-min) + min;
    }

    /**
     * 
     * @param min
     * @param max
     * @return random int between min to max
     */
    public default int getRandomInt(int min, int max){
        if(min!=0) return min + (new Random()).nextInt(max-min+1);
        else if(max==0) return 0;
        else return (new Random()).nextInt(max); // for index
    }

    /**
     * 
     * @param min
     * @param max
     * @param arraySize
     * @return random arraylist at size of arraySize,the arrayList contain int numbers between min to max 
     */
    public default ArrayList<Integer> getRandomIntArray(int min, int max, int arraySize){
        ArrayList<Integer> randomIntList = new ArrayList<>();
        //make an random index list to take set an random allowedVehicles list
		for(int i = 0; i<arraySize;i++)
		{
			int tempIndex;
			do{
				tempIndex = getRandomInt(min, max);
			}while(randomIntList.contains(tempIndex));
			randomIntList.add(tempIndex);
        }
        return randomIntList;
    }

    /**
     * print success massage of object creating 
     * @param objName
     */
    public default void successMessage(String objName){ System.out.println( objName + " has been created."); }
}