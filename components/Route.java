package components;
import java.util.ArrayList;
class Route{
    private ArrayList<Junction> junctions = new ArrayList<>();;
    private ArrayList<Road> roads = new ArrayList<>();;
    private double delay;
    private VehicleType vehicleType;

    Route(ArrayList<Junction> juncs,ArrayList<Road> roads, VehicleType vehType){
        junctions.addAll(juncs);
        this.roads.addAll(roads);
        vehicleType = vehType;
    };

    Route(Junction start, Junction end, VehicleType vehType){}; // not finish

    //Junction getStart(){};
    //Junction getEnd(){};

    double calcDelay(){ //not finish
        int length=0;
        for(Junction item: junctions)
            length += item.getDelay();

        double time=0;
        for(Road item: roads){
            if(item.getMaxSpeed()>0)//<vehicle speed)
                time += item.getLenght();// div item.getMaxSpeed()
            //else time += item.getLenght();// div vehicle speed
        // if length is delay value (from method Description)
        }

        return length;
    }; // not finished
}