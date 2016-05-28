package game.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.entity.Entity;
import game.entity.LivingEntity;
import game.world.Location;

public class CircleAnimation extends Animation {

	private Entity e;
	private double d,r;
	private int circles;
	boolean infinite;
	private Location[] points;
	
	public CircleAnimation(int maxFrames, Entity e, double radius, int circles) {
		super(maxFrames);
		this.e = e;
		this.r = radius;
		this.circles = circles;
		points = new Location[circles];
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Location(0,0);
		}
		if(maxFrames == -1)
			infinite = true;
	}

	@Override
	public void render(Graphics g, int xo, int yo) {
		Color last = g.getColor();
		g.setColor(Color.RED);
		// xo , yo = origin
		int xoff2 = (int)(e.getBounds().getWidth()/2);
		int yoff2 = (int)(e.getBounds().getHeight()/2);
		int cSize = 8;
		for (int i = 0; i < points.length; i++) {
			g.fillOval((int)points[i].getX()+xo+xoff2-(cSize/2),  (int)points[i].getY()+yo+yoff2-(cSize/2), 8, 8);
		}
		g.drawOval(xo+xoff2-(int)(r), yo+yoff2-(int)(r), (int)(r*2)-(int)(cSize/4), (int)(r*2)-(int)(cSize/4));
		g.setColor(last);
	}

	@Override
	public Location getLocation() {
		return e.getLocation();
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return e.getBounds();
	}

	@Override
	protected void changeFrame() {
		double per;
		if (!infinite)
			per = ((double) getFrame() / (double) getTotalFrames());
		else
			per = (((double)getFrame()%100)/(double)100);
		d = Math.toRadians(per * 360);
		for (int i = 0; i < points.length; i++)
		{
			double offset = ((double)i/(double)points.length) * 360;
			offset = Math.toRadians(offset);
			points[i].setX(Math.cos(d+offset) * r);
			points[i].setY(Math.sin(d+offset) * r);
		}
	}

}
