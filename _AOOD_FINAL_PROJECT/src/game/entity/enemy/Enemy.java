package game.entity.enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import game.Game;
import game.entity.LivingEntity;
import game.entity.Player;
import game.entity.neutral.ItemEntity;
import game.inventory.item.Item;
import game.util.Task;
import game.world.Location;
import game.world.Vector;

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
		this.level = -1;
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
		if(level == -1)
			return this.xp;
		else
			return this.xp + (5 * getLevel());
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
		drawBody(g,xo,yo);
		drawLevelString(g,xo,yo);
		super.render(g, xo, yo);
		g.setColor(c);
    }
	
	protected void drawLevelString(Graphics g, int xo, int yo)
	{
		g.setColor(Color.GREEN);
		g.drawString(getLevel()+"",xo+(int)(getBounds().getWidth()/2)-(5), yo+(int)(getBounds().getHeight()/2)+20);
	}
	
	protected void drawBody(Graphics g, int xo, int yo)
	{
		if(!isDead())
			g.setColor(Color.black);
		else
			g.setColor(Color.blue);
		g.fillRect(xo, yo, WIDTH, HEIGHT);
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
			doDrop();
		}
	}
	
	protected void doDrop()
	{
		ArrayList<Item> items = inventory.getItems();
		if(Math.random() < .1)
		{
			Item extra = Item.createRandomItem(getLevel());
			if(extra != null)
				items.add(extra);
		}
		for (Item i : items)
		{
			Game.getCurrentGame().getLevel().addCollectible(new ItemEntity(new Location(getCenterLocation()),i,1*Game.TICK));
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
