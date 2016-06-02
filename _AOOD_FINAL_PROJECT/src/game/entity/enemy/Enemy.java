package game.entity.enemy;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import game.ai.Mind;
import game.ai.enemy.Shooter;
import game.ai.enemy.Zombie;
import game.entity.LivingEntity;
import game.entity.Player;
import game.util.Task;
import game.world.Collidable;
import game.world.Location;
import game.world.Vector;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Enemy extends LivingEntity{

	protected int enemyid;
	protected int xp;
	protected boolean removedSelf;
	public static final int MAX_HP = 4000, MAX_STRENGTH = 500;
	
	public Enemy(Location l, int health, int strength, int xp)
	{
		super(l);
		removedSelf = false;
		this.health = health;
		this.maxHealth = health;
		this.xp = xp;
		this.strength = strength;
		this.setAttackBounds(new Rectangle((int)getLocation().getX() - 100,(int) getLocation().getY() - 100, 200, 200));
		velocity = new Vector(0,0);
	}
	
	public Enemy(Location l, int level, int xp)
	{
		super(l);
		setLevel(level);
		removedSelf = false;
		this.xp = xp;
		this.setAttackBounds(new Rectangle((int)getLocation().getX() - 100,(int) getLocation().getY() - 100, 200, 200));
		velocity = new Vector(0,0);
	}
	
	public void setLevel(int l)
	{
		super.setLevel(l);
		this.health = (int)((double)MAX_HP *  ((double)level/(double)Player.MAX_LEVEL));
		this.maxHealth = (int)health;
		this.strength = (int)((double)MAX_STRENGTH *  ((double)level/(double)Player.MAX_LEVEL));
	}

	public int getKillXP()
	{
		return this.xp;
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
		g.setColor(Color.GREEN);
		g.drawString(getLevel()+"",xo+(int)(getBounds().getWidth()/2)-(5), yo+(int)(getBounds().getHeight()/2)+20);
		super.render(g, xo, yo);
		g.setColor(c);
    }
	
	public void setStrength(int s)
	{
		this.strength=s;
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
		checkDead();
	}
	
	private void checkDead()
	{
		if(!isDead())
			return;
		getVelocity().set(0);
		if(!removedSelf)
		{
			final Enemy deadenemy = this;
			Game.getCurrentGame().addObject(new Task(2*Game.TICK) {
				@Override
				public void run()
				{
					Game.getCurrentGame().getLevel().removeEnemy(deadenemy);
				}
			});
			if(getLastDamagingEntity() != null && getLastDamagingEntity().equals(Game.getCurrentGame().getPlayer()))
			{
				((Player)getLastDamagingEntity()).addXP(getKillXP());
				//System.out.println("damn, " + getKillXP() + " xp! " + Game.getCurrentGame().getPlayer().getXP());
			}
			removedSelf = true;
			
		}
	}


	public static Enemy create(EnemyType e) {
		if(e == EnemyType.STICK)
		{
			return new Stick();
		} else if (e == EnemyType.ARCHER)
		{
			return new Archer();
		} else if (e == EnemyType.SMART_ARCHER)
		{
			return new SmartArcher();
		}
		return null;
	}  
	
}
