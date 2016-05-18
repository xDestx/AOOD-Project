package game;

import java.awt.Graphics;
import java.util.LinkedList;

import game.entity.Player;
import game.world.Collidable;

public class Ticker {
	
	
	/*
	 * This is going to hold all the GameObjects
	 * 
	 * All Game objects have a tick, and some have a render
	 * This will tick / render
	 */
	
	private Game g;
	private LinkedList<Renderable> rendrs;
	private LinkedList<GameObject> objs;
	
	public Ticker(Game g)
	{
		this.g = g;
		objs = new LinkedList<GameObject>();
		rendrs = new LinkedList<Renderable>();
	}
	
	/*
	 * Circles through and updates each object
	 */
	public void tick()
	{
		for (GameObject go : objs)
		{
			go.tick();
		}
	}
	
	/*
	 * Circling through objects to find ones that can render
	 * 
	 * Finds offset from Camera location (top left of screen) to object location (top right of object)
	 * and gives it that offset.
	 */
	public void render(Graphics g, Camera c)
	{
		this.g.getLevel().render(g, 0,0);
		for (Collidable r : this.g.getLevel().getListOfCollidables())
		{
			if(r.getBounds().intersects(c.getViewBounds()))
			{
				int x = ((int)(r.getBounds().getLocation().getX() - c.getLocation().getX()));
				int y = (int)(r.getBounds().getLocation().getY() - c.getLocation().getY());
				r.render(g,x,y);
			}
		}
		for (GameObject go : objs)
		{
			if (go instanceof Renderable)
			{
				Renderable r = (Renderable)go;
				if(go.getBounds().intersects(c.getViewBounds()))
				{
					int x = ((int)(go.getLocation().getX() - c.getLocation().getX()));
					int y = (int)(go.getLocation().getY() - c.getLocation().getY());
					r.render(g,x,y);
				}
			}
		}
		
		for (Renderable r : rendrs)
		{
			int x = ((int)(r.getLocation().getX() - c.getLocation().getX()));
			int y = (int)(r.getLocation().getY() - c.getLocation().getY());
			r.render(g,x,y);
		}
		int x = ((int)(0 - c.getLocation().getX()));
		int y = (int)(0 - c.getLocation().getY());
		g.drawLine(x, 0, x, 900);
	}
	
	
	public void addObject(GameObject o)
	{
		this.objs.add(o);
	}
	
	public void addRenderable(Renderable r)
	{
		this.rendrs.add(r);
	}
}
