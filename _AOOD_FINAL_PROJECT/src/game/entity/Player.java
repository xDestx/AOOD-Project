package game.entity;

import java.awt.Graphics;

import game.Location;

public class Player extends Entity
{
	public Location l;
	private int health;
	
	public Player(Location l, int health)
	{
		this.l = l;
		this.health = health;
	}
	
	//To find hitbox of player
	public Location getLocation()
	{
		return l;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void setLocation(Location l)
	{
		this.l = l;
	}
	
	public void attack()
    {
    }
	
	public void move(int direction)
	{
	}
	
	//When player gets hit
	public void wasHit(int damage)
	{
		health = health - damage;
	}
	
		@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g, int xo, int yo) {
		// TODO Auto-generated method stub
		
	}
    
}