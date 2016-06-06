package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import game.Game;
import game.entity.neutral.Projectile;
import game.graphic.Animation;
import game.inventory.Inventory;
import game.inventory.item.Item;
import game.util.Task;
import game.world.Collidable;
import game.world.Location;
import game.world.Vector;

public abstract class LivingEntity extends Entity {

	protected int maxHealth,strength;
	protected double health;
	protected ArrayList<Animation> animations,toAnimations;
	protected boolean toAnimationsUsed;
	protected Entity lastDamager;
	protected int level;
	protected Inventory inventory;
	protected double bonusHp, bonusRegen, bonusStr;
	
	protected Rectangle hitBounds;

	protected Vector velocity;
	
	public static final int WIDTH = 100, HEIGHT = 100;
	
	public LivingEntity(Location l) {
		super(l);
		this.health = 100;
		bonusHp = 0;
		bonusRegen = 0;
		inventory = new Inventory(this);
		bonusStr = 0;
		lastDamager = null;
		this.maxHealth = 100;
		velocity = new Vector(0, 0);
		animations = new ArrayList<Animation>();
		toAnimations = new ArrayList<Animation>();
		toAnimationsUsed = false;
		setBounds(new Rectangle((int)getLocation().getX(), (int)getLocation().getY(),LivingEntity.WIDTH,LivingEntity.HEIGHT));
		hitBounds = new Rectangle((int)getLocation().getX()-(int)(LivingEntity.WIDTH/4), (int)getLocation().getY()-LivingEntity.HEIGHT/4,(int)(LivingEntity.WIDTH*2),(int)(LivingEntity.HEIGHT*2));
		level = 1;
	}
	
	
	public Inventory getInventory()
	{
		return this.inventory;
	}
	
	public int getLevel()
	{
		return this.level;
	}
	
	public int getMaxHealth()
	{
		int v = (int)(this.maxHealth+this.bonusHp);
		return (v > 0) ? v:1;
	}
	
	public void setStrength(int str)
	{
		this.strength = str;
	}
	
	public int getStrength()
	{
		int v = (int)(this.strength+this.bonusStr);
		return (v > 0) ? v : 1;
	}
	
	public void inventoryUpdated()
	{
		updateItemEffects();
	}
	
	protected void updateItemEffects()
	{
		bonusHp = 0;
		bonusRegen = 0;
		bonusStr = 0;
		if(inventory == null)
			return;
		if(inventory.getItems() == null)
			return;
		for(Item e : inventory.getItems())
		{
			bonusHp+= e.getHealthBoost();
			bonusRegen+= e.getRegenBoost();
			bonusStr+= e.getStrengthBoost();
		}
	}
	
	public void setLevel(int l)
	{
		this.level=l;
	}

	@Override
	public void render(Graphics g, int xo, int yo) {
		renderAnimations(g,xo,yo);
		drawHealthBar(g,xo,yo);
	}

	@Override
	public void tick() {
		animationTick();
		bonusEffects();
	}
	
	protected void bonusEffects()
	{
		//Only regen right now
		this.health+=bonusRegen;
		if(health >= getMaxHealth())
			health = getMaxHealth();
	}
	
	public void addAnimation(final Animation a)
	{
		if(!toAnimationsUsed)
			this.toAnimations.add(a);
		else {
			Game.getCurrentGame().addObject(new Task(1)
					{
				@Override
				public void run()
				{
					addAnimation(a);
				}
					});
		}
	}
	
	public void animationTick()
	{
		ArrayList<Animation> adios = new ArrayList<Animation>();
		for (Animation a : animations)
		{
			a.tick();
			if(a.done())
				adios.add(a);
		}
		for(Animation a : adios)
		{
			animations.remove(a);
		}
		toAnimationsUsed = true;
		for(Animation a : toAnimations)
		{
			animations.add(a);
		}
		toAnimations.clear();
		toAnimationsUsed = false;
	}
	
	public int getHealth()
	{
		return (int)this.health;
	}
	
	
	public void setHealth(int h)
	{
		this.health = h;
	}
	
	public void setMaxHealth(int m)
	{
		this.maxHealth = m;
	}
	
	public Vector getVelocity()
	{
		return velocity;
	}
	
	protected void drawHealthBar(Graphics g, int xo, int yo)
	{
		//Height = .1 * HEIGHT
		//Width = .8 * WIDTH
		int height = (int)(.1 * LivingEntity.HEIGHT);
		int width = (int)(.8 * LivingEntity.WIDTH);
		int x = xo + (int)(.1 * LivingEntity.WIDTH);
		int y = yo + (int)(.45 * LivingEntity.HEIGHT);
		g.setColor(Color.cyan);
		g.drawRect(x-1, y-1, width+1, height+1);
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		g.setColor(Color.green);
		g.fillRect(x, y, (int)(width*((double)health/(double)getMaxHealth())), height);
	}
	
	protected void movement()
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
				 * 
				 * I want to make it so you are placed against the object somehow ??
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
					//Recheck x
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
	
	public void setLocation(Location l) {
		this.l = l;
	}

	
	
	public abstract void attack();
	
	public void wasHit(int damage, Entity damager) {
		health-= damage;
		this.lastDamager = damager;
		if(health < 0)
			health = 0;
	}
	
	public void launchProjectile(Vector v, int dmg)
	{
		Game.getCurrentGame().getLevel().addProjectile(new Projectile(new Location(getCenterLocation()), v, this, dmg));
	}
	
	public Entity getLastDamagingEntity()
	{
		return this.lastDamager;
	}
	
	public boolean isDead()
	{
		return getHealth() <= 0;
	}
	
	public void renderAnimations(Graphics g, int xo, int yo)
	{
		ArrayList<Animation> animations = new ArrayList<Animation>();
		animations.addAll(this.animations);
		for (Animation a : animations)
		{
			a.render(g, xo, yo);
		}
	}
	
	public void setAttackBoundsL(int x, int y)
	{
		getAttackBounds().setLocation(x,y);
	}
	
	public void moveX(double amt) {
		getLocation().setX(getLocation().getX() + amt);
		getBounds().setLocation((int)getLocation().getX(), (int)getLocation().getY());
		setAttackBoundsL((int)getLocation().getX()-Player.WIDTH/2, (int)getLocation().getY()-Player.HEIGHT/2);
	}

	public void moveY(double amt) {
		getLocation().setY(getLocation().getY() + amt);
		getBounds().setLocation((int)getLocation().getX(), (int)getLocation().getY());
		setAttackBoundsL((int)getLocation().getX()-Player.WIDTH/2, (int)getLocation().getY()-Player.HEIGHT/2);
	}

	public Rectangle getAttackBounds() {
		return hitBounds;
	}
	
	public void setAttackBounds(Rectangle hitBounds){
		this.hitBounds = hitBounds;
	}

	
}
