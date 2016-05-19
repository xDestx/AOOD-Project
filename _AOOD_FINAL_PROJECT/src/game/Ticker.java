package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import game.entity.Player;
import game.util.Task;
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
		ArrayList<Task> toRemove = new ArrayList<Task>();
		for (GameObject go : objs)
		{
			go.tick();
			if(go instanceof Task)
			{
				Task t = (Task)go;
				if(t.done())
					toRemove.add(t);
			}
		}
		for(GameObject go : g.getLevel().getEnemies())
		{
			go.tick();
		}
		
		for(Task t : toRemove)
		{
			objs.remove(t);
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
				if(r.getBounds().intersects(c.getViewBounds()))
				{
					int x = ((int)(r.getLocation().getX() - c.getLocation().getX()));
					int y = (int)(r.getLocation().getY() - c.getLocation().getY());
					r.render(g,x,y);
				}
			}
		}
		for (GameObject go : this.g.getLevel().getEnemies())
		{
			if (go instanceof Renderable)
			{
				Renderable r = (Renderable)go;
				if(r.getBounds().intersects(c.getViewBounds()))
				{
					int x = ((int)(r.getLocation().getX() - c.getLocation().getX()));
					int y = (int)(r.getLocation().getY() - c.getLocation().getY());
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
		//drawPlayerAttackBound(g,c);
	}
	
	
	private void drawPlayerAttackBound(Graphics g, Camera c)
	{

		int x = ((int)(Game.getCurrentGame().getPlayer().getAttackBounds().getLocation().getX() - c.getLocation().getX()));
		int y = (int)(Game.getCurrentGame().getPlayer().getAttackBounds().getLocation().getY() - c.getLocation().getY());
		g.drawRect(x, y, Player.WIDTH*2, Player.HEIGHT*2);
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
