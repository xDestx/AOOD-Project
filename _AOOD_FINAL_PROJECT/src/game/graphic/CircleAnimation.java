package game.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Game;
import game.entity.Entity;
import game.entity.LivingEntity;
import game.world.Location;

public class CircleAnimation extends Animation {

	private Entity e;
	private double d,r;
	private Color c;
	boolean infinite;
	private double revPerSec;
	private int circleSize;
	private Location[] points;
	
	public CircleAnimation(int maxFrames, Entity e, double radius, int circles, double revPerSec) {
		super(maxFrames);
		this.e = e;
		this.r = radius;
		points = new Location[circles];
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Location(0,0);
		}
		circleSize = 8;
		if(maxFrames == -1)
			infinite = true;
		c = Color.RED;
		this.revPerSec = revPerSec;
	}
	
	public CircleAnimation(int maxFrames, Entity e, double radius, int circles, Color c, int circleSize, double revPerSec) {
		super(maxFrames);
		this.e = e;
		this.r = radius;
		points = new Location[circles];
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Location(0,0);
		}
		this.circleSize = circleSize;
		if(maxFrames == -1)
			infinite = true;
		this.c = c;
		this.revPerSec = revPerSec;
	}

	@Override
	public void render(Graphics g, int xo, int yo) {
		Color last = g.getColor();
		g.setColor(c);
		// xo , yo = origin
		int xoff2 = (int)(e.getBounds().getWidth()/2);
		int yoff2 = (int)(e.getBounds().getHeight()/2);
		for (int k = 0; k < 2; k++)
		{
			int kVal = ((k%2) == 0) ? -k:k;
			for (int i = 0; i < points.length; i++) {
				g.fillOval((int)points[i].getX()+xo+xoff2-(circleSize/2)+kVal,  (int)points[i].getY()+yo+yoff2-(circleSize/2)-kVal, circleSize, circleSize);
			}
			g.drawOval(xo+xoff2-(int)(r)+kVal, yo+yoff2-(int)(r)-kVal, (int)(r*2)-(int)(circleSize/4), (int)(r*2)-(int)(circleSize/4));
		}
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
			per = (((double)getFrame()%100)/(double)(revPerSec*Game.TICK));
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
