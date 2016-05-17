package game.entity;

import java.awt.Graphics;

import game.Location;

public abstract class Enemy extends Entity{

	private Location l;
	private int health, strength;
	private int enemyid;
	
	public Enemy(Location l, int health, int strength)
	{
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
	
	public void render(Graphics g, int xo, int yo)
    {
    }

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}  
	
}
