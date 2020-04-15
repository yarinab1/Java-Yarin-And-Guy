
// Guy Cohen - 205579808, Yarin Abraham - 208401166

package components;
import java.util.ArrayList;
import java.util.Random;

import utilities.Point;

public class Map {
    private ArrayList<Junction> junctions = new ArrayList<>();;
    private ArrayList<Road> roads = new ArrayList<>();;
    
    public Map(){
        String[] namesOfJancs = {"Yehuda Alevi","Neve Zeev","Igal Yadin","Neot Leon","Ameshahrerim","Neve Itzhak","Ramot","Bialic","Ein Gedi","Patish","Gilat","Nahal Ashan","Amutot","Calaniot"
                                ,"Ein Ofarim","Nurit","Dan Patenkin","Shomron","Gertz","Kadesh"};// 20 names of junctions
        
        for(String item:namesOfJancs)
            junctions.add(new Junction(item,new Point())); //add junction with the next name and with location point(double 0<x<1000000,double 0<y<800)
     
        for(int i=0;i<10;i++)
            roads.add(new Road(junctions.get(i), junctions.get(i+1)));
    }; //Creates a map with 20 random junctions and connects all of them one to another with roads.

    public Map (int junctions, int roads){
        String[] namesOfJancs = {"Yehuda Alevi","Neve Zeev","Igal Yadin","Neot Leon","Ameshahrerim","Neve Itzhak","Ramot","Bialic","Ein Gedi","Patish","Gilat","Nahal Ashan","Amutot","Calaniot"
                                ,"Ein Ofarim","Nurit","Dan Patenkin","Shomron","Gertz","Kadesh"};// 20 names of junctions
        
        Random rand = new Random();
        for(int i=0;i<junctions;i++)
            this.junctions.add(new Junction(namesOfJancs[i]+ String.valueOf(i),new Point())); //add junction with the next name and with location point(double 0<x<1000000,double 0<y<800)
        
        ArrayList<ArrayList<Integer>> randIndexOfJuncs = new ArrayList<>();
        for(int i = 0; i<roads;i++)
		{
                ArrayList<Integer> tempIndex = new ArrayList<>();
                do{
                    tempIndex.add(rand.nextInt(junctions));
                    tempIndex.add(rand.nextInt(junctions));
                }while(tempIndex.get(0) != tempIndex.get(1) && !randIndexOfJuncs.get(i).contains(tempIndex.get(0)) && !randIndexOfJuncs.get(i).contains(tempIndex.get(1))); // Make sure there are no two repeating junctions (fromJunc,toJunc)
                randIndexOfJuncs.add(tempIndex);
            
                this.roads.add(new Road(this.junctions.get(tempIndex.get(0)), this.junctions.get(tempIndex.get(1))));
        }
        if(roads<junctions/2)
            for(Junction item: this.junctions){
                if(item.getEnteringRoads().isEmpty())
                    item.addEnteringRoads(this.roads.get(rand.nextInt(this.roads.size())));
                if(item.getExitingRoads().isEmpty())
                    item.addExitingRoads(this.roads.get(rand.nextInt(this.roads.size())));
            }
    }; //Creates a random map with given quantity of junctions and roads.

    public Map (ArrayList<Junction> juncs){
        junctions.addAll(juncs);
        
        for(int i=0;i<junctions.size()-1;i++)
            roads.add(new Road(junctions.get(i), junctions.get(i+1)));
    };

    public Map (ArrayList<Junction>juncs, ArrayList<Road>roads){
        Random rand = new Random();
        junctions.addAll(juncs);
        this.roads.addAll(roads);
        for(Junction item: this.junctions){
            if(item.getEnteringRoads().isEmpty())
                item.addEnteringRoads(this.roads.get(rand.nextInt(this.roads.size())));
            if(item.getExitingRoads().isEmpty())
                item.addExitingRoads(this.roads.get(rand.nextInt(this.roads.size())));
        }
    };

    public Map(int numOfJunc)
    {
        String[] namesOfJancs = {"Yehuda Alevi","Neve Zeev","Igal Yadin","Neot Leon","Ameshahrerim","Neve Itzhak","Ramot","Bialic","Ein Gedi","Patish","Gilat","Nahal Ashan","Amutot","Calaniot"
                ,"Ein Ofarim","Nurit","Dan Patenkin","Shomron","Gertz","Kadesh"};
        for(int i=0;i<numOfJunc;i++)
            junctions.add(new Junction(namesOfJancs[i]+ String.valueOf(i),new Point()));
        
            //roads add
        for(int i=0;i<numOfJunc/2;i++)
            roads.add(new Road(junctions.get(i), junctions.get(i+1)));
        if(numOfJunc%2!=0)
            roads.add(new Road(junctions.get(junctions.size()/2), junctions.get((junctions.size()/2)+1)));
    }

    public ArrayList<Junction> getJunctions() {
        return junctions;
    }
   
    public ArrayList<Road> getRoads() { return roads; }
    public void addRoad(Road r){ roads.add(r); };
    public void removeRoad(Road r){ roads.remove(r);};
    public void addJunction(Junction junc){ junctions.add(junc);};
    public void removeJunction(Junction junc){
        junctions.remove(junc);
        for(Road item: roads)
            if(((Junction) item.getFromJunc()).equals(junc) || ((Junction) item.getToJunc()).equals(junc))
                removeRoad(item);
    }; //removes the junction and all connected to it roads from the map.

    public boolean equals(Map M) {
        boolean junctionsAndRoadsEquals = true;
        if(junctions.size() == M.junctions.size())
		{
			for(int i=0;i<junctions.size();i++)
				if(!junctions.get(i).equals(M.junctions.get(i))){
                junctionsAndRoadsEquals = false;
                break;
            }
        }else junctionsAndRoadsEquals = false;
        
        if(roads.size() == M.roads.size() && junctionsAndRoadsEquals)
		{
			for(int i=0;i<roads.size();i++)
				if(!roads.get(i).equals(M.roads.get(i))){
                    junctionsAndRoadsEquals = false;
                    break;
                }
        }else return false;

        return junctionsAndRoadsEquals;
    }
}