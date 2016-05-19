package game.entity;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import game.ai.Mind;
import game.ai.enemy.Zombie;
import game.world.Collidable;
import game.world.Location;
import game.world.Vector;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Enemy extends Entity{

	private Location l;
	private Mind m;
	private Vector velocity;
	private int health, strength;
	private int enemyid;
	public static final int WIDTH = 100, HEIGHT = 100;
	
	public Enemy(Location l, int health, int strength)
	{
		this.l = l;
		this.health = health;
		this.strength = strength;
		m = new Zombie(this);
		velocity = new Vector(0,0);
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
	
	public Vector getVelocity()
	{
		return velocity;
	}
	
	public boolean isDead()
	{
		return getHealth() <= 0;
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

/*
	@Override
	public boolean collide(Rectangle r) {
		return (r.intersects(getBounds()));
	}
	*/
	private void movement()
	{
		ArrayList<Collidable> col = Game.getCurrentGame().getLevel().getListOfCollidables();
		moveX(velocity.getX());
		moveY(velocity.getY());
		for (Collidable c : col)
		{
			if(c.collide(getBounds()) && c != this)
			{
				/*
				 * Check if colliding x / y then move!
				 */
				//Check x
				double vx = velocity.getX();
				double vy = velocity.getY();
				if(c.getBounds().getMaxX() > getBounds().getMinX() || c.getBounds().getMinX() < getBounds().getMaxX())
				{
					moveX(vx * -1);
					velocity.setX(0);
				}
				//Check y
				if((c.getBounds().getMaxY() > getBounds().getMinY() || c.getBounds().getY() < getBounds().getMaxY()) && c.collide(getBounds()))
				{
					moveY(vy * -1);
					velocity.setY(0);
					moveX(vx);
					if(c.collide(getBounds()))
					{
						moveX(vx * -1);
					} else {
						velocity.setX(vx);
					}
				}
			}
		}
	}
	
	public void moveX(double amt) {
		getLocation().setX(getLocation().getX() + amt);
		getBounds().setLocation((int)getLocation().getX(), (int)getLocation().getY());
		//getAttackBounds().setLocation((int)getLocation().getX(), (int)getLocation().getY());
	}

	public void moveY(double amt) {
		getLocation().setY(getLocation().getY() + amt);
		getBounds().setLocation((int)getLocation().getX(), (int)getLocation().getY());
		//getAttackBounds().setLocation((int)getLocation().getX(), (int)getLocation().getY());
	}
	
	@Override
	public void tick() {
		m.think();
		movement();
	}  
	
}
