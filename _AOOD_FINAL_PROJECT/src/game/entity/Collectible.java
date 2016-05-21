package game.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.world.Location;

public abstract class Collectible extends Entity{
	
	public Collectible(Location l)
	{
		super(l);
		setBounds(new Rectangle((int)l.getX(),(int)l.getY(),50,50));
	}
	
	public abstract void effect();
	
	
	public void tick()
	{
	}
}
