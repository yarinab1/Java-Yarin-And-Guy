
// Guy Cohen - 205579808, Yarin Abraham - 208401166

package components;
import java.util.ArrayList;
public class Route{
    private ArrayList<Junction> junctions = new ArrayList<>();;
    private ArrayList<Road> roads = new ArrayList<>();;
    private double delay;
    private VehicleType vehicleType;
    
    //Route constructor
    public Route(ArrayList<Junction> juncs,ArrayList<Road> roads, VehicleType vehType){
        vehicleType = vehType;
        junctions.addAll(juncs);
        this.roads.addAll(roads);

        calcDelay();
    };

    public ArrayList<Integer> getIndexListOfRoads(ArrayList<Road> roads, Junction from){ //gets road that start from the same junction
        ArrayList<Integer> indexList = new ArrayList<>();
        for(int i = 0;i < roads.size();i++)
        {   
            if(roads.get(i).getIsEnabled() && roads.get(i).getAllowedVehicles().contains(vehicleType)){
                    if(roads.get(i).getFromJunc().equals(from)) //add all the roads that start from the wanted place
                        indexList.add(i);
            }
        }
        return indexList;
    }

    public boolean getIndexOfRoads(Road road, Junction from,Junction to){
        if(road.getIsEnabled() && road.getAllowedVehicles().contains(vehicleType)){
                if(road.getFromJunc().equals(from) && road.getToJunc().equals(to))
                    return true;
        }
        return false;
    }

    public void addConnectedRoad(ArrayList<Integer> indexList, ArrayList<Junction> juncs){
        for(Integer indexOfRoad : indexList)
        {
            for(int j = juncs.size()-1; j>=0; j--)
                if(roads.get(indexOfRoad).getToJunc().equals(juncs.get(j))){
                    this.roads.add(roads.get(indexOfRoad));
                    junctions.add(juncs.get(j));
                    return;
                }
        }
    }

    

    public Route(Junction start, Junction end, VehicleType vehType){}; // not implemented in this task.
    
   //Sets And Gets to Route
    public Junction getStart(){return junctions.get(0);};
    public Junction getEnd(){return junctions.get(junctions.size()-1);};
    public double getDelay() { return delay; }
    public ArrayList<Junction> getJunctions() { return junctions;}
    public ArrayList<Road> getRoads() { return roads; }

    public void calcDelay()//set length to be a sum of delay values of all the junctions on the route
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

    public void printRoute(){
        //for(Road itemRoad: roads)

        System.out.println(getStart() + "," + roads.get(0) + "," + getEnd() + "," + roads.get(roads.size()-1));
    } //TODO: fix the route and then print here the all route 

    public Road getNextRoad(Road R){
        for(int i=0; i< roads.size(); i++)
            {
                if(roads.get(i).equals(R) && i < roads.size()-1)
                    return roads.get(i+1);
            }
        return R;
    }
    
}
