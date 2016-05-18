package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import game.Game;
import game.world.Collidable;
import game.world.Location;
import game.world.Vector;

public class Player extends Entity {
	private Location l;
	private Vector velocity;
	private int health;
	private boolean vertMod,horizMod;
	public static final int WIDTH = 100, HEIGHT = 100;
	//Check if horizontal / vertical keys are being held.
	
	
	public Player(Location l, int health) {
		this.l = l;
		this.health = health;
		velocity = new Vector(0, 0);
		setBounds(new Rectangle((int)getLocation().getX()-50, (int)getLocation().getY()-50,100,100));
	}

	// To find hitbox of player
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
	}

	// 1 = up
	// 2 = left
	// 3 = down
	// 4 = right
	public void move(int direction) {
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
	}
	
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
	}

	public void moveY(double amt) {
		getLocation().setY(getLocation().getY() + amt);
		getBounds().setLocation((int)getLocation().getX(), (int)getLocation().getY());
	}

	public boolean canMove(int direction) {
		// checks if any object is in its path
		return true;
	}

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
		if(getBounds().getX() < 0)
			velocity.set(0);
		for (Collidable c : col)
		{
			if(c.collide(getBounds()))
			{
				moveX(velocity.getX() * -1);
				moveY(velocity.getY() * -1);
				velocity.set(0);
			}
		}
	}
	
	@Override
	public void tick() {
		// Take Velocity --> Position
		movement();

		//moveX(velocity.getX());
		//moveY(velocity.getY());
	}

	@Override
	public void render(Graphics g, int xo, int yo) {
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillRect(xo, yo, WIDTH, HEIGHT);
		g.setColor(c);
	}

}