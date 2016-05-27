package game.world;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import game.Game;
import game.GameObject;
import game.Renderable;
import game.entity.Entity;
import game.graphic.Animation;
import game.util.Task;

public abstract class WorldObject extends Entity implements Renderable
{
	protected ArrayList<Animation> animations,toAnimations;
	protected boolean toAnimationsUsed;
	
    public WorldObject()
    {
    	super(new Location(0,0));
		animations = new ArrayList<Animation>();
		toAnimations = new ArrayList<Animation>();
		toAnimationsUsed = false;
    }
    
    
    public void render(Graphics g, int xo, int yo)
    {
    	renderAnimations(g,xo,yo);
    }
    
    public void addAnimation(final Animation a)
	{
		if(!toAnimationsUsed)
			this.toAnimations.add(a);
		else {
			Game.getCurrentGame().addObject(new Task(1)
					{
				@Override
				public void run()
				{
					addAnimation(a);
				}
					});
		}
	}
	
	public void animationTick()
	{
		ArrayList<Animation> adios = new ArrayList<Animation>();
		for (Animation a : animations)
		{
			a.tick();
			if(a.done())
				adios.add(a);
		}
		for(Animation a : adios)
		{
			animations.remove(a);
		}
		toAnimationsUsed = true;
		for(Animation a : toAnimations)
		{
			animations.add(a);
		}
		toAnimations.clear();
		toAnimationsUsed = false;
	}
	
	
	public void tick()
	{
		animationTick();
	}
	
	public void renderAnimations(Graphics g, int xo, int yo)
	{
		for (Animation a : animations)
		{
			a.render(g, xo, yo);
		}
	}
}
