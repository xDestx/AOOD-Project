package game.entity;

import java.awt.Graphics;

import game.Game;
import game.world.Location;
import game.world.Vector;

public class Player extends Entity {
	private Location l;
	private Vector velocity;
	private int health;

	public Player(Location l, int health) {
		this.l = l;
		this.health = health;
		velocity = new Vector(0, 0);
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

	public void moveX(double amt) {
		getLocation().setX(getLocation().getX() + amt);
	}

	public void moveY(double amt) {
		getLocation().setY(getLocation().getY() + amt);
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
		
	}
	
	@Override
	public void tick() {
		// Take Velocity --> Position
		// movement();

		moveX(velocity.getX() / Game.TICK);
		moveY(velocity.getY() / Game.TICK);
	}

	@Override
	public void render(Graphics g, int xo, int yo) {
		// TODO Auto-generated method stub

	}

}