package game.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.GameObject;
import game.Renderable;
import game.world.Location;


public abstract class Entity extends GameObject implements Renderable
{
	protected Rectangle bounds;
	protected Location l;

    public abstract void render(Graphics g, int xo, int yo);
    
    public Rectangle getBounds()
    {
    	return bounds;
    }

    public Location getLocation()
    {
    	return l;
    }
    
    
    public void setBounds(Rectangle r)
    {
    	this.bounds = r;
    }
    
}
