package game.entity;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import game.graphic.PlayerHitAnimation;
import game.world.Location;

public class Player extends LivingEntity {
	
	
	private boolean vertMod,horizMod;
	//Check if horizontal / vertical keys are being held.
	
	
	public Player(Location l, int health) {
		super(l);
		this.health = health;
		this.maxHealth = health;
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


	@Override
	public void attack()
	{
		for (Enemy e : Game.getCurrentGame().getEnemies()){
			if (this.getAttackBounds().intersects(e.getBounds())){
				e.wasHit(10);
			}
		}
		addAnimation(new PlayerHitAnimation(this));
	}
	
	@Override
	public void tick() {
		super.tick();
		movement();
	}

	@Override
	public void render(Graphics g, int xo, int yo) {
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillRect(xo, yo, WIDTH, HEIGHT);
		renderAnimations(g,xo,yo);
		drawHealthBar(g,xo,yo);
		g.setColor(c);
	}





}