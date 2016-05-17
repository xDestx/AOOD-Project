package game.entity;

import java.awt.Graphics;

import game.world.Location;

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
	
	//1 = up
	//2 = left
	//3 = down
	//4 = right
	public void move(int direction)
	{
		if (this.canMove(direction)){
			if (direction == 1) {
				Location newLoc = new Location(l.getX(), l.getY() - 10);
				this.l = newLoc;
			} else if (direction == 2) {
				Location newLoc = new Location(l.getX() - 10, l.getY());
				this.l = newLoc;
			} else if (direction == 3) {
				Location newLoc = new Location(l.getX(), l.getY() + 10);
				this.l = newLoc;
			} else if (direction == 4) {
				Location newLoc = new Location(l.getX() + 10, l.getY() );
				this.l = newLoc;
			}
		}
	}
	
	public boolean canMove(int direction)
	{
		//checks if any object is in its path
		return true;
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