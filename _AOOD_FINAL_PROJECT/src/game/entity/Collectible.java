package game.entity;

import java.awt.Graphics;

import game.world.Location;

public abstract class Collectible extends Entity{
	
	public Collectible(Location l)
	{
		super(l);
	}
	
	public abstract void effect();
	
	public void render(Graphics g, int xo, int yo)
	{
	}
	
	public void tick()
	{
	}
}
