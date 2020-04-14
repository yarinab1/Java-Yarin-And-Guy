
// Guy Cohen - 205579808, Yarin Abraham - 208401166

package utilities;

import java.util.Random;

public class Point {

	private double x;
	private double y;
	private final int limX = 1000000;
	private final int limY = 800;
	
	public Point()//default constructor
	{
		Random rand = new Random();
		this.x = rand.nextInt(limX) + rand.nextDouble();
		this.y = rand.nextInt(limY) + rand.nextDouble();
		System.out.println(toString() + " has been created.");
	}
	
	public Point(double x,double y)
	{
		if(x >= 0 && x <=limX) this.x=x;
		else{
			Random rand = new Random();
			this.x=rand.nextInt(limX) + rand.nextDouble();
			System.out.println("The value " + x + " is illegal for X, therefore has been replaced with " + this.x);
		}
		if(y >= 0 && y <= limY) this.y=y;
		else{ 
			Random rand = new Random();
			this.y = rand.nextInt(limY) + rand.nextDouble();
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

	public void setX(double x) {
		if(x >= 0 && x <=limX)
			this.x = x;
		else
			System.out.println("The value "+x+" is illegal for x");
	}

	public void setY(double y) {
		if(y >= 0 && y <= limY)
			this.y = y;
		else
			System.out.println("The value "+y+" is illegal for y");
	}
	
	public String toString()
	{
		return "Point (" + this.x + " , " + this.y + ")";
	}

	public boolean equals(Point P) {
		return x == P.x && y == P.y;
	}
}
