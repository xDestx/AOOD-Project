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
	private LinkedList<Renderable> rendrs,toRender;
	private LinkedList<GameObject> objs,toObj;
	private boolean objsUsed,rendersUsed;
	public static final int TICK_DIST = 4800;
	
	public Ticker(Game g)
	{
		this.g = g;
		objs = new LinkedList<GameObject>();
		rendrs = new LinkedList<Renderable>();
		toObj = new LinkedList<GameObject>();
		toRender = new LinkedList<Renderable>();
		objsUsed = false;
		rendersUsed = false;
	}
	
	/*
	 * Circles through and updates each object
	 */
	public void tick()
	{
		ArrayList<Task> toRemove = new ArrayList<Task>();
		objsUsed = true;
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
		objsUsed = false;
		if(toObj.size()>0)
			transferToObj();
	}
	
	private void transferToObj()
	{
		if(objsUsed)
			return;
		for (GameObject go : toObj)
		{
			objs.add(go);
		}
		toObj.clear();
	}
	
	private void transferToRendr()
	{
		if(rendersUsed)
			return;
		for (Renderable go : toRender)
		{
			rendrs.add(go);
		}
		toRender.clear();
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
		objsUsed = true;
		for (GameObject go : objs)
		{
			if (go instanceof Renderable)
			{
				Renderable r = (Renderable)go;
				if(r.getBounds().intersects(c.getViewBounds()))
				{
					int[] xy = Camera.calculateOffset(r.getLocation());
					r.render(g, xy[0], xy[1]);
				} else if (r instanceof Player)
				{
					int[] xy = Camera.calculateOffset(r.getLocation());
					r.render(g, xy[0], xy[1]);
				}
			}
		}
		objsUsed = false;
		if(toObj.size()>0)
			transferToObj();
		rendersUsed = true;
		for (Renderable r : rendrs)
		{
			int[] xy = Camera.calculateOffset(r.getLocation());
			r.render(g, xy[0], xy[1]);
		}
		rendersUsed = false;
		if(toRender.size()>0)
			transferToRendr();
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
		if(!objsUsed)
			this.objs.add(o);
		else
			this.toObj.add(o);
	}
	
	public void addRenderable(Renderable r)
	{
		if(!rendersUsed)
			this.rendrs.add(r);
		else
			this.toRender.add(r);
	}
}
