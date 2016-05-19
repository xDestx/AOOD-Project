package game.graphic;

import java.awt.Color;
import java.awt.Graphics;

import game.entity.Player;
import game.world.Location;

public class PlayerHitAnimation extends Animation {

	private Player p;
	private int x, y, r;
	private double d;
	/*
	 * X / Y are the positions of the slice line
	 */
	public PlayerHitAnimation(Player p)
	{
		super(50);
		this.p = p;
		r = (int)( ((Player.HEIGHT + Player.WIDTH)/2)* 1.25 );
	}
	
	@Override
	public void render(Graphics g, int xo, int yo) {
		Color last = g.getColor();
		g.setColor(Color.RED);
		//xo , yo = origin
		g.drawLine(xo, yo, xo + x, yo + y);
		g.setColor(last);
	}

	@Override
	public Location getLocation() {
		return p.getLocation();
	}

	@Override
	protected void changeFrame() {
		d = Math.toRadians((double)(getFrame() / (double)getTotalFrames()) * 360);
		x = (int) (Math.cos(d) * r);
		y = (int)( Math.sin(d) * r);
		System.out.println(d + "  " + x + "  " + y + "  " + r);
	}

}
