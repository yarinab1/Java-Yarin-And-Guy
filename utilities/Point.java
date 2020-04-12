package utilities;

public class Point {

	private final double x;
	private final double y;
	
	public Point()//default constructor
	{
		this.x=0;
		this.y=0;
	}
	
	public Point(double x,double y)
	{
		if(x>=0 && x<1000000) this.x=x;
		else this.x=0;
		if(y>=0 && y<800) this.y=y;
		else this.y=0;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public String toString()
	{
		return "Points: (" + this.x + "," + this.y + ")";
	}

	public boolean equals(Point P) {
		return super.equals(P);
	}
}
