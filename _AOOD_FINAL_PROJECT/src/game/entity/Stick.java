package game.entity;

import java.awt.Color;
import java.awt.Graphics;

import game.world.Location;

public class Stick extends Enemy{
	
	private Location l;
	private int health, strength;
	
	public Stick(Location l, int health, int strength){
		super(l,  health,  strength);
	}
	
	public void wasHit()
	{
		health = health - 10;
	}
	
	public void render(Graphics g, int xo, int yo)
    {
		Color c = g.getColor();
		g.setColor(Color.black);
		g.fillRect(xo, yo, WIDTH, HEIGHT);
		g.setColor(c);
    }
}
