package game;

import java.awt.Graphics;

public abstract class WorldObject extends GameObject implements Renderable
{
    private Location l;

    public WorldObject()
    {
        l = new Location(0,0);
    }
    
    public void setLocation(Location l)
    {
        this.l = l;
    }
    
    public Location getLocation()
    {
        return this.l;
    }
    
    public abstract void render(Graphics g, int xo, int yo);

}
