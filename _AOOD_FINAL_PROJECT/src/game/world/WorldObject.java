package game.world;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.GameObject;
import game.Renderable;

public abstract class WorldObject extends GameObject implements Renderable
{
    private Location l;
    protected Rectangle bounds;

    public WorldObject()
    {
        l = new Location(0,0);
        bounds = new Rectangle(0,0,1,1);
    }
    
    public void setLocation(Location l)
    {
        this.l = l;
    }
    
    public Rectangle getBounds()
    {
    	return this.bounds;
    }
    
    public void setBounds(Rectangle r)
    {
    	this.bounds = r;
    }
    
    public Location getLocation()
    {
        return this.l;
    }
    
    
    public abstract void render(Graphics g, int xo, int yo);

}
