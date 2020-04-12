package components;
public class VehicleType {
    private String typeName;
    private int speed; //average speed of vehicle type

    public VehicleType(String name,int speed){
        typeName = name;
        this.speed = speed;
    }

    public String toString() {
        return "Typename: " + typeName + ", Speed: "+speed;
    }
    
    public boolean equals(VehicleType V){
        if(typeName == V.typeName && speed == V.speed)
            return true;
        return false;
    };

}