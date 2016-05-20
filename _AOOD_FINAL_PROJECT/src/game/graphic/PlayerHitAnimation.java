package game.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.entity.LivingEntity;
import game.world.Location;

public class PlayerHitAnimation extends Animation {

	private LivingEntity p;
	private int x, y, r;
	private double d;

	/*
	 * X / Y are the positions of the slice line
	 */
	public PlayerHitAnimation(LivingEntity livingEntity) {
		super(50);
		this.p = livingEntity;
		r = (int) (((LivingEntity.HEIGHT + LivingEntity.WIDTH) / 2) * 1.25);
	}

	@Override
	public void render(Graphics g, int xo, int yo) {
		Color last = g.getColor();
		g.setColor(Color.RED);
		// xo , yo = origin
		for (int i = 0; i < 10; i++) {
			g.drawLine(xo + LivingEntity.WIDTH / 2 + i, yo + LivingEntity.HEIGHT / 2 + i,
					xo + x + LivingEntity.WIDTH / 2 + i, yo + y + LivingEntity.HEIGHT / 2 + i);
			g.drawLine(xo + LivingEntity.WIDTH / 2 + i, yo + LivingEntity.HEIGHT / 2 + i,
					xo + x*-1 + LivingEntity.WIDTH / 2 + i, yo + y*-1 + LivingEntity.HEIGHT / 2 + i);
		}
		g.setColor(last);
	}

	@Override
	public Location getLocation() {
		return p.getLocation();
	}

	@Override
	protected void changeFrame() {
		double per = ((double) getFrame() / (double) getTotalFrames());
		double per1 = Math.sin(Math.toRadians(per * 180));
		d = Math.toRadians(per * 360);
		x = (int) (Math.cos(d) * r * per1 );
		y = (int) (Math.sin(d) * r * per1 );
	}

	@Override
	public Rectangle getBounds() {
		return p.getAttackBounds();
	}

}
