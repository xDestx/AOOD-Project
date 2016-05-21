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
		Game.getCurrentGame().getLevel().tick();
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
		this.g.getLevel().render(g,0,0);
		for (GameObject go : objs)
		{
			if (go instanceof Renderable)
			{
				Renderable r = (Renderable)go;
				if(r.getBounds().intersects(c.getViewBounds()))
				{
					int[] xy = Camera.calculateOffset(r.getLocation());
					r.render(g, xy[0], xy[1]);
				}
			}
		}
		for (Renderable r : rendrs)
		{
			int[] xy = Camera.calculateOffset(r.getLocation());
			r.render(g, xy[0], xy[1]);
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
