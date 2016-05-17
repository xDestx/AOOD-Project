package game.world;

import java.io.Serializable;

public class Location implements Serializable
{

    private double x,y;
    
    public Location(double x, double y)
    {
        this.x = x;
        this.y = y;
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
    
    @Override
    public String toString()
    {
        return "(" + this.x + "," + this.y + ")";
    }

}