package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import game.Game;
import game.graphic.Animation;
import game.graphic.PlayerHitAnimation;
import game.util.Task;
import game.world.Collidable;
import game.world.Location;
import game.world.Vector;

public class Player extends Entity {
	private Location l;
	private Rectangle hitBounds;
	private ArrayList<Animation> animations,toAnimations;
	private boolean toAnimationsUsed;
	private Vector velocity;
	private int health;
	private boolean vertMod,horizMod;
	public static final int WIDTH = 100, HEIGHT = 100;
	//Check if horizontal / vertical keys are being held.
	
	
	public Player(Location l, int health) {
		this.l = l;
		this.health = health;
		velocity = new Vector(0, 0);
		animations = new ArrayList<Animation>();
		toAnimations = new ArrayList<Animation>();
		toAnimationsUsed = false;
		setBounds(new Rectangle((int)getLocation().getX()-50, (int)getLocation().getY()-50,Player.WIDTH,Player.HEIGHT));
		hitBounds = new Rectangle((int)getLocation().getX()-50, (int)getLocation().getY()-50,(int)(Player.WIDTH*2),(int)(Player.HEIGHT*2));
	}

	public Location getLocation() {
		return l;
	}

	public Vector getVelocity() {
		return this.velocity;
	}

	public int getHealth() {
		return health;
	}

	public void setLocation(Location l) {
		this.l = l;
	}

	public void attack() {
		for (Enemy e : Game.getCurrentGame().getEnemies()){
			if (this.getAttackBounds().intersects(e.getBounds())){
				e.wasHit();
			}
		}
		addAnimation(new PlayerHitAnimation(this));
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
	
	// 1 = up
	// 2 = left
	// 3 = down
	// 4 = right
	/*public void move(int direction) {
		if (this.canMove(direction)) {
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
				Location newLoc = new Location(l.getX() + 10, l.getY());
				this.l = newLoc;
			}
		}
	}*/
	
	public void setVert(boolean b)
	{
		this.vertMod = b;
	}
	
	public void setHoriz(boolean b)
	{
		this.horizMod = b;
	}
	
	public boolean getVert()
	{
		 return this.vertMod;
	}
	
	public boolean getHoriz()
	{
		return this.horizMod;
	}

	public void moveX(double amt) {
		getLocation().setX(getLocation().getX() + amt);
		getBounds().setLocation((int)getLocation().getX(), (int)getLocation().getY());
		getAttackBounds().setLocation((int)getLocation().getX(), (int)getLocation().getY());
	}

	public void moveY(double amt) {
		getLocation().setY(getLocation().getY() + amt);
		getBounds().setLocation((int)getLocation().getX(), (int)getLocation().getY());
		getAttackBounds().setLocation((int)getLocation().getX()-WIDTH/4, (int)getLocation().getY()-HEIGHT/4);
	}

	/*public boolean canMove(int direction) {
		// checks if any object is in its path
		return true;
	}*/

	// When player gets hit
	public void wasHit(int damage) {
		health = health - damage;
	}

	
	//Check for collisions, etc and then move
	private void movement()
	{
		ArrayList<Collidable> col = Game.getCurrentGame().getLevel().getListOfCollidables();
		moveX(velocity.getX());
		moveY(velocity.getY());
		for (Collidable c : col)
		{
			if(c.collide(getBounds()))
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
	
	@Override
	public void tick() {
		movement();
		animationTick();
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
	
	@Override
	public void render(Graphics g, int xo, int yo) {
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillRect(xo, yo, WIDTH, HEIGHT);
		g.drawRect(xo-WIDTH/2, yo-HEIGHT/2,(int) getAttackBounds().getWidth(), (int)getAttackBounds().getHeight());
		g.setColor(c);
		renderAnimations(g,xo,yo);
	}
	
	public void renderAnimations(Graphics g, int xo, int yo)
	{
		for (Animation a : animations)
		{
			a.render(g, xo, yo);
		}
	}

	public Rectangle getAttackBounds() {
		return hitBounds;
	}

}