package utilities;

/**
 * 
 * @version 1.3 29 april 2020
 * @author Guy Cohen 205579808,Yarin Avraham 208401166
 *@see Utilities
 */
public abstract class Point implements Utilities{
    private double x;
	private double y;
	private final int maxX = 800;
    private final int maxY = 600;
    private final int minVal = 0;

    /**
     * Point default constructor
     */
    public Point()
	{
		this.x = getRandomDouble(minVal, maxX);
		this.y = getRandomDouble(minVal, maxY);
    }
    
	/**
	 * Point Constructor
	 * @param x
	 * @param y
	 */
	public Point(double x,double y)
	{
		if(x >= minVal && x <=maxX) this.x=x;
		else{
            this.x = getRandomDouble(minVal, maxX);
            correctingMessage(x, this.x, "X");
		}
		if(y >= minVal && y <= maxY) this.y=y;
		else{ 
			this.y = getRandomDouble(minVal, maxY);
			correctingMessage(y, this.y, "Y");
		}
	}
    
    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @return the maxX
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * @return the maxY
     */
    public int getMaxY() {
        return maxY;
    }

    /**
     * @return the minVal
     */
    public int getMinVal() {
        return minVal;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Point)) {
            return false;
        }
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }
    
    @Override
    public String toString()
	{
		return "Point (" + this.x + " , " + this.y + ")";
    }
    
    /**
     * calculate the distance from this point to another point
     * @param other
     * @return
     */
    public double calcDistance(Point other){
        return  Math.sqrt(Math.pow(this.getX()-other.getX(), 2)+Math.pow(this.getY()-other.getY(), 2));
    }
}
