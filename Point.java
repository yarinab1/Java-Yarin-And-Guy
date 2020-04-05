package utilities;

public class Point {

	private double x;
	private double y;
	
	public Point()//default constructor
	{
		this.x=0;
		this.y=0;
	}
	
	public Point(double x,double y)
	{
		this.x=x;
		this.y=y;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x=x;
	}

	public void setY(double y) {
		this.y=y;
	}
	
	public String toString()
	{
		return "(" + this.x + "," + this.y + ")";
		
	}
}
