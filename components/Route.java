package components;
import java.util.ArrayList;
class Route{
    private ArrayList<Junction> junctions;
    private ArrayList<Road> roads;
    private double delay;
    private String vehicleType;
    Route(){
        junctions = new ArrayList<>();
        roads = new ArrayList<>();
    };
    Route(ArrayList<Junction> juncs,ArrayList<Road> roads, String vehType){
        junctions = new ArrayList<>();
        this.roads = new ArrayList<>();
        junctions = (ArrayList<Junction>) juncs.clone();
        this.roads = (ArrayList<Road>) roads.clone();
        vehicleType = vehType;
    };
    Route(Junction start, Junction end, String vehType){};

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