package game;

import java.awt.Graphics;
import java.util.LinkedList;

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
		for (GameObject go : objs)
		{
			if (go instanceof Renderable)
			{
				Renderable r = (Renderable)go;
				int x = Math.abs((int)(c.getLocation().getX() - go.getLocation().getX()));
				int y = Math.abs((int)(c.getLocation().getY() - go.getLocation().getY()));
				r.render(g,x,y);
			}
		}
		for (Renderable r : rendrs)
		{
			int x = Math.abs((int)(c.getLocation().getX() - r.getLocation().getX()));
			int y = Math.abs((int)(c.getLocation().getY() - r.getLocation().getY()));
			r.render(g,x,y);
		}
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
