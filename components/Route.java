
// Guy Cohen - 205579808, Yarin Abraham - 208401166

package components;
import java.util.ArrayList;
public class Route{
    private ArrayList<Junction> junctions = new ArrayList<>();;
    private ArrayList<Road> roads = new ArrayList<>();;
    private double delay;
    private VehicleType vehicleType;

    public Route(ArrayList<Junction> juncs,ArrayList<Road> roads, VehicleType vehType){
        junctions.addAll(juncs);
        this.roads.addAll(roads);
        vehicleType = vehType;
        calcDelay();
    };

    public Route(Junction start, Junction end, VehicleType vehType){}; // not implemented in this task.
    
    public Junction getStart(){return junctions.get(0);};
    public Junction getEnd(){return junctions.get(junctions.size()-1);};
    public double getDelay() { return delay; }
    public ArrayList<Junction> getJunctions() { return junctions;}

    public void calcDelay()
     /*(1) set length to be a sum of delay values of all the junctions
    on the route (2) and the time that will take this type of vehicle to pass all
    the roads. (3) Time is calculated by dividing the distance by min(average
    speed, maxSpeed).The delay time on junctions is calculated according
    to worse case: (4) if there is a traffic lights on the junction, we use it’s
    delay value multiplied by (number of entering roads minus one). (5) If
    there is no traffic lights on the junction, the delay time is the priority
    level of the road that leads us to this junction (the index of this road in
    the list of roads)*/
    {
        double length=0; //1
        for(Junction item: junctions) 
        {
            if(item.getHasLight())
                length += item.getDelay()*item.getEnteringRoads().size()-1; //4
            else
                {
                    for(int i=0;i<roads.size();i++)
                        if(roads.get(i).getToJunc().equals(item))
                            length += i; //5 
                }
        }
            

        double time=0;
        for(Road item: roads){ //3
            if(item.getMaxSpeed()<vehicleType.getSpeed())
                time += item.getLength()/item.getMaxSpeed();
            else time += item.getLength()/vehicleType.getSpeed();
        }

        length += time; //2

        delay = length;
    };

    public void printRoute(){System.out.println(getStart() + "," + roads.get(0) + "," + getEnd() + "," + roads.get(roads.size()-1));} //TODO: fix the route and then print here the all route 
    
}