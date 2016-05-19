package game.entity;

import java.awt.Color;
import java.awt.Graphics;

import game.world.Location;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Enemy extends Entity{

	private Location l;
	private int health, strength;
	private int enemyid;
	public static final int WIDTH = 100, HEIGHT = 100;
	
	public Enemy(Location l, int health, int strength)
	{
		this.l = l;
		this.health = health;
		this.strength = strength;
	}
	
	public Location getLocation()
	{
		return l;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getEnemyID()
	{
		return enemyid;
	}
	
	public int getStrength()
	{
		return strength;
	}
	
	public void setLocation(Location l)
	{
		this.l = l;
	}
	
	public void wasHit(){
		health = health - 10;
	}
	
	public void render(Graphics g, int xo, int yo)
    {
		Color c = g.getColor();
		g.setColor(Color.black);
		g.fillRect(xo, yo, WIDTH, HEIGHT);
		g.setColor(c);
    }

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}  
	
}
