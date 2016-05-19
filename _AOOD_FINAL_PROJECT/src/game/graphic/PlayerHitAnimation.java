package game.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.entity.LivingEntity;
import game.entity.Player;
import game.world.Location;

public class PlayerHitAnimation extends Animation {

	private LivingEntity p;
	private int x, y, r;
	private double d;
	/*
	 * X / Y are the positions of the slice line
	 */
	public PlayerHitAnimation(LivingEntity livingEntity)
	{
		super(50);
		this.p = livingEntity;
		r = (int)( ((Player.HEIGHT + Player.WIDTH)/2)* 1.25 );
	}
	
	@Override
	public void render(Graphics g, int xo, int yo) {
		Color last = g.getColor();
		g.setColor(Color.RED);
		//xo , yo = origin
		for(int i = 0; i < 10; i++)
			g.drawLine(xo + Player.WIDTH/2 + i, yo + Player.HEIGHT/2 + i, xo + x + Player.WIDTH/2 + i , yo + y + Player.HEIGHT/2 + i);
		g.setColor(last);
	}

	@Override
	public Location getLocation() {
		return p.getLocation();
	}

	@Override
	protected void changeFrame() {
		d = Math.toRadians(((double)getFrame() / (double)getTotalFrames()) * 360);
		x = (int) (Math.cos(d) * r);
		y = (int) (Math.sin(d) * r);
		int xo = 800;
		int yo = 450;
		//System.out.println(d + "  " + x + "  " + y + "  " + r + " dist: " + (Math.sqrt(Math.pow((xo + Player.WIDTH/2) - (xo + x), 2) + Math.pow((yo + Player.HEIGHT/2) - (yo + y),2))));
	}

	@Override
	public Rectangle getBounds() {
		return p.getAttackBounds();
	}

}
