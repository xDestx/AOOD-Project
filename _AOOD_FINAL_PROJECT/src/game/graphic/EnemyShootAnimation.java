package game.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.entity.LivingEntity;
import game.world.Location;

public class EnemyShootAnimation extends Animation{
	
	private projectile p;
	private int x, y, distance;
	private double d;
	private static final int WIDTH = 50, HEIGHT = 10;
	
	public EnemyShootAnimation(projectile p) {
		super(50);
		this.p = livingEntity;
		distance = 250;
	}

	@Override
	public void render(Graphics g, int xo, int yo) {
		Color last = g.getColor();
		g.setColor(Color.red);
		g.drawRect(xo,  yo,  WIDTH,  HEIGHT);
		g.setColor(last);
	}

	@Override
	public Location getLocation() {
		return new Location(this.x, this.y);
	}

	@Override
	public Rectangle getBounds() {
		return p.getAttackBounds();
	}

	@Override
	protected void changeFrame() {

	}
	
	
}
