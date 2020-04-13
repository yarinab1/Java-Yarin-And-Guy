package components;
public class VehicleType {
    private String typeName;
    private int speed; //average speed of vehicle type

    public VehicleType(String name,int speed){
        typeName = name;
        this.speed = speed;
    }

    public String toString() {
        return "Type name - " + typeName + ", Speed - "+speed;
    }
    
    public boolean equals(VehicleType V){
        if(typeName == V.typeName && speed == V.speed)
            return true;
        return false;
    };

    public int getSpeed() {
        return speed;
    }

}