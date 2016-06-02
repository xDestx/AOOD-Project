package game.world;

import java.awt.Rectangle;

import game.Renderable;

public abstract class EnclosedArea {

	protected final int width,height;
	protected final Location l;
	
	
	public EnclosedArea(Location l,int width, int height)
	{
		this.width = width;
		this.height = height;
		this.l = l;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)l.getX(),(int)l.getY(),getWidth(),getHeight());
	}
	
	public Location getLocation()
	{
		return this.l;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public int getArea()
	{
		return (this.width * this.height);
	}
	
}
