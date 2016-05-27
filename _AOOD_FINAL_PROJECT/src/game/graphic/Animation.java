package game.graphic;

import game.Renderable;

public abstract class Animation implements Renderable {

	protected int frame;
	protected int maxFrames;
	
	/*
	 * When I say frames I mean ticks
	 */
	public Animation(int maxFrames)
	{
		this.maxFrames = maxFrames;
		frame = 0;
	}
	
	public int getFrame()
	{
		return this.frame;
	}
	
	public int getTotalFrames()
	{
		return maxFrames;
	}
	
	public void reset()
	{
		frame = 0;
	}
	
	public void tick()
	{
		frame++;
		changeFrame();
	}
	
	public boolean done()
	{
		if(maxFrames == -1)
			return false;
		return maxFrames - frame <= 0;
	}
	
	protected abstract void changeFrame();
	
}
