package game.entity;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import game.ai.Mind;
import game.ai.enemy.Shooter;
import game.ai.enemy.Zombie;
import game.world.Collidable;
import game.world.Location;
import game.world.Vector;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Enemy extends LivingEntity{

	protected int health, strength, maxHealth;
	protected int enemyid;
	
	public Enemy(Location l, int health, int strength)
	{
		super(l);
		this.health = health;
		this.maxHealth = health;
		this.strength = strength;
		this.setAttackBounds(new Rectangle((int)getLocation().getX() - 100,(int) getLocation().getY() - 100, 200, 200));
		velocity = new Vector(0,0);
	}

	
	public int getEnemyID()
	{
		return enemyid;
	}
	
	public int getStrength()
	{
		return strength;
	}
	
	
	public void render(Graphics g, int xo, int yo)
    {
		Color c = g.getColor();
		if(!isDead())
			g.setColor(Color.black);
		else
			g.setColor(Color.blue);
		g.fillRect(xo, yo, WIDTH, HEIGHT);
		super.render(g, xo, yo);
		g.setColor(c);
    }
	


/*
	@Override
	public boolean collide(Rectangle r) {
		return (r.intersects(getBounds()));
	}
	*/
	
	
	@Override
	public void tick() {
		super.tick();
		movement();
	}  
	
}
