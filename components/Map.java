package components;
import java.util.ArrayList;
import java.util.Random;

import utilities.Point;

public class Map {
    private ArrayList<Junction> junctions = new ArrayList<>();;
    private ArrayList<Road> roads = new ArrayList<>();;
    
    public Map(){
        Random rand = new Random();
        String[] namesOfJancs = {"Yehuda Alevi","Neve Zeev","Igal Yadin","Neot Leon","Ameshahrerim","Neve Itzhak","Ramot","Bialic","Ein Gedi","Patish","Gilat","Nahal Ashan","Amutot","Calaniot"
                                ,"Ein Ofarim","Nurit","Dan Patenkin","Shomron","Gertz","Kadesh"};// 20 names of junctions
        
        for(String item:namesOfJancs)
            junctions.add(new Junction(item,new Point(rand.nextInt(999999) + rand.nextDouble(),rand.nextInt(799) + rand.nextDouble()))); //add junction with the next name and with location point(double 0<x<1000000,double 0<y<800)
     
        for(int i=0;i<10;i++)
            roads.add(new Road(junctions.get(i), junctions.get(i+1)));
    }; //Creates a map with 20 random junctions and connects all of them one to another with roads.

    public Map (int junctions, int roads){
        String[] namesOfJancs = {"Yehuda Alevi","Neve Zeev","Igal Yadin","Neot Leon","Ameshahrerim","Neve Itzhak","Ramot","Bialic","Ein Gedi","Patish","Gilat","Nahal Ashan","Amutot","Calaniot"
                                ,"Ein Ofarim","Nurit","Dan Patenkin","Shomron","Gertz","Kadesh"};// 20 names of junctions
        
        Random rand = new Random();
        for(int i=0;i<junctions;i++)
            this.junctions.add(new Junction(namesOfJancs[i]+ String.valueOf(i),new Point(rand.nextInt(999999) + rand.nextDouble(),rand.nextInt(799) + rand.nextDouble()))); //add junction with the next name and with location point(double 0<x<1000000,double 0<y<800)
        
        ArrayList<ArrayList<Integer>> randIndexOfJuncs = new ArrayList<>();
        for(int i = 0; i<roads;i++)
		{
			ArrayList<Integer> tempIndex = new ArrayList<>();
			do{
                tempIndex.add(rand.nextInt(junctions));
                tempIndex.add(rand.nextInt(junctions));
            }while(tempIndex.get(0) != tempIndex.get(0) && !randIndexOfJuncs.contains(tempIndex)); // Make sure there are no two repeating junctions (fromJunc,toJunc)
            randIndexOfJuncs.add(tempIndex);

            this.roads.add(new Road(this.junctions.get(rand.nextInt(tempIndex.get(0))), this.junctions.get(tempIndex.get(1))));
        }
    }; //Creates a random map with given quantity of junctions and roads.

    public Map (ArrayList<Junction> juncs){
        junctions = juncs;
        
        for(int i=0;i<junctions.size()/2;i++)
            roads.add(new Road(junctions.get(i), junctions.get(i+1)));
        if(junctions.size()%2!=0)
            roads.add(new Road(junctions.get(junctions.size()/2), junctions.get((junctions.size()/2)+1)));
    };

    public Map (ArrayList<Junction>juncs, ArrayList<Road>roads){
        junctions = juncs;
        this.roads = roads;
    };

    public void addRoad(Road r){ roads.add(r); };
    public void removeRoad(Road r){ roads.remove(r);};
    public void addJunction(Junction junc){ junctions.add(junc);};
    public void removeJunction(Junction junc){
        junctions.remove(junc);
        for(Road item: roads)
        {
            if(((Junction) item.getFromJunc()).equals(junc))
                removeRoad(item);
        }

        /*for(Road item:roads)
            if(((Junction) item.getFromJunc()).equals(junc))
            {
                Random rand = new Random();
                item.setFromJunc(junctions.get(rand.nextInt(junctions.size())));
            }else if(((Junction) item.getToJunc()).equals(junc))
            {
                Random rand = new Random();
                item.setToJunc(junctions.get(rand.nextInt(junctions.size())));
            }//To save the roads but chang the removed junction (guidance is unclear)*/
            
    }; //removes the junction and all connected to it roads from the map.
}