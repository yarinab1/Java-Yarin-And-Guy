package components;
import java.lang.Math; 

public class Road {
	
	private Junction fromJunc;
	private Junction toJunc;
	private String[] allowedVehicles;// holds the list of vehicle types
	//that are allowed to move on the road.
	private boolean isOpen;// True when the light is green.
	private boolean isEnabled;//appears on the map
	private double Lenght;// the distance between the two junctions.
	private int MaxSpeed;
	
	
	public Road(Junction from,Junction to)
	{
		fromJunc=from;
		toJunc=to;
	}
	
	public Road(Junction from,Junction to, String[] allowed,boolean open, boolean enabled)
	{
		fromJunc=from;
		toJunc=to;
		allowedVehicles=new String[allowed.length];
		for(int i=0;i<allowed.length;i++)
		{
			allowedVehicles[i]=allowed[i];
		}
		isOpen=open;
		isEnabled=enabled;
		
	}
	
	public boolean GetLight() {return isOpen;}//check if the light is green or not
	public boolean GetAppears() {return isEnabled;}
	public double GetLenght() {return Lenght;}
	public int GetMaxSpeed() {return MaxSpeed;}
	
	public void SetLight(boolean TorF)//true or false
	{
		isOpen=TorF;
	}
	
	public void SetAppears(boolean TorF)//true or false
	{
		isEnabled=TorF;
	}
	public void SetLenght(double len)
	{
		Lenght=len;
	}
	public void SetMaxSpeed (int max)
	{
		MaxSpeed=max;
	}
	
	public void addVehicleType(String type)
	{
		String[] temp=new String[allowedVehicles.length+1];
        for(int i=0;i<allowedVehicles.length;i++)
        {
        	temp[i]=allowedVehicles[i];
        }
        temp[allowedVehicles.length]=type;
        allowedVehicles=new String [allowedVehicles.length+1];
        allowedVehicles=temp;
	}
	public double countLength()//calculates the length of the road using the coordinates
	{
		return Math.sqrt(Math.pow(toJunc.getX()-fromJunc.getX(), 2)+Math.pow(toJunc.getX()-fromJunc.getX(), 2));
	}
	
	public String toString()
	{
		return "the Road is from "+ fromJunc.getJunctionName()+"to "+toJunc.getJunctionName()+"\n";
	}
	
	
}
