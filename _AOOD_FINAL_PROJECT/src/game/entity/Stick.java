package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.world.Location;

public class Stick extends Enemy{
	
	private int strength;
	
	public Stick(Location l, int health, int strength){
		super(l,  health,  strength);
		setBounds(new Rectangle((int)getLocation().getX(),(int) getLocation().getY(), 100, 100));
	}
	
	
	public void render(Graphics g, int xo, int yo)
    {
		Color c = g.getColor();
		if(!isDead())
			g.setColor(Color.black);
		else
			g.setColor(Color.blue);
		g.fillRect(xo, yo, WIDTH, HEIGHT);
		g.setColor(c);
    }
	
	@Override
	public void tick()
	{
		
	}


}
