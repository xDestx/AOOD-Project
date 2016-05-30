package game.world;

import java.awt.Point;
import java.io.Serializable;

public class Location implements Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -204080932133431405L;
	private double x,y;
    
    public Location(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Location(Location l)
    {
    	this.x = l.getX();
    	this.y = l.getY();
    }
    
    public Location(Point p)
    {
    	this.x = p.getX();
    	this.y = p.getY();
    }
    
    public double getX()
    {
        return this.x;
    }
    
    public double getY()
    {
        return this.y;
    }
    
    public void setX(double x)
    {
        this.x = x;
    }
    
    public void setY(double y)
    {
        this.y = y;
    }

    public void set(Location l)
    {
    	this.x = l.getX();
    	this.y = l.getY();
    }
    
    public int distance(Location l)
    {
    	int x1 = (int)Math.pow(l.getX()-getX(), 2);
    	int y1 = (int)Math.pow(l.getY()-getY(), 2);
    	int d = (int)Math.sqrt(x1+y1);
    	return d;
    }
    
    public Point toPoint()
    {
    	return new Point((int)x,(int)y);
    }
    
    @Override
    public String toString()
    {
        return "(" + this.x + "," + this.y + ")";
    }

}