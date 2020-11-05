package utilities;

/**
 * VehicleType - an enum class of vehicle types
 * @version 1.4 11 Jone 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 */

public enum VehicleType {
    car(90), bus(60), bicycle(40), motorcycle(120), truck(80),
    tram(50), semitrailer(85);
    private int averageSpeed;
    VehicleType(int speed) {
        averageSpeed=speed;
    }

    /**
     * averageSpeed getter
     * @return averageSpeed
     */
    public int getAverageSpeed() {
        return averageSpeed;
    }

    /**
     * reduce the everageSpeed multiplied 10 - for GUI 
     * @param extraPercentsMul
     */
    public int getFixedAverageSpeed() {
        return averageSpeed/10;
    }

    /**
     * get a double and multiple it to set the avarageSpeed depending on the map 
     * @param extraPercentsMul
     */
    public void setFixedAverageSpeedByPercents(double extraPercentsMul) { // add speed (Percents)
        int newSpeed = (int) Math.round(averageSpeed*extraPercentsMul);
        averageSpeed = newSpeed-10;
    }
}