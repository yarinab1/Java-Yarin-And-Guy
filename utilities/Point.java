package utilities;

import java.util.Random;

public class Point {

	private final double x;
	private final double y;
	
	public Point()//default constructor
	{
		Random rand = new Random();
		this.x = rand.nextInt(1000000) + rand.nextDouble();
		this.y = rand.nextInt(800) + rand.nextDouble();
		System.out.println(toString() + " has been created.");
	}
	
	public Point(double x,double y)
	{
		if(x >= 0 && x <=1000000) this.x=x;
		else{
			Random rand = new Random();
			this.x=rand.nextInt(1000000) + rand.nextDouble();
			System.out.println("The value " + x + " is illegal for X, therefore has been replaced with " + this.x);
		}
		if(y >= 0 && y <= 800) this.y=y;
		else{ 
			Random rand = new Random();
			this.y = rand.nextInt(800) + rand.nextDouble();
			System.out.println("The value " + y + " is illegal for Y, therefore has been replaced with " + this.y);
		}
		System.out.println(toString() + " has been created.");
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	// Only for test setY and setX added ( final definition removed )
	/*public void setX(double x) {
		if(x >= 0 && x <=1000000)
			this.x = x;
		else
			System.out.println("The value "+x+" is illegal for x");
	}

	public void setY(double y) {
		if(y >= 0 && y <= 800)
			this.y = y;
		else
			System.out.println("The value "+y+" is illegal for y");
	}*/
	
	public String toString()
	{
		return "Point (" + this.x + " , " + this.y + ")";
	}

	public boolean equals(Point P) {
		return x == P.x && y == P.y;
	}
}
